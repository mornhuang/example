
	#include <stdio.h>
	#include <locale.h>

	int main(int argc, char *argv[])
	{
		struct lconv *lc;

		//设置 locale
		setlocale(LC_ALL, "");

		//获得 locale 信息
		lc = localeconv();
		printf("数值信息:\n");
		printf("decimal_point:%s\n", lc->decimal_point);
		printf("thousands_sep:%s\n", lc->thousands_sep);
		printf("grouping:%s\n\n", lc->grouping);
		printf("货币信息:\n");
		printf("int_curr_symbol:%s\n", lc->int_curr_symbol);
		printf("currency_symbol:%s\n", lc->currency_symbol);
		printf("mon_decimal_point:%s\n", lc->mon_decimal_point);
		printf("mon_thousands_sep:%s\n", lc->mon_thousands_sep);
		printf("mon_grouping:%s\n", lc->mon_grouping);
		printf("positive_sign:%s\n", lc->positive_sign);
		printf("negative_sign:%s\n", lc->negative_sign);
  		printf("int_frac_digits:%c\n", lc->int_frac_digits);
  		printf("frac_digits:%c\n", lc->frac_digits);

		return 0;
	}
