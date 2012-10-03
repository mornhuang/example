/*有一个单向链表，可能是以下两种：
<1>无循环链表
最后一个节点的next指针是NULL值
<2>有循环的链表
最后一个节点的next指针，指向链表中的某一个节点
*/
#include<iostream.h>
typedef struct SomeList
{
//int Some;
SomeList *next;
}SomeList;

bool ListType(SomeList * ListHead)
{
  SomeList *Now;
  SomeList *i;

  Now = ListHead;
  while(Now->next != NULL)
  {
    i = ListHead;
    while(i != Now)
    {
      if(Now->next == i)
        return 1; //&Oacute;&ETH;&Ntilde;&shy;&raquo;·
      i = i->next;
    }
    Now = Now->next;
  }
  return 0; //&Icirc;&THORN;&Ntilde;&shy;&raquo;·
}

void main()
{
SomeList *Head = new SomeList;
Head->next = NULL;
if(ListType(Head))
cout<<"test 1: 有循环！\n";
else
cout<<"test 1: 无循环！\n";

SomeList *Tail = new SomeList;
Head->next = Tail;
Tail->next = Head;
if(ListType(Head))
cout<<"test 2: 有循环！\n";
else
cout<<"test 2: 无循环！\n";

SomeList *Middle = new SomeList;
Head->next = Middle;
Middle->next = Tail;
Tail->next = Middle;
if(ListType(Head))
cout<<"test 3: 有循环！\n";
else
cout<<"test 3: 无循环！\n";

}
