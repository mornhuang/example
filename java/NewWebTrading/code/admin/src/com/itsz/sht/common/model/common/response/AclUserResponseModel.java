package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;

public class AclUserResponseModel extends AbstractResponseModel {
	
	private AclUserProfile aclUserProfile;

	public AclUserProfile getAclUserProfile() {
		return aclUserProfile;
	}

	public void setAclUserProfile(AclUserProfile aclUserProfile) {
		this.aclUserProfile = aclUserProfile;
	}
	

}
