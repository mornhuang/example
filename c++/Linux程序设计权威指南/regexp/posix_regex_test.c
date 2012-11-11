#include <stdio.h>
#include <regex.h>

/* pattern buffer 的初始化程序 */
void
init_pattern_buffer (pattern_buffer)
     regex_t *pattern_buffer;
{
  pattern_buffer->buffer = NULL;
  pattern_buffer->allocated = 0;
  pattern_buffer->used = 0;
  pattern_buffer->fastmap = NULL;
  pattern_buffer->fastmap_accurate = 0;
  pattern_buffer->translate = NULL;
  pattern_buffer->can_be_null = 0;
  pattern_buffer->re_nsub = 0;
  pattern_buffer->no_sub = 0;
  pattern_buffer->not_bol = 0;
  pattern_buffer->not_eol = 0;
}

int
test_posix (pattern_buffer, regex, text)
     regex_t *pattern_buffer;
     char *regex;
     char *text;
{
  int cflags, eflag;
  int n;
  int id;
  char buf[256];

  /* 进行 正则表达式 pattern buffer 的初始化 */
  init_pattern_buffer (pattern_buffer);

  /* 设定 正则表达式 的语法定义 */
  cflags = REG_NEWLINE | REG_EXTENDED;
  /* 编译 正则表达式 */
  id = regcomp (pattern_buffer, regex, cflags);
  /* 侦测是否有错误的发生 */
  if (id != 0)
    {
      printf (" error on compiling regex. code = %d\n", id);
      regerror (id, pattern_buffer, buf, sizeof (buf));
      printf (" error : %s\n", buf);
      exit (1);
    }
  /* 不设定执行进行寻找的特别功能 */
  eflag = 0;
  /* 在字符串 text 中进行寻找，并列出其返回值 */
  n = regexec (pattern_buffer, text, 0, 0, eflag);
  if (n == 0)
    {
      printf (" regexec match string = %s\n", text);
    }
  return n;
}

main (argc, argv)
     int argc;
     char **argv;
{
  FILE *fp;
  char line[1024];
  regex_t pattern_buffer;

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

  /* 读取测试文件中的字符串并进行 POSIX 接口 Regex 程序的测试 */
  while (fgets (line, 1024, fp) != NULL)
    {
      test_posix (&pattern_buffer, argv[1], line);
    }

  /* 释放正则表达式 pattern buffer */
  regfree (&pattern_buffer);
  /* 关闭测试的文件 */
  fclose (fp);
}
