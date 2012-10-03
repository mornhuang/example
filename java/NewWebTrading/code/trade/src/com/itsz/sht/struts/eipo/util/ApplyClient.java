package com.itsz.sht.struts.eipo.util;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itsz.eipo.webservice.ApplyWebService;
import com.itsz.eipo.webservice.EIPOResponse;
import com.itsz.eipo.webservice.IpoSubscriptionDto;

public class ApplyClient {

	private ClassPathXmlApplicationContext context;
	private ApplyWebService applyWebService;
	private static ApplyClient applyClient;

	private ApplyClient(long timeout) {
		context = new ClassPathXmlApplicationContext(new String[] { "applyClient-beans.xml" });
		applyWebService = (ApplyWebService) context.getBean("eipoApplyWebService");
		Client client = ClientProxy.getClient(applyWebService);
		if (client != null) {
			HTTPConduit conduit = (HTTPConduit) client.getConduit();
			HTTPClientPolicy policy = new HTTPClientPolicy();
			policy.setConnectionTimeout(timeout);
			policy.setReceiveTimeout(timeout);
			conduit.setClient(policy);
		}
	}

	public static ApplyClient getInstance(long timeout) {
		if (applyClient == null) {
			applyClient = new ApplyClient(timeout);
		}
		return applyClient;
	}

	public EIPOResponse enquirySuscription(String accountNo,String language){
		return applyWebService.enquirySuscription(accountNo,language);
	}
	
	public EIPOResponse enquiryIpoMasterDetail (String accountNo,String ipoId,
			String classCode,String language){
		return applyWebService.enquiryIpoMasterDetail(accountNo,ipoId,classCode,language);
	}
	
	public EIPOResponse addIpoSubscription(IpoSubscriptionDto ipoSubscriptionDto){
		return applyWebService.addIpoSubscription(ipoSubscriptionDto);
	}
	
	public EIPOResponse cancelIpoSubscription(String subscriptionId){
		return applyWebService.cancelIpoSubscription(subscriptionId);
	}
	
	public EIPOResponse enquiryAllIposByLang(String accountNo,String language){
		return applyWebService.enquiryAllIposByLang(accountNo,language);
	}
	
	public EIPOResponse enquiryIpoSubscriptionById(String subscriptionId,String language) {
		return applyWebService.enquiryIpoSubscriptionById(subscriptionId,language);
	}
}
