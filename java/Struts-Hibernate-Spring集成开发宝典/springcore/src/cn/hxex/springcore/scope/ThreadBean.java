package cn.hxex.springcore.scope;

/*
 * 自定义作用域的测试Bean
 */
public class ThreadBean {

	// 状态属性
	private int status = 0;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void display() {

		StringBuffer buf = new StringBuffer();
		buf.append( "Thread " ).append( Thread.currentThread().getId() );
		buf.append( " Status:\t" ).append( getStatus() );
		System.out.println( buf.toString() );
	
	}
}
