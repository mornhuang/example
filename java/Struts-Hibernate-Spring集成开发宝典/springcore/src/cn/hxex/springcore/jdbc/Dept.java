package cn.hxex.springcore.jdbc;

public class Dept {

	private Integer deptNo;
	private String dName;
	private String loc;
	
	public Integer getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
	public String getDName() {
		return dName;
	}
	public void setDName(String name) {
		dName = name;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append( "deptNo:\t" ).append( getDeptNo() ).append( "\t" );
		buf.append( "dName:\t" ).append( getDName() ).append( "\t" );
		buf.append( "loc:\t" ).append( getLoc() ).append( "\t" );
		
		return buf.toString();
	}
}
