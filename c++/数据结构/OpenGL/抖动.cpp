#include<windows.h>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<GL\glaux.h>

#pragma comment (lib,"opengl32")
#pragma comment (lib,"glu32")
#pragma comment (lib,"glaux")

GLenum doDither=GL_TRUE;

GLint radius1,radius2;
GLdouble angle1,angle2;
GLint slices,stacks;
GLint height;
GLUquadricObj* quadObj;

static void Init(void)
{
	static float ambient[]={0.1,0.1,0.1,1.0};
	static float diffuse[]={0.5,1.0,1.0,1.0};
	static float position[]={90.0,90.0,150.0,0.0};
	static float front_mat_shininess[]={30.0};
	static float front_mat_specular[]={0.2,0.2,0.2,1.0};
	static float front_mat_diffuse[]={0.5,0.28,0.38,1.0};
	static float back_mat_shininess[]={50.0};
	static float back_mat_specular[]={0.5,0.5,0.2,1.0};
	static float back_mat_diffuse[]={1.0,1.0,0.2,1.0};
	static float lmodel_ambient[]={1.0,1.0,1.0,1.0};
	static float lmodel_twoside[]={GL_TRUE};
	static float decal[]={GL_DECAL};
	static float modulat[]={GL_MODULATE};
	static float repeat[]={GL_REPEAT};
	static float nearest[]={GL_NEAREST};

	glClearColor(1.0,1.0,1.0,0.0);
	glDepthFunc(GL_LEQUAL);
	glEnable(GL_DEPTH_TEST);

	glLightfv(GL_LIGHT0,GL_AMBIENT,ambient);
	glLightfv(GL_LIGHT0,GL_DIFFUSE,diffuse);
	glLightfv(GL_LIGHT0,GL_POSITION,position);
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT,lmodel_ambient);
	glLightModelfv(GL_LIGHT_MODEL_TWO_SIDE,lmodel_twoside);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);

	glMaterialfv(GL_FRONT,GL_SHININESS,front_mat_shininess);
	glMaterialfv(GL_FRONT,GL_SPECULAR,front_mat_specular);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,front_mat_diffuse);
	glMaterialfv(GL_BACK,GL_SHININESS,back_mat_shininess);
	glMaterialfv(GL_BACK,GL_SPECULAR,back_mat_specular);
	glMaterialfv(GL_BACK,GL_DIFFUSE,back_mat_diffuse);

	quadObj=gluNewQuadric();

	radius1=10;
	radius2=5;
	angle1=90;
	angle2=180;
	slices=16;
	stacks=10;
	height=20;
}

static void CALLBACK Resize(int width,int height)
{
	glViewport(0,0,(GLint)width,(GLint)height);

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glFrustum(-1,1,-1,1,1,10);
	gluLookAt(2,2,2,0,0,0,0,0,1);
	glMatrixMode(GL_MODELVIEW);
}

static void CALLBACK Key_y(void)
{
	doDither=!doDither;
	(doDither)?glEnable(GL_DITHER):glDisable(GL_DITHER);
}

static void CALLBACK Paint(void)
{
	glLoadIdentity();
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

	glColor3f(0.0,0.0,0.0);
	glTranslatef(0,0,-height/20.0);
	gluCylinder(quadObj,radius1/10.0,radius2/10.0,height/10.0,
		slices,stacks);

	glFlush();
}

void main(void)
{
	auxInitPosition(0,0,300,300);
	auxInitDisplayMode(AUX_DEPTH16|AUX_SINGLE|AUX_RGB);

	if(auxInitWindow("¶¶¶¯")==GL_FALSE)auxQuit();

	Init();

	auxExposeFunc((AUXEXPOSEPROC)Resize);
	auxReshapeFunc((AUXRESHAPEPROC)Resize);
	auxKeyFunc(AUX_y,Key_y);
	auxMainLoop(Paint);
}