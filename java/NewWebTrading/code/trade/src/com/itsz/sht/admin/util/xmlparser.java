/*
 * Created on 2005-8-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.util;

import java.io.File;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class xmlparser {
    protected static String filepath;
    protected String filename = "";
    protected String encoding = "utf-8";
    protected String lang = "en_US";
    protected DocumentBuilder builder;
    protected Document doc;
    protected Element root;
    protected DocumentBuilderFactory factory;

    /**
     * @return Returns the filepath.
     */
    public static String getFilepath() {
        return filepath;
    }
    /**
     * @param filepath The filepath to set.
     */
    public static void setFilepath(String filepath) {
        xmlparser.filepath = filepath;
    }
    //find a node named as tageName in a parent node's children nodelist
    //return null if not found
    protected Node getNode(Node parentNode, String tagName) {
        NodeList nodelist = parentNode.getChildNodes();
        if(nodelist==null)
            return null;
        for(int i =0; i < nodelist.getLength(); i++) {
            Node child = nodelist.item(i);
            if(child.getNodeName().equals(tagName))
                return child;
        }
        return null;
    }

    // return innertext inside the TextNode
    protected String getNodeText(Node textNode) {
        if(textNode != null) {
            Node innerText = textNode.getFirstChild();
            if (innerText != null && innerText.getNodeType() == Node.TEXT_NODE) {
                return innerText.getNodeValue();
            }
        }
        return null;
    }

    //��docд��filename�ļ���
    protected void writeXMLFile(){
//      ���ȴ���һ��DOMSource����,�ù��캯��Ĳ��������һ��Document����
//      doc����ĺ��DOM Tree��
            DOMSource doms = new DOMSource (doc);
            
           //����һ��File����,���DOM Tree������ݵ��������,����һ��XML�ļ���
      File f = new File (filename);
      
            //����һ��StreamResult����,�ù��캯��Ĳ������ȡΪFile����
      StreamResult sr = new StreamResult (f);
      
            //�������JAXP�е�XSLT������ʵ�����DOM Tree�е���ݵ�XML�ļ��еĹ��ܡ�
//      XSLT���������ΪDOMSource����,���ΪStreamResut����
      try
      {
//      ���ȴ���һ��TransformerFactory����,���ɴ˴���Transformer����Transformer
//      ���൱��һ��XSLT���档ͨ������ʹ����������XSL�ļ�,��������������ʹ
//      ���������XML�ĵ���
      TransformerFactory tf=TransformerFactory.newInstance(); 
        Transformer t=tf.newTransformer ();
//      ��ȡTransformser������������,�༴XSLT�����ȱʡ�������,����һ����
        //java.util.Properties���󡣡�
        Properties properties = t.getOutputProperties(); 

//      �����µ��������:����ַ����ΪGB2312,�������֧�������ַ�,XSLT���������
//��XML�ĵ������������ַ�,��������ʾ,���������ν��"��������"������
        //������OutputKeys����ַ���OutputKeys.ENCODING������
        properties.setProperty(OutputKeys.ENCODING,encoding);
        properties.setProperty(OutputKeys.INDENT,"yes"); 
        //����XSLT�����������ԡ�����
        t.setOutputProperties(properties); 

        //�ؼ��һ��, ����Transformer���� (XSLT����)��transform()����,�÷����ĵ�һ
//      ��������DOMSource����,�ڶ���������StreamResult����
        t.transform(doms,sr);

        }
        catch (TransformerConfigurationException tce)
        {
       System.out.println("Transformer Configuration Exception\n-----");
        tce.printStackTrace(); 
        }
        catch (TransformerException te) 
        { 
        System.out.println ("Transformer Exception\n---------"); 
        te.printStackTrace();
        } 
        catch (Exception te) 
        { 
        System.out.println ("Transformer Exception\n---------"); 
        te.printStackTrace();
        } 
    }
}
