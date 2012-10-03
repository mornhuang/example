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
    graphstring((s->scorex)*16+32-x,(s->scorey)*16+(s->scoreloop),s->num,TEXTCOLOR,HiddenPageOffs);
    s->scoreloop--;
    if (s->scoreloop<=-20)
       removescore();
  }
}
