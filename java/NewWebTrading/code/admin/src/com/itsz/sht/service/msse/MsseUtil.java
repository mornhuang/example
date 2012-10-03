package com.itsz.sht.service.msse;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import com.taifook.framework.foundation.logging.Logger;
import com.taifook.framework.foundation.logging.LoggerFactory;
import com.taifook.mtss.mss.webservice.ClientFundMemo;
import com.taifook.mtss.mss.webservice.EServiceIntegrationService;
import com.taifook.mtss.mss.webservice.EServiceIntegrationServiceServiceLocator;
import com.taifook.mtss.mss.webservice.ErrorInfo;
import com.taifook.mtss.mss.webservice.ErrorInfoException;
import com.taifook.mtss.mss.webservice.TerminalInfoDto;

public class MsseUtil {
	private static Logger log = LoggerFactory.instance().getLogger(MsseUtil.class);
	
	private static MsseUtil instance;
	
	private static final String resource_path = "/MSSEconfig.properties"; 
	private MsseConfig config;
	private TerminalInfoDto info;
	
	private static final String COMPANY = "TFS";
	
	public static MsseUtil getInstance () {
		if (instance == null) {
			synchronized(MsseUtil.class){
				if(instance == null){
					instance = new MsseUtil();
				}
			}
		}
        return instance;
	}
	
	public MsseUtil(){
		InputStream is = getClass().getResourceAsStream(resource_path);
		Properties prop = new Properties();
		try {
			prop.load(is);
			config = new MsseConfig(prop.getProperty("service_address").trim(), prop.getProperty("service_name").trim(), prop.getProperty("service_password").trim());
			info = new TerminalInfoDto(prop.getProperty("terminal_info_version").trim(), prop.getProperty("terminal_info_ip").trim());
		} catch (IOException e) {
			log.error("Load " + resource_path + " failed: " + e);
		}
	}
	
	public BigDecimal getBalance(String accountStatementId, String ccyCode) throws RemoteException, ErrorInfoException, ServiceException{
		EServiceIntegrationServiceServiceLocator serviceLocator = new EServiceIntegrationServiceServiceLocator();
		serviceLocator.setEServiceIntegrationServicePortEndpointAddress(config.getUrl());
		serviceLocator.setMaintainSession(true);
    	EServiceIntegrationService client = serviceLocator.getEServiceIntegrationServicePort();
		client.login(config.getUsername(), config.getPassword(), info);
		client.switchCompany(COMPANY);
		BigDecimal balance = client.getPortfolioAvailableWithdrawBalance(accountStatementId, ccyCode);
		client.logout();
		return balance;
	}
	
	public MsseResult memoDebit(ClientFundMemo clientFundMemo) throws RemoteException, ServiceException {
		MsseResult result = new MsseResult();
		EServiceIntegrationService client = null;
		try {
			EServiceIntegrationServiceServiceLocator serviceLocator = new EServiceIntegrationServiceServiceLocator();
			serviceLocator.setEServiceIntegrationServicePortEndpointAddress(config.getUrl());
			serviceLocator.setMaintainSession(true);
			client = serviceLocator.getEServiceIntegrationServicePort();
			client.login(config.getUsername(), config.getPassword(), info);
			client.switchCompany(COMPANY);
			result.setResultCode(String.valueOf(client.debitMemoClientFund(clientFundMemo)));
			result.setCompleted(true);
		} catch(ErrorInfoException e) {
			result.setCompleted(false);
			ErrorInfo[] errorList = null;
			if(e.getErrorInfo() != null){
				errorList = new ErrorInfo[1];
				errorList[0] = e.getErrorInfo();
			} else {
				errorList = e.getErrorList();
			}
			result.setErrorList(errorList);
		} finally {
			if(client != null){
				try {
					client.logout();
				} catch (Exception e){
					log.error("Call MSSE logout failed: " + e);
				}
			}
		}
		return result;
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String account = "02-0100567-30";
		final String ccyCode = "HKD";
		try{
			System.out.println(account + ":  " + MsseUtil.getInstance().getBalance(account, ccyCode));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
