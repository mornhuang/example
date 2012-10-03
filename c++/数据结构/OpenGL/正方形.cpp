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
	if(w>h)glOrtho(-20.*(GLfloat)h/(GLfloat)w,20.*(GLfloat)h/(GLfloat)w
		,-20.,20.,-50.,50.);
	else glOrtho(-20.,20.,-20.*(GLfloat)h/(GLfloat)w,20.*(GLfloat)h
		/(GLfloat)w,-50.,50.);
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
auxWireCube(16.0);//边长为16的绘制正方形
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