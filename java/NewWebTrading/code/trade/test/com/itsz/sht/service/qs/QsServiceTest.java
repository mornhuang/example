package com.itsz.sht.service.qs;

import java.util.Vector;

import junit.framework.TestCase;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.common.model.request.LotSizeRequestModel;

public class QsServiceTest extends TestCase {
	
	private QsService qsService;

	protected void setUp() throws Exception {
		super.setUp();
		qsService = (QsService)ServiceLocator.getInstance().getService("qsService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.qsService = null;
	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.qs.QsService.callLotSize(LotSizeRequestModel)'
	 */
	public void testCallLotSize() {
		
	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.qs.QsService.lotSize(LotSizeRequestModel)'
	 */
	public void testLotSize() {
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
		String lotSize = qsService.lotSize(model);
		assertNotNull(lotSize);
	}

	/*
	 * Test method for 'com.itsz.channelsserver.service.qs.QsService.inLotSizeRule(PrePlaceOrderRequestModel)'
	 */
	public void testInLotSizeRule() {

	}

}
