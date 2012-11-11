package cn.hxex.springcore.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class IoCMain {

	public static void main(String[] args) {
		
		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/ioc/IoCBeans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		IoCService service = (IoCService)factory.getBean( "iocService" );
		service.display();
	}
}
