/**
 * util.c: utility functions for the Atmel platform
 * 
 * For an overview of how timer based interrupts work, see
 * page 111 and 133-137 of the Atmel Mega128 User Guide
 *
 * @author Zhao Zhang & Chad Nelson
 * @date 06/26/2012
 */

#include <avr/io.h>
#include <avr/interrupt.h>
#include "stdio.h"
#include "util.h"
#include "string.h"
#include "math.h"

// Global used for interrupt driven delay functions
volatile unsigned int timer2_tick;
void timer2_start(char unit);
void timer2_stop();

//Global used for servo pwm
unsigned int pulse_interval = 43002;
unsigned int mid_point = 2925;
unsigned int top = 4725;
unsigned int bottom = 1125;
unsigned int pulse_width = 0; //pulse width in cycles

/// Blocks for a specified number of milliseconds
void wait_ms(unsigned int time_val) {
	//Seting OC value for time requested
	OCR2=250; 				//Clock is 16 MHz. At a prescaler of 64, 250 timer ticks = 1ms.
	timer2_tick=0;
	timer2_start(0);

	//Waiting for time
	while(timer2_tick < time_val);

	timer2_stop();
}


// Start timer2
void timer2_start(char unit) {
	timer2_tick=0;
	if ( unit == 0 ) { 		//unit = 0 is for slow 
        TCCR2=0b00001011;	//WGM:CTC, COM:OC2 disconnected,pre_scaler = 64
        TIMSK|=0b10000000;	//Enabling O.C. Interrupt for Timer2
	}
	if (unit == 1) { 		//unit = 1 is for fast
        TCCR2=0b00001001;	//WGM:CTC, COM:OC2 disconnected,pre_scaler = 1
        TIMSK|=0b10000000;	//Enabling O.C. Interrupt for Timer2
	}
	sei();
}


// Stop timer2
void timer2_stop() {
	TIMSK&=~0b10000000;		//Disabling O.C. Interrupt for Timer2
	TCCR2&=0b01111111;		//Clearing O.C. settings
}


// Interrupt handler (runs every 1 ms)
ISR (TIMER2_COMP_vect) {
	timer2_tick++;
}

//Initialize USART communication
void USART_Init(unsigned int ubrr){
	UBRR0H = (unsigned char)(ubrr>>8);
	UBRR0L = (unsigned char)(ubrr);

	UCSR0A |= 0x02;
	UCSR0B = (1<<RXEN)|(1<<TXEN);
	UCSR0C = (1<<USBS)|(3<<UCSZ0);
}

//Transmit one byte character to putty
void USART_Transmit(unsigned char data){
	while(!(UCSR0A & (1<<UDRE)));
	UDR0 = data;
}

//Takes three parameters: degree, pingDistance, and irDistance and transmit them to putty
void USART_Transmit_String1 (char* String, int degree, double pingDistance, double irDistance){
	int i;
	sprintf(String, "%d\t\t%.2f\t\t\t%.2f",degree,pingDistance,irDistance);
	for(i = 0; i<strlen(String); i++){
		
		USART_Transmit(String[i]);
	}
	USART_Transmit(0x0D);
	USART_Transmit(0x0A);
}

//Transmit the number of object detected by IR
void USART_Transmit_String2 (char* String, int numObject_IR){
	int i;
	sprintf(String, "Number of objects found by IR: %d",numObject_IR);
	for(i = 0; i<strlen(String); i++){
		
		USART_Transmit(String[i]);
	}
	USART_Transmit(0x0D);
	USART_Transmit(0x0A);
}

//Initialize ADC
void ADC_init(){
	//Reference voltage, alignment, channel
	ADMUX = 0b11000010;
	//Enable, running mode, interrupt and clock select
	ADCSRA = 0b10100111;
}
//Single-shot ADC reading
int ADC_read(char channel){
	//Select channel
	ADMUX = channel;
	//Start ADC sampling
	ADCSRA |= 0x40;
	
	while(!(ADCSRA & 0x10)){}

	return ADC;
}

//convert digital value to distance(cm)
double D_to_distance(int digitalValue){
	double distance = 0;
	distance = ((pow(digitalValue, -1.096)) * 18494);
	return distance;
}

//calculate and return the average distance measured by IR
double IR_measure_dis(char channel){
	int sensorValue = 0; //initialize sensor value.
	double distance = 0;
	int count = 0;
	int average = 0;
	
	while(count<5){
		sensorValue += ADC_read(channel); //read value from ADC.
		wait_ms(40);
		count++;
	}
	average = sensorValue/5; //take the average of the five readings.
	distance = D_to_distance(average); //convert value to distance.
	return distance;
}

//Initialize ping sensor
void ping_init(){
	TCCR1A = 0x00; //don't care Compare Output Mode. Set Waveform Generator Mode: 00.
	TCCR1B = 0xC2; //set Noise Canceler, Capture rising edge, prescaler = 64.
	TCCR1C = 0x00; //set to zero, Force output compare is not used.
	TIMSK = (1<<TICIE1)|(1<<TOIE1); //Enable Input Capture Interrupt and Overflow Interrupt.
	DDRD = 0; //set port D to input.
	sei();
}

//send out a pulse on PD4
void send_pulse(){
	TIMSK &= (0<<TICIE1)|(0<<TOIE1); //disable interrupts
	
	DDRD |= 0x10; //set PD4 as output
	PORTD |= 0x10; //set PD4 to high
	wait_ms(1);
	PORTD &= 0xEF; //set PD4 to low
	DDRD &= 0xEF; //set PD4 as input
	
	TIFR &= (0<<ICF1); //Clear Input Capture Flag
	TIMSK |= (1<<TICIE1)|(1<<TOIE1); //Enable Input Capture Interrupt and Overflow Interrupt.
}

//Initialize timer3
void timer3_init(){
	OCR3A = pulse_interval - 1; //number of cycles in the interval
	OCR3B = mid_point - 1; //move servo to the middle
	TCCR3A = 0b10101011; //set COM and WGM
	TCCR3B = 0b00011010; //set WGM and CS
	TCCR3C = 0; //don't need
	
	DDRE |= _BV(4); //set Port E pin4 (OCR3B) as output
}

//move servo to a certain degree
void move_servo(unsigned int degree){
	
	//pulse_width = round(degree * 18.47222 + 700); //calculate pulse width -- ROBO#8
	//pulse_width = round(degree * 18.88889 + 1200); //calculate pulse width -- ROBO#6
	pulse_width = round(degree * 20.01 + 800); //calculate pulse width -- ROBO#6
	OCR3B = pulse_width - 1; //set pulse width
	
}
