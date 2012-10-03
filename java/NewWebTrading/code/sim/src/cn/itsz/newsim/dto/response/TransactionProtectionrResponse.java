package cn.itsz.newsim.dto.response;

import cn.itsz.newsim.dto.ResultMessage;

public class TransactionProtectionrResponse extends ResultMessage{
	private String transactionProtectionStatus;

	public String getTransactionProtectionStatus() {
		return transactionProtectionStatus;
	}

	public void setTransactionProtectionStatus(String transactionProtectionStatus) {
		this.transactionProtectionStatus = transactionProtectionStatus;
	}


 
}
