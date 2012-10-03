package net.sf.hibernate.tool.class2hbm;

import java.beans.Introspector;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import net.sf.hibernate.util.StringHelper;

/** a ReflectedClass is created by MapGenerator instances for
 *  every class encountered, from map.addClass(), while chasing the
 *  superclass chain, or grokking properties
 *
 * @version 	1.x
 * @author		<a href="mailto:doug.currie@alum.mit.edu">e</a>
 */
public class ReflectedClass {
	
	private MapGenerator map;	// my containing map
	private StringBuffer buf;// shared buffer provided by MapGenerator
	private Class clazz; 	// my class
	private Vector subs; 	// my ReflectedClass subclasses
	private Hashtable props;// my ReflectedProperty(s) keyed by property name
	private ReflectedProperty uid;
	private boolean persistent;	// true => persistent class or subclass
	// false => component or serializable
	
	private Hashtable setprops;
	private Hashtable getprops;
	
	protected ReflectedClass( MapGenerator map, Class cls ) {
		this.clazz = cls;
		this.map = map;
		this.subs = new Vector();
		this.uid = null;
		this.persistent = false;		// default to component until told otherwise
		map.rClasses.put( cls, this );	// this must happen to avoid infinite regress...
		this.reflect();					// here
	}
	
	/** used by gui */
	public String getName() {
		return clazz.getName();
	}
	
	/** used by gui */
	public ReflectedProperty[] getProperties() {
		ReflectedProperty[] rp = new ReflectedProperty[props.size()];
		Enumeration e = props.elements();
		int i = 0;
		while( e.hasMoreElements() ) {
			rp[i++] = (ReflectedProperty )e.nextElement();
		}
		return rp;
	}
	
	/** used by gui */
	public ReflectedClass[] getSubclasses() {
		ReflectedClass[] v = new ReflectedClass[subs.size()];
		subs.copyInto(v);
		return v;
	}
	
	protected void addReflectedClass( ReflectedClass rc ) {
		subs.add( rc );
	}
	
	/* no longer used; see getUidProp
	protected boolean isRoot() {
		return ( uid != null );
	}*/
	
	/** has the class been deemed hibernate-persistable? <br>
	 *  if not, it may still be useful as a component or may be serializable
	 */
	protected boolean isPersistent() {
		return this.persistent;
	}
	
	/** called by MappinByReflection when this class has been
	 *  deemed hibernate-persistable
	 */
	protected void setPersistent( boolean p ) {
		this.persistent = p;
	}
	
	/** does this class have any properties? <br> this is not reliable
	 *  until addSuperclassProps() is called once first
	 */
	protected boolean hasProperties() {
		return ( props.size() != 0 );
	}
	
	/** add to this class the properties of all its superclasses
	 */
	protected void addSuperclassProps() {
		// add properties from superclasses
		addSuperclassProps( clazz.getSuperclass() );
	}	// with helper...
	private void addSuperclassProps( Class supz ) {
		if( supz != null ) {
			reflectClass( supz );
			addSuperclassProps( supz.getSuperclass() ); // recurse
		}
	}
	
	/** dump the OR-Mapping XML for a root class and all of its subclasses
	 *  that share a table and UID
	 */
	protected void getXML(int level) {
		// dumps in map.buf
		this.buf = map.buf;
		String name = clazz.getName();
		String shortName = StringHelper.unqualify(name);
		// debug...
		map.emitPrefix(level);;
		buf.append("<!-- ")
		.append(name)
		.append(" root -->\n");
		// a root class -- polymorphic style
		map.emitPrefix(level);;
		buf.append("<class name=\"")
		.append(name)
		.append("\" table=\"")
		.append(map.tableNameFor(name))
		.append((subs.size() > 0) ? "\" discriminator-value=\"" + shortName : "")
		.append("\">\n");
		// first find the uid
		if( uid == null ) {
			// report error
			map.emitPrefix(level);
			buf.append("<!-- NO UID!? class=\"")
			.append(name)
			.append("\" -->\n");
		}
		else {
			uid.getUidXML(level+1, buf);
		}
		//the discriminator element
		if ( subs.size() > 0 ) {
			map.emitPrefix(level+1);
			buf.append("<discriminator column=\"subclass\" type=\"string\"/>\n");
		}
		// now the properties already classified by heuristic
		Enumeration ps = props.elements();
		while( ps.hasMoreElements() ) {
			ReflectedProperty prop = (ReflectedProperty )ps.nextElement();
			if( prop.isUid ) continue; // done above
			// we're root and top component level
			map.cycleBuster = new Hashtable();
			prop.getXML( level+1, buf );
		}
		// now the subclasses
		int len = subs.size();
		for( int i=0; i<len; i++ ) {
			ReflectedClass rsc = (ReflectedClass )subs.get(i);
			rsc.getXMLasSubclass(level+1);
		}
		map.emitPrefix(level);;
		buf.append("</class>\n");
	}
	
	/** dump the OR-Mapping XML for a non-root class and all of its
	 *  subclasses that share its table and UID
	 */
	protected void getXMLasSubclass(int level) {
		// dumps in map.buf
		this.buf = map.buf;
		String name = clazz.getName();
		// debug...
		map.emitPrefix(level);;
		buf.append("<!-- ")
		.append(name)
		.append(" -->\n");
		// a sub class -- polymorphic style
		map.emitPrefix(level);;
		buf.append("<subclass name=\"")
		.append(name)
		.append("\">\n");
		// now the properties already classified by heuristic
		Enumeration ps = props.elements();
		while( ps.hasMoreElements() ) {
			ReflectedProperty prop = (ReflectedProperty )ps.nextElement();
			if( prop.isUid ) continue; // done above
			prop.getXML( level+1, buf );
		}
		// now the subclasses
		int len = subs.size();
		for( int i=0; i<len; i++ ) {
			ReflectedClass rsc = (ReflectedClass )subs.get(i);
			rsc.getXMLasSubclass(level+1);
		}
		map.emitPrefix(level);;
		buf.append("</subclass>\n");
	}
	
	/** dump the OR-Mapping XML for a class used as a component
	 *
	 * @param propName the property name of the property containing this component
	 */
	protected void getXMLasComponent( int level, String propName ) {
		// dumps in buf
		this.buf = map.buf;
		String name = clazz.getName();
		/* debug...
		map.emitPrefix(level);;
		buf.append("<!-- ")
		.append(name)
		.append(" -->\n");*/
		// a component
		map.emitPrefix(level);;
		buf.append("<component name=\"")
		.append(propName)
		.append("\" class=\"")
		.append(name)
		.append("\">\n");
		// now the properties already classified by heuristic
		Enumeration ps = props.elements();
		while( ps.hasMoreElements() ) {
			ReflectedProperty prop = (ReflectedProperty )ps.nextElement();
			prop.getXML( level+1, buf );
		}
		// NOT the subclasses
		map.emitPrefix(level);;
		buf.append("</component>\n");
	}
	
	protected void getXMLasComposite(int level) {
		// dumps in buf
		this.buf = map.buf;
		String name = clazz.getName();
		// a composite-element
		map.emitPrefix(level);;
		buf.append("<composite-element class=\"")
		.append(name)
		.append("\">\n");
		// now the properties already classified by heuristic
		Enumeration ps = props.elements();
		while( ps.hasMoreElements() ) {
			ReflectedProperty prop = (ReflectedProperty )ps.nextElement();
			// call a restricted form of getXML that disallows inline
			// collections and uses composite-element instead of component
			prop.getXMLinComposite( level, buf );
		}
		// NOT the subclasses
		map.emitPrefix(level);;
		buf.append("</composite-element>\n");
	}
	
	protected void getXMLasMany2Many(int level) {
		// dumps in buf
		this.buf = map.buf;
		String name = clazz.getName();
		// a many-to-many
		map.emitPrefix(level);;
		buf.append("<many-to-many class=\"" )
		.append(name)
		.append("\" column=\"")
		.append(map.columnNameFor(name))
		.append("\"/>\n");
	}
	
	/** called to determine if the class has a UID property
	 *
	 * @param niceKeys an array of candidate UID names
	 * @return the UID ReflectedProperty
	 */
	protected ReflectedProperty getUidProp( String[] niceKeys ) {
		if( uid != null ) return uid; // cache
		// ugh; we'll try some things...
		ReflectedProperty p;
		// try a prop named identically to a niceKey[]
		for( int i=0; i<niceKeys.length; i++ ) {
			p = (ReflectedProperty )props.get(niceKeys[i]);
			// TO DO is isUidOK OK?
			if( p != null && p.isUidOK() ) {
				uid = p;
				break;
			}
		}
		/* now that we're using this for finding roots this tries too hard?...
		if( uid != null ) return uid;
		// try a prop whose name starts with a niceKey[]
		for( int i=0; i<niceKeys.length; i++ ) {
			Enumeration ps = props.elements();
			while( ps.hasMoreElements() ) {
				p = (ReflectedProperty )ps.nextElement();
				if( p.isUidOK() && p.name.startsWith(niceKeys[i])) {
					uid = p;
					break;
				}
			}
		}
		if( uid != null ) return uid;
		// try a prop whose name ends with (capitalized) niceKey[]
		for( int i=0; i<niceKeys.length; i++ ) {
			String candidate = capitalize( niceKeys[i] );
			Enumeration ps = props.elements();
			while( ps.hasMoreElements() ) {
				p = (ReflectedProperty )ps.nextElement();
				if( p.isUidOK() && p.name.endsWith(candidate)) {
					uid = p;
					break;
				}
			}
		}
		if( uid != null ) return uid;
		// more to try ?
		// use anything with an appropriate type
		Enumeration ps = props.elements();
		while( ps.hasMoreElements() ) {
			p = (ReflectedProperty )ps.nextElement();
			if( p.isUidOK() ) {
				uid = p;
				break;
			}
		}*/
		return uid;
	}
	
	private void maybeAddProp( String name, Class type
	, Hashtable thisprops, Hashtable thatprops ) {
		Object ty = thatprops.get( name );
		if( ty == type ) {
			// a winner!
			thatprops.remove( name );
			ReflectedProperty prop = map.makeProperty( name, type );
			if (prop!=null) props.put( name, prop );
		}
		else {
			// maybe later
			thisprops.put( name, type );
		}
	}
	
	private void addSetter( String name, Class type ) {
		maybeAddProp( name, type, setprops, getprops );
	}
	
	private void addGetter( String name, Class type ) {
		maybeAddProp( name, type, getprops, setprops );
	}
	
	/** called by the constructor
	 */
	private void reflect() {
		props = new Hashtable();
		setprops = new Hashtable();
		getprops = new Hashtable();
		reflectClass( clazz );
	}
	/** called to get the properties of a class,
	 *  or add properties from a superclass
	 */
	private void reflectClass( Class cls ) {
		// empty getprops and setprops if a getter and setter pair
		// should come from one class and not a class and its superclass
		try {
			Method[] ms = cls.getDeclaredMethods();
			for( int i=0; i<ms.length; i++ ) {
				Method m = ms[i];
				if( m == null ) continue;
				int mmod = m.getModifiers();
				if( Modifier.isStatic( mmod ) ) continue;
				Class argtyp[] = m.getParameterTypes();
				if( argtyp.length > 1 ) continue;
				String name    = m.getName();
				Class restyp   = m.getReturnType();
				if( argtyp.length == 1 ) {
					if( restyp == void.class && name.startsWith("set") ) {
						addSetter( Introspector.decapitalize(name.substring(3))
						, argtyp[0] );
					}
				}
				else { // argtyp.length == 0
					if( name.startsWith("get") ) {
						addGetter( Introspector.decapitalize(name.substring(3))
						, restyp );
					}
					else if( restyp == boolean.class && name.startsWith("is") ) {
						addGetter( Introspector.decapitalize(name.substring(2))
						, restyp );
					}
				}
			}
		}
		catch( SecurityException e ) {
			// ignore!?
		}
	}
	
	
}







