package com.itsz.sht.model;

import com.itsz.sht.dao.hibernate.model.UsrProdId;

public class UserProductRequest{
	private UsrProdId id;
	private String allwRenl;
	private String status;
	private String updBy;

	public UsrProdId getId() {
		return id;
	}

	public void setId(UsrProdId id) {
		this.id = id;
	}

	public String getAllwRenl() {
		return allwRenl;
	}

	public void setAllwRenl(String allwRenl) {
		this.allwRenl = allwRenl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}


}
