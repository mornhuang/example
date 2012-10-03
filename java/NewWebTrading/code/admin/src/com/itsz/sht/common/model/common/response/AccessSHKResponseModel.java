package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;



public class AccessSHKResponseModel  extends AbstractResponseModel{

	 private String productId;
	 private String rtqProvider;
	 private String rtqUrl;
	 private String rtqStatus;
	 
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRtqProvider() {
		return rtqProvider;
	}
	public void setRtqProvider(String rtqProvider) {
		this.rtqProvider = rtqProvider;
	}
	public String getRtqUrl() {
		return rtqUrl;
	}
	public void setRtqUrl(String rtqUrl) {
		this.rtqUrl = rtqUrl;
	}
	public String getRtqStatus() {
		return rtqStatus;
	}
	public void setRtqStatus(String rtqStatus) {
		this.rtqStatus = rtqStatus;
	} 
	
}
