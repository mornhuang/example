package com.itsz.sht.admin.service.util;

import java.io.File;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itsz.sht.admin.util.xmlparser;

public class WebAdminCfgParser extends xmlparser {

	public WebAdminCfgParser() throws Exception {
		try{
		if (factory == null) {
			factory = DocumentBuilderFactory.newInstance();
		}
		builder = factory.newDocumentBuilder();
		filename = getFilepath() + "/" + "webAdminCfg.xml";
//		System.out.println("filename==" + filename);
		doc = builder.parse(new File(filename));
		root = doc.getDocumentElement();
		}catch(Exception e){
			throw e;
		}
	}

	public void parser() {
		parserMCS();
		parserES();
		parserQS();
		parserLog();
		parserOther();
	}

	private void parserMCS() {
		NodeList nodeList = root.getElementsByTagName("mcs-config");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			String channelType = getNodeText(getNode(item, "channeltype"));
			String channelID = getNodeText(getNode(item, "channelid"));
			String language = getNodeText(getNode(item, "language"));			
			String accountid = getNodeText(getNode(item, "accountid"));
			String custCode = getNodeText(getNode(item, "custcode"));			
			String loginid = getNodeText(getNode(item, "loginid"));
			String password = getNodeText(getNode(item, "password"));
			String instrumentcode = getNodeText(getNode(item, "instrumentcode"));
			String lotsize = getNodeText(getNode(item, "lotsize"));
			String priceStr = getNodeText(getNode(item, "price"));
			
			BigDecimal price=null;
			try{				
				price = BigDecimal.valueOf(Long.parseLong(priceStr));
			}catch(Exception e){
				price=null;
			}

			
			WebAdminCfgManager manager = WebAdminCfgManager.getInstance();
			manager.addParam("mcs-config", "channeltype", channelType);
			manager.addParam("mcs-config", "channelid", channelID);			
			manager.addParam("mcs-config", "custcode", custCode);
			manager.addParam("mcs-config", "accountid", accountid);
			manager.addParam("mcs-config", "language", language);			
			manager.addParam("mcs-config", "loginid", loginid);
			manager.addParam("mcs-config", "password", password);
			manager.addParam("mcs-config", "instrumentcode", instrumentcode);
			manager.addParam("mcs-config", "lotsize", lotsize);
			manager.addParam("mcs-config", "price", price);
		}
	}

	private void parserES() {

	}

	private void parserQS() {
		NodeList nodeList = root.getElementsByTagName("qs-config");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			String channelType = getNodeText(getNode(item, "channeltype"));
			String language = getNodeText(getNode(item, "language"));
			String custCode = getNodeText(getNode(item, "custcode"));
			String instrumentcode = getNodeText(getNode(item, "instrumentcode"));			
			String delayTime = getNodeText(getNode(item, "delaytime"));

			WebAdminCfgManager manager = WebAdminCfgManager.getInstance();
			manager.addParam("qs-config", "channeltype", channelType);
			manager.addParam("qs-config", "language", language);
			manager.addParam("qs-config", "custCode",custCode);
			manager.addParam("qs-config", "instrumentcode", instrumentcode);						
			manager.addParam("qs-config", "delaytime", delayTime);
		}
	}
	
	private void parserOther(){
		NodeList nodeList = root.getElementsByTagName("other-config");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			String errorCount = getNodeText(getNode(item, "errorcount"));
			String normalAccessTime = getNodeText(getNode(item, "normalaccesstime"));
			String intervalTime = getNodeText(getNode(item, "intervaltime"));
			String priority=getNodeText(getNode(item, "priority"));

			WebAdminCfgManager manager = WebAdminCfgManager.getInstance();
			manager.addParam("other-config", "errorcount", errorCount);
			manager.addParam("other-config", "normalaccesstime", normalAccessTime);
			manager.addParam("other-config", "intervaltime",intervalTime);
			manager.addParam("other-config", "priority",priority);
		}
	}
	
	private void parserLog(){
		NodeList nodeList = root.getElementsByTagName("threadpool-config");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			String miniThreadSize = getNodeText(getNode(item, "miniThreadSize"));
			String maxThreadSize = getNodeText(getNode(item, "maxThreadSize"));			
			String aliveTime = getNodeText(getNode(item, "aliveTime"));

			WebAdminCfgManager manager = WebAdminCfgManager.getInstance();
			manager.addParam("threadpool-config", "miniThreadSize", miniThreadSize);
			manager.addParam("threadpool-config", "maxThreadSize", maxThreadSize);
			manager.addParam("threadpool-config", "aliveTime", aliveTime);	
		}
	}
	
}
