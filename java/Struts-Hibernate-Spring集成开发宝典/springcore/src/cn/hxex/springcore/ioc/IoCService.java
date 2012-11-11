package cn.hxex.springcore.ioc;

public class IoCService {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void display( ) {
		System.out.println( getMessage() );
	}
}
