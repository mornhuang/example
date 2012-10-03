package com.taifook.common.socket;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.taifook.adminportal.common.XMLParser;

public class SynDataCfgParser extends XMLParser {
	private static Log log = null;

	public SynDataCfgParser() throws Exception {
		try {
			log = LogFactory.getLog(this.getClass());
			if (factory == null) {
				factory = DocumentBuilderFactory.newInstance();
			}
			builder = factory.newDocumentBuilder();
			filename = this.getFilepath() + "/" + "syndatasrvcfg.xml";
//			System.out.println("filename==" + filename);
			doc = builder.parse(new File(filename));
			root = doc.getDocumentElement();
		} catch (Exception e) {
			log.error((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
					.format(new Date())
					+ "======Processing Context configuration file URL file:"
					+ filename + " fail");
			e.printStackTrace();
		}
	}

	public void parserNotifyServerCfg() {
		log.info("======Processing Context configuration file URL file:"
				+ filename);
		SynDataCfgManager manager = SynDataCfgManager.getInstance();
		NodeList nodesList = root.getElementsByTagName("socket-config");

		if (nodesList != null) {			
			for (int i = 0; i < nodesList.getLength(); i++) {
				SynDataCfgInfo cfgInfo = new SynDataCfgInfo();
				
				Node item = nodesList.item(i);
				String serviceName = item.getAttributes().getNamedItem(
						"servicename").getNodeValue();
				String role = item.getAttributes().getNamedItem(
					"role").getNodeValue();
				String ip = getNodeText(getNode(item, "server_ip"));
				String portStr = getNodeText(getNode(item, "server_port"));
				String timeoutStr=getNodeText(getNode(item, "timeout"));
				String to_channel=getNodeText(getNode(item, "to_channel"));
				
				serviceName=serviceName==null ? "" : serviceName;
				
				to_channel=to_channel==null ? "" : to_channel;
				
				role=role==null ? "" : role;
				
				int port=0;
				try{
					port=(portStr==null ? 0 : Integer.parseInt(portStr));
				}catch(NumberFormatException e){
					port=0;
				}
				
				int timeout=0;
				try{
					timeout=(timeoutStr==null ? 0 : Integer.parseInt(timeoutStr));
				}catch(NumberFormatException e){
					timeout=0;
				}				
				
				cfgInfo.setRole(role);
				cfgInfo.setServiceName(serviceName);
				cfgInfo.setIp(ip);
				cfgInfo.setPort(port);
				cfgInfo.setTimeout(timeout);
				cfgInfo.setTo_channel(to_channel);
				
				manager.addConfig(cfgInfo);
			}
		}

	}
}
