package com.taifook.mtss.web.common.util.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGeneratorFactory;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.hibernate.util.JDBCExceptionReporter;
import org.hibernate.util.PropertiesHelper;


/**
 * 
 * $Id: StringSequenceGenerator.java,v 1.3 2011/03/08 01:47:35 xli Exp $
 * @Project:mcs
 * @File:StringSequenceGenerator.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-8-13
 */
public class StringSequenceGenerator extends SequenceGenerator
{

    public static final String SEQUENCE = "sequence";
    public static final String PARAMETERS = "parameters";
    private String sequenceName;
    private String parameters;
    private Type type;
    private String sql;

    public void configure(Type type, Properties params, Dialect dialect)
        throws MappingException
    {
        sequenceName = PropertiesHelper.getString("sequence", params, "hibernate_sequence");
        parameters = params.getProperty("parameters");
        String schemaName = params.getProperty("schema");
        if(schemaName != null && sequenceName.indexOf(46) < 0)
            sequenceName = schemaName + '.' + sequenceName;
        this.type = type;
        sql = dialect.getSequenceNextValString(sequenceName);
    }

    public Serializable generate(SessionImplementor session, Object obj){
        PreparedStatement st = null;
        ResultSet rs = null;
        Serializable result;
        String s = null;
        try {
            type = new LongType();
            st = session.getBatcher().prepareStatement(sql);
            rs = st.executeQuery();
            rs.next();
            result = IdentifierGeneratorFactory.get(rs, type);//, session, obj);
            rs.close();
            
            s = String.valueOf(result);
            session.getBatcher().closeStatement(st);
        } catch (HibernateException exception) {
            try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } catch (SQLException sqle) {
            JDBCExceptionReporter.logExceptions(sqle);
        } catch (Exception exception1) {
            try {
				session.getBatcher().closeStatement(st);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }finally{
        	return s;
        }
    }
}
