/**
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

public class StatisticsImpl
    implements Statistics {

    public StatisticsImpl() {};

    Hashtable table = new Hashtable();

    public void update(String statsKey, long ms) {
        if (statsKey != null) {
            StatsProperties prop = (StatsProperties) table.get(statsKey);
            if (prop == null) {
                prop = new StatsProperties();
                table.put(statsKey, prop);
            }
            prop.update(ms);
        }
    }

    public String printStats() {
        StringBuffer buf = new StringBuffer();
        for (Enumeration e = table.keys(); e.hasMoreElements(); ) {
            String statkey = (String) e.nextElement();
            buf.append("\n<BR>");
            buf.append("** KEY: " + statkey);
            StatsProperties prop = (StatsProperties) table.get(statkey);
            buf.append("\n<BR>Max: ").append(prop.max);
            buf.append(" Min: ").append(prop.min);
            buf.append(" Average: ").append(prop.average);
            buf.append(" Count: ").append(prop.count - 1);
            buf.append("\n<BR>");
        }
        return buf.toString();

    }

    public long getAverage(String statsKey) {
        StatsProperties prop = (StatsProperties) table.get(statsKey);
        return (prop != null) ? prop.average : 0L;
    }

    public long getMax(String statsKey) {
        StatsProperties prop = (StatsProperties) table.get(statsKey);
        return (prop != null) ? prop.max : 0L;
    }

    public long getMin(String statsKey) {
        StatsProperties prop = (StatsProperties) table.get(statsKey);
        return (prop != null) ? prop.min : 0L;
    }

    public void clearAll() {
        if (table != null) {
            table.clear();
        }
    }

    public void clear(String statsKey) {
        StatsProperties prop = (StatsProperties) table.get(statsKey);
        prop.clear();
    }

    private class StatsProperties {
        public long min = 0L;
        public long max = 0L;
        public long average = 0L;
        public long total = 0L;
        public int count = 0;

        public StatsProperties() {}

        public void update(long ms) {
            count++;
            if (count != 1) {
                if (count == 2) {
                    min = ms;
                }
                if (ms < min) {
                    min = ms;
                }
                if (ms > max) {
                    max = ms;
                }
                total += ms;
                average = total / (count - 1);
            }
        }

        public void clear() {
            min = 0L;
            max = 0L;
            average = 0L;
            total = 0L;
            count = 0;
        }
    }
}