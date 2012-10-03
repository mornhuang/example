package cn.hxex.springcore.scope;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 测试自定义作用域的主程序
 */
public class ThreadMain {

	public static BeanFactory factory;

	static {
		// IoC容器的初始化
		ClassPathResource resource = new ClassPathResource("cn/hxex/springcore/scope/ThreadBeans.xml");
		factory = new XmlBeanFactory(resource);
		// 注册自定义的作用域
		((XmlBeanFactory)factory).registerScope( "thread", new ThreadScope() );
	}
	
	/**
	 * 主程序：启动多个线程的业务对象
	 */
	public static void main(String[] args) {

		for( int i=0; i<5; i++ ) {
			ThreadService service = new ThreadService();
			service.start();
		}

	}

}
