#include <stdio.h>
#include <regex.h>

int
test_bsd (regex, text)
     char *regex;
     char *text;
{
  int n;
  const char *id;

  re_syntax_options = RE_SYNTAX_GREP;
  /* 编译正则表达式 */
  id = (char*) re_comp (regex);
  /* 侦测是否有错误的发生 */
  if (id != NULL)
    {
      printf (" error on compiling regex. code = %s\n", id);
      exit (1);
    }

  /* 在字符串 text 中进行寻找，并列出其返回值 */
  n = re_exec (text);
  if (n == 1)
    {
      printf (" re_exec match string = %s\n", text);
    }
  return n;
}

main (argc, argv)
     int argc;
     char **argv;
{
  FILE *fp;
  char line[1024];

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

  /* 读取测试文件中的字符串并进行 BSD 兼容接口 Regex 程序的测试 */
  while (fgets (line, 1024, fp) != NULL)
    {
      test_bsd (argv[1], line);
    }
  /* 关闭测试的文件 */
  fclose (fp);
}
