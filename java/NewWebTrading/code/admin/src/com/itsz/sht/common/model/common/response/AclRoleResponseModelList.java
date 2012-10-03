package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.taifook.adminportal.common.util.page.Page;

public class AclRoleResponseModelList extends AbstractResponseModel {
	
	private Page page;
	
	private List<AclRole> aclRoleList;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<AclRole> getAclRoleList() {
		return aclRoleList;
	}

	public void setAclRoleList(List<AclRole> aclRoleList) {
		this.aclRoleList = aclRoleList;
	}

}
