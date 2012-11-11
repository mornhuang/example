/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import com.sun.faces.util.MessageFactory;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class XMLGregorianCalendarConverter implements Converter {
    public static final String CONVERSION_ERROR_MESSAGE_ID = "ConversionError";
    public static final String CONVERTER_ID = "com.sun.cb.jsf.XMLGregorianCalendar";

    /** Creates a new instance of XMLGregorianCalendarConverter */
    public XMLGregorianCalendarConverter() {
    }

    public Object getAsObject(
        FacesContext context,
        UIComponent component,
        String value) {
        try {
            String convertedValue = null;

            if (value == null) {
                return value;
            }

            value = value.trim();

            if (value.length() < 1) {
                return (null);
            }

            XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
                                                           .newXMLGregorianCalendar(
                        value);

            return calendar;
        } catch (ConverterException e) {
            throw e;
        } catch (Exception e) {
            throw new ConverterException(e);
        }
    }

    public String getAsString(
        FacesContext context,
        UIComponent component,
        Object value) {
        XMLGregorianCalendar xmlCalendar = null;
        String result = null;

        try {
            if (value == null) {
                return null;
            }

            try {
                xmlCalendar = (XMLGregorianCalendar) value;
            } catch (ClassCastException ce) {
                FacesMessage errMsg = MessageFactory.getMessage(
                            CONVERSION_ERROR_MESSAGE_ID,
                            (new Object[] { value, xmlCalendar }));
                throw new ConverterException(errMsg.getSummary());
            }

            GregorianCalendar calendar = xmlCalendar.toGregorianCalendar();
            String[] dayNames = new DateFormatSymbols().getWeekdays();
            String[] monthNames = new DateFormatSymbols().getMonths();
            result = new String(
                        dayNames[calendar.get(Calendar.DAY_OF_WEEK)] + ", "
                        + monthNames[calendar.get(Calendar.MONTH)] + " "
                        + calendar.get(Calendar.DAY_OF_MONTH) + ", "
                        + calendar.get(Calendar.YEAR));

            return result;
        } catch (ConverterException e) {
            throw e;
        } catch (Exception e) {
            throw new ConverterException(e);
        }
    }
}
