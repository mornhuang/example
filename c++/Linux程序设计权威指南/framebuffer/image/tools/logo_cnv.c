#include <stdio.h>
#include <stdlib.h>

#define HT (0)
#define HT_MAX (224)
#define XSIZE  (257)
#define YSIZE  (303)
#define PIXELS (XSIZE*YSIZE)
#define HEADER (15)
#define OFFSET (0x20)

int main(int argc, char *argv[])
{
	unsigned long hist[PIXELS]={},cl;
	unsigned char rdata[PIXELS+HEADER],gdata[PIXELS+HEADER],bdata[PIXELS+HEADER],idata[PIXELS+HEADER];
	FILE*	fp;
	int	i,j,ht=HT;
	char    filered[80], filegrn[80], fileblu[80], filegrh[80];
	if(argc < 3) {
		printf("Usage: ./logo_cnv input_image output_image\n");
		exit(1);
	}
	sprintf(filered, "%s.red", argv[1]);
	sprintf(filegrn, "%s.grn", argv[1]);
	sprintf(fileblu, "%s.blu", argv[1]);
	if((fp=fopen(filered,"r"))==NULL){
		fprintf(stderr,"red open error\n");
		exit(-1);
	}
	fread(rdata,PIXELS+HEADER,1,fp);
	fclose(fp);
	if((fp=fopen(filegrn,"r"))==NULL){
		fprintf(stderr,"green open error\n");
		exit(-1);
	}
	fread(gdata,PIXELS+HEADER,1,fp);
	fclose(fp);
	if((fp=fopen(fileblu,"r"))==NULL){
		fprintf(stderr,"blue open error\n");
		exit(-1);
	}
	fread(bdata,PIXELS+HEADER,1,fp);
	fclose(fp);
	for(i=0;i<PIXELS;i++){
		cl=(rdata[i+HEADER]<<16)+(gdata[i+HEADER]<<8)+(bdata[i+HEADER]);
		for(j=0;j<ht;j++){
			if(cl==hist[j]) break;
		}
		if(j==ht){
			hist[ht]=cl;
			ht++;
		}
		idata[i]=j;
	}
	printf("index size[%d]\n",ht);
	printf("index save\n");
	/* color index->file */
	sprintf(filered, "%s.red", argv[2]);
	sprintf(filegrn, "%s.grn", argv[2]);
	sprintf(fileblu, "%s.blu", argv[2]);
	sprintf(filegrh, "%s.grh", argv[2]);
	if((fp=fopen(filered,"w"))==NULL){
		fprintf(stderr,"red index open error\n");
		exit(-1);
	}
	for(j=0;j<ht;j++){
		cl=(hist[j])>>16;
		cl&=0x0ff;
		fprintf(fp," 0x%02x,",cl);
		if(((j+1)%8)==0) fprintf(fp,"\n");
	}
	fprintf(fp,"\n");
	fclose(fp);
	if((fp=fopen(filegrn,"w"))==NULL){
		fprintf(stderr,"green index open error\n");
		exit(-1);
	}
	for(j=0;j<ht;j++){
		cl=(hist[j])>>8;
		cl&=0x0ff;
		fprintf(fp," 0x%02x,",cl);
		if(((j+1)%8)==0) fprintf(fp,"\n");
	}
	fprintf(fp,"\n");
	fclose(fp);
	if((fp=fopen(fileblu,"w"))==NULL){
		fprintf(stderr,"blue index open error\n");
		exit(-1);
	}
	for(j=0;j<ht;j++){
		cl=hist[j];
		cl&=0x0ff;
		fprintf(fp," 0x%02x,",cl);
		if(((j+1)%8)==0) fprintf(fp,"\n");
	}
	fprintf(fp,"\n");
	fclose(fp);
	printf("image save\n");
	/*big logo save*/
	if((fp=fopen(filegrh,"w"))==NULL){
		fprintf(stderr,"index image open error\n");
		exit(-1);
	}
	for(i=0;i<YSIZE;i++){
	     for(j=0;j<XSIZE;j++){
		  fprintf(fp," 0x%02x,",(OFFSET+(idata[j+i*XSIZE])));
		  if(((j+1)%8)==0) fprintf(fp,"\n");
	     }
	}
	fprintf(fp,"\n");
	fclose(fp);


}



