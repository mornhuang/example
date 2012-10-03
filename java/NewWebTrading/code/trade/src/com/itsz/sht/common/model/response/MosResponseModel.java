package com.itsz.sht.common.model.response;


import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.CalMOSResponse;


public class MosResponseModel extends AbstractResponseModel {
	private CalMOSResponse calMOSResponse;
    
    public CalMOSResponse getCalMOSResponse() {
		return calMOSResponse;
	}
	public void setCalMOSResponse(CalMOSResponse calMOSResponse) {
		this.calMOSResponse = calMOSResponse;
	}
}