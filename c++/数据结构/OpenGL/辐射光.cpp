#include<windows.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void RenderTeapot(GLfloat x,GLfloat y,GLfloat ambr,GLfloat ambg,GLfloat ambb,
				  GLfloat difr,GLfloat difg,GLfloat difb,GLfloat specr,
				  GLfloat specg,GLfloat specb, GLfloat emisr,
				  GLfloat emisg,GLfloat emisb,GLfloat shine);
void CALLBACK Paint(void);
void CALLBACK Resize(GLsizei w,GLsizei h);

void Init(void)
{
	GLfloat ambient[]={0.0,0.0,0.0,1.0};
	GLfloat diffuse[]={1.0,1.0,1.0,1.0};
	GLfloat specular[]={1.0,1.0,1.0,1.0};
	GLfloat position[]={0.0,3.0,3.0,0.0};

	GLfloat lmodel_ambient[]={0.2,0.2,0.2,1.0};
	GLfloat local_view[]={0.0};

	glLightfv(GL_LIGHT0,GL_AMBIENT,ambient);
	glLightfv(GL_LIGHT0,GL_DIFFUSE,diffuse);
	glLightfv(GL_LIGHT0,GL_POSITION,position);
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT,lmodel_ambient);
	glLightModelfv(GL_LIGHT_MODEL_LOCAL_VIEWER,local_view);

	glFrontFace(GL_CW);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glEnable(GL_AUTO_NORMAL);
	glEnable(GL_NORMALIZE);
	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);
}

void RenderTeapot(GLfloat x,GLfloat y,GLfloat ambr,GLfloat ambg,GLfloat ambb,
				  GLfloat difr,GLfloat difg,GLfloat difb,GLfloat specr,
				  GLfloat specg,GLfloat specb, GLfloat emisr,
				  GLfloat emisg,GLfloat emisb,GLfloat shine)
{
	float mat[4];

	glPushMatrix();
	glTranslatef(x,y,0.0);

	mat[0]=ambr;
	mat[1]=ambg;
	mat[2]=ambb;
	mat[3]=1.0;
	glMaterialfv(GL_FRONT,GL_AMBIENT,mat);

	mat[0]=difr;
	mat[1]=difg;
	mat[2]=difb;
	glMaterialfv(GL_FRONT,GL_DIFFUSE,mat);

	mat[0]=specr;
	mat[1]=specg;
	mat[2]=specb;
	glMaterialfv(GL_FRONT,GL_SPECULAR,mat);

	mat[0]=emisr;
	mat[1]=emisg;
	mat[2]=emisb;
	glMaterialfv(GL_FRONT,GL_EMISSION,mat);

	glMaterialf(GL_FRONT,GL_SHININESS,shine*128.0);
	auxSolidTeapot(1.0);
	glPopMatrix();
}

void CALLBACK Paint(void)
{
	int x,y;

	GLdouble AmbArr[24][3]={
	{0.0215,0.1745,0.0215},{0.135,0.2225,0.1575},
	{0.05375,0.05,0.06625},{0.25,0.20725,0.20725},
	{0.1745,0.01175,0.01175},{0.1,0.18725,0.1745},
	{0.329412,0.223529,0.027451},{0.2125,0.1275,0.054},
	{0.25,0.25,0.25},{0.19125,0.0735,0.0225},
	{0.24725,0.1995,0.0745},{0.19225,0.19225,0.19225},
	{0.0,0.0,0.0},{0.0,0.1,0.06},
	{0.0,0.0,0.0},{0.0,0.0,0.0},
	{0.0,0.0,0.0},{0.0,0.0,0.0},
	{0.02,0.02,0.02},{0.05,0.0,0.0},
	{0.0,0.05,0.0},{0.05,0.0,0.0},
	{0.05,0.05,0.05},{0.05,0.05,0.0},
	};

	GLdouble DifArr[24][3]={
	{0.07568,0.61424,0.07568},{0.54,0.89,0.63},
	{0.18275,0.17,0.22525},{1,0.829,0.829},
	{0.61424,0.04136,0.04136},{0.396,0.74151,0.69102},
	{0.780392,0.568627,0.113725},{0.714,0.4284,0.18144},
	{0.4,0.4,0.4},{0.7038,0.27048,0.0828},
	{0.75164,0.60648,0.22648},{0.50754,0.50754,0.50754},
	{0.01,0.01,0.01},{0.0,0.50980392,0.50980392},
	{0.1,0.35,0.1},{0.5,0.0,0.0},{0.55,0.55,0.55},{0.5,0.5,0.0},
	{0.01,0.01,0.01},{0.4,0.5,0.5},{0.4,0.5,0.4},{0.5,0.4,0.4},
	{0.5,0.5,0.5},{0.5,0.5,0.4}
	};

	GLdouble SpeArr[24][3]={
	{0.633,0.727811,0.633},{0.316228,0.316228,0.316228},
	{0.332741,0.328634,0.346435},{0.296648,0.296648,0.296648},
	{0.727811,0.626959,0.626959},{0.297254,0.30829,0.306678},
	{0.992157,0.941176,0.807843},{0.393548,0.271906,0.166721},
	{0.774597,0.774597,0.774597},{0.256777,0.137622,0.086014},
	{0.628281,0.555802,0.366065},{0.508273,0.508273,0.508273},
	{0.5,0.5,0.5},{0.50196078,0.50196078,0.50196078},
	{0.45,0.55,0.45},{0.7,0.6,0.6},{0.7,0.7,0.7},{0.6,0.6,0.5},
	{0.4,0.4,0.4},{0.04,0.7,0.7},{0.04,0.7,0.04},{0.7,0.04,0.04},
	{0.7,0.7,0.7},{0.7,0.7,0.04}
	};

	GLdouble shine[24]={
		0.6,0.1,0.3,0.088,0.6,0.1,0.21794872,0.2,0.6,0.1,0.4,0.4,
		0.25,0.25,0.25,0.25,0.25,0.25,0.078125,0.078125,0.078125,
		0.078125,0.078125,0.078125};

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	for(x=0;x<4;x++){
		for(y=0;y<6;y++){
			RenderTeapot(2.0+x*4.0,2.0+y*3.0,
			AmbArr[x+y*4][0],AmbArr[x+y*4][1],AmbArr[x+y*4][2],
			DifArr[x+y*4][0],DifArr[x+y*4][1],DifArr[x+y*4][2],
			SpeArr[x+y*4][0],SpeArr[x+y*4][1],SpeArr[x+y*4][2],
			SpeArr[x+y*4][0],SpeArr[x+y*4][1],SpeArr[x+y*4][2],
			shine[x+y*4]);
		glFlush();
		auxSwapBuffers();
		}
	}
	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	if(!h)return;

	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=h)
		glOrtho(0.0,16.0,0.0,16.0*(GLfloat)h/(GLfloat)w,-10.0,10.0);
	else
		glOrtho(0.0,16.0*(GLfloat)w/(GLfloat)h,0.0,16.0,-10.0,10.0);
	glMatrixMode(GL_MODELVIEW);
}

void main(void)
{
	auxInitDisplayMode(AUX_DOUBLE|AUX_DEPTH16);
	auxInitPosition(0,0,500,600);
	auxInitWindow("²ÄÖÊÓë·øÉä¹â");

	Init();

	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
}