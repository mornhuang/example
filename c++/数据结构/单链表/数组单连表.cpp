#include <iostream.h> 
#include <ctype.h> 

//#include "stdafx.h" 这个是window程序vc自动生成的东西，你的程序是dos程序
//没有必要通过生成应用程序向导来做。把你的原文件用记事本另存为*.cpp文件。
//然后在此文件上单击右键从打开方式中选择vc。vc启动后会有一个提示框，问你
//是否建立工作空间文件，单击确定就可以调试了。你的程序是正确的可以运行

class animalType 
{ 
char breed[40]; 
public: 
void getBreed() 
{ 
cout<<"What is the breed."; 
cin>>breed; 
} 
void prebreed(void) 
{ 
cout<<"\nThe animal's breed is"<<breed; 

} 
}; 
int main(int argc, char* argv[]) 
{ 
animalType * animal[25]; 
int num=0; 
char ans; 
do 
{ 
animal[num]=new animalType; 
animal[num++]->getBreed(); 
cout << "Do you want to enter another animal(Y/N)?"; 
cin >> ans; 
} 
while(toupper(ans)!='N'); 
for(int ctr=0;ctr<num;ctr++) 
{ 
animal[ctr]->prebreed(); 
} 
return 0; 
} 