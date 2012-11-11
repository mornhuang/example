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
	glColor3f(1.,1.,0.);
	DrawObject();
	glFlush();
}

void DrawObject(void)
{
	glBegin(GL_POINTS);     //单个顶点集
	   glColor3f(1.,0.,0.);
	   glVertex2f(-10.,11.);
	   glColor3f(1.,1.,0.);
	   glVertex2f(-9.,10.);
	   glColor3f(0.,1.,1.);
	   glVertex2f(-8.,12.);
	glEnd();

	glBegin(GL_LINES);   //多组独立双顶点线段
	   glColor3f(1.,1.,0.);
	   glVertex2f(-11.,9.);
	   glVertex2f(-5.,3.);
	   glColor3f(1.,0.,1.);
       glVertex2f(-11.,3.);
	   glVertex2f(-5.,9.);
	glEnd();

	glBegin(GL_LINE_STRIP);     //不闭合折线
	   glColor3f(0.,1.,0.);
	   glVertex2f(-4.,0.);
	   glVertex2f(-11.,-6.);
	   glVertex2f(-11.,0.);
	   glVertex2f(-4.,-6);
	glEnd();

	glBegin(GL_LINE_LOOP);   //闭合折线
	   glColor3f(0.,1.,1.);
	   glVertex2f(-3.,-10.);
	   glVertex2f(-6.,-8.);
       glVertex2f(-9.,-10.);
	   glVertex2f(-10.,-14.);
	   glVertex2f(-7.,-15.);
	   glVertex2f(-5.,-13.);
	glEnd();

	glBegin(GL_POLYGON);     //单个连线多边形
	   glColor3f(3.,5.,7.);
	   glVertex2f(-2.,2.);
	   glVertex2f(-2.,8.);
	   glVertex2f(1.,10.);
	   glVertex2f(3.,5.);
	   glVertex2f(1.,2.);
	glEnd();

	glBegin(GL_QUADS);   //多个独立连线四边形
	   glColor3f(7.,5.,2.);
	   glVertex2f(4.,2.);
	   glVertex2f(5.,4.);
       glVertex2f(7.,3.);
	   glVertex2f(5.,1.);
	   glColor3f(5.,7.,2.);
	   glVertex2f(4.,5.);
	   glVertex2f(4.,7.);
	   glVertex2f(6.,9.);
	   glVertex2f(7.,5.);
	glEnd();

	glBegin(GL_QUAD_STRIP);//多个独立连线四边形
	   glVertex2f(12.,10.);
	   glColor3f(7.,5.,2.);
	   glVertex2f(10.,8.);
       glVertex2f(13.,8.);
	   glVertex2f(9.,6.);
       glVertex2f(12.,7.);
	   glColor3f(5.,7.,2.);
	   glVertex2f(8.,5.);
	   glVertex2f(13.,2.);
	   glVertex2f(9.,0.);
	glEnd();


	glBegin(GL_TRIANGLES);//多个独立连线三角形
	   glVertex2f(0.,-1.);
	   glColor3f(3.,4.,5.);
	   glVertex2f(3.,-4.);
       glVertex2f(0.,-7.);
	   glVertex2f(3.,-5.);
       glVertex2f(0.,-8.);
	   glColor3f(5.,4.,3.);
	   glVertex2f(3.,-11.);
	glEnd();
	
	glBegin(GL_TRIANGLE_STRIP);//线形的连线三角形串
	   glVertex2f(4.,-1.);
       glVertex2f(7.,-3.);
	   glColor3f(3.,1.,5.);
	   glVertex2f(5.,-5.);
	   glColor3f(7.,5.,3.);
	   glVertex2f(9.,-6.);
       glVertex2f(4.,-8.);
	glEnd();

	glBegin(GL_TRIANGLE_FAN);   //扇形的连线三角形串
	   glColor3f(2.,1.,5.);
	   glVertex2f(10.,-6.);
	   glVertex2f(8.,-1.);
       glColor3f(2.,1.,5.);
	   glVertex2f(10.,-2.);
	   glVertex2f(12.,-4.);
	   glColor3f(7.,5.,3.);
	   glVertex2f(12.,-8.);
	   glVertex2f(8.,-10.);
	glEnd();
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