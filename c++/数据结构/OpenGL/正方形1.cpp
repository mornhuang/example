#include <windows.h>
#include <GL\gl.h>
#include <GL\glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void CALLBACK Resize(GLsizei w,GLsizei h);
void CALLBACK Paint(void);
void DrawObject(void);

void Init(void)
{   //初始化窗口环境
	glClearColor(0.,0.,0.,0.);
	glClear(GL_COLOR_BUFFER_BIT);
	glShadeModel(GL_FLAT);
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	//调整窗口尺寸的事件响应函数
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w>h)
		glOrtho(-1.5*(GLfloat)w/(GLfloat)h,
		1.5*(GLfloat)w/(GLfloat)h,-1.5,1.5,-10.0,10.0);
	else
		glOrtho(-1.5,1.5,-1.5*(GLfloat)h/(GLfloat)w,
		1.5*(GLfloat)h/(GLfloat)w,-10.0,10.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

void CALLBACK Paint(void)
{
	//绘制窗口
	glColor3f(1.,0.,1.);//线条颜色紫色
	DrawObject();
	glFlush();
}

void DrawObject(void)
{
auxWireCube(1.0);//边长为比例为1的绘制正方形
}

void main(void)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGBA);
	auxInitPosition(0,0,500,500);
	auxInitWindow("绘制简单几何图形");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
}