/*
 * Created Fri Mar 24 11:07:44 GMT+08:00 2006 by MyEclipse Hibernate Tool.
 */ 
package com.taifook.adminportal.model;

import java.io.Serializable;

/**
 * A class that represents a row in the 'CS_BROADCAST' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class CsBroadcast
    extends AbstractCsBroadcast
    implements Serializable
{
    private static final long serialVersionUID = 1L;

	/**
     * Simple constructor of CsBroadcast instances.
     */
    public CsBroadcast()
    {
    }

    /**
     * Constructor of CsBroadcast instances given a simple primary key.
     * @param seqno
     */
    public CsBroadcast(java.lang.Long seqno)
    {
        super(seqno);
    }

    /* Add customized code below */

}
