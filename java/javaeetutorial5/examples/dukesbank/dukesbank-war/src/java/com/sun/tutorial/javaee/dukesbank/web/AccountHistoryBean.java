/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.sun.tutorial.javaee.dukesbank.exception.InvalidParameterException;
import com.sun.tutorial.javaee.dukesbank.util.AccountDetails;
import com.sun.tutorial.javaee.dukesbank.util.Debug;
import com.sun.tutorial.javaee.dukesbank.util.TxDetails;
import com.sun.tutorial.javaee.dukesbank.web.Util.Navigation;


public class AccountHistoryBean {
    protected ArrayList AVOptions = null;
    protected ArrayList beginDayOptions = null;
    protected ArrayList beginMonthOptions = null;
    protected ArrayList endDayOptions = null;
    protected ArrayList endMonthOptions = null;
    protected ArrayList sinceDayOptions = null;
    protected ArrayList sinceMonthOptions = null;
    protected ArrayList sortOptions = null;
    protected ArrayList yearOptions = null;
    private AccountDetails selectedAccount;
    private ArrayList<TxDetails> selectedTransactions;
    private BigDecimal beginningBalance;
    private BigDecimal credits;
    private BigDecimal debits;
    private BigDecimal endingBalance;
    private CustomerBean customer;
    private String range;
    private String since;
    private String sinceOrRange;
    private int activityView;
    private int beginDay;
    private int beginMonth;
    private int date;
    private int endDay;
    private int endMonth;
    private int sinceDay;
    private int sinceMonth;
    private int sortOption;
    private int year;

    public AccountHistoryBean() {
        sinceOrRange = "since";
        range = "range";
        since = "since";
        sinceMonth = 1;
        sinceDay = 1;
        beginMonth = 1;
        beginDay = 1;
        endMonth = 12;
        endDay = 31;
        year = 2006;
    }

    public void doTx() {
        BigDecimal amount;

        TxDetails td = null;
        Date startDate = null;
        Date endDate = null;

        if (sinceOrRange != null) {
            if (sinceOrRange.equals(range)) {
                date = 1;
            }
        } else {
            date = 0;
        }

        try {
            switch (date) {
            case 0:
                startDate = com.sun.tutorial.javaee.dukesbank.util.DateHelper
                    .getDate(year, sinceMonth, sinceDay);
                endDate = new Date();

                break;

            case 1:
                startDate = com.sun.tutorial.javaee.dukesbank.util.DateHelper
                    .getDate(year, beginMonth, beginDay);
                endDate = com.sun.tutorial.javaee.dukesbank.util.DateHelper
                    .getDate(year, endMonth, endDay);

                break;
            }

            List<TxDetails> transactions = customer.getTxController()
                                                   .getTxsOfAccount(
                        startDate,
                        endDate,
                        customer.getActiveAccount());

            switch (sortOption) {
            case 0:
                Collections.sort(
                        transactions,
                        new Comparator<TxDetails>() {
                            public int compare(
                                TxDetails tx1,
                                TxDetails tx2) {
                                return (tx1.getTimeStamp().compareTo(
                                        tx2.getTimeStamp()));
                            }
                        });


                break;

            case 1:
                Collections.sort(
                        transactions,
                        new Comparator<TxDetails>() {
                            public int compare(
                                TxDetails tx1,
                                TxDetails tx2) {
                                return (tx2.getTimeStamp().compareTo(
                                        tx1.getTimeStamp()));
                            }
                        });


                break;

            case 2:
                Collections.sort(
                        transactions,
                        new Comparator<TxDetails>() {
                            public int compare(
                                TxDetails tx1,
                                TxDetails tx2) {
                                return (tx1.getDescription().compareTo(
                                        tx2.getDescription()));
                            }
                        });


                break;

            case 3:
                Collections.sort(
                        transactions,
                        new Comparator<TxDetails>() {
                            public int compare(
                                TxDetails tx1,
                                TxDetails tx2) {
                                return (tx1.getAmount().compareTo(
                                        tx2.getAmount()));
                            }
                        });


                break;
            }

            credits = new BigDecimal("0.00");
            debits = new BigDecimal("0.00");

            selectedAccount = customer.getAccountDetails();

            beginningBalance = selectedAccount.getBalance();
            endingBalance = selectedAccount.getBalance();

            boolean isCreditAcct = false;

            if (selectedAccount.getType()
                                   .equals("Credit")) {
                isCreditAcct = true;
            }

            Iterator<TxDetails> i = transactions.iterator();

            if (i.hasNext()) {
                td = i.next();
                beginningBalance = td.getBalance()
                                     .subtract(td.getAmount());
            }

            i = transactions.iterator();

            if (i.hasNext()) {
                Debug.print("adding to credits and debits.");

                while (i.hasNext()) {
                    td = i.next();
                    amount = td.getAmount();

                    if (isCreditAcct) {
                        if (amount.floatValue() < 0) {
                            credits = credits.add(amount);
                        } else {
                            debits = debits.subtract(amount);
                        }
                    } else {
                        if (amount.floatValue() > 0) {
                            Debug.print("Adding " + amount + "to credits.");
                            credits = credits.add(amount);
                        } else {
                            Debug.print(
                                    "Subtracting " + amount + "from debits.");
                            debits = debits.subtract(amount);
                        }
                    }
                }
            }

            if (td != null) {
                endingBalance = td.getBalance();
            }

            selectedTransactions = new ArrayList<TxDetails>();
            i = transactions.iterator();

            if (i.hasNext()) {
                switch (activityView) {
                case 0:

                    while (i.hasNext()) {
                        td = i.next();
                        selectedTransactions.add(td);
                    }

                    break;

                case 1:

                    while (i.hasNext()) {
                        td = i.next();

                        if (isCreditAcct) {
                            if (td.getAmount()
                                      .floatValue() < 0) {
                                selectedTransactions.add(td);
                            }
                        } else {
                            if (td.getAmount()
                                      .floatValue() > 0) {
                                selectedTransactions.add(td);
                            }
                        }
                    }

                    break;

                case 2:

                    while (i.hasNext()) {
                        td = i.next();

                        if (isCreditAcct) {
                            if (td.getAmount()
                                      .floatValue() > 0) {
                                selectedTransactions.add(td);
                            }
                        } else {
                            if (td.getAmount()
                                      .floatValue() < 0) {
                                selectedTransactions.add(td);
                            }
                        }
                    }

                    break;
                }
            } else {
                Debug.print("No matching transactions.");
            }
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public Long getAccountId() {
        return customer.getActiveAccount();
    }

    public void setAccountId(Long accountId) {
        if (accountId != null) {
            customer.setActiveAccount(accountId);
        }
    }

    public BigDecimal getCredits() {
        return credits;
    }

    public BigDecimal getDebits() {
        return debits;
    }

    public BigDecimal getBeginningBalance() {
        return beginningBalance;
    }

    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    public ArrayList<TxDetails> getSelectedTransactions() {
        return selectedTransactions;
    }

    public void setSelectedTransactions(ArrayList<TxDetails> setTrans) {
        selectedTransactions = setTrans;
    }

    public AccountDetails getSelectedAccount() {
        return selectedAccount;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        if (date != null) {
            this.date = date;
        }
    }

    public void setYear(Integer year) {
        if (year != null) {
            this.year = year;
        }
    }

    public Integer getYear() {
        return year;
    }

    public ArrayList<SelectItem> getYearOptions() throws Exception {
        return loadOptions(2004, 2006, null);
    }

    public void setBeginMonth(Integer beginMonth) {
        if (beginMonth != null) {
            this.beginMonth = beginMonth;
        }
    }

    public Integer getBeginMonth() {
        return beginMonth;
    }

    public ArrayList<SelectItem> getBeginMonthOptions()
        throws Exception {
        return loadOptions(1, 12, "Month");
    }

    public void setBeginDay(Integer beginDay) {
        if (beginDay != null) {
            this.beginDay = beginDay;
        }
    }

    public Integer getBeginDay() {
        return beginDay;
    }

    public ArrayList<SelectItem> getBeginDayOptions() throws Exception {
        return loadOptions(1, 31, null);
    }

    public void setBeginDayOptions(ArrayList newBeginDayOptions) {
        beginDayOptions = newBeginDayOptions;
    }

    public void setEndMonth(Integer endMonth) {
        if (endMonth != null) {
            this.endMonth = endMonth;
        }
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public ArrayList<SelectItem> getEndMonthOptions() {
        return loadOptions(1, 12, "Month");
    }

    public void setEndDay(Integer endDay) {
        if (endDay != null) {
            this.endDay = endDay;
        }
    }

    public Integer getEndDay() {
        return endDay;
    }

    public ArrayList<SelectItem> getEndDayOptions() {
        return loadOptions(1, 31, null);
    }

    public void setSinceMonth(Integer sinceMonth) {
        if (sinceMonth != null) {
            this.sinceMonth = sinceMonth;
        }
    }

    public Integer getSinceMonth() {
        return sinceMonth;
    }

    public ArrayList<SelectItem> getSinceMonthOptions() {
        return loadOptions(1, 12, "Month");
    }

    public String getSinceOrRange() {
        return sinceOrRange;
    }

    public String getRange() {
        return range;
    }

    public String getSince() {
        return since;
    }

    public void setSinceOrRange(String value) {
        if ((value != null) && (value.length() > 0)) {
            this.sinceOrRange = value;
        }
    }

    public void setSinceDay(Integer sinceDay) {
        if (sinceDay != null) {
            this.sinceDay = sinceDay;
        }
    }

    public Integer getSinceDay() {
        return sinceDay;
    }

    public ArrayList<SelectItem> getSinceDayOptions() {
        return loadOptions(1, 31, null);
    }

    public void setActivityView(Integer activityView) {
        if (activityView != null) {
            this.activityView = activityView;
        }
    }

    public Integer getActivityView() {
        return activityView;
    }

    public ArrayList<SelectItem> getAVOptions() {
        return loadOptions(0, 2, "ViewOption");
    }

    public void setSortOption(Integer sortOption) {
        if (sortOption != null) {
            this.sortOption = sortOption;
        }
    }

    public Integer getSortOption() {
        return sortOption;
    }

    public ArrayList<SelectItem> getSortOptions() {
        return loadOptions(0, 3, "SortOption");
    }

    public ArrayList<SelectItem> loadOptions(
        int start,
        int end,
        String message) {
        ArrayList<SelectItem> options = new ArrayList<SelectItem>();
        FacesContext context = null;

        for (int i = start; i <= end; i++) {
            if (message != null) {
                if (context == null) {
                    context = FacesContext.getCurrentInstance();
                }

                String key = message.concat(Integer.toString(i));
                String text = Util.getString(context, key);
                options.add(new SelectItem(i, text));
            } else {
                options.add(
                        new SelectItem(
                            i,
                            Integer.toString(i)));
            }
        }

        return options;
    }

    public void setSelectedAccount(AccountDetails selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public String[] validateDates(
        String menu,
        int day,
        int month) {
        FacesContext context = FacesContext.getCurrentInstance();
        int numDays;
        String monthString = Util.getString(context, "Month" + month);
        String dayString = Integer.toString(day);

        switch (month) {
        case 2:

            if ((((year % 4) == 0) && !((year % 100) == 0))
                    || ((year % 400) == 0)) {
                numDays = 29;
            } else {
                numDays = 28;
            }

            if (day > numDays) {
                Util.addErrorMessage(
                        context,
                        null,
                        "DatError",
                        menu,
                        monthString,
                        dayString);
            }

            break;

        case 4:
        case 6:
        case 9:
        case 11:

            if (day > 30) {
                Util.addErrorMessage(
                        context,
                        null,
                        "DateError",
                        menu,
                        monthString,
                        dayString);
            }

            break;

        default:
            break;
        }

        return new String[] { menu, monthString, dayString };
    }

    public Object submit() {
        doTx();

        if ((beginMonth > endMonth)
                || ((beginMonth >= endMonth) && (beginDay > endDay))) {
            Util.addErrorMessage(
                    FacesContext.getCurrentInstance(),
                    null,
                    "RangeError");
        }

        FacesContext context = FacesContext.getCurrentInstance();
        String menu = Util.getString(context, "DateSince");
        validateDates(menu, sinceDay, sinceMonth);
        menu = Util.getString(context, "DateThrough");
        validateDates(menu, endDay, endMonth);
        menu = Util.getString(context, "DateRange");
        validateDates(menu, beginDay, beginMonth);

        return Navigation.accountHist;
    }
}
