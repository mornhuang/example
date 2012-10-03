/**
 * <p>Title: 3G Portal</p>
 * <p>Description: 3G Portal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.vp.ps.STT_Constants;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.response.ConfigInfo;
import com.taifook.mcs.core.beans.msg.MCSMessage;
import com.taifook.mcs.msg.MessageProcessor;


public class STTUtil {
    private static Log log_info = LogFactory.getLog(Constants.LOG_INFO_COMMON);
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
    public static String bean2csv(Object result){
        MCSMessage message;
        try{
            message = (MCSMessage)result;
        }catch(Exception e){
            return "";
        }
	     MessageProcessor processor=new MessageProcessor();
	     String csv="";
	     try {
	            csv = processor.messageToCSV(message);
	     } catch (Exception e) {
//	            e.printStackTrace();
	    	 log_debug.error("exception exist:"+e.getMessage());
	     }
	    return csv;
    }
    
    public static String TaskRespCsv(String errCode,String errMsg,String taskNo){
		StringBuffer csv = new StringBuffer("TKPO=(VSID='Ver1_00',MSID='TKPO',ECOD='");
		csv.append(errCode).append("',");
		csv.append("EMSG='").append(errMsg).append("',");
		csv.append("ESTS='").append("',");
		csv.append("TKNO='").append(taskNo).append("')");
		return csv.toString();
    }
    
    /*
     * @Description:convert config value to csv
     * example:CONFIG-L=( CONFIG [0]=(NAME='orderSize',VALUE='5'), CONFIG [1]=(NAME='basketSize',VALUE='5'))
     * @Author:kyzou
     * @Date:2009-03-30
     */
    public static String config2csv(Collection configResults){
		StringBuffer csv = new StringBuffer("RCNF=(MSID='RCNF',CONFIG-L=( ");
		try {
			int num = 0;
			for (Iterator iterator = configResults.iterator(); iterator.hasNext();) {
				ConfigInfo config = (ConfigInfo) iterator.next();
				csv.append("CONFIG [").append(num).append("]=(");
				csv.append("NAME='").append(config.getConfigKey()).append("',");
				csv.append("VALUE='").append(config.getConfigValue()).append("'),");
				num++;
			}
		} catch (Exception e) {
			log_info.error("exception exist:",e);
		}
		return csv.append("))").toString();
    }
    
    private static StringTokenizer st; 
    private static Vector v;
    public static Vector split(String str, char token)throws Exception {
        v = new Vector();
        try {
            st = new StringTokenizer(str, String.valueOf(token));
            while (st.hasMoreTokens()) {
                v.add(st.nextToken());
            }
        } catch (Exception e) {
            log_debug.info("[FormatException]=" + e.toString()
                    + "   [FormatObject]=" + str + "   [return null]");
            throw new Exception();
        }
        return v;
    }
   public static Vector split(String str, String token)throws Exception {
        v = new Vector();
        try {
            st = new StringTokenizer(str, token);
            while (st.hasMoreTokens()) {
                v.add(st.nextToken());
            }
        } catch (Exception e) {
            log_debug.info("[FormatException]=" + e.toString()
                    + "   [FormatObject]=" + str + "   [return null]");
            throw new Exception();
        }
        return v;
    }
   public static int checkVersion(HttpServletRequest request,HttpServletResponse response,
		   int version)throws Exception{
        HttpSession session=request.getSession();
	    String allowVersion=(String)session.getServletContext().getAttribute(
	    		STT_Constants.STT_ALLOW_VERSION);
	    String lastVersion=(String)session.getServletContext().getAttribute(
	    		STT_Constants.STT_LAST_VERSION);
	    boolean hasValue=true;
	    if(allowVersion==null || lastVersion==null){
	        hasValue=false;
	    }
	    
	    if (!hasValue) {
			try {
			    log_debug.info("get properties from properties ");
				allowVersion = PropertyConfig.getCommonProperty(
						STT_Constants.STT_ALLOW_VERSION);
				lastVersion = PropertyConfig.getCommonProperty(
						STT_Constants.STT_LAST_VERSION);
				session.getServletContext().setAttribute(
						STT_Constants.STT_ALLOW_VERSION,allowVersion);
				session.getServletContext().setAttribute(
						STT_Constants.STT_LAST_VERSION,lastVersion);
			} catch (Exception e) {
				log_debug.warn(STT_Constants.STT_ALLOW_VERSION+ " property Setting ERROR!!!");	
				log_debug.warn(STT_Constants.STT_LAST_VERSION+ " property Setting ERROR!!!");
				throw new Exception();
			}
	    }
	    int allowVer = 0,lastVer = 0;
	    try{
	        allowVer=Integer.parseInt(allowVersion);
	        lastVer=Integer.parseInt(lastVersion);
	    }catch(Exception e){
	        throw new Exception();
	    }
	    if(version<allowVer){
	        return STT_Constants.MUST_UPDATE;
	    }
	    if(version<lastVer){
	        return STT_Constants.HAS_NEW_VERSION;
	    }
	    return STT_Constants.IS_LAST_VERSION;
	}
   public static Locale lang2locale(String language){
       if(language==null){
           return Locale.TRADITIONAL_CHINESE;
       }
       if("ENG".equals(language.trim())){
           return Locale.ENGLISH;
       }
       if("GB".equals(language.trim())){
           return Locale.SIMPLIFIED_CHINESE;
       }
       if("BIG5".equals(language.trim())){
           return Locale.TRADITIONAL_CHINESE;
       }
       return Locale.TRADITIONAL_CHINESE;
   }
   
   public static String getTimeString(long timestamp){
	   if(timestamp == 0){
		   return "0";
	   }
        Date time = new Date(timestamp);
        String pattern = "yyyyMMddHHmmss";
		SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
		String timeString = dateFormat.format(time);
		return timeString;
   } 
 
   /*public static String getProperties(HttpServletRequest request,String key) throws Exception{
       HttpSession session = request.getSession();
       String returnValue;
       returnValue=(String)session.getServletContext().getAttribute(key);
	    if(returnValue==null){
			try {
			    log_debug.info("get properties from config.properties");
			    returnValue = PropertyConfig.getCommonProperty(key);				
				session.getServletContext().setAttribute(key,returnValue);
			} catch (Exception e) {
				log_debug.warn(key+ " property Setting ERROR!!!");		
				throw new Exception();
			}
	    }
        return returnValue;
   }*/
   
   public static void main(String[] args) {
       String s = "02-0042038-30,02-0042038-22";
       try {
           Vector v = split(s, ",");
           for (int i = 0; i < v.size(); i++) {
               System.out.println(v.get(i));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
      //System.out.println(getTimeString(555555555555L));
   }
}
