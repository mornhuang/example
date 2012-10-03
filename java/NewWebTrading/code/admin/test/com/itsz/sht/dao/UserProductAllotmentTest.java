package com.itsz.sht.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itsz.sht.common.DateUtil;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;

public class UserProductAllotmentTest extends BaseTest{
	
	public void testaddUserProductAllotment(){
		try {
			UserProductAllotmentDao dao=(UserProductAllotmentDao) factory.getBean("userProductAllotmentDao");
		
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
					 UserProductAllotment upa=new UserProductAllotment();
					    upa.setUsrProdAlltId(String.valueOf(System.currentTimeMillis())+String.valueOf(i));
					    upa.setClntId(clientid);
					    upa.setProdId(productId);
						upa.setAcesUnitExpr("100");
						upa.setAcesUnitTotal("2000");
						upa.setAcesUnitUsed("300");
					//	upa.setAlltStatus("RESERVE");
						upa.setAlltStatus("SUCC");
						upa.setAlltTime(DateUtil.getThisMonthLastDateofMonth(new Date()));
						upa.setEffDate(DateUtil.getThisMonthFirstDateofMonth(new Date()));
						upa.setExpDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
						upa.setInitBy("yangming");
						upa.setInitDate(DateUtil.getThisMonthFirstDateofMonth(new Date()));
						upa.setRemarks("备注备注备注");
						upa.setUpdBy("yangming");
						upa.setUpdDate(new Date());
						dao.addUserProductAllotment(upa);
				}
			}
			 
			
			
		//	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testdeleteUserProductAllotment(){
		try {
			UserProductAllotmentDao dao=(UserProductAllotmentDao) factory.getBean("userProductAllotmentDao");
			dao.deleteUserProductAllotment("usrProdAlltId12");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testgetUserProductAllotment(){
		try {
			UserProductAllotmentDao dao=(UserProductAllotmentDao) factory.getBean("userProductAllotmentDao");
			UserProductAllotment upa=	dao.getUserProductAllotment("usrProdAlltId11");
			if(upa!=null){
			System.out.println(	"ProductId:"+upa.getProdId()+" Init By:"+upa.getInitBy());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testupdateUserProductAllotment(){
		try {
			UserProductAllotmentDao dao=(UserProductAllotmentDao) factory.getBean("userProductAllotmentDao");
			UserProductAllotment upa=new UserProductAllotment();
			upa.setUsrProdAlltId("usrProdAlltId11");
			upa.setInitBy("yangming1111");
			dao.updateUserProductAllotment(upa);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void testfindMemoDebitList(){
		try {
			UserProductAllotmentDao dao=(UserProductAllotmentDao) factory.getBean("userProductAllotmentDao");
		 List<UserProductAllotment> list=  dao.findMemoDebitList();
		 if(list!=null&&list.size()>0){
			 System.out.println("size-----"+list.size());
			 for (UserProductAllotment upa : list) {
				System.out.println("Id--"+upa.getUsrProdAlltId());
			}
		 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	 public void testfindRenewalList(){
		try {
			UserProductAllotmentDao dao=(UserProductAllotmentDao) factory.getBean("userProductAllotmentDao");
			 List<UserProductAllotment> list= dao.findReserveList();
			 if(list!=null&&list.size()>0){
				 System.out.println("size-----"+list.size());
				 for (UserProductAllotment upa : list) {
					System.out.println("Id--"+upa.getUsrProdAlltId());
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
   }
	
}