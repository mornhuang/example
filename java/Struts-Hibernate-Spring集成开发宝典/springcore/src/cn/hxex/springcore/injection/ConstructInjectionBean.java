package cn.hxex.springcore.injection;

public class ConstructInjectionBean {

	private AnotherBean anotherBean;
	private YetAnotherBean yetAnotherBean;
	private int i;
	
	public ConstructInjectionBean( AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {
		this.anotherBean = anotherBean;
		this.yetAnotherBean = yetAnotherBean;
		this.i = i;
	}
	
	public void display() {
		System.out.println( "ConstructInjectionBean:" );
		System.out.println( this.anotherBean );
		System.out.println( this.yetAnotherBean );
		System.out.println( i );
	}
}
