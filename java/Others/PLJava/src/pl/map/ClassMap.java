package pl.map;

import java.util.*;
import java.lang.reflect.*;

import pl.*;
import pl.sql.*;

/*
 TODO we assume one one ClassMap instance can map only one table
 We should create tree - like structure with the attribute maps

 tableMap1
        |---- attributeMap1
        |---- attributeMap2
        +---- attributeMap3
 talelMap2
        |---- attributeMap4
        +---- attributeMap5
 */

/**
 * This class is responsible for mapping objects to tables of relational database.
 * @author: Artyom Rudoy
 */
public class ClassMap
{
    private String name = null;
    private SqlStatement selectStatement = null;
    private SqlStatement selectProxyStatement = null;
    private SqlStatement selectTimestampStatement = null;
    private SqlStatement insertStatement = null;
    private SqlStatement deleteStatement = null;
    private SqlStatement updateStatement = null;
    private ArrayList updateAttributeMaps = new ArrayList();
    private ArrayList attributeMaps = new ArrayList();
    private TreeMap hashedAttributeMaps = new TreeMap();
    private RelationalDatabase relationalDatabase = null;
    private ArrayList keyAttributeMaps = new ArrayList();
    private ArrayList proxyAttributeMaps = new ArrayList();
    private Class mapObjectClass = null;
    private TreeMap associationMaps = new TreeMap();
    private ClassMap superClass = null;
    private java.util.Vector referenceAttributeMaps = new Vector();
    private HashSet tables = new HashSet();
    private ArrayList inverseAssociationMaps = new ArrayList();
    private ArrayList straightAssociationMaps = new ArrayList();
    private AttributeMap timestampAttributeMap = null;
    private ClassLoader classLoader = null;
    private boolean isInited = false;
    private String xmlName = null;
    private ArrayList xmlAttributeMaps = new ArrayList();

    /**
     * Creates ClassMap for given class name with the specified class loader.
     *
     * @param name name of the class
     * @param classLoader
     */
    public ClassMap(String name, RelationalDatabase relationalDatabase, ClassLoader classLoader, PersistenceManagerFactory configurable) throws PlException
    {
        super();

        this.name = name;
        this.relationalDatabase = relationalDatabase;
        this.classLoader = classLoader;
        this.xmlName = name;

        // Load class for this map object
        try
        {
            mapObjectClass = Class.forName(getName(), true, classLoader);

            // Try to find superclass map for this class map
            Class sc = mapObjectClass.getSuperclass();
            if(sc != PersistentObject.class && PersistentObject.class.isAssignableFrom(sc))
            {
                // Try to find class map for the superclass
                ClassMap superClassMap = configurable.getClassMap(sc.getName());
                if(superClassMap != null)
                    setSuperClass(superClassMap);
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new PlException("Class " + getName() + " not found");
        }
    }

    /**
     * Adds attribute map to this class map.
     * @param attributeMap pl.map.AttributeMap
     */
    public void addAttributeMap(AttributeMap attributeMap) throws PlException
    {
      // <ÊôÐÔÃû£¬ÊôÐÔÓ³ÉäAttributeMap>
        hashedAttributeMaps.put(attributeMap.getName(), attributeMap);
        if(attributeMap.getColumnMap() != null)
        {
            attributeMaps.add(attributeMap);
            if(attributeMap.getColumnMap().getKeyType() != ColumnMap.KEY_NONE)
                keyAttributeMaps.add(attributeMap);
            else
                updateAttributeMaps.add(attributeMap);
            if(attributeMap.getReference() != null)
                referenceAttributeMaps.add(attributeMap);
            // Add attributeMap table to the table map collection
            tables.add(attributeMap.getColumnMap().getTableMap());

            if(attributeMap.isProxy() || attributeMap.getColumnMap().getKeyType() != ColumnMap.KEY_NONE)
            {
                proxyAttributeMaps.add(attributeMap);
            }

            if(attributeMap.getXmlMap() != null && attributeMap.getReference() == null)
                xmlAttributeMaps.add(attributeMap);
        }

        // Init accessors
        attributeMap.initAccessors(mapObjectClass);
    }

    public Class getMapObjectClass()
    {
        return mapObjectClass;
    }

    /**
     * Returns association map by the given name.
     *
     * @return UniDirectionalAssociationMap
     */
    public UniDirectionalAssociationMap getAssociationMap(String name)
    {
        return (UniDirectionalAssociationMap)associationMaps.get(name);
    }

    /**
     * Returns association maps for this class map.
     *
     * @return java.util.TreeMap
     */
    public TreeMap getAssociationMaps()
    {
        return associationMaps;
    }

    public int getXmlSize()
    {
        return xmlAttributeMaps.size();
    }

    public AttributeMap getXmlAttributeMap(int index)
    {
        return (AttributeMap)xmlAttributeMaps.get(index);
    }

    /**
     * Returns attribute map for the given index.
     * @return pl.map.AttributeMap
     * @param index int index of the attribute
     */
    public AttributeMap getAttributeMap(int index)
    {
        return (AttributeMap)attributeMaps.get(index);
    }

    /**
     * Returns AttributeMap for the given attribute name.
     * @return pl.map.AttributeMap
     * @param name java.lang.String name of the attribute
     */
    public AttributeMap getAttributeMap(String name)
    {
        return getAttributeMap(name, false);
    }

    /**
     * Returns AttributeMap for the given attribute name.
     * If areSuperClassesIncluded is <code>true</code> tries to find AttributeMap
     * in superclasses
     * @return pl.map.AttributeMap
     * @param name java.lang.String name of the attribute
     */
    public AttributeMap getAttributeMap(String name, boolean areSuperClassesIncluded)
    {
        AttributeMap am = null;

        ClassMap cm = this;
        do
        {
            am = (AttributeMap)cm.hashedAttributeMaps.get(name);
            cm = cm.getSuperClass();
        }
        while(areSuperClassesIncluded && am == null && cm != null);

        return am;
    }

    /**
     * Returns class loader of the current class map.
     *
     * @return java.lang.ClassLoader
     */
    public ClassLoader getClassLoader()
    {
        return classLoader;
    }

    /**
     * Returns delete sql statement for the given object.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getDeleteSqlFor(PersistentObject object) throws PlException
    {
        // Clone statement
        SqlStatement statement = (SqlStatement)deleteStatement.clone();

        // Fill statement with values
        for(int i = 0; i < getKeySize(); i++)
        {
            AttributeMap aMap = getKeyAttributeMap(i);
            Object value = aMap.getColumnMap().getConverter().convertFrom(aMap.getValue(object));
            statement.addParameter(value, aMap.getColumnMap().getPlType());
        }

        return statement;
    }

    /**
     * Returns the second and the third part of the select sql statement for this class map.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getFromAndWhereSql() throws PlException
    {
        // Create new statement
        SqlStatement statement = new SqlStatement();

        // Add 'FROM' clause to the select statement
        statement.addSqlClause(" " + getRelationalDatabase().getClauseStringFrom() + " ");
        boolean isFirst = true;
        ClassMap classMap = this;
        do
        {
            AttributeMap map = classMap.getAttributeMap(0);
            if (map != null)
            {
                statement.addSqlClause((isFirst ? "" : ", ") + map.getColumnMap().getTableMap().getName());
            }
            classMap = classMap.getSuperClass();
            isFirst = false;
        }
        while(classMap != null);

        // Add part for keys and inheritance support
        String inheritanceAssociations = getInheritanceAssociations();

        // Add 'WHERE key=?' to the select statement
        if(getKeySize() > 0 || inheritanceAssociations.length() > 0)
        {
            statement.addSqlClause(" ");

            statement.addSqlClause(getRelationalDatabase().getClauseStringWhere() + " ");
            for(int i = 0; i < getKeySize(); i++)
            {
                statement.addSqlClause((i > 0 ? " " + getRelationalDatabase().getClauseStringAnd() + " " : "") +
                getKeyAttributeMap(i).getColumnMap().getFullyQualifiedName() + "=?");
            }

            // Add part for inheritance support
            if(inheritanceAssociations.length() > 0)
            {
                statement.addSqlClause((getKeySize() > 0 ? (" " + getRelationalDatabase().getClauseStringAnd() + " ") : "") +
                inheritanceAssociations);
            }
        }

        return statement;
    }

    /**
     * Returns 'table.column=superclass_table.column' part of the select statement
     * for this class map.
     *
     * @return java.lang.String
     */
    public String getInheritanceAssociations()
    {
        StringBuffer result = new StringBuffer();
        ClassMap classMap = this;
        do
        {
            for(int i = 0; i < classMap.getReferenceSize(); i++)
            {
                result.append((i > 0 ? (" " + getRelationalDatabase().getClauseStringAnd() + " ") : "") +
                classMap.getReferenceAttributeMap(i).getColumnMap().getFullyQualifiedName() + "=" +
                classMap.getReferenceAttributeMap(i).getReference().getColumnMap().getFullyQualifiedName());
            }
            classMap = classMap.getSuperClass();
        }
        while(classMap != null);

        return result.toString();
    }

    /**
     * Returns insert sql statement for the given object.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getInsertSqlFor(PersistentObject object) throws PlException
    {
        // Clone statement
        SqlStatement statement = (SqlStatement)insertStatement.clone();

        // Fill statement with values
        for(int i = 0; i < getSize(); i++)
        {
            AttributeMap aMap = getAttributeMap(i);
            Object value = aMap.getColumnMap().getConverter().convertFrom(aMap.getValue(object));
            statement.addParameter(value, aMap.getColumnMap().getPlType());
        }

        return statement;
    }

    /**
     * Returns the inverse association map with the specified index.
     *
     * @return pl.map.UniDirectionalAssociationMap
     * @param index index
     */
    public UniDirectionalAssociationMap getInverseAssociationMap(int index)
    {
        return (UniDirectionalAssociationMap)inverseAssociationMaps.get(index);
    }

    /**
     * Returns the number of inverse association maps.
     *
     * @return int
     */
    public int getInverseAssociationMapSize()
    {
        return inverseAssociationMaps.size();
    }

    /**
     * Returns key AttributeMap for the given index.
     * @return pl.map.AttributeMap
     * @param index int index of the attribute map
     */
    public AttributeMap getKeyAttributeMap(int index)
    {
        return (AttributeMap)keyAttributeMaps.get(index);
    }

    /**
     * Returns number of key attribute maps.
     * @return int
     */
    public int getKeySize()
    {
        return keyAttributeMaps.size();
    }

    public AttributeMap getProxyAttributeMap(int index)
    {
        return (AttributeMap)proxyAttributeMaps.get(index);
    }

    public int getProxySize()
    {
        return proxyAttributeMaps.size();
    }

    /**
     * Return update attribute map with the specified index.
     */
    public AttributeMap getUpdateAttributeMap(int index)
    {
        return (AttributeMap)updateAttributeMaps.get(index);
    }

    /**
     * Return number of attributes for a select statement.
     */
    public int getUpdateSize()
    {
        return updateAttributeMaps.size();
    }

    /**
     * Returns name of the class.
     * @return java.lang.String
     */
    public java.lang.String getName()
    {
        return name;
    }

    /**
     * Return reference AttributeMap for the given index.
     * @return pl.map.AttributeMap
     * @param index int index of the reference attribute map
     */
    public AttributeMap getReferenceAttributeMap(int index)
    {
        return (AttributeMap)referenceAttributeMaps.elementAt(index);
    }

    /**
     * Returns number of the reference attribute map.
     * @return int
     */
    public int getReferenceSize()
    {
        return referenceAttributeMaps.size();
    }

    /**
     * Returns RelationalDatabase for this ClassMap.
     * @return pl.pm.RelationalDatabase
     */
    public RelationalDatabase getRelationalDatabase()
    {
        return relationalDatabase;
    }

    /**
     * Returns the first select sql statement for this class map.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getSelectSql() throws PlException
    {
        // Create new statement
        SqlStatement statement = new SqlStatement();

        // Add 'SELECT' clause to the select statement
        statement.addSqlClause(getRelationalDatabase().getClauseStringSelect() + " ");

        // Add clauses for all attributes. Do not add ", " before the first attribute
        boolean isFirst = true;
        ClassMap classMap = this;
        do
        {
            for (int i = 0; i < classMap.getSize(); i++)
            {
                statement.addSqlClause((isFirst ? "" : ", ") +
                classMap.getAttributeMap(i).getColumnMap().getFullyQualifiedName());
                isFirst = false;
            }
            classMap = classMap.getSuperClass();
        }
        while(classMap != null);

        return statement;
    }

    /**
     * Returns the first select sql statement for this class map.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getSelectProxySql() throws PlException
    {
        // Create new statement
        SqlStatement statement = new SqlStatement();

        // Add 'SELECT' clause to the select statement
        statement.addSqlClause(getRelationalDatabase().getClauseStringSelect() + " ");

        // Add clauses for all attributes. Do not add ", " before the first attribute
        boolean isFirst = true;
        ClassMap classMap = this;
        do
        {
            for (int i = 0; i < classMap.getProxySize(); i++)
            {
                statement.addSqlClause((isFirst ? "" : ", ") +
                classMap.getProxyAttributeMap(i).getColumnMap().getFullyQualifiedName());
                isFirst = false;
            }
            classMap = classMap.getSuperClass();
        }
        while(classMap != null);

        return statement;
    }

    /**
     * Returns select sql statement for the timestamp attribute of the given object.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getSelectSqlFor(PersistentObject object) throws PlException
    {
        // Clone statement
        SqlStatement statement = (SqlStatement)selectStatement.clone();

        // Fill statement with values
        for(int i = 0; i < getKeySize(); i++)
        {
            AttributeMap aMap = getKeyAttributeMap(i);
            Object value = aMap.getColumnMap().getConverter().convertFrom(aMap.getValue(object));
            statement.addParameter(value, aMap.getColumnMap().getPlType());
        }

        return statement;
    }

    /**
     * Returns select sql statement for the timestamp attribute of the given object.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getSelectProxySqlFor(PersistentObject object) throws PlException
    {
        // Clone statement
        SqlStatement statement = (SqlStatement)selectProxyStatement.clone();

        // Fill statement with values
        for(int i = 0; i < getKeySize(); i++)
        {
            AttributeMap aMap = getKeyAttributeMap(i);
            Object value = aMap.getColumnMap().getConverter().convertFrom(aMap.getValue(object));
            statement.addParameter(value, aMap.getColumnMap().getPlType());
        }

        return statement;
    }

    /**
     * Returns delete sql statement for the given object.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getSelectTimestampSqlFor(PersistentObject object) throws PlException
    {
        // Check if optimistic lock is supported by the object
        // Try to find timestamp attribute map
        if(selectTimestampStatement == null)
        {
            throw new OptimisticLockException("Optimistic lock is not supported by the object");
        }

        // Clone statement
        SqlStatement statement = (SqlStatement)selectTimestampStatement.clone();

        // Fill statement with values
        for(int i = 0; i < getKeySize(); i++)
        {
            AttributeMap aMap = getKeyAttributeMap(i);
            Object value = aMap.getColumnMap().getConverter().convertFrom(aMap.getValue(object));
            statement.addParameter(value, aMap.getColumnMap().getPlType());
        }

        return statement;
    }

    /**
     * Returns number of attribute maps.
     * @return int
     */
    public int getSize()
    {
        return attributeMaps.size();
    }

    /**
     * Returns the straight association map with the specified index.
     *
     * @return pl.map.UniDirectionalAssociationMap
     * @param index index
     */
    public UniDirectionalAssociationMap getStraightAssociationMap(int index)
    {
        return (UniDirectionalAssociationMap)straightAssociationMaps.get(index);
    }

    /**
     * Returns the number of straight association maps.
     *
     * @return int
     */
    public int getStraightAssociationMapSize()
    {
        return straightAssociationMaps.size();
    }

    /**
     * Returns ClassMap for superclass.
     * @return pl.map.ClassMap
     */
    public ClassMap getSuperClass()
    {
        return superClass;
    }

    /**
     * Insert the method's description here.
     * Creation date: (27.07.00 12:26:20)
     * @return java.util.HashSet
     */
    public java.util.HashSet getTables()
    {
        return tables;
    }

    /**
     * Returns attribute map for the timestamp attribute of this class map.
     *
     * @return attribute map for the timestamp attribute of this class map
     */
    public AttributeMap getTimestampAttributeMap()
    {
        return timestampAttributeMap;
    }

    /**
     * Returns update sql statement for the given object.
     * @return pl.sql.SqlStatement
     * @param obj pl.PersistentObject
     */
    public SqlStatement getUpdateSqlFor(PersistentObject object) throws PlException
    {
        // Clone statement
        SqlStatement statement = (SqlStatement)updateStatement.clone();

        // Fill statement with values
        for(int i = 0; i < getUpdateSize(); i++)
        {
            AttributeMap aMap = getUpdateAttributeMap(i);
            Object value = aMap.getColumnMap().getConverter().convertFrom(aMap.getValue(object));
            statement.addParameter(value, aMap.getColumnMap().getPlType());
        }
        for(int i = 0; i < getKeySize(); i++)
        {
            AttributeMap aMap = getAttributeMap(i);
            Object value = aMap.getColumnMap().getConverter().convertFrom(aMap.getValue(object));
            statement.addParameter(value, aMap.getColumnMap().getPlType());
        }

        return statement;
    }

    public String getXmlName()
    {
        return xmlName;
    }

    /**
     * Init this class map.
     */
    public synchronized void init() throws pl.PlException
    {
        // We don't have to init class map twice
        if(isInited)
            return;

        // Init all statements

        //
        // Init SELECT statement
        //
        selectStatement = getSelectSql();

        // Add 'FROM' and 'WHERE' clauses to the select statement
        selectStatement.addSqlClause(" ");
        selectStatement.addSqlStatement(getFromAndWhereSql());


        //
        // Init SELECT statement for proxy
        //
        selectProxyStatement = getSelectProxySql();

        // Add 'FROM' and 'WHERE' clauses to the select statement
        selectProxyStatement.addSqlClause(" ");
        selectProxyStatement.addSqlStatement(getFromAndWhereSql());


        //
        // Init SELECT statement for timestamp
        //
        ClassMap cm = this;
        AttributeMap am = null;
        while(cm != null)
        {
            am = cm.getTimestampAttributeMap();
            if(am != null)
                break;

            cm = cm.getSuperClass();
        }

        if(am != null)
        {
            // Create new statement
            selectTimestampStatement = new SqlStatement();

            // Add 'SELECT' clause to the select statement
            selectTimestampStatement.addSqlClause(getRelationalDatabase().getClauseStringSelect() + " ");
            selectTimestampStatement.addSqlClause(am.getColumnMap().getFullyQualifiedName());

            // Add 'FROM' and 'WHERE' clauses to the select statement
            selectTimestampStatement.addSqlStatement(getFromAndWhereSql());

            // Add FOR UPDATE clause if object needs to be locked
            selectTimestampStatement.addSqlClause(" " + getRelationalDatabase().getClauseStringForUpdate());
        }


        //
        // Init UPDATE statement
        //
        updateStatement = new SqlStatement();

        // Add 'UPDATE' clause to the select statement
        updateStatement.addSqlClause(getRelationalDatabase().getClauseStringUpdate() + " ");
        AttributeMap map = getAttributeMap(0);
        if(map != null)
        {
            updateStatement.addSqlClause(map.getColumnMap().getTableMap().getName() + " ");
        }

        // Add 'SET' clause to the select statement
        updateStatement.addSqlClause(getRelationalDatabase().getClauseStringSet() + " ");

        // Add clauses for all attributes. Do not add ", " before the first attribute
        for (int i = 0; i < getUpdateSize(); i++)
        {
            updateStatement.addSqlClause((i > 0 ? ", " : "") +
            getUpdateAttributeMap(i).getColumnMap().getName() + "=?");
        }

        // Add 'WHERE key=?' to the select statement
        updateStatement.addSqlClause(" " + getRelationalDatabase().getClauseStringWhere() + " ");
        for(int i = 0; i < getKeySize(); i++)
        {
            updateStatement.addSqlClause((i > 0 ? " AND " : "") +
            getKeyAttributeMap(i).getColumnMap().getName() + "=?");
        }


        //
        // Init INSERT statement
        //
        insertStatement = new SqlStatement();

        // Add 'INSERT INTO' clause to the select statement
        insertStatement.addSqlClause(getRelationalDatabase().getClauseStringInsert() + " ");
        if(map != null)
        {
            insertStatement.addSqlClause(map.getColumnMap().getTableMap().getName() + " ");
        }

        // Add clauses for all attributes. Do not add ", " before the first attribute
        insertStatement.addSqlClause("(");
        for (int i = 0; i < getSize(); i++)
        {
            insertStatement.addSqlClause((i > 0 ? ", " : "") + getAttributeMap(i).getColumnMap().getName());
        }
        insertStatement.addSqlClause(") ");

        // Add 'VALUES' clause to the select statement
        insertStatement.addSqlClause(getRelationalDatabase().getClauseStringValues() + " ");
        insertStatement.addSqlClause("(");
        for (int i = 0; i < attributeMaps.size(); i++)
        {
            insertStatement.addSqlClause((i > 0 ? ", " : "") + "?");
        }
        insertStatement.addSqlClause(") ");


        //
        // Init DELETE statement
        //
        deleteStatement = new SqlStatement();

        // Add 'DELETE FROM' clause to the select statement
        deleteStatement.addSqlClause(getRelationalDatabase().getClauseStringDelete() + " " + getRelationalDatabase().getClauseStringFrom() + " ");
        if(map != null)
        {
            deleteStatement.addSqlClause(map.getColumnMap().getTableMap().getName() + " ");
        }

        // Add 'WHERE key=?' to the select statement
        deleteStatement.addSqlClause(getRelationalDatabase().getClauseStringWhere() + " ");
        for(int i = 0; i < getKeySize(); i++)
        {
            deleteStatement.addSqlClause((i > 0 ? " AND " : "") +
            getKeyAttributeMap(i).getColumnMap().getName() + "=?");
        }

        isInited = true;
    }

    /**
     * Adds new association map to this class map.
     *
     * @param map pl.map.UniDirectionalAssociationMap
     */
    public void putAssociationMap(UniDirectionalAssociationMap map)
    {
        associationMaps.put(map.getTargetName(), map);
        if(map.isInverse())
            inverseAssociationMaps.add(map);
        else
            straightAssociationMaps.add(map);
    }

    /**
     * Retrieves data from ResultSet and puts them to the object.
     * @param object pl.PersistentObject
     * @param rs java.sql.ResultSet
     */
    public void retrieveObject(PersistentObject object, java.sql.ResultSet rs) throws PlException
    {
        ClassMap classMap = this;
        Object value = null;
        int index = 1;

        do
        {
            for (int i = 0; i < classMap.getSize(); i++)
            {
                value = PlTypes.getValue(rs, index, classMap.getAttributeMap(i).getColumnMap().getPlType());
                classMap.getAttributeMap(i).setValue(object,  classMap.getAttributeMap(i).getColumnMap().getConverter().convertTo(value));
                index++;
            }
            classMap = classMap.getSuperClass();
        }
        while (classMap != null);
        object.setPersistent(true);
        object.setProxy(false);
    }

    /**
     * Retrieves data from ResultSet and puts them to the object.
     * @param object pl.PersistentObject
     * @param rs java.sql.ResultSet
     */
    public void retrieveProxyObject(PersistentObject object, java.sql.ResultSet rs) throws PlException
    {
        ClassMap classMap = this;
        Object value = null;
        int index = 1;

        do
        {
            for (int i = 0; i < classMap.getProxySize(); i++)
            {
                value = PlTypes.getValue(rs, index, classMap.getProxyAttributeMap(i).getColumnMap().getPlType());
                classMap.getProxyAttributeMap(i).setValue(object,
                    classMap.getProxyAttributeMap(i).getColumnMap().getConverter().convertTo(value));
                index++;
            }
            classMap = classMap.getSuperClass();
        }
        while (classMap != null);
        object.setPersistent(true);
        object.setProxy(true);
    }

    /**
     * Sets ClassMap for super class of this class.
     *
     * @param newSuperClass pl.map.ClassMap
     */
    public void setSuperClass(ClassMap superClass)
    {
        this.superClass = superClass;
    }

    /**
     * Sets attribute map for the timestamp attribute of this class map.
     *
     * @param timestampAttributeMap
     */
    public void setTimestampAttributeMap(AttributeMap timestampAttributeMap)
    {
        this.timestampAttributeMap = timestampAttributeMap;
    }

    public void setXmlName(String xmlName)
    {
        this.xmlName = xmlName;
    }
}
