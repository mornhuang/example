package cn.hxex.springcore.beanwrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanWrapperMain {

	public static void main(String[] args) {
		
		Company c = new Company();
		BeanWrapper bwComp = new BeanWrapperImpl(c);
		
		// 设置字符串的值
		bwComp.setPropertyValue( "name", "Surecomp China" );
		// 设置数组类型属性的值
		bwComp.setPropertyValue( "addresses[0]", "Address0" );
		bwComp.setPropertyValue( "addresses[1]", "Address1" );
		bwComp.setPropertyValue( "addresses[2]", "Address2" );
		
		bwComp.setPropertyValue( "managingDirector", new Employee() );
		
		// 设置HashMap的值
		bwComp.setPropertyValue( "managingDirector.telephones[home]", "11111111" );
		bwComp.setPropertyValue( "managingDirector.telephones[mobile]", "22222222" );

		// 设置数字值
		bwComp.setPropertyValue( "managingDirector.salary", "80000.00" );

		System.out.println( c.toString() );
	}

}
