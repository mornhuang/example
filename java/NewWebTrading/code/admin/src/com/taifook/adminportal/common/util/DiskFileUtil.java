package com.taifook.adminportal.common.util;

import java.io.File;

public class DiskFileUtil {

	//
	public static boolean deepDeleteFilePath(File tempFilePath) {
		if (tempFilePath == null) {
			return false;
		}
		
		if (tempFilePath.exists()) {
			if (tempFilePath.isDirectory()) {
				File[] files = tempFilePath.listFiles();
				if (files.length > 0) {
					for (int index = 0; index < files.length; index++) {
						if (files[index].isDirectory()) {
							deepDeleteFilePath(files[index]);
						} else {
							files[index].delete();
						}
					}
				}
			}

			tempFilePath.delete();
		}
		return true;
	}
}
