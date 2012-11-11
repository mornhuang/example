package cn.hxex.springcore.introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IntroductionMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext( "cn/hxex/springcore/introduction/IntroductionBeans.xml" );
		
		IntroductionBean introductionBean = (IntroductionBean)factory.getBean( "introductionBean" );
		introductionBean.serviceMethod();
		
//		UsageTracked usageTracked = (UsageTracked)factory.getBean( "introductionBean" );
//		usageTracked.display();
	}

}
