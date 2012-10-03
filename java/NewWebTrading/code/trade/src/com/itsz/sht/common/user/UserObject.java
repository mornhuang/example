/*
 * Created on 2005-3-29
 *
 */
package com.itsz.sht.common.user;

import java.io.Serializable;

/**
 * @author wzzhan
 * 
 */
public class UserObject implements Serializable {
	private String sessionID;

	private String loginName;

	private String channelType;

	private String channelID;

	private String browser;

	private String password;

	private String lang;

	private String countryCode;

	private boolean isDefaultAccountExist = true;

	private TradeInfoObject tradeInfoObject;

	private RTQInfoObject rTQInfoObject;

	private RTQSubscribeInfo rtqSubscribeInfo;
	
	private String clv;
	
	public String getClv() {
		return clv;
	}

	public void setClv(String clv) {
		this.clv = clv;
	}

	/////////////////////////////////////////////
	private int accessUnit ;
	
	private String serviceType;
	/////////////////////////////////////////////

	/**
	 * @return Returns the isDefaultAccountExist.
	 */
	public boolean isDefaultAccountExist() {
		return isDefaultAccountExist;
	}

	/**
	 * @param isDefaultAccountExist
	 *            The isDefaultAccountExist to set.
	 */
	public void setDefaultAccountExist(boolean isDefaultAccountExist) {
		this.isDefaultAccountExist = isDefaultAccountExist;
	}

	/**
	 * @return Returns the rTQInfoObject.
	 */
	public RTQInfoObject getRTQInfoObject() {
		return rTQInfoObject;
	}

	/**
	 * @param infoObject
	 *            The rTQInfoObject to set.
	 */
	public void setRTQInfoObject(RTQInfoObject infoObject) {
		rTQInfoObject = infoObject;
	}

	/**
	 * 3g multiple devices UI's Browser setting
	 * 
	 * @return Returns the browser.
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * @param browser
	 *            The browser to set.
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	 * @return Returns the channelID.
	 */
	public String getChannelID() {
		return channelID;
	}

	/**
	 * @param channelID
	 *            The channelID to set.
	 */
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	/**
	 * @return Returns the lang.
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            The lang to set.
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return Returns the countryCode.
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            The countryCode to set.
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return Returns the tradeInfoObject.
	 */
	public TradeInfoObject getTradeInfoObject() {
		return tradeInfoObject;
	}

	/**
	 * @param tradeInfoObject
	 *            The tradeInfoObject to set.
	 */
	public void setTradeInfoObject(TradeInfoObject tradeInfoObject) {
		this.tradeInfoObject = tradeInfoObject;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the channelType.
	 */
	public String getChannelType() {
		return channelType;
	}

	/**
	 * @param channelType
	 *            The channelType to set.
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	/**
	 * @return Returns the loginName.
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            The loginName to set.
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return Returns the sessionID.
	 */
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * @param sessionID
	 *            The sessionID to set.
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public RTQSubscribeInfo getRtqSubscribeInfo() {
		return rtqSubscribeInfo;
	}

	public void setRtqSubscribeInfo(RTQSubscribeInfo rtqSubscribeInfo) {
		this.rtqSubscribeInfo = rtqSubscribeInfo;
	}
	
	//////////////////////////////////////////////////////////
	public int getAccessUnit () {
		return accessUnit;
	}
	
	public void setAccessUnit (int accessUnit) {
		this.accessUnit = accessUnit;
	}
	
	public String getServiceType () {
		return serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	//////////////////////////////////////////////////////////
}
