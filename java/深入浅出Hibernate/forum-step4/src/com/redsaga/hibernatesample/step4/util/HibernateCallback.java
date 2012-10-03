package com.redsaga.hibernatesample.step4.util;

import org.hibernate.HibernateException;

public interface HibernateCallback {
	Object execute() throws HibernateException;
}
