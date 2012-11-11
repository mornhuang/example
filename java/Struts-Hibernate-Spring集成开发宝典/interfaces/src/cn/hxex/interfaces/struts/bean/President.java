package cn.hxex.interfaces.struts.bean;

public class President {
	
	private String lastName;
	private String firstName;
	private String term;
	
	public President( String lname, String fname, String term )
	{
		this.lastName = lname;
		this.firstName = fname;
		this.term = term;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}

}
