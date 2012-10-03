#include <iostream.h>
#include <math.h>

const int size=12800;
double *data;

int main()
{
  extern void quick_sort(double *low_ptr,double *high_ptr);
  extern void display_data(double *data);

  data = new double[size];
  for(int i=0;i<size;i++)
	  * (data+i)=sin(i)/(2.4+sqrt(i));
  quick_sort(data,data+size-1);
  display_data(data);
}

void quick_sort(double *low_ptr,double *high_ptr)
{
  double *pivot_ptr;
  extern double *partition(double *low_ptr,double *high_ptr);

  if(low_ptr<high_ptr)
  {
    pivot_ptr=partition(low_ptr,high_ptr);
	quick_sort(low_ptr,pivot_ptr-1);
	quick_sort(pivot_ptr,high_ptr);
  }
}

double *partition(double *low_ptr,double *high_ptr)
{
  void swap(double &value1,double &value2);
  double pivot = *(low_ptr+(high_ptr-low_ptr)/2);
  while(low_ptr<=high_ptr)
  {
    while(*low_ptr<pivot)
		low_ptr++;
	while(*high_ptr>pivot)
		high_ptr--;
	if(low_ptr<=high_ptr)
		swap(*low_ptr++,*high_ptr--);
  }
  return low_ptr;
}

void swap(double &value1,double &value2)
{
  double temp=value1;
  value1=value2;
  value2=temp;
}

void display_data(double *data)
{
  for(int i=0;i<size;i++)
	  cout<<"\n"<<data[i];
}