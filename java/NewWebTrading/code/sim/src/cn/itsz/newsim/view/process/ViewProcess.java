package cn.itsz.newsim.view.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.itsz.newsim.dto.ResultMessage;


public interface ViewProcess {
	public ActionForward process(ResultMessage result, HttpServletResponse response);
	public ActionForward processException(ResultMessage result, HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, String clv);
}
