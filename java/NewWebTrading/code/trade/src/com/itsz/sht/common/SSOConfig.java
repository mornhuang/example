package com.itsz.sht.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SSOConfig {

	private static final Log log = LogFactory.getLog(SSOConfig.class);

	public final static String PROPERTIES_FILENAME = "/sso.properties";

	public Properties loadProperties(String filename, String info) {
		URL url = null;

		ClassLoader threadContextClassLoader = Thread.currentThread()
				.getContextClassLoader();
		if (threadContextClassLoader != null) {
			url = threadContextClassLoader.getResource(filename);
		}
		if (url == null) {
			url = SSOConfig.class.getResource(filename);
			if (url == null) {
				log.warn("sso: No properties file found in the classpath by filename " + filename);
				return new Properties();
			}
		}
		return loadProperties(url, info);
	}

	public Properties loadProperties(URL url, String info) {
		log.info("sso: Getting properties from URL " + url + " for " + info);

		Properties properties = new Properties();
		InputStream in = null;

		try {
			in = url.openStream();
			properties.load(in);
			log.info("sso: Properties read " + properties);
		} catch (Exception e) {
			log.error("sso: Error reading from " + url, e);
			log.error("sso: Ensure the properties information in " + url + " is readable and in your classpath.");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				log.warn("sso: IOException while closing InputStream: "	+ e.getMessage());
			}
		}

		return properties;
	}
}
