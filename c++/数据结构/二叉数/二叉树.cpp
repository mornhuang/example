//THE PROGRAM IS TO TEST THE CLASS "TREE" & CLASS "NODE"
//FILE TREETEST.CPP

#include <string.h>
#include  <stdio.h>
#include  <conio.h>
class NODE
{
	friend class TREE;
	private:
			int DATA;
			NODE *LEFT;
			NODE *RIGHT;
};
class TREE
{
	private:
			NODE *ROOT;
	public:
			TREE()
			{
				ROOT=0;
			}
			void BUILD_TREE(NODE *&Root,int Data);
			int SEARCH_TREE(NODE *Root,int Data);
			void TREE::DISPLAY_TREE(NODE *Root);
			~TREE(){}
};
void TREE::BUILD_TREE(NODE *&Root,int Data)
{
	NODE *TEMP;
	NODE *BACKTEMP;
	if(Root == 0)
	{
		Root=new NODE;
		Root->LEFT=Root->RIGHT=0;
		Root->DATA=Data;
	}
	else
	{
			TEMP=Root;
			while(TEMP!=0)
			{
					BACKTEMP=TEMP;
					if(Data<(TEMP->DATA)) TEMP=TEMP->LEFT;
					else TEMP=TEMP->RIGHT;
			}
			if(Data<(BACKTEMP->DATA))
			{
				NODE *NEWNODE=new NODE;
				NEWNODE->LEFT=NEWNODE->RIGHT=0;
				NEWNODE->DATA=Data;
				BACKTEMP->LEFT=NEWNODE;

			}
			else
			{
				NODE *NEWNODE=new NODE;
				NEWNODE->LEFT=NEWNODE->RIGHT=0;
				NEWNODE->DATA=Data;
				BACKTEMP->RIGHT=NEWNODE;
			}
	}
}

int TREE::SEARCH_TREE(NODE *Root,int Data)
{
	NODE *TEMP;
	TEMP=Root;
	while((TEMP!=0)&&(TEMP->DATA!=Data))
	{
		if(Data<TEMP->DATA)
		{
			TEMP=TEMP->LEFT;
		}
		else
		{
			TEMP=TEMP->RIGHT;
		}
	}
	if(TEMP->DATA!=0)
	{
		printf("Can not find it!!!.It isn't in this TREE!!!");
		return(1);
	}
	else
	{
		printf("Find it!!!");
		return(0);
	}
}

void TREE::DISPLAY_TREE(NODE *Root)
{
	if(Root!=NULL)
	{
		DISPLAY_TREE(Root->LEFT);
		printf("%d  ",Root->DATA);
		DISPLAY_TREE(Root->RIGHT);
	}
}

int main(void)
{
	TREE BTREE;
	NODE *TREE_ROOT=0;
	int NUM;

	BTREE.BUILD_TREE(TREE_ROOT,10);
	BTREE.BUILD_TREE(TREE_ROOT,18);
	BTREE.BUILD_TREE(TREE_ROOT,32);
	BTREE.BUILD_TREE(TREE_ROOT,16);
	BTREE.BUILD_TREE(TREE_ROOT,3);
	BTREE.BUILD_TREE(TREE_ROOT,77);
	BTREE.BUILD_TREE(TREE_ROOT,200);
	printf("\nTO DISPLAY THE TREE (INORDER):");
	BTREE.DISPLAY_TREE(TREE_ROOT);
	printf("\n\nNow,you can search a number in the tree!");
	printf("\n\nWhat number do you want to search? Input it:");
	scanf("%d",&NUM);
	printf("\n");
	BTREE.SEARCH_TREE(TREE_ROOT,NUM);
	getch();
	return 0;
}