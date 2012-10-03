/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.request;

import com.sun.tutorial.javaee.dukesbank.exception.CustomerNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.InvalidParameterException;
import com.sun.tutorial.javaee.dukesbank.util.CustomerDetails;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;


@Remote
public interface CustomerController {
    @RolesAllowed("bankAdmin")
    Long createCustomer(CustomerDetails details)
        throws InvalidParameterException;

    @RolesAllowed("bankAdmin")
    List<CustomerDetails> getCustomersOfAccount(Long accountId)
        throws InvalidParameterException;

    @RolesAllowed("bankAdmin")
    @SuppressWarnings("unchecked")
    List<CustomerDetails> getCustomersOfLastName(String lastName)
        throws InvalidParameterException;

    @PermitAll
    CustomerDetails getDetails(Long customerId)
        throws CustomerNotFoundException, InvalidParameterException;

    @RolesAllowed("bankAdmin")
    void removeCustomer(Long customerId)
        throws CustomerNotFoundException, InvalidParameterException;

    @RolesAllowed("bankAdmin")
    void setAddress(
        String street,
        String city,
        String state,
        String zip,
        String phone,
        String email,
        Long customerId)
        throws CustomerNotFoundException, InvalidParameterException;

    @RolesAllowed("bankAdmin")
    void setName(
        String lastName,
        String firstName,
        String middleInitial,
        Long customerId)
        throws CustomerNotFoundException, InvalidParameterException;
}
