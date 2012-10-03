package cn.itsz.newsim.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import cn.itsz.newsim.common.Constants;

public class BaseDTO {
	private String channelId;
	private String channelType = Constants.LoginChannel.NSIM;
	private String language;
	private String versionId;
	
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
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
