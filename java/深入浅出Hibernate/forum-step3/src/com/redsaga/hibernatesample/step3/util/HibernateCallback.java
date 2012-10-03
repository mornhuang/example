package com.redsaga.hibernatesample.step3.util;

import net.sf.hibernate.HibernateException;

public interface HibernateCallback {
	Object execute() throws HibernateException;
}
