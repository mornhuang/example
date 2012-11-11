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

GLfloat ControlPnts[4][4][3]={
	{{-1.5,-1.5,4.0},{-0.5,-1.5,2.0},
	{0.5,-1.5,-1.0},{1.5,-1.5,2.0}},
	{{-1.5,-0.5,1.0},{-0.5,-0.5,3.0},
	{0.5,-0.5,0.0},{1.5,-0.5,-1.0}},
	{{-1.5,0.5,4.0},{-0.5,0.5,0.0},
	{0.5,0.5,3.0},{1.5,0.5,4.0}},
	{{-1.5,1.5,-2.0},{-0.5,1.5,-2.0},
	{0.5,1.5,0.0},{1.5,1.5,-1.0}}
};

void Init(void)
{
	glClearColor(0.0,0.0,0.0,1.0);
	glMap2f(GL_MAP2_VERTEX_3,0,1,3,4,0,1,12,4,&ControlPnts[0][0][0]);
	glEnable(GL_MAP2_VERTEX_3);
	glMapGrid2f(20,0.0,1.0,20,0.0,1.0);
	glEnable(GL_DEPTH_TEST);
	glShadeModel(GL_FLAT);
}

void CALLBACK Paint(void)
{
	int i,j;

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	glColor3f(1.0,1.0,1.0);
	glPushMatrix();
	glRotatef(85.0,1.0,1.0,1.0);
	for(j=0;j<=8;j++){
		glBegin(GL_LINE_STRIP);
		   for(i=0;i<=30;i++)
			   glEvalCoord2f((GLfloat)i/30.0,(GLfloat)j/8.0);
		glEnd();
		glBegin(GL_LINE_STRIP);
		   for(i=0;i<=30;i++)
			   glEvalCoord2f((GLfloat)j/8.0,(GLfloat)i/30.0);
		glEnd();
	}
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
		glOrtho(-4.0,4.0,-4.0*(GLfloat)h/(GLfloat)w,
		4.0*(GLfloat)h/(GLfloat)w,-4.0,4.0);
	else
		glOrtho(-4.0*(GLfloat)w/(GLfloat)h,
		4.0*(GLfloat)w/(GLfloat)h,-4.0,4.0,-4.0,4.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,500,500);
	auxInitWindow("Wireframe Bezier Surface");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
	return(0);
}