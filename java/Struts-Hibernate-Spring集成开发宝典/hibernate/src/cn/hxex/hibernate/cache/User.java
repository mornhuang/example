package cn.hxex.hibernate.cache;

public class User {
	private String id;
	private String name;
	private Integer age;
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append( "\nID:\t" ).append( getId() );
		buf.append( "\nNamge:\t" ).append( getName() );
		buf.append( "\nAge:\t" ).append( getAge() );
		
		return buf.toString();
	}
}
