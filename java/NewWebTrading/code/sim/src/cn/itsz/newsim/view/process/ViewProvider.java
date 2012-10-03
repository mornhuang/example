package cn.itsz.newsim.view.process;

import org.apache.struts.action.ActionForward;


public interface ViewProvider {
	public ActionForward processLogin(ProcessBean processBean);
	public ActionForward processReg(ProcessBean processBean);
}
