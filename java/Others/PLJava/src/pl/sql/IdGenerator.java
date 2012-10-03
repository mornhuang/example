package pl.sql;

import java.util.*;
import pl.*;

/**
 * Interface for all ID generators.
 *
 * @author: Artem Rudoy
 */
public interface IdGenerator
{
/**
 * Return new ID for the class specified by the classMap parameter.
 *
 * @return new ID for the class specified by the classMap parameter
 * @param classMap class map for which ID is needed
 */
public Object getNewId(pl.map.ClassMap classMap) throws PlException;
/**
 * Init ID generator.
 *
 * @param properties properties for this ID generator
 */
public void init(Properties parameters) throws PlException;
}
