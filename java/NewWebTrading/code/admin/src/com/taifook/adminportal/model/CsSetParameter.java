package com.taifook.adminportal.model;

import java.io.Serializable;

/**
 * $Id: CsSetParameter.java,v 1.2 2010/11/09 04:31:52 kyzou Exp $
 * @Project:admin-portal
 * @File:CsSetParameter.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-26
 */
public class CsSetParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int hashValue = 0;
	
    private CsSetParameterKey id;

    private java.lang.String description;

    private java.util.Date updateTime;


    public CsSetParameter()
    {
    }

    /**
     * Constructor of CsSetParameter instances given a composite primary key.
     * @param id
     */
    public CsSetParameter(CsSetParameterKey id)
    {
        this.setId(id);
    }

	public CsSetParameterKey getId() {
		return id;
	}

	public void setId(CsSetParameterKey id) {
		this.id = id;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
    public boolean equals(Object rhs)
    {
        if (rhs == null)
            return false;
        if (! (rhs instanceof CsSetParameter))
            return false;
        CsSetParameter that = (CsSetParameter) rhs;
        if (this.getId() == null || that.getId() == null)
            return false;
        return (this.getId().equals(that.getId()));
    }

    public int hashCode()
    {
        if (this.hashValue == 0)
        {
            int result = 17;
            if (this.getId() == null)
            {
                result = super.hashCode();
            }
            else
            {
                result = this.getId().hashCode();
            }
            this.hashValue = result;
        }
        return this.hashValue;
    }

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}
}
