#define stack_init_size 20
#define stackincrement 5
#include "stdlib.h"
#include "stdio.h"
#include "string.h"
#include "memory.h"

typedef struct 
{
    double *base;
    double *top;
    int    stacksize;
}Sqstack1;

typedef struct 
{
    char *base;
    char *top;
    int stacksize;
}Sqstack2;

Sqstack1 OPND;
Sqstack2 OPTR;
//构造一个空栈OPND
int Initstack(Sqstack1 &OPND)
{
    OPND.base = (double*)malloc(stack_init_size*sizeof(double));
    if(!OPND.base)
    return -1;
    OPND.top = OPND.base;
    OPND.stacksize=stack_init_size;
    return 0;
}
//构造一个空栈OPTR
int Initstack(Sqstack2 &OPTR)
{
    OPTR.base = (char*)malloc(stack_init_size*sizeof(char));
    if(!OPTR.base)
    return -1;
    OPTR.top = OPTR.base;
    OPTR.stacksize=stack_init_size;
    return 0;
}
//把元素e压入栈OPND
int Push ( Sqstack1 &OPND , double e )
{
    if((OPND.top - OPND.base) >= OPND.stacksize)
      {
    OPND.base=(double* )realloc(OPND.base , (OPND.stacksize + stackincrement)*sizeof(double));
    if(!OPND.base) return -1;
    OPND.top = OPND.base+OPND.stacksize;
    OPND.stacksize += stackincrement;
      }
      *OPND.top++ = e;
  return 1;
}
//把元素e压入栈OPTR
int Push ( Sqstack2 &OPTR , char e )
{
    if((OPTR.top - OPTR.base) >= OPTR.stacksize)
      {
    OPTR.base=( char* )realloc(OPTR.base , (OPTR.stacksize + stackincrement)*sizeof(char));
    if(!OPTR.base) return -1;
    OPTR.top = OPTR.base+OPTR.stacksize;
    OPTR.stacksize += stackincrement;
      }
      *OPTR.top++ = e;
  return 1;
}
//把元素e从栈OPND弹出
double Pop ( Sqstack1 &OPND , double &e)
{
    if(OPND.top == OPND.base)return -1;
    e = *--OPND.top;
    return e;
}
//把元素e从栈OPTR弹出
char Pop ( Sqstack2 &OPTR , char &e)
{
    if(OPTR.top == OPTR.base)return -1;
    e = *--OPTR.top;
    return e;
}
//用e返回OPND栈顶元素的值
double Gettop(Sqstack1 OPND, double &e)
{
if(OPND.top == OPND.base) return -1 ;
    e = *(OPND.top - 1) ;
return e;
}
//用e返回OPTR栈顶元素的值
char Gettop(Sqstack2 OPTR , char &e)
{
if(OPTR.top == OPTR.base) return -1 ;
    e = *(OPTR.top - 1) ;
return e;
}
//判断读入的是不是字符,是返回1,否返回0
int Is_op (char op)
{
switch (op)
{
case '+' :
case '-' :
case '*' :
case '/' :
case '(' :
case ')' :
case '=' : return 1; break;
default: return  0 ;
}
}
//对运算符栈中的元素进行赋值，并将值返回
int isp(char op)
{
switch(op)
{
case '=' : return 0; break;
case '(' : return 1; break;
case '+' :
case '-' : return 3; break;
case '*' :
case '/' : return 5; break;
    case ')' : return 6; break;
default: return  -1 ;
}
}
//对将要读入的运算符进行赋值，并将值返回
int icp(char op)
{
switch(op)
{
case '=' : return 0; break;
case ')' : return 1; break;
case '+' :
case '-' : return 2; break;
case '*' :
case '/' : return 4; break;
case '(' : return 6; break;
default: return  -1;
}
}
//弹出两个运算数和一个运算符进行二元运算，并将值再次压入运算数栈
double Operate(double operand1 , char op , double operand2)
{
switch(op)
{
case '+' : return(operand1 + operand2); break;
case '-' : return(operand1 - operand2); break;
case '*' : return(operand1 * operand2); break;
case '/' : return(operand1 / operand2); break;
}
return 0 ;
}
//运算符的优先级比较
char Precede(char e1 , char e2)
{
if(e1-e2<0)
return '<';
    else if(e1-e2 == 0)
return '=';
    else 
return '>';
}

void main( )
{  
char express[30], num[10], theta, tp , d;//express[30]用来读入一个表达式
    double a ,b , result, tps;          //num[10]用来存放表达式中连接着的数字字符
int t, i, j;
int position = 0;//表达式读到的当前字符
Initstack(OPND); 
Initstack(OPTR); Push(OPTR , '=');
printf("input 'b' to run the calc:\n");
scanf("%c" , &d);
getchar();
while(d == 'b')
{
printf( "请输入表达式( 可使用+、-、*、/、(、)、= ): " ) ;
gets(express);
while(express[position] != '='||Gettop(OPTR , tp) != '=' )
{
if(!Is_op(express[position])) 
{ //用两个计数器t和j实现浮点数的读取
t = position;j=0;
while(!Is_op(express[position]))
{
position++;
}
for(i = t; i<position ; i++ )
{//把表达式中第t到position-1个字符赋给num[10]
num[j] = express[i];
j++;
}
Push(OPND , atof(num));
    memset(num,0,sizeof(num));//将num[10]清空
}
else
{
switch(Precede(isp(Gettop(OPTR , tp)),icp(express[position])))
{
case '<':
Push(OPTR,express[position]);position++;break;
    case '=':
    Pop(OPTR , tp) ;position++;break; 
    case '>':
{
Pop(OPTR , theta) ;
        Pop(OPND , b) ; 
        Pop(OPND , a) ; 
        result = Operate(a, theta ,b);
        Push(OPND , result);break;  
}//end sase
}//end switch
}//end else
}//end while

printf("%s%8.4f\n",express,Pop(OPND,tps));

    memset(express , 0 , sizeof(express));
    position = 0;
    printf("input 'b' to run the calc again:\n");
    scanf("%c" , &d);
    getchar();
}//end while
}
