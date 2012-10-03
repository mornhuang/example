#include <iostream.h>

class time
{
    int s,f,m;
    public:
        time(){s=0;f=0;m=0;}
        void set_s(int l=0){s=l;}
        void set_f(int l=0){f=l;}
        void set_m(int l=0){m=l;}
        void display(){cout <<s <<":" <<f <<":" <<m <<endl;}

        time operator+(time other)
        {
            time temp;
            //m++
            if(other.m+m>60)
            {
                temp.m=60-(other.m+m);
                f++;
            }
            else
            {
                temp.m=other.m+m;
            }
            //f++
            if(other.f+f>60)
            {
                temp.f=60-(other.f+f);
                s++;
            }
            else
            {
                temp.f=other.f+f;
            }
            //s++
            if(other.s+s>24)
            {
                temp.s=24-(other.s+s);
            }
            else
            {
                temp.s=other.s+s;
            }
            return temp;
        }

        time operator-(time other)
        {
            time temp;

        //m--
        if(m-other.m<0)
        {
            temp.m=60+(m-other.m);
            f--;
        }
        else
        {
            temp.m=m-other.m;
        }

        //f--
        if(f-other.f<0)
        {
            temp.f=60+(f-other.f);
            s--;
        }
        else
        {
            temp.f=f-other.f;
        }

        //s--
        if(s-other.s<0)
        {
            temp.s=24+(s-other.s);
        }
        else
        {
            temp.s=s-other.s;
        }
        return temp;
    }
};

void main()
{
    time a;
    time b;
    time c;
    a.set_s(5);
    b.set_f(16);
    c.set_m(22);
    time d=a+b;
    d.display();
    time e=c-a;
    e.display();
}

