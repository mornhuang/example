package pl.criteria;

import java.util.*;

import pl.PersistenceManager;

/**
 * This is abstract class for all criteria.
 * If a name of attribute in addSelectXxx methods has aaa.bbb form
 * a criteria part will be generated for the attribute bbb of the referenced
 * object with the target aaa
 *
 * @author: Artyom Rudoy
 */
public abstract class PersistentCriteria
{
    private pl.map.ClassMap classMap = null;
    private HashSet tables = new HashSet();
    private HashSet associations = new HashSet();
    private CriteriaCondition whereCondition = null;
    protected PersistenceManager manager = null;

    /**
     * Creates copy of persistent criteria.
     */
    public PersistentCriteria(PersistentCriteria c)
    {
        super();

        this.associations = c.associations;
        this.classMap = c.classMap;
        this.tables = c.tables;
        this.whereCondition = c.whereCondition;
        this.manager = c.manager;
    }

    /**
     * Creates PersistentCriteria.
     *
     * @param classMap class map for this criteria
     */
    public PersistentCriteria(pl.map.ClassMap classMap, PersistenceManager manager)
    {
        this.classMap = classMap;
        this.manager = manager;

        // Fill tables HashSet with values
        pl.map.ClassMap cm = classMap;
        do
        {
            tables.addAll(cm.getTables());
            cm = cm.getSuperClass();
        }
        while(cm != null);

        // Create condition for the WHERE part of this criteria
        whereCondition = getNewCondition();
    }

    /**
     * Insert the method's description here.
     *
     * @param statement pl.sql.SqlStatement
     */
    protected void fillStatementWithWhere(pl.sql.SqlStatement statement, Iterator parameters) throws pl.PlException
    {
        // Build 'WHERE' part of SqlStatement for this criteria
        String inheritanceAssociations = getClassMap().getInheritanceAssociations();

        if(whereCondition.getSize() > 0 || inheritanceAssociations.length() > 0)
        {
            // Add 'WHERE key=?' to the statement
            statement.addSqlClause(" " + classMap.getRelationalDatabase().getClauseStringWhere() + " ");

            // Add criteria

            // Add part for inheritance support and associations
            if(associations.size() > 0 || inheritanceAssociations.length() > 0)
            {
                // Add inheritance support part
                if(inheritanceAssociations.length() > 0)
                {
                    statement.addSqlClause(inheritanceAssociations);
                }

                // Add associations part
                if(associations.size() > 0)
                {
                    if(inheritanceAssociations.length() > 0)
                    {
                        statement.addSqlClause(" " + getClassMap().getRelationalDatabase().getClauseStringAnd() + " ");
                    }
                    Object[] associationsArray = associations.toArray();
                    for(int i = 0; i < associationsArray.length; i++)
                    {
                        pl.map.UniDirectionalAssociationMap map = (pl.map.UniDirectionalAssociationMap)associationsArray[i];
                        for(int j = 0; j < map.getSize(); j++)
                        {
                            pl.map.UDAMapEntry entry = map.getEntry(j);
                            statement.addSqlClause((i > 0 && j > 0 ? (" " + getClassMap().getRelationalDatabase().getClauseStringAnd()) : "") +
                            entry.getFrom().getColumnMap().getFullyQualifiedName() + "=" +
                            entry.getTo().getColumnMap().getFullyQualifiedName());
                        }
                    }
                }

                statement.addSqlClause(whereCondition.getSize() > 0 ? (" " + getClassMap().getRelationalDatabase().getClauseStringAnd() + " ") : "");
            }
            /*else if(whereCondition.getSize() > 0)
                statement.addSqlClause(" ");*/

            whereCondition.fillSqlStatement(statement, parameters);
        }
    }

    /**
     * Returns AttributeMap by the given name.
     *
     * @return pl.map.AttributeMap
     * @param attributeName java.lang.String
     */
    private pl.map.AttributeMap getAttributeMap(String attributeName) throws pl.PlException
    {
        pl.map.AttributeMap map = null;

        // Split name into tokens using "." delimiter and put tokens
        // into nameSequence Vector
        StringTokenizer st = new StringTokenizer(attributeName, ".", false);
        Vector nameSequence = new Vector();
        while(st.hasMoreTokens())
        {
            nameSequence.add(st.nextToken());
        }

        // Find attribute map for the given name
        pl.map.ClassMap cm = classMap;
        for(int i = 0; i < nameSequence.size() - 1; i++)
        {
            String name = (String)nameSequence.get(i);
            pl.map.UniDirectionalAssociationMap am = cm.getAssociationMap(name);

            // If association map is null something wrong with names
            if(am == null)
                break;

            // Put association map to the association collection
            // This will be used to generate condition for WHERE clause
            associations.add(am);

            cm = am.getForClass();
            if(cm != null)
            {
                tables.addAll(cm.getTables());
            }
            else
            {
                break;
            }
        }

        if(cm != null)
        {
            map = cm.getAttributeMap((String)nameSequence.get(nameSequence.size() - 1), true);
        }

        if(map == null)
            throw new pl.PlException("Bad attribute name '" + attributeName + "' in criteria");
        else
            return map;
    }

    /**
     * Returns class map for this criteria.
     *
     * @return pl.map.ClassMap
     */
    public pl.map.ClassMap getClassMap()
    {
        return classMap;
    }

    /**
     * Returns new empty condition for this criteria.
     *
     * @return pl.criteria.CriteriaCondition
     */
    public CriteriaCondition getNewCondition()
    {
        return new CriteriaCondition(classMap);
    }

    /**
     * Returns tables involved in this criteria.
     *
     * @return java.util.HashSet
     */
    protected java.util.HashSet getTables()
    {
        return tables;
    }

    /**
     * Returns condition for the WHERE part of this criteria.
     *
     * @return pl.criteria.CriteriaCondition
     */
    public CriteriaCondition getWhereCondition()
    {
        return whereCondition;
    }

    public EqualToCriteria getEqualToCriteria(String attributeName) throws pl.PlException
    {
        return new EqualToCriteria(classMap, getAttributeMap(attributeName));
    }

    public GreaterThanCriteria getGreaterThanCriteria(String attributeName) throws pl.PlException
    {
        return new GreaterThanCriteria(classMap, getAttributeMap(attributeName));
    }

    public GreaterThanOrEqualToCriteria getGreaterThanOrEqualToCriteria(String attributeName) throws pl.PlException
    {
        return new GreaterThanOrEqualToCriteria(classMap, getAttributeMap(attributeName));
    }

    public InCriteria getInCriteria(String attributeName) throws pl.PlException
    {
        return new InCriteria(classMap, getAttributeMap(attributeName));
    }

    public LessThanCriteria getLessThanCriteria(String attributeName) throws pl.PlException
    {
        return new LessThanCriteria(classMap, getAttributeMap(attributeName));
    }

    public LessThanOrEqualToCriteria getLessThanOrEqualToCriteria(String attributeName) throws pl.PlException
    {
        return new LessThanOrEqualToCriteria(classMap, getAttributeMap(attributeName));
    }

    public LikeCriteria getLikeCriteria(String attributeName) throws pl.PlException
    {
        return new LikeCriteria(classMap, getAttributeMap(attributeName));
    }

    public NotEqualToCriteria getNotEqualToCriteria(String attributeName) throws pl.PlException
    {
        return new NotEqualToCriteria(classMap, getAttributeMap(attributeName));
    }
    
    public RegExpCaseCriteria getRegExpCase(String name) throws pl.PlException
    {
        return new RegExpCaseCriteria(classMap, getAttributeMap(name));
    }

    public RegExpNoCaseCriteria getRegExpNoCase(String name) throws pl.PlException
    {
        return new RegExpNoCaseCriteria(classMap, getAttributeMap(name));
    }

    public NotRegExpCaseCriteria getNotRegExpCase(String name) throws pl.PlException
    {
        return new NotRegExpCaseCriteria(classMap, getAttributeMap(name));
    }

    public NotRegExpNoCaseCriteria getNotRegExpNoCase(String name) throws pl.PlException
    {
        return new NotRegExpNoCaseCriteria(classMap, getAttributeMap(name));
    }
}
