package com.itsz.sht.service;





import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;

import com.itsz.sht.common.UserProfileConstants;
import com.itsz.sht.common.UserProfileUtilizer;
import com.itsz.sht.common.ApplicationConstants.Datasource;
import com.itsz.sht.service.mis.MISUtil;
import com.taifook.framework.foundation.logging.Logger;
import com.taifook.framework.foundation.logging.LoggerFactory;
import com.taifook.framework.platform.persist.DBDataSource;
import com.taifook.framework.platform.persist.DBException;
import com.taifook.framework.platform.persist.DataSourceFactory;
import com.taifook.misgateway.TradingAccount;
import com.taifook.misgateway.TradingAccountCashUtils;
import com.taifook.misgateway.TradingAccountInfoUtils;
import com.taifook.misgateway.TradingAccountProfolio;
import com.taifook.misgateway.TransactionMessage;
import com.taifook.misgateway.TransactionResult;

/**
 * Title:
 * Description:
 * Copyright:   itsz Copyright (c) 2004
 * Company:
 * @author yhliu
 * @version 1.0
 * $Id: MISGatewayUtilTest.java,v 1.3 2011/01/05 03:43:48 cyzeng Exp $
 * 20061210 Danny:
 * For CCIS phase 10 APIs available , only memoDebit() and getAccBalance keep down
 */

public final class MISGatewayUtilTest{
    /*
     * memo code for eservice memodebit securities accounts
     */
    private static final String MEMODEBIT_MEMOCODE_SECURITIES = "MDAC";
    private static final String MEMODEBIT_MEMOCODE_SECURITIES_RTQ = "MDAE";
    
    /*
     * memo code for eservice memodebit futures accounts
     */
    private static final String MEMODEBIT_MEMOCODE_FUTURES = "CD37";
    private static final String MEMODEBIT_MEMOCODE_FUTURES_RTQ = "CD38";
    
    
    private static final String MEMODEBIT_MARKET_SECURITIES = "01";
    private static final String MEMODEBIT_MARKET_FUTURES = "HK";

    private static Logger logger = LoggerFactory.instance().getLogger(MISGatewayUtilTest.class);
    
    private MISGatewayUtilTest(){}
    
    /**
     * Get TradingAccountProfolio for the specified acCode
     * @param acCode the acCode
     * @return TradingAccountProfolio
     * @throws DBException
     */
    private static TradingAccountProfolio getAccProfolio(final String acCode) throws DBException{
        
        MISCommand command = new MISCommand(){
            public Object execute(Connection conn) throws Exception {
                return TradingAccountInfoUtils.getAccountProfolio(conn, new TradingAccount(acCode));
            }
            
            public String toString(){
                return "Get account info for " + acCode;
            }

        };
        
        TradingAccountProfolio accProfolio = (TradingAccountProfolio) executeMisCommand(command);
        
        if (accProfolio == null || accProfolio.getTotalAvailableBalance() == null)
            throw new DBException("Misgateway return null Account Profolio or availableBalance");
                
        return accProfolio;
    }
    
    /**
     * Get available balance of the acCode
     * @param acCode acCode
     * @return
     * @throws DBException
     */
    public static BigDecimal getAccBalance(String acCode) throws DBException{
                
        BigDecimal availBal = getAccProfolio(acCode).getTotalAvailableBalance();
        
        return availBal;
    }   
    
    /**
     * memodebit money from the account
     * @param acCode the acCode
     * @param money the money amount
     * @param memoCodeType currently only differentiate "RTQ"
     * @param remark description, as "eservice purchase point"
     * @return
     * @throws DBException
     */
    public static TransactionResult memoDebit(final String acCode, final BigDecimal money, final String memoCodeType, final String remark) throws
    DBException {
        
        if (acCode == null || money == null)
            throw new IllegalArgumentException("null accout or money");
        
        
        final TradingAccount trdAcc = new TradingAccount(acCode);
        final String market, memoCode;
        
        String acType = UserProfileUtilizer.checkACType(acCode);
        if (UserProfileConstants.ACType.FUTURES.equals(acType)){
            market = MEMODEBIT_MARKET_FUTURES;
            memoCode = "RTQ".equals(memoCodeType)? MEMODEBIT_MEMOCODE_FUTURES_RTQ:MEMODEBIT_MEMOCODE_FUTURES;
        }else{
            market = MEMODEBIT_MARKET_SECURITIES;
            memoCode = "RTQ".equals(memoCodeType)? MEMODEBIT_MEMOCODE_SECURITIES_RTQ:MEMODEBIT_MEMOCODE_SECURITIES;
        }
        logger.info("MISGatewayUtil.memoDebit('true','false',conn," + trdAcc
				+ ",'batchop'," + (new java.util.Date()) + "," + money + ","
				+ memoCode + "," + remark + ",'HKD'," + market + ")"); 
        MISCommand command = new MISCommand(){
            public Object execute(Connection conn) throws Exception {
                return TradingAccountCashUtils.memoDebit(true, false,
                        conn, trdAcc, "batchop", new java.util.Date(), money,
                        memoCode, remark, "HKD", market);
            }     
            
            public String toString(){
                return "memoDebit acCode: " + acCode + " money: " + money;
            }
        };        
        
        
        return (TransactionResult) executeMisCommand(command);        
    }

   /**
     * Represent a invoke of MISGateway interface
     * @author yhliu
     *
     */
    private static interface MISCommand{
        public Object execute(Connection conn) throws Exception;
    }
    
    private static Object executeMisCommand(MISCommand command) throws DBException{
        long startTime = 0;
        
        if (logger.isDebugEnabled()){
            startTime = System.currentTimeMillis();
            logger.debug(command.toString());
        }

        
        Connection conn = null;
//        DBDataSource ds = lookupMISDataSource();
        
        try {
//        	Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
//            String url="jdbc:microsoft:sqlserver://10.100.1.13:1433;DatabaseName=taifook";
//            String user="sa";
//            String password="12345678";
            
        	Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
            String url="jdbc:microsoft:sqlserver://172.25.13.220:1433;DatabaseName=taifook";
            String user="sa";
            String password="sa1890";
            
            
//        	String url="jdbc:microsoft:sqlserver://192.168.89.44:1433;DatabaseName=taifook"; 
//            String user="sa"; 
//            String password="1890"; 
            conn= DriverManager.getConnection(url,user,password);
//            conn = ds.getConnection();           
            Object obj = command.execute(conn);
            
            return obj;
        } catch (DBException e) {
            throw e;
        } catch (Exception e) {
            throw new DBException("Unknown Exception from MISGateway:", e);
        }
        finally{
            
            if (conn != null)
                try {
                	conn = null;
//                    ds.freeConnection(conn);
                }
            catch (Exception ex) {
                logger.error("Error execute MisCommand", ex);
            }
            
            if (logger.isDebugEnabled()){
                logger.debug("MIS Gateway Command duration:  " + (System.currentTimeMillis() - startTime) + "ms.");
            }            
        }                
    }
    
    /**
     * find a proper datasource for MIS DB
     * @return
     * @throws DBException
     */
    public static DBDataSource lookupMISDataSource() throws DBException{
        String dsKey = Datasource.TAIFOOK;
        
        DBDataSource ds = (DBDataSource)DataSourceFactory.instance().getDataSource(Datasource.TAIFOOK);
        
        if (ds == null){
            ds = (DBDataSource)DataSourceFactory.instance().getDefault();
            dsKey = "DEFAULT";
        }
        
        if (ds == null){
            throw new DBException("Can not get MIS DataSource for key: " + dsKey);
        }
        
        if (logger.isDebugEnabled()){
            logger.debug("Get MIS DataSource from key: " + dsKey);
        }
        
        return ds;
    }
    
    public static void main(String[] args) {
    	String acCode = "02-0000043-33";
//    	BigDecimal bd1 = getAccBalance(acCode);
//    	System.out.println(bd1.doubleValue());
    	
    	TransactionResult rs = MISUtil.getInstance().memoDebit(acCode, new BigDecimal("1"), "RTQ", "NewWebTrading Test");
    	System.out.println(rs.getIsCompleted());
    	TransactionMessage[] tms = rs.getTransactionMessage();
    	for(TransactionMessage tm :tms){
    		System.out.println(tm.getMsgCode()+ ": " + tm.getMsgContent());
    	}
    	
//    	BigDecimal money = new BigDecimal("0.17");
//    	String memoCodeType = "RTQ";
//    	String remark = "test API by kevin-IT";
//    	memoDebit(acCode, money, memoCodeType, remark);
//    	System.out.println("Memodebit for " + money.doubleValue() + " HKDs.");
//    	BigDecimal bd2 = getAccBalance(acCode);
//    	System.out.println(bd2.doubleValue());
    }
}

