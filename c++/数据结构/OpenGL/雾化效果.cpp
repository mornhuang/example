#include<windows.h>
#include<math.h>
#include<stdio.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void RenderRedTeapot(GLfloat x,GLfloat y,GLfloat z);
void CALLBACK FogCycle(AUX_EVENTREC* event);
void CALLBACK Resize(GLsizei w,GLsizei h);
void CALLBACK Paint(void);

GLint fogMode;

void CALLBACK FogCycle(AUX_EVENTREC *event)
{
	if(fogMode==GL_EXP)
	{
		fogMode=GL_EXP2;
		//printf("Fog mode is GL_EXP2\n");
	}
	else if(fogMode==GL_EXP2)
	{
		fogMode=GL_LINEAR;
		//printf("Fog mode is GL_LINEAR\n");
		glFogf(GL_FOG_START,1.0);
		glFogf(GL_FOG_END,5.0);
	}
	else if(fogMode==GL_LINEAR)
	{
		fogMode=GL_EXP;
	}
	glFogi(GL_FOG_MODE,fogMode);
}

void Init(void)
{
	GLfloat position[]={0.0,3.0,3.0,0.0};
	GLfloat local_view[]={0.0};

	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);

	glLightfv(GL_LIGHT0,GL_POSITION,position);
	glLightModelfv(GL_LIGHT_MODEL_LOCAL_VIEWER,local_view);

	glFrontFace(GL_CW);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glEnable(GL_AUTO_NORMAL);
	glEnable(GL_NORMALIZE);
	glEnable(GL_FOG);
	
		GLfloat fogColor[4]={0.5,0.5,0.5,1.0};

		fogMode=GL_EXP;
		glFogi(GL_FOG_MODE,fogMode);
		glFogfv(GL_FOG_COLOR,fogColor);
		glFogf(GL_FOG_DENSITY,0.35);
		glHint(GL_FOG_HINT,GL_DONT_CARE);
		glClearColor(0.5,0.5,0.5,1.0);
	
}

void RenderRedTeapot(GLfloat x,GLfloat y,GLfloat z)
{
	GLfloat mat[3];

	//GLfloat mat_shininess[]={50.0};

	glPushMatrix();
	glTranslatef(x,y,z);
	mat[0]=0.0;mat[1]=0.0;mat[2]=1.0;
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat);
	mat[0]=0.8;mat[1]=0.86;mat[2]=1.0;
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat);
	mat[0]=1.0;mat[1]=1.0;mat[2]=1.0;
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat);
	glMaterialf(GL_FRONT,GL_SHININESS,0.6*128.0);
	auxSolidTeapot(1.0);
	glPopMatrix();
}

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	RenderRedTeapot(-4.0,-0.5,-1.0);
	RenderRedTeapot(-2.0,-0.5,-2.0);
	RenderRedTeapot(0.0,-0.5,-3.0);
	RenderRedTeapot(2.0,-0.5,-4.0);
	RenderRedTeapot(4.0,-0.5,-5.0);
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;

	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=(h*3))
		glOrtho(-6.0,6.0,-2.0*((GLfloat)h*3)/(GLfloat)w,
		2.0*((GLfloat)h*3)/(GLfloat)w,0.0,10.0);
	else
		glOrtho(-6.0*(GLfloat)w/((GLfloat)h*3),
		6.0*(GLfloat)w/((GLfloat)h*3),-2.0,2.0,0.0,10.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,450,150);
	auxInitWindow("Он(RGBA ДЈКЅ)");
	auxMouseFunc(AUX_LEFTBUTTON,AUX_MOUSEDOWN,FogCycle);

	Init();

	auxReshapeFunc(Resize);
	auxMainLoop(Paint);

	return(0);
}