package com.taifook.adminportal.common.right;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.taifook.adminportal.common.XMLParser;

public class RightsParser extends XMLParser {

	public RightsParser() throws Exception {
		if (factory == null) {
			factory = DocumentBuilderFactory.newInstance();
		}
		builder = factory.newDocumentBuilder();
		filename =  this.getFilepath()+"/"+"rightRoles.xml";
//		System.out.println("filename==" + filename);
		doc = builder.parse(new File(filename));
		root = doc.getDocumentElement();
	}

	public List parser() {
		NodeList nodesList = root.getElementsByTagName("right");
		List rightsList = new ArrayList();
		
		if (nodesList != null) {
			for (int i = 0; i < nodesList.getLength(); i++) {
				Node item = nodesList.item(i);
				String name = item.getAttributes().getNamedItem("name")
						.getNodeValue();
				String action = getNodeText(getNode(item, "action"));
				String roles = getNodeText(getNode(item, "roles"));
				rightsList.add(new Right(name, action, roles));
			}
		}
		return rightsList;
	}

	public static void main(String[] args) {
		try {
			(new RightsParser()).parser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
