package pl.sql;

/**
 * This class contains constants for all types supported by the
 * Persistence Layer. Each type has a corresponding <code>get</code>
 * method in java.sql.ResultSet interface (except UNDEFINED).
 *
 * @author: Artem Rudoy
 */
public class PlTypes
{
    public final static int UNDEFINED = -1;
    
    public final static int ARRAY = 1;
    public final static int ASCII_STREAM = 2;
    public final static int BIG_DECIMAL = 3;
    public final static int BINARY_STREAM = 4;
    public final static int BLOB = 5;
    public final static int BOOLEAN = 6;
    public final static int BYTE = 7;
    public final static int BYTES = 8;
    public final static int CHARACTER_STREAM = 9;
    public final static int CLOB = 10;
    public final static int DATE = 11;
    public final static int DOUBLE = 12;
    public final static int FLOAT = 13;
    public final static int INT = 14;
    public final static int LONG = 15;
    public final static int OBJECT = 16;
    public final static int REF = 17;
    public final static int SHORT = 18;
    public final static int STRING = 19;
    public final static int TIME = 20;
    public final static int TIMESTAMP = 21;
    
    public static void setParameter(java.sql.PreparedStatement pst, Object value, int type, int index)
    throws pl.PlException
    {
        try
        {
            if(value == null)
                pst.setObject(index, value);
            else
            {
                switch(type)
                {
                    case ARRAY:
                        pst.setArray(index, (java.sql.Array)value);
                        break;
                        
                    case ASCII_STREAM:
                        break;
                        
                    case BIG_DECIMAL:
                        pst.setBigDecimal(index, (java.math.BigDecimal)value);
                        break;
                        
                    case BINARY_STREAM:
                        break;
                        
                    case BLOB:
                        pst.setBlob(index, (java.sql.Blob)value);
                        break;
                        
                    case BOOLEAN:
                        pst.setBoolean(index, ((Boolean)value).booleanValue());
                        break;
                        
                    case BYTE:
                        pst.setByte(index, ((Byte)value).byteValue());
                        break;
                        
                    case BYTES:
                        pst.setBytes(index, (byte[])value);
                        break;
                        
                    case CHARACTER_STREAM:
                        break;
                        
                    case CLOB:
                        pst.setClob(index, (java.sql.Clob)value);
                        break;
                        
                    case DATE:
                        pst.setDate(index, (java.sql.Date)value);
                        break;
                        
                    case DOUBLE:
                        pst.setDouble(index, ((Double)value).doubleValue());
                        break;
                        
                    case FLOAT:
                        pst.setFloat(index, ((Float)value).floatValue());
                        break;
                        
                    case INT:
                        pst.setInt(index, ((Integer)value).intValue());
                        break;
                        
                    case LONG:
                        pst.setLong(index, ((Long)value).longValue());
                        break;
                        
                    case OBJECT:
                        pst.setObject(index, value);
                        break;
                        
                    case REF:
                        pst.setRef(index, (java.sql.Ref)value);
                        break;
                        
                    case SHORT:
                        pst.setShort(index, ((Short)value).shortValue());
                        break;
                        
                    case STRING:
                        pst.setString(index, (String)value);
                        break;
                        
                    case TIME:
                        pst.setTime(index, (java.sql.Time)value);
                        break;
                        
                    case TIMESTAMP:
                        pst.setTimestamp(index, (java.sql.Timestamp)value);
                        break;
                        
                        default:
                            throw new pl.PlException("Unknown type");
                }
            }
        }
        catch(java.sql.SQLException e)
        {
            throw new pl.PlException(e);
        }
    }
    
    public static Object getValue(java.sql.ResultSet rs, int index, int type)
    throws pl.PlException
    {
        try
        {
            switch (type)
            {
                case ARRAY:
                    return rs.getArray(index);
                    
                case ASCII_STREAM:
                    return rs.getAsciiStream(index);
                    
                case BIG_DECIMAL:
                    return rs.getBigDecimal(index);
                    
                case BINARY_STREAM:
                    return rs.getBinaryStream(index);
                    
                case BLOB:
                    return rs.getBlob(index);
                    
                case BOOLEAN:
                    return new Boolean(rs.getBoolean(index));
                    
                case BYTE:
                    return new Byte(rs.getByte(index));
                    
                case BYTES:
                    return rs.getBytes(index);
                    
                case CHARACTER_STREAM:
                    return rs.getCharacterStream(index);
                    
                case CLOB:
                    return rs.getClob(index);
                    
                case DATE:
                    return rs.getDate(index);
                    
                case DOUBLE:
                    return new Double(rs.getDouble(index));
                    
                case FLOAT:
                    return new Float(rs.getFloat(index));
                    
                case INT:
                    return new Integer(rs.getInt(index));
                    
                case LONG:
                    return new Long(rs.getLong(index));
                    
                case OBJECT:
                    return rs.getObject(index);
                    
                case REF:
                    return rs.getRef(index);
                    
                case SHORT:
                    return new Short(rs.getShort(index));
                    
                case STRING:
                    return rs.getString(index);
                    
                case TIME:
                    return rs.getTime(index);
                    
                case TIMESTAMP:
                    return rs.getTimestamp(index);
                    
                    default:
                        throw new pl.PlException("Unknown type");
            }
        }
        catch(java.sql.SQLException e)
        {
            throw new pl.PlException(e);
        }
    }
    
    public static int getPlType(Class cl)
    {
        if(cl.equals(java.sql.Array.class))
        {
            return ARRAY;
        }
        else if(cl.equals(java.math.BigDecimal.class))
        {
            return BIG_DECIMAL;
        }
        else if(cl.equals(java.io.InputStream.class))
        {
            return BINARY_STREAM;
        }
        else if(cl.equals(java.sql.Blob.class))
        {
            return BLOB;
        }
        else if(cl.equals(boolean.class) || cl.equals(Boolean.class))
        {
            return BOOLEAN;
        }
        else if(cl.equals(byte.class) || cl.equals(Byte.class))
        {
            return BYTE;
        }
        else if(cl.equals(byte[].class))
        {
            return BYTES;
        }
        else if(cl.equals(java.io.Reader.class))
        {
            return CHARACTER_STREAM;
        }
        else if(cl.equals(java.sql.Clob.class))
        {
            return CLOB;
        }
        else if(cl.equals(java.sql.Date.class))
        {
            return DATE;
        }
        else if(cl.equals(double.class) || cl.equals(Double.class))
        {
            return DOUBLE;
        }
        else if(cl.equals(float.class) || cl.equals(Float.class))
        {
            return FLOAT;
        }
        else if(cl.equals(int.class) || cl.equals(Integer.class))
        {
            return INT;
        }
        else if(cl.equals(long.class) || cl.equals(Long.class))
        {
            return LONG;
        }
        else if(cl.equals(java.sql.Ref.class))
        {
            return REF;
        }
        else if(cl.equals(short.class) || cl.equals(Short.class))
        {
            return SHORT;
        }
        else if(cl.equals(String.class))
        {
            return STRING;
        }
        else if(cl.equals(java.sql.Time.class))
        {
            return TIME;
        }
        else if(cl.equals(java.sql.Timestamp.class))
        {
            return TIMESTAMP;
        }
        else
        {
            return OBJECT;
        }
    }
}
