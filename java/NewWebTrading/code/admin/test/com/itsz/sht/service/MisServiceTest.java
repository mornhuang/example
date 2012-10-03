package com.itsz.sht.service;


import junit.framework.TestCase;

import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.service.mis.MisService;

public class MisServiceTest extends TestCase {

	private MisService misService;
	
	protected void setUp() throws Exception {
		super.setUp();
		misService = (MisService)ServiceLocator.getInstance().getService("misService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		misService = null;
	}

	public void testAccBalance() throws ITSZException {
		System.out.println(misService.getAccBalance("02-0087001-33"));
	}
}
