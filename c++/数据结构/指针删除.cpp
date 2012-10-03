#include <iostream.h>
#include <string.h>

class Node 
{
private:
char* mesg;
Node* nextNode;
public :
Node(char* msg);
~Node();
void setNode(Node* n);
Node* getNode();
char* getMesg();

};

char* Node::getMesg()
{
return mesg;
}

void Node::setNode(Node* node)
{
nextNode = node;
}

Node* Node::getNode()
{
return nextNode;
}

Node::Node(char* msg)
{
mesg = new char[strlen(msg)+1];
strcpy(mesg, msg);
nextNode=NULL;
}

Node::~Node()
{
if (mesg != NULL)
{
delete mesg;
}
if (nextNode != NULL)
{
delete nextNode;
}
cout<<"\n**node deleted**\n";
}

int main()
{
Node* node=new Node("hello");
cout<<"message:"<<node->getMesg()<<"\n";
delete node;
return 1;
} 