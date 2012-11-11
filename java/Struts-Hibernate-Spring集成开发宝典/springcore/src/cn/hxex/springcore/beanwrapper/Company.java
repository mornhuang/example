package cn.hxex.springcore.beanwrapper;


public class Company {

	private String name;
    private Employee managingDirector;
    private String[] addresses = new String[3];
    
	public String[] getAddresses() {
		return addresses;
	}
	public void setAddresses(String[] addresses) {
		this.addresses = addresses;
	}
	public Employee getManagingDirector() {
		return managingDirector;
	}
	public void setManagingDirector(Employee managingDirector) {
		this.managingDirector = managingDirector;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append( "Company information:\n" );
		buf.append( "name=" ).append( getName() ).append( "\n" );
		buf.append( "Addresses:\n" );
		for( int i=0; i<addresses.length; i++ ) {
			buf.append( addresses[i] ).append( "\n" );
		}
		buf.append( "Managing Director:\n" );
		buf.append( getManagingDirector() );
		
		return buf.toString();
	}
}
