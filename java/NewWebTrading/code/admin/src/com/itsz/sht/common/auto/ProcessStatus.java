/**
 * $Id: ProcessStatus.java,v 1.1 2011/02/26 10:51:28 myang Exp $
 *  Copyright (c) 2004 Tai Fook Securities Group Limited. All rights reserved.
 *  This file contains the valuable properties of Tai Fook Securities Group
 *  Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be reproduced
 *  or distributed in any form or by any means, or stored in a data base or a
 *  retrieval system, without the prior written permission of Tai Fook
 *  Securities Group Limited.
 */
package com.itsz.sht.common.auto;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;

/**
 * @author yhliu
 *
 */
public class ProcessStatus {
    
    private static Map statuses = new HashMap();
    
    
    private String key;
    private Map errors = new Hashtable();
    private boolean processing;
    
    private long startTime;
    private long endTime;
    
    private int totalRecord;
    protected Counters counters = new Counters();
    
    /**
     * @return Returns the totalRecord.
     */
    public int getTotalRecord() {
        return totalRecord;
    }
    /**
     * @param totalRecord The totalRecord to set.
     */
    public void setTotalRecord(int totalRecord) {
        if (totalRecord < 0){
            throw new IllegalArgumentException();
        }
        
        this.totalRecord = totalRecord;
    }
    
    ProcessStatus(String key){
        if (key == null || key.equals(""))
            throw new IllegalArgumentException("Key can not be empty!");
        
        this.key = key;
    }
    
    public static ProcessStatus getStatus(String key){
        return getStatus(key, null);
    }
    
    /**
     * Get a ProcessStatus by the key
     * if the statusCache is not empty, the ProcessStatus will use it to store all status
     * else the ProcessStatus store status in its own fields
     * if no ProcessStatus found, will create one and stored in the cache
     * if the provided statusCache is a HashMap, the statusCache will be discarded.
     * @param key
     * @param statusCache
     * @return
     */
    public static synchronized ProcessStatus getStatus(String key, Map statusCache){
        ProcessStatus status = (ProcessStatus) statuses.get(key);
               
        if (status == null){
            if (statusCache == null || statusCache instanceof HashMap)
                status = new ProcessStatus(key);
            else
                status = new CachedProcessStatus(key, statusCache);
            
            statuses.put(key, status);
        }
        
        return status;
    }
    
    /**
     * @return Returns the processing.
     */
    public synchronized boolean isProcessing() {
        return processing;
    }
    
    /**
     * start process
     */
    public synchronized void start() {
        
        if (isProcessing()){
            throw new IllegalStateException("A thread is processing!");
        }
        
        reset();
        
        setProcessing(true);
        setStartTime(System.currentTimeMillis());
    }
    
    public void end(){
        setProcessing(false);
        setEndTime(System.currentTimeMillis());
    }
    
    public long getElapse(){
        if (isProcessing())
            return System.currentTimeMillis() - getStartTime().getTime();
        else
            return getEndTime().getTime() - getStartTime().getTime();
    }
    
    public String getElapseString(){
        long elapse = getElapse();
        long ms = elapse % 1000;
        long ts = elapse / 1000;
        long s = ts % 60;
        long tm = ts / 60;
        long m = tm % 60;
        long h = tm / 60;
        
        StringBuffer sb = new StringBuffer();
        if (h > 0) sb.append(h).append("h");
        if (m > 0 || h > 0) sb.append(m).append("m");
        if (s > 0 || m > 0 || h>0) sb.append(s).append("s");
        sb.append(ms).append("ms");
        
        return sb.toString();
    }
    
    public long getAverageTime(){
        if (counters.getTotal() == 0)
            return 0;
        
        return getElapse()/counters.getTotal();
    }
    
    /**
     * @return Returns the counters.
     */
    public Counters getCounters() {
        return counters;
    }
    
    public void reset(){
        if (isProcessing())
            throw new IllegalStateException("A thread is processing!");          
        
        counters.clear();
        clearErrors();
        setProcessing(false);
        
        setStartTime(0);
        setEndTime(0);
        
        setTotalRecord(0);
    }
    
    public boolean isReseted(){
        return !isProcessing() && getStartTime().getTime() == 0; 
    }
    
    /**
     * @return Returns the errors.
     */
    public Map getErrors() {
        return new HashMap(errors);
    }
    
    void clearErrors(){
        if (errors == getErrors4Append())
            errors.clear();
        
        Map m = getErrors4Append();
        CollectionUtils.filter(m.values(),
                PredicateUtils.notPredicate(PredicateUtils.instanceofPredicate(Exception.class)));
    }
    
    public Map getErrors4Append(){
        return errors;
    }
    
    /**
     * @return Returns the startTime.
     */
    public Timestamp getStartTime() {
        return new Timestamp(startTime);
    }
    /**
     * @return Returns the endTime.
     */
    public Timestamp getEndTime() {
        return new Timestamp(endTime);
    }

    
    
    private static class CachedProcessStatus extends ProcessStatus{
       
        private static final String  _PROCESSEING = "processing";
        private static final String _STARTTIME =  "startTime";
        private static final String _ENDTIME = "endTime";
        
        private static final String _TOTALRECORD =  "totalRecord";
        
        private Map cache;

        /**
         * @param key
         */
        CachedProcessStatus(String key, Map statusCache) {
            super(key);
            
            if (statusCache == null){
                throw new IllegalArgumentException("hashtable can not be empty!");
            }
            
            this.cache = statusCache;
            
            counters = new Counters(statusCache);
        }
        
        /* (non-Javadoc)
         * @see com.itsz.util.ProcessStatus#getEndTime()
         */
        public Timestamp getEndTime() {
            return new Timestamp(getLongValue(_ENDTIME));
        }
                
        public Map getErrors() {
            Map mapCopy = new HashMap(cache);
            
            CollectionUtils.filter(mapCopy.values(), 
                    PredicateUtils.instanceofPredicate(Exception.class));
            
            return mapCopy;
        }
        
        /* (non-Javadoc)
         * @see com.itsz.util.ProcessStatus#getErrors4Append()
         */
        public Map getErrors4Append() {
            return cache;
        }
        
        /* (non-Javadoc)
         * @see com.itsz.util.ProcessStatus#getStartTime()
         */
        public Timestamp getStartTime() {
            return new Timestamp(getLongValue(_STARTTIME));
        }
        
        /* (non-Javadoc)
         * @see com.itsz.util.ProcessStatus#getTotalRecord()
         */
        public int getTotalRecord() {
            return getIntValue(_TOTALRECORD);
        }
        
        /* (non-Javadoc)
         * @see com.itsz.util.ProcessStatus#isProcessing()
         */
        public boolean isProcessing() {
            return getBooleanValue(_PROCESSEING);
        }
               
        /* (non-Javadoc)
         * @see com.itsz.util.ProcessStatus#setTotalRecord(int)
         */
        public void setTotalRecord(int totalRecord) {
            setValue(_TOTALRECORD, new Integer(totalRecord));
        }
        
        /**
         * @param endTime The endTime to set.
         */
        void setEndTime(long endTime) {
            setValue(_ENDTIME, new Long(endTime));
        }
        
        /**
         * @param processing The processing to set.
         */
        void setProcessing(boolean processing) {
            setValue(_PROCESSEING, Boolean.valueOf(processing));
        }
        
        /**
         * @param startTime The startTime to set.
         */
        void setStartTime(long startTime) {
            setValue(_STARTTIME, new Long(startTime));
        }
       
        private long getLongValue(String key){
            return ((Long) getValue(key, new Long(0))).longValue();
        }
        
        private int getIntValue(String key){
            return ((Integer) getValue(key, new Integer(0))).intValue();
        }
        
        private boolean getBooleanValue(String key){
            return ((Boolean) getValue(key, Boolean.FALSE)).booleanValue();
        }
        
        private Object getValue(String key, Object defValue){
            Object value = cache.get(key);
            return value == null? defValue:value;
        }
        
        private void setValue(String key, Object value){
            cache.put(key, value);
        }
    }
    
    /**
     * @param endTime The endTime to set.
     */
    void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
    /**
     * @param processing The processing to set.
     */
    void setProcessing(boolean processing) {
        this.processing = processing;
    }
    
    /**
     * @param startTime The startTime to set.
     */
    void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
