package cn.itsz.newsim.manage;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import cn.itsz.newsim.common.ActionUtil;
import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dto.request.EnquireRTQRequest;
import cn.itsz.newsim.dto.response.entity.EnquireRTQResponseEntity;
import cn.itsz.newsim.dto.response.entity.EnquireShortRTQResponseEntity;
import cn.itsz.newsim.exception.ITSZException;

import com.itsz.rtq.exception.ErrorMsgException;
import com.itsz.rtq.provider.quot.DetailQuot;
import com.itsz.rtq.provider.quot.QUOTProvider;
import com.itsz.rtq.provider.quot.QuotHeader;
import com.itsz.rtq.provider.quot.QuotRecord;

@Component
public class QsManager {

	private static Log logger = LogFactory.getLog(QsManager.class);

	private QuotRecord[] getQuotRecords(QuotHeader header) {
		QuotRecord[] qRecords = null;
		QUOTProvider provider = new QUOTProvider();
		try {
			qRecords = (QuotRecord[]) provider.processReqMessage(header);
		} catch (ErrorMsgException ex) {
			logger.error("ErrorMsgException {}", ex);
			ITSZException itszException = new ITSZException();
			itszException.setErrorCode(Constants.ErrorKey.NSIM_00003);
			throw itszException;
		} catch (Exception ex) {
			logger.error("Exception {}", ex);
			ITSZException itszException = new ITSZException();
			itszException.setErrorCode(Constants.ErrorKey.NSIM_00004);
			throw itszException;
		}
		if (qRecords != null && qRecords.length > 0) {
			return qRecords;
		} else {
			return null;
		}
	}

	private DetailQuot[] getDetailQuots(QuotHeader header) {
		DetailQuot[] quots = null;
		QUOTProvider provider = new QUOTProvider();
		try {
			quots = (DetailQuot[]) provider.processReqMessage(header);
		} catch (ErrorMsgException ex) {
			logger.error("ErrorMsgException {}", ex);
			ITSZException itszException = new ITSZException();
			itszException.setErrorCode(Constants.ErrorKey.NSIM_00003);
			throw itszException;
		} catch (Exception ex) {
			logger.error("Exception {}", ex);
			ITSZException itszException = new ITSZException();
			itszException.setErrorCode(Constants.ErrorKey.NSIM_00004);
			throw itszException;
		}
		if (quots != null && quots.length > 0) {
			return quots;
		} else {
			return null;
		}
	}

	public List<EnquireRTQResponseEntity> callSimpleRTQInfo(EnquireRTQRequest requestModel) {
		//xwquan 2011-04-14
		//requestModel.setQuoteType(Constants.QS.S_TYPE_DELAY);
		requestModel.setQuoteType(Constants.QS.S_BASIC_MARKET_PRICE);//实时价格
		QuotHeader header = createHeader(requestModel);
		QuotRecord[] quotRecords = getQuotRecords(header);
		List<EnquireRTQResponseEntity> enquireRTQResponse = null;
		if (quotRecords != null) {
			enquireRTQResponse = ActionUtil.assembleSimpleRtqResponse(quotRecords);
		}
		return enquireRTQResponse;
	}

	public List<EnquireRTQResponseEntity> callEnquireRTQInfo(EnquireRTQRequest requestModel) {
		requestModel.setQuoteType(Constants.QS.S_TYPE_DETAIL_QUOT_DELAY);
		QuotHeader header = createHeader(requestModel);
		DetailQuot[] detailQuots = getDetailQuots(header);
		List<EnquireRTQResponseEntity> enquireRTQResponse = ActionUtil.assembleEnquireRtqResponse(detailQuots);
		return enquireRTQResponse;
	}

	public List<EnquireShortRTQResponseEntity> callShortRTQInfo(EnquireRTQRequest requestModel) {
		//xwquan 2011-04-14
		//requestModel.setQuoteType(Constants.QS.S_TYPE_DELAY);
		requestModel.setQuoteType(Constants.QS.S_BASIC_MARKET_PRICE);//实时价格
		QuotHeader header = createHeader(requestModel);
		QuotRecord[] quotRecords = getQuotRecords(header);
		List<EnquireShortRTQResponseEntity> enquireRTQResponse = null;
		if (quotRecords != null) {
			enquireRTQResponse = ActionUtil.assembleShortRtqResponse(quotRecords);
		}
		return enquireRTQResponse;
	}

	
	public List<EnquireRTQResponseEntity> callBmpRTQInfo(EnquireRTQRequest requestModel) {
		requestModel.setQuoteType(Constants.QS.S_BASIC_MARKET_PRICE);
		QuotHeader header = createHeader(requestModel);
		QuotRecord[] quotRecords = getQuotRecords(header);
		List<EnquireRTQResponseEntity> enquireRTQResponse = null;
		if (quotRecords != null) {
			enquireRTQResponse = ActionUtil.assembleSimpleRtqResponse(quotRecords);
		}
		return enquireRTQResponse;
	}
	
	private QuotHeader createHeader(EnquireRTQRequest reqRequest) {
		QuotHeader header = null;
		if (reqRequest != null) {
			header = new QuotHeader();
			header.Host = reqRequest.getChannelType();
			header.SEQN = Constants.QS.SEQN;
			header.Lang = reqRequest.getLanguage();
			header.Type = Constants.QS.QUOT_TYPE;
			header.ServiceType = Constants.QS.SERVICETYPE;
			header.SubType = reqRequest.getQuoteType();
			header.CustomerID = reqRequest.getLoginId();
			header.Code = reqRequest.getInstrCode();
		}
		return header;
	}
}
