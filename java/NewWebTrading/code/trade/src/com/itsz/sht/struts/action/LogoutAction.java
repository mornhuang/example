//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.UserManagement;
import com.itsz.sht.listener.SessionManagement;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/** 
 * MyEclipse Struts
 * Creation date: 05-30-2005
 * 
 * XDoclet definition:
 * @struts:action
 * @struts:action-forward name="xhtml_success" path="/"
 */
public class LogoutAction extends ITSZAction {
    static UserManagement userManagement=new UserManagement();
	public boolean isLoginActionExecuted(){
		return true;
	}
    /*
     * (non-Javadoc)
     * 
     * @see com.itsz.web.ITSZAction#executeAction(com.itsz.view.provider.ViewProvider,
     *      org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
    	
		if(transValidate(request.getParameter("invalid"))){
			HttpSession session=request.getSession();
			SessionManagement.removeUserObject(session);
			if(session.getAttribute(Constants.USER)!=null){
				session.invalidate();
			}
		}
		ProcessBean processBean = new ProcessBean(null,null, mapping, request,response);
        return vp.processLogout(processBean);
    }
    
    private boolean transValidate(String invalid){
    	//invalid used by webrevamp only
    	return invalid==null || "TRUE".equals(invalid);
    }
}