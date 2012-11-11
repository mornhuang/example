
	/* file sample.c */
	#include <locale.h>
	#include <libintl.h>

	#define _(String)  gettext(String)
	#define N_(String)  gettext(String)
	#define __(String) (String)

	int main(int argc, char *argv[])
	{

		//由环境变量决定locale
		setlocale(LC_ALL, "");

		//设置message的位置和文件名
		bindtextdomain("sample", "./locale");
		textdomain("sample");

		printf(_("String to be translated.\n"));
	}


