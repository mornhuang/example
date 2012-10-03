/*
 * Created Mon Apr 03 18:26:30 GMT+08:00 2006 by MyEclipse Hibernate Tool.
 */ 
package com.taifook.adminportal.model;

import java.io.Serializable;

/**
 * A class that represents a row in the 'CS_USERACTIONLOG' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class CsUseractionlog
    extends AbstractCsUseractionlog
    implements Serializable
{
    private static final long serialVersionUID = 1L;

	/**
     * Simple constructor of CsUseractionlog instances.
     */
    public CsUseractionlog()
    {
    }

    /**
     * Constructor of CsUseractionlog instances given a simple primary key.
     * @param seqno
     */
    public CsUseractionlog(java.lang.Long seqno)
    {
        super(seqno);
    }

    /* Add customized code below */

}
