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

void Init(void)
{
	GLfloat light_position[]={1.0,1.0,1.0,0.0};

	glLightfv(GL_LIGHT0,GL_POSITION,light_position);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glDepthFunc(GL_LESS);
	glEnable(GL_DEPTH_TEST);

	glColorMaterial(GL_FRONT,GL_DIFFUSE);
	glEnable(GL_COLOR_MATERIAL);
}

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

	glLoadIdentity();
	glTranslatef(-0.7,0.0,0.0);
	glColor3f(1.0,1.0,0.0);
	auxSolidSphere(0.5);

	glLoadIdentity();
	glRotatef(-65.0,1.0,0.0,0.0);
	glTranslatef(0.7,0.0,0.0);
	glColor3f(1.0,0.0,0.0);
	auxSolidCone(0.4,0.6);

	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=h)
		glOrtho(-1.5,1.5,-1.5*(GLfloat)h/(GLfloat)w,
		1.5*(GLfloat)h/(GLfloat)w,-10.0,10.0);
	else
		glOrtho(-1.5*(GLfloat)w/(GLfloat)h,
		1.5*(GLfloat)w/(GLfloat)h,-1.5,1.5,-10.0,10.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

void main(void)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,500,500);
	auxInitWindow("ColorMaterial µ÷ÓÃ");

	Init();

	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
}