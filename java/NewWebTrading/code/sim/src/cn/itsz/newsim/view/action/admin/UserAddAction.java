package cn.itsz.newsim.view.action.admin;


import java.math.BigDecimal;
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
import cn.itsz.newsim.dto.UserProfileModel;
import cn.itsz.newsim.view.action.RegAction;
import cn.itsz.newsim.view.form.RegForm;

@Controller("/nadmin/userAdd")
public class UserAddAction extends RegAction {
	private static String allowTradeStatusFlag="Y";
	private static String transactionProtection="Y";
	
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserProfileModel model = new UserProfileModel();
		form2Model(request, form, model, response);
		facade.addUserProfile(model);
		return mapping.findForward(Constants.Status.SUCCESS);
	}
	
	public static void form2Model(HttpServletRequest request,
			ActionForm form, UserProfileModel destModel, HttpServletResponse response) {
		RegForm model = (RegForm)form;
		destModel.setAddNo(((RegForm)form).getAddNo());
		destModel.setLoginId(model.getLoginId());
		destModel.setUsername(model.getUsername());
		destModel.setPassWord(MD5.Md5(((RegForm)form).getPassWord()));
		destModel.setEmail(model.getEmail());
		destModel.setTelephone(model.getTelephone());
		destModel.setClient(model.getIsClient());
		destModel.setClientNo(((RegForm)form).getClientNo());
		destModel.setClientMoney(new BigDecimal(0).doubleValue());
		destModel.setAllowTradeStatusFlag(allowTradeStatusFlag);
		destModel.setTransactionProtection(transactionProtection);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		destModel.setLastUpdate(sdf.format(new Date()));
	}
	
}
