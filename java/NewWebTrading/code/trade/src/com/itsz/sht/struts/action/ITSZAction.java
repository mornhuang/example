package com.itsz.sht.struts.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.admin.channel.util.ChannelInfoManagement;
import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.MySessionContext;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.service.Facade;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.TradeInfoObject;
import com.itsz.sht.common.user.UserManagement;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.common.util.LogUtil;
import com.itsz.sht.common.util.Statistics;

/**
 * $Id: ITSZAction.java,v 1.9 2011/05/05 02:57:11 lpchen Exp $
 * @Project:portal.head
 * @File:ITSZAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public abstract class ITSZAction extends Action {
	//get facade
	public Facade facade;

	BeginTimeProxy time = null;//new BeginTimeProxy();
    protected static final String SUCCESS_KEY = "success";
	protected static final String FAILURE_KEY = "failure";

	protected static Log log_info = LogFactory.getLog(Consts.Log.Info.COMMON);
	protected static Log log_debug = LogFactory.getLog(Consts.Log.Debug.COMMON);

	public ITSZAction() {
		super();
		facade = (Facade) ServiceLocator.getInstance().getService("facade");
	}

	/**
	 * 
	 * @Time:10:26:43
	 * @param request
	 * @param response
	 */
	protected void beforeExecute(HttpServletRequest request,
			HttpServletResponse response) {
		time.setBeginTime(System.currentTimeMillis());
		request.setAttribute("className", this.getClass().getName());
	}

	/**
	 * 
	 * @Time:10:26:55
	 * @param request
	 * @param response
	 */
	protected void afterExecute(HttpServletRequest request,
			HttpServletResponse response) {
		//log_info.info("afterExecute");
		long endTime = System.currentTimeMillis();
		String action = this.getClass().getName().toString();
		Statistics.spendTime("thread :" + Thread.currentThread().getName()
				+ " " + action + " cs :", time.getBeginTime(), endTime);
	}

	/**
	 * business action execute
	 * @Time:10:27:29
	 * @param vp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ActionForward executeAction(ViewProvider vp,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * logonAction overwrite this method and return true;
	 * @Time:10:27:59
	 * @return
	 */
	public boolean isLoginActionExecuted() {
		return false;
	}

	/**
	 * struts token required 
	 * @Time:10:28:16
	 * @return
	 */
	public boolean isTokenRequired() {
		return false;
	}

	protected void delToken(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.VALIDATION);
	}
	
	protected synchronized boolean checkAndSetToken(HttpServletRequest request) {
		boolean valid = isTokenValid(request);
		if (valid) {
			delToken(request);
		}
		return valid;
	}
	
	/**
	 * check is user login or not
	 * @Time:10:29:02
	 * @param request
	 * @throws ITSZException
	 */
	public static void sessionManagement(HttpServletRequest request)
			throws ITSZException {
		String sessionId=request.getParameter("sessionId");//NPS的请求有的并没有传jsessionId过来,而是传的sessionId
		HttpSession session = MySessionContext.getSession(sessionId);
		if(null==session)
		    session = request.getSession();
		if (session.getAttribute(Constants.USER) == null) {
			log_debug
					.info("@@@@@@@@@@@  User not Exist  id=" + session.getId());
			ITSZException ex = new ITSZException();
			ex.setErrorCode(Consts.Error.Code.SESSION_OVERTIME);
			throw ex;
		} else {
			UserObject user = (UserObject) session.getAttribute(Constants.USER);
			String channelType = user.getChannelType();
			String loginId = user.getLoginName();
			if (!UserManagement
					.allowUser(session.getId(), channelType, loginId)) {
				log_debug.info("@@@@@@@@@@@  sessionID not Exist  id="
						+ session.getId());
				ITSZException ex = new ITSZException();
				ex.setErrorCode(Consts.Error.Code.SESSION_OVERTIME);
				throw ex;
			}
		}
	}

	/**
	 * 
	 */
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LogUtil.logDTO("Client Request: ", form);
		HttpSession session = request.getSession();
		log_info.info(request.getRemoteHost() + "  " + session.getId());
		String clv = getCLV(request,response,(ITSZForm) form);
		String language = CLVSplitUtil.getLanguage(clv);
		 if(Consts.Global.Language.GB.equals(language)){
            session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName,Locale.SIMPLIFIED_CHINESE);
            
        }else if(Consts.Global.Language.ENG.equals(language)){
            session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName,Locale.US);
           
        }else if(Consts.Global.Language.BIG5.equals(language)){
        	session.setAttribute(Consts.Global.Common.defaultLocaleAttributeName,Locale.TRADITIONAL_CHINESE);
        	
        }
		String channelType = CLVSplitUtil.getChannelType(clv);
		log_info.info("channelType=" + channelType);
		ViewProvider viewProvider = getViewProvider(channelType);
		
		if (viewProvider == null) {
			return doVPEmpty(mapping, request);
		}
		if (ChannelInfoManagement.isChannelStopped(channelType)) {
			return checkChannleStatusException(viewProvider, mapping, request, response);
		}
		return doActionExecute(mapping, form, request, response, viewProvider,channelType);
	}

	private ActionForward doActionExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ViewProvider viewProvider,String channelType) {
		ActionForward forward = null;
		try {
			if (!isLoginActionExecuted()){
				sessionManagement(request);
			}
			forward = executeAction(viewProvider, mapping, form, request,response);
		} catch (ITSZException ex) {
			forward = checkSessionException(viewProvider, mapping, request,response, ex);
		} catch (Exception e) {
			e.printStackTrace();
			log_debug.error("exception exist: " + e.getMessage());
		}
		if (forward != null) {
			log_debug.info("forward to----> " + forward.getPath());
		}
		return forward;
	}

	private boolean isTokenValid(String token, String val) {
		return val != null && val.equals(token);
	}
	
	protected boolean isTokenValid(HttpServletRequest request) {
		return isTokenValid(request.getParameter("token"), (String)request.getSession().getAttribute(Constants.VALIDATION));
	}
	
	protected void setTokenToSession(HttpServletRequest request) {
		request.getSession().setAttribute(Constants.VALIDATION, request.getParameter("token"));
	}
	
	protected void setOldTokenToSession(HttpServletRequest request, String token) {
		request.getSession().setAttribute(Constants.VALIDATION, token);
	}
	
	protected String getSessionToken(HttpServletRequest request){
		return (String)request.getSession().getAttribute(Constants.VALIDATION);
	}
	
	private String getCLV(HttpServletRequest request,HttpServletResponse response, ITSZForm tForm) {
		String clv = "";
		if (tForm != null)
			clv = tForm.getCLV();
		clv = CLVSplitUtil.transNullCLV(request,response, tForm, clv);
		request.setAttribute(Consts.Global.Common.CHANNEL_CLV, clv);
		return clv;
	}

	private ActionForward doVPEmpty(ActionMapping mapping,
			HttpServletRequest request) {
		log_debug.info("viewProvider not founded!!!");
		ITSZException ex = new ITSZException();
		ex.setErrorCode(Consts.Error.Code.ERRORCODE_SYS);
		log_debug.info("ActionForward processException: " + ex.getErrorCode()
				+ "  " + ex.getErrorMessage());
		request.setAttribute("", ex);
		return mapping.findForward("");
	}

	/**
	 * 
	 * @Time:10:38:14
	 * @param vp
	 * @param mapping
	 * @param exceptionBean
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward processException(ViewProvider vp,
			ActionMapping mapping, ITSZException exceptionBean,
			HttpServletRequest request, HttpServletResponse response) {
		return vp.processException(new ProcessBean(exceptionBean, null,
				mapping, request, response));
	}

	/**
	 * @param channelType
	 * @return ViewProvider
	 */
	protected ViewProvider getViewProvider(String channelType) {
		ViewProvider provider = (ViewProvider) ServiceLocator.getInstance().getService(channelType + "ViewProvider");
		return provider;
	}

	/**
	 * check if need to update,or use cache's data
	 * @param lastUpdateTime
	 * @param nowTime
	 * @param CacheTime
	 * @return
	 */
	public static boolean isUseCache(long lastUpdateTime, long nowTime,
			long CacheTime) {
		if (lastUpdateTime == 0) {
			return false;
		}
		log_debug.info("nowTime-lastUpdateTime=" + nowTime + "-"
				+ lastUpdateTime + "=" + (nowTime - lastUpdateTime));
		log_debug.info("cacheTime=" + CacheTime);
		return (nowTime - lastUpdateTime) < CacheTime;
	}

	/**
	 * 
	 * @Time:10:39:11
	 * @param vp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkTradingAccount(ViewProvider vp,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserObject user = new UserObject();
		user = (UserObject) session.getAttribute(Constants.USER);
		String tradingAccount;
		log_debug.info("check trading account");
		try {
			tradingAccount = user.getTradeInfoObject().getTradingAccount();
			if (StringUtils.isBlank(tradingAccount)) {
				log_debug.warn("this loginID has not trading account");
				throw new Exception();
			}
		} catch (Exception e) {
			ITSZException ex = new ITSZException();
			ex.setErrorCode(Consts.Error.Code.CHECK_TRADING_ACCOUNT);
			ex.setErrorMessage(e.toString());
			log_debug.info(e.toString());
			return vp.processException(new ProcessBean(ex, null, mapping,
					request, response));
		}
		return null;
	}

	/**
	 * 
	 * @Time:10:40:08
	 * @param vp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkAllowTradeStatusFlag(ViewProvider vp,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserObject user = new UserObject();
		user = (UserObject) session.getAttribute(Constants.USER);
		String allowTradeStatusFlag;
		log_debug.info("check allow trade status flag");
		try {
			allowTradeStatusFlag = user.getTradeInfoObject()
					.getAllowTradeStatusFlag();
			if (allowTradeStatusFlag == null
					|| "N".equalsIgnoreCase(allowTradeStatusFlag.trim())) {
				log_debug.warn("this loginID can not input order");
				throw new Exception();
			}
		} catch (Exception e) {
			ITSZException ex = new ITSZException();
			ex.setErrorCode(Consts.Error.Code.ERRORCODE_CHECK_ALLOW_TRADE_FLAG);
			ex.setErrorMessage(e.toString());
			log_debug.debug(e.toString());
			return vp.processException(new ProcessBean(ex, null, mapping,
					request, response));
		}
		return null;
	}

	/**
	 * eservice language different from mcs or rtq!
	 * @param lang
	 * @return
	 */
	public String getEserviceLang(String lang) {
		String result = "EN";
		if (StringUtils.isNotBlank(lang)) {
			if (lang.equals(Consts.Global.Language.GB)) {
				result = "GB";
			} else if (lang.equals(Consts.Global.Language.BIG5)) {
				result = "B5";
			}
		}
		log_debug.info("Language  Form [" + lang + " ]==>>esev [ " + result
				+ " ]");
		return result;
	}

	/**
	 * 
	 * @Time:10:49:26
	 * @param request
	 * @param response
	 * @param accountID
	 * @return
	 */
	public boolean isOwnAccountID(HttpServletRequest request,
			HttpServletResponse response, String accountID) {
		HttpSession session = request.getSession();
		UserObject user = (UserObject) session.getAttribute(Constants.USER);
		try {
			TradeInfoObject tradeInfo = new TradeInfoObject();
			tradeInfo = user.getTradeInfoObject();
			String ta = tradeInfo.getTradingAccount();
			if (accountID.trim().equals(ta.trim())) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @Time:10:50:33
	 * @param viewProvider
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	protected ActionForward checkChannleStatusException(
			ViewProvider viewProvider, ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response) {
		ITSZException ex = new ITSZException();
		ex.setErrorCode(Consts.Error.Code.ERRORCODE_CHANNEL_STOPPED);
		return processException(viewProvider, mapping, ex, request, response);
	}

	/**
	 * 
	 * @Time:10:50:50
	 * @param viewProvider
	 * @param mapping
	 * @param request
	 * @param response
	 * @param ex
	 * @return
	 */
	protected ActionForward checkSessionException(ViewProvider viewProvider,
			ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, ITSZException ex) {
		return processException(viewProvider, mapping, ex, request, response);
	}

}

class TimeBean {
	private long beginTime;

	public void setBeginTime(long ti) {
		beginTime = ti;
	}

	public long getBeginTime() {
		return beginTime;
	}
}

class BeginTimeProxy {

	ThreadLocal<TimeBean> tl = new ThreadLocal<TimeBean>();

	public void setBeginTime(long ti) {
		getBean().setBeginTime(ti);
	}

	public long getBeginTime() {
		return getBean().getBeginTime();
	}

	private TimeBean getBean() {
		TimeBean bt = (TimeBean) tl.get();
		if (bt == null) {
			bt = new TimeBean();
			tl.set(bt);
		}
		return bt;
	}
}