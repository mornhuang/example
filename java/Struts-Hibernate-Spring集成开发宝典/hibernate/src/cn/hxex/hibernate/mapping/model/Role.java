package cn.hxex.hibernate.mapping.model;

import java.util.HashSet;
import java.util.Set;

public class Role {

	private String id;
	private Set users = new HashSet();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set getUsers() {
		return users;
	}
	public void setUsers(Set users) {
		this.users = users;
	}
}
