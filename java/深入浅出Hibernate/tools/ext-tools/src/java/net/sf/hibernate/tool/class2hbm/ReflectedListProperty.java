package net.sf.hibernate.tool.class2hbm;

/**
 * @version 	1.x
 * @author		<a href="mailto:doug.currie@alum.mit.edu">e</a>
 */
class ReflectedListProperty extends ReflectedProperty {
	
	/**
	 * Constructor for ReflectedListProperty.
	 * @param name
	 * @param type
	 */
	ReflectedListProperty(String name, Class type, MapGenerator map ) {
		super(name, type, map, "list");
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
		emitCollectionStrL(level, "list", name, true);
		emitCollectionStrK(level+1, "uid");
		emitCollectionStrI(level+1, "i");
		emitCollectionStrE(level+1, "elm");
		emitCollectionStrR(level, "list");
	}
}






