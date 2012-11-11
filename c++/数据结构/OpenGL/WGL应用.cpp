// ewew.cpp : Defines the entry point for the application.
//


// WGL应用.cpp : Defines the entry point for the application.
//
#include"如何设置断言.h"
#include<windows.h>
#include<malloc.h>
#include<math.h>
#include<stdio.h>
#include<GL/gl.h>

#pragma comment(lib,"opengl32")
#pragma comment(lib,"glu32")
#pragma comment(lib,"glaux")

#if defined(GL_SGI_cull_vertex)
      PFNGLCULLPARAMETERFVSGIPROC CullParameterfv;
#endif

#if defined(GL_SGI_compiled_vertex_array)
	  PFNGLLOCKARRAYSSGIPROC LockArrays;
	  PFNGLUNLOCKARRAYSSGIPROC UnlockArrays;
#endif

#if !defined(M_PI)
      #define M_PI 3.14159265F
#endif

#define X_OFFSET_STEP 0.025F;
#define Y_OFFSET_STEP 0.025F;

#define NUM_OBJECTS (sizeof(drawObject)/sizeof(drawObject[0]))

//
//char *windowName="WGL 应用";
int winX,winY;
int winWidth,winHeight;
HDC hDC;
HGLRC hGLRC;
HPALETTE hPalette;

void (*idleFunc)(void);

int objectIndex;
int objectNumMajor=24,objectNumMinor=23;
BOOL halfObject=FALSE;
BOOL redrawContinue=TRUE;
BOOL doubleBuffered=TRUE;
BOOL depthBuffered=TRUE;
BOOL drawOutlines=FALSE;
BOOL textureReplace=FALSE;
BOOL textureEnabled=FALSE;
BOOL useLighting=TRUE;
BOOL useVertexCull=TRUE;
BOOL useFaceCull=TRUE;
BOOL useVertexArray=TRUE;
BOOL useVertexLocking=TRUE;
BOOL perspectiveProj=TRUE;
BOOL useFog=FALSE;
enum MoveModes{MoveNone,MoveObject};
enum MoveModes mode=MoveObject;
float angle=10.0F,axis[3]={0.0F,0.0F,1.0F};
GLfloat objectXform[4][4];
GLfloat xOffset,yOffset;

void drawCube(void);
void drawTorus(void);
void drawSphere(void);

void (*drawObject[])(void)=
{
	drawTorus,drawSphere,drawCube,
};

void drawCube(void)
{
	glBegin(GL_QUADS);
	    glNormal3f(-1.0F,0.0F,0.0F);
		glTexCoord2f(0.0F,1.0F);glVertex3f(-0.5F,-0.5F,-0.5F);
		glTexCoord2f(0.0F,0.0F);glVertex3f(-0.5F,-0.5F,0.5F);
		glTexCoord2f(1.0F,0.0F);glVertex3f(-0.5F,0.5F,0.5F);
		glTexCoord2f(1.0F,1.0F);glVertex3f(-0.5F,0.5F,-0.5F);

	    glNormal3f(1.0F,0.0F,0.0F);
		glTexCoord2f(1.0F,1.0F);glVertex3f(0.5F,0.5F,0.5F);
		glTexCoord2f(0.0F,1.0F);glVertex3f(0.5F,-0.5F,0.5F);
		glTexCoord2f(0.0F,0.0F);glVertex3f(0.5F,-0.5F,-0.5F);
		glTexCoord2f(1.0F,0.0F);glVertex3f(0.5F,0.5F,-0.5F);

	    glNormal3f(0.0F,-1.0F,0.0F);
		glTexCoord2f(0.0F,1.0F);glVertex3f(-0.5F,-0.5F,-0.5F);
		glTexCoord2f(0.0F,0.0F);glVertex3f(0.5F,-0.5F,-0.5F);
		glTexCoord2f(1.0F,0.0F);glVertex3f(0.5F,-0.5F,0.5F);
		glTexCoord2f(1.0F,1.0F);glVertex3f(-0.5F,0.5F,0.5F);

	    glNormal3f(0.0F,0.0F,-1.0F);
		glTexCoord2f(0.0F,1.0F);glVertex3f(-0.5F,-0.5F,-0.5F);
		glTexCoord2f(0.0F,0.0F);glVertex3f(-0.5F,0.5F,-0.5F);
		glTexCoord2f(1.0F,0.0F);glVertex3f(0.5F,0.5F,-0.5F);
		glTexCoord2f(1.0F,1.0F);glVertex3f(0.5F,-0.5F,-0.5F);

	    glNormal3f(0.0F,0.0F,1.0F);
		glTexCoord2f(1.0F,1.0F);glVertex3f(0.5F,0.5F,0.5F);
		glTexCoord2f(0.0F,1.0F);glVertex3f(-0.5F,0.5F,0.5F);
		glTexCoord2f(0.0F,0.0F);glVertex3f(-0.5F,-0.5F,0.5F);
		glTexCoord2f(1.0F,0.0F);glVertex3f(0.5F,-0.5F,0.5F);
	glEnd();
}

void drawSphere(void)
{
	struct vertex
	{
		GLfloat t[2];
		GLfloat n[3];
		GLfloat v[3];
	};

	int numVerts=(objectNumMajor+1)*(objectNumMinor+1);
	int numStrips=halfObject?objectNumMajor/2:objectNumMajor;
	int numPerStrip=2*(objectNumMinor+1);
	int numElements=(objectNumMajor+1)*numPerStrip;

	static struct vertex *vertexArray,*v;
	static GLuint *elementArray,*e;
	static int numMajor;
	static int numMinor;
	int i,j;

	if(!vertexArray||numMajor!=objectNumMajor||
		numMinor!=objectNumMinor)
	{
		float radius=0.6F;
		double majorStep=2.0F*M_PI/objectNumMajor;
		double minorStep=M_PI/objectNumMinor;

		if(vertexArray)free(vertexArray);
		vertexArray=(struct vertex*)calloc(numVerts,sizeof(struct vertex));

		if(elementArray)free(elementArray);
		elementArray=(GLuint*)calloc(numElements,sizeof(GLuint));

		numMajor=objectNumMajor;
		numMinor=objectNumMinor;

		v=vertexArray;
		e=elementArray;
		for(i=0;i<=numMajor;++i)
		{
			double a=i*majorStep;
			GLfloat x=(GLfloat)cos(a);
			GLfloat y=(GLfloat)sin(a);

			for(j=0;j<=numMinor;++j)
			{
				double b=j*minorStep;
				GLfloat c=(GLfloat)sin(b);
				GLfloat r=c*radius;
				GLfloat z=(GLfloat)cos(b);

				v->t[0]=i/(GLfloat)numMajor;
				v->t[1]=j/(GLfloat)numMinor;

				v->n[0]=x*c;
				v->n[1]=y*c;
				v->n[2]=z;

				v->v[0]=x*r;
				v->v[1]=y*r;
				v->v[2]=z*radius;

				v++;

				*e++=i*(numMinor+1)+j;
				*e++=(i+1)*(numMinor+1)+j;
			}
		}
	}

	if(useVertexArray)
	{
		glInterleavedArrays(GL_T2F_N3F_V3F,0,vertexArray);

        #if defined(GL_SGI_compiled_vertex_array)
		if(useVertexLocking && LockArray)
			LockArray(0,numVerts);
		#endif

		for(i=0,e=elementArray;i<numStrips;++i,e+=numPerStrip)
			glDrawElements(GL_TRIANGLE_STRIP,numPerStrip,
			GL_UNSIGNED_INT,e);

		#if defined(GL_SGI_compiled_vertex_array)
		if(useVertexLocking&& UnlockArray)UnlockArrays();
		#endif
	}
	else
	{
		for(i=0,e=elementArray;i<numStrips;++i,e+=numPerStrip)
		{
			glBegin(GL_TRIANGLE_STRIP);
			    for(j=0;j<numPerStrip;++j)
				{
					v=&vertexArray[e[j]];

					glTexCoord2fv(v->t);
					glNormal3fv(v->n);
					glVertex3fv(v->v);
				}
			glEnd();
		}
	}
}

void drawTorus(void)
{
	struct vertex
	{
		GLfloat t[2];
		GLfloat n[3];
		GLfloat v[3];
	};

	int numVerts=(objectNumMajor+1)*(objectNumMinor+1);
	int numStrips=halfObject?objectNumMajor/2:objectNumMajor;
	int numPerStrip=2*(objectNumMinor+1);
	int numElements=(objectNumMajor+1)*numPerStrip;

	static struct vertex *vertexArray,*v;
	static GLuint *elementArray,*e;
	static int numMajor;
	static int numMinor;
	int i,j;

	if(!vertexArray||numMajor!=objectNumMajor||
		numMinor!=objectNumMinor)
	{
		float majorRadius=0.6F;
		float minorRadius=0.2F;
		double majorStep=2.0F*M_PI/objectNumMajor;
		double minorStep=2.0F*M_PI/objectNumMinor;

		if(vertexArray)free(vertexArray);
		vertexArray=(struct vertex*)calloc(numVerts,sizeof(struct vertex));

		if(elementArray)free(elementArray);
		elementArray=(GLuint*)calloc(numElements,sizeof(GLuint));

		numMajor=objectNumMajor;
		numMinor=objectNumMinor;

		v=vertexArray;
		e=elementArray;
		for(i=0;i<=numMajor;++i)
		{
			double a=i*majorStep;
			GLfloat x=(GLfloat)cos(a);
			GLfloat y=(GLfloat)sin(a);

			for(j=0;j<=numMinor;++j)
			{
				double b=j*minorStep;
				GLfloat c=(GLfloat)cos(b);
				GLfloat r=minorRadius*c+majorRadius;
				GLfloat z=minorRadius*(GLfloat)sin(b);

				v->t[0]=i/(GLfloat)numMajor;
				v->t[1]=j/(GLfloat)numMinor;

				v->n[0]=x*c;
				v->n[1]=y*c;
				v->n[2]=z/minorRadius;

				v->v[0]=x*r;
				v->v[1]=y*r;
				v->v[2]=z;

				v++;

				*e++=i*(numMinor+1)+j;
				*e++=(i+1)*(numMinor+1)+j;
			}
		}
	}

	if(useVertexArray)
	{
		glInterleavedArrays(GL_T2F_N3F_V3F,0,vertexArray);

        #if defined(GL_SGI_compiled_vertex_array)
		if(useVertexLocking && LockArray)
			LockArray(0,numVerts);
		#endif

		for(i=0,e=elementArray;i<numStrips;++i,e+=numPerStrip)
			glDrawElements(GL_TRIANGLE_STRIP,numPerStrip,
			GL_UNSIGNED_INT,e);

		#if defined(GL_SGI_compiled_vertex_array)
		if(useVertexLocking&& UnlockArray)UnlockArrays();
		#endif
	}
	else
	{
		for(i=0,e=elementArray;i<numStrips;++i,e+=numPerStrip)
		{
			glBegin(GL_TRIANGLE_STRIP);
			    for(j=0;j<numPerStrip;++j)
				{
					v=&vertexArray[e[j]];

					glTexCoord2fv(v->t);
					glNormal3fv(v->n);
					glVertex3fv(v->v);
				}
			glEnd();
		}
	}
}

void setCheckTexture(void)
{
	int texWidth=256;
	int texHeight=256;
	GLubyte* texPixels,*p;
	int texSize;
	int i,j;

	texSize=texWidth*texHeight*4*sizeof(GLubyte);
	if(textureReplace)
	{
		glTexEnvi(GL_TEXTURE_ENV,GL_TEXTURE_ENV_MODE,GL_REPLACE);
	}
	else
	{
		glTexEnvi(GL_TEXTURE_ENV,GL_TEXTURE_ENV_MODE,GL_MODULATE);
	}

	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_REPEAT);

	texPixels=(GLubyte *)malloc(texSize);
	if(texPixels==NULL)return;

	p=texPixels;
	for(i=0;i<texHeight;++i)
	{
		for(j=0;j<texWidth;++j)
		{
			if((i^j)&32)
			{
				p[0]=0xff;p[1]=0xff;p[2]=0xff;p[3]=0xff;
			}
			else
			{
				p[0]=0x10;p[1]=0x10;p[2]=0x10;p[3]=0xff;
			}
			p+=4;
		}
	}
	glTexImage2D(GL_TEXTURE_2D,0,GL_RGB,texWidth,texHeight,0
		,GL_RGBA,GL_UNSIGNED_BYTE,texPixels);
	free(texPixels);
}

void matrixIdentity(GLfloat m[4][4])
{
	m[0][0]=1.0f;m[0][1]=0.0f;m[0][2]=0.0f;m[0][3]=0.0f;
	m[1][0]=0.0f;m[1][1]=1.0f;m[1][2]=0.0f;m[1][3]=0.0f;
	m[2][0]=0.0f;m[2][1]=0.0f;m[2][2]=1.0f;m[2][3]=0.0f;
	m[3][0]=0.0f;m[3][1]=0.0f;m[3][2]=0.0f;m[3][3]=1.0f;
}

void setProjection(void)
{
	GLfloat aspect=(GLfloat)winWidth/(GLfloat)winHeight;

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if(perspectiveProj)
	{
		glFrustum(-0.5F*aspect,0.5F*aspect,-0.5F,0.5F,1.0F,3.0F);
		#if defined(GL_SGI_cull_vertex)
		if(CullParameterfv)
		{
			GLfloat eye[4]={0.0F,0.0F,0.0F,1.0F};
			CullParameterfv(GL_CULL_VERTEX_EYE_POSITION_SGI,eye);
		}
		#endif
	}
	else
	{
		glOrtho(-1.0F*aspect,1.0F*aspect,-1.0F,1.0F,1.0F,3.0F);
		#if defined(GL_SGI_cull_vertex)
		if(CullParameterfv)
		{
			GLfloat eye[4]={0.0F,0.0F,1.0F,0.0F};
			CullParameterfv(GL_CULL_VERTEX_EYE_POSITION_SGI,eye);
		}
		#endif
	}
	glMatrixMode(GL_MODELVIEW);
}

void setMaterial(void)
{
	GLfloat matAmb[4]={0.01F,0.01F,0.01F,1.00F};
	GLfloat matDiff[4]={0.45F,0.05F,0.65F,0.60F};
	GLfloat matSpec[4]={0.50F,0.50F,0.50F,1.00F};
	GLfloat matShine=20.00F;

	glMaterialfv(GL_FRONT,GL_AMBIENT,matAmb);
	glMaterialfv(GL_FRONT,GL_DIFFUSE,matDiff);
	glMaterialfv(GL_FRONT,GL_SPECULAR,matSpec);
	glMaterialf(GL_FRONT,GL_SHININESS,matShine);
}

void init(void)
{
	GLfloat lightOPos[4]={0.70F,0.70F,1.25F,0.00F};
	GLfloat fogDensity=2.35F*0.180F;
	GLfloat fogColor[4]={0.4F,0.4F,0.5F,0.0F};

	#if defined(GL_SGI_cull_vertex)
	  CullParameterfv=(PFNGLCULLPARAMETERFVSGIPROC)
		 wglGetProcAddress("glCullParameterfvSGI");
	#endif

	#if defined(GL_SGI_compiled_vertex_array)
	  LockArrays=(PFNGLLOCKARRAYSSGIPROC)
		  wglGetProcAddress("glLockArraysSGI");
	  UnlockArrays=(PFNGLUNLOCKARRAYSSGIPROC)
		  wglGetProcAddress("glUnlockArraysSGI");
	#endif

	glFogi(GL_FOG_MODE,GL_EXP2);
	glFogf(GL_FOG_DENSITY,fogDensity);
	glFogfv(GL_FOG_COLOR,fogColor);

	setProjection();
	glTranslatef(0.0F,0.0F,-2.0F);
	setMaterial();
	glLightModeli(GL_LIGHT_MODEL_TWO_SIDE,GL_TRUE);
	glLightfv(GL_LIGHT0,GL_POSITION,lightOPos);
	glEnable(GL_LIGHT0);
	setCheckTexture();
	matrixIdentity(objectXform);
}

void resize(void)
{
	setProjection();
	glViewport(0,0,winWidth,winHeight);
}

void doRedraw(void)
{
	if(useFog)
	{
		glClearColor(0.4F,0.4F,0.5F,1.0F);
		glEnable(GL_FOG);
	}
	else
	{
		glClearColor(0.2F,0.2F,0.1F,1.0F);
		glDisable(GL_FOG);
	}

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

	//update transformations
	if(mode==MoveObject)
	{
		glPushMatrix();
		glLoadIdentity();
		glRotatef(angle,axis[0],axis[1],axis[2]);
		glMultMatrixf((GLfloat*)objectXform);
		glGetFloatv(GL_MODELVIEW_MATRIX,(GLfloat*)objectXform);
		glPopMatrix();
	}

	if(textureEnabled)glEnable(GL_TEXTURE_2D);
	if(drawOutlines)glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
	#if defined(GL_SGI_cull_vertex)
		if(useVertexCull)
			glEnable(GL_CULL_VERTEX_SGI);
	#endif
	if(useFaceCull)
		glEnable(GL_CULL_FACE);
	if(useLighting)
		glEnable(GL_LIGHTING);
	glEnable(GL_DEPTH_TEST);

	glPushMatrix();
	glTranslatef(xOffset,yOffset,0.0F);
	glMultMatrixf((GLfloat*)objectXform);

	(*drawObject[objectIndex])();

	glPopMatrix();

	glDisable(GL_TEXTURE_2D);
	glPolygonMode(GL_FRONT_AND_BACK,GL_FILL);
	#if defined(GL_SGI_cull_vertex)
	glDisable(GL_CULL_VERTEX_SGI);
	#endif
	glDisable(GL_CULL_FACE);
	glDisable(GL_LIGHTING);
	glDisable(GL_DEPTH_TEST);

	glFlush();
	SwapBuffers(hDC);
}

void idleredraw(void)
{
	if(!redrawContinue)idleFunc=NULL;
	doRedraw();
}

void redraw(void)
{
	if(!idleFunc)
		idleFunc=idleredraw;
}
BOOL trackingMotion=FALSE;
float lastPos[3];
DWORD lastTime;
int startX,startY;
void ptov(int x,int y,int width,int height,float v[3])
{
	float d,a;
	//project x,y onto a hemi-sphere centered within width,height
	v[0]=(2.0F*x-width)/width;
	v[1]=(height-2.0F*y)/height;
	d=(float)sqrt(v[0]*v[0]+v[1]*v[1]);
	v[2]=(float)cos((M_PI/2.0F)*((d<1.0F)?d:1.0F));
	a=1.0F/(float)sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]);
	v[0]*=a;
	v[1]*=a;
	v[2]*=a;
}

void startMotion(DWORD time,int button,int x,int y)
{
	if(button==1)mode=MoveObject;
	else return;

	trackingMotion=TRUE;
	redrawContinue=FALSE;
	startX=x;
	startY=y;
	ASSERT(startY);
	ptov(x,y,winWidth,winHeight,lastPos);
}

void stopMotion(DWORD time,int button,int x,int y)
{
	if(button==1&&mode==MoveObject)trackingMotion=FALSE;
	else return;

	if(startX!=x||startY!=y)redrawContinue=TRUE;
	else
	{
		angle=0.0F;
		redrawContinue=FALSE;
	}

	if(!redrawContinue)mode=MoveNone;
	redraw();
}

void trackMotion(DWORD time,int x,int y)
{
	if(trackingMotion)
	{
		float curPos[3],dx,dy,dz;

		ptov(x,y,winWidth,winHeight,curPos);

		dx=curPos[0]-lastPos[0];
		dy=curPos[1]-lastPos[1];
		dz=curPos[2]-lastPos[2];
		angle=90.0F*(float)sqrt(dx*dx+dy*dy+dz*dz);

		axis[0]=lastPos[1]*curPos[2]-lastPos[2]*curPos[1];
		axis[1]=lastPos[2]*curPos[0]-lastPos[0]*curPos[2];
		axis[2]=lastPos[0]*curPos[1]-lastPos[1]*curPos[0];

		lastPos[0]=curPos[0];
		lastPos[1]=curPos[1];
		lastPos[2]=curPos[2];
		redraw();
	}
}

void setupPalette(HDC hDC)
{
	PIXELFORMATDESCRIPTOR pfd;
	LOGPALETTE *pPal;
	int pixelFormat=GetPixelFormat(hDC);
	int paletteSize;

	DescribePixelFormat(hDC,pixelFormat,sizeof(PIXELFORMATDESCRIPTOR),&pfd);
	if(!(pfd.dwFlags &PFD_NEED_PALETTE||pfd.iPixelType==PFD_TYPE_COLORINDEX))
	{
		return;
	}

	paletteSize=1 << pfd.cColorBits;
	pPal=(LOGPALETTE*)
	malloc(sizeof(LOGPALETTE)+paletteSize*sizeof(PALETTEENTRY));
	pPal->palVersion=0x300;
	pPal->palNumEntries=paletteSize;

	//start with a copy of the curent system palette
	(void)GetSystemPaletteEntries(hDC,0,paletteSize,&pPal->palPalEntry[0]);

	{
		//fill in an RGBA color palette
		int redMask=(1 << pfd.cRedBits)-1;
		int greenMask=(1 << pfd.cGreenBits)-1;
		int blueMask=(1 << pfd.cBlueBits)-1;
		int i;
		for(i=0;i<paletteSize;++i)
		{
			pPal->palPalEntry[i].peRed=(((i>>pfd.cRedShift)&redMask)*255)/redMask;
			pPal->palPalEntry[i].peGreen=(((i>>pfd.cGreenShift)&greenMask)*255)/greenMask;
			pPal->palPalEntry[i].peBlue=(((i>>pfd.cBlueShift)&blueMask)*255)/blueMask;
			pPal->palPalEntry[i].peFlags=0;
		}
	}
	hPalette=CreatePalette(pPal);
	free(pPal);
	if(hPalette)
	{
		SelectPalette(hDC,hPalette,FALSE);
		RealizePalette(hDC);
	}
}

void setupPixelformat(HDC hDC)
{
	PIXELFORMATDESCRIPTOR pfd=
	{
		sizeof(PIXELFORMATDESCRIPTOR), //size of this pfd
		1,                             //version num
		PFD_DRAW_TO_WINDOW|            //support window
		PFD_SUPPORT_OPENGL,            //support OpenGL
		PFD_TYPE_RGBA,                 //color type
		8,                             //8-bit color depth
		0,0,0,0,0,0,                   //color bits(ignored)
		0,                             //no alpha buffer
		0,							   //alpha bits(ignored)
		0,							   //no accumulation buffer
		0,0,0,0,					   //accum bits(ignored)
		0,							   //depth buffer(filled below)
		0,							   //no stencil buffer
		0,							   //no auxiliary buffer
		PFD_MAIN_PLANE,				   //main layer
		0,							   //reserved
		0,0,0,						   //no layer,visible,damage masks
	};
	int SelectedPixelFormat;
	BOOL retVal;
	if(doubleBuffered)pfd.dwFlags|=PFD_DOUBLEBUFFER;
	if(depthBuffered)pfd.cDepthBits=16;
	SelectedPixelFormat=ChoosePixelFormat(hDC,&pfd);
	if(SelectedPixelFormat==0)
	{
		MessageBox(WindowFromDC(hDC),"ChoosePixelFormat failed\n","Error",
			MB_ICONERROR|MB_OK);
		exit(1);
	}
	retVal=SetPixelFormat(hDC,SelectedPixelFormat,&pfd);
	if(retVal!=TRUE)
	{
		MessageBox(WindowFromDC(hDC),"SetPixelFormat failed\n","Error",
			MB_ICONERROR|MB_OK);
		exit(1);
	}
}

LRESULT APIENTRY WndProc(HWND hWnd,UINT message,WPARAM wParam,LPARAM lParam)
{
	switch(message)
	{
		case WM_CREATE:
			hDC=GetDC(hWnd);
			setupPixelformat(hDC);
			setupPalette(hDC);
			hGLRC=wglCreateContext(hDC);
			wglMakeCurrent(hDC,hGLRC);
			init();
			return 0;
		case WM_DESTROY:
			if(hGLRC)
			{
				wglMakeCurrent(NULL,NULL);
				wglDeleteContext(hGLRC);
			}
			idleFunc=NULL;
			ReleaseDC(hWnd,hDC);
			PostQuitMessage(0);
			return 0;
		case WM_SIZE:
			if(hGLRC)
			{
				winWidth=(int)LOWORD(lParam);
				winHeight=(int)HIWORD(lParam);
				resize();
				return 0;
			}
		case WM_PALETTECHANGED:
			if(hPalette!=NULL&&(HWND)wParam!=hWnd)
			{
				UnrealizeObject(hPalette);
				SelectPalette(hDC,hPalette,FALSE);
				RealizePalette(hDC);
				redraw();
				return 0;
			}
			break;
		case WM_QUERYNEWPALETTE:
			if(hPalette!=NULL)
			{
				UnrealizeObject(hPalette);
				SelectPalette(hDC,hPalette,FALSE);
				RealizePalette(hDC);
				redraw();
				return TRUE;
			}
		case WM_PAINT:
			if(hGLRC)
			{
				PAINTSTRUCT ps;
				BeginPaint(hWnd,&ps);
				redraw();
				EndPaint(hWnd,&ps);
				return 0;
			}
			break;
		case WM_LBUTTONDOWN:
			if(hGLRC)
			{
				int x=((int)LOWORD(lParam) << 16) >> 16;
				int y=((int)HIWORD(lParam) << 16) >> 16;
				SetCapture(hWnd);
				startMotion(0,1,x,y);
				return 0;
			}
			break;
		case WM_LBUTTONUP:
			if(hGLRC)
			{
				int x=((int)LOWORD(lParam) << 16) >> 16;
				int y=((int)HIWORD(lParam) << 16) >> 16;
				ReleaseCapture();
				stopMotion(0,1,x,y);
				return 0;
			}
			break;
		case WM_MOUSEMOVE:
			if(hGLRC)
			{
				int x=((int)LOWORD(lParam) << 16) >> 16;
				int y=((int)HIWORD(lParam) << 16) >> 16;
				trackMotion(0,x,y);
				break;
			}
		case WM_CHAR:
			switch((int)wParam)
			{
				case VK_ESCAPE:
					DestroyWindow(hWnd);
					return 0;
				case VK_SPACE:
					objectIndex=objectIndex<NUM_OBJECTS-1?++objectIndex:0;
					redraw();
					return 0;
				case 'a':
					useVertexArray=!useVertexArray;
					redraw();
					return 0;
				case 'c':
					useFaceCull=!useFaceCull;
					redraw();
					return 0;
				case 'f':
					useFog=!useFog;
					redraw();
					return 0;
				case 'h':
					halfObject=!halfObject;
					redraw();
					return 0;
				case 'l':
					useLighting=!useLighting;
					redraw();
					return 0;
				case 'o':
					perspectiveProj=!perspectiveProj;
					redraw();
					return 0;
				case 'p':
					drawOutlines=!drawOutlines;
					redraw();
					return 0;
				case 'v':
					useVertexCull=!useVertexCull;
					redraw();
					return 0;
				case 'i':
					yOffset +=Y_OFFSET_STEP;
					redraw();
					return 0;
				case 'j':
					xOffset -=X_OFFSET_STEP;
					redraw();
					return 0;
				case 'k':
					xOffset +=X_OFFSET_STEP;;
					redraw();
					return 0;
				case 'm':
					yOffset -=Y_OFFSET_STEP;;
					redraw();
					return 0;
				case 'r':
					textureReplace=!textureReplace;
					redraw();
					return 0;
				case 't':
					textureEnabled=!textureEnabled;
					redraw();
					return 0;
				case 'x':
					useVertexLocking=!useVertexLocking;
					redraw();
					return 0;
				default:
					break;
			}
			break;
		case WM_KEYDOWN:
			switch((int)wParam)
			{
			    case VK_DOWN:
					--objectNumMajor;
					redraw();
					break;
				case VK_UP:
					++objectNumMajor;
					redraw();
					break;
				case VK_LEFT:
					--objectNumMinor;
					redraw();
					break;
				case VK_RIGHT:
					++objectNumMinor;
					redraw();
					break;
				default:
					break;
			}
			if(hGLRC)
				redraw();
			return 0;
		default:
			break;
	}
	//Deal with any unprocessed messages
	long k=DefWindowProc(hWnd,message,wParam,lParam);
	ASSERT(k);
	return DefWindowProc(hWnd,message,wParam,lParam);
}

int APIENTRY WinMain(HINSTANCE hCurrentInst,HINSTANCE hPreviousInst,
					 PSTR lpszCmdLine, int nCmdShow)
{
	static TCHAR szAppName[] = TEXT ("OpenGL") ;
	WNDCLASS wndClass;
	HWND hWnd;
	MSG msg;
	//Define and register the window class
	wndClass.style=CS_HREDRAW|CS_VREDRAW;
	wndClass.lpfnWndProc=WndProc;
	wndClass.cbClsExtra=0;
	wndClass.cbWndExtra=sizeof(LPVOID);
	//ASSERT(hCurrentInst);
	wndClass.hInstance=hCurrentInst;
	wndClass.hIcon=LoadIcon(NULL,IDI_APPLICATION);
	wndClass.hCursor=LoadCursor(NULL,IDC_ARROW);
	wndClass.hbrBackground=(HBRUSH) GetStockObject(WHITE_BRUSH);
	wndClass.lpszMenuName=NULL;
	wndClass.lpszClassName=szAppName;
    if (!RegisterClass (&wndClass))
    {
          MessageBox (NULL, TEXT ("Program requires Windows NT!"), 
                      szAppName, MB_ICONERROR) ;
          return 0 ;
    }
	winWidth=GetSystemMetrics(SM_CYSCREEN)/3;
	winHeight=GetSystemMetrics(SM_CYSCREEN)/3;
	hWnd=CreateWindow(
		szAppName,TEXT ("WGL 应用"),
		WS_OVERLAPPEDWINDOW,
		winX,winY,winWidth,winHeight,NULL,NULL,hCurrentInst,NULL);
	ShowWindow(hWnd,nCmdShow);
	UpdateWindow(hWnd);

	while(1)
	{
		while(idleFunc&&
			PeekMessage(&msg,NULL,0,0,PM_NOREMOVE)==FALSE)
		{
			(*idleFunc)();
		}
		if(GetMessage(&msg,NULL,0,0)!=TRUE)break;
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
	return msg.wParam;
}







