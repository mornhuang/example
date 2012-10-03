package cn.hxex.springcore.beanwrapper;

import java.util.HashMap;
import java.util.Iterator;

public class Employee {

	private float salary;
    private HashMap telephones = new HashMap();

	public HashMap getTelephones() {
		return telephones;
	}

	public void setTelephones(HashMap telephones) {
		this.telephones = telephones;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append( "salary=" ).append( getSalary() ).append( "\n" );
		buf.append( "telephones:\n" );
		Iterator it = getTelephones().keySet().iterator();
		while( it.hasNext() ) {
			Object key = it.next();
			Object value = telephones.get( key );
			buf.append( key ).append( "=" ).append( value ).append( "\n" );
		}
		
		return buf.toString();
	}
}
