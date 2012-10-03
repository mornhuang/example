package com.taifook.adminportal.common.util.page;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;


/**
 * <p> * Title: admin_portal           * </p>
 * <p> * Description:                  * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public class HibernatePage implements Page {

	private List elements;
    private int pageSize;
    private int pageNumber;
    private int totalElements = 0;
 
    public HibernatePage(Query query, int pageNumber, int pageSize,int totalElement)
    {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements=totalElement; 
        try
          {           
            if (Integer.MAX_VALUE == this.pageNumber || this.pageNumber > getLastPageNumber())   //last page
               {    this.pageNumber = getLastPageNumber(); }
            if(((this.pageNumber-1) * this.pageSize)<=0)
            	query.setFirstResult(0);
            else
                query.setFirstResult((this.pageNumber-1) * this.pageSize);
            
            query.setMaxResults(this.pageSize);
            elements=query.list();              
            
        } catch (HibernateException e){  throw new RuntimeException(e); }
    }

    public boolean isFirstPage()
    {
        return getThisPageNumber() == 1;
    }

    public boolean isLastPage()
    {
        return getThisPageNumber() >= getLastPageNumber();
    }

    public boolean hasNextPage()
    {
        return getLastPageNumber() > getThisPageNumber();
    }

    public boolean hasPreviousPage()
    {
        return getThisPageNumber() > 1;
    }

    public int getLastPageNumber()
    {
        return totalElements % this.pageSize == 0 ? totalElements / this.pageSize : totalElements / this.pageSize + 1;
    }

    
    public Object getThisPageElements()
    {
        return elements;
    }

    public int getTotalNumberOfElements()
    {
        return totalElements;
    }

    public int getThisPageFirstElementNumber()
    {
        return (getThisPageNumber() - 1) * getPageSize() + 1;
    }

    public int getThisPageLastElementNumber()
    {
        int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
        return getTotalNumberOfElements() < fullPage ? getTotalNumberOfElements() : fullPage;
    }

    public int getNextPageNumber()
    {
        return getThisPageNumber() + 1;
    }

    public int getPreviousPageNumber()
    {
        return getThisPageNumber() - 1;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public int getThisPageNumber()
    {
        return pageNumber;
    }	
}

