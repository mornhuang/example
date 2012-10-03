package pl.criteria;

/**
 * Insert the type's description here.
 * @author: Artyom Rudoy
 */
public class LessThanOrEqualToCriteria extends SelectionCriteria
{
    public LessThanOrEqualToCriteria(pl.map.ClassMap classMap, pl.map.AttributeMap attributeMap)
    {
        super(classMap, attributeMap);
    }

    /**
     * asSqlClause method comment.
     */
    public void fillSqlStatement(pl.sql.SqlStatement statement, java.util.Iterator parameters) throws pl.PlException
    {
        pl.map.ColumnMap cm = getAttributeMap().getColumnMap();
        statement.addSqlClause(cm.getFullyQualifiedName() + "<=?");
        statement.addParameter(cm.getConverter().convertFrom(parameters.next()), cm.getPlType());
    }
}
