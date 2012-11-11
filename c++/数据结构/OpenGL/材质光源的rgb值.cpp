#include<windows.h>
#include<GL\gl.h>
#include<GL\glu.h>
#include<GL\glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Inid(void);
void CALLBACK Resize(GLsizei w,GLsizei h);
void CALLBACK Paint(void);

void Init(void)
{
	GLfloat ambient[]={0.0,0.0,0.0,1.0};
	GLfloat diffuse[]={1.0,1.0,1.0,1.0};
	GLfloat specular[]={1.0,1.0,1.0,1.0};
	GLfloat position[]={0.0,3.0,2.0,0.0};
	GLfloat lmode1_ambient[]={0.4,0.4,0.4,1.0};
	GLfloat local_view[]={0.0};

	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);

	glLightfv(GL_LIGHT0,GL_AMBIENT,ambient);
	glLightfv(GL_LIGHT0,GL_DIFFUSE,diffuse);
	glLightfv(GL_LIGHT0,GL_POSITION,position);
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT,lmode1_ambient);
	glLightModelfv(GL_LIGHT_MODEL_LOCAL_VIEWER,local_view);

	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);

	glClearColor(0.0,0.1,0.1,0.0);
}

void CALLBACK Paint(void)
{
	GLfloat no_mat[]={0.0,0.0,0.0,1.0};
	GLfloat mat_ambient[]={0.7,0.7,0.7,1.0};
	GLfloat mat_ambient_color[]={0.8,0.8,0.2,1.0};
	GLfloat mat_diffuse[]={0.1,0.5,0.8,1.0};
	GLfloat mat_specular[]={1.0,1.0,1.0,1.0};
	GLfloat no_shininess[]={0.0};
	GLfloat low_shininess[]={5.0};
	GLfloat high_shininess[]={50.0};
	GLfloat mat_emission[]={0.3,0.2,0.2,0.0};

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

	glPushMatrix();
	glTranslatef(-3.75,3.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,no_mat);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,no_mat);
	glMaterialfv(GL_FRONT,GL_SHININESS,no_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(-1.25,3.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,no_mat);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat_specular);
	glMaterialfv(GL_FRONT,GL_SHININESS,low_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(1.25,3.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,no_mat);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat_specular);
	glMaterialfv(GL_FRONT,GL_SHININESS,high_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(3.75,3.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,no_mat);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,no_mat);
	glMaterialfv(GL_FRONT,GL_SHININESS,no_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,mat_emission);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(-3.75,0.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,no_mat);
	glMaterialfv(GL_FRONT,GL_SHININESS,no_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(-1.25,0.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat_specular);
	glMaterialfv(GL_FRONT,GL_SHININESS,low_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();


	glPushMatrix();
	glTranslatef(1.25,0.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat_specular);
	glMaterialfv(GL_FRONT,GL_SHININESS,high_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(3.75,0.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,no_mat);
	glMaterialfv(GL_FRONT,GL_SHININESS,no_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,mat_emission);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(-3.75,-3.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient_color);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,no_mat);
	glMaterialfv(GL_FRONT,GL_SHININESS,no_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(-1.25,-3.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient_color);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat_specular);
	glMaterialfv(GL_FRONT,GL_SHININESS,low_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(1.25,-3.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient_color);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat_specular);
	glMaterialfv(GL_FRONT,GL_SHININESS,high_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
	auxSolidSphere(1.0);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(3.75,-3.0,0.0);
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat_ambient_color);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat_diffuse);
	glMaterialfv(GL_FRONT,GL_SPECULAR,no_mat);
	glMaterialfv(GL_FRONT,GL_SHININESS,no_shininess);
	glMaterialfv(GL_FRONT,GL_EMISSION,mat_emission);
	auxSolidSphere(1.0);
	glPopMatrix();

	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=(h*2))
		glOrtho(-6.0,6.0,-3.0*((GLfloat)h*2)/(GLfloat)w,
		3.0*((GLfloat)h*2)/(GLfloat)w,-10.0,10.0);
	else
		glOrtho(-6.0*(GLfloat)w/((GLfloat)h*2),
		6.0*(GLfloat)w/((GLfloat)h*2),-3.0,3.0,-10.0,10.0);
	glMatrixMode(GL_MODELVIEW);
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,600,450);
	auxInitWindow("¸Ä±ä²ÄÖÊ");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
	return(0);
}