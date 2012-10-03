#include<windows.h>
#include<GL/gl.h>
#include<GL/glu.h>
#include<GL/glaux.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

#define DrawOneLine(x1,y1,x2,y2) glBegin(GL_LINES);\
	                                glVertex2f((x1),(y1));\
									glVertex2f((x2),(y2));\
								 glEnd();

void Init(void)
{
	// background to be cleared to black
	glClearColor(0.0,0.0,0.0,0.0);
	glShadeModel(GL_FLAT);
}

void CALLBACK Display(void)
{
	int i;

	glClear(GL_COLOR_BUFFER_BIT);
	//Draw all lines in white
	glColor3f(1.0,1.0,1.0);

	//in 1st row,3 lines drawn,each with a different stipple
	glEnable(GL_LINE_STIPPLE);
	glLineStipple(1,0x0101);     //dotted
	DrawOneLine(50.0,125.0,150.0,125.0);
	glLineStipple(1,0x0ff);      //dashed
	DrawOneLine(150.0,125.0,250.0,125.0);
	glLineStipple(1,0x1c47);     //dash/dot/dash
	DrawOneLine(250.0,125.0,350.0,125.0);

	// in 2nd row,3 wide lines drawn,each with different stipple
	glLineWidth(5.0);
	glLineStipple(1,0x0101);
	DrawOneLine(50.0,100.0,150.0,100.0);
	glLineStipple(1,0x00ff);
	DrawOneLine(150.0,100.0,250.0,100.0);
	glLineStipple(1,0x1c47);
	DrawOneLine(250.0,100.0,350.0,100.0);
	glLineWidth(1.0);

	// in 3rd row,6 lines drawn,with dash/dot/dash stipple,
	//as part of a single connect line strip
	glLineStipple(1,0x1c47);
	glBegin(GL_LINE_STRIP);
	   for(i=0; i<7;i++)
		   glVertex2f(50.0+((GLfloat)i*50.0),75.0);
	   glEnd();

	//in 4th row,6 independent lines drawn,
	//with dash/dot/dash stipple
	for(i=0;i<6;i++)
	{
		DrawOneLine(50.0+((GLfloat)i*50.0),
		50.0,50.0+((GLfloat)(i+1)*50.0),50.0);
	}

	// in 5th row,1 line drawn,with dash/dot/dash stipple
	// and repeat factor of 5
	glLineStipple(5,0x1c47);
	DrawOneLine(50.0,25.0,350.0,25.0);
	glFlush();
}

int main(int argc,char** argv)
{
	auxInitDisplayMode(AUX_SINGLE|AUX_RGB);
	auxInitPosition(0,0,400,150);
	auxInitWindow("Lines");
	Init();
	auxMainLoop(Display);
	return(0);
}