/*
 * Copyright (c) 2002 Tai Fook Securities Group Limited.
 * All rights reserved.
 *
 * This file contains the valuable properties of Tai Fook Securities
 * Group Limited, embodying substantial creative efforts and confidential
 * information, ideas and expressions. No part of this file may be
 * reproduced or distributed in any form or by any means, or stored
 * in a data base or a retrieval system, without the prior written
 * permission of Tai Fook Securities Group Limited.
 *
 */
package com.itsz.sht.common.util.statistics;

import java.util.Enumeration;
import java.util.Hashtable;

public final class StatisticsManager {

    private final static StatisticsManager instance = new StatisticsManager();
    private static Hashtable statsTable = new Hashtable();

    protected StatisticsManager() {
    }

    public static StatisticsManager instance() {
        return instance;
    }

    public Statistics getStatistic(String name) {
        Statistics stat = (Statistics) statsTable.get(name);
        if (stat != null) {
            return stat;
        }
        stat = new StatisticsImpl();
        statsTable.put(name, stat);
        return stat;
    }

    public Statistics[] getStatistics() {
        Statistics[] stats = null;
        int size = statsTable.size();
        if (size > 0) {
            int i = 0;
            stats = new Statistics[size];
            for (Enumeration e = statsTable.keys(); e.hasMoreElements(); ) {
                String name = (String) e.nextElement();
                stats[i] = (Statistics) statsTable.get(name);
                i++;
            }
        }
        return stats;
    }

    public void clearAll() {
        statsTable.clear();
    }

    public void clear(String name) {
        Statistics stat = (Statistics) statsTable.get(name);
        if (stat != null) {
            stat.clearAll();
        }
    }
}