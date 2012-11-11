package cn.hxex.blog.struts.ext;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class BaseAction extends Action {

	/**
	 * 增加错误（提示）信息
	 * 
	 * @param request HttpServletRequest实例
	 * @param key 资源的Key值
	 * @param values 资源的参数值
	 */
	public void addMessage(HttpServletRequest request, String key,
			String[] values)
	{
		// 得到ActionMessages对象的实例
		ActionMessages messages = (ActionMessages) request
				.getAttribute(Globals.ERROR_KEY);
		if (messages == null)
			messages = new ActionMessages();

		// 创建ActionMessage对象的实例，并增加到ActionMessages对象的实例中
		if (values != null && values.length > 0)
			messages.add(Globals.ERROR_KEY, new ActionMessage(key, values));
		else
			messages.add(Globals.ERROR_KEY, new ActionMessage(key));
		
		// 将ActionMessages对象的实例绑定到request上
		request.setAttribute(Globals.ERROR_KEY, messages);
	}

	/**
	 * 增加错误（提示）信息
	 * 
	 * @param request HttpServletRequest实例
	 * @param key 资源的Key值
	 */
	public void addMessage(HttpServletRequest request, String key)
	{
		addMessage(request, key, null);
	}
	
}
