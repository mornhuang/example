package com.itsz.sht.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;
import com.itsz.sht.vp.ps.STT_Constants;


public class OutputResponse extends HttpServlet {

	private static Log log_info = LogFactory.getLog(Constants.LOG_INFO_COMMON);
	private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    public void init() throws ServletException {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
			//DataOutputStream out=null;
        response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();	
        try {
            String responseBuffer = null;
            //out = new DataOutputStream(response.getOutputStream());
            responseBuffer = request.getAttribute(STT_Constants.MESSAGE).toString();
	    	final int MAX_OUT_DATA_LENGTH = 30000;
	    	do {
				int length=responseBuffer.length();
	    		if (length <= MAX_OUT_DATA_LENGTH) {
					//out.writeUTF(responseBuffer);
	    		    out.println(responseBuffer);
					break;
	    		} else {
					//out.writeUTF(responseBuffer.substring(0,MAX_OUT_DATA_LENGTH));
	    		    out.println(responseBuffer.substring(0,MAX_OUT_DATA_LENGTH));
					responseBuffer = responseBuffer.substring(MAX_OUT_DATA_LENGTH);
	    		}
	    	} while(true);
           
            out.flush();
            log_info.info(responseBuffer);
            log_info.info("[outPutServlet Finish]");

            responseBuffer = null;
        }
        catch (Exception e) {
//            e.printStackTrace();
            log_info.error("exception exist:"+e.getMessage());
        }
        finally { 
        	if(out!=null)
			out.close();
        	}
    }

    public void destroy() {

    }
	
}
