package cn.hxex.springcore.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class InjectionMain {

	public static void main(String[] args) {

		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/injection/InjectionBeans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		ConstructInjectionBean constructInjectionBean = (ConstructInjectionBean)factory.getBean( "constructInjectionBean" );
		constructInjectionBean.display();

		SetterInjectionBean setterInjectionBean = (SetterInjectionBean)factory.getBean( "setterInjectionBean" );
		setterInjectionBean.display();
	}

}
