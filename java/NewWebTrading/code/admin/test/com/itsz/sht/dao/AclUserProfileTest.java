package com.itsz.sht.dao;

import java.util.Date;

import com.itsz.sht.dao.AclUserProfileDao;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;

public class AclUserProfileTest extends BaseTest {
	
public void testaddAclUserProfile(){
	AclUserProfileDao dao = (AclUserProfileDao) factory.getBean("aclUserProfileDao");
	for (int i = 0; i <30; i++) {
		AclUserProfile aup=new AclUserProfile();
		String loginId="";
		String roleId="";
		if(i<10){
			loginId="loginId0"+String.valueOf(i);
			roleId="roleId0"+String.valueOf(i);
		}else{
			loginId="loginId"+String.valueOf(i);
			roleId="roleId"+String.valueOf(i);

		}
		aup.setLognId(loginId);
		aup.setRoleId(roleId);
		aup.setDeptNme("ITSZ");
		aup.setEmailAddr("abc@163.com");
		aup.setPwd("00000");
		aup.setUsrNme("yangming");
		aup.setStatus("Y");
		aup.setInitBy("yangming");
		aup.setInitDate(new Date());
		aup.setUpdBy("yangming");
		aup.setUpdDate(new Date());
		dao.addAclUserProfile(aup);
		
	}
	
}
}
