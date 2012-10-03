package pl.criteria;

/**
 * @author  Artem Rudoy
 */
public interface CriteriaPart
{
    public abstract void fillSqlStatement(pl.sql.SqlStatement statement, java.util.Iterator parameters) throws pl.PlException;
}
