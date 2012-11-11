#include <windows.h>
#include <GL\gl.h>
#include <GL\glu.h>
#include <GL\glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

void Init(void);
void CALLBACK Resize(GLsizei w,GLsizei h);
void CALLBACK Paint(void);
void draw_triangle(void);

void Init(void)
{   //初始化窗口环境
	glShadeModel(GL_FLAT);
}

void CALLBACK Resize(GLsizei w,GLsizei h)
{
	//调整窗口尺寸的事件响应函数
	if(!h)return;
	glViewport(0,0,w,h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(w<=h)glOrtho(-50.0,50.0,-50.0*(GLfloat)h/(GLfloat)w,
		50.0*(GLfloat)h/(GLfloat)w,-1.0,1.0);
	else glOrtho(-50.0*(GLfloat)w/(GLfloat)h,50.*(GLfloat)w
		/(GLfloat)h,-50.,50.,-1.,1.);
	glMatrixMode(GL_MODELVIEW);
}

void CALLBACK Paint(void)
{
	glClearColor(0.0,0.0,0.0,1.0);
	glClear(GL_COLOR_BUFFER_BIT);

	glLoadIdentity();
	glColor3f(1.0,1.0,1.0);
	draw_triangle();             //载入三角函数

	glEnable(GL_LINE_STIPPLE);
	glLineStipple(1,0xf0f0);     //定义虚线模式
	glLoadIdentity();
	glTranslatef(-20.0,0.0,0.0); //偏移
	draw_triangle();             //载入三角函数

	glLineStipple(1,0xf00f);     //定义虚线模式
	glLoadIdentity();
	glScalef(1.5,0.5,1.0);       //变换尺寸
	draw_triangle();

	glLineStipple(1,0x8888);      //定义虚线模式
	glLoadIdentity();
	glRotatef(90.0,0.0,0.0,1.0);  //翻转
	draw_triangle();              //载入三角函数
	glDisable(GL_LINE_STIPPLE);

	glFlush();
}

void draw_triangle(void)
{
	glBegin(GL_LINE_LOOP);
	    glVertex2f(0.0,25.0);
		glVertex2f(25.0,-25.0);
		glVertex2f(-25.0,-25.0);
	glEnd();
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGBA);
	auxInitPosition(0,0,500,500);
	auxInitWindow("绘制简单几何图形");
	Init();
	auxReshapeFunc(Resize);
	auxMainLoop(Paint);
	return(0);
}