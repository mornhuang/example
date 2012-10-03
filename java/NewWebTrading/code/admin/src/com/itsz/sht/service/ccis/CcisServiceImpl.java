package com.itsz.sht.service.ccis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.Predicate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.itsz.sht.exception.ESAppException;
import com.taifook.mtss.ccis.webservice.impl.AccModel;

/**
 * Please remove com.itsz.eservice.common.exception.ESAppException from implementation
 * @author jdu
 *
 */
public class CcisServiceImpl  implements CcisService{
	
private static CcisServiceImpl instance ;
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	private static synchronized void createInstance() {
        if (instance == null)
            instance = new CcisServiceImpl();
    }
	
	public static CcisServiceImpl getInstance () {
		if (instance == null)
            createInstance();
        return instance;
	}
	
	private CcisServiceImpl() {		
		
	}

	public List<String> getAcCodeListByClntStmtID(String clntStmtId, Predicate filter) {
		long time = System.currentTimeMillis();		
//		List<AccModel> accModelList = CCISUtil.getInstance().getAccCodeListByClntId(clntStmtId,filter);
//		System.out.println("accModelList size=="+accModelList.size());
//		for (int i = 0; i < accModelList.size(); i++) {
//			AccModel accModel=(AccModel)accModelList.get(i);
//			list.add(accModel.getAcStat());
//		}
		List<String> accModelList = CCISUtil.getInstance().getAccCodeListByClntId(clntStmtId,filter);
		logger.info(this.getClass().getName() + ", getAcCodeListByClntStmtID(" + clntStmtId + "," + filter + "), " + (System.currentTimeMillis() - time) + " ;");
	    
		return accModelList;
	}
	
	public String getCNDiscountFlag(String clntId){
		long time = System.currentTimeMillis();
		String cnDiscFlag = CCISUtil.getInstance().getCNDiscountFlag(clntId);
		logger.info(this.getClass().getName() + ", getCNDiscountFlag(), " + (System.currentTimeMillis() - time) + " ;");
		return cnDiscFlag;
	}
	
	public HashMap<String, String> getStmtNotfOptnByClntStmtID(String clntStmtId) {
		long time = System.currentTimeMillis();
		HashMap<String, String> map = CCISUtil.getInstance().getStmtNotfOptn(clntStmtId);
		logger.info(this.getClass().getName() + ", getEStmtNotfOptnByClntStmtID(), " + (System.currentTimeMillis() - time) + " ;");
		return map;
	}
	
	public void updateStmtNotfOptn(String clntStmtId, String emailAddr, String faxNo , String clntName) throws ESAppException {
		long time = System.currentTimeMillis();
		CCISUtil.getInstance().updateStmtNotfOptn(clntStmtId,emailAddr,faxNo , clntName);
		logger.info(this.getClass().getName() + ", updateEstmtNotfOptn(), " + (System.currentTimeMillis() - time) + " ;");
	}
	
	
	
}
