/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.util;

public class PlayerDetails implements java.io.Serializable {
    private String id;
    private String name;
    private String position;
    private double salary;

    public PlayerDetails(
        String id,
        String name,
        String position,
        double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        String s = id + " " + name + " " + position + " " + salary;

        return s;
    }
}
