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

GLfloat ControlPnts[4][3]={
	{-4.0,-4.0,0.0},
	{-2.0,4.0,0.0},
	{2.0,-4.0,0.0},
	{4.0,4.0,4.0}
};

void Init(void)
{
	glClearColor(0.0,0.0,0.0,1.0);
	glMap1f(GL_MAP1_VERTEX_3,0.0,1.0,3,4,&ControlPnts[0][0]);
	glEnable(GL_MAP1_VERTEX_3);
	glShadeModel(GL_FLAT);
}

void CALLBACK Paint(void)
{
	int i;

	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1.0,1.0,1.0);
	glBegin(GL_LINE_STRIP);
	    for(i=0;i<=30;i++)
			glEvalCoord1f((GLfloat)i/30.0);
		glEnd();

	//The follwing code Paints the control points as dots.
	glPointSize(5.0);
	glColor3f(1.0,1.0,0.0);
	glBegin(GL_POINTS);
	    for(i=0;i<4;i++)
			glVertex3fv(&ControlPnts[i][0]);
	glEnd();
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=h)
		glOrtho(-5.0,5.0,-5.0*(GLfloat)h/(GLfloat)w,
		5.0*(GLfloat)h/(GLfloat)w,-5.0,5.0);
	else
		glOrtho(-5.0*(GLfloat)w/(GLfloat)h,
		5.0*(GLfloat)w/(GLfloat)h,-5.0,5.0,-5.0,5.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB);
	auxInitPosition(0,0,500,500);
	auxInitWindow("Bezier Corves Using Evaluators");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
	return(0);
}