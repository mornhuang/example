package cn.hxex.hibernate.mapping.model;


public class Child {

	private String id;
	private Parent parent;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public boolean equals( Object obj ) {
		if( obj==null ) return false;
		
		if( obj instanceof Child ) {
			Child c = (Child)obj;
			if( this.id==null ) {
				return c.getId()==null;
			} else {
				return this.id.equals( c.getId() );
			}
		}
		return false;
	}
	public int hashCode() {
		if( id!=null ) return id.hashCode();
		return 0;
	}
}
