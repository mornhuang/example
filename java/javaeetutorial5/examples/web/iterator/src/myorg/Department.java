/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package myorg;

import java.util.*;


public class Department {
    private TreeMap<String, Member> members = new TreeMap<String, Member>();

    public Department() {
    }

    public Member getMember(String name) {
        return (Member) members.get(name);
    }

    public Collection getMembers() {
        return members.values();
    }

    public void addMember(
        String name,
        String phone,
        String email) {
        Member aMember = new Member(name, phone, email);
        members.put(name, aMember);
    }
}
