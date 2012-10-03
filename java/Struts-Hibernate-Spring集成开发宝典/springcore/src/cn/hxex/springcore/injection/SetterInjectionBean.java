package cn.hxex.springcore.injection;

public class SetterInjectionBean {

	private AnotherBean anotherBean;
	private YetAnotherBean yetAnotherBean;
	private int i;
	
	public AnotherBean getAnotherBean() {
		return anotherBean;
	}
	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public YetAnotherBean getYetAnotherBean() {
		return yetAnotherBean;
	}
	public void setYetAnotherBean(YetAnotherBean yetAnotherBean) {
		this.yetAnotherBean = yetAnotherBean;
	}
	
	public void display() {
		System.out.println( "SetterInjectionBean:" );
		System.out.println( this.anotherBean );
		System.out.println( this.yetAnotherBean );
		System.out.println( i );
	}
}
