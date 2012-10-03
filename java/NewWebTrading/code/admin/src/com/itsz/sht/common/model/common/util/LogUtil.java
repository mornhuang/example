package com.itsz.sht.common.model.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.JSONUtil;

public class LogUtil {
	private static Log log_dto = LogFactory.getLog(Consts.Log.Info.COMMON);

	public static final void logDTO(String notes, Object logBean) {
		if(logBean == null) {
			log_dto.info("Object DTO=[NULL]");
			return;
		}
		log_dto.info(notes + getLogBeanString(logBean));
	}

	/**
	 * 
	 * @Author:
	 * @Time:2007-10-25 涓嬪崍02:43:29
	 * @param logBean
	 */
	public static final void logDTO(Object logBean) {
		if(logBean == null) {
			log_dto.info("Object DTO=[NULL]");
			return;
		}
		log_dto.info(getLogBeanString(logBean));
	}

	
	/**
	 * 
	 * @Author:
	 * @Time:2007-10-25 涓嬪崍02:43:50
	 * @param logBean
	 * @return
	 */
	private static String getLogBeanString(Object logBean){
		String logStr = null;
		if (logBean instanceof String) {
			logStr = (String) logBean;
		} else {
			logStr = JSONUtil.bean2JSON(logBean);
		}
		return logStr;
	}
}
