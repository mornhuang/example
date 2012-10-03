package com.itsz.sht.admin.parameter.util;

import java.io.File;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itsz.sht.admin.service.ParameterMgrService;
import com.itsz.sht.admin.util.xmlparser;
import com.taifook.adminportal.model.CsParameter;

public class ImportXml2DB extends xmlparser {

	private ImportXml2DB() throws Exception {
		if (factory == null) {
			factory = DocumentBuilderFactory.newInstance();
		}
		builder = factory.newDocumentBuilder();
		filename = getFilepath() + "/" + "parameter.xml";
		filename =  "conf/production/" + "parameter.xml";
		System.out.println("filename==" + filename);
		doc = builder.parse(new File(filename));
		root = doc.getDocumentElement();
	}

	private void importParameter() {
		NodeList classes = root.getElementsByTagName("class");
		if (classes == null) {
			System.out.println("none parameter in the " + this.filename);
			return;
		}
		int count = 0;
		for (int i = 0; i < classes.getLength(); i++) {
			ParaClassInfo pcinfo = new ParaClassInfo();
			Node classinfo = classes.item(i);
			String id = getNodeText(getNode(classinfo, "id"));
			pcinfo.setId(id);
			String describe = getNodeText(getNode(classinfo, "describe"));
			pcinfo.setDescribe(describe);
			Node parameters = getNode(classinfo, "parameters");
			if (parameters != null) {
				NodeList parameterlist = parameters.getChildNodes();
				for (int j = 0; j < parameterlist.getLength(); j++) {
					ParaInfo pi = new ParaInfo();
					Node parameter = parameterlist.item(j);
					pi.setDescribe(getNodeText(getNode(parameter, "describe")));
					pi.setKey(getNodeText(getNode(parameter, "key")));
					pi.setValue(getNodeText(getNode(parameter, "value")));
					if (pi.getKey() != null && !pi.getKey().equals("")) {
						CsParameter csPara = new CsParameter();
						csPara.setKey(pi.getKey());
						csPara.setValue(pi.getValue());
						csPara.setClassid(id);
						csPara.setUpdateTime(new Date());
						csPara.setReadonly(false);
						csPara.setDataType("Character");
						csPara.setDescription(pi.getDescribe());

						ParameterMgrService.getInstance().saveOrUpdate(csPara);
						
						++count;
					}
				}
			}
		}
		System.out.println("import parameters to database from  "
				+ this.filename + " success! total count: " + count);
	}
	
	public static void main(String[] args) {
		try {
			ImportXml2DB a = new ImportXml2DB();
			a.importParameter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
