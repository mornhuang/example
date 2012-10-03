/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package myorg;

import java.util.*;


public class Organization {
    private TreeMap<String, Department> departments = null;

    public Organization() {
        departments = new TreeMap<String, Department>();
        addDepartment("Sales");

        Department dept = getDepartment("Sales");
        dept.addMember("Moran, Jo", "12345", "jlm");
        dept.addMember("Dillan, Terry", "98765", "trd");
        dept.addMember("Hansen, Lee", "45678", "lah");
        dept.addMember("Dupont, Jerry", "34567", "jad");
        addDepartment("Engineering");
        dept = getDepartment("Engineering");
        dept.addMember("Corelli, Jeff", "45454", "jc");
        dept.addMember("Dan, Tina", "78787", "tad");
        dept.addMember("Harvey, Ann", "43434", "ash");
        dept.addMember("Brown, Kim", "70707", "kjb");
        addDepartment("Marketing");
        dept = getDepartment("Marketing");
        dept.addMember("Cole, Jennifer", "12121", "jac");
        dept.addMember("Kramer, Dick", "98989", "djk");
        dept.addMember("Sheridan, Dave", "23232", "dhs");
        dept.addMember("Cluney, Sue", "10101", "slc");
    }

    public TreeMap getDepartments() {
        return departments;
    }

    public Department getDepartment(String name) {
        return (Department) departments.get(name);
    }

    public Collection getDepartmentValues() {
        return departments.values();
    }

    public Collection getDepartmentNames() {
        return departments.keySet();
    }

    public void addDepartment(String name) {
        Department aDept = new Department();
        departments.put(name, aDept);
    }
}
