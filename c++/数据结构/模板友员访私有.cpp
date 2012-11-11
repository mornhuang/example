#include <iostream>
using namespace std;

void test(); //main()不能作为模版类的友元，所以...

template<class T>
class base  {
friend void test();
public:
T* pub;
T* pri;
private:
void fx(void);
};
template<class T>
void base<T>::fx(void) {
this->pub=static_cast<T*>(this);
this->pri=static_cast<T*>(this);

this->pub->pubfx();//ok
this->pri->prifx();//no,can not access a member
}

class dev : public base<dev> {
friend class base<dev>;
public:
void pubfx(void);
private:
void prifx(void);
};

void dev :: pubfx(void)
{
cout<<"public"<<endl;
}

void dev::prifx(void)
{
cout<<"private"<<endl;
}

void main(){
test(); 
}

void test() {
base<dev>* p = new dev;
p->fx();
}