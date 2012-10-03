package com.taifook.mtss.web.common.util.hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * 
 * $Id: StringClobType.java,v 1.1 2010/11/09 03:57:49 kyzou Exp $
 * @Project:mcs
 * @File:StringClobType.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-8-13
 */
public class StringClobType implements UserType {

    private static final String ORACLE_DRIVER_NAME = "Oracle JDBC driver";
    private static final int ORACLE_DRIVER_MAJOR_VERSION = 9;
    private static final int ORACLE_DRIVER_MINOR_VERSION = 0;
    

    public int[] sqlTypes() {
        return (new int[] { 2005 });
    }

    public Class returnedClass()
    {
        return null;
    }

    public boolean equals(Object x, Object y)
    {
        return ObjectUtils.equals(x, y);
    }

    public Object nullSafeGet(ResultSet rs, String names[], Object owner)
        throws HibernateException, SQLException
    {
        java.io.Reader clobReader = rs.getCharacterStream(names[0]);
        if(clobReader == null)
            return null;
        String str = new String();
        BufferedReader bufferedClobReader = new BufferedReader(clobReader);
        try
        {
            for(String line = null; (line = bufferedClobReader.readLine()) != null;)
                str = str + line;

            bufferedClobReader.close();
        }
        catch(IOException e)
        {
            throw new SQLException(e.toString());
        }
        return str;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index)
        throws HibernateException, SQLException
    {
        DatabaseMetaData dbMetaData = st.getConnection().getMetaData();
        if(value == null)
            st.setNull(index, sqlTypes()[0]);
        else
        if("Oracle JDBC driver".equals(dbMetaData.getDriverName()))
        {
            if(dbMetaData.getDriverMajorVersion() >= 9 && dbMetaData.getDriverMinorVersion() >= 0)
                try
                {
                    Class oracleClobClass = Class.forName("oracle.sql.CLOB");
                    Class oracleConnectionClass = Class.forName("oracle.jdbc.OracleConnection");
                    Class partypes[] = new Class[3];
                    partypes[0] = Connection.class;
                    partypes[1] = Boolean.TYPE;
                    partypes[2] = Integer.TYPE;
                    Method createTemporaryMethod = oracleClobClass.getDeclaredMethod("createTemporary", partypes);
                    Field durationSessionField = oracleClobClass.getField("DURATION_SESSION");
                    Object arglist[] = new Object[3];
                    Connection conn = st.getConnection();
                    if(!oracleConnectionClass.isAssignableFrom(conn.getClass()))
                        throw new HibernateException("JDBC connection object must be a oracle.jdbc.OracleConnection. Connection class is " + conn.getClass().getName());
                    arglist[0] = conn;
                    arglist[1] = Boolean.TRUE;
                    arglist[2] = durationSessionField.get(null);
                    Object tempClob = createTemporaryMethod.invoke(null, arglist);
                    partypes = new Class[1];
                    partypes[0] = Integer.TYPE;
                    Method openMethod = oracleClobClass.getDeclaredMethod("open", partypes);
                    Field modeReadWriteField = oracleClobClass.getField("MODE_READWRITE");
                    arglist = new Object[1];
                    arglist[0] = modeReadWriteField.get(null);
                    openMethod.invoke(tempClob, arglist);
                    Method getCharacterOutputStreamMethod = oracleClobClass.getDeclaredMethod("getCharacterOutputStream", null);
                    Method getAsciiOutputStreamMethod = oracleClobClass.getDeclaredMethod("getAsciiOutputStream", null);
                    Writer tempClobWriter = (Writer)getCharacterOutputStreamMethod.invoke(tempClob, null);
                    String oldString = (String)value;
                    String newString = null;
                    try
                    {
                        newString = new String(oldString.getBytes("8859_1"), "utf-8");
                    }
                    catch(Exception e)
                    {
                        newString = "";
                    }
                    tempClobWriter.write(newString);
                    tempClobWriter.flush();
                    tempClobWriter.close();
                    Method closeMethod = oracleClobClass.getDeclaredMethod("close", null);
                    closeMethod.invoke(tempClob, null);
                    st.setClob(index, (Clob)tempClob);
                }
                catch(ClassNotFoundException e)
                {
                    throw new HibernateException("Unable to find a required class.\n" + e.getMessage());
                }
                catch(NoSuchMethodException e)
                {
                    throw new HibernateException("Unable to find a required method.\n" + e.getMessage());
                }
                catch(NoSuchFieldException e)
                {
                    throw new HibernateException("Unable to find a required field.\n" + e.getMessage());
                }
                catch(IllegalAccessException e)
                {
                    throw new HibernateException("Unable to access a required method or field.\n" + e.getMessage());
                }
                catch(InvocationTargetException e)
                {
                    throw new HibernateException(e.getMessage());
                }
                catch(IOException e)
                {
                    throw new HibernateException(e.getMessage());
                }
            else
                throw new HibernateException("No CLOBS support. Use driver version 9, minor 0");
        } else
        {
            String str = (String)value;
            StringReader r = new StringReader(str);
            st.setCharacterStream(index, r, str.length());
        }
    }

    public Object deepCopy(Object value)
    {
        if(value == null)
            return null;
        else
            return new String((String)value);
    }

    public boolean isMutable()
    {
        return false;
    }

	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable disassemble(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

}
