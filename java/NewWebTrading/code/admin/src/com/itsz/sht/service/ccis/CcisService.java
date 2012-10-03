package com.itsz.sht.service.ccis;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.Predicate;

import com.itsz.sht.exception.ESAppException;
/**
 * Please remove com.itsz.eservice.common.exception.ESAppException from implementation
 * @author jdu
 *
 */
public interface CcisService {
	
	/**
	 * 
	 * @param clntStmtId
	 * @param filter 
	 *  please pass into CCISAccountPredicate.FLT_AC_ACTIVE
	 * @return
	 */
	public List<String> getAcCodeListByClntStmtID(String clntStmtId, Predicate filter);

	public String getCNDiscountFlag(String clntId);
	
	public HashMap<String, String> getStmtNotfOptnByClntStmtID(String clntStmtId);
	
	public void updateStmtNotfOptn(String clntStmtId, String emailAddr, String faxNo , String clntName) throws ESAppException;
	
}
