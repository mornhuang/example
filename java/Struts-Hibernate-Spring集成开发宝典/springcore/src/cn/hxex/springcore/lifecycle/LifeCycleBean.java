package cn.hxex.springcore.lifecycle;

/**
 * 测试初始化方法和销毁方法的Bean
 */
public class LifeCycleBean {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 初始化方法
	 */
	public void initialize() {
		System.out.println( "Initialize method..." );
		System.out.println( getMessage() );
	}
	
	/**
	 * 销毁方法
	 */
	public void cleanup() {
		System.out.println( "Destroy method..." );
	}

}
