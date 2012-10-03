package com.taifook.adminportal.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CompressUtil {
	private static Log log = LogFactory
			.getLog("com.taifook.adminportal.common.util.CompressUtil");

	static final int BUFFER = 2048;

	public File compressFile(File file) {
		if (file != null && file.exists()) {
			int readSize = 0;
			File zipFile = new File(file.getAbsolutePath() + ".zip");
			
			if(zipFile.exists()){
				zipFile.delete();
			}
			
			byte[] readData = null;
			BufferedInputStream srcBis = null;
			ZipOutputStream zipOut = null;

			try {
				srcBis = new BufferedInputStream(new FileInputStream(file));
				zipOut = new ZipOutputStream(new BufferedOutputStream(
						new FileOutputStream(zipFile)));
				ZipEntry zipEntry = new ZipEntry(file.getName());
				zipOut.putNextEntry(zipEntry);
				readData = new byte[BUFFER];
				while ((readSize = srcBis.read(readData, 0, BUFFER)) != -1) {
					zipOut.write(readData, 0, readSize);
					zipOut.flush();
					readData = new byte[BUFFER];
				}

			} catch (Exception e) {
				e.printStackTrace();
				zipFile = null;
				log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
						.format(new Date())
						+ " compress file error!");
			} finally {
				try {
					try{
						if (srcBis != null) {
							srcBis.close();
						}
					}catch(Exception e){}
					
					try{
						if (zipOut != null) {
							zipOut.flush();
							zipOut.close();
						}
					}catch(Exception e){}
					
				} catch (Exception e) {
					zipFile = null;
					log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
							.format(new Date())
							+ " compress file error!");
				}
			}
			log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
					.format(new Date())
					+ " compress file success! "+zipFile.getName());			
			return zipFile;
		} else {
			log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
					.format(new Date())
					+ " compress file fail! the file is not exist");
			return null;
		}

	}

}
