package com.itsz.sht.service.mis;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.itsz.sht.common.ApplicationConstants.Datasource;
import com.taifook.framework.foundation.logging.Logger;
import com.taifook.framework.foundation.logging.LoggerFactory;
import com.taifook.framework.platform.persist.DBDataSource;
import com.taifook.framework.platform.persist.DBException;
import com.taifook.framework.platform.persist.DataSourceFactory;
import com.taifook.misgateway.TradingAccount;
import com.taifook.misgateway.TradingAccountCashUtils;
import com.taifook.misgateway.TradingAccountInfoUtils;
import com.taifook.misgateway.TradingAccountProfolio;
import com.taifook.misgateway.TransactionResult;

public class MISUtil {

	/*
     * memo code for eservice memodebit securities accounts
     */
    private static final String MEMODEBIT_MEMOCODE_SECURITIES = "MDAC";
    private static final String MEMODEBIT_MEMOCODE_SECURITIES_RTQ = "MDAE";
    
    private static String misDayEndProcessingFlag="N";
    
    /*
     * memo code for eservice memodebit futures accounts
     */
    private static final String MEMODEBIT_MEMOCODE_FUTURES = "CD37";
    private static final String MEMODEBIT_MEMOCODE_FUTURES_RTQ = "CD38";
    
    
    private static final String MEMODEBIT_MARKET_SECURITIES = "01";
    private static final String MEMODEBIT_MARKET_FUTURES = "HK";

    private static Logger logger = LoggerFactory.instance().getLogger(MISUtil.class);
    
    
    /**
     * Get TradingAccountProfolio for the specified acCode
     * @param acCode the acCode
     * @return TradingAccountProfolio
     * @throws DBException
     */
    
	private static MISUtil instance = null;
    
	public static MISUtil getInstance() {
		if (instance == null) {
			instance = new MISUtil();
		}
		return instance;
	}
    
	private MISUtil() {
		
	}
	
	
    private TradingAccountProfolio getAccProfolio(final String acCode) throws DBException{
        
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
    
    private  void dormancy(){
    	Random random = new Random ();
        long s = 200 + new Long(random.nextInt(2000)).longValue();
        try {
    		Thread.sleep(s);
    	} catch (InterruptedException ex) {
    		logger.error("Thread sleep is error: ", ex);
    	}
    }
    
    public String getMisDayEndProcessingFlag(){
    	
    	return misDayEndProcessingFlag;
    }
    
    public void  updateMisDayEndProcessingFlag(String flag){
    	misDayEndProcessingFlag=flag;    	
    }
    
    /**
     * Get available balance of the acCode
     * @param acCode acCode
     * @return
     * @throws DBException
     */
    public  BigDecimal getAccBalance(String acCode) throws DBException{
    	long time = System.currentTimeMillis();
    	BigDecimal availBal ;
    	availBal = getAccProfolio(acCode).getTotalAvailableBalance();
        logger.info("com.taifook.eservice.common.application.util.MISGatewayUtil, getAccBalance(), " + (System.currentTimeMillis() - time) + ";");
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
    public  TransactionResult memoDebit(final String acCode, final BigDecimal money, final String memoCodeType, final String remark) throws DBException {
    	long time = System.currentTimeMillis();
        if (acCode == null || money == null)
            throw new IllegalArgumentException("null accout or money");
        final TradingAccount trdAcc = new TradingAccount(acCode);
        final String market, memoCode;
        
   
        market = MEMODEBIT_MARKET_SECURITIES;
        memoCode = "RTQ".equals(memoCodeType)? MEMODEBIT_MEMOCODE_SECURITIES_RTQ:MEMODEBIT_MEMOCODE_SECURITIES;
   
        logger.info("MISGatewayUtil.memoDebit('true','false',conn," + trdAcc
				+ ",'batchop'," + (new java.util.Date()) + "," + money + ","
				+ memoCode + "," + remark + ",'HKD'," + market + ")");
        TransactionResult result = new TransactionResult();
        
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
        result = (TransactionResult)executeMisCommand(command);    
        logger.info("com.taifook.eservice.common.application.util.MISGatewayUtil, memoDebit(), " + (System.currentTimeMillis() - time) + ";");
        return result;    	
    }


    /**
     * Represent a invoke of MISGateway interface
     * @author yhliu
     *
     */
    private  interface MISCommand{
        public Object execute(Connection conn) throws Exception;
    }
    
    private  Object executeMisCommand(MISCommand command) throws DBException{
        long startTime = 0;
        
        if (logger.isDebugEnabled()){
            startTime = System.currentTimeMillis();
            logger.debug(command.toString());
        }

        
        Connection conn = null;
        DataSource ds = null;
        try {
        	ClassPathResource resource = new ClassPathResource("mis-ds.xml");
        	XmlBeanFactory factory = new XmlBeanFactory(resource);
        	ds = (DataSource)factory.getBean("dataSource");
            conn = ds.getConnection();           
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
                    conn.close();
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
    public  DBDataSource lookupMISDataSource() throws DBException{
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
    
}
