package com.itsz.sht.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

import com.itsz.sht.common.Constants;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.vp.ps.STT_Constants;


/**
 * �쳣��Ϣ���ؽӿ�
 * ��session�л�ÿͻ�ʹ�õ����ԡ�
 * ����session����Ӧ��locale����ʹ��ͻ��˻����Ӧ��������Ϣ
 * session.setAttribute("org.apache.struts.action.LOCALE",servletContainerLocale);
 *
 */
public class OutputException  extends HttpServlet {
    private static Log log_info = LogFactory.getLog(Constants.LOG_INFO_COMMON);
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
	private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
	MessageResources mes = MessageResources.getMessageResources("com/itsz/sht/properties/ApplicationResourcesSTT");
    public void init() throws ServletException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log_debug.info("action --> output exception ");
        ITSZException exception = (ITSZException) request
                .getAttribute(STT_Constants.ITSZ_EXCEPTION);
        String errorCode = null;
        if (exception != null) {
            errorCode = exception.getErrorCode();
        }
        //DataOutputStream out = null;
        response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();	
        //out = new DataOutputStream(response.getOutputStream());
        String exMsg = "";
        try {
            exMsg = mes.getMessage(errorCode);
            //out.writeUTF(exMsg);
            out.println(exMsg);
            out.flush();
            log_debug.info(exMsg);
        } catch (Exception e) {
            log_debug.info(e.getMessage(), e);
        } finally {
            if (out != null)
                out.close();
        }
    }
    public void destroy() {

    }      
}