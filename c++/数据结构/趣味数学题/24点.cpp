#include <iostream.h>
#include <string.h>
int get1(int (*p1)[4],char (*p2)[3])
{  int temp;
    switch((*p2)[0])
    {    case 0: temp=(*p1)[0]+(*p1)[1]; break;
case 1: temp=(*p1)[0]-(*p1)[1]; break;
case 2: temp=(*p1)[0]*(*p1)[1]; break;
case 3: if ((*p1[1])&&((*p1)[0]%(*p1)[1]==0))
{    temp=(*p1)[0]/(*p1)[1];
      break;
}
else return(0);
    }
    switch((*p2)[1])
    {    case 0: temp+=(*p1)[2]; break;
case 1: temp-=(*p1)[2]; break;
case 2: temp*=(*p1)[2]; break;
case 3: if ((*p1)[2]&&(temp%(*p1)[2]==0))
{    temp=temp/(*p1)[2];
      break;
  }
  else return(0);
    }
      switch((*p2)[2])
    {    case 0: temp+=(*p1)[3]; break;
case 1: temp-=(*p1)[3]; break;
case 2: temp*=(*p1)[3]; break;
case 3: if ((*p1)[3]&&(temp%(*p1)[3]==0))
{    temp=temp/(*p1)[3];
      break;
  }
  else return(0);
    }
    return(temp);
}
int get2(int (*p1)[4],char (*p2)[3])
{  int temp;
    switch((*p2)[1])
    {    case 0: temp=(*p1)[1]+(*p1)[2]; break;
case 1: temp=(*p1)[1]-(*p1)[2]; break;
case 2: temp=(*p1)[1]*(*p1)[2]; break;
case 3: if ((*p1)[2]&&((*p1)[1]%(*p1)[2]==0))
{    temp=(*p1)[1]/(*p1)[2];
      break;
}
else return(0);
    }
    switch((*p2)[0])
    {    case 0: temp=(*p1)[0]+temp; break;
case 1: temp=(*p1)[0]-temp; break;
case 2: temp=(*p1)[0]*temp; break;
case 3: if ((temp)&&((*p1)[0]%temp==0))
{    temp=(*p1)[0]/temp;
      break;
  }
  else return(0);
    }
      switch((*p2)[2])
    {    case 0: temp+=(*p1)[3]; break;
case 1: temp-=(*p1)[3]; break;
case 2: temp*=(*p1)[3]; break;
case 3: if ((*p1)[3]&&(temp%(*p1)[3]==0))
{    temp=temp/(*p1)[3];
      break;
  }
  else return(0);
    }
    return(temp);
}
int get3(int (*p1)[4],char (*p2)[3])
{  int temp;
    switch((*p2)[1])
    {    case 0: temp=(*p1)[1]+(*p1)[2]; break;
case 1: temp=(*p1)[1]-(*p1)[2]; break;
case 2: temp=(*p1)[1]*(*p1)[2]; break;
case 3: if ((*p1)[2]&&((*p1)[1]%(*p1)[2]==0))
{    temp=(*p1)[1]/(*p1)[2];
      break;
}
else return(0);
    }
    switch((*p2)[2])
    {    case 0: temp+=(*p1)[3]; break;
case 1: temp-=(*p1)[3]; break;
case 2: temp*=(*p1)[3]; break;
case 3: if ((*p1)[3]&&(temp%(*p1)[3]==0))
{    temp=temp/(*p1)[3];
      break;
  }
  else return(0);
    }
      switch((*p2)[0])
    {    case 0: temp=(*p1)[0]+temp; break;
case 1: temp=(*p1)[0]-temp; break;
case 2: temp=(*p1)[0]*temp; break;
case 3: if ((temp)&&(*p1)[0]%temp==0)
{    temp=(*p1)[0]/temp;
      break;
  }
  else return(0);
    }
    return(temp);
}
int get4(int (*p1)[4],char (*p2)[3])
{  int temp;
    switch((*p2)[2])
    {    case 0: temp=(*p1)[2]+(*p1)[3]; break;
case 1: temp=(*p1)[2]-(*p1)[3]; break;
case 2: temp=(*p1)[2]*(*p1)[3]; break;
case 3: if ((*p1)[3]&&((*p1)[2]%(*p1)[3]==0))
{    temp=(*p1)[2]/(*p1)[3];
      break;
}
else return(0);
    }
    switch((*p2)[1])
    {    case 0: temp=(*p1)[1]+temp; break;
case 1: temp=(*p1)[1]-temp; break;
case 2: temp=(*p1)[1]-temp; break;
case 3: if ((temp)&&(*p1)[1]%temp==0)
{    temp=(*p1)[1]/temp;
      break;
  }
  else return(0);
    }
      switch((*p2)[0])
    {    case 0: temp=(*p1)[0]+temp; break;
case 1: temp=(*p1)[0]-temp; break;
case 2: temp=(*p1)[0]*temp; break;
case 3: if ((temp)&&(*p1)[0]==0)
{    temp=(*p1)[0]/temp;
      break;
  }
  else return(0);
    }
    return(temp);
}
int get5(int (*p1)[4],char (*p2)[3])
{  int tmp1,tmp2;
    switch((*p2)[0])
    {    case 0: tmp1=(*p1)[0]+(*p1)[1]; break;
case 1: tmp1=(*p1)[0]-(*p1)[1]; break;
case 2: tmp1=(*p1)[0]*(*p1)[1]; break;
case 3: if ((*p1)[1]&&((*p1)[0]%(*p1)[1]==0))
{    tmp1=(*p1)[0]/(*p1)[1];
      break;
}
else return(0);
    }
    switch((*p2)[2])
    {    case 0: tmp2=(*p1)[2]+(*p1)[3]; break;
case 1: tmp2=(*p1)[2]-(*p1)[3]; break;
case 2: tmp2=(*p1)[2]*(*p1)[3]; break;
case 3: if ((*p1)[3]&&((*p1)[2]%(*p1)[3]==0))
{    tmp2=(*p1)[2]/(*p1)[3];
      break;
  }
  else return(0);
    }
      switch((*p2)[1])
    {    case 0: tmp1+=tmp2; break;
case 1: tmp1-=tmp2; break;
case 2: tmp1*=tmp2; break;
case 3: if ((tmp2)&&(tmp1%tmp2==0))
{    tmp1/=tmp2;
      break;
  }
  else return(0);
    }
    return(tmp1);
}
void main()
{    int num[24][4],(*pp1)[4],input[4],goal,i,j,k,m,n;
    char (*pp2)[3],code[64][3];
    cout<<"Goal=";
    cin>>goal;
    cout<<"Input four numbers:";
    for(i=0;i<4;i++)  cin>>input[i];
    m=0;
    for(i=0;i<4;i++)
    for(j=0;j<4;j++)
    for(k=0;k<4;k++)
    {    code[m][0]=i;
  code[m][1]=j;
  code[m][2]=k;
  m++;
    }
    n=0;
    for(i=0;i<4;i++)
    for(j=0;j<4;j++)
    if (j!=i) for(k=0;k<4;k++)
      if ((k!=i)&&(k!=j)) {  m=6-i-j-k;
      num[n][0]=input[i];
      num[n][1]=input[j];
      num[n][2]=input[k];
      num[n][3]=input[m];
      n++;
  }
    m=0;
    for(i=0,pp1=num;i<24;i++,pp1++)
    {    for(j=0,pp2=code;j<64;j++,pp2++)
{  k=get1(pp1,pp2); if (k==goal) {m=1; break;}
    k=get2(pp1,pp2); if (k==goal) {m=2; break;}
    k=get3(pp1,pp2); if (k==goal) {m=3; break;}
    k=get4(pp1,pp2); if (k==goal) {m=4; break;}
    k=get5(pp1,pp2); if (k==goal) {m=5; break;}
}
if(m) break;
    }
    if(m)
    {    char analyst[12];
  for(i=0;i<3;i++)
  switch((*pp2)[i])
  {    case 0:(*pp2)[i]='+'; break;
      case 1:(*pp2)[i]='-'; break;
      case 2:(*pp2)[i]='*'; break;
      case 3:(*pp2)[i]='/';
  }
  switch (m)
  {    //The following may be hard to understand!
      //See how and why to do in the end of the program!
      // ( ( a ? b ) ? c ) ? d          ?=+,-,*,/
      // 0 1 2 3 4 5 6 7 8 9 10
      case 1:strcpy(analyst,"((a?b)?c)?d");
      analyst[3]=(*pp2)[0];
      analyst[6]=(*pp2)[1];
      analyst[9]=(*pp2)[2];
      if((analyst[3]=='*')||(analyst[3]=='/')||
(analyst[6]=='+')||(analyst[6]=='-'))
      {  analyst[1]='0';    analyst[5]='0';  }
      if((analyst[6]=='*')||(analyst[6]=='/')||
(analyst[9]=='+')||(analyst[9]=='-'))
      {  analyst[0]='0';    analyst[8]='0';  }
      break;
      // ( a ? ( b ? c ) ) ? d
      // 0 1 2 3 4 5 6 7 8 9 10
      case 2:strcpy(analyst,"(a?(b?c))?d");
      analyst[2]=(*pp2)[0];
      analyst[5]=(*pp2)[1];
      analyst[9]=(*pp2)[2];
      if((analyst[2]=='+')||(analyst[2]!='/')&&
((analyst[5]=='*')||(analyst[5]=='/')))
      {  analyst[3]='0';    analyst[7]='0';  }
      if((analyst[2]=='*')||(analyst[2]=='/')||
(analyst[9]=='+')||(analyst[9]=='-'))
      {  analyst[0]='0';    analyst[8]='0';  }
      break;
      // a ? ( ( b ? c ) ? d )
      // 0 1 2 3 4 5 6 7 8 9 10
      case 3:strcpy(analyst,"a?((b?c)?d");
      analyst[1]=(*pp2)[0];
      analyst[5]=(*pp2)[1];
      analyst[8]=(*pp2)[2];
      if((analyst[5]=='*')||(analyst[5]=='/')||
(analyst[8]=='+')||(analyst[8]=='-'))
      {  analyst[3]='0';    analyst[7]='0';  }
      if((analyst[1]=='+')||(analyst[1]!='/')&&
((analyst[8]=='*')||(analyst[8]=='/')))
      {  analyst[2]='0';    analyst[10]='0';  }
      break;
      // a ? ( b ? ( c ? d ) )
      // 0 1 2 3 4 5 6 7 8 9 10
      case 4:strcpy(analyst,"a?(b?(c?d))");
      analyst[1]=(*pp2)[0];
      analyst[4]=(*pp2)[1];
      analyst[7]=(*pp2)[2];
      if((analyst[4]=='+')||(analyst[4]!='/')&&
((analyst[7]=='*')||(analyst[7]=='/')))
      {  analyst[5]='0';    analyst[9]='0';  }
      if((analyst[1]=='+')||(analyst[1]!='/')&&
((analyst[4]=='*')||(analyst[4]=='/')))
      {  analyst[2]='0';    analyst[10]='0';  }
      break;
      // ( a ? b ) ? ( c ? d )
      // 0 1 2 3 4 5 6 7 8 9 10
      case 5:strcpy(analyst,"(a?b)?(c?d)");
      analyst[2]=(*pp2)[0];
      analyst[5]=(*pp2)[1];
      analyst[8]=(*pp2)[2];
      if((analyst[2]=='*')||(analyst[2]=='/')||
(analyst[5]=='+')||(analyst[5]=='-'))
      {  analyst[0]='0';    analyst[4]='0';  }
      if((analyst[5]=='+')||(analyst[5]!='/')&&
((analyst[8]=='*')||(analyst[8]=='/')))
      {  analyst[6]='0';    analyst[10]='0';  }
  }
  for(i=0;i<11;i++)
  switch(analyst[i])
  {    case 'a': cout<<(*pp1)[0]; break;
      case 'b': cout<<(*pp1)[1]; break;
      case 'c': cout<<(*pp1)[2]; break;
      case 'd': cout<<(*pp1)[3]; break;
      case '0': break;
      default:  cout<<analyst[i];
  }
  cout<<endl;
    }
    else
    {    for(i=0,pp1=num;i<24;i++,pp1++)
{    j=(*pp1)[0]*(*pp1)[1];
      k=(*pp1)[0]*(*pp1)[2];
              if(k%(*pp1)[3]==0)
              {    k/=(*pp1)[3];
                    if(j+k==goal) {    m=1;  break;  }
                    else if(j-k==goal) {    m=2;  break;  }
else if(k-j==goal) {    m=3;  break;    }
              }
      else
              {    j=(*pp1)[0]*(*pp1)[3];
                    k=(*pp1)[1]*(*pp1)[3];
                    n=(*pp1)[2];
    if((k+n)&&(j%(k+n)==0))
    {    if(j/(k+n)==goal)
{    m=4;  break;    }
                    }
                    else
    if((k-n)&&(j%(k-n)==0))
                    if(j/(k-n)==goal)  {    m=5;  break;  }
                    else if(j/(n-k)==goal) {    m=6;  break;    }
              }
        }
if(!m) cout<<"If you get it,please E-mail to:publicfrk@netease.com\n";
else  switch (m)
      {    case 1:cout<<(*pp1)[0]<<"*("<<(*pp1)[1]<<'+'
  <<(*pp1)[2]<<'/'<<(*pp1)[3]<<')'<<endl;
  break;
    case 2:cout<<(*pp1)[0]<<"*("<<(*pp1)[1]<<'-'
  <<(*pp1)[2]<<'/'<<(*pp1)[3]<<')'<<endl;
  break;
    case 3:cout<<(*pp1)[0]<<"*("<<(*pp1)[1]<<'/'
  <<(*pp1)[2]<<'-'<<(*pp1)[3]<<')'<<endl;
  break;
    case 4:cout<<(*pp1)[0]<<"/("<<(*pp1)[1]<<'+'
  <<(*pp1)[2]<<'/'<<(*pp1)[3]<<')'<<endl;
  break;
    case 5:cout<<(*pp1)[0]<<"/("<<(*pp1)[1]<<'-'
  <<(*pp1)[2]<<'/'<<(*pp1)[3]<<')'<<endl;
  break;
    case 6:cout<<(*pp1)[0]<<"/("<<(*pp1)[1]<<'/'
  <<(*pp1)[2]<<'-'<<(*pp1)[3]<<')'<<endl;
}
    }
}