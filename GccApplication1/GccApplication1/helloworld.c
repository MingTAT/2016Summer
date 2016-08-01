#include "lcd.h"
#include "util.h"
#include "string.h"

/// Simple 'Hello, world' program
/**
 * This program prints "Hello, world" to the LCD screen
 * @author Chad Nelson
 * @date 06/26/2012
 */
int main (void) {
	// Initialize the the LCD.  This also clears the screen.
	lcd_init(); 
	char name[]= "                   Microcontrollers are lots of fun!                    ";
	char space[]="                   ";

	
	// Clear the LCD screen and print "Hello, world" on the LCD
	//lprintf("Hello, world");
	while(1){
	for(int i =0; i<strlen(name) - 20; i++){
		for(int j = 0; j<20; j++){
			space[j] = name[i+j];
		}
		lprintf("%s", space);
		wait_ms(300);
	}
	}
	
	// lcd_puts("Hello, world");// Replace lprintf with lcd_puts
                                 // step through in debug mode
                                 // and explain to TA how it
                                 // works

	// Notes: Open util.h to see what functions are available for you to use.
	// You will need to use these functions in future labs.
    
	// It is recommended that you use only lcd_init(), lprintf(), lcd_putc, and lcd_puts from lcd.h.
    
	return 0;
}

