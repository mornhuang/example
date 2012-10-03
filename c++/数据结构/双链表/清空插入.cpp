//其实你的水平很高，你的程序有点复杂了。
#include<iostream.h> 
#include<stdlib.h> 
#include<stdio.h> 
#include<conio.h> 
class stacknode{               
     private: 
    stacknode *next; 
    stacknode *pre; 
    int   data; 
     public: 
stacknode(stacknode *n,stacknode *p,int i):next(n),pre(p),data(i){} 
 friend class stack; 
}; 
class stack{                   // 包含各种方法的栈类 
public: 
stack()                        //此处加了一个构造函数
{                              
front=NULL;                    //front成了头接点
rear=NULL;
}
stacknode *erw;
void pop(void); 
int tops(void); 
void display(void); 
int empty(void); 
void makenull(void); 
void push(int item);          //有返回值影响结果

private:
	stacknode *front;
	stacknode *rear;
}; 

void stack::push(int item){ 
stacknode *newnode=new stacknode(NULL,rear,item); 
newnode->data=item;
if(front==NULL)
{
	front=newnode;
	front->next=NULL;
	front->pre=NULL;
	rear=front;
}
else
{
	rear->next=newnode;
	newnode->next=NULL;
	newnode->pre=rear;
	rear=newnode;
}
} 
int stack::tops(void){ 
if(empty()){ 
cout<<"There is nothing!"<<endl; 
   return NULL; 
} 
else 
return rear->data; 
} 
int stack::empty(void){ 
if(front==NULL) 
return 1;     
else 
return 0; 
} 
void stack::pop(void){ 
if(empty()) 
cout<<"There is nothing!"<<endl; 
else if(front==rear){ 
makenull(); 
cout<<"This is end!"<<endl; 
} 
else 
rear=rear->pre; 
} 
void stack::makenull(){ 
front=NULL; 
rear=NULL; 
} 
void stack::display(void){ 
stacknode *ptr; 
ptr=front; 
while(ptr!=NULL){ 
cout<<ptr->data<<"->"<<endl; 
ptr=ptr->next; 
} 
} 
//main function 
void main(void){ 
int arry; 
stack Q; 
char c; 
   cout<<"Please press any key to create stack!"<<endl; 
   getch(); 
   cout<<"Now input the first data."<<endl; 
   cin>>arry; 
   Q.push(arry); //因为改为无返回值原来的Q.front=Q.push(arry); 就不成立了
   cout<<"please input another 4 data..."<<endl; 
   for(int i=0;i<4;i++){ 
       cout<<"input..."<<endl; 
       cin>>arry; 
       Q.push(arry); 
} 
    cout<<"The list is.."<<endl; 
    Q.display(); 
    cout<<"You can makenull(m)/pop(p)/top(t)/push(s)"<<endl; 
    do{ 
       cout<<"Do you want to continue(y/n)?"<<endl; 
       c=getchar(); 
       if(c=='y'){ 
           cout<<" Which function do you want to do (m/p/t/s)?"<<endl; 
           cin>>c; 
           switch(c){ 
          case 'm': 
            Q.makenull(); 
            break; 
          case 'p': 
            Q.pop(); 
            break; 
          case 't': 
            cout<<"The top data is %d"<<Q.tops()<<endl; 
            break; 
          case 's': 
            cout<<" Please input the data..."<<endl; 
            cin>>arry; 
            Q.push(arry); 
            break; 
}
     cout<<"Now the list is :"<<endl; 
     Q.display(); 
}  
}while(c!='n');
cout<<" Press any key to quit...."<<endl; 
getch(); 
exit(1); 
} 