package cn.itsz.newsim.dto.response;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.RegResponseEntity;

public class RegResponse extends ResultMessage {
	private RegResponseEntity regResponseEntity;

	public RegResponseEntity getRegResponseEntity() {
		return regResponseEntity;
	}

	public void setRegResponseEntity(RegResponseEntity regResponseEntity) {
		this.regResponseEntity = regResponseEntity;
	}
	
}
