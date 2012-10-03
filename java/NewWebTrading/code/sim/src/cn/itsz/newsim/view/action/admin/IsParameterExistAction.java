package cn.itsz.newsim.view.action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.dao.hibernate.model.ParameterModel;
import cn.itsz.newsim.view.action.BaseAction;

@Controller("/nadmin/isParamExist")
public class IsParameterExistAction extends BaseAction {
	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}
	
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	String pname = request.getParameter("pname");
    	try {	PrintWriter out= response.getWriter();
        	response.setContentType("text/plain;charset=UTF-8");
        	ParameterModel param = new ParameterModel();
        	param.setPname(pname);
        	boolean val=facade.isParameterExist(param);

        	out.write(String.valueOf(val));
        	out.close();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
    	return null;
    }
}
