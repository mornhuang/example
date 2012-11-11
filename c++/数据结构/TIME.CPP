//THE PROGRAM WILL SET A TIME COUNTER SYSTEM TO DISPLAY THE CURRENT(FROM 0 0 0) TIME CONTINOUSLY , AND WILL FINISH IT'S WORK AT A SPECIAL TIME AS YOU LIKE .

//FILE CLOCK.CPP

#include   <dos.h>
#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

int main(void)
{
	int HOUR,MINUTE,SECOND,HUND;
	ldiv_t H,M,S;
	struct time ST;
	struct time NT;
	int TIME;
	clrscr();
	printf("\n*************** THIS IS A CLOCK SYSTEM ***************\n");
	printf("\nPress any key to start... ... ");
	getch();
	gettime(&ST);
	printf("\n\n");
	while (1)
	{
		gettime(&NT);
		TIME=(NT.ti_hour-ST.ti_hour)*60*60
			+(NT.ti_min-ST.ti_min)*60
			+(NT.ti_sec-ST.ti_sec);
		gotoxy(20,10);
		H=ldiv(TIME,60*60);
		HOUR=H.quot;
		M=ldiv(TIME-HOUR*60,60);
		MINUTE=M.quot;
		SECOND=TIME-HOUR*3600-MINUTE*60;
		printf("%d : %d : %d ",HOUR,MINUTE,SECOND);
		if (kbhit())
		{
				break;
		}
	}
	getch();
	printf("\n\n\n 		Press any key to EXIT... ...");
	getch();
	return 0;
}
