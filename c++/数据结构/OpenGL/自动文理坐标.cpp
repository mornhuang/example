#include<windows.h>
#include<GL\gl.h>
#include<GL\glu.h>
#include<GL\glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

#define  StripeImageWidth      32

void Init(void);
void MakeStripeImage(void);
void CALLBACK Paint(void);
void CALLBACK Resize(GLsizei w,GLsizei h);

GLubyte StripeImage[3*StripeImageWidth];

void MakeStripImage(void)
{
	int j;
	for(j=0;j<StripeImageWidth;j++)
	{
		StripeImage[3*j]=(j<=4)?255:0;
		StripeImage[3*j+1]=(j>4)?255:0;
		StripeImage[3*j+2]=0;
	}
}

GLfloat GenParams[]={1.0,1.0,1.0,0.0};

void Init(void)
{
	glClearColor(0.9,0.9,0.9,0.0);

	MakeStripImage();
	glPixelStorei(GL_UNPACK_ALIGNMENT,1);
	glTexEnvf(GL_TEXTURE_ENV,GL_TEXTURE_ENV_MODE,GL_MODULATE);
	glTexParameterf(GL_TEXTURE_1D,GL_TEXTURE_WRAP_S,GL_REPEAT);
	glTexParameterf(GL_TEXTURE_1D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
	glTexParameterf(GL_TEXTURE_1D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
	glTexImage1D(GL_TEXTURE_1D,0,3,StripeImageWidth,0
		,GL_RGB,GL_UNSIGNED_BYTE,StripeImage);

	glTexGeni(GL_S,GL_TEXTURE_GEN_MODE,GL_OBJECT_LINEAR);
	glTexGenfv(GL_S,GL_OBJECT_PLANE,GenParams);//void glTexGen{if}[v](GLenum coord,GLenum pname,TYPE para);参数coord必须
	                                           //是GL_S,GL_T,GL_R,GL_Q它指定文理坐标S，T，R，Q中的一个为自动生成的文理坐标
                                             //参数pname可以是GL_TEXTURE_GEN_MODE,GL_OGJECT_PLANE,GL_EYE_PLANE
                                             //参数para根据参数pname来决定，为GL_TEXTURE_GEN_MODE时para是一个常数GL_OBJECT_LINEAR,
                                             //GL_EYE_LINEAR,GL_SPHERE_MAP，他们决定哪个函数产生文理坐标。pname如果是其他的则para是
                                             //指象参数数组的指针

	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);
	glEnable(GL_TEXTURE_GEN_S);
	glEnable(GL_TEXTURE_1D);
	glEnable(GL_CULL_FACE);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glEnable(GL_AUTO_NORMAL);
	glEnable(GL_NORMALIZE);
	glFrontFace(GL_CW);
	glCullFace(GL_BACK);
	glMaterialf(GL_FRONT,GL_SHININESS,64.0);
}

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	glPushMatrix();
	glRotatef(45.0,0.0,0.0,1.0);
	auxSolidTeapot(2.0);
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
		glOrtho(-3.5,3.5,-3.5*(GLfloat)h/(GLfloat)w,
		3.5*(GLfloat)h/(GLfloat)w,-3.5,3.5);
	else
		glOrtho(-3.5*(GLfloat)w/(GLfloat)h,
		3.5*(GLfloat)w/(GLfloat)h,-3.5,3.5,-3.5,3.5);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB|AUX_DEPTH16);
	auxInitPosition(0,0,200,200);
	auxInitWindow("自动生成文理坐标");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
	return(0);
}