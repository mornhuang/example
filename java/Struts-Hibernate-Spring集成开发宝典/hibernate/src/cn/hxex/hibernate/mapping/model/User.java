package cn.hxex.hibernate.mapping.model;

import java.util.HashSet;
import java.util.Set;

public class User {

	private String id;
	private Set roles = new HashSet();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set getRoles() {
		return roles;
	}
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	
}
