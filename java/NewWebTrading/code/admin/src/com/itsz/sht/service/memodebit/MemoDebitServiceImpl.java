package com.itsz.sht.service.memodebit;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;

import com.itsz.sht.common.Consts;
import com.itsz.sht.service.mis.MisService;
import com.itsz.sht.service.msse.MsseResult;
import com.itsz.sht.service.msse.MsseService;
import com.taifook.framework.platform.persist.DBException;
import com.taifook.misgateway.TransactionResult;
import com.taifook.mtss.mss.webservice.ClientFundMemo;
import com.taifook.mtss.mss.webservice.ErrorInfoException;
import com.taifook.mtss.mss.webservice.FundChannel;
import com.taifook.mtss.mss.webservice.Language;
import com.taifook.mtss.mss.webservice.RemarkForStatementInfo;

public class MemoDebitServiceImpl implements MemoDebitService {

	private String memoDebitSystem = "MSSE"; // memo debit时调用的系统，MSSE|MIS|MANUAL手动方式，默认为MSSE
	private MisService misService;
	private MsseService msseService;

	private String HKD = "HKD";

	public String getMemoDebitSystem() {
		return memoDebitSystem;
	}

	public void setMemoDebitSystem(String memoDebitSystem) {
		this.memoDebitSystem = memoDebitSystem;
	}

	public void setMisService(MisService misService) {
		this.misService = misService;
	}

	public void setMsseService(MsseService msseService) {
		this.msseService = msseService;
	}

	public BigDecimal getAccBalance(String acCode) throws DBException,
			RemoteException, ErrorInfoException, ServiceException {
		if (memoDebitSystem.equals(Consts.AdminPortal.memoDebitSystem.MSSE)) {
			return msseService.getBalance(acCode, HKD);
		} else if (memoDebitSystem.equals(Consts.AdminPortal.memoDebitSystem.MIS)) {
			return misService.getAccBalance(acCode);
		}
		return null;
	}

	public int balanceCheck(String acCode, long check) throws DBException,
			RemoteException, ErrorInfoException, ServiceException {
		if (!Consts.AdminPortal.memoDebitSystem.MANUAL.equals(memoDebitSystem)) {
			BigDecimal balance = this.getAccBalance(acCode);
			if (balance.longValue() >= check) {
				return 1; // 余额足够支付
			} else {
				return -1; // 余额不足
			}
		} else {
			return 0; // 不用检查余额
		}
	}

	public MemoDebitResult memoDebit(final String acCode,
			final BigDecimal money, final String memoCodeType,
			final String remark, final String language) throws DBException,
			RemoteException, ServiceException {
		MemoDebitResult result = new MemoDebitResult();
		if (memoDebitSystem.equals(Consts.AdminPortal.memoDebitSystem.MSSE)) {
			ClientFundMemo memo = new ClientFundMemo();
			memo.setAccountStmtId(acCode);
			memo.setCurrencyCode(HKD);
			memo.setAmount(money);
			memo.setFundChannelCode(FundChannel.ESERVICES);// New Web Trading channel
			memo.setIsForceDebit(true); // 余额不足时，是否强制扣款
			RemarkForStatementInfo[] remarkInfo = new RemarkForStatementInfo[1];
			remarkInfo[0] = new RemarkForStatementInfo();
			remarkInfo[0].setIsPrimary(true);
			Language lang = Language.ZH_TW;
			if (language != null) {
				if (language.equals("ENG")) {
					lang = Language.EN;
				} else if (language.equals("GS")) {
					lang = Language.ZH_CN;
				} else if (language.equals("BIG5")) {
					lang = Language.ZH_TW;
				}
			}
			remarkInfo[0].setLangCode(lang); // Language.EN 是msse api中提供
			remarkInfo[0].setRemarkForStatement(remark); // 可为空
			// memo.setSegregatedFundCode("HK"); //默认为HK
			memo.setRemarkForStatementInfoList(remarkInfo);
			memo.setTransactionTypeCode("RTQ_FEE_MD");//RTQ_SUBSCRIPTION
			memo.setValueDate(Calendar.getInstance());
			MsseResult msseResult = msseService.memoDebit(memo);
			result.setCompleted(msseResult.isCompleted());
			result.setResultCode(msseResult.getResultCode());
			result.setErrorList(msseResult.getErrorList());
		} else if (memoDebitSystem.equals(Consts.AdminPortal.memoDebitSystem.MIS)) {
			TransactionResult misResult = misService.memoDebit(acCode, money, memoCodeType, remark);
			result.setCompleted(misResult.getIsCompleted());
			result.setResultCode(misResult.getReference());
			result.setErrorMessage(misResult.getTransactionMessage());
		} else {
			result.setCompleted(true);
			result.setResultCode(Consts.AdminPortal.memoDebitSystem.MANUAL);
		}
		return result;
	}

}
