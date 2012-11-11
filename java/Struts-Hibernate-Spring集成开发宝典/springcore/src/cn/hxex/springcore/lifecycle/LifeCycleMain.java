package cn.hxex.springcore.lifecycle;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class LifeCycleMain {

	public static void main(String[] args) {

		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/lifecycle/LifeCycleBeans.xml");
		XmlBeanFactory factory = new XmlBeanFactory(resource);
		
		factory.getBean( "lifeCycleBean" );

		factory.destroySingletons();
	}

}
