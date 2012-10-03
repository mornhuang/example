package cn.hxex.strutsext.plugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

public class MyPlugIn implements PlugIn {

	private String pattern;
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void destroy() {

		DateFormat df = new SimpleDateFormat( getPattern() );
		
		Calendar rightNow = Calendar.getInstance();
		Date now = rightNow.getTime();
		System.out.println( "The service shutdown at:" + df.format( now ) );
		
	}

	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		
		DateFormat df = new SimpleDateFormat( getPattern() );
		
		Calendar rightNow = Calendar.getInstance();
		Date now = rightNow.getTime();
		System.out.println( "The service start at:" + df.format( now ) );
	}

}
