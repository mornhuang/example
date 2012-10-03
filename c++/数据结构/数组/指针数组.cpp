#include <iostream.h>
int main()
{
  extern void print_array(float *value,
	  float *end_of_array);
  const int size=1000;
  float *data=new float[size];
  float *end_ptr=data+size-1;
  for(int i=0;i<size;i++)
	  data[i]=3*i+12;
  print_array(data,end_ptr);
}

void print_array(float *value,float *end_of_array)
{
  while(value!=end_of_array)
	  cout<<"\n"<<*value++;
}