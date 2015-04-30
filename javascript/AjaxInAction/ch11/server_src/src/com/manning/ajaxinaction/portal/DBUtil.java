package com.manning.ajaxinaction.portal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

  public static DataSource source=null;
  private static final String
    DATASOURCE_JNDI_CONTEXT = "java:comp/env/jdbc/ajaxDS";

  public static Connection getConnection()
  throws MissingResourceException{
	if (source==null){
	  initDataSource();
    }
    return source.getConnection();

  }

  private static void initDataSource()
  throws MissingResourceException{
    try {
      Context initialContext = new InitialContext();
      DataSource datasource = (DataSource)
        initialContext.lookup(DATASOURCE_JNDI_CONTEXT);
      if (datasource != null){
        source=datasource;
      }else{
        throw new MissingResourceException
          ("cannot lookup datasource");
      }
    }
    catch ( NamingException ex ) {
      throw new MissingResourceException
        ("Cannot get connection " + ex);
    }
  }

  public static List getPortalWindows(User user){
    List list=new ArrayList();
  	Connection conn=getConnection();
  	try{
  	  String sql="SELECT * FROM portal_windows "
  	  	+"WHERE user_id="+user.getId();
  	  Statement stmt=conn.createStatement();
  	  ResultSet rs=stmt.executeQuery(sql);
  	  PortalWindow win=null;
  	  while (rs.next()){
  	  	int id=rs.getInt("id");
  	  	int x=rs.getInt("xPos");
  	  	int y=rs.getInt("yPos");
  	  	int w=rs.getInt("width");
  	  	int h=rs.getInt("height");
  	  	String url=rs.getString("url");
  	  	String title=rs.getString("title");
  	  	win=new PortalWindow(
  	  	  id,user,x,y,w,h,url,title
  	  	);
  	  	list.add(win);
  	  }
  	  rs.close();
  	  stmt.close();
  	}catch (SQLException sqlex){
  	}
  	return list;
  }
  public static void savePortalWindow(PortalWindow window){
  	Connection conn=getConnection();
  	int x=window.getXPos();
  	int y=window.getYPos();
  	int w=window.getWidth();
  	int h=window.getHeight();
  	int id=window.getId();
  	String sql="UPDATE portal_windows SET xPos="+x
	  +",yPos="+y
  	  +",width="+w
	  +",height="+h
	  +" WHERE id="+id;
  	try{
	  Statement stmt=conn.createStatement();
	  stmt.execute(sql);
	  stmt.close();
  	}catch (SQLException sqlex){
  	}
  }
}
