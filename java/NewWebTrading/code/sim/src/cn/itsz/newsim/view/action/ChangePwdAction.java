package cn.itsz.newsim.view.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.MD5;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.ChangePwdRequest;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.view.form.ChangePwdForm;

@Controller("/changePwd")
public class ChangePwdAction extends BaseAction {
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}

	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ChangePwdRequest changePwdRequest = new ChangePwdRequest();
		form2Model(request, form, changePwdRequest, response);
		ResultMessage resultMessage = new ResultMessage();
		resultMessage = facade.changPwd(changePwdRequest);
		if (resultMessage.getReturnCode().equals(Constants.Status.NORMAL)) {
			((LoginResponseEntity) (request.getSession().getAttribute("user")))
					.setPassword(MD5.Md5(changePwdRequest.getPassWord()));
		}
		request.setAttribute("resultStatusCPWD", resultMessage.getReturnCode());
		return mapping.findForward(resultMessage.getReturnCode());

	}

	private static void form2Model(HttpServletRequest request, ActionForm form,
			ChangePwdRequest destModel, HttpServletResponse response) {
		destModel.setLoginId(((LoginResponseEntity) (request.getSession()
				.getAttribute("user"))).getLoginId());
		destModel.setPassWord(((ChangePwdForm) form).getPassWord());
		destModel.setOldPassWrod(((ChangePwdForm) form).getOldPassword());
	}

}
