package com.taifook.adminportal.web.broadcast.actions;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.model.CsBroadcast;
import com.taifook.adminportal.web.broadcast.forms.BroadcastForm;

public abstract class BaseBroadcastAction extends BaseAction {

	public abstract ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	protected boolean checkInputDate(BroadcastForm broadcastForm) {
		Log log = LogFactory.getLog(this.getClass());
		boolean isRight = true;
		try {
			if (DataFormatUtil.String2Date(broadcastForm.getEndtime())
					.getTime()
					- DataFormatUtil.String2Date(broadcastForm.getStarttime())
							.getTime() >= 0)
				isRight = true;
			else
				isRight = false;
		} catch (Exception e) {
			log.warn("BroadcastAction-checkInputDate:is Exception!");
			log.warn(e.getMessage());
			isRight = false;
		}
		return isRight;
	}

	protected String getChannels(BroadcastForm broadcastForm) {
		StringBuffer str = new StringBuffer();
		String[] channels = broadcastForm.getChannels();
		if (channels != null) {
			for (int index = 0; index < channels.length; index++) {
				str.append(channels[index]);
			}
		}
		return str.toString();
	}

	protected CsBroadcast getBroadcast(BroadcastForm broadcastForm,
			CsBroadcast broadcast) throws Exception {
		Log log = LogFactory.getLog(this.getClass());
		try {
			log.info("starttime:" + broadcastForm.getStarttime());
			log.info("endtime:" + broadcastForm.getEndtime());
			log.info("level:" + broadcastForm.getBroadcastLevel());
			log.info("type:" + broadcastForm.getContentType());
			log.info("channels" + getChannels(broadcastForm));
			log.info("content en:" + broadcastForm.getContentEnUs());
			log.info("content cn:"
					+ (new String(broadcastForm.getContentZhCn().getBytes(
							"GB2312"), "ISO-8859-1")));
			log.info("content tw:"
					+ (new String(broadcastForm.getContentZhTw().getBytes(
							"ISO-8859-1"), "BIG5")));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.getDefault());

			broadcast.setStarttime(sdf.parse(broadcastForm.getStarttime()));
			broadcast.setEndtime(sdf.parse(broadcastForm.getEndtime()));
			broadcast.setBroadcastLevel(broadcastForm.getBroadcastLevel());
			broadcast.setContentType(broadcastForm.getContentType());
			broadcast.setChannels(getChannels(broadcastForm));
			broadcast.setContentEnUs(broadcastForm.getContentEnUs());
			broadcast.setContentZhTw(broadcastForm.getContentZhTw());
			broadcast.setContentZhCn(broadcastForm.getContentZhCn());
			broadcast.setLastupdatetime(new Date());
		} catch (Exception e) {
			log.warn("BroadcastAction-getBroadcast:CsBroadcast Exception!");
			log.warn(e);
			broadcast = null;
			throw e;
		}
		return broadcast;
	}

	protected Object[] getFiltersForDB(BroadcastForm actionForm) {
		List filterList = new ArrayList();

		if (actionForm.getStarttime() != null
				&& !actionForm.getStarttime().trim().equals("")) {	
			filterList.add("to_char(starttime,\'YYYY-MM-DD HH24:MI:SS\')  "+getConstraintForDB(actionForm.getStarttime(),true));
		}

		if (actionForm.getEndtime() != null
				&& !actionForm.getEndtime().trim().equals("")) {		
			filterList.add("to_char(endtime,\'YYYY-MM-DD HH24:MI:SS\')  "+getConstraintForDB(actionForm.getEndtime(),true));
		}

		if (actionForm.getBroadcastLevel() != null
				&& !actionForm.getBroadcastLevel().trim().equals("")) {
			filterList.add("UPPER(broadcastLevel) "+getConstraintForDB(actionForm.getBroadcastLevel(),true));
		}

		if (actionForm.getContentType() != null
				&& !actionForm.getContentType().trim().equals("")) {
			filterList.add("UPPER(contentType) "+getConstraintForDB(actionForm.getContentType(),true));
		}

		if (actionForm.getChannels() != null) {
			String channels[] = actionForm.getChannels();
			for (int index = 0; index < channels.length; index++) {
				if (channels[index] != null
						&& !channels[index].trim().equals("")) {
					filterList.add("UPPER(channels) "+getConstraintForDB(channels[index],true));
				}
			}

		}

		if (actionForm.getLastupdatetime() != null
				&& !actionForm.getLastupdatetime().trim().equals("")) {
			filterList.add("to_char(lastupdatetime,\'YYYY-MM-DD HH24:MI:SS\')  "+getConstraintForDB(actionForm.getLastupdatetime(),true));
		}

		return filterList.toArray();
	}
	
		
	protected String getFiltersForWeb(BroadcastForm actionForm) {
		StringBuffer sb = new StringBuffer();

		if (actionForm.getStarttime() != null
				&& !actionForm.getStarttime().trim().equals("")) {
			sb.append("starttime="+URLEncoder.encode(actionForm.getStarttime())+"&");
		}

		if (actionForm.getEndtime() != null
				&& !actionForm.getEndtime().trim().equals("")) {
			sb.append("endtime="+URLEncoder.encode(actionForm.getEndtime())+"&");
		}

		if (actionForm.getBroadcastLevel() != null
				&& !actionForm.getBroadcastLevel().trim().equals("")) {
			sb.append("broadcastLevel="+URLEncoder.encode(actionForm.getBroadcastLevel())+"&");
		}

		if (actionForm.getContentType() != null
				&& !actionForm.getContentType().trim().equals("")) {
			sb.append("contentType="+URLEncoder.encode(actionForm.getContentType())+"&");
		}

		if (actionForm.getChannels() != null) {
			String channels[] = actionForm.getChannels();
			for (int index = 0; index < channels.length; index++) {
				if (channels[index] != null
						&& !channels[index].trim().equals("")) {
					sb.append("channels="+URLEncoder.encode(channels[index])+"&");
				}
			}
		}

		if (actionForm.getLastupdatetime() != null
				&& !actionForm.getLastupdatetime().trim().equals("")) {
			sb.append("lastupdatetime="+URLEncoder.encode(actionForm.getLastupdatetime())+"&");
		}

		return sb.toString();
	}
			
}
