#include<windows.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void CALLBACK Resize(GLsizei w,GLsizei h);
void CALLBACK Paint(void);

GLUquadricObj* quadObj;

void CALLBACK Paint(void)
{
	glClearColor(0.0,0.0,0.0,1.0);
	glClear(GL_COLOR_BUFFER_BIT);

	glPushMatrix();
	gluQuadricDrawStyle(quadObj,GLU_FILL);
	glColor3f(1.0,1.0,1.0);
	glTranslatef(10.0,10.0,0.0);
	gluDisk(quadObj,0.0,5.0,10,2);
	glPopMatrix();

	glPushMatrix();
	glColor3f(1.0,1.0,0.0);
	glTranslatef(20.0,20.0,0.0);
	gluPartialDisk(quadObj,0.0,5.0,10,3,30.0,120.0);
	glPopMatrix();
	glPushMatrix();
	gluQuadricDrawStyle(quadObj,GLU_SILHOUETTE);
	glColor3f(0.0,1.0,1.0);
	glTranslatef(30.0,30.0,0.0);
	gluPartialDisk(quadObj,0.0,5.0,10,3,135.0,270.0);
	glPopMatrix();

	glPushMatrix();
	gluQuadricDrawStyle(quadObj,GLU_LINE);
	glColor3f(1.0,0.0,1.0);
	glTranslatef(40.0,40.0,0.0);
	gluDisk(quadObj,2.0,5.0,10,10);
	glPopMatrix();
	glFlush();
}

void Init(void)
{
	quadObj=gluNewQuadric();
	glShadeModel(GL_FALSE);
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=h)
		glOrtho(0.0,50.0,0.0,50.0*(GLfloat)h/(GLfloat)w,-1.0,1.0);
	else
		glOrtho(0.0,50.0*(GLfloat)w/(GLfloat)h,0.0,50.0,-1.0,1.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

void main(void)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB);
	auxInitPosition(0,0,500,500);
	auxInitWindow("GLU-¼òµ¥±íÃæ");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
}