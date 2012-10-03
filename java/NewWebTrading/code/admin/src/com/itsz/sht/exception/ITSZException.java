/*
 * Created on 2005-3-29
 *
 */
package com.itsz.sht.exception;

/**
 * @author wzzhan
 *
 */
public class ITSZException extends Exception{
	private static final long serialVersionUID = 1601116840599105904L;
	protected String errorKey;
	protected String errorCode;
	protected Object[] values;
    protected Object value1;
    
    /**
     * construct an exception with null as the detailed message
     *
     */
    public ITSZException()
    {
        super();
    }
    
    /**
     * construct an exception with the specified error message
     * 
     * @param message
     */
    public ITSZException(String errorKey,String message)
    {
        super(message);
        this.errorKey = errorKey;
    }
    
    /**
     * construct an exception with error message and root cause
     * 
     * @param message
     * @param cause
     */
    public ITSZException(String message, Throwable cause)
    {
        super(message, cause); 
    }
    
    /**
     * construct an exception with errorkey
     * 
     * @param errorKey
     */
    public ITSZException(String errorKey)
    {        
    	super(errorKey);
        this.errorKey = errorKey;
    }
    
    /**
     * construct an exception with the specified error message
     * 
     * @param message
     */
    public ITSZException(String errorKey,String errorCode, String message)
    {
        super(message);
        this.errorKey = errorKey;
    }

    public ITSZException(String errorKey,String errorCode, String message,Object value1)
    {
        super(message);
        this.errorKey = errorKey;
        this.value1 = value1;
        this.errorCode = errorCode;
    }
    
    public ITSZException(String errorKey,String errorCode, String message,Object[] values)
    {
        super(message);
        this.errorKey = errorKey;
        this.values = values;
      
    }
  
    /**
     * the getter method for error key
     * 
     * @return
     */
    public String getErrorKey()
    {
    	return this.errorKey;
    }  

	/**
	 * @return Returns the value.
	 */
	public Object getValue1()
	{
		return value1;
	}


	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param values the values to set
	 */
	public Object[] getValues() {
		return values;
	}

}
