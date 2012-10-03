package com.itsz.sht.dao;


import java.util.Date;

import com.itsz.sht.dao.AclRoleFnctnPrmisnDao;
import com.itsz.sht.dao.hibernate.model.AclRoleFnctnPrmisn;

public class AclRoleFnctnPrmisnTest extends BaseTest{
	
	public void testaddAclRole(){
		try {
			AclRoleFnctnPrmisnDao dao = (AclRoleFnctnPrmisnDao) factory.getBean("aclRoleFnctnPrmisnDao");
			for (int i = 0; i < 50; i++) {
				AclRoleFnctnPrmisn aclRole=new AclRoleFnctnPrmisn();
				String aclRoleFnctnPrmisnId=String.valueOf(System.currentTimeMillis());
				String fnctnId="";
				if(i<10){
				//	aclRoleFnctnPrmisnId="rfp0"+String.valueOf(i);
					fnctnId="fctnId0"+String.valueOf(i);
				}else{
				//	aclRoleFnctnPrmisnId="rfp"+String.valueOf(i);
					fnctnId="fctnId"+String.valueOf(i);
				}
				aclRole.setAclRoleFnctnPrmisnId(aclRoleFnctnPrmisnId+String.valueOf(i));
				aclRole.setFnctnId(fnctnId);
				aclRole.setInitBy("yangming");
				aclRole.setInitDate(new Date());
				aclRole.setPrmisnCde("A");
				aclRole.setRoleId("roleyang");
				aclRole.setUpdBy("yangming");
				aclRole.setUpdDate(new Date());
				dao.addAclRoleFuctnPrmisn(aclRole);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
