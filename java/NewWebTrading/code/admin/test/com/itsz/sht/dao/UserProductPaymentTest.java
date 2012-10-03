package com.itsz.sht.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itsz.sht.dao.hibernate.model.UserProductPayment;

public class UserProductPaymentTest extends BaseTest{

	public void testaddUserProductAllotment(){
		try {
			UserProductPaymentDao dao=(UserProductPaymentDao) factory.getBean("userProductPaymentDao");
		
//			for (int i = 20; i < 30; i++) {
//				UserProductAllotment upa=new UserProductAllotment();
//				if(i<10){
//					upa.setUsrProdAlltId("usrProdAlltId0"+String.valueOf(i));
//					upa.setProdId("productId0"+String.valueOf(i));
//					upa.setClntId("clientId0"+String.valueOf(i));
//					upa.setPayReqId("payRequestId0"+String.valueOf(i));
//				}else{
//					upa.setUsrProdAlltId("usrProdAlltId"+String.valueOf(i));
//					upa.setProdId("productId"+String.valueOf(i));
//					upa.setClntId("clientId"+String.valueOf(i));
//					upa.setPayReqId("payRequestId"+String.valueOf(i));
//				}
			 List<String> listProduct=new ArrayList<String>();
//			 listProduct.add("SSTR_AAST");
//			 listProduct.add("SSTR_AAST_CN");
			 listProduct.add("SSTR_IQS_CN");
//			 listProduct.add("SHK");
//			 listProduct.add("NO_EMAIL");
//			 listProduct.add("SSTR_IQS");
			// String clientId="";
			 for (int i = 0; i < 51; i++) {
				String clientid="0020"+String.valueOf(i);
				 for (String productId : listProduct) {
					 UserProductPayment upa=new UserProductPayment();
					    upa.setUsrProdPayId(String.valueOf(System.currentTimeMillis())+String.valueOf(i));
					    upa.setClntId(clientid);
					    upa.setProdId(productId);
						//upa.setPaySatus("PENDING");
					    upa.setPaySatus("MEMO");
						upa.setInitBy("yangming");
						upa.setInitDate(new Date());
						upa.setUpdBy("yangming");
						upa.setUpdDate(new Date());
						dao.addUserProductPayment(upa);
				}
			}
			 
			
			
		//	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}