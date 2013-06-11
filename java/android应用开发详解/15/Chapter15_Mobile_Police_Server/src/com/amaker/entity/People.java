package com.amaker.entity;

/**
 * 
 * @author 郭宏志
 * 实体类对应数据库escapedpeopletbl表结构
 */
public class People {
	private int id;
	private String name;
	private int age;
	private String gender;
	private String address;
	private String idno;
	private String crimerecord;
	private String pic;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCrimerecord() {
		return crimerecord;
	}

	public void setCrimerecord(String crimerecord) {
		this.crimerecord = crimerecord;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	/**
	 * 覆盖toString()方法返回People信息字符串
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		
		sb.append("身份证号：");
		sb.append(idno);
		sb.append("\n");
		
		sb.append("姓名：");
		sb.append(name);
		sb.append("\n");
		
		sb.append("性别：");
		sb.append(gender);
		sb.append("\n");
		
		sb.append("年龄：");
		sb.append(age);
		sb.append("\n");
		
		sb.append("籍贯：");
		sb.append(address);
		sb.append("\n");
		
		sb.append("犯罪记录：");
		sb.append(crimerecord);
		sb.append("\n");
		
		return sb.toString();
	}

}
