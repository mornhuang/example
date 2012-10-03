package com.itsz.sht.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.axis.AxisFault;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itsz.sht.exception.ESAppException;
import com.itsz.sht.service.ccis.CCISAccountPredicate;
import com.itsz.sht.service.ccis.CCISUtil;
import com.taifook.mtss.ccis.webservice.impl.CCISWsException;
import com.taifook.mtss.ccis.webservice.impl.ClntAddrModel;
import com.taifook.mtss.ccis.webservice.impl.ClntEmailModel;
import com.taifook.mtss.ccis.webservice.impl.ClntNotfOptnModel;
import com.taifook.mtss.ccis.webservice.impl.ClntPhoneModel;
import com.taifook.mtss.ccis.webservice.impl.EServiceClientInfoModel;
import com.taifook.mtss.ccis.webservice.impl.InvBgModel;

public class CCISUtilTestClient {
	private static Logger logger = Logger.getLogger(CCISUtilTestClient.class);
	private static CCISUtilTestClient instance = null;
	
	public static CCISUtilTestClient getInstance(String url,String user,String pass) {
		if (instance == null) {
			instance = new CCISUtilTestClient(url,user,pass);
		}
		return instance;
	}
	private CCISUtilTestClient(String url,String user,String pass) {
		
	}	
	public static void main(String arg[]) {
		
		System.out.println("======================= CCIS API TEST =======================");
	    System.out.println();
	    System.out.println();
	    System.out.println(" CCIS Commands Start:" + (new Date(System.currentTimeMillis())));
	    System.out.println(" -A:getAccInfoListByClntId" ) ;
	    System.out.println(" -A2:getAllAccByClntId" ) ;
	    System.out.println(" -B:getInvBg");
	    System.out.println(" -C:updateInvBg");
	    System.out.println(" -D:getCustCodeListByPatialAcCode");
	    System.out.println(" -E:getEstmtEmailFax");
	    System.out.println(" -F:updateEstmtEmailFax");
	        
	    System.out.println(" -I:getEmailByClntId");
	    System.out.println(" -J:getPhoneByClntId");
	    System.out.println(" -K:getCustCodeListByBirthday");
	    System.out.println(" -L:getCustCodeListByDocID");
	    System.out.println(" -M:getEServiceClientInfoModel");
	    System.out.println(" -N:getAccCodeListByClntId");
	    System.out.println(" -N2:getAccCodeListByClntIdForEStatement");
	    System.out.println(" -NA:getClntNotiOptByClntId");
	    System.out.println(" -NB:getClntEmailByClntId");
	    System.out.println(" -NC:getClntPhoneByClntId");
	    System.out.println(" -NJ:getClntAddrByClntId");
	    System.out.println(" -O:getEstatementInfo");
	    System.out.println(" -P:updateEstatementInfo");
	    System.out.println(" -U:updateStmtSendOptn");
	    
	    System.out.println(" -q:end");
	    
	    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	    
	    String input = "exit";
//		private static String url2="http://10.101.10.153:9010/CCISSIT01/services";
//		private static String user="eservice";
//		private static String pass="abc123";
	    CCISUtilTestClient ccis = getInstance("http://10.101.10.153:9010/CCISSIT01/services","eservice","abc123");
		try {
		    while (true) {
			      try {
			        input = stdin.readLine();
			        if (input.equalsIgnoreCase("q")) {
			          break;
			        }
			        else if (input.equalsIgnoreCase("A")) {
			        	System.out.println("Please input client statement id ");
			    	    BufferedReader accin = new BufferedReader(new InputStreamReader(System.in));
			    	    String accinput="q";
			    	    accinput = accin.readLine();
			    	    
			    	    System.out.println("filter=null");
						ccis.getAccInfoListByClntId(accinput, null);
						
						System.out.println("filter=stock+active");	
						ccis.getAccInfoListByClntId(accinput,
									PredicateUtils.andPredicate(
											CCISAccountPredicate.FLT_AC_ACTIVE,
											CCISAccountPredicate.FLT_AC_STOCK));
						
						System.out.println("filter=futures+active");
						ccis.getAccInfoListByClntId(accinput,
									PredicateUtils.andPredicate(
											CCISAccountPredicate.FLT_AC_ACTIVE,
											CCISAccountPredicate.FLT_AC_FUTURES));

						System.out.println("filter=stock+futures+active");
						ccis.getAccInfoListByClntId(accinput, 
									PredicateUtils.andPredicate(
											CCISAccountPredicate.FLT_AC_ACTIVE,
											CCISAccountPredicate.FLT_AC_STOCK_OR_FUTURES));
						
						System.out.println("filter=stock+active_inactive");
						ccis.getAccInfoListByClntId(accinput,
									PredicateUtils.andPredicate(
											CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
											CCISAccountPredicate.FLT_AC_STOCK));
						
						System.out.println("filter=futures+active+inactive");
						ccis.getAccInfoListByClntId(accinput,
									PredicateUtils.andPredicate(
											CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
											CCISAccountPredicate.FLT_AC_FUTURES));
						
						System.out.println("filter=stock+futures+active+inactive");
						ccis.getAccInfoListByClntId(accinput,
									PredicateUtils.andPredicate(
											CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
											CCISAccountPredicate.FLT_AC_STOCK_OR_FUTURES));
						System.out.println("finished");
						
			        }
			        else if (input.equalsIgnoreCase("A2")) {
			        	System.out.println("Please input client statement id:");
						BufferedReader accin = new BufferedReader(new InputStreamReader(System.in));
			    	    String accinput="q";
			    	    accinput = accin.readLine();
			    	    
			    	    System.out.println("filter=null");				
						ccis.getAllAccByClntId(accinput, null);
			
						System.out.println("filter=stock+active");
							
						ccis.getAllAccByClntId(accinput,
									PredicateUtils.andPredicate(
											CCISAccountPredicate.FLT_AC_ACTIVE,
											CCISAccountPredicate.FLT_AC_STOCK));
						
						System.out.println("filter=futures+active");
						
						ccis.getAllAccByClntId(accinput,
								PredicateUtils.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE,
										CCISAccountPredicate.FLT_AC_FUTURES));
						
						System.out.println("filter=stock+futures+active");
						
						ccis.getAllAccByClntId(accinput, 
								PredicateUtils.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE,
										CCISAccountPredicate.FLT_AC_STOCK_OR_FUTURES));
						
						System.out.println("filter=stock+active_inactive");
						
						ccis.getAllAccByClntId(accinput,
								PredicateUtils.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
										CCISAccountPredicate.FLT_AC_STOCK));
						
						System.out.println("filter=futures+active+inactive");
						
						ccis.getAllAccByClntId(accinput,
								PredicateUtils.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
										CCISAccountPredicate.FLT_AC_FUTURES));
						
						System.out.println("filter=stock+futures+active+inactive");
						
						ccis.getAllAccByClntId(accinput,
								PredicateUtils.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
										CCISAccountPredicate.FLT_AC_STOCK_OR_FUTURES));
						
						System.out.println("finished.");
					
			        }
			        else if (input.equalsIgnoreCase("B")) {
			        	System.out.println("Please input client statement id ");
			    	    BufferedReader invbgin = new BufferedReader(new InputStreamReader(System.in));
			    	    String invbginput="q";
			    	    invbginput = invbgin.readLine();
						ccis.getInvBg(invbginput);
			        }
			        else if (input.equalsIgnoreCase("C")) {
			        	System.out.println("Please input client statement id ");
			    	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			    	    String cinput="q";
			    	    cinput = in.readLine();
						InvBgModel invBgModel = ccis.getInvBg(cinput);
						invBgModel.setExpr(new String[0]);//{"STOCK","WARRANTS","FUTURES","OPTIONS"});//,"GD_SIL","FX","OTHS"});
						invBgModel.setObj(new String[]{"CAP_APPRE","DIV_YIELD","HEDGING","SPECULATN","OTHS"});
						ccis.updateInvBg(invBgModel);

			        }
			        else if (input.equalsIgnoreCase("D")) {
			        	System.out.println("Please input account code **-*******-**");
			    	    BufferedReader acin = new BufferedReader(new InputStreamReader(System.in));
			    	    String acinput="q";
			    	    acinput = acin.readLine();
						ccis.getCustCodeListByPatialAcCode(acinput);
			        }
			        else if (input.equalsIgnoreCase("E")) {
			        	System.out.println("Please input client statement id ");
			    	    BufferedReader efin = new BufferedReader(new InputStreamReader(System.in));
			    	    String efinput="q";
			    	    efinput = efin.readLine();
						ccis.getEstmtEmailFax(efinput);
			        }
			        else if (input.equalsIgnoreCase("F")) {
			        	System.out.println("Please input clientStatementId,email,fax ");
			    	    BufferedReader f1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String f1input="q";
			    	    f1input = f1in.readLine();
			    	    BufferedReader f2in = new BufferedReader(new InputStreamReader(System.in));
			    	    String f2input="q";
			    	    f2input = f2in.readLine();
			    	    if(f2input.equalsIgnoreCase("null")){
			    	    	f2input=null;
			    	    }
			    	    if("".equals(StringUtils.trim(f2input))){
			    	    	f2input="";
			    	    }
			    	    BufferedReader f3in = new BufferedReader(new InputStreamReader(System.in));
			    	    String f3input="q";
			    	    f3input = f3in.readLine();
			    	    if(f3input.equalsIgnoreCase("null")){
			    	    	f3input=null;
			    	    }
			    	    if("".equals(StringUtils.trim(f3input))){
			    	    	f3input="";
			    	    }
			    	    ccis.updateEstmtEmailFax( f1input,f2input,f3input,"TestClnt");
			        }
			        else if (input.equalsIgnoreCase("K")) {
			        	System.out.println("Please input client Birthday yyyy-mm-dd");
			    	    BufferedReader jin = new BufferedReader(new InputStreamReader(System.in));
			    	    String jinput="q";
			    	    jinput = jin.readLine();
						ccis.getCustCodeListByBirthday(Timestamp.valueOf(jinput+" 00:00:00.000000000"));
			        }
			        else if (input.equalsIgnoreCase("L")) {
			        	System.out.println("Please input client idNo, idType ");
			    	    BufferedReader l1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String l1input="q";
			    	    l1input = l1in.readLine();
			    	    BufferedReader l2in = new BufferedReader(new InputStreamReader(System.in));
			    	    String l2input="q";
			    	    l2input = l2in.readLine();
						ccis.getCustCodeListByDocID(l1input,l2input);
			        }
			        else if (input.equalsIgnoreCase("M")) {
			        	System.out.println("Please input client statement id ");
			    	    BufferedReader min = new BufferedReader(new InputStreamReader(System.in));
			    	    String minput="q";
			    	    minput = min.readLine();
						ccis.getEServiceClientInfoModel(minput);
			        }
			        else if (input.equalsIgnoreCase("N")) {
			        	System.out.println("Please input client statement id");
			    	    
			    	    BufferedReader accin = new BufferedReader(new InputStreamReader(System.in));
			    	    String accinput="q";
			    	    accinput = accin.readLine();
			    	    
			    	    System.out.println("filter=null");
						ccis.getAccCodeListByClntId(accinput, null);
						
						System.out.println();
						System.out.println("filter=stock+active");
						ccis.getAccCodeListByClntId(accinput, PredicateUtils
								.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE,
										CCISAccountPredicate.FLT_AC_STOCK));
						
						System.out.println();
						System.out.println("filter=futures+active");
						ccis.getAccCodeListByClntId(accinput, PredicateUtils
								.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE,
										CCISAccountPredicate.FLT_AC_FUTURES));
						
						System.out.println();
						System.out.println("filter=stock+futures+active");
						ccis
								.getAccCodeListByClntId(
										accinput,
										PredicateUtils
												.andPredicate(
														CCISAccountPredicate.FLT_AC_ACTIVE,
														CCISAccountPredicate.FLT_AC_STOCK_OR_FUTURES));
						
						System.out.println();
						System.out.println("filter=stock+active_inactive");
						ccis
								.getAccCodeListByClntId(
										accinput,
										PredicateUtils
												.andPredicate(
														CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
														CCISAccountPredicate.FLT_AC_STOCK));
						
						System.out.println();
						System.out.println("filter=futures+active+inactive");
						ccis.getAccCodeListByClntId(
										accinput,
										PredicateUtils
												.andPredicate(
														CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
														CCISAccountPredicate.FLT_AC_FUTURES));
						
						System.out.println();
						System.out.println("filter=stock+futures+active+inactive)");
						ccis
								.getAccCodeListByClntId(
										accinput,
										PredicateUtils
												.andPredicate(
														CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
														CCISAccountPredicate.FLT_AC_STOCK_OR_FUTURES));
						System.out.println("finished");

			        }
			        else if (input.equalsIgnoreCase("N2")) {
			        	System.out.println("Please input client statement");
			    	    
			    	    BufferedReader accin = new BufferedReader(new InputStreamReader(System.in));
			    	    String accinput="q";
			    	    accinput = accin.readLine();

			    	    System.out.println();
						System.out.println("filter=null");
						ccis
								.getAccCodeListByClntIdForEStatement(accinput,
										null);

						System.out.println();
						System.out.println("filter=stock+active");
						ccis.getAccCodeListByClntIdForEStatement(accinput,
								PredicateUtils.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE,
										CCISAccountPredicate.FLT_AC_STOCK));

						System.out.println();
						System.out.println("filter=futures+active");
						ccis.getAccCodeListByClntIdForEStatement(accinput,
								PredicateUtils.andPredicate(
										CCISAccountPredicate.FLT_AC_ACTIVE,
										CCISAccountPredicate.FLT_AC_FUTURES));

						System.out.println();
						System.out.println("filter=stock+futures+active");
						ccis.getAccCodeListByClntIdForEStatement(
										accinput,
										PredicateUtils
												.andPredicate(
														CCISAccountPredicate.FLT_AC_ACTIVE,
														CCISAccountPredicate.FLT_AC_STOCK_OR_FUTURES));

						System.out.println();
						System.out.println("filter=stock+active+inactive");
						ccis.getAccCodeListByClntIdForEStatement(
										accinput,
										PredicateUtils
												.andPredicate(
														CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
														CCISAccountPredicate.FLT_AC_STOCK));

						System.out.println();
						System.out.println("filter=futures+active+inactive");
						ccis.getAccCodeListByClntIdForEStatement(
										accinput,
										PredicateUtils
												.andPredicate(
														CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
														CCISAccountPredicate.FLT_AC_FUTURES));

						System.out.println();
						System.out.println("filter=stock+futures+active+inactive");
						ccis.getAccCodeListByClntIdForEStatement(
										accinput,
										PredicateUtils
												.andPredicate(
														CCISAccountPredicate.FLT_AC_ACTIVE_OR_IA_IN2M,
														CCISAccountPredicate.FLT_AC_STOCK_OR_FUTURES));

						System.out.println("finished");
			        }
			        
			        else if (input.equalsIgnoreCase("NA")) {
			        	System.out.println("Please input clientStatementID");
			    	    BufferedReader na1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String na1input="q";
			    	    na1input = na1in.readLine();
			    	    System.out.println("Please input optType[EMAIL,FAX,MAIL]");
			    	    BufferedReader na3in = new BufferedReader(new InputStreamReader(System.in));
			    	    String na3input="q";
			    	    na3input = na3in.readLine();
						ccis.getClntNotiOptByClntId(na1input,na3input.toUpperCase());
			        }
			        
			        else if (input.equalsIgnoreCase("NB")) {
			        	System.out.println("Please input client statement id. ");
			    	    BufferedReader na1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String na1input="q";
			    	    na1input = na1in.readLine();
						ccis.getEmailByClntId(na1input);
			        }
			        
			        else if (input.equalsIgnoreCase("NC")) {
			        	System.out.println("Please input client statement id. ");
			    	    BufferedReader na1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String na1input="q";
			    	    na1input = na1in.readLine();
						ccis.getPhoneByClntId(na1input);
			        }
			        
			        else if (input.equalsIgnoreCase("NJ")) {
			        	System.out.println("Please input clientStatementID");
			    	    BufferedReader ni1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni1input="q";
			    	    ni1input = ni1in.readLine();
			    	    
						ccis.getClntAddrByClntId(ni1input);
			        }			        
			        else if (input.equalsIgnoreCase("O")) {
			        	System.out.println("Please input client StatementID");
			    	    BufferedReader ni1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni1input="q";
			    	    ni1input = ni1in.readLine();
			    	    
						// ccis.getEstatementInfo(ni1input);
			        }			        
			        else if (input.equalsIgnoreCase("P")) {
			        	System.out.println("Please input client ClntStmtId,eMail,dailyMail,monthlyMail,dailyEmail,monthlyEmail");
			    	    BufferedReader ni1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni1input="q";
			    	    ni1input = ni1in.readLine();
			    	    
			    	    BufferedReader ni2in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni2input="q";
			    	    ni2input = ni2in.readLine();
			    	    
			    	    BufferedReader ni3in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni3input="q";
			    	    ni3input = ni3in.readLine();
			    	    
			    	    BufferedReader ni4in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni4input="q";
			    	    ni4input = ni4in.readLine();

			    	    BufferedReader ni5in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni5input="q";
			    	    ni5input = ni5in.readLine();

			    	    BufferedReader ni6in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni6input="q";
			    	    ni6input = ni6in.readLine();
			    	    
			    	    
					//	ccis.updateEstatementInfo(ni1input,ni2input,ni3input,ni4input,ni5input,ni6input);
			        }
			        else if (input.equalsIgnoreCase("U")) {
			        	System.out.println("Please input client ClntStmtId,dailyMail,monthlyMail,dailyEmail,monthlyEmail");
			        	
			        	BufferedReader ni1in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni1input="q";
			    	    ni1input = ni1in.readLine();
			    	    
			    	    BufferedReader ni2in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni2input="q";
			    	    ni2input = ni2in.readLine();
			    	    
			    	    BufferedReader ni3in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni3input="q";
			    	    ni3input = ni3in.readLine();
			    	    
			    	    BufferedReader ni4in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni4input="q";
			    	    ni4input = ni4in.readLine();

			    	    BufferedReader ni5in = new BufferedReader(new InputStreamReader(System.in));
			    	    String ni5input="q";
			    	    ni5input = ni5in.readLine();

			    	    
				//		ccis.updateStmtSendOptn(ni1input,ni2input,ni3input,ni4input,ni5input);
			        }	
			        else {
			        	System.out.println("Input error!Please input again:\n");
			        }
			      }
			      catch (AxisFault e) {
			    	  if(e.getCause() instanceof ConnectException){
			    		  instance=null;
			    		  logger.info("retry connect again!");
			    		  getInstance(null,null,null).getEstmtEmailFax("0035355");
			    	  }
				    e.printStackTrace();
			      }
			   }	
		} catch (Exception e) {
	    	logger.error(e);
			logger.info(" Exception type : " + e.getClass().getName());
			e.printStackTrace();
		}

		logger.info(" end client test ... ");
	}

	/**
	 * 
	 * @param clntStmtId
	 * @return
	 * @throws RemoteException
	 * Replace:
	 * MISGatewayUtil.getAccInfoList(final String custCode, Predicate filter):
	 *   get TradingAccount list for the custCode
	 */
	private  ArrayList getAccInfoListByClntId(String clntStmtId,Predicate filter) throws CCISWsException, RemoteException{
		
		return CCISUtil.getInstance().getAccInfoListByClntId(clntStmtId, filter);
	}
	/**
	 * 
	 * @param clntStmtId
	 * @return
	 * @throws RemoteException
	 * Replace:
	 * MISGatewayUtil.getAccInfoList(final String custCode, Predicate filter):
	 *   get TradingAccount list for the custCode
	 */
	private  ArrayList getAllAccByClntId(String clntStmtId,Predicate filter) throws CCISWsException, RemoteException{
		
		return CCISUtil.getInstance().getAllAccByClntId(clntStmtId, filter);
	}
	
	private  ArrayList getAccCodeListByClntIdForEStatement(String clntStmtId,Predicate filter) throws CCISWsException, RemoteException{
		
		return CCISUtil.getInstance().getAccCodeListByClntIdForEStatement(clntStmtId, filter);
	}
	/**
	 * 
	 * @param clntStmtId
	 * @return
	 * @throws RemoteException
	 * Replace:
	 * MISGatewayUtil.getAccList(final String custCode, Predicate filter):
	 *   get acCode list for the custCode
	 * 
	 */
	private  List<String> getAccCodeListByClntId(String clntStmtId,Predicate filter) throws RemoteException {
		
		return CCISUtil.getInstance().getAccCodeListByClntId(clntStmtId, filter) ;
	}
	/**
	 * 
	 * @param clntStmtID
	 * @return
	 * @throws RemoteException
	 * New:n
	 * 
	 */
	private  InvBgModel getInvBg(String clntStmtId) throws RemoteException {		
		
		return CCISUtil.getInstance().getInvBg(clntStmtId);
	}
	/**
	 * 
	 * @param invBg
	 * @throws RemoteException
	 * New:
	 */
	private  void updateInvBg(InvBgModel invBg) throws RemoteException {
		
		CCISUtil.getInstance().updateInvBg(invBg);
	}
	
	/**
	 * 
	 * @param clntStmtID
	 * @return
	 * @throws RemoteException
	 * Replace:
	 * MCSUtil.mcsGetEstmtEmailFax(String loginID)
	 */
	private  HashMap getEstmtEmailFax(String clntStmtID)
			throws RemoteException {

		return CCISUtil.getInstance().getStmtNotfOptn(clntStmtID);
	}
	/**
	 * 
	 * @param obj
	 * @throws RemoteException
	 * Replace:
	 * MCSUtil.mcsUpdateEstmtEmailFax(String loginID, String eMail, String fax)
	 * @throws ESAppException 
	 */
	private  void updateEstmtEmailFax (String clntStmtID,String emailAddr,String faxNo , String clntName) throws RemoteException, ESAppException {
		
		CCISUtil.getInstance().updateStmtNotfOptn(clntStmtID, emailAddr, faxNo , clntName) ;
	}
	/**
	 * 
	 * @param partialAc
	 * @return
	 * @throws RemoteException
	 * Replace:
	 * MISGatewayUtil.getCustListByPatialAcCode(final String patialAcCode)
	 */
	private  ArrayList getCustCodeListByPatialAcCode (String partialAc) throws RemoteException {
		
		return CCISUtil.getInstance().getCustCodeListByPatialAcCode(partialAc);
	}
	

	private ArrayList getCustCodeListByBirthday(Timestamp birthday) throws RemoteException {
    	
    	return CCISUtil.getInstance().getCustCodeListByBirthday(birthday) ;
    }

    private ArrayList getCustCodeListByDocID(String idNo, String idType) throws RemoteException {
    	
    	return CCISUtil.getInstance().getCustCodeListByDocID(idNo, idType);
    }

    private EServiceClientInfoModel getEServiceClientInfoModel(String clntStmtId) throws RemoteException {
    	
    	return CCISUtil.getInstance().getEServiceClientInfoModel(clntStmtId);
    }
    
    private ClntPhoneModel[] getPhoneByClntId(String clntStmtId) throws RemoteException{
    	return CCISUtil.getInstance().getPhoneByClntId(clntStmtId) ;
    }
    
    private ClntEmailModel[] getEmailByClntId(String clientStatementID) throws RemoteException{
    	return CCISUtil.getInstance().getEmailByClntId(clientStatementID);
    }
    
    private ClntNotfOptnModel getClntNotiOptByClntId(String clientStatementID,String optType) throws RemoteException {
    	return CCISUtil.getInstance().getClntNotiOptByClntId(clientStatementID,optType) ;
    }
    
    private ClntAddrModel[] getClntAddrByClntId(String clntStmtId) throws RemoteException {
    	return CCISUtil.getInstance().getClntAddrByClntId(clntStmtId);
    }
    
//    private EstatementOptionInfoModel getEstatementInfo(String clntStmtId)throws RemoteException {
//    	return CCISUtil.getInstance().getEstatementInfo(clntStmtId);
//    }
    
//    private void updateEstatementInfo(String clntStmtId,String eMail,String dailyMail,String monthlyMail,String dailyEmail,String monthlyEmail)
//    throws ESAppException,RemoteException {
//    	EstatementOptionInfoModel estmOptionModel = new EstatementOptionInfoModel();
//    	estmOptionModel.setCustCode(clntStmtId);    	
//    	estmOptionModel.setEmail(eMail);
//    	estmOptionModel.setDailyMail(dailyMail);
//    	estmOptionModel.setDailyEmail(dailyEmail);
//    	estmOptionModel.setMonthlyMail(monthlyMail);
//    	estmOptionModel.setMonthlyEmail(monthlyEmail);
//    	
//    	CCISUtil.getInstance().updateEstatementInfo(estmOptionModel);
//    }
//    private void updateStmtSendOptn(String clntStmtId,String dailyMail,String monthlyMail,String dailyEmail,String monthlyEmail){
//    	EstatementOptionInfoModel estmOptionModel = new EstatementOptionInfoModel();
//    	estmOptionModel.setCustCode(clntStmtId);  
//    	estmOptionModel.setDailyMail(dailyMail);
//    	estmOptionModel.setDailyEmail(dailyEmail);
//    	estmOptionModel.setMonthlyMail(monthlyMail);
//    	estmOptionModel.setMonthlyEmail(monthlyEmail);
//    	
//    	CCISUtil.getInstance().updateStmtSendOptn(estmOptionModel);
//    }
    
}
