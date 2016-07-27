/// Blocks for a specified number of milliseconds
void wait_ms(unsigned int time_val);

void USART_Init(unsigned int ubrr);

void USART_Transmit(unsigned char data);

void USART_Transmit_String1 (char* String, int degree, double pingDistance, double irDistance);

void USART_Transmit_String2 (char* String, int numObject_IR);

void ADC_init();

int ADC_read(char channel);

double D_to_distance(int digitalValue);

double IR_measure_dis(char channel);

void ping_init();

void send_pulse();

void timer3_init();

void move_servo(unsigned int degree);