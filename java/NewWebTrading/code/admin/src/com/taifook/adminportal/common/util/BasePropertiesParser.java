package com.taifook.adminportal.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BasePropertiesParser {
	protected static String basePath = null;

	protected String fileName = null;

	public static void setBasePath(String newBasePath) {
		basePath = newBasePath;
//		System.out.println("the base properties path: " + basePath);
	}

	public String getBasePath() {
		return this.basePath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public Properties reader() {
		Properties pros = new Properties();
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(this.basePath
					+ "/" + this.fileName));
			if (is != null) {
				pros = new Properties();
				pros.load(is);
			}
		} catch (Exception e) {

		} finally {
			if (is != null) {
				try {
					is.close();
					is=null;
				} catch (IOException e) {
					is=null;
				}
			}
		}

		return pros;
	}
}
