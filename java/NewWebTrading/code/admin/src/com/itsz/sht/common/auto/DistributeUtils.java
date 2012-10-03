/**
 *  $Id: DistributeUtils.java,v 1.1 2011/02/26 10:51:28 myang Exp $
 * 
 *  Copyright (c) 2004 Tai Fook Securities Group Limited. All rights reserved.
 *  This file contains the valuable properties of Tai Fook Securities Group
 *  Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be reproduced
 *  or distributed in any form or by any means, or stored in a data base or a
 *  retrieval system, without the prior written permission of Tai Fook
 *  Securities Group Limited.
 */
package com.itsz.sht.common.auto;

import java.util.HashMap;
import java.util.Map;

//import org.jgroups.ChannelException;
//import org.jgroups.blocks.DistributedHashtable;

//import com.taifook.eservice.common.application.constants.ApplicationConstants.*;
import com.taifook.framework.foundation.logging.Logger;
import com.taifook.framework.foundation.logging.LoggerFactory;

/**
 * @author yhliu
 *
 */
public class DistributeUtils {
    
    private static Logger logger = LoggerFactory.instance().getLogger(DistributeUtils.class);
    
    private static Map htCache = new HashMap();
    
//    private static final String KEY_CHANNEL_PREFIX = "//distribute/channel_prefix/text()";
//    private static final String KEY_ENABLE = "//distribute/enable/text()";
//    
    private DistributeUtils(){};
    
    public static Map getDistributedHashtable(String key){
        
 //       String prefix = MgtConfigUtil.getValue(KEY_CHANNEL_PREFIX);
        
  //      key = prefix + key;
      
 //       boolean enable = isDistributeEnabled();
        
        try {
            synchronized (htCache) {
                Map ht = (Map) htCache.get(key);
                if (ht == null){
//                    if (enable){
//                        ht = new DistributedHashtable(key, null, ConfigManager.getConfRealPath(
//                                ConfigFile._JGROUP_STATE_TRSFER), 5000);                        
//                    }else{
                        ht = new HashMap();
                 //   }
                    htCache.put(key, ht);
                }
                
                return ht;
            }            
        } catch (Exception e) {
            if (logger.isErrorEnabled()){
                logger.error("Unable to get a DistributedHashtable", e);
            }
            throw new RuntimeException(e);
        }
    }
    
//    private static boolean isDistributeEnabled(){
//        String strBl = MgtConfigUtil.getValue(KEY_ENABLE);        
//        try {
//            return Boolean.valueOf(strBl).booleanValue();
//        } catch (Exception e){
//            return false;
//        }        
//    }
    
//    public static void main(String[] args) throws Exception {
//        String prefix = MgtConfigUtil.getValue(KEY_CHANNEL_PREFIX);
//        
//        System.out.println(prefix);
//        
//        Map map1 = getDistributedHashtable("test");
//        Map map2 = new DistributedHashtable(prefix + "test", null, ConfigManager.getConfRealPath(
//                ConfigFile._JGROUP_STATE_TRSFER), 5000);
//        
//        map1.put("hello", "world");
//        
//        System.out.println(map1);
//        System.out.println(map2);
//    }
}
