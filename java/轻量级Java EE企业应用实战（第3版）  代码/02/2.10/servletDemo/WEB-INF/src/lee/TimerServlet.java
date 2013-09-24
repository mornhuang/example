package lee;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
	
@WebServlet(loadOnStartup=1)
public class TimerServlet extends HttpServlet
{
	public void init(ServletConfig config)throws ServletException
	{
		super.init(config);
		Timer t = new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(new Date());
			}
		});
		t.start();
	}
}