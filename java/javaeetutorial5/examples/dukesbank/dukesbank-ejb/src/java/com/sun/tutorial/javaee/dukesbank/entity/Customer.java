/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.entity;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "BANK_CUSTOMER")
@NamedQueries({
    @NamedQuery(name = "Customer.FindById",query = "SELECT a FROM Customer a WHERE a.id = :id")
    , @NamedQuery(name = "Customer.FindByLastName", query = "SELECT a FROM Customer a WHERE a.lastName LIKE :lastName")
    , @NamedQuery(name = "Customer.FindByFirstName", query = "SELECT a FROM Customer a WHERE a.firstName = :firstName")
    , @NamedQuery(name = "Customer.FindByMiddleInitial", query = "SELECT a FROM Customer a WHERE a.middleInitial = :middleInitial")
    , @NamedQuery(name = "Customer.FindByStreet", query = "SELECT a FROM Customer a WHERE a.street = :street")
    , @NamedQuery(name = "Customer.FindByCity", query = "SELECT a FROM Customer a WHERE a.city = :city")
    , @NamedQuery(name = "Customer.FindByState", query = "SELECT a FROM Customer a WHERE a.state = :state")
    , @NamedQuery(name = "Customer.FindByZip", query = "SELECT a FROM Customer a WHERE a.zip = :zip")
    , @NamedQuery(name = "Customer.FindByPhone", query = "SELECT a FROM Customer a WHERE a.phone = :phone")
    , @NamedQuery(name = "Customer.FindByEmail", query = "SELECT a FROM Customer a WHERE a.email = :email")
    , @NamedQuery(name = "Customer.FindAllCustomersOfAccount", query = "SELECT c FROM Customer c JOIN c.accounts a WHERE a.id = :accountId")
})
public class Customer implements java.io.Serializable {
    @ManyToMany(mappedBy = "customers")
    private Collection<Account> accounts;
    @TableGenerator(name = "customerIdGen", table = "BANK_SEQUENCE_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "CUSTOMER_ID", initialValue = 203, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customerIdGen")
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long id;
    @Column(name = "CITY")
    private String city;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "MIDDLE_INITIAL")
    private String middleInitial;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "STATE")
    private String state;
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIP")
    private String zip;

    /** Creates a new instance of Customer */
    public Customer() {
    }

    public Customer(
        String lastName,
        String firstName,
        String middleInitial,
        String street,
        String city,
        String state,
        String zip,
        String phone,
        String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public Long getCustomerId() {
        return this.id;
    }

    public void setCustomerId(Long customerId) {
        this.id = customerId;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return this.middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Collection<Account> accountId) {
        this.accounts = accountId;
    }
}
