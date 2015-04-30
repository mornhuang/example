/*
 * Created on 16-Jun-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manning.ajaxinaction.command;

import org.jdom.Element;

/**
 * @author dave
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface XMLCommandProcessor {
	Element processXML(Element el);
}
