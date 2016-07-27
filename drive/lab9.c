/*
 * lab9.c
 *
 * Created: 3/24/2015 6:11:58 PM
 *  Author: sc9220
 */ 


#include <avr/io.h>
#include <avr/interrupt.h>
#include "util.h"
#include "stdio.h"
#include "lcd.h"
#include "open_interface.h"
#include "string.h"
#include "math.h"


#define FOSC 16000000
#define BAUD 57600
#define MYUBRR FOSC/8/BAUD-1

//ADC channel 2
char ADC_channel = 0b11000010;

//Ping variables
volatile int overflow = 0;
volatile int fallEdge = 0;
volatile int riseEdge = 0;
volatile int delta = 0;
volatile double time = 0;
volatile double current_ping_distance = 0;
volatile double last_ping_distance = 0;

//Ping Interrupts
ISR(TIMER1_OVF_vect) {
	overflow++;
}

ISR(TIMER1_CAPT_vect){
	if(TCCR1B & 0x40){
		riseEdge = ICR1;
		TCCR1B = 0x82; //set to capture falling edge.
	}
	else{
		fallEdge = ICR1;
		TCCR1B = 0xC2; //set to capture rising edge.
		delta = fallEdge - riseEdge;
		time = 0.0005 * delta;
		current_ping_distance = 34 * time / 2 - 29.5;
	}
	
}

int main(void)
{
	USART_Init(MYUBRR);//initialize transmission
	ADC_init();//initialize ADC.
	ping_init();
	timer3_init(); //initialize timer3 registers
	lcd_init();
	
	//variables that keep updated
	char ch[150]; //string to transmit for every 2 degree
	double current_IR_distance = 0; 
	double last_IR_distance = 0;
	int degree = 0;
	
	//transmit the title of the table to Putty
	char title[] = "Degree\t\tSonar Distance (cm)\tIR Distance (cm)";
	int i;
	for(i = 0; i<strlen(title); i++){
		
		USART_Transmit(title[i]);
	}
	USART_Transmit(0x0D);
	USART_Transmit(0x0A);
	wait_ms(500);

	//Extra variables to help track distance data
	int IR_width = 0;
	int Ping_width = 0;
	
	int numObject_IR=0;
	int numObject_Ping = 0;
	
	int IR_object_degree[50];
	int Ping_object_degree[50];
	
	int IR_object_size[50];
	int Ping_object_size[50];
	
	double IR_object_distance[50];
	double Ping_object_distance[50];
	
	double IR_distance[181];
	double Ping_distance[181];
  
		
		//set servo to initial position
		move_servo(0);
		degree = 0;
		wait_ms(1200);
		
		while(degree<180){ //every 180 degrees, do...
			
			send_pulse();
			move_servo(degree);
			
			current_IR_distance = IR_measure_dis(ADC_channel); //calculate distance IR
			
			IR_distance[degree] = current_IR_distance;	//store the distance measurements into two arrays
			Ping_distance[numObject_Ping] = current_ping_distance;
			
			if ((current_IR_distance>34)&&(current_IR_distance<81)){	//when looking at start and middle of object

				IR_width+=2;
				
			}

			if((last_IR_distance>34)&&(last_IR_distance<81)&&(current_IR_distance>81)){	//when looking at end of object
				
				numObject_IR++;
				IR_object_size[numObject_IR] = IR_width;
				IR_object_degree[numObject_IR] = degree - IR_width/2;	
					
					if(IR_object_degree[numObject_IR] %2){IR_object_degree[numObject_IR]++;}
				
				IR_object_distance[numObject_IR] = IR_distance[IR_object_degree[numObject_IR]];
				IR_width = 0;
				
			}

			last_IR_distance = current_IR_distance;//update last to current
			last_ping_distance = current_ping_distance;

			wait_ms(25);
			USART_Transmit_String1(ch,degree,current_ping_distance,current_IR_distance); //transmit to putty
			wait_ms(25);
			degree += 2;
		}
		
		//number of objects found by IR
		char temp_IR[50];
		USART_Transmit_String2(temp_IR,numObject_IR);
		
		//number of objects found by Ping
		//smallest object found by IR
		int j;
		int minIndex = 1;
		for(j = 1; j<(numObject_IR+1); j++){
			if(IR_object_size[minIndex] > IR_object_size[j]){
				minIndex = j;
			}
		}
		move_servo(IR_object_degree[minIndex]); //move servo to the smallest object
		//calculate the width of the object
		double width = 0;
		width = 2 * (IR_object_distance[minIndex]) * (tan(IR_object_size[minIndex] / 360.0 * 3.1415926));
		//print information on LCD
		lprintf("Index: %d\nAngular width: %d\nDist (cm): %.2f\nWidth (cm): %.2f", minIndex, IR_object_size[minIndex], IR_object_distance[minIndex], width);

}