#include"iostream.h" 
#include"stdio.h" 

const int NUM=5; 
const int GAME=24; 
int array[NUM]; 
char symbol[NUM+1]=" +-*/"; 
int a,b,c,d; 
int degree=0; 
void output(int,int,int); 
void out(int,int,int); 

void main() 
{ 
int p,q,r,s; 
int k,m,n; 
int temp,wap; 
int num,j; 


cout<<"请输入四个数:"<<endl; 
for(j=1;j<NUM;j++) 
cin>>array[j]; 
for(p=1;p<NUM;p++) 
for(q=1;q<NUM;q++) 
for(r=1;r<NUM;r++) 
for(s=1;s<NUM;s++) 
{//sssssssssssssssssssssssssssssssssssssssssssss 
if(q!=p&&r!=p&&r!=q&&s!=p&&s!=q&&s!=r) 
{a=array[p];b=array[q];c=array[r];d=array[s];} 
else continue; 
for(k=1;k<NUM;k++) 
{ 
switch(k) 
{//k 
case 1: 
num=a+b;break; 
case 2: 
if(a>=b) 
num=a-b; 
else continue;break; 
case 3: 
num=a*b;break; 
case 4: 
if(a>=b&&b!=0&&a%b==0) 
num=a/b; 
else continue;break; 
} 
wap=num; 
for(m=1;m<NUM;m++) 
{//m 
num=wap; 
switch(m)//###################### 
{ 
case 1: 
if(k==3) 
{ 
if(a*b+c*d==GAME) 
output(k,m,3); 
if((c>=d&&d!=0&&c%d==0)&&(a*b+c/d==GAME)) 
output(k,m,4); 
} 
if(k==4) 
{ 
if(a/b+c*d==GAME) 
output(k,m,3); 
if((c>=d&&d!=0&&c%d==0)&&(a/b+c/d==GAME)) 
output(k,m,4); 
} 
num=num+c; 
break; 
case 2: 
if(k==3) 
{ 
if(a*b>=c*d&&(a*b-c*d==GAME)) 
output(k,m,3); 
if((c>=d&&d!=0&&c%d==0&&a*b>=c/d)&&(a*b-c/d==GAME)) 
output(k,m,4); 
} 
if(k==4) 
{ 
if(a/b>=c*d&&(a/b-c*d==GAME)) 
output(k,m,3); 
if((c>=d&&d!=0&&(c%d==0))&&a/b>c/d&&(a/b-c/d==GAME)) 
output(k,m,4); 
} 
if(num>=c) 
num=num-c; 
else continue; 
break; 
case 3: 
num=num*c;break; 
case 4: 
if(num>=c&&c!=0&&num%c==0) 
num=num/c; 
else continue;break; 
}//############################# 
temp=num; 
for(n=1;n<NUM;n++) 
{//n 
num=temp; 
switch(n) 
{ 
case 1: 
num=num+d;break; 
case 2: 
if(num>=d) 
num=num-d; 
else continue;break; 
case 3: 
num=num*d;break; 
case 4: 
if(num>=d&&d!=0&&num%d==0) 
num=num/d; 
else continue;break; 
} 
if(num==GAME) 
{ 
out(k,m,n); 
//goto END; 
} 
}///n 
}///m 
}///k 
}//sssssssssssssssssssssssssssssssssssssssssssssssssssssss 
//END:; 
if(!degree) 
cout<<"对不起，这四个数无解。"<<endl; 
} 
void output(int k,int m,int n) 
{ 
degree=1; 
printf("%d%c%d%c%d%c%d\t",a,symbol[k], 
b,symbol[m],c,symbol[n],d); 
} 
void out(int k,int m,int n) 
{ 
degree=1; 
if(n==1||n==2) 
{//n 
if(m==1||m==2) 
{//m 
printf("%d%c%d%c%d%c%d\t",a,symbol[k], 
b,symbol[m],c,symbol[n],d); 
}else///m 
{ 
if(k==1||k==2) 
printf("(%d%c%d)%c%d%c%d\t",a,symbol[k], 
b,symbol[m],c,symbol[n],d);//(a+b)*c 
else 
printf("%d%c%d%c%d%c%d\t",a,symbol[k], 
b,symbol[m],c,symbol[n],d);//a*b*c 
}////m 
}else///n 
{ 
if(m==1||m==2) 
{//m 
printf("(%d%c%d%c%d)%c%d\t",a,symbol[k], 
b,symbol[m],c,symbol[n],d);//(a+b+c)*d 
}else///m 
{ 
if(k==1||k==2) 
printf("(%d%c%d)%c%d%c%d\t",a,symbol[k], 
b,symbol[m],c,symbol[n],d);//(a+b)*c*d 
else 
printf("%d%c%d%c%d%c%d\t",a,symbol[k], 
b,symbol[m],c,symbol[n],d); 
}////m 
}////n 
} 