package cn.itsz.newsim.view.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.dto.UserProfileModel;

@Controller("/isUserExist")
public class IsUserExistAction extends BaseAction {
	
	@Override
	protected boolean isRequiredLogon() {
		return false;
	}
	
	@Override
	protected ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	String loginid=request.getParameter("loginId");
    	try 
        {	PrintWriter out= response.getWriter();
        	response.setContentType("text/plain;charset=UTF-8");
        	UserProfileModel userProfileModel = new UserProfileModel();
        	userProfileModel.setLoginId(loginid);
        	boolean val=facade.isUserExist(userProfileModel);
        	
        	if(val)
        	{
        		//out.println("true");
        		out.write("true");
        	}
        	else
        	{
        		//out.println("false");
        		out.write("false");
        	}
        	out.close();
        } 
        catch (Exception e) 
        {
        	
        	e.printStackTrace();
        }
    	return null;
    }
}
