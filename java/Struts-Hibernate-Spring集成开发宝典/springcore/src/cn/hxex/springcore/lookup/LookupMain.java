package cn.hxex.springcore.lookup;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class LookupMain {

	public static void main(String[] args) throws Exception {

		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/lookup/LookupBeans.xml");
		XmlBeanFactory factory = new XmlBeanFactory(resource);
		
		LookupBean lookupBean = (LookupBean)factory.getBean( "lookupBean" );
		System.out.println( "======First Time======" );
		System.out.println( "getCurrentTime:" );
		lookupBean.getCurrentTime().printCurrentTime();
		System.out.println( "createCurrentTime£º" );
		lookupBean.createCurrentTime().printCurrentTime();
		Thread.sleep( 12345 );
		System.out.println( "======Second Time======" );
		System.out.println( "getCurrentTime:" );
		lookupBean = (LookupBean)factory.getBean( "lookupBean" );
		lookupBean.getCurrentTime().printCurrentTime();
		System.out.println( "createCurrentTime£º" );
		lookupBean.createCurrentTime().printCurrentTime();
	}

}
