package com.itsz.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

import com.itsz.common.Constants;


public class DBConnPoolUtil {

	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
    static{
        try {
            JAXPConfigurator.configure(AppConfig.getBaseAppPath()+"/WEB-INF/classes/proxool.xml", false);
        } catch (ProxoolException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;     
        try {
            Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
            con = DriverManager.getConnection("proxool.DBPool");
        } catch (Exception e) {
            log_debug.error(e);
        }
        
        return con;

    }
	public static void closeConnection(Connection con, Statement s, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}

			if (s != null) {
				s.close();
				s = null;
			}

			if (con != null) {
				con.close();
				con = null;
			}
		} 
		catch (SQLException sqle) {
			log_debug.info("failure close connection");
		}
	}

	public static void closeConnection(Connection con,PreparedStatement ps,ResultSet rs){
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}

			if (ps != null) {
				ps.close();
				ps = null;
			}

			if (con != null) {
				con.close();
				con = null;
			}
		} 
		catch (SQLException sqle) {
			log_debug.info("failure close connection");
		}
	}
	public static void closeConnection(Connection con,PreparedStatement ps){
		try {

			if (ps != null) {
				ps.close();
				ps = null;
			}

			if (con != null) {
				con.close();
				con = null;
			}
		} 
		catch (SQLException sqle) {
			log_debug.info("failure close connection");
		}
	}

}
