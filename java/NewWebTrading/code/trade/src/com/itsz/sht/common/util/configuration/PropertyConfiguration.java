/**
 *  Copyright (c) 2002 Tai Fook Securities Group Limited. All rights reserved.
 *  This file contains the valuable properties of Tai Fook Securities Group
 *  Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be reproduced
 *  or distributed in any form or by any means, or stored in a data base or a
 *  retrieval system, without the prior written permission of Tai Fook
 *  Securities Group Limited.
 */
package com.itsz.sht.common.util.configuration;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyConfiguration extends FileBasedConfigurable {

    private String filename = null;
    private Properties properties = null;

    public PropertyConfiguration(String name) throws Exception {
        super(name);
        if (name == null) throw new Exception();
        filename = name;
        load();
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
	
     public int getIntValue(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
    public Enumeration getKeys(){
    	return properties.keys();
    }
    public String getValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String[] getValues(String key) {
        String values[] = {
            properties.getProperty(key)
        };
        return values;
    }

    public String[] getValues(String key, String[] defaultValues) {
        String values[] = null;
        if (defaultValues != null && defaultValues.length > 0) {
            values = new String[1];
            values[0] = properties.getProperty(key, defaultValues[0]);
        } else {
            values = new String[1];
            values[0] = properties.getProperty(key, null);
        }
        return values;
    }

    private void load() throws Exception {
        Properties p = new Properties();
        InputStream in = null;
        try {
            File file = new File(getFullPathName());
            in = new java.io.FileInputStream(file);
            p.load(in);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        this.properties = p;
    }

    public static void main(String[] argv) {
      //  Configurable x = new PropertyConfiguration("new.properties");
      //  System.out.println(x.getValue("a"));
    }

}
