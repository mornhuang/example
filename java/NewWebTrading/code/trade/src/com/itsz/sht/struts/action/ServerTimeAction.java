package com.itsz.sht.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;


public class ServerTimeAction extends ITSZAction {
	final String ACTION_NAME = "ServerTime";
	final String BEIJING_TIME_ZONE = "8";
	final String CSV_ACTION_NAME = "RSDT";
	final String CSV_FILELD_TIME = "SRTM";
	final String CSV_FILELD_TIMEZONE = "SRTZ";
	String datetime = null;


	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Consts.DateTime.Format.Pattern.yyyyMMddHHmmss);
		datetime = dateFormat.format(new Date());
		String csv = PortalUtil.createCSV(CSV_ACTION_NAME, CSV_FILELD_TIME, datetime);
		csv = PortalUtil.addFieldToCSV(csv, CSV_FILELD_TIMEZONE, BEIJING_TIME_ZONE);
        ProcessBean processBean = new ProcessBean(csv, null, mapping, request, response);
		return vp.processServerTime(processBean);

	}
}