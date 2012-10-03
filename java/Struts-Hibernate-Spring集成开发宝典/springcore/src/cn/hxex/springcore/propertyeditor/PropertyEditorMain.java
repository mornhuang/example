package cn.hxex.springcore.propertyeditor;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class PropertyEditorMain {

	public static void main(String[] args) {
		
		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/propertyeditor/PropertyEditorBeans.xml");
		XmlBeanFactory factory = new XmlBeanFactory(resource);

		CustomEditorConfigurer  config = (CustomEditorConfigurer)factory.getBean("customEditorConfigurer");
		config.postProcessBeanFactory( factory );
		
		DependsOnExoticType dependsOnExoticType = (DependsOnExoticType)factory.getBean( "dependsOnExoticType" );
		dependsOnExoticType.display();
	}
}
