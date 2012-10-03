package pl.test;
import java.sql.*;
import java.util.*;

import pl.*;
import pl.map.*;
import pl.sql.*;

public class SequenceKeyGenerator  implements IdGenerator
{

  String sequenceName=null;
  public SequenceKeyGenerator()
  {

  }


  public Object getNewId(pl.map.ClassMap classMap) throws PlException
  {

    String select = "select nextval('"+ sequenceName +"')";
    RelationalDatabase dataBase = classMap.getRelationalDatabase();
    PreparedStatement pstmt = null;
 	  Connection conn = null;
	  try
	  {
      conn = dataBase.getConnection();
      pstmt = conn.prepareStatement(select);
		  ResultSet rs = pstmt.executeQuery();

		  if(rs.next())
		  {
			  return rs.getObject(1);
		  }else{
        return null;
      }
	  }
	  catch (Throwable e)
	  {
		  throw new pl.PlException(e.getMessage());
    }
	  finally
	  {
      try {
 		    pstmt.close();
      }catch (SQLException ex){}
		  dataBase.freeConnection(conn);
    }
  }
  /**
  * Init ID generator.
  *
  * @param properties properties for this ID generator
  */
  public void init(Properties parameters) throws PlException
  {
    sequenceName = (String)parameters.get("sequence");
  }
}