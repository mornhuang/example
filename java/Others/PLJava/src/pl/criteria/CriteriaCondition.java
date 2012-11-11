package pl.criteria;

import java.util.*;

/**
 * This class represents a condition for criteria's
 * WHERE and HAVING parts
 *
 * @author:
 */
public class CriteriaCondition implements CriteriaPart
{
    private Vector parts = new Vector();
    private pl.map.ClassMap classMap = null;

    /**
     * Creates CreteriaCondition.
     */
    CriteriaCondition(pl.map.ClassMap classMap)
    {
        super();
        
        this.classMap = classMap;
    }

    /**
     * Adds OR criteria condition to this condition.
     *
     * @param orCriteria pl.criteria.CriteriaCondition
     */
    public void addOrCriteria(CriteriaPart orCriteria)
    {
        // Add condition to the parts of this criteria
        parts.add(new Part(orCriteria, Part.OR_PART));
    }

    /**
     * Adds AND criteria condition to this condition.
     *
     * @param orCriteria pl.criteria.CriteriaCondition
     */
    public void addAndCriteria(CriteriaPart andCriteria)
    {
        // Add condition to the parts of this criteria
        parts.add(new Part(andCriteria, Part.AND_PART));
    }

    /**
     * Fills SQL statement with this condition.
     *
     * @param statement statement to fill
     * @param parameters parameters
     */
    public void fillSqlStatement(pl.sql.SqlStatement statement, java.util.Iterator parameters) throws pl.PlException
    {
        if(parts.size() > 0)
        {
            statement.addSqlClause("(");
            for(int i = 0; i < getSize(); i++)
            {
                Part part = (Part)parts.get(i);
                if(i != 0)
                {
                    switch(part.kind)
                    {
                        case Part.AND_PART:
                            statement.addSqlClause(" " + classMap.getRelationalDatabase().getClauseStringAnd() + " ");
                            break;

                        case Part.OR_PART:
                            statement.addSqlClause(" " + classMap.getRelationalDatabase().getClauseStringOr() + " ");
                            break;
                    }
                }
                part.part.fillSqlStatement(statement, parameters);
            }
            statement.addSqlClause(")");
        }
    }

    /**
     * Returns size of this condition.
     *
     * @return int
     */
    public int getSize()
    {
        return parts.size();
    }
}

class Part
{
    public final static int OR_PART = 0;
    public final static int AND_PART = 1;

    public CriteriaPart part = null;
    public int kind = AND_PART;

    public Part(CriteriaPart part, int kind)
    {
        this.part = part;
        this.kind = kind;
    }
}
