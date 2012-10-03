#include <conio.h>
#include <stdio.h>

class node
{	
	friend class list;
	private :
			node *next;
			char tlt[10];
			char name[20];
			int  age;
			int j;
			int k;
};

class list
{
	private :
			node *head;
	public :
			list()
			{
				head=0;
			}
			node* gethead();
			void list::play(node *q);
			void list::intsert();
			void deletes();
			~list()
			{
				deletes();
			}
};

node* list::gethead()
{
	return (head);
}

void list::play(node *q)
{
	int j;
	if (q==0)
	{
		printf("----Sorry!!!  It's EMPTY !!! ");
	}
	else
	{
		while(q!=0)
		{
			for(j=0;j<q->j;j++)
				printf("%c",q->name[j]);
			printf("  ");
			for(j=0;j<q->k;j++)
				printf("%c",q->tlt[j]);
			printf("  ");
			printf("%d ",q->age);
			q=q->next;
			printf("\n");
		}
	}
}
void list::intsert()
{
	node *p;
	node *s;
	int i=1,j,z,l;
	char a,b[20],c[10];
	while(i>0)
	{
		j=0;z=0;
		while ((a=getchar())!='\n');
		printf("请输入名字:");
		while ((a=getchar())!='\n') 
		{   
			b[ j ]=a; 
			j++; 
		}
		printf("请输入电话号码:");
		while ((a=getchar())!='\n')
		{   
			c[ z ]=a; 
			z++; 
		}
		printf("请输入年龄:");
		scanf("%d",&i);
		s=new node;
		for(l=0;l<j;l++)
		{
			s->name[l]=b[l];
		}
		for(l=0;l<z;l++)
		{
			s->tlt[l]=c[l];
		}
		s->age=i;
		s->j=j;
		s->k=z;
		s->next=0;

		if(head==0)
		{
			head=s;
			p=s;
		}
		else
		{
			p->next=s;
			p=s;
		}

	}	
	p->next=NULL;
}
void list::deletes()
{
	node *q_head=head;

	if (q_head==0) return;
	do
	{
		node *q_node=q_head;
		q_head=q_head->next;
		delete q_node;
	}
	while (q_head!=0);
}

int main(void)
{
	list MYlist;
	node *head;
	int cord;
	do
	{
		printf("\n        主菜单        \n");
		printf("     1   建立记录	    \n");
		printf("     2   显示记录	    \n");
		printf("     3   查找记录       \n");
		printf("--------------------------\n");
		printf(" 请输入您的选择(1, 2, 3) ");
		scanf("%d",&cord);
		switch(cord)
		{
		case 1:
			{
				MYlist.intsert();
			}break;
		case 2:
			{	
				head=MYlist.gethead();
				MYlist.play(head);
			}break;
		case 3:
			{

			}break;
		}
	}while(cord>=1&&cord<=3);
	getch();
	return 0;
}