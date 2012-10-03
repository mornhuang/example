package com.taifook.adminportal.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReaderPropertiesUtil extends BasePropertiesParser{
	public ReaderPropertiesUtil(){
		
	}
	
	public ReaderPropertiesUtil(String fileName){
		this.fileName=fileName;
	}
}
