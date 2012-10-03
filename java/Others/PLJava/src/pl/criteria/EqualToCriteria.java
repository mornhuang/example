package pl.criteria;

/**
 * Insert the type's description here.
 * @author: Artyom Rudoy
 */
public class EqualToCriteria extends SelectionCriteria
{
    public EqualToCriteria(pl.map.ClassMap classMap, pl.map.AttributeMap attributeMap)
    {
        super(classMap, attributeMap);
    }

    /**
     * asSqlClause method comment.
     */
    public void fillSqlStatement(pl.sql.SqlStatement statement, java.util.Iterator parameters) throws pl.PlException
    {
        pl.map.ColumnMap cm = getAttributeMap().getColumnMap();
        Object parameter = parameters.next();
        if(parameter != null)
        {
            statement.addSqlClause(cm.getFullyQualifiedName() + "=?");
        }
        else
        {
            statement.addSqlClause(cm.getFullyQualifiedName() + " " +
            getClassMap().getRelationalDatabase().getClauseStringIs() + " ?");
        }
        statement.addParameter(cm.getConverter().convertFrom(parameter), cm.getPlType());
    }
}
