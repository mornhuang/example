/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package myorg;

import java.util.*;


public class Member implements Comparable {
    private String email = null;
    private String name = null;
    private String phone = null;

    public Member(
        String name,
        String phone,
        String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int compareTo(Object o) {
        Member m = (Member) o;

        return name.compareTo(m.name);
    }
}
