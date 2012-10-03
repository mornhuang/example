package com.itsz.sht.service.qs;

import java.util.List;

import com.itsz.sht.common.model.request.EnquireRTQRequestModel;
import com.itsz.sht.common.model.request.LotSizeRequestModel;
import com.itsz.sht.common.model.response.EnquireDeptRTQResponse;
import com.itsz.sht.common.model.response.EnquireRTQResponse;
import com.itsz.sht.common.model.response.EnquireShortRTQResponse;
import com.itsz.sht.common.model.response.LotSizeResponseModel;
import com.itsz.sht.exception.ITSZException;

/**
 * $Id: QsService.java,v 1.1 2010/11/09 03:57:35 kyzou Exp $
 * @Project:portal.head
 * @File:RtqService.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public interface QsService {
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:16:11:20
	 * @param lotSizeRequestModel
	 * @return
	 */
	public LotSizeResponseModel callLotSize(LotSizeRequestModel lotSizeRequestModel) throws ITSZException ;
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-31 13:51:04
	 * @param lotSizeRequestModel
	 * @return
	 * @throws ITSZException
	 */
	public String lotSize(LotSizeRequestModel lotSizeRequestModel);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-31 16:55:51
	 * @param lotSizeRequestModel
	 * @return
	 * @throws ITSZException
	 */
	public boolean inLotSizeRule(int orderQuantity , int lotSize);
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-1 11:49:32
	 * @param orderQuantity
	 * @param lotSize
	 * @return
	 */
	public boolean isExceedMaxLot(int orderQuantity , int lotSize);

	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-3-06
	 * @param 
	 * @return
	 */	  
	public List<EnquireRTQResponse> callEnquireRTQInfo(EnquireRTQRequestModel requestModel) throws ITSZException;
	public String lotSize(EnquireRTQRequestModel reqRequest);
	public List<EnquireRTQResponse> callSimpleRTQInfo(EnquireRTQRequestModel requestModel) throws ITSZException;
	public List<EnquireShortRTQResponse> callShortRTQInfo(EnquireRTQRequestModel requestModel) throws ITSZException;
	public List<EnquireDeptRTQResponse> callDeptRTQInfo(EnquireRTQRequestModel requestModel) throws ITSZException;
}
