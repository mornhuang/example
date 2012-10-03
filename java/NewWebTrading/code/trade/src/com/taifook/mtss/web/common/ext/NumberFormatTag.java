package com.taifook.mtss.web.common.ext;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.itsz.sht.common.FormatUtil;

public class NumberFormatTag extends AbstractTag{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6728607725885746252L;
	private int bit;	//保留小数位数
	private String pattern;	//格式化样式
	private boolean round;	//true四舍五入,false不进行四舍五入
	
	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String regx) {
		this.pattern = regx;
	}

	public boolean isRound() {
		return round;
	}

	public void setRound(boolean round) {
		this.round = round;
	}

	@Override
	protected String processTag() {
		if(StringUtils.isNotBlank(this.value)){
			if(round){
				return FormatUtil.data2Round(new BigDecimal(this.value),bit,pattern);
			}else if(StringUtils.isNotBlank(pattern)){
				return FormatUtil.formatNumber(new BigDecimal(this.value),bit,pattern);
			}else{
				return FormatUtil.formatNumber(new BigDecimal(this.value),bit);
			}
		}else{
			return "";
		}
	}
}
