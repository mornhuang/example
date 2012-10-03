package com.itsz.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

/**
 * A map with string as key and values. This is a shortcut class implemented
 * using generic.
 */
public class StringMap extends HashMap<String, String>
{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param initialCapacity
     * @param loadFactor
     */
    public StringMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * @param initialCapacity
     */
    public StringMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * 
     */
    public StringMap() {
        super();
    }

    /**
     * @param m
     */
    public StringMap(Map<? extends String, ? extends String> m) {
        super(m);
    }

    public StringMap(String pairs, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(pairs, delimiter);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            int eqSign = token.indexOf('=');
            if (eqSign > 0) { // then
                @SuppressWarnings("unused") String value = token.length() > eqSign + 1 ? token
                        .substring(eqSign) : "";
                put(token.substring(0, eqSign), token.substring(eqSign + 1));
            } // end if eqSign > 0
        } // while tokenizer has more elements
    }

    /**
     * Get a value as a string or return a default value. If there is no such
     * key in the map or its value is <code>null</code>, return the default.
     * 
     * @param key
     * @param defaultValue
     */
    public String getAsString(String key, String defaultValue) {
        String s = get(key);
        return s == null ? defaultValue : s;
    }

    /**
     * get the key specified string, if "" return null
     * 
     * @param key
     * @return
     */
    public String getAsStringEmptyNull(String key) {
        String s = get(key);
        if (StringUtils.isEmpty(s)) {
            return null;
        } else {
            return s;
        }
    }
    
    /**
     * get the key specified string, if "" return null
     * 
     * @param key
     * @return
     */
    public Long getAsLong(String key) {
        String s = get(key);
        if (StringUtils.isEmpty(s)) {
            return null;
        } else {
            return Long.parseLong(s);
        }
    }

    /**
     * Get a value as an integer. If there is no such key or its value is not an
     * integer string, return the given default value.
     * 
     * @param key
     * @param defaultValue
     */
    public int getAsInt(String key, int defaultValue) {
        String s = get(key);
        if (StringUtils.isEmpty(s)) { // then
            return defaultValue;
        }
        try {
            int result = Integer.parseInt(s);
            return result;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Get a value as a boolean. If there is no such key or its value is an
     * empty string , return the default value. Othrwise the string value is
     * parsed to a boolean value.
     * 
     * @param key
     * @param defaultValue
     * @see Boolean#parseBoolean(java.lang.String)
     */
    public boolean getAsBoolean(String key, boolean defaultValue) {
        String s = get(key);
        if (StringUtils.isEmpty(s)) { // then
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public Date getAsDate(String key, String pattern) {
        String s = get(key);
        if (StringUtils.isEmpty(s)) { // then
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * convert StringMap to string form, using specified delimiter
     */
    public String toString(String delimiter) {
        StringBuffer sb = new StringBuffer();
        for (String key : keySet()) {
            String s = key + "=" + get(key) + delimiter;
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * copy all String properties from specified map
     * 
     * @param map
     */
    @SuppressWarnings(value = { "unchecked" })
    public void copyFrom(Map map, boolean skipEmpty) {
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            Object value = map.get(key);

            if (key instanceof String && value instanceof String) {
                if (skipEmpty && "".equals(value)) {
                    continue;
                }
                put((String)key, (String)value);
            }
        }
    }
}
