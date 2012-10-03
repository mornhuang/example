package com.itsz.sht.dao;



import java.util.Date;

import com.itsz.sht.dao.AclRoleDao;
import com.itsz.sht.dao.hibernate.model.AclRole;

public class AclRoleTest extends BaseTest {

	public void testaddAclRole(){
		AclRoleDao dao = (AclRoleDao) factory.getBean("aclRoleDao");
		for (int i = 0; i <30; i++) {
			AclRole role=new AclRole();
			String roleId="";
			if(i<10){
				roleId="roleId0"+String.valueOf(i);
			}else{
				roleId="roleId"+String.valueOf(i);
			}
			role.setRoleId(roleId);
			role.setDescr("role!!!");
			role.setInitBy("yangming");
			role.setInitDate(new Date());
			role.setUpdBy("yangming");
			role.setUpdDate(new Date());
			dao.addAclRole(role);
			
		}
		
	}
}
