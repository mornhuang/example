#include<windows.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void CALLBACK Paint(void);
void CALLBACK Resize(GLsizei w,GLsizei h);

void Init(void)
{
	GLfloat light_ambient[]={0.6,0.6,0.6,1.0};
	GLfloat light_diffuse[]={1.0,1.0,1.0,1.0};
	GLfloat light_specular[]={1.0,1.0,1.0,1.0};
	GLfloat light_position[]={1.0,1.0,1.0,0.0};

	glLightfv(GL_LIGHT0,GL_AMBIENT,light_ambient);
	glLightfv(GL_LIGHT0,GL_DIFFUSE,light_diffuse);
	glLightfv(GL_LIGHT0,GL_SPECULAR,light_specular);
	glLightfv(GL_LIGHT0,GL_POSITION,light_position);

	glFrontFace(GL_CW);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glEnable(GL_AUTO_NORMAL);
	glEnable(GL_NORMALIZE);
	glDepthFunc(GL_LESS);
	glEnable(GL_DEPTH_TEST);
}

void CALLBACK Paint(void)
{
	GLdouble eqn[4]={1.0,0.0,-1.0,1.0};
	GLfloat two_side_on[]={GL_TRUE};
	GLfloat tow_side_off[]={GL_FALSE};
	GLfloat mat_diffuse[]={0.8,0.8,0.8,1.0};
	GLfloat back_diffuse[]={0.8,0.2,0.8,1.0};

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

	glPushMatrix();
	glClipPlane(GL_CLIP_PLANE0,eqn); //slice objects
	glEnable(GL_CLIP_PLANE0);

	glPushMatrix();
	glTranslatef(0.0,2.0,0.0);
	auxSolidTeapot(1.0);    // one_sided lighting
	glPopMatrix();

	// two-sided lighting,but same material
	glLightModelf(GL_LIGHT_MODEL_TWO_SIDE,GL_TRUE);
	glMaterialfv(GL_FRONT_AND_BACK,GL_DIFFUSE,mat_diffuse);
	glPushMatrix();
	glTranslatef(0.0,0.0,0.0);
	auxSolidTeapot(1.0);
	glPopMatrix();

	// two-sided lighting,two different materials
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,back_diffuse);
	glPushMatrix();
	glTranslatef(0.0,-2.0,0.0);
	auxSolidTeapot(1.0);
	glPopMatrix();

	glLightModelf(GL_LIGHT_MODEL_TWO_SIDE,GL_FALSE);
	glDisable(GL_CLIP_PLANE0);
	glPopMatrix();
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
		4.0*(GLfloat)h/(GLfloat)w,-10.0,10.0);
	else
		glOrtho(-4.0*(GLfloat)w/(GLfloat)h,
		4.0*(GLfloat)w/(GLfloat)h,-4.0,4.0,-10.0,10.0);
	glMatrixMode(GL_MODELVIEW);
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,400,400);
	auxInitWindow("Ë«Ãæ¹âÕÕ");

	Init();

	auxReshapeFunc(Resize);
	auxMainLoop(Paint);

	return(0);
}