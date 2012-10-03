package cn.itsz.newsim.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.itsz.newsim.exception.ITSZException;
import cn.itsz.newsim.view.action.BaseAction;

public class CreateTableDao {
	protected static Log log = LogFactory.getLog(BaseAction.class);

	private String sqlCreateTable1="create table USERPROFILE(LOGINID VARCHAR(26) not null,PASSWORD VARCHAR(60) not null,EMAIL VARCHAR(50) not null,ADDNO VARCHAR(20) not null,TELEPHONE VARCHAR(20) not null,USERNAME VARCHAR(26) not null,CLIENT VARCHAR(20) not null,CLIENTMONEY DOUBLE not null,transactionProtection char(1) not null,allowTradeStatusFlag char(1) not null,tradeAccount VARCHAR(50) not null,CLIENTNO VARCHAR(20),LASTUPDATE CHAR(19),primary key (LOGINID))";
	private String sqlCreateTable2="create table admin(uname varchar(20) not null,upass varchar(35) not null,primary key(uname))";
	private String sqlCreateTable3="create table parameter(pname varchar(20) not null,pvalue varchar(20) not null,pdesc varchar(100) not null,primary key(pname))";
	private String sql3="select * from userprofile ";
	private String sql4="insert into admin values('admin','670b14728ad9902aecba32e22fa4f6bd')";
	private String sql5="insert into parameter values('money','10000000','初始金额')";
	private String sql6="insert into parameter values('stime','9','交易开始时间')";
	private String sql7="insert into parameter values('etime','17','交易结束时间')";
	
	private String URL="";
	private String DIRIVER="";
	public void CreatTable() {
		Statement st=null;
		try {
			
			InputStream input = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("newsim.properties");
			
			Properties config = new Properties();
			try {
				config.load(input);
				URL = config.getProperty("jdbc.url");
				DIRIVER=config.getProperty("jdbc.driver");
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				 Class.forName(DIRIVER);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn=DriverManager.getConnection(URL);
			st = conn.createStatement();
			try {
				st.execute(sql3);
			} catch (Exception e) {
				st.execute(sqlCreateTable1);
				st.execute(sqlCreateTable2);
				st.execute(sqlCreateTable3);
				st.execute(sql4);
				st.execute(sql5);
				st.execute(sql6);
				st.execute(sql7);
//				System.out.println("create table");
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		}finally{
			try {
				if(st!=null){
					st.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
}
