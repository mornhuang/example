package com.itsz.sht.common;

public class NumberUtils {

	public static int getNumber(String str){
		String numString="";
		for (int i = 0; i < str.length(); i++) {
			int chr=str.charAt(i);
			char chr2=str.charAt(i);
			 if(!(chr<48 || chr>57)) 
				 numString=numString+String.valueOf(chr2);				
		}		
		return new Integer(numString);
	}

	public static String getPreString(String str){
		String numString="";
		for (int i = 0; i < str.length(); i++) {
			int chr=str.charAt(i);
			char chr2=str.charAt(i);
			 if(chr<48 || chr>57) 
				 numString=numString+String.valueOf(chr2);	
		}		
		return numString;
	}
	
	public static String getString(String preStr, int num,int length){		
		String str=preStr+String.valueOf(num);		
		for (int i =str.length(); i < length; i++) {
			preStr=preStr+"0";
		}		
		return preStr+String.valueOf(num);
	}
	
	public static void main(String[] args) {
		String test="APP001";
        int num=getNumber(test);
        System.out.println(num);
        String preString=getPreString(test);
        System.out.println(preString);
        System.out.println(getString(preString,num,test.length()));
        
		
	}
	
}
