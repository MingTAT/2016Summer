#include "stdio.h"

// calculate the sum and product of two integers
void sum_and_product (int *sum, int *product, int *x)
{
  *sum = *x + *(x+1);
  *product = *x * *(x+1);
}

// example of calling sum_and_product()
void call_example()
{
  int sum, product;
  int myarray[2] = {10, 20}; // compute 10 + 20 and 10*20

  sum_and_product (&sum, &product, myarray);

  printf("sum = %d, product = %d", sum, product);
  // Desired output of printf: sum = 30, product = 200
}

void main(){call_example();}
