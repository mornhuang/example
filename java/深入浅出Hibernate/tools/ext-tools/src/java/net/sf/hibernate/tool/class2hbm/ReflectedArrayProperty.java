package net.sf.hibernate.tool.class2hbm;

/**
 * @version 	1.x
 * @author		<a href="mailto:doug.currie@alum.mit.edu">e</a>
 */
class ReflectedArrayProperty extends ReflectedProperty {
	
	protected ReflectedProperty element;
	
	/**
	 * Constructor for ReflectedArrayProperty.
	 * @param name
	 * @param type
	 * @param comment
	 */
	ReflectedArrayProperty( String name, Class type, MapGenerator map ) {
		super(name, type, map, "array");
		this.element = null;
		Class elmcls = type.getComponentType();
		if( elmcls == null ) {
			// huh! not an array after all!?
			// use type.getName() in case tynm modified
			emitPCommentStr( 1,"NOT an array!?", name, type.getName() );
			return;
		}
		String roleName = map.tableNameFor(elmcls.getName());
		this.element = map.makeProperty( roleName, elmcls );
		// TO DO
		// if elmcls is a collection, it would be nice to make
		// the top level collection template here with role name roleName
	}
	
	protected void getXMLinArray( int level, StringBuffer buf ) {
		this.buf = buf;
		emitPCommentStr(level, "BUG!?: array of array ", name, element.name);
	}
	
	protected void getXMLinComposite( int level, StringBuffer buf ) {
		this.buf = buf;
		emitCollectionInCompositeStr(level);
	}
	
	//<!ELEMENT array (key, index, (element|one-to-many|many-to-many|subcollection|composite-element))>
	//  <!ATTLIST array role CDATA #REQUIRED>
	//  <!ATTLIST array table CDATA #IMPLIED>
	
	protected void getXML( int level, StringBuffer buf ) {
		this.buf = buf;
		if( comment != null ) {
			emitPCommentStr(level, comment, name, type.getName());
		}
		if( element == null ) {
			emitPCommentStr(level, "BUG array of unknown kind!", name, element.name);
		}
		else {
			boolean p = element.type.isPrimitive();
			emitCollectionStrL(level, p ? "primitive-array" : "array", name, false);
			emitCollectionStrK(level+1, "uid");
			emitCollectionStrI(level+1, "idx");
			element.getXMLinArray( level+1, buf );
			emitCollectionStrR(level, p ? "primitive-array" : "array");
		}
	}
}






