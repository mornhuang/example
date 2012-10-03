package com.itsz.sht.dao;

import java.util.Date;
import java.util.List;

import com.itsz.sht.common.DateUtil;
import com.itsz.sht.dao.ProductDao;
import com.itsz.sht.dao.hibernate.model.Product;

public class ProductTest extends BaseTest {
	
	public void testAdd() {
	try {
		ProductDao dao = (ProductDao) factory.getBean("productDao");
		for (int i = 31; i < 32; i++) {
			String prodocutId="";
			Product	p=new Product();
			if(i<10)
			{prodocutId="productId0"+String.valueOf(i);}
			else
				{
				prodocutId="productId"+String.valueOf(i);
			}
			p.setProdId(prodocutId);
			p.setProdStatus("AVAIL");
			p.setAcesUnit(new Long(1000));
			p.setAlltQuota(new Long(1000));
			p.setBillType("CHRG");
			p.setDiscType("PRC");
			p.setEffDate(new Date());
			p.setExprDate(DateUtil.getNextMonthFirstDateofMonth(new Date()));
			p.setInitBy("yangming");
			p.setInitDate(new Date());
			p.setUpdBy("yangming");
			p.setUpdDate(new Date());
			p.setPriceInHkd(new Long(8));
			p.setQuota(new Long(2000));
			p.setRemarks("这是一个很好的产品，欢迎订购！！！");
		    p.setSvcMode("ABCD");
		    p.setValtOfSvc("MONTHLY");
		    dao.addProduct(p);
		}	
	} catch (Exception e) {
		e.printStackTrace();
	}		
}

	public void testUpdate() {
		try {
			ProductDao dao = (ProductDao) factory.getBean("productDao");
			Product p=new Product();
			p.setProdId("productId01");
			p.setProdStatus("AVAIL");
			p.setAcesUnit(new Long(500));
			p.setAlltQuota(new Long(500));
			p.setBillType("CHRG");
			p.setDiscType("NONE");
			p.setEffDate(new Date());
			p.setExprDate(DateUtil.getNextMonthFirstDateofMonth(new Date()));
			p.setInitBy("yangming001");
			p.setInitDate(new Date());
			p.setUpdBy("yangming001");
			p.setUpdDate(new Date());
			p.setPriceInHkd(new Long(8));
			p.setQuota(new Long(2000));
			p.setRemarks("这是一个很好的产品，欢迎订购！！！");
		    p.setSvcMode("ABCD");
		    p.setValtOfSvc("MONTHLY");
			dao.upateProduct(p);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void testDelete() {
		try {
			ProductDao dao = (ProductDao) factory.getBean("productDao");
			dao.deleteProduct("productId19");
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
  }
	
	public void testGet() {
		try {
			ProductDao dao = (ProductDao) factory.getBean("productDao");
			Product p=dao.getProduct("productId01");
			System.out.println("status:"+p.getProdStatus()+"remarks:"+p.getRemarks());
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
  }
	public void testGetList() {
		try {
			ProductDao dao = (ProductDao) factory.getBean("productDao");
			List<Product> list =dao.getProductList();
			if(list!=null&&list.size()>0){
				System.out.println("size--------"+list.size());
				for (Object object : list) {
					Product p=(Product) object;
					System.out.println("productId:"+p.getProdId()+" status:"+p.getProdStatus()+" remarks:"+p.getRemarks());
				}	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
  }
	
}