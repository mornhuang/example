#include "time.h"
#include "alloc.h"
#include "graphics.h"
#include "dos.h"
#include "stdio.h"
#include "stdlib.h"
#include "math.h"
#include "fcntl.h"
#include "string.h"

void Initgraph(); /* initgraph */
void drawwindow(int x1,int y1,int x2,int y2,char *topname);
void Change_Control_window();
void End();
int getbit(unsigned char c,int n);
void clprint(int cx,int cy,char far *cpr,int c);
void initmouse(); /* initmouse */
void closemouse();
void dispmouse();
void mousemove();
void mouseplace(); /* mouse place&keys infomation */
void Opendilog(char *Dilog);
void getstring();
void closedilog();
void button(int bx1,int by1,int bx2,int by2,int c1,int c2); /* draw bars */
void init_bomb(int bombs,int bombh,int bombw);
void enter_bombs();
void reset_bomb();
void showtime();
void showbombs();
void show_around(int yy,int mm);
void safe_place(int ll,int bb);
void music_1();
void bomb_game();
void bomb_lb_recorder();
void bomb_press();

enum NOTES /* A0=c1=440Hz */
{ C11=0,
C10=131,D10=147,E10=165,F10=175,G10=196,A10=220,b10=247,
C0=262, D0=296, E0=330, F0=349, G0=392, A0=440, B0=494,
C1=523, D1=587, E1=659, F1=698, G1=784, A1=880, B1=988,
C2=1047,D2=1175,E2=1319,F2=1397,G2=1568,A2=1760,B2=1976,
C3=2094,D3=2350,E3=2639,F3=2795,G3=3135,A3=3520,B3=3955,
}
song[]=
{/* dream whole life for love */
A0,4,G0,4,F0,24, /*FOR LOVE*/
D1,4,C1,4,B0,24, /*WAIT WHOLE LIFE*/
C11,8,B0,4,C1,4,D1,4,C1,4,B0,4,C1,4,B0,4,C1,2,B0,2,A0,24, /*AM I MAD OR HIT ?*/
A0,4,G0,4,F0,4,G0,4,A0,8,B0,4,A0,4,G0,4,F0,4,E0,4,F0,4,F0,4,G0,4,E0,8,F0,32,
/*HOW I LOVE YOU IS HOW BIG THE JOKE FOR ME !*/
A0,4,G0,4,F0,24, /*CAN'T THINK*/
D1,4,C1,4,B0,24, /*CAN'T ASK */
C11,8,B0,4,C1,4,D1,4,C1,4,B0,4,C1,4,B0,4,C1,2,B0,2,A0,24, /*AM I FOOLISH OR SIMPLE ?*/
A0,4,G0,4,F0,4,G0,4,A0,8,B0,4,A0,4,G0,4,F0,4,E0,4,F0,4,F0,4,G0,4,E0,8,F0,32,
/*HOW MANY SPRING CLOD WINTER COULD HOLD ?*/
F0,24,F0,4,A0,4,B0,8,/*FOR LOVE */
C11,4,D1,4,C1,4,B0,4,A0,4,F0,4,F0,16, /*NOT WANT WAKE*/
F0,4,A0,4,B0,16, /*EVEN TOO HEART*/
C11,4,D1,4,F1,4,E1,4,C1,4,C1,20, /*I NEVER MIND */
F0,24,F0,4,A0,4,B0,8, /*for love */
C11,4,D1,4,C1,4,B0,4,C1,4,B0,4,A0,16, /*THIS DREAM NEVER BE SPRITED */
C11,4,A0,4,G0,12,F0,4,G0,4,A0,4,B0,4,D1,4,C1,32, /*I'D WAIT FOR WHOLE LIFE */

A0,4,G0,4,F0,24, /*FOR LOVE */
D1,4,C1,4,B0,24, /*DREAM A LIFE */
C11,8,B0,4,C1,4,D1,4,C1,4,B0,4,C1,4,B0,4,C1,2,B0,2,A0,24, /*WOUNDERING FOR SEVERAL LIVES */
A0,4,G0,4,F0,4,G0,4,A0,8,B0,4,A0,4,G0,4,F0,4,E0,4,F0,4,F0,4,G0,4,E0,8,F0,32, /*THE WALM IN DREAM COULD MAKE ME FORCE THE COLD*/


999,000
};

int lb=320,ym=240,lbym,oldlb,oldym,fsize=1,fhv=0,fonts=0,EXIT=0,tflag=1,rest_bombs=15,
bomb_number=15,bomb_high=6,bomb_width=21,dragflag=0,mvx,mvy,ccc=6,newlb,newym;
int oldwx1=100,wx1=100,oldwx2=510+2*15,wx2=510+2*15,oldwy1=100+15,rstflg=0,m_on_off=0,s_on_off=1,
BW=16,control,wy1=100+15,oldwy2=304+3*6*2,wy2=304+3*6*2;
int t=0,oldt=0,tt=0,OL=1974,OB=215;
long float first1=0,first2=0,first3=0;

int safef[42][64],bomb_flag[42][64],lb_bomb[36+6][36+2*15-2],ym_bombrdf[36+6][36+2*15-2]; /* 40 62 */
char *ttt=" ",*topname="摹拟Window-BOMB界面",*bombs=" ";
void *Img1;

main()
{

Initgraph();
initmouse();
music_1();
}

void Initgraph() /* initgraph */
{
int GD=DETECT,GM;
int ErrorCode;
initgraph(&GD,&GM,"c:\\tc20\\bgi");
graphresult(); /* clear error code */
ErrorCode = graphresult(); /* check result */
if( ErrorCode != grOk ) /* if failed */
{
closegraph();
printf(" Graphics System Error: %s\n", grapherrormsg( ErrorCode ) );
exit( 1 );
}
setvisualpage(0);
setactivepage(0);
setbkcolor(LIGHTGRAY);setcolor(DARKGRAY);
}

void drawwindow(int x1,int y1,int x2,int y2,char *topname)
{
FILE *fp;int l,b;unsigned char LBym[215];
dragflag=1;
/* draw window */
setcolor(DARKGRAY);closemouse();

rectangle(x1-1,y1-1,x2,y2);
rectangle(x1+1,y1+1,x2-2,y2-2);

line(x1+20,y1,x1+20,y1+2);line(x2-20,y1,x2-20,y1+1); /*eight shot_lines*/
line(x1+20,y2,x1+20,y2-2);line(x2-20,y2,x2-20,y2-2);
line(x1,y1+20,x1+2,y1+20);line(x2,y1+20,x2-2,y1+20);
line(x1,y2-20,x1+2,y2-20);line(x2,y2-20,x2-2,y2-20);

rectangle(x1+3,y1+3,x1+18,y1+16); rectangle(x1+1,y1+1,x1+20,y1+18);
rectangle(x2-4,y1+3,x2-18,y1+16); rectangle(x2-2,y1+1,x2-20,y1+18);
button(x1,y1+18,x2,y2+1,WHITE,LIGHTGRAY);

setfillstyle(SOLID_FILL,LIGHTBLUE);floodfill(x1+21,y1+3,DARKGRAY);

rectangle(x1+7,y1+8,x1+15,y1+11); /*exit key*/
setfillstyle(SOLID_FILL,WHITE);floodfill(x1+8,y1+10,DARKGRAY);
clprint((x2+x1-strlen(topname)*9)/2,y1+1,topname,LIGHTGREEN);

/* bombs and time */
clprint(x1+6,y1+22,"雷数",DARKGRAY);
clprint(x2-42,y1+22,"时间",DARKGRAY);

setfillstyle(SOLID_FILL,LIGHTGRAY);
button((x1+x2)/2-8,y1+22,(x1+x2)/2+8,y1+36+2,WHITE,LIGHTGRAY);
button(x1+44,y1+22,x1+90,y1+38,LIGHTGRAY,WHITE);
button(x2-90,y1+22,x2-44,y1+38,LIGHTGRAY,WHITE);

circle((x1+x2)/2,y1+30,4); /* boy's face */

clprint(x2-42,y1+22,"时间",DARKGRAY);
rectangle(x2-90,y1+22,x2-44,y1+38);

dispmouse();setfillstyle(SOLID_FILL,LIGHTGRAY);
wx1=x1;wy1=y1;wx2=x2;wy2=y2;

setviewport(0,0,639,479,1);
if(m_on_off==1) /* music_on_off */
{clprint((wx2+wx1)/2-8*9,wy2+1,"Music->",DARKGRAY);rectangle((wx1+wx2)/2-8*9-3,wy2+3,(wx1+wx2)/2-9,wy2+14);
clprint((wx2+wx1)/2,wy2+1,"ON",RED);rectangle((wx1+wx2)/2-3,wy2+3,(wx1+wx2)/2+21,wy2+14);
clprint((wx2+wx1)/2+3*9,wy2+1,"OFF",DARKGRAY);rectangle((wx1+wx2)/2+24,wy2+3,(wx1+wx2)/2+60,wy2+14);
}
if(m_on_off==0)
{clprint((wx2+wx1)/2-8*9,wy2+1,"Music->",DARKGRAY);rectangle((wx1+wx2)/2-8*9-3,wy2+3,(wx1+wx2)/2-9,wy2+14);
clprint((wx2+wx1)/2,wy2+1,"ON",DARKGRAY);rectangle((wx1+wx2)/2-3,wy2+3,(wx1+wx2)/2+21,wy2+14);
clprint((wx2+wx1)/2+3*9,wy2+1,"OFF",RED);rectangle((wx1+wx2)/2+24,wy2+3,(wx1+wx2)/2+60,wy2+14);
}

if(s_on_off==1) /* sound_on_off */
{clprint((wx2+wx1)/2-8*9,wy1-13,"Sound->",DARKGRAY);rectangle((wx1+wx2)/2-8*9-3,wy1-10,(wx1+wx2)/2-9,wy1-2);
clprint((wx2+wx1)/2,wy1-13,"ON",RED);rectangle((wx1+wx2)/2-3,wy1-10,(wx1+wx2)/2+21,wy1-2);
clprint((wx2+wx1)/2+3*9,wy1-13,"OFF",DARKGRAY);rectangle((wx1+wx2)/2+24,wy1-10,(wx1+wx2)/2+60,wy1-2);
}
if(s_on_off==0)
{clprint((wx2+wx1)/2-8*9,wy1-13,"Sound->",DARKGRAY);rectangle((wx1+wx2)/2-8*9-3,wy1-10,(wx1+wx2)/2-9,wy1-2);
clprint((wx2+wx1)/2,wy1-13,"ON",DARKGRAY);rectangle((wx1+wx2)/2-3,wy1-10,(wx1+wx2)/2+21,wy1-2);
clprint((wx2+wx1)/2+3*9,wy1-13,"OFF",RED);rectangle((wx1+wx2)/2+24,wy1-10,(wx1+wx2)/2+60,wy1-2);
}
setcolor(DARKGRAY);

}


void Change_Control_window()
{int e=0,f=0,g=0,l=0,b=0,ll=0,bb=0;char *bombf=" ";
FILE *fp;
mouseplace();oldlb=lb;oldym=ym;

/* boy face */
if(lb>(wx1+wx2)/2-8&&ym>wy1+22&&lb<(wx1+wx2)/2+8&&ym<wy1+36+2&&lbym==1)
{
while(lbym==1)
{mouseplace();
if(lb>(wx1+wx2)/2-8&&ym>wy1+22&&lb<(wx1+wx2)/2+8&&ym<wy1+36+2&&lbym==0)
{reset_bomb();}
}
}

/* control music */
if(lb>(wx1+wx2)/2-4&&ym>wy2+2&&lb<(wx1+wx2)/2+21&&ym<wy2+14&&lbym==1&&m_on_off==0)
{m_on_off=1;
clprint((wx2+wx1)/2,wy2+1,"ON",RED);
rectangle((wx1+wx2)/2-3,wy2+3,(wx1+wx2)/2+21,wy2+14);
clprint((wx2+wx1)/2+3*9,wy2+1,"OFF",DARKGRAY);
rectangle((wx1+wx2)/2+24,wy2+3,(wx1+wx2)/2+60,wy2+14);
}
if(lb>(wx1+wx2)/2+23&&ym>wy2+2&&lb<(wx1+wx2)/2+60&&ym<wy2+14&&lbym==1&&m_on_off==1)
{m_on_off=0;
clprint((wx2+wx1)/2,wy2+1,"ON",DARKGRAY);
rectangle((wx1+wx2)/2-3,wy2+3,(wx1+wx2)/2+21,wy2+14);
clprint((wx2+wx1)/2+3*9,wy2+1,"OFF",RED);
rectangle((wx1+wx2)/2+24,wy2+3,(wx1+wx2)/2+60,wy2+14);
}

/* control sound */
if(lb>(wx1+wx2)/2-4&&ym>wy1-13&&lb<(wx1+wx2)/2+21&&ym<wy1-2&&lbym==1&&s_on_off==0)
{s_on_off=1;
clprint((wx2+wx1)/2,wy1-13,"ON",RED);rectangle((wx1+wx2)/2-3,wy1-10,(wx1+wx2)/2+21,wy1-2);
clprint((wx2+wx1)/2+3*9,wy1-13,"OFF",DARKGRAY);rectangle((wx1+wx2)/2+24,wy1-10,(wx1+wx2)/2+60,wy1-2);
}
if(lb>(wx1+wx2)/2+23&&ym>wy1-13&&lb<(wx1+wx2)/2+60&&ym<wy1-2&&lbym==1&&s_on_off==1)
{s_on_off=0;
clprint((wx2+wx1)/2,wy1-13,"ON",DARKGRAY);rectangle((wx1+wx2)/2-3,wy1-10,(wx1+wx2)/2+21,wy1-2);
clprint((wx2+wx1)/2+3*9,wy1-13,"OFF",RED);rectangle((wx1+wx2)/2+24,wy1-10,(wx1+wx2)/2+60,wy1-2);
}
/* enter bomb's number.high.width */

if(lb>wx2-18&&ym>wy1+4&&lb<wx2-4&&ym<wy1+18&&lbym==1)
{
while(lbym==1)
{mouseplace();
if(lb>wx2-18&&ym>wy1+4&&lb<wx2-4&&ym<wy1+18&&lbym==0)
{enter_bombs();}
}
}

/* !!!!!!!!!! bomb_game control block !!!!!!!!!!!! */
/* debug safe_place */
if(lb>wx1+2&&lb<wx2-5&&ym>wy1+40&&ym<wy2-5&&lbym==1)
{/*(wx1+3+b*BW,wy1+42+l*BW,wx1+3+(b+1)*BW,wy1+42+(l+1)*BW)*/
tflag=0;
while(lbym==1) /* WA '1' BOMB '0' NOT BOMB */
{
mouseplace();
l=(ym-42-wy1)/BW+1;b=(lb-wx1-2)/BW+1;
mousemove();
if(safef[l][b]!=1&&bomb_flag[l][b]!=1&&(mvx!=0||mvy!=0)&&lb>wx1+2&&lb<wx2-5&&ym>wy1+40&&ym<wy2-5)bomb_press();

mouseplace();
if(lb>wx1+2&&lb<wx2-5&&ym>wy1+40&&ym<wy2-5&&lbym==0)
{OL=1974;OB=215;
if(s_on_off==1){for(l=3060*2;l>2150;l--){sound(l);}nosound();}

l=(ym-42-wy1)/BW+1;b=(lb-wx1-2)/BW+1;
if(lb_bomb[l][b]==0&&bomb_flag[l][b]!=1)
{
if(ym_bombrdf[l][b]!=0&&bomb_flag[l][b]!=1&&safef[l][b]!=1)
{
itoa(ym_bombrdf[l][b],bombf,10);closemouse();
setfillstyle(SLASH_FILL,CYAN);
bar3d(wx1+3-BW+b*BW,wy1+42-BW+l*BW,wx1+3+b*BW,wy1+42+l*BW,0,0);
setfillstyle(SLASH_FILL,LIGHTGRAY);
clprint(wx1-BW/2+b*BW,wy1+35-BW/2+l*BW,bombf,7+ym_bombrdf[l][b]);dispmouse();
}
safef[l][b]=1;
if(ym_bombrdf[l][b]==0) /*YM_FLAG_AROUND*/
{
itoa(ym_bombrdf[l][b],bombf,10);
closemouse();
setfillstyle(SLASH_FILL,CYAN);
bar3d(wx1+3-BW+b*BW,wy1+42-BW+l*BW,wx1+3+b*BW,wy1+42+l*BW,0,5);

safe_place(l,b);

setfillstyle(SOLID_FILL,LIGHTGRAY);
dispmouse();
}
/* win_or_not
for(e=0;e<42;e++)for(f=0;f<64;f++){if(safef[e][f]==1)g++;}
if(bomb_high*bomb_width-g==bomb_number)bomb_lb_recorder();*/

}/*if bomb==0*/

if(lb_bomb[l][b]==1&&bomb_flag[l][b]!=1)
{closemouse();tflag=1;
{bomb_flag[l][b]=1;
/* draw a bomb */
ccc=getcolor();setcolor(DARKGRAY);
circle(wx1+3-BW/2+b*BW,wy1+42-BW/2+l*BW,BW/3);
circle(wx1+5-BW/2+b*BW,wy1+40-BW/2+l*BW,BW/6);
setfillstyle(SOLID_FILL,WHITE);
floodfill(wx1+5-BW/2+b*BW,wy1+40-BW/2+l*BW,DARKGRAY);
setfillstyle(SOLID_FILL,DARKGRAY);
floodfill(wx1+3-BW/2+b*BW,wy1+42+BW/6-BW/2+l*BW,DARKGRAY);
setfillstyle(SOLID_FILL,LIGHTRED);
floodfill(wx1+3+2-BW+b*BW,wy1+42+2-BW+l*BW,DARKGRAY);
setfillstyle(SOLID_FILL,LIGHTGRAY);setcolor(ccc);
}

if(s_on_off==1)
{for(l=3060;l>215;l--){sound(l);}
for(l=215;l<3060;l++)sound(36);nosound();
}
for(l=1;l<bomb_high+1;l++)
for(b=1;b<bomb_width+1;b++)
{
if(lb_bomb[l][b]==0&&bomb_flag[l][b]==1)
{
ccc=getcolor();setcolor(LIGHTBLUE);
line(wx1+b*BW-BW/6,wy1+46-BW+l*BW+BW/6,wx1+6-BW+b*BW+BW/6,wy1+40+l*BW-BW/6);
line(wx1+6-BW+b*BW+BW/6,wy1+46-BW+l*BW+BW/6,wx1+b*BW-BW/6,wy1+40+l*BW-BW/6);
setcolor(ccc);
}
if(lb_bomb[l][b]==1&&bomb_flag[l][b]!=1)
{
/* draw a bomb */
ccc=getcolor();setcolor(DARKGRAY);
circle(wx1+3-BW/2+b*BW,wy1+42-BW/2+l*BW,BW/3);
circle(wx1+5-BW/2+b*BW,wy1+40-BW/2+l*BW,BW/6);
setfillstyle(SOLID_FILL,WHITE);
floodfill(wx1+5-BW/2+b*BW,wy1+40-BW/2+l*BW,DARKGRAY);
setfillstyle(SOLID_FILL,DARKGRAY);
floodfill(wx1+3-BW/2+b*BW,wy1+42+BW/6-BW/2+l*BW,DARKGRAY);
setfillstyle(SOLID_FILL,LIGHTGRAY);setcolor(ccc);
}


if(lb_bomb[l][b]==0&&bomb_flag[l][b]!=1)
{
if(ym_bombrdf[l][b]==0)
{
/*itoa(ym_bombrdf[l][b],bombf,10);*/
setfillstyle(SLASH_FILL,CYAN);
bar3d(wx1+3-BW+b*BW,wy1+42-BW+l*BW,wx1+3+b*BW,wy1+42+l*BW,0,2.15);
setfillstyle(SOLID_FILL,LIGHTGRAY);
}
if(ym_bombrdf[l][b]!=0)
{
itoa(ym_bombrdf[l][b],bombf,10);
clprint(wx1-BW/2+b*BW,wy1+35-BW/2+l*BW,bombf,7+ym_bombrdf[l][b]);
if(bomb_flag[l][b]==0)
{itoa(ym_bombrdf[l][b],bombf,10);
setfillstyle(SLASH_FILL,CYAN);
bar3d(wx1+3-BW+b*BW,wy1+42-BW+l*BW,wx1+3+b*BW,wy1+42+l*BW,0,2.15);
setfillstyle(SOLID_FILL,LIGHTGRAY);
clprint(wx1-BW/2+b*BW,wy1+35-BW/2+l*BW,bombf,7+ym_bombrdf[l][b]);
}
}
}/*for*/
}
}/*if bomb==1*/
}/* if lbym==0*/
}/* while lbym==1*/
dispmouse();
}/* if lbymm==1 */



/* flag bomb */
if(lb>wx1+2&&lb<wx2-5&&ym>wy1+40&&ym<wy2-5&&lbym==2)
{
tflag=0;
while(lbym==2) /* ZUO BIAO ZHI */
{
mouseplace();
l=(ym-42-wy1)/BW+1;b=(lb-wx1-2)/BW+1;
mousemove();
if(safef[l][b]!=1&&bomb_flag[l][b]!=1&&(mvx!=0||mvy!=0)&&lb>wx1+2&&lb<wx2-5&&ym>wy1+40&&ym<wy2-5)bomb_press();

mouseplace();
if(safef[l][b]!=1&&lb>wx1+2&&lb<wx2-5&&ym>wy1+40&&ym<wy2-5&&lbym==0)
{OL=1974;OB=215;
if(s_on_off==1){for(l=2150;l<3060*2;l++){sound(l);}nosound();}

l=(ym-42-wy1)/BW+1;b=(lb-wx1-2)/BW+1;
closemouse();
button(wx1+3-BW+b*BW,wy1+42-BW+l*BW,wx1+3+b*BW,wy1+42+l*BW,WHITE,LIGHTGRAY);
if(bomb_flag[l][b]==0) /* is bomb ? '1' yes '0' no */
{
bomb_flag[l][b]=1;rest_bombs-=1;
/* bar3d(wx1+3-BW+b*BW,wy1+42-BW+l*BW,wx1+3+b*BW,wy1+42+l*BW,0,0); */
/* draw a bomb */ccc=getcolor();setcolor(DARKGRAY);
circle(wx1+3-BW/2+b*BW,wy1+42-BW/2+l*BW,3+2);setcolor(ccc);
clprint(wx1-BW/2+b*BW,wy1+35-BW/2+l*BW,"*",LIGHTRED);
}
else
{if(bomb_flag[l][b]==1) /* is bomb ? '1' yes '0' no */
{
bomb_flag[l][b]=0;rest_bombs+=1;
ccc=getcolor();setcolor(LIGHTGRAY);
circle(wx1+3-BW/2+b*BW,wy1+42-BW/2+l*BW,3+2);setcolor(ccc);
clprint(wx1-BW/2+b*BW,wy1+35-BW/2+l*BW,"*",LIGHTGRAY);

}
}
dispmouse();

if(rest_bombs==0)bomb_lb_recorder();

}/* if lbym==0 */
}/* while lbym==2 */
}/* iflbym==2 */
showbombs();

/* show recorder */

if(lb>wx1+20&&lb<wx2-20&&ym>wy1+2&&ym<wy1+20&&lbym==1)
{
while(lbym==1)
{
mouseplace();
if(lb>wx1+20&&lb<wx2-20&&ym>wy1+2&&ym<wy1+20&&lbym==0)
{
if((fp=fopen("recorder.lby","rb"))!=NULL)
{fgetc(fp);
while(!feof(fp))
{ccc=getcolor();setcolor(RED);
Opendilog("First_one:");
rectangle(0,0,471-215,306-215);
for(bb=0;bb<5;bb++)
{
for(ll=0;ll<15;ll++){*(bombs+ll)='\0';}
for(ll=0;(*(bombs+ll)=fgetc(fp))!='%'&&!feof(fp);ll++);; /* 1997.12.30.10.36.zigong earthquick */
if(bb<4){outtextxy(15+bb/2*(150+15),40+fmod(bb,2)*9,bombs);}
else{outtextxy(20+150,30,bombs);}
}outtextxy(2,2,"Marks=bombs^3/(high*width*time)");
while(1){mouseplace();if(lbym!=0)break;}
setcolor(ccc);closedilog();
}
fclose(fp);
}
else{Opendilog("Have no such filename");
outtextxy(36,52,"Press a key to return");getch();closedilog();}
}/*iflbym==0*/
}/*whilelbym==1*/
}/* if lbym==1*/

/* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */

/* exit it */
if(lb>wx1+2&&lb<wx1+20&&ym>wy1+2&&ym<wy1+20&&lbym==1) /* W-[E]-bar */
{
while(lbym==1)
{mouseplace();
if(lb>wx1+2&&lb<wx1+20&&ym>wy1+2&&ym<wy1+20&&lbym==0)
{End();}
}
}
}

void End()
{int lbt,lbt1=0,lbt2=0;
Opendilog(" Yuo are sure to exit ?");
setfillstyle(SOLID_FILL,LIGHTGRAY);closemouse();
setcolor(DARKGRAY);
button(30,50,70,70,15,2);clprint(34,52,"Yes",BLUE);
button(80,50,120,70,15,2);clprint(84,52,"No",BLUE);
dispmouse();
while(1)
{mouseplace();lbt=0;
while(lbym==1)
{mouseplace();
lb=lb-215;ym=ym-215;
if(lb>30&&lb<70&&ym>50&&ym<70&&lbt1==0)
{closemouse();
lbt1=1;lbt2=0;
button(30,50,70,70,2,15);clprint(34,52,"Yes",BLUE);
button(80,50,120,70,15,2);clprint(84,52,"No",BLUE);
dispmouse();
}
if(lb>80&&lb<120&&ym>50&&ym<70&&lbt2==0)
{closemouse();
lbt2=1;lbt1=0;
button(30,50,70,70,15,2);clprint(34,52,"Yes",BLUE);
button(80,50,120,70,2,15);clprint(84,52,"No",BLUE);
dispmouse();
}
if(lb>30&&lb<70&&ym>50&&ym<70&&lbym==0){EXIT=1;lbt=1;break;}
if(lb>80&&lb<120&&ym>50&&ym<70&&lbym==0){EXIT=2;lbt=1;break;}
}if(lbt==1)break;
}setcolor(ccc); setfillstyle(SOLID_FILL,ccc);
if(EXIT==1)
{
closegraph();closemouse();

outportb(0x61,control); /* no_speek */

printf("\n\n\b\b%s\n"," Copyright (C) LiYang Corp. Bomb-Game Version 1.0 All Rights Reserved");
printf("\b\b\b\b%s\n"," Zigong Sichuan China. 1998.1. Yuanyang");exit(1);
}
else{closedilog();}
}

int getbit(unsigned char c,int n)
{return((c>>n)&1);}

void clprint(int cx,int cy,char far *cpr,int c)
{
int qu_ma,wei_ma,fp,i1,i2,i3,rec,i;char * s=" ";
long l;unsigned char by[32];ccc=getcolor();

fp=open("hzk16",O_RDONLY|O_BINARY);
if(fp==-1)
{ closemouse();
Opendilog("Have no filename:HZK16");
outtextxy(36,52,"Press a key to return");
getch();closedilog();dispmouse();
} /*if*/
while((i=*s=*cpr++)!=0)
{
if(i>>7==0x00)
{
setcolor(c);settextstyle(0,0,1);
outtextxy(cx,cy+4,s);cx=cx+8+1;
setcolor(ccc);settextstyle(fonts,fhv,fsize);}
else
{
qu_ma=(i-0xa1)&0x07f; /* turn lei_qu_ma to qu_ma */
wei_ma=(*cpr++-0xa1)&0x07f; /* turn lei_wei_ma to wei_ma */

closemouse();
rec=qu_ma*94+wei_ma;
l=rec*32L;
lseek(fp,l,SEEK_SET);
read(fp,by,32);
for(i1=0;i1<16;i1++)
for(i2=0;i2<2;i2++)
for(i3=0;i3<8;i3++)
if(getbit(by[i1*2+i2],7-i3))
putpixel(cx+i2*8+i3+2,cy+i1,c);
cx=cx+16+2;dispmouse();
}
} /*while*/
close(fp);setcolor(ccc);
}

void initmouse() /* initmouse */
{ union REGS regs;
regs.x.ax=0x0000; /* INITMOUSE */
int86(0x33,&regs,&regs);
if(regs.x.ax==0)
{outtextxy(100,100,"No mouse or mousedriver !");
outtextxy(100,120,"press a key to exit !!");
getch();closegraph();}
dispmouse();
regs.x.ax=0x0007; /* Limit mouse's X place */
regs.x.cx=5;regs.x.dx=630;
int86(0x33,&regs,&regs);
regs.x.ax=0x0008; /* Limit mouse's Y place */
regs.x.cx=6;regs.x.dx=470;
int86(0x33,&regs,&regs);
}

void closemouse()
{
union REGS regs;
regs.x.ax=0x0002; /*CLOSE MOUSE*/
int86(0x33,&regs,&regs);
}

void dispmouse()
{ union REGS regs;
regs.x.ax=0x0001; /*DISP MOUSE*/
int86(0x33,&regs,&regs);
}

void mousemove()
{ union REGS regs;
regs.x.ax=0x000B; /*MOUSE MOVE OR NOT */
int86(0x33,&regs,&regs);
mvx=regs.x.cx;
mvy=regs.x.dx;
}

void mouseplace() /* mouse place&keys infomation */
{ union REGS regs;
regs.x.ax=0x0003; /*510304740205471*/
int86(0x33,&regs,&regs);
lbym=regs.x.bx; /* show which mouse key is pressed */
lb=regs.x.cx; /* show mouse x place */
ym=regs.x.dx; /* show mouse y place */
}

void Opendilog(char *Dilog)
{
unsigned long Size1;closemouse();
setviewport(45,40,620,455,1);
outportb(0x61,control); /* no_speek */
settextstyle(1,0,1);setcolor(LIGHTBLUE);
Size1=imagesize(215,215,471,306);Img1=malloc(Size1);
if(Img1!=NULL){
getimage(170,175,426,266,Img1);
setlinestyle(SOLID_LINE,0,NORM_WIDTH);

setviewport(215,215,471,306,1);clearviewport();
rectangle(0,0,471-215,306-215);
outtextxy(20,15,Dilog );
outtextxy(152+20,60,"LB.Ver 1.0");
line(36,74-10,152,74-10);
settextstyle(0,0,1);}
setcolor(ccc);dispmouse();
}

void getstring()
{
char s;int i=0;
closemouse();setcolor(DARKGRAY);
outtextxy(36,52,bombs);
while((s=getch())!='\r')
{if(s==83)
{for(i=0;i<25;i++){*(bombs+i)='\0';}
setfillstyle(SOLID_FILL,EGA_LIGHTGRAY);
bar(36,52,150,62);setfillstyle(SOLID_FILL,ccc);i=0;}
else{*(bombs+i)=s;outtextxy(36,52,bombs);i++;}}
dispmouse();setcolor(ccc);
}


void closedilog()
{
closemouse();
setviewport(45,40,620,455,1);putimage(170,175,Img1,COPY_PUT);
free(Img1);dispmouse();setviewport(0,0,639,479,1);
}

void button(int bx1,int by1,int bx2,int by2,int c1,int c2) /* draw bars */
{
ccc=getcolor();
setcolor(EGA_DARKGRAY);setfillstyle(SOLID_FILL,LIGHTGRAY);
bar(bx1,by1,bx2,by2);rectangle(bx1+1,by1+1,bx2-1,by2-1);
rectangle(bx1,by1,bx2,by2);setcolor(c1);
line(bx1+2,by1+2,bx2-1,by1+2);line(bx2-1,by1+2,bx2-1,by2-2);
setcolor(c2);
line(bx1+3,by1+2,bx1+3,by2-2);line(bx1+3,by2-2,bx2-2,by2-2);
setcolor(ccc);
}

void init_bomb(int bombs,int bombh,int bombw)
{
int l,b,y; /* y is bomb's number */

t=1;oldt=0;tflag=1;tt=0;

closemouse();clearviewport();dispmouse();
drawwindow(320-bombw*BW/2-3,240-bombh*BW/2-23,320+bombw*BW/2+3,240+bombh*BW/2+23,topname);

setviewport(wx1+3,wy1+41,wx2-3,wy2-3,1);
closemouse();clearviewport();dispmouse();
setviewport(0,0,639,479,1);

rest_bombs=bombs;showbombs();

for(l=0;l<42;l++){for(b=0;b<64;b++)
{lb_bomb[l][b]=0;bomb_flag[l][b]=0;safef[l][b]=0;}}

/* limit bomb_high>=6,bomb_width>=21 bomb_high<36\,bomb_width<60 */

/* lb_bomb[][]=0 not bomb,lb_bomb[][]=1 is bomb *//*bombs=1500*/

randomize();
for(y=0;y<bombs;y++)
{
lablelb:
l=random(bombh);
b=random(bombw);
if(lb_bomb[l+1][b+1]==1)
{goto lablelb;}
else{lb_bomb[l+1][b+1]=1;}
}

/* ym_bombrdf[][]=it's around bombs! */
for(l=1;l<bombh+1;l++)
for(b=1;b<bombw+1;b++)
{ym_bombrdf[l][b]=
lb_bomb[l-1][b]+
lb_bomb[l+1][b]+
lb_bomb[l-1][b-1]+
lb_bomb[l][b-1]+
lb_bomb[l+1][b-1]+
lb_bomb[l-1][b+1]+
lb_bomb[l+1][b+1]+
lb_bomb[l][b+1];}
closemouse();
for(l=0;l<bombh;l++)
for(b=0;b<bombw;b++)
button(wx1+3+b*BW,wy1+42+l*BW,wx1+3+(b+1)*BW,wy1+42+(l+1)*BW,WHITE,LIGHTGRAY);
dispmouse();
}

void enter_bombs()
{
/*(320-bombw*BW/2-3,240-bombh*BW/2-23,320+bombw*BW/2+3,240+bombh*BW/2+23);*/

Opendilog("Enter Bomb_numbers:");
itoa(bomb_number,bombs,10);
getstring();bomb_number=atoi(bombs);
closedilog();

Opendilog("Enter Bomb_High:");
itoa(bomb_high,bombs,10);
getstring();bomb_high=atoi(bombs);
closedilog();

Opendilog("Enter Bomb_width:");
itoa(bomb_width,bombs,10);
getstring();bomb_width=atoi(bombs);
closedilog();

Opendilog("Enter How Bomb Big?:");
itoa(BW,bombs,10);
getstring();BW=atoi(bombs);
if(BW<15-2){BW=15-2;}if(BW>50){BW=50;}
closedilog();

if(bomb_number<6)bomb_number=6;
if(bomb_number>(480/BW-4)*(640/BW-5))bomb_number=(480/BW-4)*(640/BW-5);
if(bomb_number>bomb_high*bomb_width)bomb_number=bomb_width*bomb_high-15;

if(bomb_high<5)bomb_high=5;

if(bomb_high>480/BW-(6-BW/10))bomb_high=480/BW-(6-BW/10);

if(bomb_width<21)bomb_width=21;
if(bomb_width>640/BW-1)bomb_width=640/BW-1;

init_bomb(bomb_number,bomb_high,bomb_width);
}

void reset_bomb()
{
init_bomb(bomb_number,bomb_high,bomb_width);
}

void showtime()
{

union REGS regs;
regs.h.ah=0x2c; /* GET TIME */
int86(0x21,&regs,&regs);
t=regs.h.dh;

if(oldt!=t&&tt<=2150)
{tt++;
button(wx2-90,wy1+22,wx2-44,wy1+38,LIGHTGRAY,WHITE);oldt=t;
ccc=getcolor();setcolor(LIGHTGREEN);itoa(tt,ttt,10);
outtextxy(wx2-85,wy1+25,ttt);setcolor(ccc);}

}

void showbombs()
{
char *tmpbomb=" ";
itoa(rest_bombs,tmpbomb,10);
closemouse();
button(wx1+44,wy1+22,wx1+90,wy1+38,LIGHTGRAY,WHITE);
clprint(wx1+48,wy1+22,tmpbomb,RED);
dispmouse();
}

void safe_place(int ll,int bb)
{

if(ll>0&&ll<bomb_high+1&&bb>0&&bb<bomb_width+1)
{
show_around(ll,bb);
safef[ll][bb]=1;

if(safef[ll-1][bb]!=1&&ym_bombrdf[ll-1][bb]==0&&bomb_flag[ll-1][bb]==0)
{if(ll-1!=0)bar3d(wx1+3-BW+bb*BW,wy1+42-BW+(ll-1)*BW,wx1+3+bb*BW,wy1+42+(ll-1)*BW,0,5); safe_place(ll-1,bb);}

if(safef[ll+1][bb]!=1&&ym_bombrdf[ll+1][bb]==0&&bomb_flag[ll+1][bb]==0)
{if(ll!=bomb_high)bar3d(wx1+3-BW+bb*BW,wy1+42-BW+(ll+1)*BW,wx1+3+bb*BW,wy1+42+(ll+1)*BW,0,5); safe_place(ll+1,bb);}

if(safef[ll][bb-1]!=1&&ym_bombrdf[ll][bb-1]==0&&bomb_flag[ll][bb-1]==0)
{if(bb-1!=0)bar3d(wx1+3-BW+(bb-1)*BW,wy1+42-BW+ll*BW,wx1+3+(bb-1)*BW,wy1+42+ll*BW,0,5); safe_place(ll,bb-1);}

if(safef[ll+1][bb-1]!=1&&ym_bombrdf[ll+1][bb-1]==0&&bomb_flag[ll+1][bb-1]==0)
{if(bb-1!=0&&ll!=bomb_high)bar3d(wx1+3-BW+(bb-1)*BW,wy1+42-BW+(ll+1)*BW,wx1+3+(bb-1)*BW,wy1+42+(ll+1)*BW,0,5); safe_place(ll+1,bb-1);}

if(safef[ll-1][bb-1]!=1&&ym_bombrdf[ll-1][bb-1]==0&&bomb_flag[ll-1][bb-1]==0)
{if(bb-1!=0&&ll-1!=0)bar3d(wx1+3-BW+(bb-1)*BW,wy1+42-BW+(ll-1)*BW,wx1+3+(bb-1)*BW,wy1+42+(ll-1)*BW,0,5); safe_place(ll-1,bb-1);}

if(safef[ll][bb+1]!=1&&ym_bombrdf[ll][bb+1]==0&&bomb_flag[ll][bb+1]==0)
{if(bb!=bomb_width)bar3d(wx1+3-BW+(bb+1)*BW,wy1+42-BW+ll*BW,wx1+3+(bb+1)*BW,wy1+42+ll*BW,0,5); safe_place(ll,bb+1);}

if(safef[ll-1][bb+1]!=1&&ym_bombrdf[ll-1][bb+1]==0&&bomb_flag[ll-1][bb+1]==0)
{if(bb!=bomb_width&&ll-1!=0)bar3d(wx1+3-BW+(bb+1)*BW,wy1+42-BW+(ll-1)*BW,wx1+3+(bb+1)*BW,wy1+42+(ll-1)*BW,0,5); safe_place(ll-1,bb+1);}

if(safef[ll+1][bb+1]!=1&&ym_bombrdf[ll+1][bb+1]==0&&bomb_flag[ll+1][bb+1]==0)
{if(bb!=bomb_width&&ll!=bomb_high)bar3d(wx1+3-BW+(bb+1)*BW,wy1+42-BW+(ll+1)*BW,wx1+3+(bb+1)*BW,wy1+42+(ll+1)*BW,0,5); safe_place(ll+1,bb+1);}

}
}

void show_around(int yy,int mm)
{int aa,bb;char *bbf=" ";
for(aa=-1;aa<2;aa++)
for(bb=-1;bb<2;bb++)
{

if(!(aa==0&&bb==0)&&bomb_flag[aa+yy][bb+mm]==0&&ym_bombrdf[aa+yy][bb+mm]!=0&&lb_bomb[yy+aa][mm+bb]==0&&aa+yy>0&&aa+yy<bomb_high+1&&bb+mm>0&&bb+mm<bomb_width+1)
{itoa(ym_bombrdf[aa+yy][bb+mm],bbf,10);closemouse();
bar3d(wx1+3-BW+(bb+mm)*BW,wy1+42-BW+(aa+yy)*BW,wx1+3+(bb+mm)*BW,wy1+42+(aa+yy)*BW,0,0);
safef[yy+aa][mm+bb]=1;
clprint(wx1-BW/2+(bb+mm)*BW,wy1+35-BW/2+(aa+yy)*BW,bbf,7+ym_bombrdf[aa+yy][bb+mm]);dispmouse();
}

}
}

void music_1()
{
int note=0,fre,dur;

clock_t goal;

while(2)
{
if(rest_bombs==0)bomb_lb_recorder();
if(m_on_off==0){bomb_game();}
mouseplace();
while(song[note]!=999&&!kbhit()&&lbym==0&&m_on_off==1)
{
mouseplace();
bomb_game();if(tflag==0)showtime();

fre=song[note];dur=song[note+1];
if(fre)
{outportb(0x43,0xb6); /* get control to pc_speeker */
fre=(unsigned)(1192180L/fre);
outportb(0x42,(char)fre);
outportb(0x42,(char)(fre>>6));
control=inp(0x61);
outportb(0x61,control | 0x3);
}
goal=(clock_t)dur+clock(); /* delay the dur_time */
while(goal>clock())
{
mouseplace();if(tflag==0)showtime();
if(lbym!=0){bomb_game();}
}
if(fre)
outportb(0x61,control); /* no_speek */
goal=(clock_t)0;note+=2;
}note=0;
}/*while 2*/


}

void bomb_game()
{
mouseplace();
if(dragflag!=1)reset_bomb();
if(lbym!=0)
{
Change_Control_window();
}
if(tflag==0)showtime();

}

void bomb_lb_recorder()
{int l,b,ll,bb;
FILE *fp;
tflag=1;showbombs();
first2=bomb_number*bomb_number*bomb_number*1500/(bomb_high*bomb_width*tt);
for(l=0;l<42;l++)
for(b=0;b<64;b++)
{if(lb_bomb[l][b]!=bomb_flag[l][b])rstflg+=1;}
if(rstflg==0)
{if(s_on_off==1){for(l=215;l<3060;l++){sound(l);}nosound();}
rest_bombs=bomb_number;
/* in first...recorder */

if((fp=fopen("recorder.lby","r"))!=NULL)
{fgetc(fp);
while(!feof(fp))
{
for(bb=0;bb<5;bb++)
{
for(ll=0;ll<15;ll++){*(bombs+ll)='\0';}
ll=0;
while(2)
{
*(bombs+ll)=fgetc(fp);
if(*(bombs+ll)=='%'||feof(fp))break;
if(*(bombs+ll)<':'&&*(bombs+ll)>'/')ll++;
}
if(bb==1){first1=atoi(bombs)*atoi(bombs)*atoi(bombs)*1500;}
if(bb==2){first1/=atoi(bombs);}
if(bb==3){first1/=atoi(bombs);}
if(bb==4){first1/=atoi(bombs);}
}/* for bb */
if(first3<first1)first3=first1;
}/*while eof*/
fclose(fp);
}/* if !NULL*/

Opendilog("You Win ! your name ?");itoa(74215,bombs,16);
getstring();
closedilog();

if(*(bombs)!='\0'&&(first1<first2||first3<first2))
{
if((fp=fopen("recorder.lby","wa"))!=NULL)
{
fputs("%Name:",fp);/*%*/
fputs(bombs,fp);/*name*/fputs("%Bombs:",fp);/*%*/
itoa(bomb_number,bombs,10);
fputs(bombs,fp);/*bombs*/fputs("%High:",fp);/*%*/
itoa(bomb_high,bombs,10);
fputs(bombs,fp);/*high*/fputs("%Width:",fp);/*%*/
itoa(bomb_width,bombs,10);
fputs(bombs,fp);/*width*/fputs("%Time:",fp);/*%*/
fputs(ttt,fp);/*time*/
fclose(fp);
}/*if(!NULL)*/
else{Opendilog("Have no such filename");
outtextxy(36,52,"Press a key to return");getch();closedilog();}
}/* if f1<f2 */
}/*restflag=0*/
}

void bomb_press()
{int l,b;/*OL IS OLD BUTTON'S Y CURCER,OB IS ITS X CURCER */
l=(ym-42-wy1)/BW+1;b=(lb-wx1-2)/BW+1;

closemouse();
button(wx1+3-BW+OB*BW,wy1+42-BW+OL*BW,wx1+3+OB*BW,wy1+42+OL*BW,WHITE,LIGHTGRAY);
button(wx1+3-BW+b*BW,wy1+42-BW+l*BW,wx1+3+b*BW,wy1+42+l*BW,WHITE,LIGHTGRAY);
bar3d(wx1+3-BW+b*BW,wy1+42-BW+l*BW,wx1+3+b*BW,wy1+42+l*BW,0,5);
OL=l;OB=b;

dispmouse();
}