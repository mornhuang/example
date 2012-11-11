#include <iostream.h> 
#include <stdlib.h> 
#include <string.h> 
int compare(const void *a,const void *b);
char *list[5]={"cat","car","cab","zap","can"}; //这里用的是指针
//实际是list[5][4]={"cat","car","cab","zap","can"};
void main() 
{ qsort((void*)list,5,sizeof(list[0]),compare); 
 for(int i=0;i<5;i++) 
     cout<<list[ i ]<<endl; 
} 
int compare (const void* a,const void* b) 
{ return strcmp( * (char * *)a,*(char * *)b);} 
//(char * *)a是二级指针，(char * )a是一级当然不一样
//二级指针的操作是以list[x][y]中的x来操作的相当于是
//操作的字符串。而一级指针是以y为单位来操作的，操作
//的是单个的字符，给你一个小程序很能说明这个问题

#include<stdio.h>
#define T "%s,%s\n"
main()
{
	static char *x[]={"abcd","efgh","mnpq"};
	char **y,**z;
	y=x+1;
	z=x+2;
	printf(T,*x,*x+1);
	printf(T,*y,*z);
}