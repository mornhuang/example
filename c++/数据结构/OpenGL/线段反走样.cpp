#include<windows.h>

#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>
#include<stdio.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void)
{
	GLfloat values[2];
	glGetFloatv(GL_LINE_WIDTH_GRANULARITY,values);

	glGetFloatv(GL_LINE_WIDTH_RANGE,values);

	glEnable(GL_LINE_SMOOTH);
	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
	glHint(GL_LINE_SMOOTH_HINT,GL_DONT_CARE);
	glLineWidth(1.5);

	glShadeModel(GL_FLAT);
	glClearColor(0.0,0.0,0.0,0.0);
	glDepthFunc(GL_LESS);
	glEnable(GL_DEPTH_TEST);
}

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	glColor4f(1.0,1.0,1.0,1.0);
	auxWireIcosahedron(1.0);
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;

	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0,(GLfloat) w/(GLfloat)h,3.0,5.0);

	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0.0,0.0,-4.0);
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,400,400);
	auxInitWindow("线段的反走样");

	Init();

	auxReshapeFunc(Resize);
	auxMainLoop(Paint);

	return(0);
}