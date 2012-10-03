package cn.itsz.newsim.dto.request;

public class AdminChangePwdRequest {
	private String uname;
	private String upass;
	private String newUpass;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getNewUpass() {
		return newUpass;
	}
	public void setNewUpass(String newUpass) {
		this.newUpass = newUpass;
	}
}
