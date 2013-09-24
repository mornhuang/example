package lee;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.tool.hbm2ddl.*;

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
public class NewsManager
{
	public static void main(String[] args) throws Exception
	{
		//实例化Configuration，这行代码默认加载hibernate.cfg.xml文件
		Configuration conf = new Configuration().configure();
		//以Configuration创建SessionFactory
		SessionFactory sf = conf.buildSessionFactory();
//		//创建SchemaExport对象
//		SchemaExport se = new SchemaExport(conf);
//		//设置输出格式良好的SQL脚本
//		se.setFormat(true)
//			//设置保存SQL脚本的文件名
//			.setOutputFile("new.sql")
//			//输出SQL脚本，并执行SQL脚本
//			.create(true, true);
	}
}
