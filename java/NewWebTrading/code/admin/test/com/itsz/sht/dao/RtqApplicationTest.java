package com.itsz.sht.dao;

import java.util.Date;
import java.util.List;

import com.itsz.sht.dao.RtqApplicationDao;
import com.itsz.sht.dao.hibernate.model.RtqApplication;

public class RtqApplicationTest extends BaseTest {

	public void testAdd() {
		try {
			RtqApplicationDao dao = (RtqApplicationDao) factory
					.getBean("rtqApplicationDao");
			for (int i = 0; i < 20; i++) {
				RtqApplication rtq = new RtqApplication();
				if (i < 10) {
					rtq.setProdId("productId0" + String.valueOf(i));
				}
				rtq.setProdId("productId" + String.valueOf(i));
				rtq.setRtqPrdr("HaiTong");
				rtq.setRtqUrl("www.htsec.com");
				rtq.setRtqStatus("AVAIL");
				rtq.setInitBy("yangming");
				rtq.setInitDate(new Date());
				rtq.setUpdBy("yangming");
				rtq.setUpdDate(new Date());
				dao.addRtqApplication(rtq);
			}
		} catch (Exception e) {

		}
	}
	
	public void testlistRTQApplication(){
		RtqApplicationDao dao = (RtqApplicationDao) factory
		.getBean("rtqApplicationDao");
		List list=dao.listRTQApplication();
		System.out.println("*****"+list.size());
	}
}
