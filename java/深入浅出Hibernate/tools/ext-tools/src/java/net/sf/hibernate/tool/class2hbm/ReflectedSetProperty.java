package net.sf.hibernate.tool.class2hbm;

/**
 * @version 	1.x
 * @author		<a href="mailto:doug.currie@alum.mit.edu">e</a>
 */
class ReflectedSetProperty extends ReflectedProperty {
	
	/**
	 * Constructor for ReflectedListProperty.
	 * @param name
	 * @param type
	 */
	ReflectedSetProperty(String name, Class type, MapGenerator map) {
		super(name, type, map, "set");
	}
	
	protected void getXMLinArray( int level, StringBuffer buf ) {
		this.buf = buf;
		emitSubCollectionStr(level, name);
	}
	
	protected void getXMLinComposite( int level, StringBuffer buf ) {
		this.buf = buf;
		emitCollectionInCompositeStr(level);
	}
	
	protected void getXML( int level, StringBuffer buf ) {
		this.buf = buf;
		if( comment != null ) {
			emitPCommentStr(level, comment, name, type.getName());
		}
		emitCollectionStrL(level, "set", name, true);
		emitCollectionStrK(level+1, "uid");
		emitCollectionStrET(level+1, "elm", "string");
		emitCollectionStrR(level, "set");
	}
}






