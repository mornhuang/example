/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.entity;

import javax.persistence.Entity;
import roster.util.IncorrectSportException;


@Entity
public class SummerLeague extends League implements java.io.Serializable {
    /** Creates a new instance of SummerLeague */
    public SummerLeague() {
    }

    public SummerLeague(
        String id,
        String name,
        String sport) throws IncorrectSportException {
        this.id = id;
        this.name = name;

        if (sport.equalsIgnoreCase("swimming")
                || sport.equalsIgnoreCase("soccer")
                || sport.equalsIgnoreCase("basketball")
                || sport.equalsIgnoreCase("baseball")) {
            this.sport = sport;
        } else {
            throw new IncorrectSportException("Sport is not a summer sport.");
        }
    }
}
