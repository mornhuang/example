package cn.hxex.springcore.factorybean;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.FactoryBean;

public class CurrentDateFactory implements FactoryBean {

	public Object getObject() throws Exception {
		Calendar rightNow = Calendar.getInstance();
		return rightNow.getTime();
	}

	public Class getObjectType() {
		return Date.class;
	}

	public boolean isSingleton() {
		return false;
	}

}
