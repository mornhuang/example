#include<windows.h>
#include<GL\gl.h>
#include<GL\glu.h>
#include<GL\glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void CALLBACK Resize(GLsizei w,GLsizei h);
void CALLBACK Paint(void);

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1.0,1.0,1.0);
	glLoadIdentity();
	glTranslatef(0.0,0.0,-5.0);
	glScalef(1.0,2.0,1.0);
	auxWireCube(1.0);
}

void Init(void)
{
	glShadeModel(GL_FLAT);
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	/*if(w>h)
		glOrtho(-20.*(GLfloat)h/(GLfloat)w,20.*(GLfloat)h/(GLfloat)w
		,-20.,20.,-50.,50.);
	else
        glOrtho(-20.,20.,-20.*(GLfloat)h/(GLfloat)w,20.*(GLfloat)h/(GLfloat)w
		,-50.,50.);*/
	glFrustum(-1.0,1.0,-1.0,1.0,1.5,20.0);
	glMatrixMode(GL_MODELVIEW);
}
void main(void) 
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB);
	auxInitPosition(0,0,500,500);
	auxInitWindow("基本变换演示");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);

} 