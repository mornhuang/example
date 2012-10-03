package cn.hxex.springcore.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext( "cn/hxex/springcore/aop/AOPBeans.xml" );
		
		AOPBean aopBean = (AOPBean)factory.getBean( "aopBean" );
		aopBean.display();
		
	}
}
