package com.amaker.entity;
/**
 * 
 * @author 郭宏志
 * 机动车违章信息采集实体类
 */
public class VehicleFaultInfo {
	private int id;
	private String name;
	private String idno;
	private String createTime;
	private String license;
	private String faultRecord;
	private double penalty;
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFaultRecord() {
		return faultRecord;
	}
	public void setFaultRecord(String faultRecord) {
		this.faultRecord = faultRecord;
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
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	
}
