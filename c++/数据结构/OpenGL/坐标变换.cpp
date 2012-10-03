#ifndef DEFINED
#define DEFINED

#include <iostream>
using namespace std;

#define DEBUG

#ifndef DEBUG
   #define ASSERT(x)
#else
   #define ASSERT(x) \
            if (! (x)) \
            { \
				cout << "ERROR!! Assert " << #x << " failed\n"; \
				cout << " on line " << __LINE__  << "\n"; \
				cout << " in file " << __FILE__ << "\n";  \
            }
#endif

#endif
#include<windows.h>
#include<stdio.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void processHits(GLint hits,GLuint buffer[]);
void CALLBACK pickSquares(AUX_EVENTREC *event);
void CALLBACK Paint(void);
void CALLBACK Resize(GLsizei w,GLsizei h);

int board[3][3]; //amount of color for each square

void Init(void)
{
	int i,j;
	for(i=0;i<3;i++)
		for(j=0;j<3;j++)
			board[i][j]=0;
	glClearColor(0.0,0.0,0.0,0.0);
}

void drawSquares(GLenum mode)
{
	GLuint i,j;
	for(i=0;i<3;i++)
	{
		if(mode==GL_SELECT)
			glLoadName(i);
		for(j=0;j<3;j++)
		{
			if(mode==GL_SELECT)
				glPushName(j);
			glColor3f((GLfloat)i/3.0,(GLfloat)j/3.0,
				(GLfloat)board[i][j]/3.0);
			glRecti(i,j,i+1,j+1);
			if(mode==GL_SELECT)
				glPopName();
		}
	}
}

void processHits(GLint hits,GLuint buffer[])
{
	int i,j;
	GLint ii,jj,names,*ptr;

	//printf("hits=%d\n",hits);
	ptr=(GLint*)buffer;
    ASSERT(ptr);
	for(i=0;i<hits;i++)
	{
		// for each hit
		names=*ptr;
		//printf("number of names for this hit=%d\n",names);
		ptr++;
		//printf("  z1 is %u;",*ptr);
		ptr++;
		//printf(z2 is %u\n",*ptr);
		ptr++;
		//printf("  name are");
		for(j=0;j<names;j++)
		{
			//for each name
			//printf("%d ",*ptr);
			//if(j==0) //for each name
				//printf("%d",*ptr);
				if(j==0)  //set row and colum
					ii=*ptr;
				else if(j==1)
					jj=*ptr;
				ptr++;
		}
			//printf("\n");
			board[ii][jj]=(board[ii][jj]+1)%3;
	}
}

#define  BUFSIZE  512

void CALLBACK pickSquares(AUX_EVENTREC *event)
{
	GLuint selectBuf[BUFSIZE];
	GLint hits;
	GLint viewport[4];

	int x,y;

	x=event->data[AUX_MOUSEX];
	y=event->data[AUX_MOUSEY];
	glGetIntegerv(GL_VIEWPORT,viewport);

	glSelectBuffer(BUFSIZE,selectBuf);
	(void)glRenderMode(GL_SELECT);

	glInitNames();
	glPushName((unsigned)-1);

	glMatrixMode(GL_PROJECTION);
	glPushMatrix();
	glLoadIdentity();
	gluPickMatrix((GLdouble)x,(GLdouble)(viewport[3]-y),5.0,5.0,viewport);
	gluOrtho2D(0.0,3.0,0.0,3.0);
	drawSquares(GL_SELECT);

	glMatrixMode(GL_PROJECTION);
	glPopMatrix();
	glFlush();

	hits=glRenderMode(GL_RENDER);
	processHits(hits,selectBuf);
}

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	drawSquares(GL_RENDER);
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0,3.0,0.0,3.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB);
	auxInitPosition(0,0,100,100);
	auxInitWindow("Picking Squares");
	Init();
	auxMouseFunc(AUX_LEFTBUTTON,AUX_MOUSEDOWN,pickSquares);
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);

	return(0);
}