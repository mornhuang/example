#include<windows.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>
#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void CALLBACK SpinDisplay(void);
void CALLBACK StartIdleFunc(AUX_EVENTREC *event);
void CALLBACK StopIdleFunc(AUX_EVENTREC *event);
void CALLBACK Resize(GLsizei w,GLsizei h);
void CALLBACK Paint(void);

static GLfloat spin=0.0;

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	glPushMatrix();
	glRotatef(spin,0.0,0.0,1.0);
	glRectf(-25.0,-25.0,25.0,25.0);
	glPopMatrix();

	glFlush();
	auxSwapBuffers();
}

void CALLBACK SpinDisplay(void)
{
	spin=spin+2.0;
	if(spin>360.0)
		spin=spin-360;
	Paint();
}

void CALLBACK StartIdleFunc(AUX_EVENTREC* event)
{
	auxIdleFunc(SpinDisplay);
}

void CALLBACK StopIdleFunc(AUX_EVENTREC* event)
{
	auxIdleFunc(0);
}

void Init(void)
{
	glClearColor(1.0,1.0,1.0,1.0);
	glColor3f(0.0,0.0,0.0);
	glShadeModel(GL_FLAT);
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=h)
		glOrtho(-50.0,50.0,-50.0*(GLfloat)h/(GLfloat)w,
		50.0*(GLfloat)w/(GLfloat)h,-1.0,1.0);
	else
		glOrtho(-50.0*(GLfloat)w/(GLfloat)h,
		50.0*(GLfloat)w/(GLfloat)h,-50.0,50.0,-1.0,1.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_DOUBLE|AUX_RGB);
	auxInitPosition(0,0,500,500);
	auxInitWindow("旋转的方形");
	Init();
	auxReshapeFunc(Resize);
	auxIdleFunc(SpinDisplay);
	auxMouseFunc(AUX_LEFTBUTTON,AUX_MOUSEDOWN,StartIdleFunc);
	auxMouseFunc(AUX_RIGHTBUTTON,AUX_MOUSEDOWN,StopIdleFunc);
	auxMainLoop(Paint);
	return(0);
}