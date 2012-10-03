/*
 * JDBCUtil.java
 *
 * Created on November 9, 2002, 4:27 PM
 */

package net.sf.hibernate.tool.ddl2hbm;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.type.NullableType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author  Administrator
 */
public class JDBCUtil {
	private static final Log logger = LogFactory.getLog(JDBCUtil.class);
	public static class Column {
		public String name;
		public int sqlType;
		public int sqlColumnLength;
		public int sqlDecimalLength;
		public boolean sqlNotNull;
		public boolean sqlReadOnly;
		public NullableType hibernateType;
		public Class javaType;
		public boolean equals(Object o) {
			boolean rv = false;
			if (o != null && o instanceof JDBCUtil.Column) {
				rv = (name.equals(((JDBCUtil.Column)o).name));
			}
			return rv;
		}
		public int hashCode() {
			return (name != null) ? name.hashCode() : 0;
		}
	};
	
	public static List getCatalogs(Connection c) throws SQLException {
		DatabaseMetaData dmd = c.getMetaData();
		ResultSet rs = null;
		try {
			rs = dmd.getCatalogs();
			List l = new LinkedList();
			while (rs.next()) {
				l.add(rs.getString(1));
			}
			return l;
		}
		finally {
			if (rs != null) rs.close();
		}
	}
	
	public static Map getSchemas(Connection c) throws SQLException {
		DatabaseMetaData dmd = c.getMetaData();
		ResultSet rs = null;
		try {
			rs = dmd.getSchemas();
			Map map = new HashMap();
			List l;
			while (rs.next()) {
				String schema = rs.getString(1);
				String catalog = null;
				if (rs.getMetaData().getColumnCount() > 1) {
					catalog = rs.getString(2);
				};
				l = (List)map.get(catalog);
				if (l == null) {
					l = new LinkedList();
					map.put(catalog, l);
				}
				l.add(schema);
			}
			return map;
		}
		finally {
			if (rs != null) rs.close();
		}
	}
	
	public static List getTables(Connection c, String catalog, String schema, String tablePattern) throws SQLException {
		logger.debug("catalog='" + catalog + "'");
		logger.debug("schema='" + schema + "'");
		logger.debug("table='" + tablePattern + "'");
		DatabaseMetaData dmd = c.getMetaData();
		ResultSet rs = null;
		try {
			rs = dmd.getTables(catalog, schema, tablePattern, new String[] {"TABLE", "VIEW", "SYNONYM", "ALIAS"} );
			List l= new LinkedList();
			while (rs.next()) {
				l.add(rs.getString(3));
			}
			return l;
		}
		finally {
			if (rs != null) rs.close();
		}
	}
	
	public static Set getForeignKeyColumns(Connection c, String catalog, String schema, String table) throws SQLException {
		logger.debug("catalog='" + catalog + "'");
		logger.debug("schema='" + schema + "'");
		logger.debug("table='" + table + "'");
		DatabaseMetaData dmd = c.getMetaData();
		ResultSet rs = null;
		try {
			rs = dmd.getImportedKeys(catalog, schema, table);
			HashSet columns = new HashSet();
			while (rs.next()) {
				columns.add(rs.getString(8));
			}
			return columns;
		}
		finally {
			if (rs != null) rs.close();
		}
	}
	
	public static List getPrimaryKeyColumns(Connection c, String catalog, String schema, String table) throws SQLException {
		logger.debug("catalog='" + catalog + "'");
		logger.debug("schema='" + schema + "'");
		logger.debug("table='" + table + "'");
		DatabaseMetaData dmd = c.getMetaData();
		ResultSet rs = null;
		try {
			rs = dmd.getPrimaryKeys(catalog, schema, table);
			
			List pkColumns = new LinkedList();;
			while (rs.next()) {
				List tmp = getTableColumns(c, catalog, schema, table, rs.getString(4));
				Column pkColumn = (Column)tmp.get(0);
				pkColumns.add(pkColumn);
			}
			return pkColumns;
		}
		finally {
			if (rs != null) rs.close();
		}
	}
	
	public static List getTableColumns(Connection c, String catalog, String schema, String table) throws SQLException  {
		return getTableColumns(c, catalog, schema, table, null);
	}
	
	public static List getTableColumns(Connection c, String catalog, String schema, String table, String columnPattern) throws SQLException {
		logger.debug("catalog='" + catalog + "'");
		logger.debug("schema='" + schema + "'");
		logger.debug("table='" + table + "'");
		logger.debug("column='" + columnPattern+ "'");
		DatabaseMetaData dmd = c.getMetaData();
		ResultSet rs = null;
		try {
			rs = dmd.getColumns(catalog, schema, table, columnPattern);
			List columns = new LinkedList();
			while (rs.next()) {
				JDBCUtil.Column aCol = new JDBCUtil.Column();
				aCol.name = rs.getString(4);
				aCol.sqlType = rs.getShort(5);
				aCol.sqlColumnLength=rs.getInt(7);
				aCol.sqlDecimalLength=rs.getInt(9);
				aCol.sqlNotNull = ("NO".equals(rs.getString(18)));
				aCol.hibernateType = getHibernateType(
					aCol.sqlType,
					aCol.sqlColumnLength,
					aCol.sqlDecimalLength
				);
				aCol.javaType = getJavaType(
					aCol.sqlType,
					aCol.sqlColumnLength,
					aCol.sqlDecimalLength
				);
				columns.add(aCol);
			}
			return columns;
		}
		finally {
			if (rs != null) rs.close();
		}
		
	}
	
	public static NullableType getHibernateType(int sqlType, int columnSize, int decimalDigits) {
		logger.debug("sqlType=" + sqlType);
		logger.debug("columnSize=" + columnSize);
		logger.debug("decimalDigits=" + decimalDigits);
		NullableType rv=Hibernate.SERIALIZABLE;
		if (sqlType == Types.CHAR || sqlType == Types.VARCHAR) {
			rv = Hibernate.STRING;
		}
		else if (sqlType == Types.FLOAT || sqlType == Types.REAL) {
			rv = Hibernate.FLOAT;
		}
		else if (sqlType == Types.INTEGER) {
			rv = Hibernate.INTEGER;
		}
		else if (sqlType == Types.DOUBLE) {
			rv = Hibernate.DOUBLE;
		}
		else if (sqlType == Types.DATE) {
			rv = Hibernate.DATE;
		}
		else if (sqlType == Types.TIMESTAMP) {
			rv = Hibernate.TIMESTAMP;
		}
		else if (sqlType == Types.TIME) {
			rv = Hibernate.TIME;
		}
		// commented to support JDK version < 1.4
		/*      else if (sqlType == Types.BOOLEAN) {
			rv = Hibernate.BOOLEAN;
		} */
		else if (sqlType == Types.SMALLINT) {
			rv = Hibernate.SHORT;
		}
		else if (sqlType == Types.BIT) {
			rv = Hibernate.BYTE;
		}
		else if (sqlType == Types.BIGINT) {
			rv = Hibernate.LONG;
		}
		else if (sqlType == Types.NUMERIC || sqlType == Types.DECIMAL) {
			if (decimalDigits == 0) {
				if (columnSize == 1) {
					rv = Hibernate.BYTE;
				}
				else if (columnSize < 5) {
					rv = Hibernate.SHORT;
				}
				else if (columnSize < 10) {
					rv = Hibernate.INTEGER;
				}
				else {
					rv = Hibernate.LONG;
				}
			}
			else {
				if (columnSize < 9) {
					rv = Hibernate.FLOAT;
				}
				else {
					rv = Hibernate.DOUBLE;
				}
			}
		}
		return rv;
	}
	
	public static Class getJavaType(int sqlType, int columnSize, int decimalDigits) {
		logger.debug("sqlType=" + sqlType);
		logger.debug("columnSize=" + columnSize);
		logger.debug("decimalDigits=" + decimalDigits);
		Class rv=String.class;
		if (sqlType == Types.CHAR || sqlType == Types.VARCHAR) {
			rv = String.class;
		}
		else if (sqlType == Types.FLOAT || sqlType == Types.REAL) {
			rv = Float.class;
		}
		else if (sqlType == Types.INTEGER) {
			rv = Integer.class;
		}
		else if (sqlType == Types.DOUBLE) {
			rv = Double.class;
		}
		else if (sqlType == Types.DATE) {
			rv = java.util.Date.class;
		}
		else if (sqlType == Types.TIMESTAMP) {
			rv = java.util.Date.class;
		}
		else if (sqlType == Types.TIME) {
			rv = java.util.Date.class;
		}
		// commented to support JDK version < 1.4
		/*      else if (sqlType == Types.BOOLEAN) {
			rv = Boolean.class;
		} */
		else if (sqlType == Types.SMALLINT) {
			rv = Short.class;
		}
		else if (sqlType == Types.BIT) {
			rv = Byte.class;
		}
		else if (sqlType == Types.BIGINT) {
			rv = Long.class;
		}
		else if (sqlType == Types.NUMERIC || sqlType == Types.DECIMAL) {
			if (decimalDigits == 0) {
				if (columnSize == 1) {
					rv = Byte.class;
				}
				else if (columnSize < 5) {
					rv = Short.class;
				}
				else if (columnSize < 10) {
					rv = Integer.class;
				}
				else {
					rv = Long.class;
				}
			}
			else {
				if (columnSize < 9) {
					rv = Float.class;
				}
				else {
					rv = Double.class;
				}
			}
		}
		return rv;
	}
	
}






