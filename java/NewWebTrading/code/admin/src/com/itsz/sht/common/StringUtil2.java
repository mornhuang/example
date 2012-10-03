package com.itsz.sht.common;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtil2 {
	private StringUtil2() {

	  }
	  public static final String PARTION_SEPERATOR="|^|";

	  /**
	   * "234"-->234
	   * @author : figo
	   * @param value
	   * @return
	   */
	  public static int toInt(String value) throws Exception{
	    return (int) toLong(value);
	  }

	  /**
	   * "234"-->234
	   * @author : figo
	   * @param value
	   * @return
	   */
	  public static long toLong(String value) throws Exception{
	    if (value == null)
	      return 0L;
	    String szTemp = "";
	    for (int i = 0; i < value.length(); i++)
	      if (value.charAt(i) != ',')
	        szTemp = szTemp + value.charAt(i);
	    try {
	      double dd = Double.parseDouble(szTemp);
	      long l1 = (long) dd;
	      return l1;
	    } catch (NumberFormatException e) {
	      long l = 0L;
	      return l;
	    }
	  }



	  /**
	   * round double
	   * 0.00155 (format : 0.0000)
	   * get 0.0016
	   * @author : figo
	   * @param d
	   * @param format
	   * @return
	   */
	  public static String toPrice(double d, String format) {
	    DecimalFormat df = new DecimalFormat(format);
	    return df.format(d);
	  }


	  /**
	   * Cut apart into an array according to the form appointed
	   * and if it hava the space ,then ignore;
	   * "2003-10-10" => "2003 10 10"
	   * @author : figo
	   * @param msg
	   * @param token
	   * @return
	   * @throws java.lang.Exception
	   */
	  public static String[] getDivisionString(String msg, String token) {
	    try {
	      if (msg == null || token == null)
	        return null;

	      StringTokenizer st1 = new StringTokenizer(msg, token);
	      String d1[] = new String[st1.countTokens()];
	      for (int x = 0; x < d1.length; x++)
	        if (st1.hasMoreTokens())
	          d1[x] = st1.nextToken();
	      return d1;
	    } catch (Exception ex) {
	      return null;
	    }
	  }

	  /**
	   *
	   * @author : figo
	   * @param buf :Original String
	   * @param src :replaced String
	   * @param dest:replace  String
	   * @return
	   */
	  public static StringBuffer replace(StringBuffer buf, String src, String dest) {
	    int offset = -1;
	    int lastOffset = 0;
	    while ((offset = buf.toString().indexOf(src, lastOffset)) != -1) {
	      buf.replace(offset, offset + src.length(), dest);
	      lastOffset = offset + 1;
	    }
	    return buf;
	  }

	  /**
	   *
	   * @param msg
	   * @return
	   */
	  public static boolean getStrTrimAndIgnoreCase(String sourceString,String comparedString){
	    try {
	      return sourceString.trim().equalsIgnoreCase(comparedString);
	    } catch (Exception ex) {
	      return false;
	    }
	  }

	  /**
	   * int[] msg = new int[]{2003,01,01}
	   * =>assembled with "-"
	   * get the String "2003-01-01"
	   * @param msg
	   * @param token
	   * @return
	   */
	  public static String assembledToString(int[] msg, String token) {
	    try {
	      if (msg.length==1) {return ""+msg[0];}

	      StringBuffer bf = new StringBuffer();
	      for (int i = 0; i < msg.length; i++) {
	        bf.append(""+msg[i]);
	        if (i != (msg.length-1)) bf.append(token);
	      }
	      return bf.toString();
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return null;
	    }
	  }


	  /**
	   * String[] msg = new String[]{"2003","01","01"}
	   * =>assembled with "-"
	   * get the String "2003-01-01"
	   * @param msg
	   * @param token
	   * @return
	   */
	  public static String assembledToString(String[] msg, String token) {
	    try {
	      if (msg.length==1) {return msg[0];}

	      StringBuffer bf = new StringBuffer();
	      for (int i = 0; i < msg.length; i++) {
	        bf.append(((msg[i]!=null) && !(msg[i].trim().equals("")))?msg[i]:" " );
	        if (i != (msg.length-1)) bf.append(token);
	      }
	      return bf.toString();
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return null;
	    }
	  }


	  /**
	   * "2003-10-10","-" =>
	   * "2003 10 10" Array
	   * @param msg
	   * @param token
	   * @return
	   * @throws java.lang.Exception
	   */
	  public static String[] partitionString(String msg, String token) {
	    try{
	      StringTokenizer st1 = new StringTokenizer(msg, token);
	      String d1[] = new String[st1.countTokens()];
	      for (int x = 0; x < d1.length; x++)
	        if (st1.hasMoreTokens())
	          d1[x] = st1.nextToken();
	      return d1;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return null;
	    }
	  }

	  /**
	   * "2003-10-10","-" =>
	   * "2003 10 10" Array int
	   * @param msg
	   * @param token
	   * @return
	   * @throws java.lang.Exception
	   */
	  public static int[] partitionStringToInt(String msg, String token) {
	    try{
	      StringTokenizer st1 = new StringTokenizer(msg, token);
	      String d1[] = new String[st1.countTokens()];
	      int[] tmp = new int[d1.length];
	      for (int x = 0; x < d1.length; x++)
	        if (st1.hasMoreTokens()) {
	          tmp[x] = Integer.parseInt(st1.nextToken());
	        }
	      return tmp;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      return new int[]{};
	    }
	  }


	  /**
	   * "11111111<!--$MSG$-->11111111","<!--$MSG$-->","222"
	   * =>replace
	   * 1111111122211111111
	   * @param buf :Original String
	   * @param src :replaced String
	   * @param dest:replace  String
	   * @return
	   */
	  public static StringBuffer replaceStringBf(StringBuffer buf, String src, String dest) {
	    int offset = -1;
	    int lastOffset = 0;
	    while ((offset = buf.toString().indexOf(src, lastOffset)) != -1) {
	      buf.replace(offset, offset + src.length(), dest);
	      lastOffset = offset + 1;
	    }
	    return buf;
	  }

	  /**
	   * "11111111<!--$MSG$-->11111111","<!--$MSG$-->","222"
	   * =>replace
	   * 1111111122211111111
	   * @param buf :Original String
	   * @param src :replaced String
	   * @param dest:replace  String
	   * @return
	   */
	  public static String replaceString(String msg, String src, String dest) {
	    int offset = -1;
	    int lastOffset = 0;
	    StringBuffer buf = new StringBuffer(msg);
	    while ((offset = buf.toString().indexOf(src, lastOffset)) != -1) {
	      buf.replace(offset, offset + src.length(), dest);
	      lastOffset = offset + 1;
	    }
	    return buf.toString();
	  }

	  /**
	   * no null and no space is true
	   * @author figo
	   * @param msg
	   * @return
	   */
	  public static boolean validateString (String msg) {
	    return (msg != null && !msg.trim().equals("")) ?(true) :(false);
	  }

	  /**
	   * get Space and msg value
	   * @param msg
	   * @return
	   */
	  public static String trimString (String msg) {
	    return (msg != null ) ?(msg.trim()) :("");
	  }

	  /**
	   * get tail int number from string
	   *    getTailInt("abc123") return 123
	   *    return -1 if the parameter is empty or has no tail number
	   * @param str
	   * @return
	   */
	  public static int getTailInt(String str){
	      if (str == null)
	          return -1;

	      int i;
	      for (i = 0; i < str.length(); i++) {
	          if (Character.isDigit(str.charAt(i)))
	              break;
	      }
	      try {
	          String numStr = str.substring(i);
	          return Integer.parseInt(numStr);
	      }
	      catch (Exception ex) {
	          return -1;
	      }
	  }

	  /**
	   * find the first digit char's index
	   * if parameter is null, will throw NullPointerException
	   * if there is no digit char, return str.length()
	   * @param str
	   * @return
	   */

	  public static int getFirstNumIndex(String str){
	      int i;
	      for ( i = 0; i < str.length(); i++) {
	          if (Character.isDigit(str.charAt(i)))
	              break;
	      }
	      return i;
	  }

	  /**
	   * add lead zero to make sure the result string's length reach at least minLength
	   * @param data
	   * @param minLength
	   * @return
	   */

	  public static String valueOfInt(int data, int minLength){

	      String result = String.valueOf(data);
	      if (result.length() >= minLength)
	          return result;

	      StringBuffer bf = new StringBuffer();
	      for (int i = 0; i < minLength - result.length(); i++) {
	          bf.append("0");
	      }
	      bf.append(result);
	      return bf.toString();
	  }

	  /************************************************************************
	   ************************************************************************
	   ************************************************************************
	   * Continue according to the form, please !!!
	   *
	   * .....
	   * @author : figo
	   * @version: 1.0
	   * .....
	   ************************************************************************
	   ************************************************************************
	   *************************************************************************/

	  public static boolean isEqual(String a,String b){
		  if(a==null && b==null){
			  return true;
		  }else if(a!=null && b!=null && a.equals(b)){
			  return true;
		  }
		  return false;
	  }
	  
	  public static List divisionList(List list,int num){
		  int i = list.size() /num + 1;
		  List divList = new ArrayList();
		  List temp = new ArrayList();
		  for(int j = 0; list != null && j < list.size(); j++){
			int k  = j /num;
	      	int m  = j % num;
	      	if(k != 0 && m == 0){  //many list
	      		divList.add(temp);
	      		temp = new ArrayList();
	      		temp.add((String)list.get(j));
	      	}else {
	      		temp.add((String)list.get(j));
	    	}
		  }
		  divList.add(temp);
		  return divList;
	  }
	  
	  
	  public static String convertToString(List list){
		  String str = "";
		  for(int i = 0; list != null && i < list.size();i++){
			  if (i < list.size() - 1) {
	          	
				  str = str + "'" + (String) list.get(i)
	                      + "'" + ",";
	          } else {
	        	  str = str + "'" + (String) list.get(i)
	                      + "'";
	          }
		  }
		  return str;
	  }
	  
	  


	  public static void main(String[] args) {
//	    try {
//	      boolean bl = StringUtil.validateString("String");
//	      System.out.println("bl:"+bl);
//	    }
//	    catch (Exception ex) {
//	      ex.printStackTrace();
//	    }
		  StringUtil2 utl = new StringUtil2();
		  List list = new ArrayList();
		  for(int i = 0; i < 100; i++){
			  list.add(i+" ");
		  }
//		  System.out.println("dfsdf ");
//		  System.out.println("dfsdf "+utl.convertToString(list));
		  System.out.println("begin");
		  List last = utl.divisionList(list,8);
		  System.out.println(last.size());
		  for(int k = 0; last != null && k <last.size(); k++){
			  List te = (ArrayList)last.get(k);
			  String str = "";
			  for(int m = 0; m < te.size(); m++){
				 str = str + "  " + (String)te.get(m);
			  }
			  System.out.println(str);
		  }
//		  System.out.println(utl.convertToString(list));
	  }
}
