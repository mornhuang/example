/*
 * Created on 2005-4-1
 *
 */
package com.itsz.sht.common.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.service.util.ServiceManagement;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.model.response.MISAccountListResponseModel;
import com.taifook.mcs.core.beans.msg.AccountDetail;
import com.taifook.mcs.core.beans.msg.Exception01;
import com.taifook.mcs.core.beans.msg.MCSMessage;
import com.taifook.mcs.core.beans.msg.MISAccountEnquiryResponse;
import com.taifook.mcs.core.beans.msg.MISAccountListResponse;

/**
 * @author wzzhan
 *
 */
public class TradeInfoObject
    implements Serializable {
    private String custCode;
    //private String accountID;
    private String marketCode = "AMS3";
    private String tradingAccount; // �����˺�
    private String allowTradeStatusFlag; //�ɽ��ױ�־
    private Collection AcEnquiryList; //�˻�list
    private OrderDetailInfo orderDetailInfo; //���µ�order���飬ÿ�β�ѯ����ʱ������
    //private Collection transFromAccountList;
    private Collection transToCommonAccountList; //ת��ʱ��ת�����ͨ�˻�
    private Map transToBankAccountMap; //ת��ʱ��ת��������˻�����accoutΪkey,valueΪbank code
    private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_MCS);
	private MISAccountListResponse misAccountList; //交易帐户列表
	
	public MISAccountListResponse getMisAccountList() {
		return misAccountList;
	}
	public void setMisAccountList(MISAccountListResponse misAccountList) {
		this.misAccountList = misAccountList;
	}

    /**
     * @return Returns the acEnquiryList.
     */
    public Collection getAcEnquiryList(HttpServletRequest request) {
        return AcEnquiryList;
    }

    /**
     * @param acEnquiryList The acEnquiryList to set.
     */
    public void setAcEnquiryList(Collection acEnquiryList) {
        AcEnquiryList = acEnquiryList;
    }

    /**
     * @return Returns the orderDetailInfo.
     */
    public OrderDetailInfo getOrderDetailInfo() {
        return orderDetailInfo;
    }

    /**
     * @param orderDetailInfo The orderDetailInfo to set.
     */
    public void setOrderDetailInfo(OrderDetailInfo orderDetailInfo) {
        this.orderDetailInfo = orderDetailInfo;
    }

    /**
     * @return Returns the marketCode.
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * @param marketCode The marketCode to set.
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    /**
     * @return Returns the allowTradeStatusFlag.
     */
    public String getAllowTradeStatusFlag() {
        return allowTradeStatusFlag;
    }

    /**
     * @param allowTradeStatusFlag The allowTradeStatusFlag to set.
     */
    public void setAllowTradeStatusFlag(String allowTradeStatusFlag) {
        this.allowTradeStatusFlag = allowTradeStatusFlag;
    }

    /**
     * @return Returns the tradingAccount.
     */
    public String getTradingAccount() {
        return tradingAccount;
    }

    /**
     * @param tradingAccount The tradingAccount to set.
     */
    public void setTradingAccount(String tradingAccount) {
        this.tradingAccount = tradingAccount;
    }

    /**
     * @return Returns the custCode.
     */
    public String getCustCode() {
        return custCode;
    }

    /**
     * @param custCode The custCode to set.
     */
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    /**
     * @return Returns the transToCommonAccountList.
     */
    public Collection getTransToCommonAccountList() {
        return transToCommonAccountList;
    }

    /**
     * @param transToCommonAccountList The transToCommonAccountList to set.
     */
    public void setTransToCommonAccountList(Collection transToCommonAccountList) {
        this.transToCommonAccountList = transToCommonAccountList;
    }

    /**
     * @return Returns the transToBankAccountMap.
     */
    public Map getTransToBankAccountMap() {
        return transToBankAccountMap;
    }

    /**
     * @param transToBankAccountMap The transToBankAccountMap to set.
     */
    public void setTransToBankAccountMap(Map transToBankAccountMap) {
        this.transToBankAccountMap = transToBankAccountMap;
    }
}
