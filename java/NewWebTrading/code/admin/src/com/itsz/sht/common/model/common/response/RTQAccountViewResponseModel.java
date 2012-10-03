package com.itsz.sht.common.model.common.response;



import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.model.RtqAccountView;
public class RTQAccountViewResponseModel  extends AbstractResponseModel{
 
	private List<RtqAccountView> rtqAccountViewList;

	public List<RtqAccountView> getRtqAccountViewList() {
		return rtqAccountViewList;
	}

	public void setRtqAccountViewList(List<RtqAccountView> rtqAccountViewList) {
		this.rtqAccountViewList = rtqAccountViewList;
	}

}
