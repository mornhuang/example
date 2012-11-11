package cn.hxex.basic.model;

public class User {

	private 	String 	id;
	private 	String 	name;
	private 	String 	password;

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

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append( "id=" ).append( id );
		buf.append( " name=" ).append( name );
		buf.append( " password=" ).append( password );
		return buf.toString();
	}
}
