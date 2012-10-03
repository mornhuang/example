//$Id: MapGenerator.java,v 1.2 2003/11/04 17:39:33 oneovthafew Exp $
package net.sf.hibernate.tool.class2hbm;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Vector;

import net.sf.hibernate.PersistentEnum;
import net.sf.hibernate.type.Type;
import net.sf.hibernate.type.TypeFactory;
import net.sf.hibernate.util.StringHelper;

/**
 * <P> MapGenerator provides a mechanism to produce a Hibernate XML
 * OR-Mapping from compiled classes. It does this using Java reflection to
 * find <b>properties</b> to be persisted in the classes, and using the
 * types of the properties to further guide the reflection.
 * <P> The usual way to use MapGenerator is to place your compiled
 * classes on the classpath, and start Java in the MapGenerator
 * static main() method. As arguments you can either supply all of the
 * classes to be processed, or the single argument <code>--interact</code>
 * which will provide an interactive prompt/response console. Using this
 * mode you can set the UID property name for each class using the
 * <code>uid=XXX</code> command where XXX is the UID property name. Other
 * command alternatives are simply a fully qualified class name, or the
 * command <code>done</code> which emits the XML and terminates.
 *
 * <P> MapGenerator will reject classes that are not
 * <b>hibernate perisitable</b>. To be hibernate persistable a class must
 * not be a primitive type, an array, an interface, or a nested class, and
 * it must have a default (zero argument) constructor.
 *
 * <P> MapGenerator will climb the superclass chain of all added
 * classes attempting to add as many hibernate perisitable superclasses
 * as possible to the same database table. The search stops as soon as a
 * property is found that has a name appearing on the list of candidate
 * UID names, and has type String, Long, or long.
 *
 * <P> Properties are discovered when there are two methods in the class,
 * a setter and a getter, where the type of the setter's single argument is
 * the same as the return type of the zero argument getter, and the setter
 * returns <code>void</code>. Furthermore, the setter's name must start with
 * the string "set" and either the getter's name starts with "get" or the
 * getter's name starts with "is" and the type of the property is
 * <code>boolean</code>. In either case, the remainder of their names must
 * match. This matching portion is the name of the property, except that the
 * initial character of the property name is made lower case if the second
 * letter is lower case.
 *
 * <P> The rules for determing the database type of each property are as
 * follows. If the Java type is Hibernate.basic(), then the property is a
 * simple column of that type. For hibernate.type.Type custom types and
 * PersistentEnum a simple column is used as well. If the property type
 * is an array, then a Hibernate array is used, and MapGenerator
 * attempts to reflect on the array element type. If the property has
 * type java.util.List, java.util.Map, or java.util.Set, then the
 * corresponding Hibernate types are used, but MapGenerator cannot
 * further process the insides of these types. If the property's type is
 * any other class, MapGenerator defers the decision on the database
 * representation until all classes have been processed (i.e., until
 * <code>done</code> is typed at the interactive prompt. At this point, if
 * the class was discovered through the superclass search described above,
 * then the property is an association (many-to-one). If the class has any
 * properties, then it is a component. Otherwise it is serializable, or
 * not persistable.
 *
 *
 * @version 	1.x
 * @author		<a href="mailto:doug.currie@alum.mit.edu">e</a>
 */
public class MapGenerator {

	private static String defaultKeys[] = { "uid", "UID", "id", "ID", "key", "KEY", "pk", "PK" };

	private ClassLoader classLoader;
	private boolean verbose = true;

	public static void main(String[] args) {

		Writer outputWriter=null;
		int len = args.length;
		if( len == 0 ) {
			System.out.println( "<!-- No args provided, no classes reflected! -->" );
		}
		else if( len == 1 && "--interact".equals(args[0]) ){
			MapGenerator map = new MapGenerator( null, ClassLoader.getSystemClassLoader() );
			map.interact();
		}
		else {
			//MapGenerator map = new MapGenerator( args );
			MapGenerator map = new MapGenerator( null, ClassLoader.getSystemClassLoader() );
			for( int i=0; i<len; i++ ) {
				if(args[i].startsWith("--")) {

					/* Shouldn't all of these options be else if? (Everman) */
					if(args[i].startsWith("--setUID=")) {
						map.setUID( args[i].substring(9) );
					}
					else if(args[i].startsWith("--addUID=")) {
						map.addUID( args[i].substring(9) );
					}
					else if(args[i].startsWith("--depth=")) {
						try {
							map.maxDepth = Integer.parseInt( args[i].substring(8) );
						}
						catch( NumberFormatException e ) {
							System.err.println("<!-- Can't set maxDepth "
							+ e.getMessage() + "-->");
						}
					}
					else if(args[i].equals("--quiet")) {
						map.verbose = false;
					}
					else if(args[i].startsWith("--output=")) {
						outputWriter = makeWriter( args[i].substring(9) );
					}

					//Next option added by Eric Everman 5-16-2002
					//See protected abstractClasses for details
					else if(args[i].startsWith("--abstract=")) {
						map.abstractClasses.put(args[i].substring(11), StringHelper.EMPTY_STRING);
					}
				}
				else {
					map.addClass( args[i], true ); // ignore verbose here?
				}
			}
			map.writeXML(outputWriter);
		}
	}

	public void writeXML(Writer outputWriter) {
		String xml = getXML();
		if( outputWriter != null ) {
			try { outputWriter.write( xml ); outputWriter.flush(); }
			catch( Exception e ) { outputWriter = null; }
		}
		if( verbose == true || outputWriter == null ) {
			System.out.println( xml );
		}
	}

	private static FileWriter makeWriter( String fileName ) {
		try {
			FileWriter fw = new FileWriter( fileName );
			return fw;
		}
		catch(Exception e) {
			System.err.println("<!-- Error making FileWriter " + e.getMessage() + "-->");
			//e.printStackTrace();
			return null;
		}
	}

	/** how low will you go? the depth of component nesting followed
	 */
	protected int maxDepth = 0;

	/** candidate UID property names; presence of one of these with a
	 *  supported type (String, Long, long) will stop
	 *  the reflect code from chasing the superclass chain any further
	 *  except to find additional properties (not classes)
	 */
	protected String[] niceKeys = defaultKeys;

	/** the XML we make; this buffer is shared by all string emitters
	 *  created by this MapGenerator instance
	 */
	protected StringBuffer buf;

	/** a cache of seen reflected classes; Class -> ReflectedClass
	 *  <br>also necessary to avoid infinite regress
	 */
	protected Hashtable rClasses;

	/**
	 * <p>A list of class names which are treated as 'abstract' in that they are not
	 * allowed to be root classes.  Classes added to this list will have their
	 * properties (and their superclass properties) mapped to any subclasses
	 * extending the base classes listed here.
	 *
	 * Documentation notes:
	 * --abstract=<fully.qualified.class.name>
	 * use to add a class which will have its properties persisted only by its
	 * subclasses.  Multiple --abstract entries can be used, but they must all
	 * come before any class names to be mapped.  Classes listed by this option
	 * do not need to actually be abstract in the Java language sense.
	 *
	 *
	 * <p>Note that the name abstract is somewhat misleading as abstract classes
	 * can otherwise be handled as a top level class by Hibernate.
	 *
	 * @author Eric Everman 5-16-2002
	 */
	protected Hashtable abstractClasses = new Hashtable(5);

	/** a cache of emitted components for the current property; Class -> Integer
	 *  <br>also necessary to avoid infinite regress by tracking
	 *  the depth of data recursion
	 */
	protected Hashtable cycleBuster;

	/** just to prevent duplicate table names; String -> Integer
	 */
	private Hashtable usedTableNames;

	/** just to prevent duplicate column names; String -> Integer
	 */
	private Hashtable usedColumnNames;

	/** the top level collections; <br> currently just a placeholder
	 *  because top level collections are not supported
	 */
	private Vector entities;

	/** the top level (most souper) ReflectedClasses;
	 *  <br>these are the classes eventually dumped as <class> in the XML
	 */
	private Vector roots;

	private char[] prefix = StringHelper.repeat("\t", 100).toCharArray();

	/** the only MapGenerator constructor
	 *
	 * @param className an array of fully specified class names to add, or null
	 */
	public MapGenerator( String[] className, ClassLoader loader ) {
		setClassLoader(loader);
		reset();
		if( className != null ) {
			int len = className.length;
			for( int i=0; i<len; i++ ) {
				addClass( className[i], true );
			}
		}
	}

	private String interact_usage = "usage: class.name | uid=<newUID> | done";

	/** won't somebody please make a GUI?
	 */
	private void interact() {
		Reader r = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(r);
		st.wordChars( 33, 126 );
		System.out.println(interact_usage);
		System.out.print("? ");
		boolean allIsWell = true;
		while( allIsWell ) {
			int tt;
			try {
				tt = st.nextToken();
			}
			catch( Exception e ) {
				System.out.println("IOException; done");
				tt = StreamTokenizer.TT_EOF;
			}
			switch( tt ) {
				case StreamTokenizer.TT_EOF: allIsWell = false; break;
				case StreamTokenizer.TT_EOL: st.eolIsSignificant(false); break;
				case StreamTokenizer.TT_NUMBER:
				System.out.println("number!? ");
				System.out.println(interact_usage);
				System.out.print("? ");
				break;
				case StreamTokenizer.TT_WORD:
				String name = st.sval;
				if( "done".equals(name) ) {
					allIsWell = false;
				}
				else if( name.startsWith("uid=") ) {
					String uid = name.substring(4);
					System.out.print("niceKey(" + uid + ")=>" ); // debug
					addUID( uid );
					for( int i=0; i<niceKeys.length; i++ ) {
						System.out.print(" ");
						System.out.print(niceKeys[i]);
					}
					System.out.print("\n? ");
				}
				else {
					System.out.println("addClass(" + name + ")"); // debug
					addClass( name, true );
					System.out.print("? ");
				}
				break;
				default: /* ignore */ break;
			}
		}
		System.out.println( getXML() );
	}

	/**
	 * start over
	 */
	public void reset() {
		this.buf = new StringBuffer();
		this.roots = new Vector();
		this.entities = new Vector();
		this.rClasses = new Hashtable();
		this.usedTableNames = new Hashtable();
		buf.append("<?xml version=\"1.0\"?>\n")
		.append("<!DOCTYPE hibernate-mapping PUBLIC\n")
		.append("\t\"-//Hibernate/Hibernate Mapping DTD 2.0//EN\"\n")
		.append("\t\"http://hibernate.sourceforge.net/hibernate-mapping-2.0.dtd\">\n");
	}

	/**
	 * add a class to the map and reflect upon it
	 * @param className a fully qualified class name in the class path
	 * @param verbose squawk (as a comment into the final XML) if
	 *                  there are problems with this class
	 */
	public void addClass( String className, boolean verbose ) {
		Class clazz = checkClassNamed( className, verbose );
		if( clazz != null ) {
			ReflectedClass rc = reallyAdd( clazz, verbose );
			if( rc == null && verbose ) {
				buf.append("<!-- ")
				.append(clazz.getName())
				.append(" cannot be added, no UID found! -->\n");
			}
		}
	}

	/**
	 * set the list of candidate UIDs to a single name
	 * @param uid the new candidate UID
	 */
	public void setUID( String uid ) {
		String[] uida = { uid };
		niceKeys = uida;
	}

	/**
	 * add a new name to the front of the list of candidate UIDs
	 * @param uid the new candidate UID
	 */
	public void addUID( String uid ) {
		String[] uida = new String[niceKeys.length + 1];
		for( int i=0; i<niceKeys.length; i++ ) {
			uida[i+1] = niceKeys[i];
		}
		uida[0] = uid;
		niceKeys = uida;
		// debug
		for( int i=0; i<niceKeys.length; i++ ) {
			System.out.print(" ");
			System.out.print(niceKeys[i]);
		}
	}

	/** used by gui */
	public ReflectedClass[] getRoots() {
		ReflectedClass[] v = new ReflectedClass[roots.size()];
		roots.copyInto(v);
		return v;
	}

	private ReflectedClass reallyAdd( Class clazz, boolean verbose ) {
		ReflectedClass rc = (ReflectedClass) rClasses.get(clazz);
		Class superclass = clazz.getSuperclass();
		if( rc == null ) {
			// not already added
			rc = new ReflectedClass( this, clazz );
		}
		else if( rc.isPersistent() ) {
			if( verbose ) {
				buf.append("<!-- ")
				.append(clazz.getName())
				.append(" already added -->\n");
			}
			return rc;
		}
		rc.setPersistent( true );
		if( rc.getUidProp( niceKeys ) != null ) {
			// it's a root
			// add properties from superclasses
			rc.addSuperclassProps();
			roots.add( rc );
		}
		else if ( // added by Eric Everman on 5-16-2002
			superclass!=null &&
			abstractClasses.containsKey( superclass.getName() )
		) {
			//The user has requested that class diving go no farther by
			//marking the next class up the chain as abstract.

			rc.addSuperclassProps();    //add superclass props

			if( rc.getUidProp( niceKeys ) != null ) {
				//Thank goodness, its persistable
				roots.add( rc );
			}
			else {
				//User specified an abstract 'root' class which is not persistable
				rc.setPersistent( false );
				return null;    //generic can't add message generated by addClass
			}

		}
		else {
			// add superclasses 'til root is found
			if( superclass == null ) {
				// can't add to roots because didn't find UID
				// rc isn't persistent after all
				rc.setPersistent( false );
				// report!? (addClass will do it when it sees null)
				return null;
			}
			else {
				ReflectedClass sup = reallyAdd( superclass, false ); // recurse
				if( sup == null ) {
					// rc isn't persistent after all
					rc.setPersistent( false );
					return null;
				}
				sup.addReflectedClass( rc );
			}
		}
		return rc;
	}

	class PEntity {
		// we don't make these yet
		// maybe someday toplevel collections will be supported
		void getXML( int level, boolean isRoot ) {
		}
	}

	/** used to make unique table and column names
	 * @param best is the desired name
	 * @param h is the uniqifying hashtable
	 * @return the unique name
	 */
	protected String nextName( String best, Hashtable h ) {
		Integer seen = (Integer )h.get( best );
		if( seen == null ) {
			// all set
			h.put( best, new Integer(1) );
			return best;
		}
		h.put( best, new Integer(seen.intValue() + 1) );
		return (best + "_" + seen);
	}
	protected String tableNameFor( String name ) {
		String best = StringHelper.unqualify( name );
		// TO DO check for illegal names?
		// check for duplicate names...
		return nextName( best, usedTableNames );
	}
	protected String columnNameFor( String best ) {
		// TO DO check for illegal names?
		// check for duplicate names...
		return nextName( best, usedColumnNames );
	}

	/** adds spaces to the front of lines in buf for indentation
	 * @param n indentation level
	 */
	protected void emitPrefix( int n ) {
		int e = n * 1; // configurable?
		if( e > prefix.length ) e = prefix.length;
		buf.append( prefix, 0, e );
	}

	/** after all classes are added
	 * @return the XML
	 */
	public String getXML() {
		buf.append("<hibernate-mapping>\n");
		// xml the common entities, e.g., keyed collections
		int eln = entities.size();
		for( int i=0; i<eln; i++ ) {
			PEntity pet = (PEntity )roots.get(i);
			usedColumnNames = new Hashtable();
			pet.getXML( 1, true );
		}
		// xml the classes
		int len = roots.size();
		for( int i=0; i<len; i++ ) {
			ReflectedClass rc = (ReflectedClass )roots.get(i);
			usedColumnNames = new Hashtable();
			rc.getXML(1);
		}
		buf.append("</hibernate-mapping>\n");
		return buf.toString();
	}

	private Class checkClassNamed( String className, boolean v ) {
		try {
			Class clazz = classLoader.loadClass( className );
			return checkClass( clazz, className, v );
		}
		catch( Exception e ) {
			if(v) buf.append("<!-- Class ")
			.append(className)
			.append(" gave exception ")
			.append(e)
			.append(" -->\n");
			return null;
		}
	}

	/** this is the factory to make a ReflectedProperty
	 * <br>using this factory will insure that the property
	 * types are inferred consistently
	 *
	 * @param name the property name
	 * @param cls the property class
	 * @return the new ReflectedProperty
	 */
	protected ReflectedProperty makeProperty( String name, Class cls ) {
		String tynm = cls.getName();
		Type htyp = TypeFactory.basic(tynm);

		if ( htyp != null ) {
			return new ReflectedProperty( name, cls, this, "basic" );
		}
		else if ( cls.isArray() ) {
			return new ReflectedArrayProperty( name, cls, this );
		}
		/*else if ( Type.class.isAssignableFrom(cls) ) {
			return new ReflectedProperty( name, cls, this, "custom" );
		}*/
		else if ( PersistentEnum.class.isAssignableFrom(cls) ) {
			return new ReflectedProperty( name, cls, this, "enum" );
		}
		// differs from Hibernate.auto...
		else if ( java.util.List.class.isAssignableFrom(cls) ) {
			return new ReflectedListProperty( name, cls, this );
		}
		else if ( java.util.Map.class.isAssignableFrom(cls) ) {
			return new ReflectedMapProperty( name, cls, this );
		}
		else if ( java.util.Set.class.isAssignableFrom(cls) ) {
			return new ReflectedSetProperty( name, cls, this );
		}
		else if ( java.util.Collection.class.isAssignableFrom(cls) ) {
			return new ReflectedCollectionProperty( name, cls, this );
		}
		// leave the hard stuff for ReflectedComponent...
		else {
			Class comp = checkComponent( cls, tynm, true );
			if( comp == null ) {
				//return null; // hibernate can't handle
				return new ReflectedProperty( name, cls, this, "custom" );
			}
			ReflectedClass rc = (ReflectedClass )rClasses.get(comp);
			if( rc == null ) {
				// not already processed (avoid infinite regress!)
				rc = new ReflectedClass( this, comp );
			}
			return new ReflectedComponent( name, cls, this, rc );
		}
	}

	/** verify that a class is <b>hibernate-persistable</b>
	 *
	 * @param clazz the class to ckeck
	 * @param className the name of the class for error reporting
	 * @param v verbose - should be true for supplied classes,
	 *        but false for chased superclasses
	 * @return the class clazz if it is hibernate-persistable, else null
	 */
	protected Class checkClass( Class clazz, String className, boolean v ) {
		return checkClCoGuts( clazz, className, v, "<!-- Class " );
	}
	/** verify that a class is <b>hibernate-persistable</b> as a component
	 *
	 * @param clazz the class to ckeck
	 * @param className the name of the class for error reporting
	 * @param v verbose - should probably always be true,
	 *        but can be false to ignore the property's status
	 * @return the class clazz if it is hibernate-persistable as a component, else null
	 */
	protected Class checkComponent( Class clazz, String className, boolean v ) {
		return checkClCoGuts( clazz, className, v, "<!-- Component " );
	}
	private Class checkClCoGuts( Class clazz, String className, boolean v, String c ) {
		if( clazz.isPrimitive() ) {
			if(v) buf.append(c).append(className).append(" is a primitive! -->\n");
			return null;
		}
		if( clazz.isArray() ) {
			if(v) buf.append(c).append(className).append(" is an array! -->\n");
			return null;
		}
		if( clazz.isInterface() ) {
			if(v) buf.append(c).append(className).append(" is an interface! -->\n");
			return null;
		}
		if( clazz.getDeclaringClass() != null ) {
			if(v) buf.append(c).append(className).append(" is a nested class! -->\n");
			return null;
		}
		/* per Gavin, abstract is OK
		int clsmods = clazz.getModifiers();
		if( Modifier.isAbstract( clsmods ) ) {
			if(v) buf.append(c).append(className).append(" is abstract! -->\n");
			return null;
		}
		 */
		// must have a default constructor
		Constructor z;
		try {
			// try the public constructor
			z = clazz.getConstructor(new Class[0]);
		}
		catch( NoSuchMethodException e ) {
			z = null;
		}
		catch( SecurityException e ) {
			// ugh
			if(v) buf.append(c)
			.append(className)
			.append(" cannot be reflected due to a SecurityException! -->\n");
			return null;
		}
		// no public default constructor, try harder...
		if( z == null ) {
			try {
				Constructor[] cs = clazz.getDeclaredConstructors();
				for ( int i=0; i<cs.length; i++ ) {
					if ( cs[i].getParameterTypes().length==0 ) {
						z = cs[i];
						break;
					}
				}
			}
			catch( SecurityException e ) {
				// ugh
				if(v) buf.append(c)
				.append(className)
				.append(" cannot be reflected due to a SecurityException! -->\n");
				return null;
			}
		}
		if( z == null ) {
			if(v) buf.append(c)
			.append(className)
			.append(" has no 0-arg constructor! -->\n");
			return null;
		}
		// the class is OK to perisist
		return clazz;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}


}

/* TO DO
 * new dtd accommodated?
 * length property for string/blob/binary?
 * top level collections (see PEntity and entities)
 * (nice)...
 * maybe also give access to ReflectedClass so callers can set...
 *   UID property name and generator
 *   (or heuristically?)...
 *     get real UID, index, and data colummns for collections
 *     nesting into collections
 *     optimistic locking
 *     ...
 *
 */







