/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.util;

public class LeagueDetails implements java.io.Serializable {
    private String id;
    private String name;
    private String sport;

    public LeagueDetails(
        String id,
        String name,
        String sport) {
        this.id = id;
        this.name = name;
        this.sport = sport;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSport() {
        return sport;
    }

    public String toString() {
        String s = id + " " + name + " " + sport;

        return s;
    }
}
