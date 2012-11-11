#include <iostream.h> 
typedef struct node { 
int data; 
struct node *next; 
}stack; 

void push(stack *&top,int); 
int pop(stack *&top); 
//int empstack (stack*); 
void  main() 
{ 
int x=0; 

stack *top; 
top=new stack; 
top->data=0; 
top->next=0; 
push(top,3); 
push(top,7); 
while(top->next!=0)//这里加了一个循环不然只输出一个结果 
{ 
x=pop(top); 
cout <<x<<endl; 
} 
} 
void push (stack *&top,int x) 
{ 
stack *p;         //看来这里是正确的我的理解有误 
p=new stack; 
p->data=x; 
p->next=top; 
top=p; 
} 
int pop(stack *&top) 
{ 
int x; 
x=top->data; 
//stack *temp; 被注释掉的语句没有起作用 
//temp=top; 
if(top->next!=0) 
{ 
top=top->next; 
//delete temp; 
return x; 
} 
//delete temp; 
cout<<"The stack yet empty!"<<endl; 
return 0; 
} 


/*int empstack(stack *top) 
{ 
if (top->next!=0) 
cout<<"the stack isn't empty!"; 
else 
cout<<"Empty!"; 
return 0; 

}   */ 