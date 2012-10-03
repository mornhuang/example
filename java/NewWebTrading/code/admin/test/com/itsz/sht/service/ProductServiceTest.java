package com.itsz.sht.service;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.AccessSHKRequestModel;
import com.itsz.sht.common.model.common.request.UserProductRequestModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.AccessSHKResponseModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.service.channels.ProductService;

import junit.framework.TestCase;

public class ProductServiceTest extends TestCase {

	private ProductService upService;

	protected void setUp() throws Exception {
		super.setUp();
		ClassPathResource resource = new ClassPathResource("userproduct.xml");
		XmlBeanFactory factory =  new XmlBeanFactory(resource);
		this.upService = (ProductService) factory.getBean("productService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.upService = null;
	}
	 
	public void testAccessRTQ() {
		AccessRTQRequestModel requestModel = new AccessRTQRequestModel();
		requestModel.setClientId("0901605");
		requestModel.setClientIp("127.0.0.1");
		AccessRTQResponseModel responseModel = upService.accessRTQ(requestModel);
		System.out.println(responseModel.getRtqAccess().getRtqUrl());

	}

	public void testAccessSHK() {
		AccessSHKRequestModel requestModel = new AccessSHKRequestModel();
		requestModel.setClientId("0901605");
		requestModel.setProductId("SHK");
		AccessSHKResponseModel responseModel = upService.accessSHK(requestModel);
		System.out.println(responseModel.getRtqStatus());
	}
}
