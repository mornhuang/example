package cn.hxex.strutsext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public abstract class BaseAction extends Action {

	public final ActionForward execute(ActionMapping mapping, 
			ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
			throws Exception {
		try {
			return doExecute( mapping, form, request, response );
		} catch ( Exception ex ) {
			return doException( ex, mapping, form, request, response );
		}
	}

	/**
	 * 异常处理方法
	 */
	public ActionForward doException(Exception ex,
			ActionMapping mapping, 
			ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response) {
		// 对异常进行处理
		
		return null;  // 返回页面
	}

	/**
	 * Action处理方法
	 */
	public abstract ActionForward doExecute(ActionMapping mapping, 
			ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
			throws Exception;
}
