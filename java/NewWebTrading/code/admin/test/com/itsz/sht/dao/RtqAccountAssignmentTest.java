package com.itsz.sht.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itsz.sht.dao.hibernate.model.RtqAccAsgnId;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.model.RtqAccountAssDto;
import com.taifook.adminportal.common.util.page.Page;

public class RtqAccountAssignmentTest extends BaseTest {

	public void testaddRtqAccountAssignment() {

		try {
			RtqAccountAssignmentDao dao = (RtqAccountAssignmentDao) factory
					.getBean("rtqAccountAssignmentDao");
			for (int i = 0; i < 50; i++) {
				RtqAccountAssignment ra = new RtqAccountAssignment();
				RtqAccAsgnId id = new RtqAccAsgnId();
				String clntId = "";
				String prodId = "";
				String rtqLognId = "";
				if (i < 10) {
					clntId = "clientId0" + String.valueOf(i);
					prodId = "productId0" + String.valueOf(i);
					rtqLognId = "rtqLoginId0" + String.valueOf(i);
				} else {
					clntId = "clientId" + String.valueOf(i);
					prodId = "productId" + String.valueOf(i);
					rtqLognId = "rtqLoginId" + String.valueOf(i);
				}
				id.setClntId(clntId);
				id.setProdId(prodId);
				id.setRtqLognId(rtqLognId);
				ra.setId(id);
				ra.setRtqLognPwd("000000");
				ra.setInitBy("yangming");
				ra.setInitDate(new Date());
				ra.setUpdBy("yangming");
				ra.setUpdDate(new Date());
				dao.addRtqAccountAssignment(ra);
			}
			System.out.println("sssss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testdeleteRtqAccountAssignment() {

		try {
			RtqAccountAssignmentDao dao = (RtqAccountAssignmentDao) factory
					.getBean("rtqAccountAssignmentDao");
			List<String> clintIds = new ArrayList<String>();
			clintIds.add("productId47");
			clintIds.add("productId48");
			clintIds.add("productId49");
			dao.deleteRtqAccountAssignment(clintIds,"SSTR_AAST");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testgetRtqAccountAssignment() {

		try {
			RtqAccountAssignmentDao dao = (RtqAccountAssignmentDao) factory
					.getBean("rtqAccountAssignmentDao");
			List<RtqAccountAssignment> list = dao.getRtqAccountAssignment(
					"SSTR_AAST", "0077444");
			System.out.println("list===="+list.size());
			if (list != null && list.size() > 0) {
				for (RtqAccountAssignment o : list) {
					System.out.println("RtqLogPwd:" + o.getRtqLognPwd()
							+ " Init By:" + o.getInitBy());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testdeleteRtqAccountAssignmentList() {

		try {
			RtqAccountAssignmentDao dao = (RtqAccountAssignmentDao) factory
					.getBean("rtqAccountAssignmentDao");
			List<RtqAccountAssignment> list = new ArrayList<RtqAccountAssignment>();
			RtqAccountAssignment a1 = new RtqAccountAssignment();
			RtqAccAsgnId id1 = new RtqAccAsgnId();
			id1.setProdId("productId00");
			id1.setRtqLognId("rtqLoginId00");
			a1.setId(id1);
			list.add(a1);
			RtqAccountAssignment a2 = new RtqAccountAssignment();
			RtqAccAsgnId id2 = new RtqAccAsgnId();
			id2.setProdId("productId01");
			id2.setRtqLognId("rtqLoginId01");
			a2.setId(id2);
			list.add(a2);
			RtqAccountAssignment a3 = new RtqAccountAssignment();
			RtqAccAsgnId id3 = new RtqAccAsgnId();
			id3.setProdId("productId02");
			id3.setRtqLognId("rtqLoginId02");
			a3.setId(id3);
			list.add(a3);
			RtqAccountAssignment a4 = new RtqAccountAssignment();
			RtqAccAsgnId id4 = new RtqAccAsgnId();
			id4.setProdId("productId03");
			id4.setRtqLognId("rtqLoginId03");
			a4.setId(id4);
			list.add(a4);
			dao.deleteRtqAccountAssignmentList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testgetRtqAccountByClientId() {

		try {
			RtqAccountAssignmentDao dao = (RtqAccountAssignmentDao) factory
					.getBean("rtqAccountAssignmentDao");
			List<RtqAccountAssignment> list = dao
					.getRtqAccountByClientId("clientId04");
			if (list != null && list.size() > 0) {
				for (RtqAccountAssignment o : list) {
					System.out.println("productId:" + o.getId().getProdId()
							+ " clientId" + o.getId().getClntId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testgetRtqAccountAssignmentByProductId() {

		try {
			RtqAccountAssignmentDao dao = (RtqAccountAssignmentDao) factory
					.getBean("rtqAccountAssignmentDao");
			Page page = dao
					.getRtqAccountAssignmentByProductId("productId04",0,10);
			if(page!=null){
				System.out.println("totail:"+page.getTotalNumberOfElements());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public  void testlistRtqAccountAssByProductId(){
		
		try {
			RtqAccountAssignmentDao dao = (RtqAccountAssignmentDao) factory
			.getBean("rtqAccountAssignmentDao");
			
		List<RtqAccountAssDto>	list=dao.listRtqAccountAssByProductId("SSTR_IQS_CN");
		if(list!=null&&list.size()>0){
		System.out.println("帐号分配情况**********")	;
			for (RtqAccountAssDto r : list) {
			System.out.println("Product ID:"+r.getProductId()+" RtqLogin ID:"+r.getRtqLoginId()+" Rtq Pwd:"+r.getRtqLoginPwd()+" Client ID:"+r.getClientId());
			}
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
