%{	
#include <stdlib.h>
int num_chars = 0, num_lines = 0;
%}

%%
\n		++num_chars; ++num_lines; /* "\n" 匹配换行符，一定要顶行 */
.		++num_chars; /* "."匹配除换行符以外的任意字符 */

%%
main() /* 主函数 */
	{
	yylex();
	printf("本文件的行数为：%d，字符数为：%d\n", num_lines, num_chars);
	}

int yywrap() /* 文件结束处理函数，yylex在读到文件结束标记EOF时，
				   调用该函数，用户必须提供该函数，否则在编译连接时
				   会出错 */
	{
	return 1;  /* 返回1表示文件扫描结束，不必再扫描别的文件 */
	}

