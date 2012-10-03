package com.itsz.util.database;

import java.io.File;

/**
 * $Id: AppConfig.java,v 1.1 2011/03/01 02:15:43 xlliu Exp $
 * @Project:contingencysystem
 * @File:AppConfig.java
 * @Description:
 * @Author:clyao
 * @Date:Nov 21, 2007
 * @version:
 */
public class AppConfig {
    
    private static File baseAppPath;
    
    private static String contextName;
    
    public static String getContextName() {
        if (contextName != null) {
            return contextName;
        }
        
        return "";
    }
    
    public static File getBaseAppPath() {
        return baseAppPath;
    }
    
    public static String getBaseAppPath2URL() {
        return baseAppPath.toURI().toString();
    }
    
    public static void setBaseAppPath(File baseAppPath) {
        AppConfig.baseAppPath = baseAppPath;
        
        if (baseAppPath != null) {
            String baseAppUrl = baseAppPath.toURI().toString();
            baseAppUrl = baseAppUrl.charAt(baseAppUrl.length() - 1) == '/' ? baseAppUrl
                    .substring(0, baseAppUrl.length() - 1)
                    : baseAppUrl;
                    contextName = baseAppUrl.substring(baseAppUrl.lastIndexOf("/"));
        }
    }
    
}



