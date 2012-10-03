package com.itsz.sht.common;

import java.util.Map;


/**
 * $Id: ChannelsParamInit.java,v 1.2 2011/01/19 02:46:02 kyzou Exp $
 * @Project:portal_b61
 * @File:ChannelsParamInit.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-5-5
 */

public class ChannelsParamInit {
	private Map channelMap;
	
	private Map channelIdMap;

	private Map languageMap;
	
	public Map getChannelIdMap() {
		return channelIdMap;
	}

	public void setChannelIdMap(Map channelIdMap) {
		this.channelIdMap = channelIdMap;
	}

	public Map getChannelMap() {
		return channelMap;
	}

	public Map getLanguageMap() {
		return languageMap;
	}

	public ChannelsParamInit(){
		
	}

	public void setChannelMap(Map channelMap) {
		this.channelMap = channelMap;
	}

	public void setLanguageMap(Map languageMap) {
		this.languageMap = languageMap;
	}
}
