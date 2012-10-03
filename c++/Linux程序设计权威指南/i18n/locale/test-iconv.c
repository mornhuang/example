// File: test-iconv.c

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iconv.h>
#include <errno.h>

// 用 iconv() 对单个字符进行编码转换：输入 charset -> UTF-8
static int try ();

// UTF-8 -> Unicode
static unsigned int utf8_decode ();


int
main (int argc, char **argv)
{
  int i;
  const char *charset;
  unsigned const char *str;
  unsigned char buf[4];
  unsigned char out[6];
  size_t inlength;
  size_t result;
  iconv_t cd;

  if (argc != 3)
    {
      fprintf (stderr, "Usage: %s charset string\n", argv[0]);
      exit (1);
    }
  charset = argv[1];
  str = argv[2];

  // 打开 charset -> UTF-8 的转换描述符
  cd = iconv_open ("UTF-8", charset);
  if (cd == (iconv_t) (-1))
    {
      perror ("iconv_open");
      exit (1);
    }

  inlength = strlen (str);

  // 对输入字符串进行逐字转换
  for (i = 0; i < inlength; i++)
    {
      buf[0] = str[i];
      result = try (cd, buf, 1, out);
      if (result < 0)
	{
	}
      else if (result > 0)
	{
	  // 单字节编码
	  printf ("%c\t", buf[0]);
	  printf ("0x%02X\t0x%04X\n", buf[0], utf8_decode (out, result));
	}
      else
	{
	  buf[1] = str[++i];
	  result = try (cd, buf, 2, out);
	  if (result < 0)
	    {
	    }
	  else if (result > 0)
	    {
	      // 双字节编码
	      printf ("%c%c\t", buf[0], buf[1]);
	      printf ("0x%02X%02X\t0x%04X\n",
		      buf[0], buf[1], utf8_decode (out, result));
	    }
	  else
	    {
	      buf[2] = str[++i];
	      result = try (cd, buf, 3, out);
	      if (result < 0)
		{
		}
	      else if (result > 0)
		{
		  // 三字节编码
	          printf ("%c%c%c\t", buf[0], buf[1], buf[2]);
		  printf ("0x%02X%02X%02X\t0x%04X\n",
			  buf[0], buf[1], buf[2], utf8_decode (out, result));
		}
	      else
		{
		  buf[3] = str[++i];
		  result = try (cd, buf, 4, out);
		  if (result < 0)
		    {
		    }
		  else if (result > 0)
		    {
		      // 四字节编码
	              printf ("%c%c%c%c\t", buf[0], buf[1], buf[2], buf[3]);
		      printf ("0x%02X%02X%02X%02X\t0x%04X\n",
			      buf[0], buf[1], buf[2], buf[3],
			      utf8_decode (out, result));
		    }
		  else
		    {
		      // 转换失败
		      fprintf (stderr, "0x%02X%02X%02X%02X",
			       buf[0], buf[1], buf[2], buf[3]);
		      fprintf (stderr, ": incomplete byte sequence\n");
		      exit (1);
		    }
		}
	    }
	}
    }

  // 关闭转换描述符
  if (iconv_close (cd) < 0)
    {
      perror ("iconv_close");
      exit (1);
    }

  return (0);
}


// 用 iconv() 对单个字符进行编码转换：输入 charset -> UTF-8
static int
try (iconv_t cd, unsigned char buf[], unsigned int buflen, unsigned char *out)
{
  int i;
  const char *inbuf = (const char *) buf;
  size_t inbytesleft = buflen;
  char *outbuf = (char *) out;
  size_t outbytesleft = 6;
  size_t result = iconv (cd,
			 (char **) &inbuf, &inbytesleft,
			 &outbuf, &outbytesleft);
  if (result == (size_t) (-1))
    {
      if (errno == EILSEQ)
	{
	  return -1;
	}
      else if (errno == EINVAL)
	{
	  return 0;
	}
      else
	{
	  int saved_errno = errno;
	  fprintf (stderr, "0x");
	  for (i = 0; i < buflen; i++)
	    fprintf (stderr, "%02X", buf[i]);
	  fprintf (stderr, ": iconv error: ");
	  errno = saved_errno;
	  perror ("");
	  exit (1);
	}
    }
  else
    {
      if (inbytesleft != 0)
	{
	  fprintf (stderr, "0x");
	  for (i = 0; i < buflen; i++)
	    fprintf (stderr, "%02X", buf[i]);
	  fprintf (stderr, ": inbytes = %ld, outbytes = %ld\n",
		   (long) (buflen - inbytesleft), (long) (6 - outbytesleft));
	  exit (1);
	}
      return 6 - outbytesleft;
    }
}


// UTF-8 -> Unicode
static unsigned int
utf8_decode (const unsigned char *out, unsigned int outlen)
{
  return (outlen == 1 ? out[0] :
	  outlen == 2 ? ((out[0] & 0x1f) << 6) + (out[1] & 0x3f) :
	  outlen == 3 ? ((out[0] & 0x0f) << 12) + ((out[1] & 0x3f) << 6) +
			 (out[2] & 0x3f) :
	  outlen == 4 ? ((out[0] & 0x07) << 18) + ((out[1] & 0x3f) << 12) +
			((out[2] & 0x3f) << 6) + (out[3] & 0x3f) :
	  outlen == 5 ? ((out[0] & 0x03) << 24) + ((out[1] & 0x3f) << 18) +
			((out[2] & 0x3f) << 12) + ((out[3] & 0x3f) << 6) +
			 (out[4] & 0x3f) :
	  outlen == 6 ? ((out[0] & 0x01) << 30) + ((out[1] & 0x3f) << 24) +
			((out[2] & 0x3f) << 18) + ((out[3] & 0x3f) << 12) +
			((out[4] & 0x3f) << 6) + (out[5] & 0x3f) :
	  0xfffd);
}
