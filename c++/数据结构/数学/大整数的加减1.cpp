#include <iostream.h>
const int MAXINPUTBIT=100;
const int MAXRESULTBIT=500;

class LargeNumber{
	int i,j;
	int temp;
	int one[MAXINPUTBIT+1];
	int onebit;			//one的位数
	int two[MAXINPUTBIT+1];
	int twobit;			//two的位数
	int result[MAXRESULTBIT+1];
public:
	LargeNumber();
	~LargeNumber();
	int  inputone();		//出错返回0，否则返回1;
	int  inputtwo();		//同上
	void add();				//加
	void sub();				//减
	void multiplication();	//乘
	void division();		//除
	void clearresult();		//清零
	void showresult();		//显示
};
LargeNumber::LargeNumber()
{
	for(i=0;i<=MAXINPUTBIT;i++)
	{
		one[i]=0;
		two[i]=0;
	}
	onebit=0;
	twobit=0;
	inputone();
	inputtwo();
}
LargeNumber::~LargeNumber()
{
}
int LargeNumber::inputone()
{
	char Number[MAXINPUTBIT+1];
	cout<<"Please enter one:";
	cin>>Number;
	i=0;j=MAXINPUTBIT;
	while(Number[i]!='\0')
		i++;
	onebit=i;
	for(i--;i>=0;i--,j--)
	{
		if(int(Number[i])>=48&&int(Number[i])<=57)
			one[j]=int(Number[i]-48);	//由字符化为数字
		else
			return 0;
	}
	//for(i=MAXINPUTBIT+1-onebit;i<=MAXINPUTBIT;i++)
	//	cout<<one[i];
	//cout<<endl<<onebit;
	//cout<<endl;
	return 1;
}
int LargeNumber::inputtwo()
{
	char Number[MAXINPUTBIT+1];
	cout<<"Please enter two:";
	cin>>Number;
	i=0;j=MAXINPUTBIT;
	while(Number[i]!='\0')
		i++;
	twobit=i;
	for(i--;i>=0;i--,j--)
	{
		if(int(Number[i])>=48&&int(Number[i])<=57)
			two[j]=int(Number[i]-48);	//由字符化为数字
		else
			return 0;
	}
	//for(i=MAXINPUTBIT+1-twobit;i<=MAXINPUTBIT;i++)
	//	cout<<two[i];
	//cout<<endl<<twobit;
	//cout<<endl;
	return 1;
}
void LargeNumber::add()					//加法
{
	clearresult();
	for(i=MAXINPUTBIT,j=MAXRESULTBIT;i>0;i--,j--)///////////////
	{
		temp=one[i]+two[i];
		result[j]+=temp;
		if(result[j]>9)
		{
			result[j-1]=1;
			result[j]=result[j]%10;
		}
	}
	cout<<"one+two=";
	showresult();
}
void LargeNumber::sub()					//减法
{
	clearresult();
	for(i=MAXINPUTBIT,j=MAXRESULTBIT;i>0;i--,j--)////////////
	{
		temp=one[i]-two[i];
		result[j]+=temp;
		if(result[j]<0)
		{
			result[j-1]-=1;
			result[j]+=10;
		}
	}
	if(result[j]<0)        //if(one<tow)
	{
		clearresult();
		for(i=MAXINPUTBIT,j=MAXRESULTBIT;i>0;i--,j--)////////////
		{
			temp=two[i]-one[i];
			result[j]+=temp;
			if(result[j]<0)
			{
				result[j-1]-=1;
				result[j]+=10;
			}
		}
		cout<<"one-two=-";//只是多输出了一个负号
	}else
		cout<<"one-two=";
	showresult();
}
void LargeNumber::showresult()
{
	i=0;
	while(result[i]==0&&i<=MAXRESULTBIT)
		i++;
	if(i>MAXRESULTBIT)
		cout<<"0";			//输出0
	for(;i<=MAXRESULTBIT;i++)
		cout<<result[i];
	cout<<endl;
}
void LargeNumber::division()			//乘法
{
	clearresult();
	int m;
	for(i=MAXINPUTBIT;i>=0;i--)//////////
	{
		temp=two[i];
		for(j=MAXRESULTBIT-(MAXINPUTBIT-i),m=MAXINPUTBIT;
							m>=0;m--,j--)/////////////
		{
			result[j]+=temp*one[m];
			if(result[j]>9)
			{
				result[j-1]+=result[j]/10;
				result[j]%=10;
			}
		}
	}
	cout<<"one*two=";
	showresult();
}

void LargeNumber::clearresult()
{
	for(i=0;i<=MAXRESULTBIT;i++)
		result[i]=0;

}

void main()
{
	LargeNumber a;
	a.add();
	a.sub();
	a.division();
}