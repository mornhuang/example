package com.itsz.sht.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * $Id: FieldUtil.java,v 1.1 2010/11/09 03:57:25 kyzou Exp $
 * @Project:portal
 * @File:FieldUtil.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-5
 */
public class FieldUtil {

	public static final DecimalFormat decimalFormat = new DecimalFormat("#,###.000");

	public FieldUtil() {
	}

	public static String toLabel(String s) {
		return StringUtil.toUTF8(s);
	}

	public static String decimal2String(Double doubleValue) {
		return decimal2String(doubleValue.doubleValue());
	}

	public static String decimal2String(double doubleValue) {
		String doubleStr = "";
		try {
			doubleStr = decimalFormat.format(doubleValue);
		} catch (Exception e) {
		}
		return doubleStr;
	}

	public static String format(Double value) {
		if (value == null)
			return null;
		else
			return decimal2String(value);
	}

	public static String formatLong(Long value, String formatPattern) {
		if (value == null) {
			return null;
		} else {
			DecimalFormat decimalFormatter = new DecimalFormat(formatPattern);
			return decimalFormatter.format(value.longValue());
		}
	}

	public static String formatDouble(Double value, String formatPattern) {
		if (value == null) {
			return null;
		} else {
			DecimalFormat decimalFormatter = new DecimalFormat(formatPattern);
			return decimalFormatter.format(value.doubleValue());
		}
	}

	public static String formatBigDecimal(BigDecimal value, String formatPattern) {
		if (value == null) {
			return null;
		} else {
			DecimalFormat decimalFormatter = new DecimalFormat(formatPattern);
			return decimalFormatter.format(value.doubleValue());
		}
	}

	public static String formatQuantity(Double lv, String pattern) {
		String result = null;
		try {
			if (lv != null) {
				DecimalFormat df = new DecimalFormat(pattern);
				double value = lv.doubleValue();
				double doubleValue = 0.0D;
				if (value < 10000D)
					result = String.valueOf(value);
				else if (value < 1000000D) {
					doubleValue = value / 1000D;
					result = df.format(doubleValue) + 'K';
				} else if (value < 1000000000D) {
					doubleValue = value / 1000000D;
					result = df.format(doubleValue) + 'M';
				} else {
					doubleValue = value / 1000000000D;
					result = df.format(doubleValue) + 'B';
				}
			}
		} catch (Throwable t) {
//			t.printStackTrace();
		}
		return result;
	}

}
