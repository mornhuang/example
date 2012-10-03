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

void Init(void)
{
	GLfloat mat_ambient[]={0.0,0.0,0.0,1.00};
	GLfloat mat_specular[]={0.9,0.9,0.5,1.00};
	GLfloat mat_shininess[]={15.0};

	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient);
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat_specular);
	glMaterialfv(GL_FRONT,GL_SHININESS,mat_shininess);

	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
	glCullFace(GL_BACK);
	glEnable(GL_CULL_FACE);
	glEnable(GL_POLYGON_SMOOTH);

	glClearColor(0.0,0.0,0.0,1.0);
}

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

	//glTranslatef(0.0,0.0,-8.0);
	glRotatef(-45.0,1.0,0.0,0.0);
	glRotatef(45.0,0.0,1.0,0.0);
	glColor4f(1.0,1.0,1.0,1.0);
	auxSolidIcosahedron(1.0);
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	h=(h==0)?1:h;
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(30.0,(GLfloat)w/(GLfloat)h,1.0,20.0);

	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0.0,0.0,-4.0);
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,200,200);
	auxInitWindow("多边形的反走样");
	Init();

	auxReshapeFunc(Resize);
	auxMainLoop(Paint);

	return(0);
}