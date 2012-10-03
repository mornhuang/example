package cn.hxex.hibernate.mapping.model;

import java.util.HashSet;
import java.util.Set;

public class Parent {

	private String id;
	private Set children = new HashSet();
	
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public boolean equals( Object obj ) {
		if( obj==null ) return false;
		
		if( obj instanceof Parent ) {
			Parent p = (Parent)obj;
			if( this.id==null ) {
				return p.getId()==null;
			} else {
				return this.id.equals( p.getId() );
			}
		}
		return false;
	}
	
	public int hashCode() {
		if( id!=null ) return id.hashCode();
		return 0;
	}
}
