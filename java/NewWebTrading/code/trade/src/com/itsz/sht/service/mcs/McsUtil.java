package com.itsz.sht.service.mcs;

import java.math.BigDecimal;


/**
 * $Id: McsUtil.java,v 1.1 2010/11/09 03:57:35 kyzou Exp $
 * @Project:portal.head
 * @File:McsUtil.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class McsUtil {
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:15:54:12
	 * @param oldQuantity
	 * @param newQuantity
	 * @param price
	 * @return
	 */
	public static Integer getDelta(BigDecimal initialQuantity, BigDecimal newQuantity, BigDecimal changedQty){
		return Integer.valueOf(-(initialQuantity.intValue() + changedQty.intValue() - newQuantity.intValue()));	   
	}
}
