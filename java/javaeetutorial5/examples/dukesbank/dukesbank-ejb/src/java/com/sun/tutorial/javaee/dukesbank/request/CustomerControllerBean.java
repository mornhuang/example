/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.request;

import com.sun.tutorial.javaee.dukesbank.entity.Account;
import com.sun.tutorial.javaee.dukesbank.entity.Customer;
import com.sun.tutorial.javaee.dukesbank.exception.CustomerNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.InvalidParameterException;
import com.sun.tutorial.javaee.dukesbank.util.CustomerDetails;
import com.sun.tutorial.javaee.dukesbank.util.Debug;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@DeclareRoles("bankAdmin")
@Stateful
public class CustomerControllerBean implements CustomerController {
    @PersistenceContext
    private EntityManager em;
    private Long customerId = null;

    @RolesAllowed("bankAdmin")
    public Long createCustomer(CustomerDetails details)
        throws InvalidParameterException {
        // makes a new customer and enters it into db
        Customer customer = null;

        Debug.print("CustomerControllerBean createCustomer");

        if (details.getLastName() == null) {
            throw new InvalidParameterException("null lastName");
        }

        if (details.getFirstName() == null) {
            throw new InvalidParameterException("null firstName");
        }

        try {
            Debug.print("Creating Customer.");
            customer = new Customer(
                        details.getLastName(),
                        details.getFirstName(),
                        details.getMiddleInitial(),
                        details.getStreet(),
                        details.getCity(),
                        details.getState(),
                        details.getZip(),
                        details.getPhone(),
                        details.getEmail());
            em.persist(customer);
        } catch (Exception ex) {
            throw new EJBException("createCustomer: " + ex.getMessage());
        }

        return customer.getCustomerId();
    }

    @RolesAllowed("bankAdmin")
    public void removeCustomer(Long customerId)
        throws CustomerNotFoundException, InvalidParameterException {
        // removes customer from db
        Debug.print("CustomerControllerBean removeCustomer");

        if (customerId == null) {
            throw new InvalidParameterException("null customerId");
        }

        try {
            Customer customer = em.find(Customer.class, customerId);

            if (customer == null) {
                throw new CustomerNotFoundException();
            } else {
                em.remove(customer);
            }
        } catch (Exception ex) {
            throw new EJBException("removeCustomer: " + ex.getMessage());
        }
    }

    // getters
    @PermitAll
    public CustomerDetails getDetails(Long customerId)
        throws CustomerNotFoundException, InvalidParameterException {
        // returns the CustomerDetails for the specified customer
        Debug.print("CustomerControllerBean getDetails");

        CustomerDetails result = null;

        if (customerId == null) {
            throw new InvalidParameterException("null customerId");
        }

        try {
            Customer customer = em.find(Customer.class, customerId);

            if (customer == null) {
                throw new CustomerNotFoundException(customerId);
            } else {
                result = new CustomerDetails(
                            customer.getCustomerId(),
                            customer.getLastName(),
                            customer.getFirstName(),
                            customer.getMiddleInitial(),
                            customer.getStreet(),
                            customer.getCity(),
                            customer.getState(),
                            customer.getZip(),
                            customer.getPhone(),
                            customer.getEmail());
            }
        } catch (Exception ex) {
            System.err.println(
                    "CustomerControllerBean.getDetails: Caught an exception.");
            ex.printStackTrace();
        }

        return result;
    }

    @RolesAllowed("bankAdmin")
    public List<CustomerDetails> getCustomersOfAccount(Long accountId)
        throws InvalidParameterException {
        // returns an ArrayList of CustomerDetails 
        // that correspond to the accountId specified
        Debug.print("CustomerControllerBean getCustomersOfAccount");

        Collection customers = null;

        if (accountId == null) {
            throw new InvalidParameterException("null accountId");
        }

        try {
            Account account = em.find(Account.class, accountId);
            customers = account.getCustomers();
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }

        return copyCustomersToDetails(customers);
    }

    @RolesAllowed("bankAdmin")
    @SuppressWarnings("unchecked")
    public List<CustomerDetails> getCustomersOfLastName(String lastName)
        throws InvalidParameterException {
        // returns an ArrayList of CustomerDetails 
        // that correspond to the the lastName specified
        // returns null if no customers are found
        Debug.print("CustomerControllerBean getCustomersOfLastName");

        Collection customers = null;

        if (lastName == null) {
            throw new InvalidParameterException("null lastName");
        }

        try {
            customers = (List<Customer>) em.createNamedQuery(
                        "Customer.FindByLastName")
                                           .setParameter("lastName", lastName)
                                           .getResultList();
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }

        if (customers.isEmpty()) {
            System.err.println(
                    "CustomerControllerBean.getCustomersOfLastName: no customers returned");

            return null;
        } else {
            return copyCustomersToDetails(customers);
        }
    }

    // setters
    @RolesAllowed("bankAdmin")
    public void setName(
        String lastName,
        String firstName,
        String middleInitial,
        Long customerId)
        throws CustomerNotFoundException, InvalidParameterException {
        Debug.print("CustomerControllerBean setName");

        if (lastName == null) {
            throw new InvalidParameterException("null lastName");
        }

        if (firstName == null) {
            throw new InvalidParameterException("null firstName");
        }

        if (customerId == null) {
            throw new InvalidParameterException("null customerId");
        }

        if (customerExists(customerId) == false) {
            throw new CustomerNotFoundException(customerId);
        }

        try {
            Customer customer = em.find(Customer.class, customerId);
            customer.setLastName(lastName);
            customer.setFirstName(firstName);
            customer.setMiddleInitial(middleInitial);
        } catch (Exception ex) {
            throw new EJBException("setName: " + ex.getMessage());
        }
    }

    @RolesAllowed("bankAdmin")
    public void setAddress(
        String street,
        String city,
        String state,
        String zip,
        String phone,
        String email,
        Long customerId)
        throws CustomerNotFoundException, InvalidParameterException {
        Debug.print("CustomerControllerBean setAddress");

        if (street == null) {
            throw new InvalidParameterException("null street");
        }

        if (city == null) {
            throw new InvalidParameterException("null city");
        }

        if (state == null) {
            throw new InvalidParameterException("null state");
        }

        if (customerId == null) {
            throw new InvalidParameterException("null customerId");
        }

        try {
            Customer customer = em.find(Customer.class, customerId);
            customer.setStreet(street);
            customer.setCity(city);
            customer.setState(state);
            customer.setZip(zip);
            customer.setPhone(phone);
            customer.setEmail(email);
        } catch (Exception ex) {
            throw new EJBException("setAddress: " + ex.getMessage());
        }
    }

    private boolean customerExists(Long customerId) {
        // If a business method has been invoked with
        // a different customerId, then update the
        // customerId and customer variables.
        // Return null if the customer is not found.
        Customer customer = null;

        Debug.print("CustomerControllerBean customerExists");

        if (customerId.equals(this.customerId) == false) {
            try {
                customer = em.find(Customer.class, customerId);
                this.customerId = customerId;
            } catch (Exception ex) {
                return false;
            }
        }

        return true;
    }

    private List<CustomerDetails> copyCustomersToDetails(Collection customers) {
        List<CustomerDetails> detailsList = new ArrayList<CustomerDetails>();
        Iterator<Customer> i = customers.iterator();

        try {
            while (i.hasNext()) {
                Customer customer = (Customer) i.next();
                CustomerDetails details = new CustomerDetails(
                            customer.getCustomerId(),
                            customer.getLastName(),
                            customer.getFirstName(),
                            customer.getMiddleInitial(),
                            customer.getStreet(),
                            customer.getCity(),
                            customer.getState(),
                            customer.getZip(),
                            customer.getPhone(),
                            customer.getEmail());
                detailsList.add(details);
            }
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }

        return detailsList;
    }
}
