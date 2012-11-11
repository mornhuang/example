package cn.hxex.order.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.RequestProcessor;

public class HxexRequestProcessor extends RequestProcessor
{
	protected final Log log = LogFactory.getLog(HxexRequestProcessor.class);

	protected void processLocale( HttpServletRequest request, HttpServletResponse response )
	{
		super.processLocale( request, response );
        try
        {
                request.setCharacterEncoding( "utf-8" );
        }
        catch( Exception ex )
        {
        }
	}
}
