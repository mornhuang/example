package cn.itsz.newsim.dto.response;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.CalMOSResponseEntity;

public class MosResponse extends ResultMessage {
	private CalMOSResponseEntity calMOSResponse;
    
    public CalMOSResponseEntity getCalMOSResponse() {
		return calMOSResponse;
	}
	public void setCalMOSResponse(CalMOSResponseEntity calMOSResponse) {
		this.calMOSResponse = calMOSResponse;
	}
}
