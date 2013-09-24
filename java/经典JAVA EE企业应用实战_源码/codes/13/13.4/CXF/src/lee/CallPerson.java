package lee;

import org.crazyit.*;

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
public class CallPerson
{
	public static void main(String[] args) 
	{
		//PersonWS是wsdl2java生成的类，用于简化客户端开发
		PersonWS service = new PersonWS();
		//Person类也是动态生成的类
		Person pws = service.getPersonPort();
		//创建Person_Type(这也是动态生成的类)对象，该对象将会作为实际的参数
		Person_Type person = new Person_Type();
		person.setName("crazyit.org");
		person.setAge(20);
		//将Person_Type对象包装成SayHello对象
		SayHello sh = new SayHello();
		sh.setSomebd(person);
		//调用Web Service操作。
		System.out.println(pws.sayHello(sh).getReturn());
	}
}
