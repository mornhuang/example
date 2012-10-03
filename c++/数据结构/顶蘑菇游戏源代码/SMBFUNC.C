//SET UP ALL OF THE GRAPHICS AND OTHER JUNK//////////////////////////////////
void init()
{
  int loop;
  x_set_mode(X_MODE_320x240,320);	//SET MODE X//
  x_set_tripplebuffer(240);		//INSTALL TRIPPLE BUFFER//
  install_new_key_handler();		//INSTALL KEYBOARD HANDLER//
  x_install_vsync_handler(1);		//INSTALL VSYNC HANDLER//
  for (loop=0;loop<52;loop++)		//ALLOCATE MEM FOR GRAPHICS//
    if ((data.block[loop]=(char far *)malloc(258))==NULL)
      exitgame();
  for (loop=0;loop<3;loop++)
    if ((data.coin[loop]=(char far *)malloc(258))==NULL)
      exitgame();
  if ((bcoin=(cointype *)malloc(sizeof(cointype)))==NULL)
    exitgame();
  bcoin->next=NULL;
  if ((bscore=(scoretype *)malloc(sizeof(scoretype)))==NULL)
    exitgame();
  bscore->next=NULL;
  for (loop=0;loop<2;loop++)
  {
    player[loop].lives=3;
    player[loop].level=1;
    player[loop].screenx=0;
  }
}

//FILL SCREEN ///////////////////////////////////////////////////////////////
void fillscreen(int color)
{
  x_rect_fill(32,0,288,224,HiddenPageOffs,color);
}

//GET INPUTS FROM DATA FILES/////////////////////////////////////////////////
int getinputs()
{
  FILE *in;
  int count,loop1,loop2,loop3,yinit,blocknum,backnum;
  char *temp,ch,levelfile[13],graphfile[13];
  char far *tempbm;
  strcpy(levelfile,"LEVEL000.DAT");
  levelfile[7]=player[curplay].level%10+48;
  if (player[curplay].level>9)
    levelfile[6]=player[curplay].level/10+48;
  if ((in=fopen(levelfile,"r"))==NULL)
  {
    exitgame();
    printf("%s could not be found!\n",levelfile);
    exit(1);
  }
  data.maxlength=(int)fgetc(in);
  yinit=(int)fgetc(in);
  data.startpos[0]=(int)fgetc(in);
  data.startpos[1]=(int)fgetc(in);
  blocknum=(int)fgetc(in);
  backnum=(int)fgetc(in);
  fseek(in,0,SEEK_SET);
  for (loop1=0;loop1<14;loop1++)
    for (loop2=0;loop2<=data.maxlength;loop2++)
    {
      ch=getc(in);
      if(ch<='0')
	data.world[loop1][loop2]=0;
      if (ch>='a' && ch<='z')
	data.world[loop1][loop2]=ch-70;
      if (ch>='A' && ch<='Z')
	data.world[loop1][loop2]=ch-64;
      if (loop1==0)
	data.world[loop1][loop2]=0;
    }
  fclose(in);
  strcpy(graphfile,"BLOCK000.PCX");
  graphfile[7]=blocknum;
  if ((in=fopen(graphfile,"rb"))==NULL)
  {
    exitgame();
    printf("%s could not be found!\n",graphfile);
    exit(1);
  }
  temp=getpcx(in,temp,6000);
  tempbm=(char far *)farmalloc(258);
  tempbm[0]=tempbm[1]=16;
  for (loop1=0;loop1<26;loop1++)
  {
    count=2;
    for (loop2=0;loop2<16;loop2++)
      for (loop3=0;loop3<16;loop3++)
	tempbm[count++]=temp[loop1*17+(loop2+1)*320+loop3+1];
    x_bm_to_pbm(tempbm,data.block[loop1+26]);
  }
  free(temp);
  fseek(in,-768,SEEK_END);
  for (loop1=0;loop1<256;loop1++)
  {
    data.palette[loop1].red=(getc(in)>>2);
    data.palette[loop1].green=(getc(in)>>2);
    data.palette[loop1].blue=(getc(in)>>2);
  }
  fclose(in);
  strcpy(graphfile,"BGRND000.PCX");
  graphfile[7]=backnum;
  if ((in=fopen(graphfile,"rb"))==NULL)
  {
    exitgame();
    printf("%s could not be found!\n",graphfile);
    exit(1);
  }
  temp=getpcx(in,temp,6000);
  for (loop1=0;loop1<26;loop1++)
  {
    count=2;
    for (loop2=0;loop2<16;loop2++)
      for (loop3=0;loop3<16;loop3++)
	tempbm[count++]=temp[loop1*17+(loop2+1)*320+loop3+1];
    x_bm_to_pbm(tempbm,data.block[loop1]);
  }
  free(temp);
  fclose(in);
  strcpy(graphfile,"COIN.PCX");
  if ((in=fopen(graphfile,"rb"))==NULL)
  {
    exitgame();
    printf("%s could not be found!\n",graphfile);
    exit(1);
  }
  temp=getpcx(in,temp,6000);
  for (loop1=0;loop1<3;loop1++)
  {
    count=2;
    for (loop2=0;loop2<16;loop2++)
      for (loop3=0;loop3<16;loop3++)
	tempbm[count++]=temp[loop1*17+(loop2+1)*320+loop3];
    x_bm_to_pbm(tempbm,data.coin[loop1]);
  }
  farfree(tempbm);
  free(temp);
  fclose(in);
  for (loop1=0;loop1<256;loop1++)
    setpalette(loop1,data.palette[loop1]);
  return(yinit);
}

//GET MARIO DATA/////////////////////////////////////////////////////////////
void getmario()
{
  FILE *marioin;
  int loop1,loop2,loop3;
  char *temp;
  if ((marioin=fopen("MARIO.PCX","rb"))==NULL)
  {
    exitgame();
    printf("MARIO.PCX could not be found!\n");
    exit(1);
  }
  fseek(marioin,128,SEEK_SET);
  temp=(char *)malloc(6000);
  temp=getpcx(marioin,temp,6000);
  for (loop1=0;loop1<7;loop1++)
    for (loop2=0;loop2<=15;loop2++)
      for (loop3=0;loop3<=15;loop3++)
	data.mario[loop1][loop2][loop3]=temp[loop1*17+(loop2+1)*320+loop3+1];
  fseek(marioin,-765,SEEK_END);
  for (loop1=0;loop1<2;loop1++)
  {
    data.marioc[loop1].red=(getc(marioin)>>2);
    data.marioc[loop1].green=(getc(marioin)>>2);
    data.marioc[loop1].blue=(getc(marioin)>>2);
  }
  fseek(marioin,-750,SEEK_END);
  for (loop1=0;loop1<2;loop1++)
  {
    data.luigic[loop1].red=(getc(marioin)>>2);
    data.luigic[loop1].green=(getc(marioin)>>2);
    data.luigic[loop1].blue=(getc(marioin)>>2);
  }
  fclose(marioin);
  free(temp);
}

char *getpcx(FILE *file, char *temp, int length)
{
  long int count=0;
  int numbytes,loop;
  unsigned char ch;
  fseek(file,128,SEEK_SET);
  temp=(char *)malloc(length);
  while(count<length)
  {
    ch=fgetc(file);
    if ((ch>=192 && ch<=255))
    {
      numbytes=ch-192;
      ch=fgetc(file);
      for(loop=0;loop<numbytes;loop++)
	temp[count++]=ch;
    }
    else
      temp[count++]=ch;
  }
  return(temp);
}

//SET PALETTE////////////////////////////////////////////////////////////////
void setpalette(int index, colorpal color)
{
  outp(0x3c6,0xff);
  outp(0x3c8,index);
  outp(0x3c9,color.red);
  outp(0x3c9,color.green);
  outp(0x3c9,color.blue);
}

//CALCULATE CURRENT SCREEN///////////////////////////////////////////////////
void drawscreen(int x)
{
  int loop1,loop2,back,x2;
  static int dy;
  status();
  x2=x;
  back=0;
  while(x2>=16)
  {
    x2-=16;
    back++;
  }
  for (loop1=1;loop1<=13;loop1++)
    for (loop2=back;loop2<=back+16;loop2++)
      if (data.world[loop1][loop2]>0)
      {
	if (data.bumped[0]==loop2 && data.bumped[1]==loop1)
	{
	  dy+=data.bumploop;
	  x_put_masked_pbm((loop2<<4)-x+32,(loop1<<4)+dy,HiddenPageOffs,data.block[data.world[loop1][loop2]-1]);
	  data.bumploop++;
	  if(data.bumploop==3)
	  {
	    data.bumploop=0;
	    data.bumped[0]=0;
	    data.bumped[1]=0;
	    dy=0;
	  }
	}
	else
	  x_put_masked_pbm((loop2<<4)-x+32,(loop1<<4),HiddenPageOffs,data.block[data.world[loop1][loop2]-1]);
      }
  drawcoin(x);
  drawscore(x);
  x_rect_fill(15,0,32,224,HiddenPageOffs,0);
  x_rect_fill(288,0,306,224,HiddenPageOffs,0);
}

//DRAW INFO ON TOP OF SCREEN/////////////////////////////////////////////////
void status()
{
  static char pscore[7]="000000";
  char pscore2[7];
  char slevel[4]="1-1";
  char scoins[3]="00";
  static char stime[4]="   ";
  int loop1,loop2;
  if (curplay==0)
    graphstring(34,1,"MARIO",TEXTCOLOR,HiddenPageOffs);
  else
    graphstring(34,1,"LUIGI",TEXTCOLOR,HiddenPageOffs);
  graphstring(180,1,"WORLD",TEXTCOLOR,HiddenPageOffs);
  graphstring(254,1,"TIME",TEXTCOLOR,HiddenPageOffs);
  if(player[curplay].score[1]!=player[curplay].score[0])
  {
    itoa(player[curplay].score[1],pscore2,10);
    loop2=5;
    for(loop1=strlen(pscore2)-1;loop1>=0;loop1--)
	pscore[loop2--]=pscore2[loop1];
    graphstring(34,9,pscore,BACKGROUND,HiddenPageOffs);
  }
  strcpy(pscore,"000000");
  itoa(player[curplay].score[0],pscore2,10);
  loop2=5;
  for(loop1=strlen(pscore2)-1;loop1>=0;loop1--)
    pscore[loop2--]=pscore2[loop1];
  graphstring(34,9,pscore,TEXTCOLOR,HiddenPageOffs);
  player[curplay].score[1]=player[curplay].score[0];
  if (times[1]!=times[2])
  {
    itoa(times[0],stime,10);
    graphstring(260,9,stime,BACKGROUND,HiddenPageOffs);
    times[2]=times[1];
    times[0]--;
    itoa(times[0],stime,10);
  }
  graphstring(260,9,stime,TEXTCOLOR,HiddenPageOffs);
  slevel[0]=(char)(player[curplay].level/5+1-208);
  slevel[2]=(char)(player[curplay].level%5-208);
  graphstring(188,9,slevel,TEXTCOLOR,HiddenPageOffs);
  if (player[curplay].coins[0]!=player[curplay].coins[1])
  {
    itoa(player[curplay].coins[1],scoins,10);
    if (player[curplay].coins[1]<10)
    {
      scoins[1]=scoins[0];
      scoins[0]='0';
    }
    graphstring(116,9,scoins,BACKGROUND,HiddenPageOffs);
    player[curplay].coins[1]=player[curplay].coins[0];
  }
  itoa(player[curplay].coins[0],scoins,10);
  if (player[curplay].coins[0]<10)
  {
    scoins[1]=scoins[0];
    scoins[0]='0';
  }
  graphchar(108,9,'x',TEXTCOLOR,HiddenPageOffs);
  graphstring(116,9,scoins,TEXTCOLOR,HiddenPageOffs);
  x_line(100,8,101,8,20,HiddenPageOffs);
  x_put_pix(102,8,HiddenPageOffs,0);
  x_line(100,15,101,15,20,HiddenPageOffs);
  x_put_pix(102,15,HiddenPageOffs,0);
  for(loop1=0;loop1<6;loop1++)
  {
    x_line(99,9+loop1,102,9+loop1,20,HiddenPageOffs);
    x_put_pix(103,9+loop1,HiddenPageOffs,0);
  }
}

//CALCULATE AND DRAW MARIO///////////////////////////////////////////////////
void drawmario(int x, int y)
{
  int loop1,loop2;
  for (loop1=0;loop1<=15;loop1++)
    for (loop2=0;loop2<=15;loop2++)
      if (data.mario[abs(data.sprite/2)][loop1][loop2]>=0)
	if (y-15+loop1>=0 && y-15+loop1<224)
	{
	  if (data.direction==0)
	    x_put_pix(x+loop2+32,y-15+loop1,HiddenPageOffs,data.mario[data.sprite/2][loop1][loop2]);
	  else x_put_pix(x+15-loop2+32,y-15+loop1,HiddenPageOffs,data.mario[data.sprite/2][loop1][loop2]);
	}
}

//DRAW CHARACTERS////////////////////////////////////////////////////////////
void graphchar(int xc, int yc, char c,int color, unsigned offset)
{
  int x,y;
  unsigned char data;
  unsigned char far *work;
  unsigned char bitmask=0x80;
  work=charset+(c<<3);
  for(y=0;y<8;y++)
  {
    bitmask=0x80;
    for(x=0;x<8;x++)
    {
      if((*work & bitmask))
	x_put_pix(x+xc,y+yc,offset,color);
      bitmask=(bitmask>>1);
    }
  work++;
  }
}

//DRAW A STRING//////////////////////////////////////////////////////////////
void graphstring(int x,int y,char *string,int color, unsigned offset)
{
  int loop;
  for(loop=0;loop<strlen(string);loop++)
    graphchar(x+loop*8,y,string[loop],color,offset);
}

//DRAW SCREEN SHOWING LEVEL AND NUMBER OF LIVES//////////////////////////////
void levelscreen()
{
  int x=75;
  int y=110;
  int wait[3];
  char print[3];
  data.sprite=0;
  data.direction=0;
  fillscreen(0);
  status();
  drawmario(x,y);
  graphstring(100,82,"WORLD",TEXTCOLOR,HiddenPageOffs);
  graphchar(132,100,'x',TEXTCOLOR,HiddenPageOffs);
  graphchar(156,82,'-',TEXTCOLOR,HiddenPageOffs);
  print[0]=(char)(player[curplay].level/5+1-208);
  graphchar(148,82,print[0],TEXTCOLOR,HiddenPageOffs);
  print[0]=(char)(player[curplay].level%5-208);
  graphchar(164,82,print[0],TEXTCOLOR,HiddenPageOffs);
  itoa(player[curplay].lives,print,10);
  graphstring(148,100,print,TEXTCOLOR,HiddenPageOffs);
  x_page_flip(0,0);
  gettime(&t);
  wait[0]=t.ti_sec;
  wait[1]=wait[0];
  wait[2]=0;
  while(wait[2]!=3)
  {
    gettime(&t);
    wait[0]=t.ti_sec;
    if(wait[0]!=wait[1])
    {
      wait[1]=wait[0];
      wait[2]++;
    }
  }
}

//TITLE SCREEN AND CHOOSING NUMBER OF PLAYERS////////////////////////////////
int startscreen()
{
  int x=40;
  int y=191;
  int choice,screenx,offset,numplayers=1;
  FILE *logoin;
  int count,loop1,loop2;
  char *temp;
  data.sprite=0;
  screenx=0;
  if ((logoin=fopen("LOGO.PCX","rb"))==NULL)
  {
    exitgame();
    printf("LOGO.PCX not found!\n");
    exit(1);
  }
  fseek(logoin,128,SEEK_SET);
  temp=getpcx(logoin,temp,31040);
  count=-111;
  fillscreen(BACKGROUND);
  drawscreen(screenx);
  drawmario(x,y);
  for (loop1=0;loop1<97;loop1++)
  {
    count+=111;
    for (loop2=0;loop2<209;loop2++)
      x_put_pix(loop2+55,loop1+20,HiddenPageOffs,temp[count++]);
  }
  free(temp);
  fclose(logoin);
  graphstring(160,118,"1985 NINTENDO",TEXTCOLOR,HiddenPageOffs);
  graphstring(120,130,"1 PLAYER GAME",TEXTCOLOR,HiddenPageOffs);
  graphstring(120,146,"2 PLAYER GAME",TEXTCOLOR,HiddenPageOffs);
  graphstring(120,162,"TOP - 000000",TEXTCOLOR,HiddenPageOffs);
  x_page_flip(0,0);
  while(!keybuf[KEY_ENTER])
  {
    if (keybuf[KEY_DOWN])
    {
      x_rect_fill(108,129,117,137,VisiblePageOffs,BACKGROUND);
      numplayers=2;
    }
    if (keybuf[KEY_UP])
    {
      x_rect_fill(108,145,117,153,VisiblePageOffs,BACKGROUND);
      numplayers=1;
    }
    if (numplayers==1)
      offset=129;
    else
      offset=145;
    x_line(110,offset,113,offset,21,VisiblePageOffs);
    x_line(109,offset+1,114,offset+1,21,VisiblePageOffs);
    x_line(109,offset+2,114,offset+2,21,VisiblePageOffs);
    x_line(108,offset+3,115,offset+3,21,VisiblePageOffs);
    x_line(108,offset+4,115,offset+4,21,VisiblePageOffs);
    x_line(108,offset+5,115,offset+5,21,VisiblePageOffs);
    x_put_pix(109,offset+6,VisiblePageOffs,21);
    x_put_pix(114,offset+6,VisiblePageOffs,21);
    x_line(110,offset+6,113,offset+6,20,VisiblePageOffs);
    x_line(110,offset+7,113,offset+7,20,VisiblePageOffs);
  }
  return(numplayers);
}

//CHANGE COLORS FOR MARIO OR LUIGI///////////////////////////////////////////
void setcolors()
{
  if (curplay==0)
  {
    setpalette(1,data.marioc[0]);
    setpalette(2,data.marioc[1]);
  }
  else
  {
    setpalette(1,data.luigic[0]);
    setpalette(2,data.luigic[1]);
  }
}

void rotatepal(int *palloopp, colorpal *tempcp)
{
  int palloop=*palloopp;
  colorpal tempc=*tempcp;
  int loop;
  if (palloop>=16 && palloop%4==0)
  {
    if (palloop<=24)
    {
      tempc=data.palette[20];
      data.palette[20]=data.palette[21];
      data.palette[21]=data.palette[22];
      data.palette[22]=data.palette[23];
      data.palette[23]=tempc;
    }
    else
    {
      tempc=data.palette[23];
      data.palette[23]=data.palette[22];
      data.palette[22]=data.palette[21];
      data.palette[21]=data.palette[20];
      data.palette[20]=tempc;
    }
    for(loop=20;loop<=23;loop++)
      setpalette(loop,data.palette[loop]);
    if (palloop==36)
      palloop=0;
  }
  palloop++;
  *tempcp=tempc;
  *palloopp=palloop;
}

void check_keybuf(int *movep, int *finishp, int *upp, int *downp, int *jumpp)
{
  int move=*movep;
  int finish=*finishp;
  int up=*upp;
  int down=*downp;
  int jumploop=*jumpp;
  if (keybuf[KEY_RIGHT] && !keybuf[KEY_LEFT])
  {
   if (keybuf[KEY_CTRL])
   {
     if (move<16)
       move++;
   }
   else
   {
     if (move>8)
       move--;
     if (move<8)
       move++;
   }
   if (landed) //(data.world[(marioy+1)/16][(mariox+player[curplay].screenx+8)/16]>26)
     data.direction=0;
   }
   if (keybuf[KEY_LEFT] && !keybuf[KEY_RIGHT])
   {
     if (keybuf[KEY_CTRL])
     {
       if (move>-16)
	 move--;
     }
     else
     {
       if (move<-8)
	 move++;
       if (move>-8)
	 move--;
     }
     if (landed) //(data.world[(marioy+1)/16][(mariox+player[curplay].screenx+8)/16]>26)
       data.direction=1;
     }
   if (keybuf[KEY_ALT] && !down)
   {
     up=-17;
     data.sprite=12;
     jumploop++;
     if (jumploop>10)
     {
       down=1;
       jumploop=0;
     }
   }
   if (keybuf[KEY_P])
   {
     graphstring(140,100,"Pause",TEXTCOLOR,VisiblePageOffs);
     clear_key_buffer();
     while(!keybuf[KEY_P]);
     clear_key_buffer();
     graphstring(140,100,"Pause",BACKGROUND,VisiblePageOffs);
   }
   if(keybuf[KEY_Q])
   {
     graphstring(130,100,"Quit(Y/N)",TEXTCOLOR,VisiblePageOffs);
     clear_key_buffer();
     while(!keybuf[KEY_Y] && !keybuf[KEY_N]);
     if (keybuf[KEY_Y] && !keybuf[KEY_N])
       finish=3;
     clear_key_buffer();
     graphstring(130,100,"Quit(Y/N)",BACKGROUND,VisiblePageOffs);
   }
   if ((!keybuf[KEY_RIGHT] && !keybuf[KEY_LEFT]) || (keybuf[KEY_RIGHT] && keybuf[KEY_LEFT]))
   {
    if (move>0)
      move--;
    if (move<0)
      move++;
   }
  *movep=move;
  *finishp=finish;
  *upp=up;
  *downp=down;
  *jumpp=jumploop;
}

void animate_mario(int move)
{
  if (landed)
  {
    if (data.sprite==10 && ((move>0 && data.direction==0) || (move<0 && data.direction==1)))
      data.sprite=1;
    if (move==0)
      data.sprite=0;
    if ((move>0 && data.direction==0) || (move<0 && data.direction==1))
    {
      if(abs(move)<16)
	data.sprite++;
      else
	data.sprite+=2;
      if (data.sprite>=10)
	data.sprite=2;
    }
    if ((move>0 && data.direction==1) || (move<0 && data.direction==0))
      data.sprite=10;
  }
}

void move_mario(int *movep)
{
  int move=*movep;
  int loop1,loop2;
  if (marioy>16)
  {
    if (move>0)
      for (loop1=0;loop1<16;loop1++)
	for (loop2=0;loop2<=move;loop2++)
	  if (data.world[(marioy-loop1)/16][(mariox+player[curplay].screenx+13+loop2/4)/16]>26)
	    move=loop2;
    if (move<0)
      for (loop1=0;loop1<16;loop1++)
	for (loop2=0;loop2>=move;loop2--)
	  if (data.world[(marioy-loop1)/16][(mariox+player[curplay].screenx+2+loop2/4)/16]>26 || (mariox+loop1/2)<=0)
	    move=loop2;
  }
  if(mariox>100 && move>0)
    player[curplay].screenx+=move/4;
  else
    if (mariox+move/4>0)
      mariox+=move/4;
  *movep=move;
}

void addcoin(int x, int y)
{
  cointype *temp,*c;
  c=bcoin;
  while ((c->next)!=NULL)
    c=c->next;
  temp=(cointype *)malloc(sizeof(cointype));
  temp->next=NULL;
  temp->coinx=x;
  temp->coiny=y;
  temp->coinloop=-10;
  temp->dy=0;
  c->next=temp;
}

void removecoin()
{
  cointype *temp;
  cointype *c=bcoin;
  temp=c->next;
  c->next=c->next->next;
  addscore(temp->coinx,temp->coiny,"200");
  free(temp);
}

void drawcoin(int x)
{
  cointype *c=bcoin;
  int loop,num=0;
  while ((c->next)!=NULL)
  {
    num++;
    c=c->next;
  }
  c=bcoin;
  for (loop=0;loop<num;loop++)
  {
    c=c->next;
    if (((c->coinx)*16+32-x)>16)
      x_put_masked_pbm((c->coinx)*16+32-x,(c->coiny)*16+c->dy,HiddenPageOffs,data.coin[abs((c->coinloop)%3)]);
    c->coinloop++;
    c->dy+=c->coinloop;
    if (c->coinloop>=10)
       removecoin();
  }
}

void addscore(int x, int y, char *pscore)
{
  scoretype *temp,*s;
  s=bscore;
  while ((s->next)!=NULL)
    s=s->next;
  temp=(scoretype *)malloc(sizeof(scoretype));
  temp->next=NULL;
  temp->scorex=x;
  temp->scorey=y;
  temp->scoreloop=0;
  strcpy(temp->num,pscore);
  s->next=temp;
}

void removescore()
{
  scoretype *temp;
  scoretype *s=bscore;
  temp=s->next;
  s->next=s->next->next;
  free(temp);
}

void drawscore(int x)
{
  scoretype *s=bscore;
  while ((s->next)!=NULL)
  {
    s=s->next;
    if (((s->scorex)*16+32-x)>16)
      graphstring((s->scorex)*16+32-x,(s->scorey)*16+(s->scoreloop),s->num,TEXTCOLOR,HiddenPageOffs);
    s->scoreloop--;
    if (s->scoreloop<=-20)
       removescore();
  }
}

void exitgame()
{
  int loop;
  for (loop=0;loop<52;loop++)
    farfree(data.block[loop]);
  for (loop=0;loop<3;loop++)
    farfree(data.coin[loop]);
  free(bcoin);
  free(bscore);
  uninstall_new_key_handler();
  x_remove_vsync_handler();
  x_text_mode();
}

void check_jump(int *upp, int *downp, int *jumploopp)
{
  int loop1,loop2;
  int up=*upp;
  int down=*downp;
  int jumploop=*jumploopp;
  loop1=3;
  landed=0;
  while (loop1<13 && landed==0)
  {
    if (data.world[(marioy+1)/16][(mariox+player[curplay].screenx+loop1)/16]>26)
      landed=1;
    else
      landed=0;
    loop1++;
  }
  if (!landed || marioy<16)
    if (up<17)
      up+=1;
  if (up>0)	//CHECK IF MARIO LANDED//
  {
    down=1;
    loop1=marioy;
    if (marioy<207)
    {
      while(loop1++<marioy+up/4)
      {
	for (loop2=3;loop2<14;loop2+=9)
	  if (data.world[loop1/16][(mariox+player[curplay].screenx+loop2)/16]>26)
	  {
	    marioy=loop1-1;
	    loop1=marioy+up/4;
	    loop2=13;
	    up=0;
	    down=0;
	    data.sprite=0;
	  }
      }
    }
    marioy+=up/4;
  }
  if (up<0)	//CHECK IF MARIO HIT A BLOCK/
  {
    if (!keybuf[KEY_ALT])
    {
      down=1;
      jumploop=0;
    }
    loop1=marioy;
    marioy+=up/4;
    if (marioy>24)
    {
      while(loop1-->=marioy+up/4)
      {
	if(data.world[loop1/16-1][(mariox+player[curplay].screenx+8)/16]>26 ||data.world[loop1/16-1][(mariox+player[curplay].screenx+8)/16]==18)
	{
	  marioy=loop1;
	  loop1=marioy+up/4-1;
	  up=-up;
	  down=1;
	  jumploop=0;
	  if (data.bumped[0]==0 && data.bumped[1]==0)
	  {
	    data.bumped[0]=(mariox+player[curplay].screenx+8)/16;
	    data.bumped[1]=marioy/16-1;
	    data.bumploop=-3;
	  }
	}
	if (data.world[loop1/16-1][(mariox+player[curplay].screenx+12)/16]>26 && data.world[loop1/16-1][(mariox+player[curplay].screenx+8)/16]<26)
	  mariox-=1;
	if (data.world[loop1/16-1][(mariox+player[curplay].screenx+3)/16]>26 && data.world[loop1/16-1][(mariox+player[curplay].screenx+8)/16]<26)
	  mariox+=1;
      }
    }
  }
  *upp=up;
  *downp=down;
  *jumploopp=jumploop;
}

void check_blocks()
{
  int bumpedblock=0;
  if (data.world[(marioy-8)/16][(mariox+player[curplay].screenx+8)/16]==10)
  {
    data.world[(marioy-8)/16][(mariox+player[curplay].screenx+8)/16]=0;
    player[curplay].score[0]+=200;
    player[curplay].coins[0]++;
  }
  if (data.bumploop==-3)
  {
    bumpedblock=data.world[marioy/16-1][(mariox+player[curplay].screenx+8)/16];
    switch (bumpedblock)
    {
      case 18:
      case 28:
	data.world[marioy/16-1][(mariox+player[curplay].screenx+8)/16]=27;
	player[curplay].score[0]+=200;
	player[curplay].coins[0]++;
	addcoin(data.bumped[0],data.bumped[1]-1);
      case 29:
	if (data.world[data.bumped[1]-1][data.bumped[0]]==10)
	{
	  data.world[data.bumped[1]-1][data.bumped[0]]=0;
	  player[curplay].score[0]+=200;
	  player[curplay].coins[0]++;
	  addcoin(data.bumped[0],data.bumped[1]-1);
	}
	break;
      default:
	data.bumped[0]=data.bumped[1]=0;
	data.bumploop=0;
	break;
    }
  }
  if (player[curplay].coins[0]==100)
  {
    player[curplay].lives++;
    player[curplay].coins[0]=0;
  }
}

void check_finish(int *finishp)
//CHECK FOR END OF CURRENT PLAYER LOOP///////////////////////////////////////
{
  int finish=*finishp;
  if (marioy>=250)
    finish=1;
  if ((mariox+player[curplay].screenx+16)/16==data.maxlength-13)
    finish=2;
  if (times[0]==0)
    finish=1;
  *finishp=finish;
}
//END////////////////////////////////////////////////////////////////////////
