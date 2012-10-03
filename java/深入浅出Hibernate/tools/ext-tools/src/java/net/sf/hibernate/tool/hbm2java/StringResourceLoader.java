/*
 * Created on 12-10-2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package net.sf.hibernate.tool.hbm2java;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.tools.ant.filters.StringInputStream;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

/**
 * @author MAX
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StringResourceLoader extends ResourceLoader {

	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.resource.loader.ResourceLoader#init(org.apache.commons.collections.ExtendedProperties)
	 */
	public void init(ExtendedProperties configuration) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.resource.loader.ResourceLoader#getResourceStream(java.lang.String)
	 */
	public InputStream getResourceStream(String source)
			throws ResourceNotFoundException {
		return new StringInputStream(source);
	}

	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.resource.loader.ResourceLoader#isSourceModified(org.apache.velocity.runtime.resource.Resource)
	 */
	public boolean isSourceModified(Resource resource) {		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.resource.loader.ResourceLoader#getLastModified(org.apache.velocity.runtime.resource.Resource)
	 */
	public long getLastModified(Resource resource) {
		return System.currentTimeMillis();
	}
}
