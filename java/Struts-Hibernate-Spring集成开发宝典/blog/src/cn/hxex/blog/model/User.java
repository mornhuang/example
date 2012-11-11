package cn.hxex.blog.model;

import java.util.Set;


public class User extends BaseModel {

	/**
	 * The Generated SerialVersionUID
	 */
	private static final long serialVersionUID = -6892646236097552692L;
	
	private 	String 	name;
	private 	String 	password;
	private 	Set 	messages;

	public Set getMessages() {
		return messages;
	}
	public void setMessages(Set messages) {
		this.messages = messages;
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
}
