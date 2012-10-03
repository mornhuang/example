package net.sf.hibernate.tool.hbm2java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.util.StringHelper;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.jdom.Element;

/**
 * Helper for loading, merging  and accessing <meta> tags.
 * 
 * @author max
 *
 * 
 */
public class MetaAttributeHelper {

	static class MetaAttribute {
		String value;
		boolean inheritable = true;

		MetaAttribute(String value, boolean inherit) {
			this.value = value;
			this.inheritable = inherit;
		}

		public String toString() {
			return value;
		}
	}

	/**
	 * Load meta attributes from jdom element into a MultiMap.
	 * 
	 * @param element
	 * @return MultiMap
	 */
	static protected MultiMap loadMetaMap(Element element) {
		MultiMap result = new MultiHashMap();
		List metaAttributeList = new ArrayList();
		metaAttributeList.addAll(element.getChildren("meta"));

		for (Iterator iter = metaAttributeList.iterator(); iter.hasNext();) {
			Element metaAttrib = (Element) iter.next();
			// does not use getTextNormalize() or getTextTrim() as that would remove the formatting in new lines in items like description for javadocs.
			String attribute = metaAttrib.getAttribute("attribute").getValue();
			String value = metaAttrib.getText();
			String inheritStr= metaAttrib.getAttributeValue("inherit");
			boolean inherit = true;
			if(inheritStr!=null) {
				inherit = Boolean.valueOf(inheritStr).booleanValue(); 
			}			
			
			MetaAttribute ma = new MetaAttribute(value, inherit);
			result.put(attribute, ma);
		}
		return result;

	}

	/**
	 * Merges a Multimap with inherited maps.
	 * Values specified always overrules/replaces the inherited values.
	 * 
	 * @param local
	 * @param inherited
	 * @return a MultiMap with all values from local and extra values
	 * from inherited
	 */
	static public MultiMap mergeMetaMaps(MultiMap local, MultiMap inherited) {
		MultiHashMap result = new MultiHashMap();
		copyMultiMap(result, local);
		
		if (inherited != null) {
			for (Iterator iter = inherited.keySet().iterator();
				iter.hasNext();
				) {
				String key = (String) iter.next();

				if (!local.containsKey(key)) {
					// inheriting a meta attribute only if it is inheritable
					Collection ml = (Collection) inherited.get(key);
					for (Iterator iterator = ml.iterator();
						iterator.hasNext();
						) {
						MetaAttribute element = (MetaAttribute) iterator.next();
						if (element.inheritable) {
							result.put(key, element);
						}
					}
				}
			}
		}

		return result;

	}
	/**
	 * Method loadAndMergeMetaMap.
	 * @param classElement
	 * @param inheritedMeta
	 * @return MultiMap
	 */
	public static MultiMap loadAndMergeMetaMap(
		Element classElement,
		MultiMap inheritedMeta) {
		return mergeMetaMaps(loadMetaMap(classElement), inheritedMeta);
	}

	/**
	 * @param collection
	 * @param string
	 */
	public static String getMetaAsString(Collection meta, String seperator) {
		StringBuffer buf = new StringBuffer();
			for (Iterator iter = meta.iterator(); iter.hasNext();) {				
				buf.append(iter.next());
				if(iter.hasNext()) buf.append(seperator);
			}
		return buf.toString();
	}

	static	boolean getMetaAsBool(Collection c, boolean defaultValue) {
			if(c==null || c.isEmpty()) {
				return defaultValue;
			} else {
				return Boolean.valueOf(c.iterator().next().toString()).booleanValue();
			}
		}

	static String getMetaAsString(Collection c) {
			if(c==null || c.isEmpty()) {
		        return StringHelper.EMPTY_STRING;
		    } else {
		    StringBuffer sb = new StringBuffer();
		  for (Iterator iter = c.iterator(); iter.hasNext();) {
		    Object element = iter.next();
		    sb.append(element.toString());
		}   
		    return sb.toString();
		}
	}

    /**
     * Copies all the values from one MultiMap to another.
     * This method is needed because the (undocumented) behaviour of 
     * MultiHashMap.putAll in versions of Commons Collections prior to 3.0
     * was to replace the collection in the destination, whereas in 3.0
     * it adds the collection from the source as an _element_ of the collection
     * in the destination.  This method makes no assumptions about the implementation
     * of the MultiMap, and should work with all versions.
     * 
     * @param destination
     * @param source
     */
    static public void copyMultiMap(MultiMap destination, MultiMap source) {
        for (Iterator keyIterator = source.keySet().iterator(); keyIterator.hasNext(); ) {
            Object key = keyIterator.next();
            Collection c = (Collection) source.get(key);
            for (Iterator valueIterator = c.iterator(); valueIterator.hasNext(); ) 
                destination.put(key, valueIterator.next());
        }
    }

}
