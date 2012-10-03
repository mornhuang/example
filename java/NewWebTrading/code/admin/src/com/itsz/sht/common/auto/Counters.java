// $Id: Counters.java,v 1.1 2011/02/26 10:51:28 myang Exp $

package com.itsz.sht.common.auto;

import java.io.Serializable;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;

/**
 * <p>Title: eService III</p>
 * <p>Description: eService RTQ,Price Alert</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: TaiFook</p>
 * @author yhliu
 * @version 1.0
 */

public class Counters implements Serializable{
    
    private static final String CLASS = "Counters";
    
    private Map map;
    
    /**
     * @see Counters(Map)
     */
    private boolean initByMap = false;
    
    public Counters() {
        map = new HashMap();
    }
    
    /**
     * if the Counters is initiated with a map
     * every time a counter's value changed, a new Counter will be put to the cache
     * the store key will be append <code>CLASS</code> at head
     * @see genNewKey
     * @see setValue
     */
    public Counters(Map map){
        if (map == null){
            throw new NullPointerException();
        }
        
        this.map = map;
        this.initByMap = true;
    }
    
    static final Predicate PRD_VALUECOUNTER = new Predicate(){

        public boolean evaluate(Object arg0) {
            if (arg0 instanceof Map.Entry){
                Map.Entry item = (Map.Entry) arg0;
                return item.getValue() instanceof Counter;
            }else
                return arg0 instanceof Counter;
        }
        
    };
    
    public void clear(){
        if (initByMap){
            CollectionUtils.filter(map.entrySet(),
                    PredicateUtils.notPredicate(PRD_VALUECOUNTER));
        }else
            map.clear();
    }

    public synchronized int increment(String key) {
        return setValue(key, getValue(key) + 1);
    }

    public synchronized int decrement(String key) {
        return setValue(key, getValue(key) - 1);
    }

    public synchronized int initCounter(String key, int value)throws RuntimeException{
        Counter counter = getCounter(key);
        if (counter != null)
            throw new RuntimeException("The Counter for " + key + " exists.");
        return setValue(key, value);
    }

    public int getValue(String key) {
        Counter counter = getCounter(key);
        if (counter == null)
            return 0;
        else
            return counter.getValue();
    }

    private Counter getCounter(String key){
        return (Counter)map.get(genNewKey(key));
    }
    
    private void setCounter(String key, Counter counter){
        map.put(genNewKey(key), counter);        
    }
    
    private String genNewKey(String key){
        if (key == null || key.length() == 0){
            throw new NullPointerException("Null counter key");
        }
        if (initByMap){
            return CLASS + key;
        }
        return key;
    }
    
    private String getOriginKey(String newKey){
        if (initByMap){
            return newKey.substring(CLASS.length());
        }
        return newKey;
    }
        
    private int setValue(String key, int value){
        Counter counter;
        if (initByMap){
            counter = new Counter();
        }else{
            counter = getCounter(key);
            if (counter == null) {
                counter = new Counter();
            }
        }
        counter.setValue(value);
        setCounter(key, counter);
        return counter.getValue();
    }

    public String toString(){
        StringBuffer buf = new StringBuffer();
        for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry item = (Map.Entry)iter.next();
            String key = (String) item.getKey();        
            if (item.getValue() instanceof Counter == false){
                continue;
            }
            Counter counter = (Counter) item.getValue();
            buf.append("<").append(getOriginKey(key)).append(": ").append(counter.getValue()).append(">");
        }
        return buf.toString();
    }

    public int getTotal(){
        int total = 0;
        for (Iterator iter = map.values().iterator(); iter.hasNext(); ) {
            Object obj = iter.next();
            
            if (obj instanceof Counter == false){
                continue;
            }
            
            Counter item = (Counter)obj;
            total += item.getValue();
        }
        return total;
    }
}