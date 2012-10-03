package net.sf.hibernate.tool.class2hbm;

/**
 * @version 	1.x
 * @author		<a href="mailto:doug.currie@alum.mit.edu">e</a>
 */
class ReflectedMapProperty extends ReflectedProperty {
	
	/**
	 * Constructor for ReflectedMapProperty.
	 * @param name
	 * @param type
	 */
	ReflectedMapProperty(String name, Class type, MapGenerator map) {
		super(name, type, map, "map");
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
		emitCollectionStrL(level, "map", name, true);
		emitCollectionStrK(level+1, "uid");
		emitCollectionStrIT(level+1, "key", "string");
		emitCollectionStrE(level+1, "elm");
		emitCollectionStrR(level, "map");
	}
}






