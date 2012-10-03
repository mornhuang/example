/*
 *  Copyright (c) 2002 Tai Fook Securities Group Limited.
 *  All rights reserved.
 *
 *  This file contains the valuable properties of Tai Fook Securities
 *  Group Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be
 *  reproduced or distributed in any form or by any means, or stored
 *  in a data base or a retrieval system, without the prior written
 *  permission of Tai Fook Securities Group Limited.
 *
 */
package com.itsz.sht.common.util.statistics;

public interface Statistics {

    public void update(String statsKey, long ms);

    public long getAverage(String statsKey);

    public long getMax(String statsKey);

    public long getMin(String statsKey);

    public void clear(String statsKey);

    public void clearAll();

    public String printStats();
}
