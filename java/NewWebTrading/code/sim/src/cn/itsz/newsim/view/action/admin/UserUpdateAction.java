package cn.itsz.newsim.view.action.admin;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.MD5;
import cn.itsz.newsim.dto.UserProfileModel;
import cn.itsz.newsim.dto.response.UserProfileResponse;
import cn.itsz.newsim.view.action.BaseAction;
import cn.itsz.newsim.view.form.RegForm;

@Controller("/nadmin/userUpdate")
public class UserUpdateAction extends BaseAction {
	private static String allowTradeStatusFlag="Y";
	private static String transactionProtection="Y";
	private String M_PASSWORD="abcdefg1";
	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}
	
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserProfileModel model = new UserProfileModel();
		form2Model(request, form, model, response);
		//如果用户没有修改密码
		String pwd=((RegForm)form).getPassWord();
		if(M_PASSWORD.equals(pwd)){
			UserProfileResponse userResponse = facade.enquireUserProfile(model.getLoginId());
			List<UserProfileModel> userList = userResponse.getUserProfiles();
			if (userList.size() > 0) {
				UserProfileModel user = userList.get(0);
				model.setPassWord(user.getPassWord());
			}
		}
		boolean b = facade.updateUserProfile(model);
		try {	
			PrintWriter out= response.getWriter();
        	response.setContentType("text/plain;charset=UTF-8");
        	out.write(b ? "true" : "false");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    	return null;
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
		
	}
}
