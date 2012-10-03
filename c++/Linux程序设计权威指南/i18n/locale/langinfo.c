// File: langinfo.c

// 定义获取每个 locale 信息使用的索引号对应的宏
#include <langinfo.h>

main ()
{
  unsigned char *s;
  int i;

  // 设置 locale
  setlocale (LC_ALL, "");

  // 打印当前 locale 中上午和下午的表示
  printf ("Morning: %s\n", nl_langinfo (AM_STR));
  printf ("Afternoon: %s\n", nl_langinfo (PM_STR));

  // 打印当前 locale 中每星期七天的表示
  for (i = 0; i < 7; i++)
    {
      printf ("No.%d day of the week: %s\n", i + 1, nl_langinfo (DAY_1 + i));
    }
}
