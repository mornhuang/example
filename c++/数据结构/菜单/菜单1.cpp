#include <stdio.h>
#include <conio.h>
#include <PROCESS.H>
#include <STDLIB.H>


int main(void)
{
	extern void INIT();
	extern void UNDO();
	extern void CUT();
	extern void COPY();
	extern void PASTE();
	extern void CLEAR();
	extern void EXIT();

	int CHOICE;

	
	INIT();
	do
	{
		printf("\n\n PLEASE ENTER THE CHOICE : ");
		scanf("%d",&CHOICE);
		switch (CHOICE){
			case	1:	UNDO();
					break;
			case 2:	CUT();
					break;
			case 3:	COPY();
					break;
			case 4:	PASTE();
					break;
			case 5:	CLEAR();
					break;
			case 6:	EXIT();
					break;
		}
	}
	while (1);
	return 0;
}

void UNDO()
{
	printf("\n Your Choice is 1 (* * UNDO * *)\n");
	printf("\nSorry,it is just an example!");
}

void CUT()
{
	printf("\n Your Choice is 2 (* * CUT * *)\n");
	printf("\nSorry,it is just an example!");
}

void COPY()
{
	printf("\n Your Choice is 3 (* * COPY * *)\n");
	printf("\nSorry,it is just an example!");
}

void PASTE()
{
	printf("\n Your Choice is 4 (* * PASTE * *)\n");
	printf("\nSorry,it is just an example!");
}

void CLEAR()
{
	printf("\n Your Choice is 5 (* * CLEAR * *)\n");
	printf("\nSorry,it is just an example!");
}

void EXIT()
{
	printf("\n Your Choice is 6 (* * EXIT * *)\n");
	printf("\nThank you for using this example!");
	printf("\nPress any key to EXIT... ...");
	getch();
	exit(0);
}
void INIT()
{
	printf("\n CHOOSE  1 ---------------->UNDO");
	printf("\n CHOOSE  2 ---------------->CUT");
	printf("\n CHOOSE  3 ---------------->COPY");
	printf("\n CHOOSE  4 ---------------->PASTE");
	printf("\n CHOOSE  5 ---------------->CLEAR");
	printf("\n CHOOSE  6 ---------------->EXIT");
}

