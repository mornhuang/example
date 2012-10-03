/*
 * Created on 2005-3-30
 */
package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * @author wzzhan
 */
public class ITSZActionForm extends ValidatorForm {

	
	
	/** Lang property */
	//private String lang;
	/** ChannelType property */
	private String channelType;
	private String CLV;

	
	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	
	/** 
	 * Returns the ChannelType.
	 * @return String
	 */
	public String getChannelType() {
		return channelType;
	}

	/** 
	 * Set the ChannelType.
	 * @param ChannelType The ChannelType to set
	 */
	public void setChannelType(String ChannelType) {
		this.channelType = ChannelType;
	}

    /**
     * @return Returns the cLV.
     */
    public String getCLV() {
        return CLV;
    }
    /**
     * @param clv The cLV to set.
     */
    public void setCLV(String clv) {
        CLV = clv;
    }
}