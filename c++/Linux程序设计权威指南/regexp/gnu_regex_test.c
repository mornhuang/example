#include <stdio.h>
#include <regex.h>

int
gnu_regex (regex_pattern, line1, line2)
     char *regex_pattern;
     char *line1;
     char *line2;
{
  struct re_pattern_buffer pattern_buffer;
  struct re_registers regs;
  int n, len1, len2;
  const char *id;

  len1 = strlen (line1);
  len2 = strlen (line2);

  /* 设定 正则表达式 的语法定义 */
  re_syntax_options =
    RE_SYNTAX_EGREP | RE_INTERVALS | RE_BACKSLASH_ESCAPE_IN_LISTS;
  /* 将 正则表达式 的 pattern buffer 初始化 */
  pattern_buffer.allocated = 0;
  pattern_buffer.buffer = 0;
  pattern_buffer.fastmap = 0;
  pattern_buffer.translate = 0;
  /* 编译 正则表达式 */
  id = re_compile_pattern (regex_pattern, strlen (regex_pattern),
			   &pattern_buffer);
  /* 侦测是否有错误的发生 */
  if (id != NULL)
    {
      printf (" error on compiling regex1. code = %s\n", id);
      exit (1);
    }
  /* 在字符串 line1 中进行比较，并列出其返回值 */
  n = re_match (&pattern_buffer, line1, len1, 0, &regs);
  printf (" re_match return = %d\n", n);
  /* 在字符串 line1 与字符串 line2 中进行比较，并列出其返回值 */
  n =
    re_match_2 (&pattern_buffer, line1, len1, line2, len2, 0, &regs,
		len1 + len2);
  printf (" re_match_2 return = %d\n", n);
  /* 在字符串 line1 中进行寻找，并列出其返回值 */
  n = re_search (&pattern_buffer, line1, len1, 0, len1, &regs);
  printf (" re_search return = %d\n", n);
  if (n >= 0)
    printf (" re_search string = %s\n", line1);
  /* 在字符串 line1 与字符串 line2 中进行寻找，并列出其返回值 */
  n =
    re_search_2 (&pattern_buffer, line1, len1, line2, len2, 0, len1 + len2,
		 &regs, len1 + len2);
  if (n >= 0)
    {
      printf (" re_search_2 return = %d\n", n);
      if (n < len1)
	printf (" re_search_2 string = %s\n", line1);
      else
	printf (" re_search_2 string = %s\n", line2);
      return 1;
    }
  printf (" re_search_2 return = %d\n", n);
  return n;
}

main (argc, argv)
     int argc;
     char **argv;
{
  FILE *fp;
  char line[2][1024];
  int i, n, k, j;

  /* 检查参数的个数 */
  if (argc != 3)
    {
      printf ("Usage: %s pattern file\n", argv[0]);
      exit (1);
    }
  /* 打开测试的文件 */
  fp = fopen (argv[2], "r");
  if (fp == NULL)
    {
      fprintf (stderr, "Can't open %s.\n", argv[2]);
      exit (1);
    }
  /* 读取测试文件中的字符串并进行 GNU 接口 Regex 程序的测试 */
  j = 1;
  fgets (line[0], 1024, fp);
  i = strlen (line[0]) - 1;
  if (line[0][i] == '\n')
    {
      line[0][i] = '\0';
    }
  while (1)
    {
      n = j & 1;
      k = n ^ 1;
      if (fgets (line[n], 1024, fp) == NULL)
	{
	  break;
	}
      j++;
      i = strlen (line[n]) - 1;
      if (line[n][i] == '\n')
	{
	  line[n][i] = '\0';
	}
      gnu_regex (argv[1], line[k], line[n]);
    }
  line[n][0] = '\0';
  gnu_regex (argv[1], line[k], line[n]);
  /* 关闭测试的文件 */
  fclose (fp);
}
