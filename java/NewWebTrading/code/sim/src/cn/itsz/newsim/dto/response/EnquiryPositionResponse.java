package cn.itsz.newsim.dto.response;

import java.util.Collection;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.ShareHoldingInfo;

public class EnquiryPositionResponse extends ResultMessage {
	private Collection<ShareHoldingInfo> shareHoldingInfoCol;

	public Collection<ShareHoldingInfo> getShareHoldingInfoCol() {
		return shareHoldingInfoCol;
	}

	public void setShareHoldingInfoCol(Collection<ShareHoldingInfo> shareHoldingInfoCol) {
		this.shareHoldingInfoCol = shareHoldingInfoCol;
	}
}
