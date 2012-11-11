
# include <iostream.h>
# include <string.h>
typedef int (* compare)(void *, void *);

struct name_type
{
  char *name;
  int id_number;
};

typedef name_type *rec_type;
name_type my_records[]={"Apple",       5,
                        "Orange",      7,
						"Pear",        3,
						"Grapefruit",  4,
						"Lemon",       1,
						"Plum",        0,
						"Lim",         2};

void main()
{
	extern int cmp1(void *p,void *q);  //按名字比较记录
	extern int cmp2(void *p,void *q);  //按标识号比较记录

	extern void sort(char *base,unsigned int n, int elemsize,
		compare cmp);

	extern void print(rec_type r,int n);

	sort((char *)my_records,7,sizeof(name_type),cmp1);
	print(my_records,7);
    sort((char *)my_records,7,sizeof(name_type),cmp2);
    print(my_records,7);
}

void sort(char *base,unsigned int n,int elemsize,compare cmp)
//冒泡分类法
{
    for(int i=0;i<n-1; i++)
	{
	   for(int j=n-1;i<j;j--)
	   {
	     char *bj=base+j*elemsize;  //b[j]
		 char *bj1=bj-elemsize;     //b[j-1]
		 if((*cmp)(bj,bj1)<0)       //指针是如此比较大小
		 {
		    for(int k=0;k<elemsize;k++)//逐个替换，小的在前
			{
			   char temp=bj[k];
			   bj[k]=bj1[k];
			   bj1[k]=temp;
			}
		 }
	   }
	}
}

void print(rec_type r,int n)
{
   cout << "\n\n";
   for(int i=0; i<n; i++)
	   cout << r[i].name << "\t\t" << r[i].id_number << "\n";
}

int cmp1(void *p,void *q)
{
   return strcmp(rec_type(p)->name,rec_type(q)->name);
}

int cmp2(void *p,void *q)
{
   return rec_type(p)->id_number-rec_type(q)->id_number;
}
 