package com.redsaga.hibnatesample.step2.util;

import net.sf.hibernate.HibernateException;

public interface HibernateCallback {
	Object execute() throws HibernateException;
}
