package pl;

import pl.map.ClassMap;
import pl.sql.RelationalDatabase;

public interface Configurable
{
    public void addClassMap(ClassMap classMap) throws PlException;
    public ClassMap getClassMap(String name);
    public void addRelationalDatabase(RelationalDatabase database) throws PlException;
    public RelationalDatabase getRelationalDatabase(String name);
}