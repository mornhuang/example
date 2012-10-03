package com.itsz.sht.service.qs;

import java.util.List;
import java.util.Vector;
import org.apache.commons.logging.Log;
import com.itsz.rtq.exception.ErrorMsgException;
import com.itsz.rtq.provider.enquiry.EnqHeader;
import com.itsz.rtq.provider.enquiry.EnqProvider;
import com.itsz.rtq.provider.quot.DetailQuot;
import com.itsz.rtq.provider.quot.QUOTProvider;
import com.itsz.rtq.provider.quot.QuotHeader;
import com.itsz.rtq.provider.quot.QuotRecord;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.model.common.ModelUtil;
import com.itsz.sht.common.model.request.EnquireRTQRequestModel;
import com.itsz.sht.common.model.request.LotSizeRequestModel;
import com.itsz.sht.common.model.response.EnquireDeptRTQResponse;
import com.itsz.sht.common.model.response.EnquireRTQResponse;
import com.itsz.sht.common.model.response.EnquireShortRTQResponse;
import com.itsz.sht.common.model.response.LotSizeResponseModel;
import com.itsz.sht.exception.ITSZException;

/**
 * $Id: QsServiceImpl.java,v 1.3 2011/04/13 03:14:08 lpchen Exp $
 * @Project:portal.head
 * @File:RtqServiceImpl.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class QsServiceImpl implements QsService {
	
	Log qsInfo = LoggerFactory.getInstance().getQsInfo();
	
	/**
	 * call lot size
	 * 1.delay
	 * @throws ITSZException
	 */
	public LotSizeResponseModel callLotSize(LotSizeRequestModel lotSizeRequestModel) throws ITSZException {
		LotSizeResponseModel lotSizeResponse = null;
		//get quotHeader data from LotSizeRequestModel
		QuotHeader header = ModelUtil.assembleHeader(lotSizeRequestModel);
		DetailQuot detailQuot = getDetailQuot(header);
		//get LotSizeResponseModel data from detailQuot
		lotSizeResponse = ModelUtil.assembleLotSizeResponse(detailQuot);
		return lotSizeResponse;
	}
	
	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-3-19 11:40:07
	 * @param lotSizeRequestModel
	 * @return
	 */
	public List<EnquireRTQResponse> callEnquireRTQInfo(EnquireRTQRequestModel requestModel) throws ITSZException{
		List<EnquireRTQResponse> enquireRTQResponse = null;
		QuotHeader header = createHeader(requestModel);
		QuotRecord[] quotRecords = getQuotRecords(header);
		enquireRTQResponse = ModelUtil.assembleEnquireRtqResponse(quotRecords);
		return enquireRTQResponse;
	}
	
	public List<EnquireRTQResponse> callSimpleRTQInfo(EnquireRTQRequestModel requestModel) throws ITSZException{
		List<EnquireRTQResponse> enquireRTQResponse = null;
		EnqHeader header = createEnqHeader(requestModel);
		QuotRecord[] detailQuots = getQuotRecords(header);
		enquireRTQResponse = ModelUtil.assembleEnquireRtqResponse(detailQuots);
		return enquireRTQResponse;
	}
	
	public List<EnquireShortRTQResponse> callShortRTQInfo(EnquireRTQRequestModel requestModel) throws ITSZException{
		List<EnquireShortRTQResponse> enquireRTQResponse = null;
		QuotHeader header = createHeader(requestModel);
		DetailQuot[] detailQuots = getDetailQuots(header);
		enquireRTQResponse = ModelUtil.assembleShortRtqResponse(detailQuots);
		return enquireRTQResponse;
	}
	
	public List<EnquireDeptRTQResponse> callDeptRTQInfo(EnquireRTQRequestModel requestModel) throws ITSZException{
		List<EnquireDeptRTQResponse> enquireRTQResponse = null;
		QuotHeader header = createHeader(requestModel);
		DetailQuot[] detailQuots = getDetailQuots(header);
		enquireRTQResponse = ModelUtil.assembleDeptRtqResponse(detailQuots);
		return enquireRTQResponse;
	}

	/**
	 * get lot size -- String
	 * @throws ITSZException
	 */
	public String lotSize(LotSizeRequestModel lotSizeRequestModel){
		String lotsize = null;
        long bt = System.currentTimeMillis();
		try {
			QuotHeader header = ModelUtil.assembleHeader(lotSizeRequestModel);
//			DetailQuot quot = getDetailQuot(header);
			QuotRecord[] quot = getQuotRecords(header);
			if(quot != null){
			    lotsize = quot[0].LotSize;
            }
            LoggerFactory.getInstance().qsInfo(bt , "lotSize");
		} catch (ITSZException lotSizeEx) {
            LoggerFactory.getInstance().qsInfo(bt , "lotSize"+" "+lotSizeEx.getErrorMessage());
		}
		return lotsize;
	}
	
	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-3-19 11:40:07
	 * @param lotSizeRequestModel
	 * @return
	 */
	public String lotSize(EnquireRTQRequestModel reqRequest){
		String lotsize = null;
        long bt = System.currentTimeMillis();
		try {
			QuotHeader header = createHeader(reqRequest);
			DetailQuot quot = getDetailQuot(header);
			if(quot != null){
			    lotsize = quot.Quot.LotSize;
            }
            LoggerFactory.getInstance().qsInfo(bt , "lotSize");
		} catch (ITSZException lotSizeEx) {
            LoggerFactory.getInstance().qsInfo(bt , "lotSize"+" "+lotSizeEx.getErrorMessage());
		}
		return lotsize;
	}
	
	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-3-19 11:40:07
	 * @param lotSizeRequestModel
	 * @return
	 */
	private QuotHeader createHeader(EnquireRTQRequestModel reqRequest){
		QuotHeader header = null;
		if(reqRequest != null){
			header = new QuotHeader();
			header.Host = reqRequest.getChannelType();
			header.SEQN = reqRequest.getSeqn();
			header.Lang = reqRequest.getLanguage();
			header.Type = reqRequest.getType();
			header.ServiceType = reqRequest.getServiceType();
			header.SubType = reqRequest.getSubType();
			header.CustomerID = reqRequest.getCustomerId();
			header.Code = reqRequest.getCode();
			header.DelayTime = reqRequest.getDelayTime();
		}
		return header;
	}

	
	/**
	 * @Author:Arthur
	 * @Time:2011-4-6 11:40:07
	 */
	private EnqHeader createEnqHeader(EnquireRTQRequestModel reqRequest){
		EnqHeader header = null;
		if(reqRequest != null){
			header = new EnqHeader();
			header.Host = reqRequest.getChannelType();
			header.SEQN = reqRequest.getSeqn();
			header.Lang = reqRequest.getLanguage();
			header.Type = reqRequest.getType();
			header.ServiceType = reqRequest.getServiceType();
			header.SubType = reqRequest.getSubType();
			header.CustomerID = reqRequest.getCustomerId();
			header.Code = reqRequest.getCode();
			header.DelayTime = reqRequest.getDelayTime();
		}
		return header;
	}

	
	/**
	 * get quot detail
	 * @Author:Cimenon Saint
	 * @Time:2007-5-30 16:16:00
	 * @param header
	 * @return
	 * @throws ITSZException
	 */
	private DetailQuot getDetailQuot(QuotHeader header) throws ITSZException {
		DetailQuot[] quots = null;
		QUOTProvider provider = new QUOTProvider();
        long bt = System.currentTimeMillis();
		try{
			Object object = provider.processReqMessage(header);
			quots = (DetailQuot[])object;
            LoggerFactory.getInstance().qsInfo(bt , "DetailQuot");
		}catch(ErrorMsgException ex){
			//throw new ITSZException(Consts.Error.Code.ERRORCODE_CONNECT_RTQ , ex.getErrorMsg());
            LoggerFactory.getInstance().qsInfo(bt , "DetailQuot"+" "+ex.getErrorMsg());
		}
		if(quots != null && quots.length > 0){
			return quots[0];
		} else {
			return null;
		}
	}
	
	private DetailQuot[] getDetailQuots(QuotHeader header) throws ITSZException {
		DetailQuot[] quots = null;
		QUOTProvider provider = new QUOTProvider();
        long bt = System.currentTimeMillis();
		try{
			Object object = provider.processReqMessage(header);
			quots = (DetailQuot[])object;
            LoggerFactory.getInstance().qsInfo(bt , "DetailQuot");
		}catch(ErrorMsgException ex){
			//throw new ITSZException(Consts.Error.Code.ERRORCODE_CONNECT_RTQ , ex.getErrorMsg());
            LoggerFactory.getInstance().qsInfo(bt , "DetailQuot"+" "+ex.getErrorMsg());
		}
		if(quots != null && quots.length > 0){
			return quots;
		} else {
			return null;
		}
	}
	
	
	/**
	 * get enq list detail
	 * @Author:Arthur
	 * @Time:2011-4-6 11:40:07
	 * @param header
	 * @return
	 * @throws ITSZException
	 */
	private QuotRecord[] getQuotRecords(EnqHeader header) throws ITSZException {
		QuotRecord[] qRecords = null;
		EnqProvider provider = new EnqProvider();
        long bt = System.currentTimeMillis();
		try{
			Object object = provider.processReqMessage(header);
			qRecords = (QuotRecord[])object;
            LoggerFactory.getInstance().qsInfo(bt , "QuotRecord");
		}catch(ErrorMsgException ex){
			//throw new ITSZException(Consts.Error.Code.ERRORCODE_CONNECT_RTQ , ex.getErrorMsg());
            LoggerFactory.getInstance().qsInfo(bt , "QuotRecord"+" "+ex.getErrorMsg());
		}
		catch(Exception ex){
			//throw new ITSZException(Consts.Error.Code.ERRORCODE_CONNECT_RTQ , ex.getErrorMsg());
	        LoggerFactory.getInstance().qsInfo(bt , "QuotRecord"+" "+ex.getMessage());
		}
		if(qRecords != null && qRecords.length > 0){
			return qRecords;
		} else {
			return null;
		}
	}
	
	
	/**
	 * get quot list detail
	 * @Author:kyzou
	 * @Time:2008-3-19 11:40:07
	 * @param header
	 * @return
	 * @throws ITSZException
	 */
	private QuotRecord[] getQuotRecords(QuotHeader header) throws ITSZException {
		QuotRecord[] qRecords = null;
		QUOTProvider provider = new QUOTProvider();
        long bt = System.currentTimeMillis();
		try{
			Object object = provider.processReqMessage(header);
			qRecords = (QuotRecord[])object;
            LoggerFactory.getInstance().qsInfo(bt , "QuotRecord");
		}catch(ErrorMsgException ex){
			//throw new ITSZException(Consts.Error.Code.ERRORCODE_CONNECT_RTQ , ex.getErrorMsg());
            LoggerFactory.getInstance().qsInfo(bt , "QuotRecord"+" "+ex.getErrorMsg());
		}
		catch(Exception ex){
			//throw new ITSZException(Consts.Error.Code.ERRORCODE_CONNECT_RTQ , ex.getErrorMsg());
	        LoggerFactory.getInstance().qsInfo(bt , "QuotRecord"+" "+ex.getMessage());
		}
		if(qRecords != null && qRecords.length > 0){
			return qRecords;
		} else {
			return null;
		}
	}
	
	/**
	 * judge the order quantify whether in lot size rule or not
	 */
	public boolean inLotSizeRule(int orderQuantity , int lotSize){
		boolean result = Consts.Global.Boolean.TRUE;
		if((lotSize != 0) && (orderQuantity % lotSize != 0)){
			result = Consts.Global.Boolean.FALSE;
		}
		return result;
	}
	
	/**
	 * whether exceed max lot
	 */
	public boolean isExceedMaxLot(int orderQuantity , int lotSize){
		boolean result = Consts.Global.Boolean.FALSE;
		if((orderQuantity > lotSize*Consts.Qs.MAX_LOT) && (lotSize > 0)){
			result = Consts.Global.Boolean.TRUE;
		}
		return result;
	}
	
    public static void main(String[] args) {
		QsServiceImpl impl = new QsServiceImpl();
		LotSizeRequestModel model = new LotSizeRequestModel();
		model.setChannelType(Consts.Channel.STT);
		model.setSeqn("1008");
		model.setLanguage(Consts.Global.Language.ENG);
		model.setType("ENQ");
		model.setSubType("SRFP"); 
		model.setCustomerId("Test08");
		model.setServiceType("OK");
		Vector vector = new Vector();
		vector.add("0001");
		model.setCode(vector);
		model.setDelayTime("60");
		String lotsize = impl.lotSize(model);
		System.out.println("lotsize..." + lotsize);

//	    QuotHeader header = new QuotHeader();
//        header.Host = "STT";
//        header.SEQN = "1008";
//        header.Lang = "ENG";
//        header.Type = "QUOT";
//        header.SubType = "SRFP";
//        header.CustomerID = "Test08";
//        header.ServiceType = "OK";
//        header.Code.addElement("0001");
//        header.DelayTime = "60";
//    
//        DetailQuot[] q_record8 = null;
//        try {
//        	q_record8 = QsServiceImpl.getDetailQuot(header);
//        	//q_record8 = (DetailQuot[]) q_provider8.processReqMessage(header);
//          System.out.println("-------------------------------------");
//          for(int i=0;i<q_record8.length;i++) {
//            Date time = new Date(Long.parseLong(q_record8[i].Quot.Time));
//            System.out.println(time.toString() + "\t" + q_record8[i].Quot.Name + "\t" + q_record8[i].Quot.Note + "\t" + q_record8[i].Quot.PClose);
//            System.out.println(q_record8[i].Quot.Open + "\t" + q_record8[i].Quot.High + "\t" + q_record8[i].Quot.Low + "\t" + q_record8[i].Quot.Close);
//            System.out.println(q_record8[i].Quot.Volume + "\t" + q_record8[i].Quot.Turnover + "\t" + q_record8[i].Quot.Change + "\t" + q_record8[i].Quot.PctChange);
//            System.out.println("+++++++++++++++++++++++++++++++++++++");
//            System.out.println(q_record8[i].Depth.APRC[0] + "\t" + q_record8[i].Depth.APRC[1] + "\t" + q_record8[i].Depth.APRC[2] + "\t" + q_record8[i].Depth.APRC[3] + "\t" + q_record8[i].Depth.APRC[4]);
//            System.out.println(q_record8[i].Depth.ASIZ[0] + "\t" + q_record8[i].Depth.ASIZ[1] + "\t" + q_record8[i].Depth.ASIZ[2] + "\t" + q_record8[i].Depth.ASIZ[3] + "\t" + q_record8[i].Depth.ASIZ[4]);
//            System.out.println(q_record8[i].Depth.ASKQ[0] + "\t" + q_record8[i].Depth.ASKQ[1] + "\t" + q_record8[i].Depth.ASKQ[2] + "\t" + q_record8[i].Depth.ASKQ[3] + "\t" + q_record8[i].Depth.ASKQ[4]);
//            System.out.println(q_record8[i].Depth.BPRC[0] + "\t" + q_record8[i].Depth.BPRC[1] + "\t" + q_record8[i].Depth.BPRC[2] + "\t" + q_record8[i].Depth.BPRC[3] + "\t" + q_record8[i].Depth.BPRC[4]);
//            System.out.println(q_record8[i].Depth.BSIZ[0] + "\t" + q_record8[i].Depth.BSIZ[1] + "\t" + q_record8[i].Depth.BSIZ[2] + "\t" + q_record8[i].Depth.BSIZ[3] + "\t" + q_record8[i].Depth.BSIZ[4]);
//            System.out.println(q_record8[i].Depth.BIDQ[0] + "\t" + q_record8[i].Depth.BIDQ[1] + "\t" + q_record8[i].Depth.BIDQ[2] + "\t" + q_record8[i].Depth.BIDQ[3] + "\t" + q_record8[i].Depth.BIDQ[4]);
//            System.out.println("+++++++++++++++++++++++++++++++++++++");
//          }
//        }
//        catch (ITSZException ex) {
//          System.out.println("ErrorMsgException: " + "Code=" + ex.getErrorCode() + " Msg=" + ex.getErrorMessage());
//        }
	}


}
