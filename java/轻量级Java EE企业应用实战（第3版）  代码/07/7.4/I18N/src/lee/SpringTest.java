package lee;import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;import java.util.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SpringTest 
{
	public static void main(String[] args)throws Exception
	{
		//实例化ApplicationContext
		ApplicationContext ctx = new
			ClassPathXmlApplicationContext("bean.xml");
		//创建参数数组
		String[] a = {"读者"};
		//使用getMessage方法获取本地化消息。Locale的getDefault方法
		//返回计算机环境的默认Locale
		String hello = ctx.getMessage("hello" , a,Locale.getDefault());
		Object[] b = {new Date()};
		String now = ctx.getMessage("now" , b,Locale.getDefault());
		//打印出两条本地化消息
		System.out.println(hello);
		System.out.println(now);
	}
}