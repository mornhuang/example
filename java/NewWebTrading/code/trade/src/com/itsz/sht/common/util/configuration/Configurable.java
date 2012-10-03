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

/**
 *  Configurable provides interfaces for users to retrieve configuration value
 *
 */
public interface Configurable {

    /**
     *  Returns the configuration value given the key
     *
     *@param  key  The key to identify the configuration value
     *@return      returns null if key is not
     *      found
     */
    public String getValue(String key);

    /**
     *  Returns the configuration value given the key, if the key is not found
     *  returns the default value
     *
     *@param  key           The key to identify the configuration value
     *@param  defaultValue  The default value if key is not found
     *@return               Returns the default value if key is not found
     */
    public String getValue(String key, String defaultValue);

    /**
     *  Returns the configuration value as an array of string given the key,
     *  if the key is not found, return null
     *
     *@param  key  The key to identify the configuration value
     *@return      return null if key is not found
     */
    public String[] getValues(String key);

    /**
     *  Returns the configuration value as an array of string given the key,
     *  if the key is not found, return the default array of String
     *
     *@param  key            Description of the Parameter
     *@param  defaultValues  Description of the Parameter
     *@return                The values value
     */
    public String[] getValues(String key, String[] defaultValues);

    /**
     *  Returns the file name used to store the configuration
     *
     *@return    The fileName value
     */
    public String getFileName();
}
