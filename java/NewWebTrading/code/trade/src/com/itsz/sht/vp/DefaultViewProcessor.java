package com.itsz.sht.vp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;

import com.itsz.sht.common.Constants;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id:
 * @Project:portal
 * @File:DefaultViewProcessor.java
 * @Description:
 * @Author:swLiu
 * @Date:2007-5-28
 */
public class DefaultViewProcessor implements ViewProcessor {
	
	protected static Log log = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);	
	
	public ActionForward processException(ProcessBean processBean) {
		return null;
	}
}
