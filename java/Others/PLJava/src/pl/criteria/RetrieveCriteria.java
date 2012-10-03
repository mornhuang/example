package pl.criteria;

import pl.PersistenceManager;
import pl.map.*;

/**
 * Insert the type's description here.
 * @author: Artyom Rudoy
 */
public class RetrieveCriteria extends PersistentCriteria
{
    private java.util.Vector groupAttributes = new java.util.Vector();
    private java.util.Vector orderAttributes = new java.util.Vector();
    private CriteriaCondition havingCondition = null;

    /**
     * Creates a copy of persistent criteria.
     *
     * @param criteria source criteria
     */
    public RetrieveCriteria(PersistentCriteria criteria)
    {
        super(criteria);

        if(criteria instanceof RetrieveCriteria)
            havingCondition = ((RetrieveCriteria)criteria).havingCondition;
    }

    /**
     * Creates RetrieveCriteria.
     *
     * @param classMap class map for this criteria
     */
    public RetrieveCriteria(ClassMap classMap, PersistenceManager manager)
    {
        super(classMap, manager);

        havingCondition = getNewCondition();
    }

    /**
     * Adds the specified attribute to the GROUP BY part of the statement.
     *
     * @param attributeName name of the attribute
     */
    public void addGroupAttribute(String attributeName)
    {
        groupAttributes.addElement(getClassMap().getAttributeMap(attributeName, true));
    }

    /**
     * Adds the specified attribute to the ORDER BY part of the statement
     * with the default direction of sorting.
     *
     * @param attributeName name of the attribute
     */
    public void addOrderAttribute(String attributeName)
    {
        addOrderAttribute(attributeName, true);
    }

    /**
     * Adds the specified attribute to the ORDER BY part of the statement
     * with the specified direction of sorting.
     *
     * @param attributeName name of the attribute
     * @param ascend true to sort in the ascending order of false to sort in the descsending order
     */
    public void addOrderAttribute(String attributeName, boolean ascend)
    {
        orderAttributes.add(new OrderEntry(getClassMap().getAttributeMap(attributeName, true), ascend));
    }

    public void addOrderAttribute(OrderEntry entry)
    {
        orderAttributes.add(entry);
    }

    /**
     * Returns condition for the HAVING part of this criteria.
     *
     * @return pl.criteria.CriteriaCondition
     */
    public CriteriaCondition getHavingCondition()
    {
        return havingCondition;
    }

    /**
     * Returns sql statement for this criteria.
     *
     * @return pl.sql.SqlStatement
     */
    public pl.sql.SqlStatement getSqlStatement(java.util.Vector parameters, boolean forProxy) throws pl.PlException
    {
        pl.sql.SqlStatement statement = null;
        if(forProxy)
        {
            statement = getClassMap().getSelectProxySql();
        }
        else
        {
            statement = getClassMap().getSelectSql();
        }
        java.util.Iterator enumerationParameters = parameters.iterator();

        // Add 'FROM' clause to the select statement
        statement.addSqlClause(" " + getClassMap().getRelationalDatabase().getClauseStringFrom() + " ");
        Object[] tablesArray = getTables().toArray();
        for(int i = 0; i < tablesArray.length; i++)
        {
            TableMap tableMap = (TableMap)tablesArray[i];
            statement.addSqlClause((i > 0 ? ", " : "") + tableMap.getName());
        }

        // Fill statement with WHERE condition
        fillStatementWithWhere(statement, enumerationParameters);

        // Add GROUP BY clause
        if(groupAttributes.size() > 0)
        {
            statement.addSqlClause(" " + getClassMap().getRelationalDatabase().getClauseStringGroupBy());
            for(int i = 0; i < groupAttributes.size(); i++)
            {
                AttributeMap am = (AttributeMap)groupAttributes.get(i);
                statement.addSqlClause((i > 0 ? ", " : " ") + am.getColumnMap().getFullyQualifiedName());
            }
        }

        // Fill statement with HAVING condition
        if(havingCondition != null &&  havingCondition.getSize() > 0)
        {
            statement.addSqlClause(" " + getClassMap().getRelationalDatabase().getClauseStringHaving() + " ");
            havingCondition.fillSqlStatement(statement, enumerationParameters);
        }

        // Add ORDER BY clause
        if(orderAttributes.size() > 0)
        {
            statement.addSqlClause(" " + getClassMap().getRelationalDatabase().getClauseStringOrderBy());
            for(int i = 0; i < orderAttributes.size(); i++)
            {
                OrderEntry entry = (OrderEntry)orderAttributes.get(i);
                statement.addSqlClause((i > 0 ? ", " : " ") +
                entry.getAttributeMap().getColumnMap().getFullyQualifiedName() + " " +
                (entry.isAscend() ? getClassMap().getRelationalDatabase().getClauseStringAscend() :
                    getClassMap().getRelationalDatabase().getClauseStringDescend()));
            }
        }

        return statement;
    }

    /**
     * This method performs this criteria.
     *
     * @param parameters parameters for this criteria. For the
     * <code>addSelectIn</code> criteria the parameter must have <code>Vector</code> type
     */
    public pl.Cursor perform(java.util.Vector parameters) throws pl.PlException
    {
        return manager.processCriteria(this, parameters);
    }

    /**
     * This method performs this criteria to retrieve proxy objects.
     *
     * @param parameters parameters for this criteria. For the
     * <code>addSelectIn</code> criteria the parameter must have <code>Vector</code> type
     */
    public pl.Cursor performForProxies(java.util.Vector parameters) throws pl.PlException
    {
        return manager.processCriteriaForProxies(this, parameters);
    }
}
