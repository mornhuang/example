package pl.criteria;

/**
 * Insert the type's description here.
 * @author: Artyom Rudoy
 */
public class InCriteria extends SelectionCriteria
{
    public InCriteria(pl.map.ClassMap classMap, pl.map.AttributeMap attributeMap)
    {
        super(classMap, attributeMap);
    }

    /**
     * asSqlClause method comment.
     */
    public void fillSqlStatement(pl.sql.SqlStatement statement, java.util.Iterator parameters) throws pl.PlException
    {
        Object parameter = parameters.next();
        if(!(parameter instanceof java.util.Vector))
        {
            throw new pl.PlException("Bad parameter type for IN criteria");
        }
        
        java.util.Vector inParameters = (java.util.Vector)parameter;
        pl.map.ColumnMap cm = getAttributeMap().getColumnMap();
        if(inParameters.size() > 0)
        {
            statement.addSqlClause(cm.getFullyQualifiedName() + " " +
            getClassMap().getRelationalDatabase().getClauseStringIn() + " (");
            int plType = cm.getPlType();
            pl.converter.Converter converter = cm.getConverter();
            for(int i = 0; i < inParameters.size(); i++)
            {
                statement.addSqlClause((i > 0 ? ", " : "") + "?");
                statement.addParameter(converter.convertFrom(inParameters.get(i)), plType);
            }
            statement.addSqlClause(")");
        }
    }
}
