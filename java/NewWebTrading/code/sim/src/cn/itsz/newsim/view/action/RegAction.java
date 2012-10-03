package cn.itsz.newsim.view.action;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.MD5;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.RegRequest;
import cn.itsz.newsim.view.form.RegForm;

@Controller("/reg")
public class RegAction extends BaseAction {
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	
	private static String allowTradeStatusFlag="Y";
	private static String transactionProtection="Y";
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
		// TODO Auto-generated method stub
		RegRequest regRequest=new RegRequest();
		form2Model(request, form, regRequest, response);
		ResultMessage regResponse=facade.register(regRequest);
		if(regResponse.getReturnCode()==Constants.Status.NORMAL||Constants.Status.NORMAL.equals(regResponse.getReturnCode())){
			request.setAttribute("regStatus", Constants.Status.SUCCESS);
			return mapping.findForward(Constants.Status.SUCCESS);
		}else if(regResponse.getReturnCode()==Constants.Status.FAILED||Constants.Status.FAILED.equals(regResponse.getReturnCode())){
			request.setAttribute("regStatus", Constants.Status.FAILED);
			return mapping.findForward(Constants.Status.FAILED);
		}else {
			request.setAttribute("regStatus", Constants.Status.WARN);
			return mapping.findForward(Constants.Status.WARN);
			}
		
		
	}

	public static void form2Model(HttpServletRequest request,
			ActionForm form, RegRequest destModel, HttpServletResponse response) {
		destModel.setAddNo(((RegForm)form).getAddNo());
		destModel.setClient(((RegForm)form).getIsClient());
		destModel.setClientNo(((RegForm)form).getClientNo());
		destModel.setEmail(((RegForm)form).getEmail());
		destModel.setLoginId(((RegForm)form).getLoginId());
		destModel.setPassWord(MD5.Md5(((RegForm)form).getPassWord()));
		destModel.setTelephone(((RegForm)form).getTelephone());
		destModel.setUsername(((RegForm)form).getUsername());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		destModel.setLastUpdate(sdf.format(new Date()));
		destModel.setClientMoney(0);
		destModel.setAllowTradeStatusFlag(allowTradeStatusFlag);
		destModel.setTransactionProtection(transactionProtection);
	}
}
