package com.itsz.sht.common.model.common;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.ForwardMappingUtil;



public class AbstractResponseModel {
	protected String channelId; //refact from ChannelId to channelId
	protected String channelType;
	protected String language;
	protected String versionId;//refact from version to versionId
	protected String returnCode;
	protected String resultForward;
	
	public AbstractResponseModel(){
		this.setReturnCode(Consts.Error.Code.NORMAL);
		this.setResultForward(ForwardMappingUtil.getForward(Consts.Error.Code.SUCCESS));
	}
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getResultForward() {
		return resultForward;
	}
	public void setResultForward(String resultForward) {
		this.resultForward = resultForward;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}	
	
}
