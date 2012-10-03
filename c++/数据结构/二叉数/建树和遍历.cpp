#include <iostream>
using namespace std;
typedef struct node{ 
int data; 
struct node *lchild,*rchild; 
}*treetp,tree; 
void create (treetp &t,int c); 
void print1(treetp); 
void print2(treetp);
void print3(treetp);
int number=0;
void main() 
{   
  treetp t=0; 
  create (t,0); 
  printf("前序排列 ：");
  print1  (t); 
  printf("\n中序排列 ：");
  print2 (t);
  printf("\n后序排列 ：");
  print3 (t);
} 

void  create(treetp &t,int c) 
{ 
treetp p,di; 
do{ 
scanf("%d",&c); 
if (t==0) 
 { 
 t=new tree; 
 t->lchild=t->rchild=0; 
 t->data=c; 
 } 
else 
 {   p=t; 
  while(p!=0) 
  { 
  di=p; 
  if(c<(p->data)) 
  p=p->lchild; 
  else 
  p=p->rchild; 
  } 
if(c<(di->data)) 
{ 
treetp NEWdi=new tree; 
NEWdi->lchild=NEWdi->rchild=0; 
NEWdi->data=c; 
di->lchild=NEWdi; 
} 
else 
{ 
treetp NEWdi=new tree; 
NEWdi->lchild=NEWdi->rchild=0; 
NEWdi->data=c; 
di->rchild=NEWdi; 
} 
 } 
++number;
}while(c!=0); 
printf("叶子的数量：%d",number);
}   
void print1(treetp  t) 
{ 
   if (t!=0) 
  {     
   printf("%d ",t->data);
   print1(t->lchild); 
   print1(t->rchild); 
   } 
} 
void print2(treetp  t) 
{ 
   if (t!=0) 
  {     
   print2(t->lchild); 
   printf("%d ",t->data); 
   print2(t->rchild); 
   } 
} 
void print3(treetp  t) 
{ 
   if (t!=0) 
  {     
   print3(t->lchild);  
   print3(t->rchild); 
   printf("%d ",t->data);
   } 
} 