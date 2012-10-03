package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.AclRole;

public class AclRoleResponseModel  extends AbstractResponseModel{
	
	private AclRole aclRole;

	public AclRole getAclRole() {
		return aclRole;
	}

	public void setAclRole(AclRole aclRole) {
		this.aclRole = aclRole;
	}

}
