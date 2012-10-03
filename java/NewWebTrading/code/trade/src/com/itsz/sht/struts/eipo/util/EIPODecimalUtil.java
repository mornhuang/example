package com.itsz.sht.struts.eipo.util;

import java.math.BigDecimal;

import com.itsz.sht.common.FieldUtil;


public class EIPODecimalUtil {

	public static String getFormatPriceRate(BigDecimal price) {
		String s = null;
		if (price != null) {
			s = FieldUtil.formatDouble(price.doubleValue(),"#,###.####%");
		}
		return s;
	}
}
