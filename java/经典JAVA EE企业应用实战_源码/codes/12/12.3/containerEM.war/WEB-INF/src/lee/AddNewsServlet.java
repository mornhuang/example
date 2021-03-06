package lee;

import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.annotation.*;
import javax.transaction.*;
import javax.naming.*;

import org.crazyit.model.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
//为JPA持久化单元配置一个引用，指定引用名为newsUnit。
@PersistenceContext(name="newsUnit" , unitName="newsUnit")
public class AddNewsServlet extends HttpServlet
{
	//依赖注入容器管理的JTA事务
	@Resource
	private UserTransaction tx;	
	public void service(HttpServletRequest request
		, HttpServletResponse response)
		throws IOException , ServletException
	{
		request.setCharacterEncoding("GBK");
		//获取请求参数
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//创建实体对象
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		try
		{
			tx.begin();
			//通过JNDI查找获取EntityManager
			Context ctx = new InitialContext();
			EntityManager em = (EntityManager)ctx.lookup("java:/comp/env/"
				+ "newsUnit");
			//持久化News实体
			em.persist(news);
			tx.commit();
			PrintStream out = new PrintStream(response.getOutputStream());
			out.println("<h3>消息添加成功！</h3>");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}