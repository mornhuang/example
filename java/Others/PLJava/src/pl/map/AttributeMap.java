package pl.map;

import java.beans.*;
import java.lang.reflect.*;
import pl.*;

/**
 * This class maps a class attribute to a table column.
 *
 * @author: Artyom Rudoy
 */
public class AttributeMap
{
    private final static Object[] emptyParameters = new Object[0];
    private java.lang.String name = null;
    private ColumnMap columnMap = null;
    private java.lang.reflect.Method getter = null;
    private java.lang.reflect.Method setter = null;
    private AttributeMap reference = null;
    private boolean proxy = true;
    private XmlMap xmlMap = null;

    /**
     * Creates AttributeMap.
     */
    public AttributeMap(String name)
    {
        super();
        
        this.name = name;
    }

    /**
     * Returns the ColumnMap object for this attribute or <code>null</code>
     * if this attribute is not mapped to any column.
     *
     * @return pl.map.ColumnMap
     */
    public ColumnMap getColumnMap()
    {
        return columnMap;
    }
    
    public XmlMap getXmlMap()
    {
        return xmlMap;
    }

    /**
     * Returns the name of this attribute.
     *
     * @return java.lang.String
     */
    public java.lang.String getName()
    {
        return name;
    }
    
    public boolean isProxy()
    {
        return proxy;
    }
    
    public void setProxy(boolean proxy)
    {
        this.proxy = proxy;
    }

    /**
     * Return the AttributeMap for the referenced attribute of this attribute.
     *
     * @return pl.map.AttributeMap
     */
    public AttributeMap getReference()
    {
        return reference;
    }

    /**
     * Inits the getter and setter methods for this attribute.
     *
     * @param mapObjectClass the Class of the attribute's object
     */
    void initAccessors(Class mapObjectClass) throws PlException
    {
        try
        {
            PropertyDescriptor descriptor = null;
            BeanInfo info = java.beans.Introspector.getBeanInfo(mapObjectClass);
            for(int i = 0; i < info.getPropertyDescriptors().length; i++)
            {
                descriptor = info.getPropertyDescriptors()[i];
                if(descriptor.getName().equals(getName()))
                {
                    getter = descriptor.getReadMethod();
                    setter = descriptor.getWriteMethod();
                    break;
                }
            }

            if(getter == null)
                throw new PlException("No getter method for attribute " + getName() + " in class " + mapObjectClass.getName());
        
            if(setter == null)
                throw new PlException("No setter method for attribute " + getName() + " in class " + mapObjectClass.getName());

            // Init type of the attribute
            if(getColumnMap() != null)
            {
                getColumnMap().setPlType(pl.sql.PlTypes.getPlType(descriptor.getPropertyType()));
            }
        }
        catch(IntrospectionException e)
        {
            throw PlException.toPlException(e);
        }
    }

    /**
     * Sets the column map for this attribute.
     *
     * @param columnMap new column map
     */
    public void setColumnMap(ColumnMap columnMap)
    {
        this.columnMap = columnMap;
    }
    
    public void setXmlMap(XmlMap xmlMap)
    {
        this.xmlMap = xmlMap;
    }

    /**
     * Sets the referenced AttributeMap for this attribute.
     *
     * @param newReference new reference attribute for this attribute
     */
    public void setReference(AttributeMap reference)
    {
        this.reference = reference;
    }
    
    public Object getValue(Object target) throws PlException
    {
        try
        {
            return getter.invoke(target, emptyParameters);
        }
        catch(IllegalAccessException e)
        {
            throw new PlException(e);
        }
        catch(InvocationTargetException e)
        {
            throw new PlException(e);
        }
    }
    
    public void setValue(Object target, Object value) throws PlException
    {
        try
        {
            Object[] values = new Object[]{value};
            setter.invoke(target, values);
        }
        catch(IllegalAccessException e)
        {
            throw new PlException(e);
        }
        catch(InvocationTargetException e)
        {
            throw new PlException(e);
        }
    }
}
