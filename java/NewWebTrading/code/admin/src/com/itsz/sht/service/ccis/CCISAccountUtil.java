package com.itsz.sht.service.ccis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>com.itsz.eservice.common.application.util</p>
 * <p>CCISAccountUtil.java</p>
 *
 * <p>Description: This file contains the valuable properties of iT Technology(Shenzhen)Co.,Ltd
 * embodying substantial creative efforts and confidential  information,
 * ideas and expressions. No part of this file may  be  reproduced 
 * or distributed in any form or by any means, or stored  in a
 * data base or a retrieval system, without the prior written  permission of
 * iT Technology(Shenzhen)Co.,Ltd.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: iT Technology(Shenzhen)Co.,Ltd</p>
 *
 * @author xchen
 * @version 1.0
 */
public class CCISAccountUtil {

	public static String acMatcher(String accode){
		String regEx = "[0-9]{2}\\-[0-9]{7}\\-[0-9]{2}";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(accode);
		if(m.matches()){
			return m.group();
		}else{
			return "";
		}
	}
	
	public static final String checkACType(String ac) {
        if (ac.indexOf(CCISAccountConstants.ACTypeDetail.FUTURES) == 0) {
            return CCISAccountConstants.ACType.FUTURES;
        }

        if (((ac.indexOf(CCISAccountConstants.ACTypeDetail.SECURITIES) == 0))
                && ((ac.indexOf(CCISAccountConstants.ACTypeDetail.ONLINE_CASH) == (ac
                        .length() - CCISAccountConstants.ACTypeDetail.ONLINE_CASH
                        .length())) || (ac
                        .lastIndexOf(CCISAccountConstants.ACTypeDetail.ONLINE_MARGIN) == (ac
                        .length() - CCISAccountConstants.ACTypeDetail.ONLINE_MARGIN
                        .length())))) {
            return CCISAccountConstants.ACType.SECURITIES_ONLINE;
        }

        if (((ac.indexOf(CCISAccountConstants.ACTypeDetail.SECURITIES) == 0))
                && ((ac.lastIndexOf(CCISAccountConstants.ACTypeDetail.OFFLINE_CASH) == (ac
                        .length() - CCISAccountConstants.ACTypeDetail.OFFLINE_CASH
                        .length())) || (ac
                        .lastIndexOf(CCISAccountConstants.ACTypeDetail.OFFLINE_MARGIN) == (ac
                        .length() - CCISAccountConstants.ACTypeDetail.OFFLINE_MARGIN
                        .length())))) {
            return CCISAccountConstants.ACType.SECURITIES_OFFLINE;
        }

        return CCISAccountConstants.ACType.SECURITIES_ONLINE;

    }
}
