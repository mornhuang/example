/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.util;

public class TeamDetails implements java.io.Serializable {
    private String city;
    private String id;
    private String name;

    public TeamDetails(
        String id,
        String name,
        String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String toString() {
        String s = id + " " + name + " " + city;

        return s;
    }
}
