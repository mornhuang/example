package cn.hxex.springcore.jdbc;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class JdbcDaoSupportMain {

	public static void main(String[] args) {
		
		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/jdbc/JdbcDaoSupportBeans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);

		IDeptDao dao = (IDeptDao)factory.getBean( "deptDao" );
		List depts = dao.getDepts();
		for( int i=0; i<depts.size(); i++ ) {
			System.out.println( depts.get( i ) );
		}
	}
}
