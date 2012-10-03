package cn.hxex.springcore.construct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class ConstructMain {

	public static void main(String[] args) {

		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/construct/ConstructBeans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		ConstructBean constructBean = (ConstructBean)factory.getBean( "constructBean" );
		constructBean.display();

		StaticFactoryBean staticFactoryBean = (StaticFactoryBean)factory.getBean( "staticFactoryBean" );
		staticFactoryBean.display();
		
		DynamicFactoryBean dynamicFactoryBean = (DynamicFactoryBean)factory.getBean( "dynamicFactoryBean" );
		dynamicFactoryBean.display();
		
		
	}

}
