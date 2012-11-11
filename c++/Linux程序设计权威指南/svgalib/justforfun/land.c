

   /*--------------------------------------------------*
       Ray-Casting Heightfield Engine       

       Pre-SVGAlib code by Alex Chalfin

       Usage:  Use the mouse to move l, r, u, & d.
               '+' and '-' control your forward speed.
    *--------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <malloc.h>
#include <math.h>
#include <time.h>
#include <unistd.h>
#include <vga.h>
#include <vgagl.h>
#include <vgamouse.h>

/*------------------------------------------------------------------------*
  Floating point related macros
 *------------------------------------------------------------------------*/
#define  FLT2INT(a) ((int)(a))            /* float to integer conversion */
#define  FLT2FXD(a) (FLT2INT((a)*65536))  /* float to 16.16 fixed point  */

/*------------------------------------------------------------------------*
  Map size and indexing related macros
 *------------------------------------------------------------------------*/
#define  MAPSHIFT       8                              /* log2(map_dimention)          */
#define  MAPSIZE        (1 << MAPSHIFT)                /* size of one side of the map  */
#define  BYTE(a)        (a & (MAPSIZE-1))              /* map wrapping macro           */
#define  INDEX(x,y)   ((BYTE(y)<<MAPSHIFT)+BYTE(x))    /* std index calculation        */
#define  fpINDEX(x,y) (INDEX(x>>16, y>>16))            /* fixed point 16.16 index calc */
#define  iINDEX(x,y)  (INDEX(FLT2INT(x), FLT2INT(y)))  /* flt index calc               */

/*------------------------------------------------------------------------*
  A nice random macro (ranged)
 *------------------------------------------------------------------------*/
#define  RANDOM(range)    (rand() % (range))
#define  DEG2RAD(ang)     ((ang)*2*3.141592/360)

/*------------------------------------------------------------------------*
  Rendering related constants
 *------------------------------------------------------------------------*/
#define  FARDEPTH      100    /* how far into horizon to render        */
#define  HEIGHTMUL     48     /* perspective multiplication factor     */
#define  FIELD_OF_VIEW 90     /* 90 degree field of view               */
#define  RENDERWIDTH   320    /* width of the screen to render to      */
#define  RENDERHEIGHT  200    /* height of the screen to render to     */


/*------------------------------------------------------------------------*
  Bi-linear filter blend 
 *------------------------------------------------------------------------*/
#define BLEND(map,u,v,value) {                                \
  c0 = (int)(map)[fpINDEX(u, v)];                             \
  c1 = (int)(map)[fpINDEX((u+65536), v)];                     \
  c2 = (int)(map)[fpINDEX(u, (v+65536))];                     \
  c3 = (int)(map)[fpINDEX((u+65536),(v+65536))];              \
  c11 = (c0 << 8) + (((u & 0xffff) >> 8) * (c1 - c0));        \
  c22 = (c2 << 8) + (((u & 0xffff) >> 8) * (c3 - c2));        \
  (value) = (c11 << 8) + (((v & 0xffff) >> 8) * (c22 - c11)); \
}

/*------------------------------------------------------------------------*
  Types used in rendering process
 *------------------------------------------------------------------------*/
typedef struct tview                                
{                                                            
  float cx, cy;
  float angle;
  int z;
} tview;

/*------------------------------------------------------------------------*
  Global Variables
 *------------------------------------------------------------------------*/
char  heightmap[MAPSIZE * MAPSIZE];         // height field for the voxels
char  colormap [MAPSIZE * MAPSIZE];         // colors for the voxels
char  vpage [RENDERWIDTH * RENDERHEIGHT];   // a virtual screen page

/*------------------------------------------------------------------------*
  Function Headers
 *------------------------------------------------------------------------*/
void  CreateMaps();
void  CreateFractalMap(int x1, int y1, int x2, int y2);
void  NewColor(int xa, int ya, int x, int y, int xb, int yb);
void  SmoothMap();
void  MakeColorMap();
void  DrawScreen(tview *v);
void  MakePalette();
void  SetRGB(char n, char r, char g, char b);
void  SetRenderView(float speed, float moveangle, tview *v);
short int InitMouse();
void  ReadMouseCoords(short int *x, short int *y);
void  CastRay(float x1, float y1, float x2, float y2, int viewer, int scr_x);

/*------------------------------------------------------------------------*
  Da main funk!            
 *------------------------------------------------------------------------*/
int main(void)
{
  tview mainview;       
  int x, y, key, quit = 0, height = -1;
  float angle = 0, speed = 0;

  printf("Use the '+' and '-' keys to change speed\n");
  printf("and the mouse to fly around.\n");
  getchar();

  CreateMaps();

  vga_init();

  vga_setmousesupport(1);
  vga_setmode(G320x200x256);
  gl_setcontextvga(G320x200x256);

  MakePalette();

  while (!quit) {
    mouse_update();
    x = mouse_getx();
    y = mouse_gety();

    angle  += (float)(x - (RENDERWIDTH / 2)) / 1024.0f;
    height += ((RENDERHEIGHT / 2) - (int)(y))*16;

    if (height > 1000*256)
      height = 1000*256;

    if (height < ((int)heightmap[iINDEX(mainview.cx, mainview.cy)] << 8))
      height = ((int)heightmap[iINDEX(mainview.cx, mainview.cy)]) << 8;

    mainview.z = height;

    if ((key = vga_getkey()) > 0)
    {
      switch (key)
      {
        case '+' : speed += 1.0 / 4.0;
                   if (speed > 16) speed = 16;
                   break;
        case '-' : speed -= 1.0 / 4.0;
                   if (speed < 0) speed = 0;
                   break;
        case 27  : if ((key = vga_getkey()) == 0) quit = 1;
                   break;
      }
    }

    SetRenderView(speed, angle, &mainview);

    DrawScreen(&mainview);  

    vga_waitretrace();
    gl_putbox(0, 0, RENDERWIDTH, RENDERHEIGHT, vpage);
    memset(vpage, 0, RENDERWIDTH * RENDERHEIGHT);
  }

  vga_setmode(TEXT);

  return 0;
}

/*------------------------------------------------------------------------*
  SetRenderView - assigns view values based on speed and angle of movement
 *------------------------------------------------------------------------*/
void SetRenderView(float speed, float moveangle, tview *v)
{
  v->cx += speed * cos(moveangle);
  v->cy += speed * sin(moveangle);

  v->angle = moveangle;
}

/*------------------------------------------------------------------------*
  DrawScreen - given a view structure, render the screen to the virtual buf
 *------------------------------------------------------------------------*/
void DrawScreen(tview *v)
{
  int   i_x;
  float angle;
  float target_x, target_y;

  for (i_x = 0; i_x < RENDERWIDTH; i_x++)
    {
      angle = FIELD_OF_VIEW * ((float)i_x / (float)(RENDERWIDTH - 1)) - (FIELD_OF_VIEW / 2);
      target_x = v->cx + FARDEPTH * cos(v->angle + DEG2RAD(angle));
      target_y = v->cy + FARDEPTH * sin(v->angle + DEG2RAD(angle));

      CastRay(v->cx, v->cy, target_x, target_y, (v->z+(20<<8))<<8, i_x);
    }
}

/*------------------------------------------------------------------------*
  CastRay - casts a ray into the landscape and draws the height sliver
 *------------------------------------------------------------------------*/
void CastRay(float x1, float y1, float x2, float y2, int viewer, int scr_x)
{
  int c0,c1,c2,c3,c11,c22;    /* vars for bi-linear interpolation */
  int fxd_x, fxd_dx;
  int fxd_y, fxd_dy;
  int miny;
  int depth;
  int per;

  int start_h, start_c;
  int end_h, end_c;

  int scr_y1, scr_y2;
  int i_c, i_dc;
  int i_y;

  /* store the traversing vars in 16.16 fixed point */
  fxd_x = FLT2FXD(x1);
  fxd_y = FLT2FXD(y1);

  fxd_dx = FLT2FXD((x2 - x1) / FARDEPTH);
  fxd_dy = FLT2FXD((y2 - y1) / FARDEPTH);

  miny = RENDERHEIGHT;

  /* create the starting height and color */
  start_h = 199;
  BLEND(colormap, fxd_x, fxd_y, start_c);

  for (depth = 1; depth < FARDEPTH; depth++)
    {
      per = (HEIGHTMUL << 16) / depth;

      /* calculate the color and height in map space */
      BLEND(heightmap, fxd_x, fxd_y, end_h);
      BLEND(colormap,  fxd_x, fxd_y, end_c);

      /* convert the height to screen space */
      end_h = (RENDERHEIGHT / 2) - (long)(((long long)(end_h - viewer) * per) >> 32);

      /* only draw if the new height is less than the old height */
      if (end_h < start_h)
        {
          /* only draw if the new height is less than the minimum reached height */
          if (end_h < miny)
            {
              scr_y1 = end_h;
              scr_y2 = miny - 1;
              i_c    = end_c;
              i_dc   = (start_c - end_c) / (start_h - end_h);

              miny   = end_h;  /* reset the min y value to the new highest value */

              if (scr_y1 < 0)  /* perform some clipping so we don't go off the top of the screen */
                {
                  i_c    += i_dc * (-scr_y1);
                  scr_y1  = 0;
                }

              scr_y1 = ( (int)(vpage) + scr_y1 * RENDERWIDTH + scr_x);
              scr_y2 = ( (int)(vpage) + scr_y2 * RENDERWIDTH + scr_x);

              for (i_y = scr_y1; i_y <= scr_y2; i_y += RENDERWIDTH, i_c += i_dc)
                *(char *)(i_y) = i_c >> 16;
            }
        }
      fxd_x  += fxd_dx;
      fxd_y  += fxd_dy;
      start_h = end_h;
      start_c = end_c;
    }
}

/*------------------------------------------------------------------------*
  MakePalette - Makes a sandy dark red to red palette           
 *------------------------------------------------------------------------*/
void MakePalette()
{
   int i;

   for (i = 1; i < 64; i++)
   {
     gl_setpalettecolor(i, 16 + (i/4), i/8, 0);
   }

   for (i = 64; i < 128; i++)
   {
     gl_setpalettecolor(i, 32 + ((i-64)/2), i/8, 0);
   }

   /* gl_setpalettecolor(255, 63, 63, 63); */
}

/*------------------------------------------------------------------------*
  CreateMaps - calculates heightmap and color map. Allocates draw buffers
 *------------------------------------------------------------------------*/
void CreateMaps()
{
  int i;

  srand(getpid());

  /* initialize some of the memory */
  memset(heightmap, 0, MAPSIZE*MAPSIZE);
  memset(vpage, 0, RENDERWIDTH * RENDERHEIGHT);

  printf("Creating %dx%d fractal terrain\n", MAPSIZE, MAPSIZE);
  heightmap[0] = (rand() % 128) + 64;   // initialize starting point on map

  CreateFractalMap(0, 0, MAPSIZE, MAPSIZE);

  printf("Smoothing terrain\n");
  for (i = 0; i < 5; i++)
    SmoothMap();

  MakeColorMap();
}

/*------------------------------------------------------------------------*
  CreateFractalMap - builds a fractal landscape                          
 *------------------------------------------------------------------------*/
void CreateFractalMap(int x1, int y1, int x2, int y2)
{
  int x, y, c;

  if (((x2-x1) < 2) && ((y2-y1) < 2))
    return;

  x = (x1 + x2) >> 1;
  y = (y1 + y2) >> 1;

  NewColor(x1, y1, x,  y1, x2, y1);
  NewColor(x2, y1, x2, y,  x2, y2);
  NewColor(x1, y2, x,  y2, x2, y2);
  NewColor(x1, y1, x1, y,  x1, y2);

  c = (heightmap[INDEX(x1,y1)] + heightmap[INDEX(x1,y2)] +
       heightmap[INDEX(x2,y1)] + heightmap[INDEX(x2,y2)]) >> 2;

  if (heightmap[INDEX(x, y)] == 0)
    heightmap[INDEX(x, y)] = c;

  CreateFractalMap(x1, y1, x, y);
  CreateFractalMap(x, y1, x2, y);
  CreateFractalMap(x, y, x2, y2);
  CreateFractalMap(x1, y, x, y2);
}

/*------------------------------------------------------------------------*
  NewColor - new color evaluation (fractal landscape function)         
 *------------------------------------------------------------------------*/
void NewColor(int xa, int ya, int x, int y, int xb, int yb)
{
  int color;

  color = abs(xa - xb) + abs(ya - yb);
  color = RANDOM(color << 1) - color;
  color = color + ((heightmap[INDEX(xa, ya)] + heightmap[INDEX(xb, yb)] + 1) >> 1);

  if (color < 1)   color = 1;
  if (color > 255) color = 255;

  if (heightmap[INDEX(x, y)] == 0)
    heightmap[INDEX(x, y)] = color;
}

/*------------------------------------------------------------------------*
  SmoothMap - smooth the landscape via 3x3 box filter              
 *------------------------------------------------------------------------*/
void SmoothMap()
{
  int x,y;

  for (x = 0; x < MAPSIZE; x++)
    for (y = 0; y < MAPSIZE; y++)
      heightmap[INDEX(x,y)] = (heightmap[INDEX((x-1),(y-1))] +
                               heightmap[INDEX((x-1),(y+1))] +
                               heightmap[INDEX((x+1),(y-1))] +
                               heightmap[INDEX((x+1),(y+1))] +
                               heightmap[INDEX(x,    (y-1))] +
                               heightmap[INDEX(x,    (y+1))] +
                               heightmap[INDEX((x-1), y   )] +
                               heightmap[INDEX( x,    y   )] +
                               heightmap[INDEX((x+1), y   )]) / 9;
}

/*------------------------------------------------------------------------*
  MakeColorMap - tries to build a creative color map for the landscape
 *------------------------------------------------------------------------*/
void MakeColorMap()
{
  int x,y,temp;

  for (x = 0; x < MAPSIZE; x++)
    for (y = 0; y < MAPSIZE; y++)
      {
        temp = heightmap[INDEX(x,y)] >> 1;
        if (heightmap[INDEX((x-1),y)] < heightmap[INDEX(x,y)])
          {
            temp -= 8*(heightmap[INDEX(x,y)] - heightmap[INDEX((x-1),y)]);
            if (temp < 2)
               temp = 2;
          }
        else
          {
            if (heightmap[INDEX((x-1),y)] > heightmap[INDEX(x,y)])
              {
                temp += 3*(heightmap[INDEX((x-1),y)] - heightmap[INDEX(x,y)]);
                if (temp > 127)
                  temp = 127;
              }
          }
        colormap[INDEX(x,y)] = temp;
      }
}
