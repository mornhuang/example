/*
 * Created on 2005-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.common.util.configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wzzhan
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FileObjectLoader {
	public static void saveFileObject(String name, Object obj) throws Exception {
		FileOutputStream fileOutput = null;
		ObjectOutputStream oOutput = null;
		try {
			fileOutput = new FileOutputStream(name);
			oOutput = new ObjectOutputStream(fileOutput);
			oOutput.writeObject(obj);
			oOutput.flush();

		} catch (Exception e) {
			throw e;
		} finally {
			if (fileOutput != null) {
				fileOutput.close();
			}
			if (oOutput != null) {
				oOutput.close();

			}
		}
	}

	public static Object getFileObject(String name) throws Exception {
		FileInputStream fInput = null;
		ObjectInputStream oInput = null;
		try {
			fInput = new FileInputStream(name);
			oInput = new ObjectInputStream(fInput);
			return oInput.readObject();
		} catch (Exception e) {
			throw e;
		} finally {
			if (fInput != null) {
				fInput.close();
			}
			if (oInput != null) {
				oInput.close();

			}
		}

	}

	public static void main(String[] args) {
	}
}