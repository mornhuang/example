//THE PROGRAM IS TO SET A GAME BETWEEN PEOPLE & COMPUTER
//FILE APPLE.CPP

#include <stdio.h>
#include <conio.h>

int  CHANCE,FIRST,MODEL,ANSWER;
long LASTGET,WANTGET,SUMAPPLE,FIBONACI[46];
void INITIAL();                         //To  Init      Environment
long FIBFUNCTION(int NUM);              //To  Form      Fibonacci List
void GAME();                           	//The Slow      Model Game
void PERSON();                          //The Person    Function
void RESULT();						//To  See the   Result
void MACHINESLOW();                     //The Slow      Machine Function
void MACHINEQUICK();                    //The Quick     Machine Function
int  GETMINFIB(int CURRENTAPPLES);      //To  Get a     Minimum Fibonacci

int main(void)
{
	INITIAL();
	GAME();                            //To Play the Game of Apple(s)
	getch();                           //To See  the Result
	RESULT();                          //To See  the Display
	getch();
	return 0;
}

long FIBFUNCTION(int NUM)
{
	long FIB1=1,FIB2=1,FIB3;
	int CIRCLE;
	if (NUM<=1) return 1;
	for (FIB3=FIB1+FIB2,CIRCLE=2;CIRCLE<NUM;CIRCLE++)
	{
		FIB1=FIB2;
		FIB2=FIB3;
		FIB3=FIB1+FIB2;
	}
	return FIB3;
}

void INITIAL()
{
	
	printf("--- Please input the sum number of the apple(s) (sum>=1) : ");
	scanf("%ld",&SUMAPPLE);
	printf("\n--- Please take the chance(0---YOU FIRST / 1---I FIRST): ");
	scanf("%d",&CHANCE);
	printf("\n--- Please take the type(0---SLOW MODEL/ 1---QUICK MODEL): ");
	scanf("%d",&MODEL);
	if (SUMAPPLE>1)
		printf("\n--- The first time both of us can only choose 1 - %ld\n"
			 ,SUMAPPLE-1);
	else printf("\n--- The first time both of us can only choose 1 !\n");
	for (int circle=0;circle<46;circle++) FIBONACI[circle]=FIBFUNCTION(circle);
	FIRST=1;
	LASTGET=0;
}

int GETMINFIB(int CURRENTAPPLES)
{
	int circle=45,FOUND=0;               //The above limit of the Fibonacci
	while ((circle>=0) && (FOUND==0))
	{
		while (CURRENTAPPLES<FIBONACI[circle]) circle=circle-1;
		if (CURRENTAPPLES==FIBFUNCTION(circle)) FOUND=1;
		else
		{
			CURRENTAPPLES=CURRENTAPPLES-FIBFUNCTION(circle);
			circle=circle-1;
		}
	}
	return FIBFUNCTION(circle);
}

void MACHINESLOW()
{
	WANTGET=GETMINFIB(SUMAPPLE);
	if ((FIRST) && ((SUMAPPLE-GETMINFIB(SUMAPPLE))!=0))
		WANTGET=GETMINFIB(SUMAPPLE);
	if ((FIRST) && ((SUMAPPLE-GETMINFIB(SUMAPPLE))==0)) WANTGET=1;
	if ((WANTGET>2*LASTGET) && (!FIRST)) WANTGET=1;
	LASTGET=WANTGET;
	SUMAPPLE=SUMAPPLE-WANTGET;
	printf("\n***** I get %ld apple(s).\n",LASTGET);
	printf("\n----- The Current Apple(s) is/are : %ld\n",SUMAPPLE);
	CHANCE=0;
	FIRST=0;
	if (SUMAPPLE==0) printf("\n***** I win the GAME !");
	ANSWER=0;
}

void MACHINEQUICK()
{
	int TRY1,TRY2;
	TRY1=TRY2=SUMAPPLE;
	WANTGET=GETMINFIB(SUMAPPLE);                      //Initial get
	if ((FIRST) && (SUMAPPLE==1)) WANTGET=1;          //Only one apple
	while ((FIRST) && (WANTGET*2<(SUMAPPLE-WANTGET)))
	{
		TRY1=TRY1-GETMINFIB(TRY1);
		WANTGET=WANTGET+GETMINFIB(TRY1);
		if (WANTGET*2>GETMINFIB(SUMAPPLE-WANTGET))
		{	
			WANTGET=WANTGET-GETMINFIB(TRY1);
			break;
		}
	}
	if (((FIRST) && ((SUMAPPLE-GETMINFIB(SUMAPPLE))==0)) && (SUMAPPLE!=1))
		WANTGET=WANTGET+1;                           //Fibonacci apples

	if ((WANTGET>2*LASTGET) && (!FIRST)) WANTGET=1;
	while ((!FIRST)&&((WANTGET*2)<(SUMAPPLE-WANTGET))&&(WANTGET<=2*LASTGET))
	{
		TRY2=TRY2-GETMINFIB(TRY2);
		WANTGET=WANTGET+GETMINFIB(TRY2);
	}
	if ((FIRST)&&(WANTGET!=0)&&(SUMAPPLE-WANTGET<=WANTGET*2))
		WANTGET=WANTGET-GETMINFIB(TRY1);
	if ((WANTGET!=1)&&(!FIRST)) WANTGET=WANTGET-GETMINFIB(TRY2);
	if (WANTGET==0) WANTGET=WANTGET+GETMINFIB(TRY2);
	if ((SUMAPPLE<=LASTGET*2) && (!FIRST))
	{
		WANTGET=SUMAPPLE;
		SUMAPPLE=0;
	}
	LASTGET=WANTGET;
	if (SUMAPPLE!=0) SUMAPPLE=SUMAPPLE-WANTGET;
	if ((SUMAPPLE+WANTGET)==GETMINFIB(SUMAPPLE+WANTGET)
		&&(SUMAPPLE>LASTGET*2))
	{
		SUMAPPLE=SUMAPPLE+WANTGET-1;
		LASTGET=1;
	}
	printf("\n***** I get %ld apple(s).\n",LASTGET);
	printf("\n----- The Current Apple(s) are/is : %ld\n",SUMAPPLE);
	CHANCE=0;
	FIRST=0;
	if (SUMAPPLE==0) printf("\n***** I win the GAME !");
	ANSWER=0;
}

void PERSON()
{
	printf("\n***** The apple(s) you want to get : ");
	scanf("%ld",&WANTGET);
	if (FIRST)
	{
		if (SUMAPPLE==1)
		{
			while (WANTGET!=1)
			{
				printf("\n----- You are cheated !\n");
				printf("\nThe apple(s) you want to get : ");
				scanf("%ld",&WANTGET);
			}
		}
		if (SUMAPPLE>1)
		{
			while ((WANTGET>=SUMAPPLE) || (WANTGET<=0))
			{
				printf("\n----- You are cheated !\n");
				printf("\nThe apple(s) you want to get : ");
				scanf("%ld",&WANTGET);
			}
		}
		FIRST=0;
	}
	else
	{
		while ((WANTGET>2*LASTGET) || (WANTGET<=0) || (WANTGET>SUMAPPLE))
		{
			printf("\n----- You are cheated !\n");
			printf("\nThe apple(s) you want to get : ");
			scanf("%ld",&WANTGET);
		}
	}
	SUMAPPLE=SUMAPPLE-WANTGET;
	LASTGET=WANTGET;
	CHANCE=1;
	printf("\n----- You get %ld apple(s).\n",LASTGET);
	if (SUMAPPLE==0) printf("\n***** You win the GAME ! ");
	ANSWER=1;
}

void GAME()
{
	while (SUMAPPLE>0)
	{
		if ((CHANCE==1) && (MODEL==0) && (SUMAPPLE!=0)) MACHINESLOW();
		if ((CHANCE==1) && (MODEL==1) && (SUMAPPLE!=0)) MACHINEQUICK();
		if ((CHANCE==0) && (SUMAPPLE!=0)) PERSON();
	}
}

void RESULT()
{
	if (ANSWER==1) cprintf("\nCongratulations !\n");
	else cprintf("\nYou Lose the Game, Try again !\n");
	cprintf("DESIGNED BY NEWTRUMP\n");
}