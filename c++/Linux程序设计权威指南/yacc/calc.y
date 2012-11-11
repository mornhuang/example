%{
#define YYSTYPE double
#include <stdio.h>
#include <math.h>
#include <ctype.h>
%}

/* 定义部分 */
%token NUM
%left '-' '+'
%left '*' '/'
%right '^'       /* 指数运算   */

/* 规则部分 */

%%
input:		  /* 空串 */
       		| input line
     		;
     
line:		  '\n'
			| exp '\n'  { printf ("\t%.10g\n", $1); }
			;
     
exp:		  NUM                { $$ = $1;         }
			| exp '+' exp        { $$ = $1 + $3;    }
     		| exp '-' exp        { $$ = $1 - $3;    }
       		| exp '*' exp        { $$ = $1 * $3;    }
 			| exp '/' exp        { $$ = $1 / $3;    }
			| exp '^' exp        { $$ = pow ($1, $3); }
			| '(' exp ')'        { $$ = $2;         }
     		;
%% 
/* 用户代码部分同本章例2.1一样，在此省略*/

main ()	/* 主函数 */
	{
     yyparse ();
	}

yyerror (s)  /* 语法分析函数yyparse()出错时调用该函数 */
 	char *s;
	{
   	printf ("%s\n", s);
	}
/* yyparse()在对输入文件进行词法分析时，通过调用词法分析函数yylex()获
   得当前词汇的编码，在此我们提供一个手工编写的词法分析函数，该函数在返回
   词汇的编码的同时，还处理词汇的语义值yylval，该语义值将和词汇码一起
   移进分析栈 */
 
int yylex ()
	{
	int c;
 
	/* 跳过白字符 */
	while ((c = getchar ()) == ' ' || c == '\t')
	;
	/* 处理数字   */
	if (c == '.' || isdigit (c))
		{
		ungetc (c, stdin);
		scanf ("%lf", &yylval); /* 全局变量yylval记录当前词形的
					语义值，它在y.tab.c中定义，其数据类型由定义部分
					所定义的宏YYSTYPE给出，即双精度数double */
		return NUM; /* 返回数字对应的词汇编码NUM，YACC将定义部分的
				"%token NUM"翻译为C的宏定义"#define NUM XXX"
				输出到y.tab.c中，因此，在此可直接使用宏名NUM */
		}
       /* 文件结束时，返回0 */
	if (c == EOF)
         return 0;
       /* 返回字符的ASCII码，对语法规则部以单个字符形式出现的终结符（加' ' ）
  		 在定义部分不需要用%token语句声明，它的词汇编码是其ASCII码 */
	return c;
	}
