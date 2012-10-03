/**
 * 
 */
package com.itsz.sht.dao.hibernate.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cyzeng
 *
 */
public class CsParameter implements Serializable {
	
	// Fields
	private static final long serialVersionUID = 1L;
	private String keyPk;
	private String value;
	private String classId;
	private String dataType;
	private Long readonly;
	private String description;
	private Date updateTime;
	
	// Constructors
	public CsParameter() {
	}
	
	public CsParameter(String keyPk) {
		super();
		this.keyPk = keyPk;
	}

	public CsParameter(String keyPk, String value, String classId,
			String dataType, Long readonly, String description, Date updateTime) {
		super();
		this.keyPk = keyPk;
		this.value = value;
		this.classId = classId;
		this.dataType = dataType;
		this.readonly = readonly;
		this.description = description;
		this.updateTime = updateTime;
	}

	// Property accessors
	public String getKeyPk() {
		return keyPk;
	}

	public void setKeyPk(String keyPk) {
		this.keyPk = keyPk;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getReadonly() {
		return readonly;
	}

	public void setReadonly(Long readonly) {
		this.readonly = readonly;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
