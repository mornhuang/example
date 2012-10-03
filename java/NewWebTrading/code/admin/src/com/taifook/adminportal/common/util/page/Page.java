package com.taifook.adminportal.common.util.page;
/**
 * <p> * Title: admin_portal        * </p>
 * <p> * Description:                     * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
  public interface Page 
    {
	    public boolean isFirstPage();
	    public boolean isLastPage();    
	    public boolean hasNextPage();
	    public boolean hasPreviousPage();
	    
	    public int getLastPageNumber();

	    public Object getThisPageElements();
   
	    public int getTotalNumberOfElements();
	   
	    public int getThisPageFirstElementNumber();

	    public int getThisPageLastElementNumber();

	    public int getNextPageNumber();

	    public int getPreviousPageNumber();

	    public int getPageSize();

	    public int getThisPageNumber();
     }

