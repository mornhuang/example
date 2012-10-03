package net.sf.hibernate.tool.class2hbm;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.type.CurrencyType;

/** a ReflectedProperty is made by an instance of MapGenerator
 *  to model properties of Reflected classes;<br>
 *  basic properties are handled here: primitive, enum, custom;<br>
 *  subclasses handle more involved cases
 *
 * @version 	1.x
 * @author		<a href="mailto:doug.currie@alum.mit.edu">e</a>
 */
public class ReflectedProperty {

	protected boolean isUid;
	protected Class type;
	protected String tynm;
	protected String name;
	protected String comment;
	protected StringBuffer buf;
	protected MapGenerator map;

	private static HashMap shortTypeNames = new HashMap();
	static {
		shortTypeNames.put( Long.class.getName(), Hibernate.LONG.getName() );
		shortTypeNames.put( Integer.class.getName(), Hibernate.INTEGER.getName() );
		shortTypeNames.put( Boolean.class.getName(), Hibernate.BOOLEAN.getName() );
		shortTypeNames.put( Short.class.getName(), Hibernate.SHORT.getName() );
		shortTypeNames.put( Byte.class.getName(), Hibernate.BYTE.getName());
		shortTypeNames.put( Float.class.getName(), Hibernate.FLOAT.getName() );
		shortTypeNames.put( Double.class.getName(), Hibernate.DOUBLE.getName() );
		shortTypeNames.put( Character.class.getName(), Hibernate.CHARACTER.getName() );
		shortTypeNames.put( String.class.getName(), Hibernate.STRING.getName() );
		shortTypeNames.put( Locale.class.getName(), Hibernate.LOCALE.getName() );
		shortTypeNames.put( Calendar.class.getName(), Hibernate.CALENDAR.getName() );
		if ( CurrencyType.CURRENCY_CLASS!=null) shortTypeNames.put( CurrencyType.CURRENCY_CLASS.getName(), Hibernate.CURRENCY.getName() );
		shortTypeNames.put( TimeZone.class.getName(), Hibernate.TIMEZONE.getName() );
		shortTypeNames.put( BigDecimal.class.getName(), Hibernate.BIG_DECIMAL.getName() );
		shortTypeNames.put( Class.class.getName(), Hibernate.CLASS.getName() );
		shortTypeNames.put( Object.class.getName(), Hibernate.OBJECT.getName() );
		shortTypeNames.put( Time.class.getName(), Hibernate.TIME.getName() );
		shortTypeNames.put( Timestamp.class.getName(), Hibernate.TIMESTAMP.getName() );
		shortTypeNames.put( java.util.Date.class.getName(), Hibernate.TIMESTAMP.getName() );
		shortTypeNames.put( java.sql.Date.class.getName(), Hibernate.DATE.getName() );
		shortTypeNames.put( byte[].class.getName(), Hibernate.BINARY.getName() ); // not shorter, but nicer
		shortTypeNames.put( Blob.class.getName(), Hibernate.BLOB.getName() );
		shortTypeNames.put( Clob.class.getName(), Hibernate.CLOB.getName() );
	}

	protected static String abbreviateType(String typeName) {
		String abbreviated = (String) shortTypeNames.get(typeName);
		return (abbreviated==null) ? typeName : abbreviated;
	}

	ReflectedProperty( String name, Class type
	, MapGenerator map, String comment ) {
		this.name = name;
		this.type = type;
		this.tynm = abbreviateType( type.getName() ); // default; may be changed later
		this.comment = comment;
		this.map = map;
		this.isUid = false;
	}

	/** used by gui */
	public String getName() {
		return name;
	}

	/** used by gui */
	public String getTypeName() {
		return tynm;
	}

	protected boolean isUidOK() {
		/*if( long.class == type ) return true;
		if( Long.class == type ) return true;
		if( String.class == type ) return true;
		// more?
		return false;*/
		return ( !type.isArray() ); // since arrays never implement equals properly
	}

	protected void emitIntUidStr(int level) {
		map.emitPrefix(level);
		buf.append("<id name=\"")
		.append(name)
		.append("\" type=\"")
		.append(tynm)			// or just use "long" ?
		.append("\" column=\"")
		.append(map.columnNameFor(name))
		.append("\">\n");
		map.emitPrefix(level+1);
		buf.append("<generator class=\"native\"/>\n");
		map.emitPrefix(level);
		buf.append("</id>\n");
	}

	protected void emitStrUidStr(int level) {
		map.emitPrefix(level);
		buf.append("<id name=\"" )
		.append(name)
		.append("\" type=\"")
		.append(tynm)			// or just use "string" ?
		.append("\">\n");
		map.emitPrefix(level+1);
		buf.append("<column name=\"")
		.append(map.columnNameFor(name))
		.append("\" length=\"16\"/>\n");
		map.emitPrefix(level+1);
		buf.append("<generator class=\"uuid.hex\"/>\n");
		map.emitPrefix(level);
		buf.append("</id>\n");
	}

	protected void emitPropertyStr(int level) {
		map.emitPrefix(level);
		buf.append("<property name=\"" )
		.append(name)
		.append("\" column=\"")
		.append(map.columnNameFor(name))
		.append("\" type=\"")
		.append(tynm)
		.append("\"/>\n");
	}

	protected void emitPCommentStr( int level, String comment, String name, String tynm ) {
		/*map.emitPrefix(level);
		buf.append("<!-- ")
		.append(comment)
		.append(" name=\"")
		.append(name)
		.append("\" type=\"")
		.append(tynm)
		.append("\" -->\n");*/
	}

	protected void emitCollectionStrL( int level, String collection
	, String name, boolean lazy ) {
		map.emitPrefix(level);
		buf.append("<")
		.append(collection)
		.append(" name=\"" )
		.append(name)
		.append("\" table=\"")
		.append(map.tableNameFor(name));
		if( lazy ) {
			buf.append("\" lazy=\"true");
		}
		buf.append("\">\n");
	}
	protected void emitCollectionStrK( int level, String key ) {
		map.emitPrefix(level);
		buf.append("<key column=\"")
		.append(map.columnNameFor(key))
		.append("\"/>\n");
	}
	protected void emitCollectionStrI( int level, String idx ) {
		map.emitPrefix(level);
		buf.append("<index column=\"")
		.append(map.columnNameFor(idx))
		.append("\"/>\n");
	}
	protected void emitCollectionStrIT( int level, String idx, String type ) {
		map.emitPrefix(level);
		buf.append("<index column=\"")
		.append(map.columnNameFor(idx))
		.append("\" type =\"")
		.append(type)
		.append("\"/>\n");
	}
	protected void emitCollectionStrE( int level, String elm ) {
		map.emitPrefix(level);
		buf.append("<element column=\"")
		.append(map.columnNameFor(elm))
		.append("\"/>\n");
	}
	protected void emitCollectionStrET( int level, String elm, String type ) {
		map.emitPrefix(level);
		buf.append("<element column=\"")
		.append(map.columnNameFor(elm))
		.append("\" type =\"")
		.append(type)
		.append("\"/>\n");
	}
	protected void emitCollectionStrR( int level, String collection ) {
		map.emitPrefix(level);
		buf.append("</")
		.append(collection)
		.append(">\n");
	}
	//protected void emitCollectionStrLazy(int level) {
	//	map.emitPrefix(level);
	//	buf.append("<lazy=\"true\">\n");
	//}

	//<!ELEMENT subcollection (column*)>
	//  <!ATTLIST subcollection column CDATA #IMPLIED>
	//  <!ATTLIST subcollection role CDATA #REQUIRED>
	//  <!ATTLIST subcollection length CDATA #IMPLIED>
	//  <!ATTLIST subcollection not-null CDATA #IMPLIED>

	protected void emitSubCollectionStr( int level, String name ) {
		map.emitPrefix(level);
		buf.append("<subcollection role=\"" )
		.append(name)
		.append("\" column=\"")
		.append(map.columnNameFor(name))
		.append("\">\n");
	}

	//<!ELEMENT collection (column*)>
	//  <!ATTLIST collection name CDATA #REQUIRED>
	//  <!ATTLIST collection role CDATA #IMPLIED>
	//  <!ATTLIST collection column CDATA #IMPLIED>
	//  <!ATTLIST collection length CDATA #IMPLIED>
	//  <!ATTLIST collection not-null CDATA #IMPLIED>

	protected void emitCollectionInCompositeStr(int level) {
		map.emitPrefix(level);
		buf.append("<collection name=\"" )
		.append(name)
		.append("\" column=\"")
		.append(map.columnNameFor(name))
		.append("\">\n");
	}

	protected void getUidXML(int level, StringBuffer buf) {
		this.buf = buf;
		isUid = true; // to prevent being emitted twice
		if( type == String.class ) {
			emitStrUidStr(level);
		}
		else {
			emitIntUidStr(level);
		}
	}

	protected void getXMLinComposite( int level, StringBuffer buf ) {
		this.buf = buf;
		// basic types are OK in composite-elements
		emitPropertyStr(level);
	}

	protected void getXMLinArray( int level, StringBuffer buf ) {
		this.buf = buf;
		emitCollectionStrET(level, "elm", tynm);
	}

	protected void getXML( int level, StringBuffer buf ) {
		this.buf = buf;
		if( comment != null ) {
			emitPCommentStr(level, comment, name, type.getName());
		}
		emitPropertyStr(level);
	}
}






