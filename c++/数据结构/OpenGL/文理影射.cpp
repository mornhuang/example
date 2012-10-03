#include<windows.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void MakeImage(void);
void CALLBACK Resize(GLsizei w,GLsizei h);
void CALLBACK Paint(void);

#define ImageWidth      64
#define ImageHeight     64

GLubyte Image[ImageWidth][ImageHeight][3];

void MakeImage(void)
{
	int i,j,r,g,b;

	for(i=0;i<ImageWidth;i++)
	{
		for(j=0;j<ImageHeight;j++)
		{
			r=(i*j)%255;
			g=(4*i)%255;
			b=(4*i)%255;
			Image[i][j][0]=(GLubyte)r;
			Image[i][j][1]=(GLubyte)g;
			Image[i][j][2]=(GLubyte)b;
		}
	}
}

void Init(void)
{
	glClearColor(0.0,0.0,0.0,0.0);
	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);

	MakeImage();
	glPixelStorei(GL_UNPACK_ALIGNMENT,1);
	glTexImage2D(GL_TEXTURE_2D,0,3,ImageWidth,      //二维文理函数
		ImageHeight,0,GL_RGB,GL_UNSIGNED_BYTE,&Image[0][0][0]);
	glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_CLAMP); //滤波函数参数GL_TEXTURE_2D一维或二维，
	glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_CLAMP); //GL_TEXTURE_WRAP_S表示重复在s或t方向上重复
	glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);//GL_TEXTURE_MAG_FILTER MAB是放大滤波MIN是缩小滤波
	glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);//GL_NEAREST取像素使图形走样
	glTexEnvf(GL_TEXTURE_2D,GL_TEXTURE_ENV_MODE,GL_DECAL);//文理影射函数
        //如果第二个参数是GL_TEXTURE_ENV_MODE，那么第三个参数可以是GL_DECAL，GL_MODULATE,GL_GL_BLEND设文理值与表面的颜色的处理方式
        //如果第二个参数是GL_TEXTURE_ENV_COLOR,那么第三个参数必须是浮点R,G,B,A的数组，而且在采用GL_BLEND文理函数是才可用
	glEnable(GL_TEXTURE_2D);
	glShadeModel(GL_FLAT);
}

void CALLBACK Paint(void)
{
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	glBegin(GL_QUADS);
	    glTexCoord2f(0.0,0.0);
		glVertex3f(-2.0,-1.0,0.0);
		glTexCoord2f(0.0,1.0);       //文理坐标函数，可以用自动文理坐标函数
                                             //void glTexGen{if}[v](GLenum coord,GLenum pname,TYPE para);参数coord必须
                                             //是GL_S,GL_T,GL_R,GL_Q它指定文理坐标S，T，R，Q中的一个为自动生成的文理坐标
                                             //参数pname可以是GL_TEXTURE_GEN_MODE,GL_OGJECT_PLANE,GL_EYE_PLANE
                                             //参数para根据参数pname来决定，为GL_TEXTURE_GEN_MODE时para是一个常数GL_OBJECT_LINEAR,
                                             //GL_EYE_LINEAR,GL_SPHERE_MAP，他们决定哪个函数产生文理坐标。pname如果是其他的则para是
                                             //指象参数数组的指针
		glVertex3f(-2.0,1.0,0.0);
		glTexCoord2f(1.0,1.0);
		glVertex3f(0.0,1.0,0.0);
		glTexCoord2f(1.0,0.0);
		glVertex3f(0.0,-1.0,0.0);
		glTexCoord2f(0.0,0.0);
		glVertex3f(1.0,-1.0,0.0);
		glTexCoord2f(0.0,1.0);
		glVertex3f(1.0,1.0,0.0);
		glTexCoord2f(1.0,1.0);
		glVertex3f(2.41421,1.0,-1.41421);
		glTexCoord2f(1.0,0.0);
		glVertex3f(2.41421,-1.0,-1.41421);
	glEnd();

	glFlush();
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(60.0,1.0*(GLfloat)w/(GLfloat)h,1.0,30.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0.0,0.0,-3.6);
}

void main(void)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGBA|AUX_DEPTH);
	auxInitPosition(0,0,500,500);
	auxInitWindow("生成简单文理");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
}

/*二维文理定义函数是：
  void glTexImage2D(GLenum target,GLint level,GLint components,
  GLsizei width,GLsizei height,GLint border,GLenum format,GLenum type
  ,const GLvoid *pixels)*/