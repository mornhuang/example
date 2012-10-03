package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.TProtectionRequset;
import cn.itsz.newsim.dto.response.TransactionProtectionrResponse;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.TransactionProtectionForm;

@Controller("/webTP")
public class ChangeTProtectionAction extends BaseAction {

	@Override
	protected boolean isRequiredLogon() {
		return false;
	}
	
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TProtectionRequset protectionRequset=new TProtectionRequset();
		protectionRequset.setLoginId(((LoginResponseEntity)(request.getSession().getAttribute("user"))).getLoginId());
		protectionRequset.setTransactionProtection(((TransactionProtectionForm)form).getTransactionProtection().toString());
		if(((TransactionProtectionForm)form).getPassword()!=null){
			protectionRequset.setPassword(((TransactionProtectionForm)form).getPassword());
		}
		ResultMessage resultMessage=facade.updateTransactionProtection(protectionRequset);
		if(Constants.Status.NORMAL.equals(resultMessage.getReturnCode())){
			String transactionProtection=(((TransactionProtectionrResponse)resultMessage).getTransactionProtectionStatus());
			((LoginResponseEntity)request.getSession().getAttribute("user")).setTransactionProtection(transactionProtection);
			((TransactionProtectionrResponse)resultMessage).setTransactionProtectionStatus(transactionProtection);
			
		}else{
			resultMessage.setErrorCode(Constants.ErrorKey.WEB_051090);
			resultMessage.setReturnCode(Constants.Status.WARN);
		}
		return viewProcess.process(resultMessage, response);
	}

}
