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
	GLfloat mat_ambient[]={1.0,1.0,1.0,1.0};
	GLfloat mat_specular[]={1.0,1.0,1.0,1.0};
	GLfloat light_position[]={0.0,0.0,10.0,1.0};
	GLfloat lm_ambient[]={0.2,0.2,0.2,1.0};

	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient);
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat_specular);
	glMaterialf(GL_FRONT,GL_SHININESS,50.0);
	glLightfv(GL_LIGHT0,GL_POSITION,light_position);
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT,lm_ambient);

	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glHint(GL_POLYGON_SMOOTH_HINT,GL_NICEST);
	glEnable(GL_POLYGON_SMOOTH);
	glDepthFunc(GL_LESS);
	glEnable(GL_DEPTH_TEST);
}

void CALLBACK Paint(void)
{
	GLfloat torus_diffuse[]={0.7,0.7,0.0,1.0};
	GLfloat cube_diffuse[]={0.0,0.7,0.7,1.0};
	GLfloat sphere_diffuse[]={0.7,0.0,0.7,1.0};
	GLfloat octa_diffuse[]={0.7,0.4,0.4,1.0};

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

	glShadeModel(GL_SMOOTH);
	glPushMatrix();
	glRotatef(3.0,1.0,0.0,0.0);
	glPushMatrix();
	glTranslatef(-0.80,0.35,0.0);
	glRotatef(100.0,1.0,0.0,0.0);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,torus_diffuse);
	auxSolidTorus(0.275,0.85);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(-0.75,-0.50,0.0);
	glRotatef(45.0,0.0,0.0,1.0);
	glRotatef(45.0,1.0,0.0,0.0);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,cube_diffuse);
	auxSolidCube(1.5);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(0.75,0.60,0.0);
	glRotatef(30.0,1.0,0.0,0.0);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,sphere_diffuse);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(0.70,-0.90,0.25);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,octa_diffuse);
	auxSolidOctahedron(1.0);
	glPopMatrix();

	auxCreateFont();
	auxDrawStr("OpenGL Auxillary Library");

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
		glOrtho(-2.25,2.25,-2.25*h/w,2.25*h/w,-10.0,10.0);
	else
		glOrtho(-2.25*w/h,2.25*w/h,-2.25,2.25,-10.0,10.0);
	glMatrixMode(GL_MODELVIEW);
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_ACCUM|AUX_DEPTH16);
	auxInitPosition(20,20,320,320);
	auxInitWindow("OpenGL ¸¨ÖúÓ¦ÓÃ¿â");

	Init();

	auxReshapeFunc(Resize);
	auxMainLoop(Paint);

	return(0);
}