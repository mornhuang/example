/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;

public class RTQLog {
static Log log_info = LogFactory.getLog(Constants.LOG_INFO_RTQ_LOG);
    
    public static void rtqType(String channelType,String custCode,String type,boolean isAvailable){
        log_info.info(channelType + "\t" + custCode + "\t" + type + "\t" + "available:" + isAvailable);
    }
	/*public static void rtqType(String channelType,String custCode,String type){
		log_info.info(channelType + "\t" + custCode + "\t" + type);
	}*/
}
