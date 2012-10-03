/*
 * Created on 28-09-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */package net.sf.hibernate.tool.hbm2java;

import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.jdom.Element;
/**
 * @author MAX
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class MappingElement {

	private Element element;
	private MappingElement parentElement;
	private MultiMap metaattribs;

	public MappingElement(Element element, MappingElement parentElement) {
		this.element = element;
		this.parentElement = parentElement; // merge with parent meta map
		/*
		 * MultiMap inherited = null; if (parentModel != null) { inherited =
		 * parentModel.getMetaMap(); }
		 */

	}

	public MappingElement getParentElement() {
		return parentElement;
	}

	public Element getXMLElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	/** Returns true if this element has the meta attribute */
	public boolean hasMeta(String attribute) {
		return metaattribs.containsKey(attribute);
	}

	/* Given a key, return the list of metaattribs. Can return null! */
	public List getMeta(String attribute) {
		return (List) metaattribs.get(attribute);
	}

	/**
	 * Returns all meta items as one large string.
	 * 
	 * @param string
	 * @return String
	 */
	public String getMetaAsString(String attribute) {
		List c = getMeta(attribute);

		return MetaAttributeHelper.getMetaAsString(c);
	}

	public String getMetaAsString(String attribute, String seperator) {
		return MetaAttributeHelper.getMetaAsString(getMeta(attribute), seperator);
	}
	
	public boolean getMetaAsBool(String attribute) {
		return getMetaAsBool(attribute, false);
	}

	public boolean getMetaAsBool(String attribute, boolean defaultValue) {
		List c = getMeta(attribute);

		return MetaAttributeHelper.getMetaAsBool(c, defaultValue);
	}

	protected void setMetaAttribs(MultiMap metaAttribs) {
		this.metaattribs = metaAttribs;
	}

	protected MultiMap getMetaAttribs() {
		return metaattribs;
	}

}