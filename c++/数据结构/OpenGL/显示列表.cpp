#include<windows.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void drawLine(void);
void CALLBACK Paint(void);
void CALLBACK Resize(GLsizei w,GLsizei h);
GLuint listName;

void Init(void)
{
	GLfloat color_vector[3]={1.0,0.0,0.0};

	listName=glGenLists(1);
	glNewList(listName,GL_COMPILE);
	    glPushAttrib(GL_CURRENT_BIT);

		glColor3fv(color_vector);
		glBegin(GL_TRIANGLES);
		    glVertex2f(0.0,0.0);
			glVertex2f(1.0,0.0);
			glVertex2f(0.0,1.0);
		glEnd();
		glTranslatef(1.5,0.0,0.0);
		glPopAttrib();
	glEndList();
	glShadeModel(GL_FLAT);
}

void drawLine(void)
{
	glBegin(GL_LINES);
	    glVertex2f(0.0,0.5);
		glVertex2f(15.0,0.5);
	glEnd();
}

void CALLBACK Paint(void)
{
	GLuint i;
	GLfloat new_color[3]={0.0,1.0,0.0};

	glClear(GL_COLOR_BUFFER_BIT);
	glColor3fv(new_color);
	glPushMatrix();
	for(i=0;i<10;i++)
		glCallList(listName);
	glPopMatrix();
	drawLine();
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=h)
		gluOrtho2D(0.0,2.0,-0.5*(GLfloat)h/(GLfloat)w,
		1.5*(GLfloat)h/(GLfloat)w);
	else
		gluOrtho2D(0.0,2.0*(GLfloat)w/(GLfloat)h,-0.5,1.5);
	glMatrixMode(GL_MODELVIEW);
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB);
	auxInitPosition(0,0,400,50);
	auxInitWindow("Paint List");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
	return(0);
}