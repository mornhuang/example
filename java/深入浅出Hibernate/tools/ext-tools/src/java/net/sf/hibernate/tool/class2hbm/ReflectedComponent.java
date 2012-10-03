package net.sf.hibernate.tool.class2hbm;

import java.io.Serializable;

/**
 * @version 	1.x
 * @author		<a href="mailto:doug.currie@alum.mit.edu">e</a>
 */
class ReflectedComponent extends ReflectedProperty {
	
	private static final int KIND_UNKNOWN		= 0;
	private static final int KIND_BASIC			= 1; // nothing or serializable
	private static final int KIND_ASSOCIATION	= 2;
	private static final int KIND_COMPONENT		= 3;
	
	private ReflectedClass component;	// my component
	private int kind = KIND_UNKNOWN;
	
	protected ReflectedComponent( String name, Class type
	, MapGenerator map, ReflectedClass comp )
	{
		super( name, type, map, "prospective-component" );
		this.component = comp;
	}
	
	private int determineKind() {
		// this decision has to be delayed to avoid circularities
		// e.g., a component which includes itself will not have
		//       access to this info until it is fully processed
		// but we cache it here...
		if( kind != KIND_UNKNOWN ) return kind;
		// is the prospective component a persistent class
		if( component.isPersistent() ) {
			// make it an association
			comment = "association";
			kind = KIND_ASSOCIATION;
			return KIND_ASSOCIATION;
		}
		// does the prospective component have any properties?
		// first let it look in superclasses
		component.addSuperclassProps();
		if( component.hasProperties() ) {
			// make it a component
			comment = "component";
			kind = KIND_COMPONENT;
			return KIND_COMPONENT;
		}
		if ( Serializable.class.isAssignableFrom(type) ) {
			// make it a serializable
			tynm = "serializable";
			comment = "serializable";
			kind = KIND_BASIC;
			return KIND_BASIC;
		}
		else {
			buf.append("<!--  BUG unknown kind! name=\"")
			.append(name)
			.append("\" type=\"")
			.append(type.getName())
			.append("\" -->\n");
			kind = KIND_BASIC;
			return KIND_BASIC;
		}
	}
	
	protected void getXMLinArray( int level, StringBuffer buf ) {
		this.buf = buf;
		switch( determineKind() ) {
			case KIND_BASIC:
			super.getXML( level, buf );
			break;
			case KIND_COMPONENT:
			getXMLas(level, true);
			break;
			case KIND_ASSOCIATION:
			component.getXMLasMany2Many(level);
			break;
			default:
			emitPCommentStr(level, "BUG! unknown component kind", name, type.getName());
			break;
		}
	}
	
	protected void getXMLinComposite( int level, StringBuffer buf ) {
		//this.buf = buf;
		getXMLinArray( level, buf );
	}
	
	protected void emitMany2OneStr(int level) {
		map.emitPrefix(level);
		buf.append("<many-to-one name=\"" )
		.append(name)
		.append("\" column=\"")
		.append(map.columnNameFor(name))
		.append("\" class=\"")
		.append(tynm)
		.append("\"/>\n");
	}
	
	protected void getXML( int level, StringBuffer buf ) {
		this.buf = buf;
		switch( determineKind() ) {
			case KIND_BASIC:
			super.getXML( level, buf );
			return;
			case KIND_COMPONENT:
			getXMLas(level, false); //go on...
			return;
			case KIND_ASSOCIATION:
			emitMany2OneStr(level);
			return;
			default:
			emitPCommentStr(level, "BUG! unknown component kind", name, type.getName());
			return;
		}
	}
	
	private void getXMLas( int level, boolean isComposite ) {
		if( component != null ) {
			Integer x = (Integer )map.cycleBuster.get( component );
			if( x != null ) {
				// we're nested
				int i = x.intValue();
				if( i >= map.maxDepth ) {
					emitPCommentStr(level, "component too deeply nested", name, type.getName());
					return; // bail!
				}
				else {
					map.cycleBuster.put( component, new Integer( i + 1 ) );
				}
			}
			else {
				map.cycleBuster.put( component, new Integer( 1 ) );
			}
			if( isComposite ) {
				component.getXMLasComposite(level);
			}
			else {
				component.getXMLasComponent( level, name );
			}
		}
		else {
			emitPCommentStr(level, "not component?", name, type.getName());
		}
	}
	
}






