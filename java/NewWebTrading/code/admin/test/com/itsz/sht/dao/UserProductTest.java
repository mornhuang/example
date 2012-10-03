package com.itsz.sht.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itsz.sht.common.DateUtil;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UsrProdId;
import com.itsz.sht.model.ReserveAndRenewalEnquiry;
import com.taifook.adminportal.common.util.page.Page;

public class UserProductTest extends BaseTest {

	 public void testaddUserProduct() {
	
	 try {
		 //创建自动续期数据
	 UserProductDao dao = (UserProductDao) factory.getBean("userProductDao");
	 List<String> listProduct=new ArrayList<String>();
	 listProduct.add("SSTR_AAST");
//	 listProduct.add("SSTR_AAST_CN");
//	 listProduct.add("SSTR_IQS_CN");
//	 listProduct.add("SHK");
//	 listProduct.add("NO_EMAIL");
//	 listProduct.add("SSTR_IQS");
	// String clientId ="clntId20";
	 for (int i = 0; i < 51; i++) {
		String clientId="0100"+String.valueOf(i);
		for (String productId : listProduct) {
			 UserProduct up = new UserProduct();
			 UsrProdId id = new UsrProdId();
			 id.setClntId(clientId);
			 id.setProdId(productId);
			 up.setId(id);
			 up.setAllwRenl("Y");
			 up.setAcesUnitExpr("100");
			 up.setAcesUnitTotal("1000");
			 up.setAcesUnitUsed("500");
			 up.setExprDate(DateUtil.getBeforeMonthLastDateofMonth((new Date())));
			 up.setInitBy("yangming");
			 up.setInitDate(new Date());
			 up.setStatus("AVAIL");
			 up.setRemarks("这个月月底到期！！");
			 up.setUpdBy("yangming");
			 up.setUpdDate(new Date());
			 dao.addUserProduct(up);
		   }
	}
	 
	 
	 
	 }    catch (Exception e) {
	        e.printStackTrace();
	 }
	 }

	public void testupdateUserProduct() {

		try {
		UserProductDao dao = (UserProductDao) factory
					.getBean("userProductDao");
//			 List<String> listProduct=new ArrayList<String>();
//			 listProduct.add("SSTR_AAST");
//			 listProduct.add("SSTR_AAST_CN");
//			 listProduct.add("SSTR_IQS_CN");
//			 listProduct.add("SHK");
//			 listProduct.add("NO_EMAIL");
//			 listProduct.add("SSTR_IQS");
//			 String clientId="clntId20";
	//		 for (String productId : listProduct) {
					UserProduct up = new UserProduct();
					UsrProdId id = new UsrProdId();
					id.setClntId("0000054");
					id.setProdId("SSTR_IQS");
					up.setId(id);
					up.setExprDate(DateUtil.getThisMonthLastDateofMonth(new Date()));
					up.setInitBy("yangming333");
					up.setUpdBy("yangming333");
					up.setUpdDate(new Date());
			 	   up.setInitBy("yangming333");
					dao.updateUserProduct(up);	
		//	}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 public void testfindUserProductByClientId(){
			
	 try {
	   UserProductDao dao=(UserProductDao) factory.getBean("userProductDao");
	 List<UserProduct> list= dao.findUserProductByClientId("clientId01");
	 if(list!=null&&list.size()>0){
		 for (UserProduct up : list) {
			System.out.println("Init By:"+up.getInitBy());
		}
	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	
		
	 public void testfindUserProductByProductId(){
			
	 try {
	 UserProductDao dao=(UserProductDao) factory.getBean("userProductDao");
	 List<UserProduct> list= dao.findUserProductByProductId("productId100");
	 if(list!=null&&list.size()>0){
		 for (UserProduct up : list) {
			System.out.println("Init By:"+up.getInitBy());
		}
	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	
		
	 public void testfindAvailUserProduct(){
			
	 try {
	 UserProductDao dao=(UserProductDao) factory.getBean("userProductDao");
	 List<UserProduct> list= dao.findAvailUserProduct("clientId03");
	 if(list!=null&&list.size()>0){
		 for (UserProduct up : list) {
			System.out.println("Init By:"+up.getInitBy());
		}
	 }else{
		 System.out.println(" list is null!!!");
	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	
		
	 public void testfindUserProductByClientId1(){
			
	 try {
	 UserProductDao dao=(UserProductDao) factory.getBean("userProductDao");
	 List<UserProduct> list=dao.findUserProductByClientId("clientId101", new Date());
	 if(list!=null&&list.size()>0){
	 for (UserProduct up : list) {
		System.out.println("Init By:"+up.getInitBy());
	}
 }else{
	 System.out.println(" list is null!!!");
 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	
		
	 public void testgetExistingUserProductByClientId(){
			
	 try {
	 UserProductDao dao=(UserProductDao) factory.getBean("userProductDao");
	 List<UserProduct> list= dao.getExistingUserProductByClientId("clientId101", false);
	 if(list!=null&&list.size()>0){
		 for (UserProduct up : list) {
			System.out.println("Init By:"+up.getInitBy());
		}
	 }else{
		 System.out.println(" list is null!!!");
	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	
	 public void testfindAccountRequiredThisMonth(){
			
	 try {
	 UserProductDao dao=(UserProductDao) factory.getBean("userProductDao");
	 List<UserProduct> list= dao.findAccountRequiredThisMonth("productId05");
	 if(list!=null&&list.size()>0){
		 for (UserProduct up : list) {
			System.out.println("Init By:"+up.getInitBy());
		}
	 }else{
		 System.out.println(" list is null!!!");
	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	//
	// public void test(){
	//		
	// try {
	// UserProductDao dao=(UserProductDao) factory.getBean("userProductDao");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	 public void testfindReserveAndRenewalEnquiry(){
		 
		 try {
			 UserProductDao dao=(UserProductDao) factory.getBean("userProductDao");
			 Page page=dao.findReserveAndRenewalEnquiry(1, 10, null);
			 if(page!=null)
			 {
		    	List list=	(List) page.getThisPageElements();
			    if(list!=null&&list.size()>0){
				 System.out.println("list size="+list.size()); 
				 for(int a=0;a<list.size();a++){
					 Object[] obj=(Object[]) list.get(a);
					 System.out.println("clientId:"+obj[0].toString()+" prodcutId:"+obj[1]);
				 }
			 }else{
				 System.out.println(" list is null!!!");
			 }
			 }
			 } catch (Exception e) {
			 e.printStackTrace();
	   }
	 }
}
