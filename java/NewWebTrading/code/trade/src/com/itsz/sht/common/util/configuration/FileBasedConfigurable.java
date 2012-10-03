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
import java.net.URL;

/**
 *  Description of the Class
 *
 */
public abstract class FileBasedConfigurable
    implements Configurable {

    private String fileName;
    protected String fullPathName;

    public FileBasedConfigurable(String name) {
        fileName = name;
    }

    public String getFileName() {
        return fileName;
    }

    protected String getFullPathName() {
        if (new File(fileName).exists()) {
            return fileName;
        }

        String confRoot = System.getProperty("conf.root");
        if (confRoot == null) {
            return getClassLoaderPath();
        } else {
            String pathName = confRoot + File.separatorChar + fileName;
            if (!new File(pathName).exists()) {
                return getClassLoaderPath();
            } else {
                return pathName;
            }
        }
    }

    private String getClassLoaderPath() {
        URL configURL = this.getClass().getClassLoader().getResource(fileName);
        if (configURL == null) {
            return null;
        } else {
            return configURL.getPath();
        }
    }
}
