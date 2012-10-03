#include <conio.h>
#include <stdio.h>

class NODE
{
	//FRIEND CLASS "LIST" CAN HAVE ACCESS TO THE CLASS "NODE" 'S PRIVATE PART
	friend class LIST;
	private :
			//NEXT NODE LINK
			NODE *NEXT;
			//THE CURRENT NODE DATA
			int DATA;
};

class LIST
{
	private :
			//THE HEAD OF THE LIST
			NODE *HEAD;
	public :
			//CONSTRUCTOR FUNCTION
			LIST()
			{
				//INITIAL
				HEAD=0;
			}
			//GET THE HEAD OF THE LIST
			NODE* GET_HEAD();
			//GET THE DATA OF THE LIST
			void LIST::DISPLAY(NODE *TEMP);
			//INSERT A DATA TO THE LIST
			void LIST::INSERT(int Data);
			//REMOVE ALL THE NODES IN THE LIST
			void CLEAR();
			~LIST()
			{
				CLEAR();
			}
};

NODE* LIST::GET_HEAD()
{
	return (HEAD);
}

void LIST::DISPLAY(NODE *TEMP)
{
	if (HEAD==0)
	{
		printf("----Sorry!!!  It's EMPTY !!! ");
	}
	else
	{
		while(TEMP!=0)
		{
			printf("%d ",TEMP->DATA);
			TEMP=TEMP->NEXT;
		}
	}
}
void LIST::INSERT(int Data)
{
	NODE *TEMP=HEAD;
	NODE *PREVIOUS;
	NODE *NEWNODE;

	NEWNODE=new NODE;
	NEWNODE->DATA=Data;
	NEWNODE->NEXT=0;

	if(HEAD==0)
	{
		HEAD=NEWNODE;
	}
	else
	{
		while(((NEWNODE->DATA)<=Data)&&(TEMP!=0))
		{

		   PREVIOUS=TEMP;
		   TEMP=TEMP->NEXT;
		}
		if(TEMP==HEAD)
		{
			NEWNODE->NEXT=HEAD;
			HEAD=NEWNODE;
		}
		else
		{
			NEWNODE->NEXT=TEMP;
			PREVIOUS->NEXT=NEWNODE;
		}
	}
}
void LIST::CLEAR()
{
	NODE *TEMP_HEAD=HEAD;

	if (TEMP_HEAD==0) return;
	do
	{
		NODE *TEMP_NODE=TEMP_HEAD;
		TEMP_HEAD=TEMP_HEAD->NEXT;
		delete TEMP_NODE;
	}
	while (TEMP_HEAD!=0);
}

int main(void)
{
	LIST TEST;
	NODE *Head;
	int BUFFER;

	BUFFER=10;
	TEST.INSERT(BUFFER);
	BUFFER=20;
	TEST.INSERT(BUFFER);
	BUFFER=30;
	TEST.INSERT(BUFFER);
	Head=TEST.GET_HEAD();
	printf("\nThe data in the list is(from HEAD to END):");
	TEST.DISPLAY(Head);
	printf("\nInput a number to insert in the list:");
	scanf("%d",&BUFFER);
	TEST.INSERT(BUFFER);
	Head=TEST.GET_HEAD();/*SOMETIMES HEAD MAY BE CHANGED*/
	TEST.DISPLAY(Head);
	getch();
	return 0;
}