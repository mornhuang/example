package cn.hxex.springcore.lookup;

import java.util.Calendar;

public class CurrentTime {

	private Calendar now = Calendar.getInstance();
	
	public void printCurrentTime() {
		System.out.println( "Current Time:" + now.getTime() );
	}
}
