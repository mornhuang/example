#include<iostream.h>
//#include <iomanip.h>
#include<stdlib.h>
template<class T>
class Store
{
  private:
      T item;
      int haveValue;
  public:
      Store(void){}
      T GetElement(void);
      void PutElement(T x);
};
template<class T>
T Store<T>::GetElement(void)
{
if (haveValue==0)
  {
    cout<<"No item present!"<<endl;
    exit(1);
  }
return item;
}
template<class T>
void Store<T>::PutElement(T x)
{
  haveValue++;
  item=x;
}

struct Student
{
int  studID;
float gpa;
};

void main(void)
{
    Student graduate={1000,23};
    Store<int>A,B;
    Store<Student>S;
    Store<double>D;
    A.PutElement(-7);
	B.PutElement(9);
    cout<<A.GetElement()<<" "<<B.GetElement()<<endl;
    S.PutElement(graduate);
    cout<<"The student ID is"<<S.GetElement().studID<<endl;
    cout<<"Retrieving object D";
    cout<<D.GetElement()<<endl;
}