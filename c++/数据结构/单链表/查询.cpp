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
			//ADD TO THE FRONT OF THE LIST
			void Build_Forward(int Data);
			//ADD TO THE BACK OF THE LIST
			void Build_Backward(int Data);
			//GET THE HEAD OF THE LIST
			NODE* GET_HEAD();
			//GET THE DATA OF THE LIST
			int LIST::GET_NEXT(NODE * &TEMP);
			//GET THE DATA IN THE GIVED POSITION
			int LIST::GET_POSITION(int P);
			//REMOVE ALL THE NODES IN THE LIST
			void CLEAR();
			~LIST()
			{
				CLEAR();
			}
};

void LIST::Build_Forward(int Data)
{
	NODE *BUFFER;

	BUFFER=new NODE;
	BUFFER->DATA=Data;
	if (HEAD)
	{
		BUFFER->NEXT=HEAD;
		HEAD=BUFFER;
	}
	else
	{
		BUFFER->NEXT=0;
		HEAD=BUFFER;
	}
}

void LIST::Build_Backward(int Data)
{
	NODE *PREVIOUS,*CURRENT,*NEWNODE;

	if (HEAD)
	{
		PREVIOUS=HEAD;
		CURRENT=HEAD->NEXT;
		while (CURRENT!=0)
			{
				PREVIOUS=CURRENT;
				CURRENT=CURRENT->NEXT;
			}
		NEWNODE=new NODE;
		NEWNODE->DATA=Data;
		NEWNODE->NEXT=0;
		PREVIOUS->NEXT=NEWNODE;
	}
	else
	{
		HEAD=new NODE;
		HEAD->DATA=Data;
		HEAD->NEXT=0;
	}
}

NODE* LIST::GET_HEAD()
{
	return (HEAD);
}

int LIST::GET_NEXT(NODE * &TEMP)
{
	if (HEAD==0)
	{
		printf("----Sorry!!!  It's EMPTY !!! ");
		return(NULL);
	}
	else
	{
		int BUFFER;
		BUFFER=TEMP->DATA;
		TEMP=TEMP->NEXT;
		return BUFFER;
	}
}

int LIST::GET_POSITION(int P)
{
	NODE *CURRENT;
	int BUFFER=0;
	int COUNTER=1;
	if (HEAD)
	{
		CURRENT=HEAD;
		while ((CURRENT!=0)&&(COUNTER!=P)&&(P>=COUNTER))
		{
			CURRENT=CURRENT->NEXT;
			COUNTER++;
		}
		if(P<COUNTER)
		{
			printf("\nSorry,can not find the position you gived.");
			printf("\nThe position you gived is too small!");
			return(NULL);
		}
		else if(CURRENT==0)
		{
			printf("\nSorry,can not find the position you gived.");
			printf("\nThe position number is too BIG! ");
			return(NULL);
		}
		else //CURRENT!=0
		{
			BUFFER=CURRENT->DATA;
			return(BUFFER);
		}
	}
	else
	{
		printf("\nSorry,can not find the position you gived.");
		printf("\nThe list is EMPTY! ");
		return(NULL);
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
	int CIRCLE=0;
	int PLACE;

	BUFFER=1;
	TEST.Build_Forward(BUFFER);
	BUFFER=2;
	TEST.Build_Forward(BUFFER);
	BUFFER=3;
	TEST.Build_Forward(BUFFER);
	BUFFER=1;
	TEST.Build_Backward(BUFFER);
	BUFFER=2;
	TEST.Build_Backward(BUFFER);
	BUFFER=3;
	TEST.Build_Backward(BUFFER);
	Head=TEST.GET_HEAD();
	printf("\nThe data in the list is(from HEAD to END):");
	for (CIRCLE=0;CIRCLE<6;CIRCLE++)
	{
		BUFFER=TEST.GET_NEXT(Head);
		printf("\n %d",BUFFER);
	}
	printf("\n\nWe can find the data for you,Plese input a position(1--):");
	scanf("%d",&PLACE);
	printf("The data is:%d",TEST.GET_POSITION(PLACE));
	getch();
	return 0;
}