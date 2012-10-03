package com.itsz.sht.service.mcs;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.LoggerFactory;
import com.itsz.sht.common.model.common.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.common.response.VerifyPasswordResponseModel;
import com.itsz.sht.exception.ITSZException;
import com.taifook.mcs.core.beans.msg.Exception01;
import com.taifook.mcs.core.beans.msg.MCSMessage;
import com.taifook.mcs.core.beans.msg.VerifyPasswordRequest;
import com.taifook.mcs.core.beans.msg.VerifyPasswordResponse;
import com.taifook.mcs.msg.MessageSender;
public class McsServiceImpl  implements McsService {

	Log mcsInfo = LoggerFactory.getInstance().getMcsInfo();
    
    private MessageSender messageSender;

	public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
	public VerifyPasswordResponseModel callVerifyPassword(VerifyPasswordRequestModel requestModel) throws ITSZException{
		MCSMessage mes = null;
		VerifyPasswordResponseModel verifyPassResModel = new VerifyPasswordResponseModel();
		VerifyPasswordResponse verifyPassRes =null;
		VerifyPasswordRequest verifyPassRequest = new VerifyPasswordRequest();
        long bt = System.currentTimeMillis();
		try {
			PropertyUtils.copyProperties(verifyPassRequest,requestModel);
			verifyPassRequest.setMessageId(Consts.Mcs.MsgId.VerifyPassword);
			verifyPassRequest.setMessageSEQNum(Long.decode(Consts.Global.Number.SONE));
			mes = messageSender.sendRequest(verifyPassRequest, requestModel.getLanguage());
            LoggerFactory.getInstance().info(bt , verifyPassRequest.getMessageId());
		} catch (Exception ex) {
            LoggerFactory.getInstance().info(bt , verifyPassRequest.getMessageId()+" "+ex.getMessage());
			throw new ITSZException(Consts.Error.Code.CONNECT_MCS, ex.getMessage());
		}
		if (mes instanceof VerifyPasswordResponse) {
			verifyPassRes = (VerifyPasswordResponse) mes;
			verifyPassResModel.setPasswordMatch(verifyPassRes.getPasswordMatch());
		} else if (mes instanceof Exception01) {
			Exception01 cancelorderEx = (Exception01) mes;
			throw new ITSZException(cancelorderEx.getErrorSubCode());
		}
		return verifyPassResModel;
	}
	
}
