/*
 * Created Thu Mar 30 14:48:38 GMT+08:00 2006 by MyEclipse Hibernate Tool.
 */ 
package com.taifook.adminportal.model;

import java.io.Serializable;

/**
 * A class that represents a row in the 'CS_ONLINEUSER' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class CsOnlineuser
    extends AbstractCsOnlineuser
    implements Serializable
{
    private static final long serialVersionUID = 1L;

	/**
     * Simple constructor of CsOnlineuser instances.
     */
    public CsOnlineuser()
    {
    }

    /**
     * Constructor of CsOnlineuser instances given a composite primary key.
     * @param id
     */
    public CsOnlineuser(CsOnlineuserKey id)
    {
        super(id);
    }

    /* Add customized code below */

}
