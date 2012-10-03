package com.itsz.sht.common;

/**
 * 
 * $Id: ErrorReferenceGenerator.java,v 1.1 2010/11/09 03:57:25 kyzou Exp $
 * @Project:portal
 * @File:ErrorReferenceGenerator.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-5
 */
public class ErrorReferenceGenerator {

	protected ErrorReferenceGenerator() {
		config = null;
		counter = 0;
	}

	public static ErrorReferenceGenerator instance() {
		return instance;
	}

	public void init(WebConfig config) {
		this.config = config;
	}

	public String getErrorRef() {
		long time = System.currentTimeMillis();
		synchronized (com.itsz.sht.common.ErrorReferenceGenerator.class) {
			if (counter < 0)
				counter = 0;
			counter++;
		}
		StringBuffer buf = new StringBuffer();
		buf.append(config.getId());
		buf.append("-");
		buf.append(time);
		buf.append("-");
		buf.append(counter);
		return buf.toString();
	}

	private static ErrorReferenceGenerator instance = new ErrorReferenceGenerator();
	private WebConfig config;
	private short counter;

}