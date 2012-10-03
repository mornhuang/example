package com.taifook.adminportal.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jspsmart.upload.SmartUpload;

public class UpDownLoadUtil {
	private static Log log = LogFactory
			.getLog("com.taifook.adminportal.common.util.DownLoadUtil");

	public static boolean downLoadFile(File file, ServletConfig servletConfig,
			HttpServletRequest request, HttpServletResponse response)throws Exception {

		if (file == null) {
			log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
					.format(new Date())
					+ " down load file is null ");
			return false;
		}

		SmartUpload downLoad = new SmartUpload();
		try {
			downLoad.initialize(servletConfig, request, response);
			downLoad.setContentDisposition(null);
			downLoad.downloadFile(file.getAbsolutePath());
			log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
					.format(new Date())
					+ " down load file success ");
		} catch (Exception e) {
			log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
					.format(new Date())
					+ " down load file fail: " + e.getMessage());
			throw e;
		}
		return true;

	}

}
