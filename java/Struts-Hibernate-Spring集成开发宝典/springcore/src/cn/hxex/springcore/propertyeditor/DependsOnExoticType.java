package cn.hxex.springcore.propertyeditor;

public class DependsOnExoticType {

	private ExoticType type;

	public ExoticType getType() {
		return type;
	}

	public void setType(ExoticType type) {
		this.type = type;
	}
	
	public void display() {
		System.out.println( type.toString() );
	}
}
