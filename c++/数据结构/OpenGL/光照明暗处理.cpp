#include<windows.h>

#include<GL\gl.h>
#include<GL\glu.h>
#include<GL\glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void triangle(void);
void CALLBACK Paint(void);
void CALLBACK Resize(GLsizei w,GLsizei h);

void Init(void)
{
	glShadeModel(GL_SMOOTH); //指定明暗处理方式
}

void triangle(void)
{
	glBegin(GL_TRIANGLES);
	    glColor3f(1.0,0.0,0.0);
		glVertex2f(5.0,5.0);
		glColor3f(0.0,1.0,0.0);
		glVertex2f(25.0,5.0);
		glColor3f(0.0,0.0,1.0);
		glVertex2f(5.0,25.0);
	glEnd();
}

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	triangle();
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=h)
		gluOrtho2D(0.0,30.0,0.0,30.0*(GLfloat)h/(GLfloat)w);
	else
		gluOrtho2D(0.0,30.0*(GLfloat)w/(GLfloat)h,0.0,30.0);
	glMatrixMode(GL_MODELVIEW);
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB);
	auxInitPosition(0,0,500,500);
	auxInitWindow("光滑明暗处理");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
	return(0);
}