package cn.itsz.newsim.view.process;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itsz.newsim.common.ActionUtil;
import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.ResultMessage;


@Controller
public class DefaultViewProcess implements ViewProcess {
	protected static final Log log = LogFactory.getLog(DefaultViewProcess.class);
	
	public ActionForward process(ResultMessage result, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");
			PrintWriter writer = response.getWriter();
			String resultStr = ActionUtil.Obj2JSONString(result);
			writer.print(resultStr);
			writer.flush();
			log.info(resultStr);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	public ActionForward processException(ResultMessage result, HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, String clv) {
		if (Constants.Sync.SYNC.equals(ActionUtil.getSync(clv))) {
			request.setAttribute("result", result);
			return mapping.findForward(result.getReturnCode());
		} else {
			return process(result, response);
		}
	}

}
