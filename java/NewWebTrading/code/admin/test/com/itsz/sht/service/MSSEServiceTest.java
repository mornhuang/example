package com.itsz.sht.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Properties;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.mtss.mss.webservice.ClientFundMemo;
import com.taifook.mtss.mss.webservice.EServiceIntegrationService;
import com.taifook.mtss.mss.webservice.EServiceIntegrationServiceServiceLocator;
import com.taifook.mtss.mss.webservice.ErrorInfoException;
import com.taifook.mtss.mss.webservice.FundChannel;
import com.taifook.mtss.mss.webservice.TerminalInfoDto;

public class MSSEServiceTest {
	private Log log = LogFactory.getLog(this.getClass());
	private static String HKD = "HKD";
	
	public static void main(String [] args){
		InputStream is = MSSEServiceTest.class.getClass().getResourceAsStream("/MSSEconfig.properties");
        Properties prop = new Properties();
     	try {
     		prop.load(is);
     		String url = prop.getProperty("service_address");
            String name = prop.getProperty("service_name");
            String password = prop.getProperty("service_password");
            String version = prop.getProperty("terminal_info_version");
            String infoIp = prop.getProperty("terminal_info_ip");
            
            EServiceIntegrationServiceServiceLocator eissLocator = new EServiceIntegrationServiceServiceLocator();
    		eissLocator.setEServiceIntegrationServicePortEndpointAddress(url);
    		eissLocator.setMaintainSession(true);
        	EServiceIntegrationService client = eissLocator.getEServiceIntegrationServicePort();
			client.login(name, password, new TerminalInfoDto(version,infoIp));
			client.switchCompany("TFS");
			
			String accountID = "02-0100567-30";
			
			System.out.println(client.getPortfolioAvailableWithdrawBalance(accountID, HKD));
			
			Calendar c = Calendar.getInstance();
			c.set(2010, 7, 2);
			System.out.println("Transaction Date = " + c.getTime().toString());
			ClientFundMemo clientFundMemo = new ClientFundMemo();
			clientFundMemo.setAccountStmtId(accountID);
			clientFundMemo.setCurrencyCode(HKD);
			clientFundMemo.setAmount(new BigDecimal("1"));
			clientFundMemo.setFundChannelCode(FundChannel.ESERVICES);
			clientFundMemo.setIsForceDebit(new Boolean(true));
			clientFundMemo.setRemarkForStatementInfoList(null);
			clientFundMemo.setTransactionTypeCode("RTQ_FEE_MD"); //"FOR_ESERVICE_DR"
			clientFundMemo.setValueDate(c);
//			clientFundMemo.setSegregatedFundCode("");
			System.out.println(client.debitMemoClientFund(clientFundMemo));
			
			System.out.println(client.getPortfolioAvailableWithdrawBalance(accountID, HKD));
			
			client.logout();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(e instanceof ErrorInfoException){
				System.out.println("errorCode: " + ((ErrorInfoException) e).getErrorInfo().getErrorCode());
			}else {
				e.printStackTrace();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
