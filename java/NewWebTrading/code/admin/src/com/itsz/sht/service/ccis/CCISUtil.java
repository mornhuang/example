package com.itsz.sht.service.ccis;



import java.net.ConnectException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.axis.AxisFault;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;

import com.itsz.sht.exception.ESAppException;
import com.itsz.sht.exception.ESErrorCode;
import com.itsz.sht.exception.ESSysException;
import com.itsz.sht.service.ccis.CCISAccountPredicate;
import com.itsz.sht.common.model.common.request.EstatementOptionInfoModel;
import com.itsz.sht.common.AdminConfigUtil;
import com.itsz.sht.common.StringUtil2;
import com.itsz.sht.exception.ESSystemException;
import com.taifook.framework.foundation.logging.Logger;
import com.taifook.framework.foundation.logging.LoggerFactory;
import com.taifook.mtss.ccis.client.cmn.CCISListOfValue.RiskRef;
import com.taifook.mtss.ccis.client.service.CCISAccService;
import com.taifook.mtss.ccis.client.service.CCISClientService;
import com.taifook.mtss.ccis.client.service.CCISCmmService;
import com.taifook.mtss.ccis.client.service.CCISLogPrflService;
import com.taifook.mtss.ccis.client.webservice.CCISWsAgent;
import com.taifook.mtss.ccis.client.webservice.CCISWsConnection;
import com.taifook.mtss.ccis.client.webservice.CCISWsManager;
import com.taifook.mtss.ccis.webservice.impl.AccHdrModel;
import com.taifook.mtss.ccis.webservice.impl.AccModel;
import com.taifook.mtss.ccis.webservice.impl.AddrModel;
import com.taifook.mtss.ccis.webservice.impl.AllAccModel;
import com.taifook.mtss.ccis.webservice.impl.CCISWsException;
import com.taifook.mtss.ccis.webservice.impl.ClntAddrModel;
import com.taifook.mtss.ccis.webservice.impl.ClntEmailModel;
import com.taifook.mtss.ccis.webservice.impl.ClntNotfOptnModel;
import com.taifook.mtss.ccis.webservice.impl.ClntNotfSendOptnModel;
import com.taifook.mtss.ccis.webservice.impl.ClntPhoneModel;
import com.taifook.mtss.ccis.webservice.impl.ClntSendOptnModel;
import com.taifook.mtss.ccis.webservice.impl.ClntSubptnInfoModel;
import com.taifook.mtss.ccis.webservice.impl.CntryModel;
import com.taifook.mtss.ccis.webservice.impl.CntryNameModel;
import com.taifook.mtss.ccis.webservice.impl.EServiceClientInfoModel;
import com.taifook.mtss.ccis.webservice.impl.EServiceClientInfoModel2;
import com.taifook.mtss.ccis.webservice.impl.EmailModel;
import com.taifook.mtss.ccis.webservice.impl.InvBgModel;
import com.taifook.mtss.ccis.webservice.impl.PhoneModel;
import com.taifook.mtss.core.model.cmn.msg.MsgCore;

public class CCISUtil {
	private static Logger logger = LoggerFactory.instance().getLogger(
			CCISUtil.class);
	private static CCISUtil instance = null;
//	private static String url1=PropertiesUtil.getInstance().read("CCIS_service_1");
//	private static String url2=PropertiesUtil.getInstance().read("CCIS_service_2");
//	private static String user=PropertiesUtil.getInstance().read("CCIS_user");
//	private static String pass=PropertiesUtil.getInstance().read("CCIS_password");
	
	private static String url1=AdminConfigUtil.getInstance().getCcisUrl1();
	private static String url2=AdminConfigUtil.getInstance().getCcisUrl2();
	private static String user=AdminConfigUtil.getInstance().getCcisUser();
	private static String pass=AdminConfigUtil.getInstance().getCcisPass();
	
	private CCISWsManager webMgr = null;
	private CCISWsAgent agent = null;
	private CCISWsConnection conn;
	private CCISClientService clientSrv = null;
	private CCISAccService accSrv = null;
	private CCISLogPrflService logPrflService = null;
	private CCISCmmService cmmService=null;

	public static CCISUtil getInstance() {
		if (instance == null) {
			instance = new CCISUtil();
		}
		return instance;
	}
	private CCISUtil() {

		//webMgr = CCISWsManager.getInstance(url);
		
		List webAddressList = new ArrayList();		
		webAddressList.add(url1);
		webAddressList.add(url2);
		webMgr = CCISWsManager.getInstance(webAddressList);
		
		logger.debug(webMgr.getClass().getName()+" = CCISWsManager.getInstance("+url1+")");
		
		boolean isStartUp=true;
		
		while(isStartUp==true){
			try {
				agent = webMgr.createAgent(user, pass);
				logger.debug(agent.getClass().getName() + "=webMgr.createAgent(" + user + ", " + pass	+ ")");
				
				conn = (CCISWsConnection)agent.getConnection();
				logger.debug(conn.getClass().getName() + " = agent.getConnection()");
				
				clientSrv = conn.getClientService();
				logger.debug(clientSrv.getClass().getName() + " = conn.getClientService()");
				
				accSrv = conn.getAccService();
				logger.debug(accSrv.getClass().getName() + " = conn.getAccService()");
				
				logPrflService = conn.getLogPrflService();
				logger.debug(logPrflService.getClass().getName() + " = conn.getLogPrflService()");

				cmmService = conn.getCmmService();
				logger.debug(cmmService.getClass().getName() + " = conn.getLogPrflService()");

				logger.debug("Initialize ccis api client success ... ");
				break ;
			} catch (Exception e) {
				if (e.getCause() instanceof ConnectException) {
					logger.error("Could not connect to CCIS "+url1+" or "+url2+" !");
					try {
						throw new Exception(
								" Could not connect to CCIS or CCIS don't start up.");
					} catch (Exception ex) {
							try {// sleep then retry..
								logger.info("thread sleep 30 seconds try to connect CCIS again.");
								Thread.sleep(30000); // better sleep a while
							} catch (Exception exe) {
								//
								throw new ESSysException("");
							}	
							//
							throw new ESSysException("");
					}
				}else{
					logger.error(" CCIS Login Failed !");
					logger.error(e);
					throw new ESSysException(" CCIS Login Failed ! ");
				}
			}
		}
	}

	/**
	 * 
	 * @param clntStmtId
	 * @return
	 * @throws RemoteException
	 * @throws ESAppException 
	 * Replace:
	 * MISGatewayUtil.getAccInfoList(final String custCode, Predicate filter):
	 *   get TradingAccount list for the custCode
	 */
	public  ArrayList getAccInfoListByClntId(String clntStmtId,Predicate filter) {
		AccModel[] accModel=null;
		ArrayList  accList=null;
		try {
			accModel = clientSrv.getAccByClntId(clntStmtId);
			logger.info("Start:getAccInfoListByClntId(clntStmtId="+clntStmtId+",filter="+filter+"):accModel.length=" + accModel.length);
			accList = new ArrayList();
			if (accModel != null && accModel.length > 0) {
				for (int i = 0; i < accModel.length; i++) {
					if(accModel[i].getAcStmtId() == null || "".equals(accModel[i].getAcStmtId())){
						continue;
					}else{
						accModel[i].setAcStmtId(convertCustCode(accModel[i].getAcStmtId()));
						logAccModel(accModel[i],i);
						accList.add(accModel[i]);
					}
				}
			}			
			CollectionUtils.filter(accList, filter);			
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getAccInfoListByClntId("+clntStmtId+","+ filter+")");
	    		  accList=getInstance().getAccInfoListByClntId(clntStmtId,filter);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getAccInfoListByClntId("+clntStmtId+","+ filter+")");
	    		  accList=getInstance().getAccInfoListByClntId(clntStmtId,filter);
	    	  }else{
	    		  logger.error("Calling getAccByClntId("+clntStmtId+"),CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_ACCINFO_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getAccByClntId(" + clntStmtId + ") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException();
			es.setErrCode(ESErrorCode.CCIS_GET_ACCINFO_ERROR);
			throw es;
		}
		logger.info("Finish:getAccInfoListByClntId(clntStmtId="+clntStmtId+"):accModelList.size() = "+ accList==null?"null":""+accList.size());
		
		return accList;
	}
	public  ArrayList getAllAccByClntId(String clntStmtId,Predicate filter) {
		AllAccModel[] allAccModel=null;
		ArrayList  accList=null;
		try {
			allAccModel = clientSrv.getAllAccByClntId(clntStmtId);
			logger.info("Start:getAllAccByClntId(clntStmtId="+clntStmtId+",filter="+filter+"):accModel.length=" + allAccModel.length);
			accList = new ArrayList();
			if (allAccModel != null && allAccModel.length > 0) {
				for (int i = 0; i < allAccModel.length; i++) {
					if(allAccModel[i].getAcStmtId() == null || "".equals(allAccModel[i].getAcStmtId())){
						continue;
					}else{
						allAccModel[i].setAcStmtId(convertCustCode(allAccModel[i].getAcStmtId()));
						logAllAccModel(allAccModel[i],i);
						accList.add(allAccModel[i]);
						
					}
				}
			}			
			CollectionUtils.filter(accList, filter);			
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getAccInfoListByClntId("+clntStmtId+","+ filter+")");
	    		  accList=getInstance().getAllAccByClntId(clntStmtId,filter);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getAccInfoListByClntId("+clntStmtId+","+ filter+")");
	    		  accList=getInstance().getAllAccByClntId(clntStmtId,filter);
	    	  }else{
	    		  logger.error("Calling getAccByClntId("+clntStmtId+"),CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_ACCINFO_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getAccByClntId(" + clntStmtId + ") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException();
			es.setErrCode(ESErrorCode.CCIS_GET_ACCINFO_ERROR);
			throw es;
		}
		logger.info("Finish:getAllAccByClntId(clntStmtId = " + clntStmtId + "):accModelList = " + accList==null?"null":""+accList.size());
		
		return accList;
	}
	/**
	 * 
	 * @param clntStmtId
	 * @return
	 * @throws RemoteException
	 * @throws ESAppException 
	 * Replace:
	 * MISGatewayUtil.getAccList(final String custCode, Predicate filter):
	 *   get acCode list for the custCode
	 * 
	 */
	public  ArrayList getAccCodeListByClntId(String clntStmtId,Predicate filter) {
        ArrayList accList = getAccInfoListByClntId(clntStmtId, filter); 
		        
        Transformer TRANS_CLIENTINFO_ACCODE = new Transformer(){
            public Object transform(Object model) {
                return ((AccModel) model).getAcStmtId().trim();
            }
        };
        CollectionUtils.transform(accList, TRANS_CLIENTINFO_ACCODE);
        logger.info("Finish:getAccCodeListByClntId(clntStmtId="+clntStmtId+", filter="+filter+"):accList="+accList==null?"null":accList.toString());
        
        return accList;        
	}	
	public  ArrayList getAccCodeListByClntIdForEStatement(String clntStmtId,Predicate filter) {
        ArrayList accList = getAllAccByClntId(clntStmtId, filter);		        
        Transformer TRANS_CLIENTINFO_ACCODE = new Transformer(){
            public Object transform(Object model) {
                return ((AllAccModel) model).getAcStmtId().trim();
            }
        };
        CollectionUtils.transform(accList, TRANS_CLIENTINFO_ACCODE);
        
        logger.info("Finish:getAccCodeListWithClosedByClntId(clntStmtId="+clntStmtId+", filter="+filter+"): accList="+accList==null?"null":accList.toString());
        return accList;        
	}	
	/**
	 * 
	 * @param clientStatmentID
	 * @return
	 * @throws RemoteException
	 * @throws ESAppException 
	 * New:
	 */
	public  InvBgModel getInvBg(String clntStmtId) {
		InvBgModel invBgModel=null;
		try {
			invBgModel = clientSrv.getInvBgByClntId(clntStmtId);
			logger.info("getInvBg(clientStatmentID="+clntStmtId+"):");
			this.logInvBg(invBgModel);
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getInvBgByClntId("+clntStmtId+")");
	    		  invBgModel = getInstance().getInvBg(clntStmtId);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getInvBgByClntId("+clntStmtId+")");
	    		  invBgModel = getInstance().getInvBg(clntStmtId);
	    	  }else{
	    		  logger.error("Calling getInvBgByClntId("+clntStmtId+"),CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_INV_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getInvBgByClntId(" + clntStmtId + ") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException();
			es.setErrCode(ESErrorCode.CCIS_GET_INV_ERROR);
			throw es;
		}

		return invBgModel;
	}
	/**
	 * 
	 * @param invBg
	 * @throws RemoteException
	 * New:
	 * @throws ESAppException 
	 */
	public  void updateInvBg(InvBgModel invBg) {
		logger.info("updateInvBg(InvBgModel="+invBg+")");
		this.logInvBg(invBg);
		try {
			if (invBg != null) {

				clientSrv.updateInvBg(invBg);
			}
			
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for updateInvBg("+invBg+")");
	    		  getInstance().updateInvBg(invBg);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when updateInvBg("+invBg+")");
	    		  getInstance().updateInvBg(invBg);
	    	  }else{
	    		  logger.error("Calling updateInvBg,CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_UPDATE_INV_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call updateInvBg(InvBgModel invBg) failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException();
			es.setErrCode(ESErrorCode.CCIS_UPDATE_INV_ERROR);
			throw es;
		}
	}
	
	public final static String ESTMT_EMAIL = "ESTMT_EMAIL";
	
    public final static String ESTMT_FAX   = "ESTMT_FAX";
	
    public final static String ESTMT_ADDR1   = "ESTMT_ADDR1";
	
    public final static String ESTMT_ADDR2   = "ESTMT_ADDR2";
	
    public final static String ESTMT_ADDR3   = "ESTMT_ADDR3";
    
    public final static String ESTMT_EMAIL_SEQ = "ESTMT_EMAIL_SEQ";
    
    public final static String ESTMT_FAX_SEQ = "ESTMT_FAX_SEQ";
	/**
	 * 
	 * @param clientStatmentID
	 * @return
	 * @throws RemoteException
	 * Replace:
	 * MCSUtil.mcsGetEstmtEmailFax(String loginID)
	 * 
	 * eServices 5 搴旇濡備綍鑾峰彇ccis鐨別mail鍜宖ax? - Lilian
	 *   濡傛灉鍦╟cis鐨刵otification option 閲岄潰杩樻病鏈夊叧浜巈statement鐨別mail/fax閫夐」鐨勶紝
	 *   鑰屽湪eservice涓嬀閫変簡email/fax閫夐」锛屽垯搴旇浠庡鎴疯祫鏂欎腑鎵惧埌primary email鍜岀涓�釜 fax
	 *   锛堝洜涓篺ax鍙兘鏈夊涓�鍙堜笉涓�畾鏄痯rimary锛屽垯鍙朓D鏈�墠鐨勯偅涓猣ax锛夊仛涓洪粯璁ょ殑鏄剧ず椤广�
	 *   濡傛灉ccis鐨刵otification option閲岄潰宸茬粡鏈塭statement鐨別mail/fax閫夐」鐨勶紝
	 *   鑰屽湪eservice涓嬀閫変簡email/fax閫夐」锛屽垯搴斾粠notification option鐨別mail/fax閫夐」涓壘鍒�
	 *   鐩稿簲鐨別mail/fax鍋氫负榛樿鏄剧ず椤广�
	 * 
	 * 鍏充簬Delivery Mail Address - by Sandy 20070110
	 * ccis涓彲浠ユ湁2鍊嬪堡闈� 濡傛灉瀹汉鐨勬墍鏈夋埗鍙ｉ兘瀵勫埌鍚屼竴鍊媋鍦板潃鐨� cs鍚屼簨鏈冨湪client maintenance > notification option涓殑statement (mail)涓ō瀹氬湴鍧�偤a
	 * 濡傛灉瀹汉鏈変换浣曚竴鍊媋/c鏄瘎鍒癰鍦板潃, 閭ｉ杭, 鏈冨湪client maintenance > notification option涓殑statement (mail)涓ō瀹氬湴鍧�偤a, 
	 * 鑰屽湪a/c maintenance 鐨勯伕鎿囪┎a/c,  鍐嶅埌notification option 涓殑statement (mail), 瑷畾鐐篵鍦板潃 
	 * Q:濡傛灉鍙湁a鍦板潃灏辫偗瀹氬簲璇ヨ繑鍥瀉锛屽鏋滃悓鏃舵湁a鍜宐锛�
	 * A:灏峞services鐨勮┍, eservices鏄痗lient level, 鑰屼笉鏄痑/c level, 鎵�互鏄洖a鐨�
	 * 
	 * Steve:MailAddr鍙槸鏄剧ず缁欏鎴风湅锛屾墍浠ュ綋ClntNotfOptnModel涓病鏈夌殑鏃跺�涓嶉渶瑕佸幓call clientSrv.getAddrByClntId(clientStatmentID);
	 * Sandy:鍦╡services涓璼tatement鐨勫湴鍧� 璜嬮’绀�Client Maintenance > Notification Option > Statemail (Mail) OR (By Hand) 涓殑鍦板潃
	 * Statemail (Mail) 鎴栬� (By Hand) 鍙渻鏈夊叾涓竴鍊�  涓嶆渻涓﹀瓨
	 * Please remember to test on Joint Client ,address is referring to the corresponding client, not on the joint client (virtual client)
	 * 	
	 */
    public  HashMap getStmtNotfOptn(String clntStmtID){
		logger.debug("getEstmtEmailFax(clientStatmentID="+clntStmtID+")");
		HashMap rst = new HashMap();		
		try{
			String email=null;
			String fax=null;
			String address1=null;
			String address2=null;
			String address3=null;			
			
			//
			ClntNotfOptnModel noEmail = clientSrv.getClntStmtNotiOptByClntId(clntStmtID,"EMAIL");
			//logger.info("ClntNotfOptnModel "+emailOption+" = clientSrv.getClntStmtNotiOptByClntId("+clntStmtID+",\"EMAIL\")");
			ClntNotfOptnModel noFax = clientSrv.getClntStmtNotiOptByClntId(clntStmtID,"FAX");
			//logger.info("ClntNotfOptnModel "+faxOption+" = clientSrv.getClntStmtNotiOptByClntId("+clntStmtID+",\"FAX\")");
			ClntNotfOptnModel noMail = clientSrv.getClntStmtNotiOptByClntId(clntStmtID,"MAIL");
			//logger.info("ClntNotfOptnModel "+mailOption+" = clientSrv.getClntStmtNotiOptByClntId("+clntStmtID+",\"MAIL\")");
			ClntNotfOptnModel noHand = clientSrv.getClntStmtNotiOptByClntId(clntStmtID,"BY_HAND");
			
			if(logger.isDebugEnabled()){
				this.logClntNotfOptn(noEmail,0);
			}

			if(noEmail!=null){
				ClntEmailModel clntEmailModel = noEmail.getEmail();
				this.logClntEmail(clntEmailModel,0);
				if (clntEmailModel != null) {						
					EmailModel emailModel = clntEmailModel.getEmail();					
					if (emailModel != null) {						
						email = emailModel.getEmailAddr();						
					}
				}
			}else{
				ClntEmailModel[] clntEmailModel = clientSrv.getEmailByClntId(clntStmtID);
				logger.debug("getEmailByClntId("+clntStmtID+")");
				if(clntEmailModel!=null){
					logger.debug("clntEmailModel.length=" + clntEmailModel.length);
					for (int i = 0; i < clntEmailModel.length; i++) {
						this.logClntEmail(clntEmailModel[i],i);							
						if(true == clntEmailModel[i].isPrimy()){
							if(clntEmailModel[i].getEmail()!=null){								
								EmailModel emailModel = clntEmailModel[i].getEmail();								
								if(emailModel!=null){								
									email = emailModel.getEmailAddr();																		
								}
							}
							break ;
						}
					}
				}
			}
			
			logger.debug("getClntStmtNotiOptByClntId("+clntStmtID+",\"EMAIL\").getEmail() = "+email);

			if(logger.isDebugEnabled()){
				this.logClntNotfOptn(noFax,0);
			}

			if(noFax!=null){
				ClntPhoneModel clntPhoneModel = noFax.getPhone();				
				if(clntPhoneModel!=null){
					this.logClntPhone(clntPhoneModel,0);
					PhoneModel phoneModel = clntPhoneModel.getPhone();					
					if(phoneModel!=null){						
						if(true == phoneModel.isFax()){
							fax = phoneModel.getPhneNum();							
						}
					}						
				}
			}else{
				ClntPhoneModel[] clntPhoneModel = clientSrv.getPhoneByClntId(clntStmtID);
				logger.debug("clntPhoneModel[] " + clntPhoneModel +" = clientSrv.getPhoneByClntId("+clntStmtID+")");
				if(clntPhoneModel!=null){
					/* need return the first fax,in another word, the sequence number is the minimum			
					 * and country code must be 852
					 */					
					long tmpSeq = -1;
					int tmpCnt = -1 ;
					for (int i = 0 ; i < clntPhoneModel.length; i++) {
						this.logClntPhone(clntPhoneModel[i],i);
						if (clntPhoneModel[i].getPhone() != null) {
							if (clntPhoneModel[i].getPhone().isFax() && "852".equals(clntPhoneModel[i].getPhone().getCntryCde())) {
								if (tmpSeq == -1 || tmpSeq > clntPhoneModel[i].getSeqNum()) {
									tmpSeq = clntPhoneModel[i].getSeqNum();
									tmpCnt = i;
								}
							}
						}
					}
					if (tmpSeq !=-1) {
						fax = clntPhoneModel[tmpCnt].getPhone().getPhneNum();
						logger.debug("The First Fax is ");
						this.logClntPhone(clntPhoneModel[tmpCnt],tmpCnt);
					}					
				}
			}
			
//			if(logger.isDebugEnabled())this.logClntNotfOptn(noMail,0);
//			if(logger.isDebugEnabled())this.logClntNotfOptn(noHand,0);
			
			if(noMail!=null && noMail.getAddress()!=null){
				ClntAddrModel clntAddrModel = noMail.getAddress();
				this.logClntAddr(clntAddrModel,0 );
				if(clntAddrModel!=null){
					
					AddrModel addrModel= clntAddrModel.getAddr();					
					if(addrModel!=null){
						address1 = addrModel.getAddrLine1()!=null?addrModel.getAddrLine1():"";						
						address2 = addrModel.getAddrLine2()!=null?addrModel.getAddrLine2():"";						
						address3 = addrModel.getAddrLine3()!=null?addrModel.getAddrLine3():"";						
					}
				}
			}else if (noHand!=null && noHand.getAddress()!=null) {
				ClntAddrModel clntAddrModel = noHand.getAddress();
				this.logClntAddr(clntAddrModel,0 );
				if(clntAddrModel!=null){					
					AddrModel addrModel= clntAddrModel.getAddr();					
					if(addrModel!=null){
						address1 = addrModel.getAddrLine1()!=null?addrModel.getAddrLine1():"";						
						address2 = addrModel.getAddrLine2()!=null?addrModel.getAddrLine2():"";						
						address3 = addrModel.getAddrLine3()!=null?addrModel.getAddrLine3():"";						
					}
				}				
			}else{
				logger.warn("Can not found ClntNotfOptnModel by MAIL and BY_HAND");
			}
			
			logger.info("getClntStmtNotiOptByClntId(USER_ID = "+clntStmtID+",EMAIL = "+email+",FAX = "+fax
					+",ESTMT_ADDR1 = "+address1+"ESTMT_ADDR2 = "+address2+"ESTMT_ADDR3 = "+address3);	
			
		    rst.put(ESTMT_EMAIL, email);
		    rst.put(ESTMT_FAX, fax);
		    rst.put(ESTMT_ADDR1,address1);
		    rst.put(ESTMT_ADDR2,address2);
		    rst.put(ESTMT_ADDR3,address3);
		    
		    logger.debug("map:rst.get(ESTMT_EMAIL)="+email+",rst.get(ESTMT_FAX)="+fax
		    		+"rst.get(ESTMT_ADDR1)="+address1+"rst.get(ESTMT_ADDR2)="+address2+"rst.get(ESTMT_ADDR3)="+address3);
		    
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getClntStmtNotiOptByClntId");
	    		  rst = getInstance().getStmtNotfOptn(clntStmtID);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getClntStmtNotiOptByClntId");
	    		  rst = getInstance().getStmtNotfOptn(clntStmtID);
	    	  }else{
	    		  logger.error("Calling getClntStmtNotiOptByClntId,CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_EMAIL_FAX_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getClntStmtNotiOptByClntId failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException();
			es.setErrCode(ESErrorCode.CCIS_GET_EMAIL_FAX_ERROR);
			throw es;
		}
		return rst;
	}
	/**
	 * 
	 * @param obj
	 * @throws ESAppException 
	 * @throws RemoteException
	 * Replace:
	 * MCSUtil.mcsUpdateEstmtEmailFax(String loginID, String eMail, String fax)
	 * - Described by Lucia (Nov 15,2006)
	 *  About eServices - CCIS API call, would like to have a minor change 
	 *  on Client Statement Send Option
	 *  org method, for insert/update client notification option, eServices need to pass 
	 *  in the Notification Item Code (i.e. STATEMENT)
	 *  but now, would like to change the API name (e.g. insertClntStmtNotfOptn), so that eServices do 
	 *  not need to hard code the Notification Item Code
	 *  Notification Item Code 锛歋TATEMENT and GENERAL, PROMOTION ,etc. 
	 *  
	 * - Described by Lucia (Nov 15,2006)
	 *  something would like to remind on using the CCIS API for Client Statement Send Option
	 *  when eServices launch the update client statement send option 
	 *  will go thro the following API calls
	 *  1. getEmailByClntId, and getPhoneByClntId  - to get the full list of email, fax of that client
	 *  2. if want to subscript eStatement by email
	 *     2.1. check the client's email address is in the result list of getEmailByClntId
	 *          if record exists, u will have the seq number
	 *     2.2. then, will call insertClntStmtNotfOptn, set the ClntNotfOptnModel.clntEmailSeq = (the value obtained from 2.1)      
	 *     for 2.1. if the email address not exists in the list, then, eServices will also call insertClntStmtNotfOptn, 
	 *     and ClntNotfOptnModel.clntEmailSeq = null, and ClntNotfOptnModel.clientEmailModel.emailAddr = the new email address
	 *     just remind, if want to subscript eStatement by email, and want to change the email as well, 
	 *     u need to call updateClntStmtNotfOptn, AND updateClntEmail
	 *  Case 2: if orginially subscripted by email, but want to change the email address e.g. from old@tf.com to new@tf.com
	 *     1. getEmailByClntId, and getPhoneByClntId  - to get the full list of email, fax of that client
	 *     2. check the client's email address (new@tf.com) is in the result list of getEmailByClntId
	 *     if record exists, u will have the seq number
	 *     if record not exists, eServices will also call updateClntStmtNotfOptn, and ClntNotfOptnModel.clntEmailSeq = null, 
	 *     and ClntNotfOptnModel.clientEmailModel.emailAddr = the new email address
	 *     
	 * - Described by Lilian (Dec 2006)
	 * 鍦╡Services5鏇存柊浜唀mail鍜宖ax灏嗗浣曞鐞嗭紵
	 *  1. 濡傛灉娌℃湁鍋氫换浣曚慨鏀硅�鎻愪氦锛屽垯 ccis 鐨刢lient maintenance/notification option
	 *     閮戒笉搴旇鍙戠敓浠讳綍鍙樺姩锛屽綋鐒秏is涔熶笉搴旇鍙戠敓浠讳綍鍙樺姩
	 *  2. 濡傛灉娌℃湁鏀瑰彉option鑰屽彧鏄洿鏀逛簡email鎴杅ax鐨剉alue,鍒�
	 *     1锛�濡傛灉鎵�洿鏀圭殑email鎴杅ax涓篶lient mainenance閲岄潰鎵�病鏈夌殑鏂扮殑value,鍒檆cis浼氬鍔犱竴涓猠mail鎴杅ax璁板綍锛�
	 *     浣唒rimary鐨刦lag涓嶄細鍥犱负鏂板鐨勮褰曡�鍙戠敓鍙樺姩锛屽嵆浠ュ墠鍝釜鏄痯rimary灏辩淮鎸佽繕鏄摢涓�
	 *     鑰宯otification option 涓殑email鎴杅ax option鎵�搴旂殑mail鎴杅ax鑷姩鍙樹负鏇存柊鍚庣殑value.
	 *     MIS涓殑client鎵�湁account鐨別mail鎴杅ax涔熷皢鏇存柊涓烘渶鏂扮殑value.
	 *     2锛�濡傛灉鎵�洿鏀圭殑email鎴杅ax涓篶lient mainenance閲岄潰宸叉湁鐨剉alue,鍒檆cis涓嶄細澧炲姞涓�釜email鎴杅ax璁板綍锛�
	 *     鍗砪lient maintenance涓殑phone 鎴杄mail璁板綍涓嶅彉锛宲rimary鐨刦lag涔熶笉鍙樸�
	 *     鑰宯otification option涓殑email鎴杅ax option鎵�搴旂殑mail鎴杅ax鑷姩鍙樹负鏇存柊鍚庣殑value. 
	 *     MIS涓殑client鎵�湁account鐨別mail鎴杅ax涔熷皢鏇存柊涓烘渶鏂扮殑value.
	 *  3. 濡傛灉鏀瑰彉option(渚嬪鏈韩娌℃湁鐜板湪鏀规垚鏈夛紝鎴栬�鏈韩鏈夌幇鍦ㄦ敼鎴愭病鏈�
	 *     1锛�濡傛灉鏈韩娌℃湁鐜板湪鏀规垚鏈夛紝鍒欓鍏堣鐪嬭緭鍏ョ殑email鎴杅ax鍊煎湪client maintenance鐨別mail鎴杅ax璁板綍涓槸鍚︽湁璇ュ�锛�
	 *     娌℃湁灏辨柊澧烇紝鏈夊氨淇濇寔锛宲rimary flag涓嶅彉锛岀劧鍚嶤CIS notification option鏂板鐩稿簲鐨刼ption锛宔mail鎴杅ax涓哄鎴疯緭鍏ョ殑閭ｄ釜锛�
	 *     MIS涓殑client鐨勬墍鏈塧ccount灏嗘柊澧炰竴鏉ail鎴栬�fax鐨刵otification option,鑰屾瘡涓猘ccount鐨別mail鎴杅ax涔熷皢鏇存柊涓烘渶鏂扮殑value.
	 *     2锛�濡傛灉鏈韩鏈夋敼鎴愭病鏈夛紝鍒機CIS client 璧勬枡涓嶅彉锛宯otification option涓浉搴旇祫鏂欒鍒犻櫎锛�
	 *     MIS鎵�湁account鐩稿簲鐨刼ption琚垹闄わ紝email鎴杅ax璧勬枡涓嶅彉銆�
	 *     
	 *  Clarified by Sandy on 20070118
	 *  They confirmed that if the email address / fax number on eStatement / Order Notification is changed through eServices, 
	 *  the corresponding record in CCIS should be changed accordingly instead of adding a new one.
	 *  
	 *  鍒涘缓NO-Email NO-Fax鐨勬椂鍊欙紝鐢盋CIS API鑷璁惧畾PrefLang="OTHS"锛宔Services涓嶉渶瑕佽瀹�
	 *  --confirmed by Steve on 20070125
	 *  
	 * refer Tony's email subject: 48969 client estatement setting problem
	 * 
	 * As per our discussion, please find the updated CCIS client library for virtual joint client e-statement problem as attached.
	 * Two fields added in model ClntNotfOptnModel. 
	 * notfClntId: notification client id of client鈥檚 notification option 
	 * langTyp:   language type of client鈥檚 notification option 
	 * Please set the field value of notification client id and language type in the model ClntNotfOptnModel when calling the web services API -- updateClntStmtNotiOpt(), insertClntStmtNotiOpt(). 
	 * These field values can be retrieved from the web service API getClntStmtNotiOptByClntId(). 
	 * p.s. Notification client id will be set as client id if not specified. 
	 * For insertClntStmtNotiOpt(), the language type of notification option is defaulted from system parameter in CCIS if not specified.
	 * 
	 * the value of notification client id (notfClntId) of client 0048969 should be the same as the client id of model ClntEmailModel, i.e. 0082337. This field value can be retrieved from the web service API getClntStmtNotiOptByClntId().
	 * 
	 * For virtual joint client鈥檚 notification option, the client to be notified can only be the corresponding individual clients but not the virtual joint client itself.
	 * In your case, individual client 0039130 was set as the client to be notified and email hqhua@itsz.cn for client 0039130 was created.
	 * In fact, notification option for virtual joint client 0915116 was created successfully. 
	 * 
	 *  
	 *  Declared by Karen in 2008-9-30:
	 *  the logics should be as follows when the email address of joint/virtual client is updated through eServices       
	 * 1. If the email address of eStatement NO of this virtual client has been referred to one of the 
	 *    designated email address (no matter it is primary or not) of its non-virtual client, 
	 *    then this referred email address of its non-virtual client will be replaced by the updated one directly.
	 * 2. If the email address of eStatement NO of this virtual client has been referred to itself one of the 
	 *    designated email address (no matter it is primary or not), then this designated email address of 
	 *    the virtual client will be replaced by the updated one directly.    
	 *  
	 *  
	 */
    public  void updateStmtNotfOptn (String clntStmtId,String emailAddr,String faxNo , String clntName ) throws ESAppException{   
		logger.info("updateEstmtEmailFax ("+clntStmtId+","+emailAddr+")");
		try{
			
			ClntEmailModel clntEmailArr[] = clientSrv.getEmailByClntId(clntStmtId);
			long newEmailSeq = -1;
			int newEmailCnt = -1;
			if (emailAddr!=null && !"".equals(emailAddr)) {
				for (int i = 0 ; i < clntEmailArr.length ; i++) {
					if (emailAddr.equalsIgnoreCase( clntEmailArr[i].getEmail().getEmailAddr())) {
						//杈撳叆鐨凟Mail鍦–lntEMail
						newEmailSeq = clntEmailArr[i].getSeqNum();
						this.logClntEmail(clntEmailArr[i],i);
						newEmailCnt = i;
						break;
					}
				}
				ClntNotfOptnModel clntNotfOptn = clientSrv.getClntStmtNotiOptByClntId(clntStmtId,"EMAIL");
				
				logger.debug("ClntNotfOptnModel "+clntNotfOptn+" = clientSrv.getClntStmtNotiOptByClntId("+clntStmtId+",\"EMAIL\")");				
				
				if (clntNotfOptn!=null) {
					//瀹㈡埛宸茬粡鏈塏O-Email
					ClntEmailModel clntEmailModel = clntNotfOptn.getEmail();
					if (clntEmailModel!=null) {
						long emailSeq = clntNotfOptn.getClntEmailSeq();
						if (newEmailSeq!=-1 && emailSeq==newEmailSeq) {
							//杈撳叆鐨別amiladdr杩樻槸鍘熸潵鐨勶紝need not do any change
							clntNotfOptn = null;
						}else if (newEmailSeq!=-1 && emailSeq!=newEmailSeq){
							//杈撳叆鐨別amiladdr鍦–lntEmailModel[]涓兘鎵惧埌
							clntNotfOptn.setClntEmailSeq(newEmailSeq);
							clntNotfOptn.setEmail(clntEmailArr[newEmailCnt]);
						} else {
							//杈撳叆鐨別mail鍦板潃鍦–lntEmailModel[]涓笉鑳芥壘鍒帮紝淇敼NO锛峞mail鎸囧悜鐨別mail
							EmailModel emailModel = clntNotfOptn.getEmail().getEmail();
							emailModel.setEmailAddr(emailAddr);							
							
						}

					} else {
						//杩欎釜閫昏緫搴旇涓嶄細鍒拌揪
						EmailModel newEM = new EmailModel();
						newEM.setEmailAddr(emailAddr);						
						ClntEmailModel newCEM = new ClntEmailModel();
						newCEM.setClntId(clntStmtId);
						newCEM.setEmail(newEM);						
						clntNotfOptn.setEmail(newCEM);
						
					} 
					if (clntNotfOptn != null) {
						if(logger.isDebugEnabled())this.logClntNotfOptn(clntNotfOptn,0);
						
						clntNotfOptn.setNotfMtdTyp("EMAIL");

						clientSrv.updateClntStmtNotiOpt(clntNotfOptn);
					}
				}else {
					//瀹㈡埛杩樻病鏈塏O-email
					
					ClntNotfOptnModel noMail = clientSrv.getClntStmtNotiOptByClntId(clntStmtId,"MAIL");
					
					clntNotfOptn = new ClntNotfOptnModel();
					
					if (newEmailSeq!=-1){
						clntNotfOptn.setClntEmailSeq(newEmailSeq);
						clntNotfOptn.setEmail(clntEmailArr[newEmailCnt]);	
						
					}else {
						EmailModel newEM = new EmailModel();
						newEM.setEmailAddr(emailAddr);
						ClntEmailModel newCEM = new ClntEmailModel();
						newCEM.setClntId(clntStmtId);
						newCEM.setEmail(newEM);
						clntNotfOptn.setEmail(newCEM);					
					}
					
					clntNotfOptn.setNotfMtdTyp("EMAIL");
					
					clntNotfOptn.setClntId(clntStmtId);
					if(noMail!=null){
						clntNotfOptn.setNotfClntId(noMail.getNotfClntId());
					}else{
						logger.error("System error: can not found NotfOptn Object from CCIS");
					}

					if(logger.isDebugEnabled())this.logClntNotfOptn(clntNotfOptn,0);
					
					clientSrv.insertClntStmtNotiOpt(clntNotfOptn);
										
				}
			}
				
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for updateEstmtEmailFax");
	    		  getInstance().updateStmtNotfOptn(clntStmtId,emailAddr,faxNo,clntName);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when updateEstmtEmailFax");
	    		  getInstance().updateStmtNotfOptn (clntStmtId,emailAddr,faxNo,clntName);
	    	  } else if ("CCIS-API-000067".equals(e.getFaultCode().getLocalPart())){
	    		  logger.error("Calling updateEstmtEmailFax('"+clntStmtId+"','"+emailAddr+"'),CCIS system have an thrown CCIS-API-000067 = EMAIL_DUPLICATE for duplicated email exception!");
	    		  logger.error(e);
	    		  ESAppException es = new ESAppException();
	    	//	  es.setErrCode(ESErrorCode.CCIS_DUPLICATED_EMAIL_ERROR);
	    		  throw es;
	    	  }else{
	    		  logger.error("Calling updateEstmtEmailFax,CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_UPDATE_EMAIL_FAX_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call updateEstmtEmailFax failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException();
			es.setErrCode(ESErrorCode.CCIS_UPDATE_EMAIL_FAX_ERROR);
			throw es;
		}
	}
    
    public EstatementOptionInfoModel getEstatementInfo(String userID){
    	
    	logger.info("Start: getEstatementInfo("+userID+")");
   	EstatementOptionInfoModel model = new EstatementOptionInfoModel();
		
        String address1 = null;
        String address2 = null;
        String address3 = null;
        String email = null;
        String subscriptionStat = null;
        String dailyMail = "N";
        String monthlyMail = "N";
        String dailyEmail = "N";
        String monthlyEmail = "N";
        boolean withHoldFlag = false;
        
        
        ClntEmailModel clntEmailModel = null;
        ClntAddrModel  clntAddrModel  = null;

		try {
			ClntNotfSendOptnModel[] clntNotfSendOptinModel = clientSrv.getClntStmtNotiSendOptnByClntId(userID);
			
			if(clntNotfSendOptinModel == null){
				  logger.error("Calling getClntStmtNotiSendOptnByClntId() failed,CCIS return null");
				  ESSysException es = new ESSysException();
			//	  es.setErrCode(ESErrorCode.CCIS_GET_NOTI_SEND_OPTN_ERROR);
				  throw es;
			}
			
			//get emailAddress and address
			for (int i = 0 ; i < clntNotfSendOptinModel.length; i++) {
				if("EMAIL".equals(clntNotfSendOptinModel[i].getNotfMtdTyp())){
//					get emailAddress
					clntEmailModel = clntNotfSendOptinModel[i].getEmail();
					
					if(clntEmailModel != null){
						EmailModel emailModel = clntEmailModel.getEmail();
						if(emailModel != null){
							email = emailModel.getEmailAddr();
						}else{
							logger.error("System error: CCIS return EmailModel is null");
						}
					}else{
						logger.error("System error: When notfMtdTyp is EMAIL Then CCIS return ClntEmailModel is null");
					}
					//
				}else if("MAIL".equals(clntNotfSendOptinModel[i].getNotfMtdTyp())){
//					get address
					clntAddrModel  = clntNotfSendOptinModel[i].getAddress();
					
					if(clntAddrModel != null){
						AddrModel addrModel = clntAddrModel.getAddr();
						if(addrModel != null){
							address1 = addrModel.getAddrLine1()!=null?addrModel.getAddrLine1():"";
							address2 = addrModel.getAddrLine2()!=null?addrModel.getAddrLine2():"";						
							address3 = addrModel.getAddrLine3()!=null?addrModel.getAddrLine3():"";	
						}else{
							logger.error("System error: CCIS return AddrModel is null");
						}
					}else{
						logger.error("System error: When notfMtdTyp is MAIL Then CCIS return ClntAddrModel is null");
					}
				}else if("NO_DELIVER".equalsIgnoreCase(clntNotfSendOptinModel[i].getNotfMtdTyp())){
					withHoldFlag = true;
				}
				
//				get SendOption 
				ClntSendOptnModel[] clntSendOptnList = clntNotfSendOptinModel[i].getClntSendOptnModelList();
				
				if(clntSendOptnList!=null){
					
					for (int x = 0 ; x < clntSendOptnList.length; x++) {
						if("DLY".equals(clntSendOptnList[x].getSendOptnFor())&&"MAIL".equals(clntSendOptnList[x].getSendMtdTyp())){
							dailyMail = "Y";							
						}
						if("DLY".equals(clntSendOptnList[x].getSendOptnFor())&&"EMAIL".equals(clntSendOptnList[x].getSendMtdTyp())){
							dailyEmail = "Y";							
						}
						if("MLY".equals(clntSendOptnList[x].getSendOptnFor())&&"MAIL".equals(clntSendOptnList[x].getSendMtdTyp())){
							monthlyMail = "Y";							
						}
						if("MLY".equals(clntSendOptnList[x].getSendOptnFor())&&"EMAIL".equals(clntSendOptnList[x].getSendMtdTyp())){
							monthlyEmail = "Y";							
						}
					}
					
				}

//				get Subscription State
				if(subscriptionStat==null || "".equals(subscriptionStat)){
					ClntSubptnInfoModel clntSubInfoModel = clntNotfSendOptinModel[i].getClntSubptnInfoModel();	
					
					if(clntSubInfoModel!=null){
						subscriptionStat = clntSubInfoModel.getSubptnStat();
					}
				}
				
			}
			
			if(email==null){
				ClntEmailModel[] clntEmailModelList = clientSrv.getEmailByClntId(userID);

				if(clntEmailModelList!=null){
					for (int i = 0; i < clntEmailModelList.length; i++) {
						
						this.logClntEmail(clntEmailModelList[i],i);		
						
						if(true == clntEmailModelList[i].isPrimy()){
							if(clntEmailModelList[i].getEmail()!=null){
								EmailModel emailModel = clntEmailModelList[i].getEmail();								
								if(emailModel!=null){
									email = emailModel.getEmailAddr();
								}
							}
							break ;
						}
					}
				}
			}
			
			
			model.setAddress1(address1);
			model.setAddress2(address2);
			model.setAddress3(address3);
			model.setCustCode(userID);
			model.setEmail(email);
			model.setSubscriptionStat(subscriptionStat);
			model.setDailyMail(dailyMail);
			model.setDailyEmail(dailyEmail);
			model.setMonthlyMail(monthlyMail);
			model.setMonthlyEmail(monthlyEmail);
			model.setWithHoldFlag(withHoldFlag);
			
			logger.info("End: getEstatementInfo(USER_ID = "+userID+",EMAIL = "+email
					+",ESTMT_ADDR1 = "+address1+",ESTMT_ADDR2 = "+address2+",ESTMT_ADDR3 = "+address3
					+",SUBSCRIPTIONSTAT="+subscriptionStat+",DAILYMAIL="+dailyMail+",DAILYEMAIL="+dailyEmail
					+",MONTHLYMAIL="+monthlyMail+",MONTHLYEMAIL="+monthlyEmail+",WITHHOLDFLAG="+withHoldFlag);	
			
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getEstatementInfo");
	    		  getInstance().getEstatementInfo(userID);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getEstatementInfo");
	    		  getInstance().getEstatementInfo(userID);
	    	  }else{
	    		  logger.error("Calling getClntStmtNotiSendOptnByClntId,CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_EMAIL_FAX_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getEstatementInfo failed , cause by RemoteException!",e);
			ESSysException es = new ESSysException();
			es.setErrCode(ESErrorCode.CCIS_GET_EMAIL_FAX_ERROR);
			throw es;
		}
		
    	return model;
    }
    
    public void updateStmtSendOptn(EstatementOptionInfoModel estmOptionModel){
    	if(estmOptionModel!=null){
        	
        	logger.info("updateStmtSendOptn : Start...");

        	ClntSendOptnModel[] csoModelArray = null;
        	ClntSendOptnModel csoModel = null;
        	List csoModelList = new ArrayList();  
        	int dailyMailSeq = -1;
        	int dailyEmailSeq = -1;
        	int monthlyMailSeq = -1;
        	int monthlyEmailSeq = -1;
        	
        	try {
           		
        		csoModelArray = clientSrv.getClntSendOptnList(estmOptionModel.getCustCode(), "STMT");
        		
    			for (int x = 0 ; x < csoModelArray.length; x++) {				
    				if("DLY".equals(csoModelArray[x].getSendOptnFor())&&"MAIL".equals(csoModelArray[x].getSendMtdTyp())){
    					dailyMailSeq = x;
    				}
    				if("DLY".equals(csoModelArray[x].getSendOptnFor())&&"EMAIL".equals(csoModelArray[x].getSendMtdTyp())){
    					dailyEmailSeq = x;
    				}
    				if("MLY".equals(csoModelArray[x].getSendOptnFor())&&"MAIL".equals(csoModelArray[x].getSendMtdTyp())){
    					monthlyMailSeq = x;
    				}
    				if("MLY".equals(csoModelArray[x].getSendOptnFor())&&"EMAIL".equals(csoModelArray[x].getSendMtdTyp())){
    					monthlyEmailSeq = x;
    				}
    			}
    			
            	if("Y".equals(estmOptionModel.getDailyMail())){
            		if(dailyMailSeq>-1){
            			csoModel = csoModelArray[dailyMailSeq];
            		}else{
                		csoModel = new ClntSendOptnModel();
                		
                		csoModel.setClntId(estmOptionModel.getCustCode());
                		csoModel.setNotfitemcode("STMT");
                		csoModel.setSendOptnFor("DLY");
                		csoModel.setSendMtdTyp("MAIL");
            		}
            		csoModelList.add(csoModel);
            	}
            	
            	if("Y".equals(estmOptionModel.getDailyEmail())){
            		if(dailyEmailSeq>-1){
            			csoModel = csoModelArray[dailyEmailSeq];
            		}else{
                		csoModel = new ClntSendOptnModel();
                		
                		csoModel.setClntId(estmOptionModel.getCustCode());
                		csoModel.setNotfitemcode("STMT");
                		csoModel.setSendOptnFor("DLY");
                		csoModel.setSendMtdTyp("EMAIL");
            		}
            		csoModelList.add(csoModel);
            	}
            	
            	if("Y".equals(estmOptionModel.getMonthlyMail())){
            		if(monthlyMailSeq>-1){
            			csoModel = csoModelArray[monthlyMailSeq];
            		}else{
                		csoModel = new ClntSendOptnModel();
                		
                		csoModel.setClntId(estmOptionModel.getCustCode());
                		csoModel.setNotfitemcode("STMT");
                		csoModel.setSendOptnFor("MLY");
                		csoModel.setSendMtdTyp("MAIL");
            		}
            		csoModelList.add(csoModel);
            	}
            	
            	if("Y".equals(estmOptionModel.getMonthlyEmail())){
            		if(monthlyEmailSeq>-1){
            			csoModel = csoModelArray[monthlyEmailSeq];
            		}else{
                		csoModel = new ClntSendOptnModel();
                		
                		csoModel.setClntId(estmOptionModel.getCustCode());
                		csoModel.setNotfitemcode("STMT");
                		csoModel.setSendOptnFor("MLY");
                		csoModel.setSendMtdTyp("EMAIL");
            		}
            		csoModelList.add(csoModel);
            	}
        		
    			clientSrv.updateClntSendOptnList((ClntSendOptnModel[]) csoModelList.toArray(new ClntSendOptnModel[]{}));
    			
    	    	logger.info("updateStmtSendOptn : End...");
    		} catch (AxisFault e) {
    	    	  if(e.getCause() instanceof ConnectException){
    	    		  instance=null;
    	    		  logger.error("Could not connect to CCIS for getEstatementInfo");
    	    		  getInstance().updateStmtSendOptn(estmOptionModel);
    	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
    	    		  instance=null;
    	    		  logger.error("Connect to CCIS Session time out when getEstatementInfo");
    	    		  getInstance().updateStmtSendOptn(estmOptionModel);
    	    	  }else{
    	    		  logger.error("Calling updateClntSendOptnList,CCIS system have an unknown exception!");
    	    		  logger.error(e);
    	    		  ESSysException es = new ESSysException();
    	    	//	  es.setErrCode(ESErrorCode.CCIS_UPDATE_SEND_OPTN_ERROR);
    	    		  throw es;
    	    	  }
    		} catch (RemoteException e) {
    			logger.error("Call updateStmtSendOptn failed , cause by RemoteException!",e);
    			ESSysException es = new ESSysException();
    	//		es.setErrCode(ESErrorCode.CCIS_UPDATE_SEND_OPTN_ERROR);
    			throw es;
    		}
        
    	}else{
			logger.error("The param:estmOptionModel is null");
    	}
    }
    

    public void updateEstatementInfo(EstatementOptionInfoModel estmOptionModel) throws ESAppException{
    	logger.info("updateEstatementInfo Start...");

    	if(estmOptionModel!=null){
    		
    		updateStmtNotfOptn(estmOptionModel.getCustCode(), estmOptionModel.getEmail(), null, estmOptionModel.getCustCode());
    		
    		updateStmtSendOptn(estmOptionModel);
 
    	}
    	
    	logger.info("updateEstatementInfo End...");
    }
    
    
    private void updateEstatementInfoWithClntNotfSendOptnModel(EstatementOptionInfoModel estmOptionModel){

    	if(estmOptionModel!=null){
    		
    	   	List csoModelList = new ArrayList();   	
        	ClntSendOptnModel csoModel;
        	
        	if("Y".equals(estmOptionModel.getDailyMail())){
        		csoModel = new ClntSendOptnModel();
        		csoModel.setClntId(estmOptionModel.getCustCode());
        		csoModel.setSendOptnFor("DLY");
        		csoModel.setSendMtdTyp("MAIL");
        		
        		csoModelList.add(csoModel);
        	}
        	
        	if("Y".equals(estmOptionModel.getDailyEmail())){
        		csoModel = new ClntSendOptnModel();
        		csoModel.setClntId(estmOptionModel.getCustCode());
        		csoModel.setSendOptnFor("DLY");
        		csoModel.setSendMtdTyp("EMAIL");
        		
        		csoModelList.add(csoModel);
        	}
        	
        	if("Y".equals(estmOptionModel.getMonthlyMail())){
        		csoModel = new ClntSendOptnModel();
        		csoModel.setClntId(estmOptionModel.getCustCode());
        		csoModel.setSendOptnFor("MLY");
        		csoModel.setSendMtdTyp("MAIL");
        		csoModel.setClntId(estmOptionModel.getCustCode());
        		csoModelList.add(csoModel);
        	}
        	
        	if("Y".equals(estmOptionModel.getMonthlyEmail())){
        		csoModel = new ClntSendOptnModel();
        		csoModel.setClntId(estmOptionModel.getCustCode());
        		csoModel.setSendOptnFor("MLY");
        		csoModel.setSendMtdTyp("EMAIL");
        		
        		csoModelList.add(csoModel);
        	}
        	
        	ClntNotfSendOptnModel model = null;
        	
        	ClntSubptnInfoModel subStatModel = null;
        	
    		try {
    			ClntNotfSendOptnModel[] clntNotfModel = clientSrv.getClntStmtNotiSendOptnByClntId(estmOptionModel.getCustCode());
    			if(clntNotfModel!=null&&clntNotfModel.length>0){
    				for(int i=0;i<clntNotfModel.length;i++){  
        				if("EMAIL".equalsIgnoreCase(model.getNotfMtdTyp())){
            				model = clntNotfModel[i];
            				model.getEmail().getEmail().setEmailAddr(estmOptionModel.getEmail());
            				model.getClntSubptnInfoModel().setSubptnStat("SUBSCRIBED");
            				model.setClntSendOptnModelList((ClntSendOptnModel[]) csoModelList.toArray());
        				}
        				if("MAIL".equalsIgnoreCase(model.getNotfMtdTyp())){
        					subStatModel = clntNotfModel[i].getClntSubptnInfoModel();
        				}
    				}
    			}else{
//    				model = new ClntNotfSendOptnModel();
//    				model.setClntId(estmOptionModel.getCustCode());
    			}

    			if(model==null){
    				model = new ClntNotfSendOptnModel();
    				model.setClntId(estmOptionModel.getCustCode());
    				model.setNotfMtdTyp("EMAIL");
    				
    				ClntEmailModel cEmailModel = new ClntEmailModel();
    				EmailModel eMailModel = new EmailModel();
    				eMailModel.setEmailAddr(estmOptionModel.getEmail());
    				cEmailModel.setEmail(eMailModel);
    				model.setEmail(cEmailModel);
    				
    				if(subStatModel==null){
        				subStatModel = new ClntSubptnInfoModel();
        				subStatModel.setClntId(estmOptionModel.getCustCode());
    				}
    				subStatModel.setSubptnStat("SUBSCRIBED");
    				
    				model.setClntSubptnInfoModel(subStatModel);
    				
    				model.setClntSendOptnModelList((ClntSendOptnModel[]) csoModelList.toArray());
    				
    			}
    			
    	    	clientSrv.updateClntStmtNotiSendOpt(model);
    	    	
    	    	
    		} catch (CCISWsException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (RemoteException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    
    
	/**
	 * 
	 * @param partialAc
	 * @return
	 * @throws RemoteException
	 * @throws ESAppException 
	 * Replace:
	 * MISGatewayUtil.getCustListByPatialAcCode(final String patialAcCode)
	 */
    public  ArrayList getCustCodeListByPatialAcCode (String partialAc) {
		logger.debug("getCustCodeListByPatialAcCode("+partialAc+")");
		AccHdrModel[] accHdrModel=null;
		ArrayList ls=null;
		try {
			if("%-%%-%".equals(partialAc) || "%-%-%".equals(partialAc)){
				logger.error("Invalid search parameter for CCIS API getAccHdrByAccId... [" + partialAc + "]");
				return new ArrayList();
			}
			
			accHdrModel = accSrv.getAccHdrByAccId(partialAc);
			logger.info("AccHdrModel[] "+accHdrModel+" =  accSrv.getAccHdrByAccId("+partialAc+")");
	    	ls = new ArrayList();
	        if(accHdrModel!=null && accHdrModel.length>0){
	    		logger.debug("accHdrModel.length = " + accHdrModel.length);
	    		for( int i=0;i<accHdrModel.length;i++) {
	    			logger.debug("accHdrModel["+i+"].getClntStmtId()="+accHdrModel[i].getClntStmtId());
	        		if(accHdrModel[i].getClntStmtId()!=null) {
	        			ls.add(accHdrModel[i].getClntStmtId());
	        			logger.debug("ls.add("+accHdrModel[i].getClntStmtId()+")");
	        		}	        		
	    		}
	        }
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getAccHdrByAccId("+partialAc+")");
	    		  ls = getInstance().getCustCodeListByPatialAcCode(partialAc);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getAccHdrByAccId("+partialAc+")");
	    		  ls = getInstance().getCustCodeListByPatialAcCode(partialAc);
	    	  }else{
	    		  logger.error("Calling getAccHdrByAccId("+partialAc+"),CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_ACCHDR_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getAccHdrByAccId("+partialAc+") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException(e);
			es.setErrCode(ESErrorCode.CCIS_GET_ACCHDR_ERROR);
			throw es;
		}
        return ls;
	}
	

	public ArrayList getCustCodeListByBirthday(Timestamp birthday) {
    	logger.debug( "getCustCodeListByBirthday(birthday="+birthday+")");
    	java.util.Calendar c = null;
    	c = java.util.Calendar.getInstance();
    	c.setTimeInMillis(birthday.getTime());
    	String[] cc=null;
    	ArrayList  ccList=null;
		try {
			cc = logPrflService.getCustCodeList(c);
	    	logger.info( "String[] "+cc+" = clientSrv.getCustCodeList("+c+")");
			ccList = new ArrayList();
			if(cc!=null&&cc.length>0){
				for(int i=0;i<cc.length;i++) {
					ccList.add(cc[i]);
					logger.debug( "ccList.add(cc["+i+"]="+cc[i]+")");
				}
			}
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getCustCodeList("+c+")");
	    		  ccList = getInstance().getCustCodeListByBirthday(birthday);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getCustCodeList("+c+")");
	    		  ccList = getInstance().getCustCodeListByBirthday(birthday);
	    	  }else{
	    		  logger.error("Calling getCustCodeList("+birthday+"),CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_BIRTHDAY_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getCustCodeList("+birthday+") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException(e);
			es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_BIRTHDAY_ERROR);
			throw es;
		}
        return ccList;
    }

	public ArrayList getCustCodeListByDocID(String idNo, String idType) {
    	logger.debug( "getCustCodeListByDocID(idNo="+idNo+", idType="+idType+")");
    	String[] cc=null;
    	ArrayList  ccList=null;
		try {
			cc = logPrflService.getCustCodeList(idNo, idType);
	    	logger.info( "String[] "+cc+" = logPrflService.getCustCodeList(idNo="+idNo+", idType="+idType+")");
			ccList = new ArrayList();
			if(cc!=null&&cc.length>0){
				for(int i=0;i<cc.length;i++) {
					ccList.add(cc[i]);
					logger.debug( "ccList.add(cc["+i+"]="+cc[i]+")");
				}
			}
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getCustCodeList("+idNo+", "+idType+")");
	    		  ccList = getInstance().getCustCodeListByDocID(idNo, idType);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getCustCodeList("+idNo+", "+idType+")");
	    		  ccList = getInstance().getCustCodeListByDocID(idNo, idType);
	    	  }else{
	    		  logger.error("Calling getCustCodeList("+idNo+", "+idType+"),CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getCustCodeList("+idNo+","+idType+") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException(e);
			es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR);
			throw es;
		}
        return ccList;
    }

	/**
	 * 
	 * @param clntStmtId
	 * @return
	 *
	 * Described by Sandy 20070110
	 * for RTQ CN Plan, eServices should refer to 
	 * 1. country code = china (code=086), and
	 * 2. Has PRC ID = true, and
	 * 3. Has PRC Address Proof = true	 * 
	 * since 1+2+3 = country code = PRC in MIS
	 * if country = china and Has PRC Address Proof = true only, this is country code = china in MIS which cannot buy CN Plan gar
	 * remember each client can provide 2 addresses
	 * 
	 * 鍦ㄥ垽鏂峰浜烘槸鍚﹀彲浠ヨ卜rtq-cn plan, 鎳夎┎鏌ョ湅:
	 * 1. 浠讳綍Address鍏х殑country code = china (086); AND
	 * 2. ID Country = China, OR, Has PRC ID = true, AND
	 * 3. Has PRC Address Proof = ture
	 * 
	 * ID Country 浠嶦ServiceClientInfoModel.country瀛楁鑾峰緱,鍙﹀闇�鍘籧all clientSrv.getAddrByClntId(clntStmtId)鑾峰緱涓�釜ClntAddrModel鐨刲ist
	 * 
	 * Clarified by Lilian on 20070119
	 * 1.	Eservice 灏嗙敤浠�箞鏉′欢鏉ユ渶缁堝喅瀹氫竴涓鎴锋槸鍚﹀浗鍐呭鎴凤細
	 * 鍙瀹㈡埛鐨勮嚦灏戜竴涓猘ddress锛堜竴涓猲on virtual client鏈�鏈夛紥涓猘ddress锛屾渶灏戯紤涓級閲岄潰鐨勨�Has PRC ID鈥�"Has PRC address Proof"涓や釜check box閮戒负checked锛�
	 * 灏卞彲浠ュ垽鏂鎴蜂负鍥藉唴瀹㈡埛銆備絾瑕佹敞鎰忕殑鏄鍚堟潯浠剁殑address閲岄潰鐨勪袱涓猚heck box閮藉繀椤诲悓鏃朵负checked銆�
	 * 鐗瑰埆瑕佹敞鎰忕殑鏄紝濡傛灉涓�釜瀹㈡埛鏄痸irtual client,鍒欓渶瑕侀亶鍘嗗叾account holder鐨勬墍鏈塧ddress锛堟渶灏戯紥涓紝鏈�锛斾釜锛夈�
	 * 2.	鍏充簬鐩墠鐨勮璁℃棤娉曞尯鍒哻hina/oversea瀹㈡埛锛屾殏鏃剁殑绛斿鏄洰鍓嶆病鏈夊垱寤哄拰鎻愪緵oversea瀹㈡埛鐨勪环閽卞拰product锛屾墍浠ュ彲浠ヤ笉鑰冭檻銆備笉杩囨槑澶╀笌online鐨勪細璁細缁х画纭銆�
	 * 
	 * About country field by Wallace in 2007-3-27
	 * EServiceClientInfoModel return DOC ID country code,we can not use this field for cn plan
	 * e5 should use getEServiceClientInfoModel, then use getAddrByClntId to get the address country code.
	 * if one of the addresses isHasPrcId==true and isHasPrcAddr , return "PRC" 
	 * then 
	 *   if getAddrByClntId returns ClntAddrModel has more than one addr, use primary address.
	 *   if the client is non-virtual client, only one address is primary.
	 *   if the client is virtual client, it may have two primary addresses.
	 *       In this case, you can use the smallest client id to be the primary address of virtual client.
	 *       because the two primary addresses of virtual client may have different address country code.
	 *       e.g you use virtual client id 0067838,it may return
	 *       clnt id, seq_num, is_primy,...
	 *       0022618, 1, Y, ...
	 *       0018822, 1, Y, ...
	 *       then you can use 0018822 to be the primary address of virtual client. 
	 *        
	 *       
	 */
	public EServiceClientInfoModel getEServiceClientInfoModel(String clntStmtId) {
    	logger.info( "getEServiceClientInfoModel("+clntStmtId+")");
    	EServiceClientInfoModel clientInfo=null;
		try {
			clientInfo = logPrflService.getEServiceClientInfoModel(clntStmtId);
	    	logger.debug( "EServiceClientInfoModel = logPrflService.getEServiceClientInfoModel("+clntStmtId+")");
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getEServiceClientInfoModel("+clntStmtId+")");
	    		  clientInfo=getInstance().getEServiceClientInfoModel(clntStmtId);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getEServiceClientInfoModel("+clntStmtId+")");
	    		  clientInfo=getInstance().getEServiceClientInfoModel(clntStmtId);
	    	  }else{
	    		  logger.error("Calling getEServiceClientInfoModel("+clntStmtId+"),CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_ECINFO_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getEServiceClientInfoModel("+clntStmtId+") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException(e);
			es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR);
			throw es;
		}		
///////////////////////// sqpei	20070724
		try {
			ClntAddrModel clntAddr[] = this.getClntAddrByClntId(clntStmtId);
			logger.debug( "clientSrv.getAddrByClntId("+clntStmtId+")");			
			for (int i = 0 ; i< clntAddr.length; i++) {
				if (clntAddr[i].getAddr()!=null) {		
					if (clntAddr[i].getAddr().isHasPrcId() && clntAddr[i].getAddr().isHasPrcAddr()) {	
						clientInfo.setCountryCode("PRC");
						break;
					}else{
						if(clntAddr[i].isIsPrimy()){
							if(clntAddr[i].getAddr().getCntryCde()!=null){
								if(clntAddr.length>1 && i<clntAddr.length-1){
									if(StringUtils.leftPad(clntAddr[i].getClntId(),7,"0").compareTo(StringUtils.leftPad(clntAddr[i+1].getClntId(),7,"0"))>0){
										continue;
									}
								}
								clientInfo.setCountryCode(clntAddr[i].getAddr().getCntryCde());	
								break;
							}
						}else clientInfo.setCountryCode("");
					}
				}
			}
		} catch (RemoteException e) {
			logger.error("Call getClntAddrByClntId("+clntStmtId+") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException(e);
			es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR);
			throw es;
		}
		
//		boolean allowCNPlan = false;
//		try {
//			
//			ClntAddrModel clntAddr[] = this.getClntAddrByClntId(clntStmtId);
//			logger.info( "clientSrv.getAddrByClntId("+clntStmtId+")");			
//			for (int i = 0 ; i< clntAddr.length; i++) {
//				if (clntAddr[i].getAddr()!=null) {
//					//this.logClntAddr(clntAddr[i],i);					
//					if (clntAddr[i].getAddr().isHasPrcId() && clntAddr[i].getAddr().isHasPrcAddr()) {						
//						allowCNPlan = true ;
//						break;
//					} 				
//				}
//			}			
//		} catch (RemoteException e) {
//			logger.error("Call getClntAddrByClntId("+clntStmtId+") failed , cause by RemoteException!");
//			logger.error(e);
//			ESSysException es = new ESSysException(e);
//			es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR);
//			throw es;
//		}
//		
//		if (allowCNPlan){
//			clientInfo.setCountryCode("PRC");
//		} else {
//			clientInfo.setCountryCode("ALL");
//		}
		
    	//this.logEServiceClientInfo(clientInfo);
    	logger.info("clntStmtId=" + clntStmtId + "get clientInfo return!");
        return clientInfo;
    }
	
	//by sqpei 2009-04-10
	public EServiceClientInfoModel2 getEServiceClientInfoModel2(String clntStmtId) {
    	logger.info( "getEServiceClientInfoModel2("+clntStmtId+")");
    	EServiceClientInfoModel2 clientInfo=null;
		try {
			clientInfo = logPrflService.getEServiceClientInfoModel2(clntStmtId);
	    	logger.debug( "EServiceClientInfoModel = logPrflService.getEServiceClientInfoModel2("+clntStmtId+")");
		} catch (AxisFault e) {
	    	  if(e.getCause() instanceof ConnectException){
	    		  instance=null;
	    		  logger.error("Could not connect to CCIS for getEServiceClientInfoModel2("+clntStmtId+")");
	    		  clientInfo=getInstance().getEServiceClientInfoModel2(clntStmtId);
	    	  }else if(MsgCore.ErrCode.INVALID_SESSION.equals(e.getFaultCode().getLocalPart())){
	    		  instance=null;
	    		  logger.error("Connect to CCIS Session time out when getEServiceClientInfoModel2("+clntStmtId+")");
	    		  clientInfo=getInstance().getEServiceClientInfoModel2(clntStmtId);
	    	  }else{
	    		  logger.error("Calling getEServiceClientInfoModel2("+clntStmtId+"),CCIS system have an unknown exception!");
	    		  logger.error(e);
	    		  ESSysException es = new ESSysException();
	    		  es.setErrCode(ESErrorCode.CCIS_GET_ECINFO_ERROR);
	    		  throw es;
	    	  }
		} catch (RemoteException e) {
			logger.error("Call getEServiceClientInfoModel2("+clntStmtId+") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException(e);
			es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR);
			throw es;
		}		

		try {
			ClntAddrModel clntAddr[] = this.getClntAddrByClntId(clntStmtId);
			logger.debug( "clientSrv.getAddrByClntId("+clntStmtId+")");			
			for (int i = 0 ; i< clntAddr.length; i++) {
				if (clntAddr[i].getAddr()!=null) {		
					if (clntAddr[i].getAddr().isHasPrcId() && clntAddr[i].getAddr().isHasPrcAddr()) {	
						clientInfo.setCountryCode("PRC");
						break;
					}else{
						if(clntAddr[i].isIsPrimy()){
							if(clntAddr[i].getAddr().getCntryCde()!=null){
								if(clntAddr.length>1 && i<clntAddr.length-1){
									if(StringUtils.leftPad(clntAddr[i].getClntId(),7,"0").compareTo(StringUtils.leftPad(clntAddr[i+1].getClntId(),7,"0"))>0){
										continue;
									}
								}
								clientInfo.setCountryCode(clntAddr[i].getAddr().getCntryCde());	
								break;
							}
						}else clientInfo.setCountryCode("");
					}
				}
			}
		} catch (RemoteException e) {
			logger.error("Call getClntAddrByClntId("+clntStmtId+") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException(e);
			es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR);
			throw es;
		}

    	logger.info("clntStmtId=" + clntStmtId + "get clientInfo return!");
        return clientInfo;
    }
    /**
     * if custcode.length=5,must be added 0 ahead of the custcode. 
     * @param acCode
     * @return String
     */
	public static String convertCustCode(String s){
		int start,end;
		String sub=null;
		start=s.indexOf("-");
		end=s.indexOf("-",start+1);
		sub=s.substring(start+1,end);
		if(sub.length() < 7){
			for(sub.indexOf(0);sub.length()<7;sub="0"+sub){}
			StringBuffer sf = new StringBuffer();
			sf.append(s.substring(0,start+1));	
			sf.append(sub);
			sf.append(s.substring(end,s.length()));
			s=sf.toString();
		}
		return s;
	} 
    /**
     * riskRefValue From CCIS to here will be LOW銆丮EDIUM銆丠IGH. 
     * So must be convert 1 bit length,etc. L銆丮銆丠
     * @param riskRefValue
     * @return String
     */
    public static String getMapRiskRefValueFromCCIS(String riskRefValue){
    	//LOW, MEDIUM, HIGH
    	if (RiskRef.LOW.equals(StringUtil2.trimString(riskRefValue))){
    		return "L";
    	}
    	else if (RiskRef.MEDIUM.equals( StringUtil2.trimString( riskRefValue))){
    		return "M";
    	}
    	else if (RiskRef.HIGH.equals( StringUtil2.trimString( riskRefValue))){
    		return "H";
    	}
    	else 
    		return "";
    }
    public static String getMapRiskRefValue(String riskRefValue){
    	//LOW, MEDIUM, HIGH
    	if ("L".equals(StringUtil2.trimString(riskRefValue))){
    		return RiskRef.LOW;
    	}
    	else if ("M".equals( StringUtil2.trimString( riskRefValue))){
    		return RiskRef.MEDIUM;
    	}
    	else if ("H".equals( StringUtil2.trimString( riskRefValue))){
    		return RiskRef.HIGH;
    	}
    	else if("".equals( StringUtil2.trimString( riskRefValue))) {
    		return riskRefValue;
    	}else
    		throw new ESSystemException("getMapRiskRefValue(riskRefValue: "+riskRefValue+") Parameter error!");
    }
    /**
     * MaritalValue From CCIS to here will be UNKNOWN銆丮ARRIED銆丼INGLE. 
     * So must be convert 1 bit length,etc. S銆丮
     * @param maritalValue
     * @return String
     */
    public static String getMapMaritalValueFromCCIS(String maritalValue){
    	//UNKNOWN銆丮ARRIED銆丼INGLE
    	if ("UNKNOWN".equals(StringUtil2.trimString(maritalValue))){
    		return "";
    	}
    	else if ("SINGLE".equals( StringUtil2.trimString( maritalValue))){
    		return "S";
    	}
    	else if ("MARRIED".equals( StringUtil2.trimString( maritalValue))){
    		return "M";
    	}
    	else 
    		return "";
    }
    public static String getMapMaritalValue(String maritalValue){
    	//UNKNOWN銆丮ARRIED銆丼INGLE
    	if (maritalValue==null){
    		return "UNKNOWN";
    	}
    	else if ("M".equals( StringUtil2.trimString( maritalValue))){
    		return "MARRIED";
    	}
    	else if ("S".equals( StringUtil2.trimString( maritalValue))){
    		return "SINGLE";
    	}
    	else if("".equals( StringUtil2.trimString( maritalValue))) {
    		return "UNKNOWN";
    	}else
    		throw new ESSystemException("getMapMaritalValue(maritalValue: "+maritalValue+") Parameter error!");
    }
    /**
     * AnnualincomeValue From CCIS to here will be RANGE01銆丷ANGE02銆丷ANGE03銆丷ANGE04. 
     * So must be convert 1 bit length,etc. 1銆�銆�銆�
     * @param annualincomeValue
     * @return String
     */
    public static String getMapAnnualincomeValueFromCCIS(String annualincomeValue){
    	//RANGE01銆丷ANGE02銆丷ANGE03銆丷ANGE04
    	if ("RANGE01".equals(StringUtil2.trimString(annualincomeValue))){
    		return "1";
    	}
    	else if ("RANGE02".equals( StringUtil2.trimString( annualincomeValue))){
    		return "2";
    	}
    	else if ("RANGE03".equals( StringUtil2.trimString( annualincomeValue))){
    		return "3";
    	}
    	else if ("RANGE04".equals( StringUtil2.trimString( annualincomeValue))){
    		return "4";
    	}
    	else 
    		return "";
    }
    public static String getMapAnnualincomeValue(String annualincomeValue){
    	//RANGE01銆丷ANGE02銆丷ANGE03銆丷ANGE04
    	if ("1".equals( StringUtil2.trimString( annualincomeValue))){
    		return "RANGE01";
    	}
    	else if ("2".equals( StringUtil2.trimString( annualincomeValue))){
    		return "RANGE02";
    	}
    	else if ("3".equals( StringUtil2.trimString( annualincomeValue))){
    		return "RANGE03";
    	}
    	else if("4".equals( StringUtil2.trimString( annualincomeValue))) {
    		return "RANGE04";
    	}
    	else if("".equals( StringUtil2.trimString( annualincomeValue))) {
    		return "";
    	}
    	else if(annualincomeValue==null) {
    		return "";
    	}else
    		throw new ESSystemException("getMapAnnualincomeValue(annualincomeValue: "+annualincomeValue+") Parameter error!");
    }
    /**
     * SalutationValue From CCIS to here will be MR銆丮S銆丮I銆丮RS銆丏R銆乁NKNOWN. 
     * 
     * @param salutationValue
     * @return String
     */
    public static String getMapSalutationValueFromCCIS(String salutationValue){
    	//
    	if ("UNKNOWN".equals(StringUtil2.trimString(salutationValue))){
    		return "";
    	}
    	else 
    		return salutationValue;
    }
    public static String getMapSalutationValue(String salutationValue){
    	//
    	if (salutationValue==null){
    		return "UNKNOWN";
    	}
    	else if ("".equals( salutationValue)){
    		return "UNKNOWN";
    	}else
    		return salutationValue;
    }   
    /***********************************************************************************************************************/
    private String getNationalityName (String countryCode,String local){
    	logger.debug("getNationalityName ("+countryCode+","+local+")");
    	try {
			CntryModel countryModel = cmmService.getCntry(countryCode);
			logger.debug("CntryModel "+countryModel+" = cmmService.getCntry("+countryCode+")");
			CntryNameModel[] countryNameChildModels = countryModel.getCntryName();
			logger.debug("CntryNameModel[] "+countryNameChildModels+" = countryModel.getCntryName()");
			for (int i = 0; i < countryNameChildModels.length; i++) {
				logger.debug("countryNameChildModels["+i+"].isPrimy()="+countryNameChildModels[i].isPrimy());
				logger.debug("countryNameChildModels["+i+"].getLangTyp()="+countryNameChildModels[i].getLangTyp());
				logger.debug("countryNameChildModels["+i+"].getCntryNam()="+countryNameChildModels[i].getCntryNam());
				logger.debug("countryNameChildModels["+i+"].getCntryShtNam()="+countryNameChildModels[i].getCntryShtNam());
			    if (countryNameChildModels[i].isPrimy()==true) {
			        String lang = countryNameChildModels[i].getLangTyp(); //values can be EN, ZH_TW, ZH_CN, OTHS
			        logger.debug("String "+lang+" = countryNameChildModels["+i+"].getLangTyp()");
			    }
			}
		} catch (CCISWsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
	/****************************************************************************************************************
	 * 	the following is only for test																				*
	 ****************************************************************************************************************/ 
    public ClntNotfOptnModel getClntNotiOptByClntId(
			String clientStatementID,String optType) throws RemoteException {
		logger.debug("getClntNotiOptByClntId("+clientStatementID+")");
		ClntNotfOptnModel option = clientSrv.getClntStmtNotiOptByClntId(clientStatementID,optType);
		this.logClntNotfOptn(option,0);		
		return option;
	}
	public ClntEmailModel[] getEmailByClntId(String clientStatementID) throws RemoteException{

		logger.debug("clientSrv.getEmailByClntId("+clientStatementID+")");
		ClntEmailModel[] clntEmailModel = clientSrv.getEmailByClntId(clientStatementID);
		logger.info("clntEmailModel " + clntEmailModel +" = clientSrv.getEmailByClntId("+clientStatementID+")");
		if(clntEmailModel!=null){
			logger.debug("clntEmailModel.length=" + clntEmailModel.length);
			for (int i = 0; i < clntEmailModel.length; i++) {				
				this.logClntEmail(clntEmailModel[i],i);				
			}
		}
		return clntEmailModel;
	}
	public ClntPhoneModel[] getPhoneByClntId(String clientStatementID) throws RemoteException{

		logger.debug("clntPhoneModel = clientSrv.getPhoneByClntId("+clientStatementID+")");
		ClntPhoneModel[] clntPhoneModel = clientSrv.getPhoneByClntId(clientStatementID);
		logger.debug("clntPhoneModel = " + clntPhoneModel);
		if(clntPhoneModel!=null){
			logger.debug("clntPhoneModel.length=" + clntPhoneModel.length);
			long tmpSeq = -1;
			int tmpCnt = -1 ;
			for (int i = 0 ; i < clntPhoneModel.length; i++) {
				this.logClntPhone(clntPhoneModel[i],i);
				if (clntPhoneModel[i].getPhone().isFax()){
					
					if (tmpSeq == -1 || tmpSeq > clntPhoneModel[i].getSeqNum()) {
						tmpSeq = clntPhoneModel[i].getSeqNum() ;
						tmpCnt = i ;
						
					}
				}
			}
			if (tmpSeq !=-1) {
				logger.debug("The First Fax is ");
				this.logClntPhone(clntPhoneModel[tmpCnt],tmpCnt);
			}
		}
		return clntPhoneModel;
	}
	public ClntAddrModel[] getClntAddrByClntId(String clntStmtId) throws RemoteException {
		
		ClntAddrModel[] clntAddrArr = clientSrv.getAddrByClntId(clntStmtId);
		logger.debug("clientSrv.getAddrByClntId("+clntStmtId+")");
		if (clntAddrArr != null) {
			for (int i = 0 ; i < clntAddrArr.length ; i++) {
				this.logClntAddr(clntAddrArr[i],i) ;				
			}
		}		
		return clntAddrArr;		
	}
	private void updateClntNotiOpt(ClntNotfOptnModel notifyOption) throws RemoteException{
		logger.debug("updateClntNotiOpt(ClntNotfOptnModel="+notifyOption+")");
		logger.debug( "notifyOption.getNotfMtdTyp()=="+notifyOption.getNotfMtdTyp());

		logger.debug("clientSrv.getEmailByClntId(0000001)");
		ClntEmailModel[] clntEmailModel = clientSrv.getEmailByClntId("0000001");
		logger.debug("clntEmailModel = " + clntEmailModel);
		if(clntEmailModel!=null){
			logger.debug("clntEmailModel.length=" + clntEmailModel.length);
			for (int i = 0; i < clntEmailModel.length; i++) {
				logger.debug("clntEmailModel["+i+"].isPrimy() = " + clntEmailModel[i].isPrimy());
				logger.debug("clntEmailModel["+i+"].getEmail() = " + clntEmailModel[i].getEmail());
				if(clntEmailModel[i].getEmail()!=null&&true==clntEmailModel[i].isPrimy()){
					logger.debug("clntEmailModel["+i+"].getEmail().isIsNoCntct()="+clntEmailModel[i].getEmail().isNoCntct());
					if(clntEmailModel[i].getEmail().isNoCntct()== true){
						logger.debug("clntEmailModel["+i+"].getSeqNum()="+clntEmailModel[i].getSeqNum()+",clntEmailModel["+i+"].getEmail().getEmailAddr()="+clntEmailModel[i].getEmail().getEmailAddr());
						notifyOption.setClntEmailSeq(clntEmailModel[i].getSeqNum());
						logger.debug("notify.setClntEmailSeq("+clntEmailModel[i].getSeqNum()+")");
						notifyOption.setEmail(clntEmailModel[i]);
						logger.debug("notify.setEmail(clntEmailModel["+i+"])");
					}
				}
			}
		}
		logger.debug("clntPhoneModel = clientSrv.getPhoneByClntId("+"0000001"+")");
		ClntPhoneModel[] clntPhoneModel = clientSrv.getPhoneByClntId("0000001");
		logger.debug("clntPhoneModel = " + clntPhoneModel);
		if(clntPhoneModel!=null){
			logger.debug("clntPhoneModel.length=" + clntPhoneModel.length);
			for (int i = 0; i < clntPhoneModel.length; i++) {
				logger.debug("clntPhoneModel["+i+"].getClntId() = " + clntPhoneModel[i].getClntId());
				logger.debug("clntPhoneModel["+i+"].getSeqNum() = " + clntPhoneModel[i].getSeqNum());
				logger.debug("clntPhoneModel["+i+"].getPhone() = " + clntPhoneModel[i].getPhone());
				logger.debug("clntPhoneModel["+i+"].getPhone().getAttn()="+clntPhoneModel[i].getPhone().getAttn());
				logger.debug("clntPhoneModel["+i+"].getPhone().getCntryCde()="+clntPhoneModel[i].getPhone().getCntryCde());
				logger.debug("clntPhoneModel["+i+"].getPhone().getPhneCmpnyCde()="+clntPhoneModel[i].getPhone().getPhneCmpnyCde());
				logger.debug("clntPhoneModel["+i+"].getPhone().getPhneExtension()="+clntPhoneModel[i].getPhone().getPhneExtension());
				logger.debug("clntPhoneModel["+i+"].getPhone().getPhneNum()="+clntPhoneModel[i].getPhone().getPhneNum());
				logger.debug("clntPhoneModel["+i+"].getPhone().getPhneTyp()="+clntPhoneModel[i].getPhone().getPhneTyp());
				logger.debug("clntPhoneModel["+i+"].getPhone().getRemrk()="+clntPhoneModel[i].getPhone().getRemrk());
				logger.debug("clntPhoneModel["+i+"].getPhone().isFax()="+clntPhoneModel[i].getPhone().isFax());
				logger.debug("clntPhoneModel["+i+"].isPrimy() = " + clntPhoneModel[i].isPrimy());
				if(clntPhoneModel[i].isPrimy()== true){
					logger.debug("clntPhoneModel["+i+"].getPhone()="+clntPhoneModel[i].getPhone());
					if(clntPhoneModel[i].getPhone()!=null){
						PhoneModel phonemodel = clntPhoneModel[i].getPhone();
						logger.debug("phonemodel.getAttn()="+phonemodel.getAttn());
						logger.debug("phonemodel.getCntryCde()="+phonemodel.getCntryCde());
						logger.debug("phonemodel.getPhneCmpnyCde()="+phonemodel.getPhneCmpnyCde());
						logger.debug("phonemodel.getPhneExtension()="+phonemodel.getPhneExtension());
						logger.debug("phonemodel.getPhneNum()="+phonemodel.getPhneNum());
						logger.debug("phonemodel.getPhneTyp()="+phonemodel.getPhneTyp());
						logger.debug("phonemodel.getRemrk()="+phonemodel.getRemrk());
						logger.debug("phonemodel.isFax()="+phonemodel.isFax());
						if(phonemodel.isFax()==true){
							notifyOption.setClntPhneSeq(clntPhoneModel[i].getSeqNum());
							logger.debug("clntPhoneModel["+i+"].getPhone().getPhneNum()="+clntPhoneModel[i].getPhone().getPhneNum());
							notifyOption.setPhone(clntPhoneModel[i]);
						}
					}
				}
			}
		}
		if(notifyOption!=null){
			logger.debug( "notifyOption.getEmail()="+notifyOption.getEmail());
			if(notifyOption.getEmail()!=null){
				logger.debug( "notifyOption.getEmail().getEmail()="+notifyOption.getEmail().getEmail());
				if(notifyOption.getEmail().getEmail()!=null){
					logger.debug( "notifyOption.getEmail().getEmail().getEmailAddr()="+notifyOption.getEmail().getEmail().getEmailAddr());
				}
			}
			logger.debug( "notifyOption.getPhone()="+notifyOption.getPhone());
			if(notifyOption.getPhone()!=null){
				logger.debug( "notifyOption.getPhone().getPhone()="+notifyOption.getPhone().getPhone());
				if(notifyOption.getPhone().getPhone()!=null){
					logger.debug( "notifyOption.getPhone().getPhone().getPhneNum()="+notifyOption.getPhone().getPhone().getPhneNum());
				}
			}			
		}

		notifyOption.setNotfMtdTyp("FAX");
		clientSrv.updateClntStmtNotiOpt(notifyOption);
		notifyOption.setNotfMtdTyp("EMAIL");
		clientSrv.updateClntStmtNotiOpt(notifyOption);
		logger.debug("clientSrv.updateClntNotiOpt("+notifyOption+")");
	}
	/**
	 * 
	 * @param clientStatementID
	 * @param email
	 * @param emailseq
	 * @param b
	 * @throws RemoteException
	 * 
	 * 1 more possible returned message: CCIS-API-000067 = "EMAIL_DUPLICATE"
	 */
	private void updateEmail(String clientStatementID,String email,long emailseq,boolean b) throws RemoteException{
		logger.debug("updateEmail(clientStatementID="+clientStatementID+","+email+","+emailseq+")");
		//set opt email
		logger.debug("clientSrv.getEmailByClntId("+clientStatementID+")");
		ClntEmailModel[] clntEmailModel = clientSrv.getEmailByClntId(clientStatementID);
		logger.debug("clntEmailModel = " + clntEmailModel);
		if(clntEmailModel!=null){
			logger.debug("clntEmailModel.length=" + clntEmailModel.length);
			for (int i = 0; i < clntEmailModel.length; i++) {
				logger.debug("clntEmailModel["+i+"].getEmail() = " + clntEmailModel[i].getEmail());
				clntEmailModel[i].setPrimy(b);
				logger.debug("clntEmailModel["+i+"].primy ="+b);
				if(clntEmailModel[i].getEmail()!=null){
					logger.debug("clntEmailModel["+i+"].getEmail().isIsNoCntct()="+clntEmailModel[i].getEmail().isNoCntct());
					
						logger.debug("clntEmailModel["+i+"].getSeqNum()="+clntEmailModel[i].getSeqNum()+",clntEmailModel["+i+"].getEmail().getEmailAddr()="+clntEmailModel[i].getEmail().getEmailAddr());
						if(clntEmailModel[i].getSeqNum()==emailseq){
							clntEmailModel[i].getEmail().setNoCntct(b);
							clntEmailModel[i].getEmail().setEmailAddr(email);
							clientSrv.updateEmail(clntEmailModel[i]);
							logger.debug("clientSrv.updateEmail("+clntEmailModel[i]+")");
							logger.debug("clntEmailModel["+i+"].getEmail().isIsNoCntct()="+clntEmailModel[i].getEmail().isNoCntct());
							logger.debug("clntEmailModel["+i+"].getSeqNum()="+clntEmailModel[i].getSeqNum()+",clntEmailModel["+i+"].getEmail().getEmailAddr()="+clntEmailModel[i].getEmail().getEmailAddr());
						}
					
				}
			}
		}
	}
	private void updatePhone(String clientStatementID,String phone,long phoneseq,boolean b) throws RemoteException{
		logger.debug("updatePhone(clientStatementID="+clientStatementID+")");
		//set opt phone
		logger.debug("clntPhoneModel = clientSrv.getPhoneByClntId("+clientStatementID+","+phone+","+phoneseq+")");
		ClntPhoneModel[] clntPhoneModel = clientSrv.getPhoneByClntId(clientStatementID);
		logger.debug("clntPhoneModel = " + clntPhoneModel);
		if(clntPhoneModel!=null){
			logger.debug("clntPhoneModel.length=" + clntPhoneModel.length);
			for (int i = 0; i < clntPhoneModel.length; i++) {
				logger.debug("clntPhoneModel["+i+"].isPrimy() = " + clntPhoneModel[i].isPrimy());
				if(clntPhoneModel[i].isPrimy()== true){
					logger.debug("clntPhoneModel["+i+"].getPhone()="+clntPhoneModel[i].getPhone());
					if(clntPhoneModel[i].getPhone()!=null){
						PhoneModel phonemodel = clntPhoneModel[i].getPhone();
						logger.debug("phonemodel.isFax()="+phonemodel.isFax());
//						if(phonemodel.isFax()==true){
							logger.debug("clntPhoneModel["+i+"].getPhone().getPhneNum()="+clntPhoneModel[i].getPhone().getPhneNum());
							if(clntPhoneModel[i].getSeqNum()==phoneseq){
								clntPhoneModel[i].setPrimy(b);
								clntPhoneModel[i].getPhone().setFax(b);
								clntPhoneModel[i].getPhone().setPhneNum(phone);
								clientSrv.updatePhone(clntPhoneModel[i]);
								logger.debug("clntPhoneModel["+i+"].getPhone().isFax()="+clntPhoneModel[i].getPhone().isFax());
								logger.debug("clientSrv.updatePhone("+clntPhoneModel[i]+")");
								logger.debug("clntPhoneModel["+i+"].getPhone().getPhneNum()="+clntPhoneModel[i].getPhone().getPhneNum());
							}
//						}
					}
				}
			}
		}
	}
	private void insertClntNotiOpt(String clientStatementID, String NotfMetdType, long seq) throws RemoteException{
		ClntNotfOptnModel notify = new ClntNotfOptnModel();
		notify.setClntId(clientStatementID);
		notify.setNotfItemCde("STMT");

		notify.setNotfMtdTyp(NotfMetdType);

		//set opt email
		logger.debug("clientSrv.getEmailByClntId("+clientStatementID+")");
		ClntEmailModel[] clntEmailModel = clientSrv.getEmailByClntId("0000001");
		logger.debug("clntEmailModel = " + clntEmailModel);
		if(clntEmailModel!=null){
			logger.debug("clntEmailModel.length=" + clntEmailModel.length);
			for (int i = 0; i < clntEmailModel.length; i++) {
				logger.debug("clntEmailModel["+i+"].isPrimy() = " + clntEmailModel[i].isPrimy());
				logger.debug("clntEmailModel["+i+"].getEmail() = " + clntEmailModel[i].getEmail());
				if(clntEmailModel[i].getEmail()!=null&&true==clntEmailModel[i].isPrimy()){
					logger.debug("clntEmailModel["+i+"].getEmail().isIsNoCntct()="+clntEmailModel[i].getEmail().isNoCntct());
					if(clntEmailModel[i].getEmail().isNoCntct()== true){
						logger.debug("clntEmailModel["+i+"].getSeqNum()="+clntEmailModel[i].getSeqNum()+",clntEmailModel["+i+"].getEmail().getEmailAddr()="+clntEmailModel[i].getEmail().getEmailAddr());
						notify.setClntEmailSeq(clntEmailModel[i].getSeqNum());
						logger.debug("notify.setClntEmailSeq("+clntEmailModel[i].getSeqNum()+")");
						notify.setEmail(clntEmailModel[i]);
						logger.debug("notify.setEmail(clntEmailModel["+i+"])");
					}
				}
			}
		}
		//set opt phone
		logger.debug("clntPhoneModel = clientSrv.getPhoneByClntId("+clientStatementID+")");
		ClntPhoneModel[] clntPhoneModel = clientSrv.getPhoneByClntId("0000001");
		logger.debug("clntPhoneModel = " + clntPhoneModel);
		if(clntPhoneModel!=null){
			logger.debug("clntPhoneModel.length=" + clntPhoneModel.length);
			for (int i = 0; i < clntPhoneModel.length; i++) {
				logger.debug("clntPhoneModel["+i+"].getClntId() = " + clntPhoneModel[i].getClntId());
				logger.debug("clntPhoneModel["+i+"].getSeqNum() = " + clntPhoneModel[i].getSeqNum());
				logger.debug("clntPhoneModel["+i+"].getPhone() = " + clntPhoneModel[i].getPhone());
				logger.debug("clntPhoneModel["+i+"].getPhone().getAttn()="+clntPhoneModel[i].getPhone().getAttn());
				logger.debug("clntPhoneModel["+i+"].getPhone().getCntryCde()="+clntPhoneModel[i].getPhone().getCntryCde());
				logger.debug("clntPhoneModel["+i+"].getPhone().getPhneCmpnyCde()="+clntPhoneModel[i].getPhone().getPhneCmpnyCde());
				logger.debug("clntPhoneModel["+i+"].getPhone().getPhneExtension()="+clntPhoneModel[i].getPhone().getPhneExtension());
				logger.debug("clntPhoneModel["+i+"].getPhone().getPhneNum()="+clntPhoneModel[i].getPhone().getPhneNum());
				logger.debug("clntPhoneModel["+i+"].getPhone().getPhneTyp()="+clntPhoneModel[i].getPhone().getPhneTyp());
				logger.debug("clntPhoneModel["+i+"].getPhone().getRemrk()="+clntPhoneModel[i].getPhone().getRemrk());
				logger.debug("clntPhoneModel["+i+"].getPhone().isFax()="+clntPhoneModel[i].getPhone().isFax());
				logger.debug("clntPhoneModel["+i+"].isPrimy() = " + clntPhoneModel[i].isPrimy());
				if(clntPhoneModel[i].isPrimy()== true){
					logger.debug("clntPhoneModel["+i+"].getPhone()="+clntPhoneModel[i].getPhone());
					if(clntPhoneModel[i].getPhone()!=null){
						PhoneModel phonemodel = clntPhoneModel[i].getPhone();
						logger.debug("phonemodel.getAttn()="+phonemodel.getAttn());
						logger.debug("phonemodel.getCntryCde()="+phonemodel.getCntryCde());
						logger.debug("phonemodel.getPhneCmpnyCde()="+phonemodel.getPhneCmpnyCde());
						logger.debug("phonemodel.getPhneExtension()="+phonemodel.getPhneExtension());
						logger.debug("phonemodel.getPhneNum()="+phonemodel.getPhneNum());
						logger.debug("phonemodel.getPhneTyp()="+phonemodel.getPhneTyp());
						logger.debug("phonemodel.getRemrk()="+phonemodel.getRemrk());
						logger.debug("phonemodel.isFax()="+phonemodel.isFax());
						if(phonemodel.isFax()==true){
							notify.setClntPhneSeq(clntPhoneModel[i].getSeqNum());
							logger.debug("clntPhoneModel["+i+"].getPhone().getPhneNum()="+clntPhoneModel[i].getPhone().getPhneNum());
							notify.setPhone(clntPhoneModel[i]);
						}
					}
				}
			}
		}

		clientSrv.insertClntStmtNotiOpt(notify);
		logger.debug("clientSrv.insertClntStmtNotiOpt("+notify+")");
	}
	/**
	 * 
	 * @param clientStatementID
	 * @param emailAddr
	 * @param seq
	 * @param NoCntct
	 * @throws RemoteException
	 * 1 more possible returned message: CCIS-API-000067 = "EMAIL_DUPLICATE"
	 */
	private void insertEmail(String clientStatementID,String emailAddr,long seq,boolean NoCntct) throws RemoteException{
		ClntEmailModel email= new ClntEmailModel();
		EmailModel em = new EmailModel();
		em.setEmailAddr(emailAddr);
		em.setNoCntct(true);
		email.setClntId(clientStatementID);
		email.setSeqNum(seq);
		email.setPrimy(true);
		email.setEmail( em);
		//option.setEmail(email);
		logger.debug("em.setEmailAddr("+emailAddr+"),em.setNoCntct(true),email.setClntId("+clientStatementID+"),email.setEmail( "+em+"),option.setEmail("+email+")");
		clientSrv.insertEmail(email);
		logger.debug("clientSrv.insertEmail("+email+")");
	}
	private void insertPhone(String clientStatementID,String faxNo,long seq,boolean pk) throws RemoteException{
		ClntPhoneModel fax = new ClntPhoneModel();
		PhoneModel pm = new PhoneModel();
		pm.setIsFax(pk);
		pm.setPhneNum(faxNo);
		fax.setClntId(clientStatementID);
		fax.setSeqNum(seq);
		fax.setPrimy(pk);
		fax.setPhone( pm);
//		option.setPhone(fax);
		logger.debug("option.setPhone("+fax+"),fax.setPhone("+ pm+"),fax.setPrimy(true),fax.setClntId("+clientStatementID+")");
		clientSrv.insertPhone(fax);
		logger.debug("clientSrv.insertPhone("+fax+")");
	}
	private void logClntNotfOptn(ClntNotfOptnModel notifyOption, int count) {
		StringBuffer buf = new StringBuffer();
		buf.append("ClntNotfOptnModel[" + count + "]");
		if (notifyOption == null) {
			buf.append(" is null ");
		} else {
			buf.append(":clntId=" + notifyOption.getClntId());
			buf.append(";notfMtdTyp=" + notifyOption.getNotfMtdTyp());
			buf.append(";seqNum=" + notifyOption.getSeqNum());
			buf.append(";notfItemCde=" + notifyOption.getNotfItemCde());
			buf.append(";clntEmailSeq=" + notifyOption.getClntEmailSeq());
			if (notifyOption.getEmail() == null) {
				buf.append(";ClntEmailModel is null");
			} else {
				buf.append(";email.seq=" + notifyOption.getEmail().getSeqNum());
				buf.append(";email.isPri=" + notifyOption.getEmail().isPrimy());				
				if (notifyOption.getEmail().getEmail() == null) {
					buf.append(";EmailModel is null");
				} else {					
					buf.append(";email.addr=" + notifyOption.getEmail().getEmail().getEmailAddr());
				}
			}
			buf.append(";clntPhneSeq=" + notifyOption.getClntPhneSeq());
			if (notifyOption.getPhone() == null) {
				buf.append(";ClntPhneModel is null");
			} else {
				buf.append(";phone.seq=" + notifyOption.getPhone().getSeqNum());
				if (notifyOption.getPhone().getPhone() == null) {
					buf.append(";PhoneModel is null");
				} else {
					buf.append(";phone.isFax="	+ notifyOption.getPhone().getPhone().isFax());
					buf.append(";phone.num=" + notifyOption.getPhone().getPhone().getPhneNum());
				}
			}
			buf.append(";clntAddressSeq = " + notifyOption.getClntAddressSeq());
			if (notifyOption.getAddress() == null) {
				buf.append("ClntAddrModel is null");
			} else {
				buf.append(";address.seq=" + notifyOption.getAddress().getSeqNum());
				buf.append(";address.isPrimary=" + notifyOption.getAddress().isIsPrimy());
				if (notifyOption.getAddress().getAddr() == null) {
					buf.append(";AddrModel is null");
				} else {
					buf.append(";address.line1=" + notifyOption.getAddress().getAddr().getAddrLine1());
					buf.append(";address.line2=" + notifyOption.getAddress().getAddr().getAddrLine2());
					buf.append(";address.line3=" + notifyOption.getAddress().getAddr().getAddrLine3());
				}
			}
		}
		logger.debug(buf.toString() );
	}
	
	private void logClntEmail(ClntEmailModel clntEmailModel,int count){
		StringBuffer buf = new StringBuffer();
		buf.append("clntEmailModel[" + count + "]:clntId()=");
		buf.append(clntEmailModel == null ? "is null" : clntEmailModel.getClntId());
		buf.append(";seqNum()=");
		buf.append(clntEmailModel == null ? "is null" : clntEmailModel.getSeqNum()+"");
		buf.append(";isPrimy()=");
		buf.append(clntEmailModel == null ? "is null" : clntEmailModel.isPrimy()+"");
		buf.append(";email() = ");
		buf.append(clntEmailModel == null || clntEmailModel.getEmail() == null ? "is null" : clntEmailModel.getEmail().getEmailAddr());
		
		logger.debug(buf.toString());
	}
    private void logClntPhone(ClntPhoneModel clntPhoneModel,int i){
    	StringBuffer buf = new StringBuffer();
		buf.append("clntPhoneModel["+i+"]:clntId() = ");
		buf.append(clntPhoneModel == null ? "is null":clntPhoneModel.getClntId());
		buf.append(";seqNum = ");
		buf.append(clntPhoneModel == null ? "is null":clntPhoneModel.getSeqNum()+"");
		buf.append(";isPrimy() = ");
		buf.append(clntPhoneModel == null ? "is null":clntPhoneModel.isPrimy()+"");
		buf.append(";phone.isFax=");
		buf.append(clntPhoneModel == null || clntPhoneModel.getPhone() == null ? "is null":clntPhoneModel.getPhone().isFax()+"");
		buf.append(";phone.phneNum=");
		buf.append(clntPhoneModel == null || clntPhoneModel.getPhone() == null ? "is null":clntPhoneModel.getPhone().getPhneNum());
		buf.append(";phone.phneTyp=");
		buf.append(clntPhoneModel == null || clntPhoneModel.getPhone() == null ? "is null":clntPhoneModel.getPhone().getPhneTyp()+"");
		buf.append(";phone.attn=");
		buf.append(clntPhoneModel == null || clntPhoneModel.getPhone() == null ? "is null":clntPhoneModel.getPhone().getAttn());
		buf.append(";phone.cntryCde=");
		buf.append(clntPhoneModel == null || clntPhoneModel.getPhone() == null ? "is null":clntPhoneModel.getPhone().getCntryCde());
		buf.append(";phone.phneCmpnyCde=");
		buf.append(clntPhoneModel == null || clntPhoneModel.getPhone() == null ? "is null":clntPhoneModel.getPhone().getPhneCmpnyCde());
		buf.append(";phone.phneExtension=");
		buf.append(clntPhoneModel == null || clntPhoneModel.getPhone() == null ? "is null":clntPhoneModel.getPhone().getPhneExtension());
		buf.append(";phone.remrk=");
		buf.append(clntPhoneModel == null || clntPhoneModel.getPhone() == null ? "is null":clntPhoneModel.getPhone().getRemrk());
		buf.append("");
		buf.append("");
    	
    	
    	logger.debug(buf.toString());
    }
    private void logClntAddr(ClntAddrModel clntAddrModel,int i) {
    	logger.debug("ClntAddrModel["+i+"]: " + clntAddrModel == null ? "is null":
    			"seqNum = " + clntAddrModel == null ? "is null":clntAddrModel.getSeqNum()
				+ ";isPrimy = " + clntAddrModel == null ? "is null":clntAddrModel.isIsPrimy()
				+ ";addr.AddrTyp = " + clntAddrModel == null || clntAddrModel.getAddr() == null ? "is null":clntAddrModel.getAddr().getAddrTyp()
				+ ";CntryCde = " + clntAddrModel == null || clntAddrModel.getAddr() == null ? "is null":clntAddrModel.getAddr().getCntryCde() 
				+ ";HasPrcAddr = " + clntAddrModel == null || clntAddrModel.getAddr() == null ? "is null":clntAddrModel.getAddr().isHasPrcAddr() 
				+ ";HasPrcId = " + clntAddrModel == null || clntAddrModel.getAddr() == null ? "is null":clntAddrModel.getAddr().isHasPrcId() 
				+ ";addr.addrLine1 = " + clntAddrModel == null || clntAddrModel.getAddr() == null ? "is null":clntAddrModel.getAddr().getAddrLine1()
				+ ";addr.addrLine2 = " + clntAddrModel == null || clntAddrModel.getAddr() == null ? "is null":clntAddrModel.getAddr().getAddrLine2()
				+ ";addr.addrLine3 = " + clntAddrModel == null || clntAddrModel.getAddr() == null ? "is null":clntAddrModel.getAddr().getAddrLine3());
    }
    
    private void logAccModel(AccModel accModel,int i){
    	StringBuffer buf = new StringBuffer();
    	buf.append("AccModel["+i+"]:");
    	if (accModel==null) {
    		buf.append("is null.");
    	} else {
    		
    		//accStatus:NORMAL SUSPEND DECEASED CEASED CLOSED
    		buf.append(";AcStat="+accModel.getAcStat());
    		buf.append(";AcStmtId="+accModel.getAcStmtId());    		
    		//accType:INDIVIDUAL JOINT CORPORATE
    		buf.append(";AcTyp="+accModel.getAcTyp());
    		//鑾峰緱AEGrpCode
    		buf.append(";AeAcesGrpCde="+accModel.getAeAcesGrpCde());
    		if (accModel.getCeaseDate()!=null) {
    			buf.append(";CeaseDate=" + 
    				((Calendar)accModel.getCeaseDate()).get(Calendar.YEAR) + "-" +
    				(((Calendar)accModel.getCeaseDate()).get(Calendar.MONTH)+1) + "-" +
    				((Calendar)accModel.getCeaseDate()).get(Calendar.DAY_OF_MONTH));
    		} else {
    			buf.append(";CeaseDate=" + accModel.getCeaseDate()); 
    		}

    		buf.append(";CmpnyCde="+accModel.getCmpnyCde());
    		buf.append(";OpenDate="+accModel.getOpenDate());
    		buf.append(";Remrk="+accModel.getRemrk());
    		if (accModel.getReopenDate()!=null) {
    			buf.append(";ReopenDate="+
    				((Calendar)accModel.getReopenDate()).get(Calendar.YEAR) + "-" +
    				(((Calendar)accModel.getReopenDate()).get(Calendar.MONTH)+1) + "-" +
    				((Calendar)accModel.getReopenDate()).get(Calendar.DAY_OF_MONTH));
    		} else {
    			buf.append(";ReopenDate="+ accModel.getReopenDate());
    		}
    		buf.append(";SuspdDate="+accModel.getSuspdDate());
    		
    		buf.append(";UnitCde="+accModel.getUnitCde());    		
    		
    	}
    	logger.debug(buf.toString());
    	
    	
    }
    
    private void logAllAccModel(AllAccModel allAccModel,int i){
    	StringBuffer buf = new StringBuffer();
    	buf.append("AllAccModel["+i+"]:");
    	if (allAccModel==null) {
    		buf.append("is null.");
    	} else {
    		
    		buf.append(";AcStat="+allAccModel.getAcStat());
    		buf.append(";AcStmtId="+allAccModel.getAcStmtId());    		
    		//accType:INDIVIDUAL JOINT CORPORATE
    		buf.append(";AcTyp="+allAccModel.getAcTyp());
    		
    		if (allAccModel.getClosDate()!=null) {
    			buf.append(";CloseDate=" + 
    				((Calendar)allAccModel.getClosDate()).get(Calendar.YEAR) + "-" +
    				(((Calendar)allAccModel.getClosDate()).get(Calendar.MONTH)+1) + "-" +
    				((Calendar)allAccModel.getClosDate()).get(Calendar.DAY_OF_MONTH));
    		} else {
    			buf.append(";CeaseDate=" + allAccModel.getClosDate()); 
    		}
    		//鑾峰緱AEGrpCode
    		buf.append(";AeAcesGrpCde="+allAccModel.getAeAcesGrpCde());
    		if (allAccModel.getCeaseDate()!=null) {
    			buf.append(";CeaseDate=" + 
    				((Calendar)allAccModel.getCeaseDate()).get(Calendar.YEAR) + "-" +
    				(((Calendar)allAccModel.getCeaseDate()).get(Calendar.MONTH)+1) + "-" +
    				((Calendar)allAccModel.getCeaseDate()).get(Calendar.DAY_OF_MONTH));
    		} else {
    			buf.append(";CeaseDate=" + allAccModel.getCeaseDate()); 
    		}
    		//?
    		buf.append(";CmpnyCde="+allAccModel.getCmpnyCde());
    		buf.append(";OpenDate="+allAccModel.getOpenDate());
    		buf.append(";Remrk="+allAccModel.getRemrk());
    		if (allAccModel.getReopenDate()!=null) {
    			buf.append(";ReopenDate="+
    				((Calendar)allAccModel.getReopenDate()).get(Calendar.YEAR) + "-" +
    				(((Calendar)allAccModel.getReopenDate()).get(Calendar.MONTH)+1) + "-" +
    				((Calendar)allAccModel.getReopenDate()).get(Calendar.DAY_OF_MONTH));
    		} else {
    			buf.append(";ReopenDate="+ allAccModel.getReopenDate());
    		}
    		buf.append(";SuspdDate="+allAccModel.getSuspdDate());
    		//?
    		buf.append(";UnitCde="+allAccModel.getUnitCde());    		
    		
    	}
    	logger.debug(buf.toString());
    	
    	
    }
    private void logInvBg(InvBgModel invBgModel){
    	StringBuffer buf = new StringBuffer();
    	buf.append("AccModel:");
    	if(invBgModel==null){
    		buf.append("is null.");    		
    	} else {
    		buf.append("ClntStmtId=" + invBgModel.getClntStmtId());
			//invest expirence : others ?
    		buf.append("ExprOths=" + invBgModel.getExprOths());
			//invest object : others ?
    		buf.append("ObjOths=" + invBgModel.getObjOths());
			//primary brokerage firm
    		buf.append("PrimyBrkgFirm=" + invBgModel.getPrimyBrkgFirm());
			//risk preference
    		buf.append("RiskPref=" + invBgModel.getRiskPref());
    		buf.append("Expr()=" + Arrays.asList(invBgModel.getExpr()));
    		buf.append("Obj()=" + Arrays.asList(invBgModel.getObj()));			
		}
    	logger.debug(buf.toString());
    }
    private void logEServiceClientInfo(EServiceClientInfoModel clientInfo){
    	StringBuffer buf = new StringBuffer();
    	buf.append("EServiceClientInfoModel:");
    	if (clientInfo==null){
    		buf.append("is null.");
    	} else {
    		buf.append("AddressLine1=" + clientInfo.getAddressLine1());
    		buf.append(";AddressLine2=" + clientInfo.getAddressLine2());
    		buf.append(";AddressLine3=" + clientInfo.getAddressLine3());
    		buf.append(";CountryCode=" + clientInfo.getCountryCode());
    		buf.append(";CustName=" + clientInfo.getCustName());
    		buf.append(";District=" + clientInfo.getDistrict());
    		buf.append(";DocId=" + clientInfo.getDocId());
    		buf.append(";DocType=" + clientInfo.getDocType());
    		buf.append(";IndStaff=" + clientInfo.getIndStaff());
    		buf.append(";Martial=" + clientInfo.getMartial());
    		buf.append(";Nationality=" + clientInfo.getNationality());
    		buf.append(";Occupation=" + clientInfo.getOccupation());
    		buf.append(";PhoneNum1=" + clientInfo.getPhoneNum1());
    		buf.append(";PhoneNum2=" + clientInfo.getPhoneNum2());
    		buf.append(";PrimaryBrokerageFirm=" + clientInfo.getPrimaryBrokerageFirm());
    		buf.append(";RiskRef=" + clientInfo.getRiskRef());
    		buf.append(";Sex=" + clientInfo.getSex());
    		buf.append(";TitleCode=" + clientInfo.getTitleCode());
        	if (clientInfo.getBirthday()!=null) {
        		buf.append(";Birthday=" + 
        			((Calendar)clientInfo.getBirthday()).get(Calendar.YEAR) + "-" + 
        			(((Calendar)clientInfo.getBirthday()).get(Calendar.MONTH)+1) + "-" +
        			((Calendar)clientInfo.getBirthday()).get(Calendar.DAY_OF_MONTH)) ;
        	}
        	buf.append(";InvestExp=" + Arrays.asList(clientInfo.getInvestExp()));			
        	buf.append(";InvestObj=" + Arrays.asList(clientInfo.getInvestObj()));			
    	}
    	logger.info(buf.toString());
    }
 
    public String getCNDiscountFlag(String clntStmtId){
      	logger.info( "getCNDiscountFlag("+clntStmtId+")");
    	String cnDiscFlag = "" ;
		try {
			ClntAddrModel clntAddr[] = this.getClntAddrByClntId(clntStmtId);
			logger.debug( "clientSrv.getAddrByClntId("+clntStmtId+")");			
			for (int i = 0 ; i< clntAddr.length; i++) {
				if (clntAddr[i].getAddr()!=null) {		
					if (clntAddr[i].getAddr().isHasPrcId() && clntAddr[i].getAddr().isHasPrcAddr()) {	
						cnDiscFlag = "PRC";
						break;
					}else{
						if(clntAddr[i].isIsPrimy()){
							if(clntAddr[i].getAddr().getCntryCde()!=null){
								if(clntAddr.length>1 && i<clntAddr.length-1){
									if(StringUtils.leftPad(clntAddr[i].getClntId(),7,"0").compareTo(StringUtils.leftPad(clntAddr[i+1].getClntId(),7,"0"))>0){
										continue;
									}
								}
								cnDiscFlag = clntAddr[i].getAddr().getCntryCde();	
								break;
							}
						} else cnDiscFlag = "";
					}
				} else cnDiscFlag = "";
			}
		} catch (RemoteException e) {
			logger.error("Call getCNDiscountFlag("+clntStmtId+") failed , cause by RemoteException!");
			logger.error(e);
			ESSysException es = new ESSysException(e);
			es.setErrCode(ESErrorCode.CCIS_GET_CUSTCODELIST_BY_DOCID_ERROR);
			throw es;
		}

    	logger.info("clntStmtId=" + clntStmtId + "get clientInfo return!");
    	
    	if(!cnDiscFlag.equals("PRC")){
    		cnDiscFlag="OTHERS";
    	}
        return cnDiscFlag;
    }
    
    public static void main(String[] args){
    	List invBgModel = CCISUtil.getInstance().getAccCodeListByClntId("0006460",CCISAccountPredicate.FLT_AC_ACTIVE);
    	System.out.println(invBgModel.toString());
    	
    }
}