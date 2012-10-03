#include <iostream.h>
class Time{
public:
    Time()
    { h = m = 0;
    }
    Time(int hr, int min)
    { if (hr>=0 && hr<24) h = hr; else h = 0;
          if (min>=0 && min<60) m = min; else m = 0;
		  cout<<h<<"\n"<<m<<endl;
          }
  private:
    int h, m;
};
///
// µ¥Á´±í£º
struct QueueNode{
    Time val;
    QueueNode *next;
};
//////////////
class Queue{
private:
    struct QueueNode *f,*b;
public:
        Queue(){
            f=b=new QueueNode;
            b->next=NULL;

        }
        Queue(Time val){

                      f=b=new QueueNode;
                      b->val=val;
                    b->next=NULL;
                    }
                  add(Time i){
QueueNode *newcell = new QueueNode;
newcell->val=i;
newcell->next=NULL;
b->next=newcell;
b=newcell;
}
}
//////////////
  main(){
  Time t(12,12);
  Time t2(3,3);
  Queue q;
  q.add(t);
}  