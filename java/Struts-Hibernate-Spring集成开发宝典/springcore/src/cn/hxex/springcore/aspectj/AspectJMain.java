package cn.hxex.springcore.aspectj;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectJMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext( "cn/hxex/springcore/aspectj/AspectJBeans.xml" );
		
		AspectJBean aspectJBean = (AspectJBean)factory.getBean( "aspectJBean" );
		aspectJBean.display();
		
	}
}
