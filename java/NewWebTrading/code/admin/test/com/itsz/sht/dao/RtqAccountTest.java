package com.itsz.sht.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itsz.sht.dao.RtqAccountDao;
import com.itsz.sht.dao.hibernate.model.RtqAccId;
import com.itsz.sht.dao.hibernate.model.RtqAccount;

public class RtqAccountTest extends BaseTest {

	 public void testAdd(){
	 try {
	 RtqAccountDao dao = (RtqAccountDao) factory.getBean("rtqAccountDao");
	 for (int i = 60; i < 70; i++) {
	 RtqAccount rtq=new RtqAccount();
	 RtqAccId id=new RtqAccId();
	 String productId="";
	 String rtqLoginId="";
	 if(i<10){
	 productId="productId0"+String.valueOf(i);
	 rtqLoginId="rtqLoginId0"+String.valueOf(i);
	 }
	 else{
	 productId="productId"+String.valueOf(i);
	 rtqLoginId="rtqLoginId"+String.valueOf(i);
	 }
	 id.setProdId(productId);
	 id.setRtqLognId(rtqLoginId);
	 rtq.setId(id);
	 rtq.setRtqLognPwd("000000");
	 rtq.setInitDate(new Date());
	 rtq.setInitBy("yangming");
	 rtq.setUpdBy("yangming");
	 rtq.setUpdDate(new Date());
	 dao.addRtqAccount(rtq);
	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }

	 public void testUpdate(){
			
	 try {
	 RtqAccountDao dao = (RtqAccountDao) factory.getBean("rtqAccountDao");
	 RtqAccount rtq=new RtqAccount();
	 RtqAccId id=new RtqAccId();
	 id.setProdId("productId01");
	 id.setRtqLognId("rtqLoginId01");
	 rtq.setId(id);
	 rtq.setInitBy("yangming");
	 rtq.setInitDate(new Date());
	 rtq.setRtqLognPwd("2222");
	 rtq.setUpdBy("yangming222");
	 dao.updateRtqAccount(rtq);
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }

	 public void testGet(){
	 RtqAccountDao dao = (RtqAccountDao) factory.getBean("rtqAccountDao");
	 RtqAccount rtq= dao.getRtqAccount("productId02", "rtqLoginId02");
	 if(rtq!=null){
	 System.out.println("rtqLogPwd:"+rtq.getRtqLognPwd()+" InitBy:"+rtq.getInitBy());
	 }
	 }

	 public void testgetRtqAccountByProductId(){
	 RtqAccountDao dao = (RtqAccountDao) factory.getBean("rtqAccountDao");
	 List<RtqAccount> rtqList=dao.getRtqAccountByProductId("productId04");
	 if(rtqList!=null&&rtqList.size()>0){
	 System.out.println("size---------"+rtqList.size());
	 for (RtqAccount rtq : rtqList) {
	 System.out.println("rtqLogId:"+rtq.getId().getRtqLognId()+" rtqLogPwd:"+rtq.getRtqLognPwd()+" InitBy:"+rtq.getInitBy());
	 }
				
	 }
	 }

	 public void testaddRtqAccountList() {
	 try {
	 RtqAccountDao dao = (RtqAccountDao) factory
	 .getBean("rtqAccountDao");
	 List<RtqAccount> list = new ArrayList<RtqAccount>();
	 for (int i = 50; i < 70; i++) {
	 RtqAccount rtq = new RtqAccount();
	 RtqAccId id = new RtqAccId();
	 String productId = "";
	 String rtqLoginId = "";
	 if (i < 10) {
	 productId = "productId0" + String.valueOf(i);
	 rtqLoginId = "rtqLoginId0" + String.valueOf(i);
	 } else {
	 productId = "productId" + String.valueOf(i);
	 rtqLoginId = "rtqLoginId" + String.valueOf(i);
	 }
	 id.setProdId(productId);
	 id.setRtqLognId(rtqLoginId);
	 rtq.setId(id);
	 rtq.setRtqLognPwd("000000");
	 rtq.setInitDate(new Date());
	 rtq.setInitBy("yangming");
	 rtq.setUpdBy("yangming");
	 rtq.setUpdDate(new Date());
	 list.add(rtq);
	 }
	 dao.addRtqAccountList(list);
	 } catch (Exception e) {
	 e.printStackTrace();
	 }

	 }

	 public void testdeleteRtqAccountList() {
	 try {
	 RtqAccountDao dao = (RtqAccountDao) factory
	 .getBean("rtqAccountDao");
	 List<RtqAccount> list = new ArrayList<RtqAccount>();
	 for (int i = 50; i < 70; i++) {
	 RtqAccount rtq = new RtqAccount();
	 RtqAccId id = new RtqAccId();
	 String productId = "";
	 String rtqLoginId = "";
	 if (i < 10) {
	 productId = "productId0" + String.valueOf(i);
	 rtqLoginId = "rtqLoginId0" + String.valueOf(i);
	 } else {
	 productId = "productId" + String.valueOf(i);
	 rtqLoginId = "rtqLoginId" + String.valueOf(i);
	 }
	 id.setProdId(productId);
	 id.setRtqLognId(rtqLoginId);
	 rtq.setId(id);
	 rtq.setRtqLognPwd("000000");
	 rtq.setInitDate(new Date());
	 rtq.setInitBy("yangming");
	 rtq.setUpdBy("yangming");
	 rtq.setUpdDate(new Date());
	 list.add(rtq);
	 }
	 dao.deleteRtqAccountList(list);
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	
	 }

	 public void testgetRtqAccountMountByProductId(){
			
	 RtqAccountDao dao = (RtqAccountDao) factory.getBean("rtqAccountDao");
	 Long mount=dao.getRtqAccountMountByProductId("productId04");
	 System.out.println("productId04 size----"+mount);
	 }

	public void testupdateRtqAccountList() {
		RtqAccountDao dao = (RtqAccountDao) factory.getBean("rtqAccountDao");
		List<RtqAccount> list = new ArrayList<RtqAccount>();
		for (int i = 40; i < 50; i++) {
			RtqAccount rtq = new RtqAccount();
			RtqAccId id = new RtqAccId();
			String productId = "";
			String rtqLoginId = "";
			if (i < 10) {
				productId = "productId0" + String.valueOf(i);
				rtqLoginId = "rtqLoginId0" + String.valueOf(i);
			} else {
				productId = "productId" + String.valueOf(i);
				rtqLoginId = "rtqLoginId" + String.valueOf(i);
			}
			id.setProdId(productId);
			id.setRtqLognId(rtqLoginId);
			rtq.setId(id);
			rtq.setRtqLognPwd("222222");
			rtq.setInitDate(new Date());
			rtq.setInitBy("mingyang");
			rtq.setUpdBy("mingyang");
			rtq.setUpdDate(new Date());
			list.add(rtq);

		}
		dao.updateRtqAccountList(list);
	}
	
	
	public void testdeleteLastNRtqAccountByProductId(){
		try {
			RtqAccountDao dao = (RtqAccountDao) factory.getBean("rtqAccountDao");
			System.out.print(dao.deleteLastNRtqAccountByProductId("SSTR_AAST", new Long(10)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
