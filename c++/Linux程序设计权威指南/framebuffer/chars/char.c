
#include "font_8x16.c"
#include "font_gb16.c"

#define gb16_index(l,r) ((((l-0xa1)&0x7f)*94+((r-0xa1)&0x7f)) << 5)
#define en8_index(c)    (c * 16)

void other_ascii_char(int x0, int y0, int color, unsigned char c)
{
	unsigned char *p= fontdata_8x16 + en8_index(c);
	int xi, yi, x, y;

	for (yi = 0, y = y0; yi < 16; yi++, y++)
	for (xi = 0, x = x0; xi < 8; xi++, x++){
		if (((p[yi + xi/8] >> (7-xi%8)) & 0x01) == 0) continue;
		other_set_pixel(x, y, color);
	}
}


void other_ascii_string(int x, int y, int color, unsigned char *str, int len)
{
	int i;
	int x0 = x;
	for(i=0; i<len; i++){
		other_ascii_char(x0, y, color, str[i]);
		x0 += 8;
	}
}

void other_chinese_char(int x0, int y0,
        unsigned long color, unsigned char c1, unsigned char c2)
{
	unsigned char *p = font_gb16 + gb16_index(c1, c2);
	int xi, yi, x, y;

	for (yi = 0, y = y0; yi < 16; yi++, y++)
	for (xi = 0, x = x0; xi < 16; xi++, x++) {
		if (((p[yi*16/8 + xi/8] >> (7-xi%8)) & 0x01) == 0) continue;
		other_set_pixel(x, y, color);
	}
}

void other_chinese_string(int x, int y,
	unsigned long color, unsigned char *text, int len)
{
	int i;
	unsigned char line[256];
	strncpy(line, text, len*2);

	for (i=0;i<len;i++){
		other_chinese_char(x, y, color, line[2*i], line[2*i+1]);
		x += 16;
	}
}

static int next_string(unsigned char *ch,int i,int length,int *gb)
{
        *gb = -1;
        for (; i < length;) {
                if ( ch[i] >= 0x80 && i< length-1) {
                        if ( ch[i] >= 0xa1 && ch[i] <= 0xf7 &&
                                ch[i+1] >= 0xa1 && ch[i+1] <= 0xfe ) {
                                if(*gb==-1) *gb=1;
                                if(*gb==0 || *gb==2) return i;
                                i += 2;
                        } else {
                                if(*gb==-1) *gb=2;
                                if(*gb==1 || *gb==0) return i;
                                i += 2;
                        }
                } else {
                        if(*gb==-1) *gb=0;
                        if (*gb) return i;
                        i++;
                }
        }
        return i;
}


void other_mixed_string(int x, int y, unsigned long color,
	unsigned char *text, int length)
{
        int i, j;
        int hz = 0;
        unsigned char *ch = text;
        int cur_pos = 0;

        for(i=0,j=0; i<length;){
                i = next_string(ch,i,length,&hz);
                if(hz){
                        other_chinese_string(x + cur_pos, y, color, &ch[j], (i-j)/2);
                } else{
                        other_ascii_string(x + cur_pos, y, color, &ch[j], i-j);
                }
                cur_pos += (i-j)*8;
                j = i;
        }
}
