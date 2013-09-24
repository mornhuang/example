package lee;

import org.springframework.context.*;
import org.springframework.context.support.*;

import org.crazyit.app.service.*;
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
	public static void main(String[] args)
	{
		//以类加载路径下的配置文件创建ClassPathResource实例
		ApplicationContext ctx = new 
			ClassPathXmlApplicationContext("bean.xml");
		Being b1 = ctx.getBean("dog" , Being.class);
		b1.testBeing();
		Being b2 = ctx.getBean("cat" , Being.class);
		b2.testBeing();
	}
}