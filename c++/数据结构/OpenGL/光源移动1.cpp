#include<windows.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void CALLBACK MoveLight(AUX_EVENTREC* event);
void CALLBACK Paint(void);
void CALLBACK Resize(GLsizei w,GLsizei h);

static int spin=0;

void CALLBACK MoveLight(AUX_EVENTREC* event)
{
	spin=(spin+30)%360;
}

void Init(void)
{
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);

	glDepthFunc(GL_LESS);
	glEnable(GL_DEPTH_TEST);
}

void CALLBACK Paint(void)
{
	GLfloat position[]={0.0,0.0,1.5,1.0};

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

	glPushMatrix();
	glTranslatef(0.0,0.0,-5.0);

	glPushMatrix();
	glRotated((GLdouble)spin,1.0,0.0,0.0);
	glRotated(0.0,1.0,0.0,0.0);
	glLightfv(GL_LIGHT0,GL_POSITION,position);

	glTranslated(0.0,0.0,1.5);
	glDisable(GL_LIGHTING);
	glColor3f(0.0,1.0,1.0);
	auxWireCube(0.1);
	glEnable(GL_LIGHTING);
	glPopMatrix();

	auxSolidTorus(0.275,0.85);
	glPopMatrix();
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;

	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(40.0,(GLfloat)w/(GLfloat)h,1.0,20.0);
	glMatrixMode(GL_MODELVIEW);
}

void main(void)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,500,500);
	auxInitWindow("π‚‘¥“∆∂Ø");

	Init();

	auxMouseFunc(AUX_LEFTBUTTON,AUX_MOUSEDOWN,MoveLight);
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
}