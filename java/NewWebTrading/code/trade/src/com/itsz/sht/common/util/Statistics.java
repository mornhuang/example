/**
 * <p>Title:Channels Server for STT2.5</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;

public class Statistics {
    static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_STAT);
    
    public static void spendTime(String name,long beginTime,long endTime){
        long spendTime = endTime - beginTime;
        log_debug.info(name + spendTime);
    }

}
