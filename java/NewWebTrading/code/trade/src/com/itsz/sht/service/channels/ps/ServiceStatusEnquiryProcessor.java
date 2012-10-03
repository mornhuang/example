package com.itsz.sht.service.channels.ps;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


import com.itsz.sht.common.Consts;
import com.itsz.sht.dao.ServiceParamDAO;
import com.itsz.sht.dao.ServiceParamDAOFactory;
import com.itsz.sht.dao.ServiceStatusDAO;
import com.itsz.sht.dao.ServiceStatusDAOFactory;
import com.itsz.sht.dao.ServiceStatusRemarksDAO;
import com.itsz.sht.dao.ServiceStatusRemarksDAOFactory;
import com.itsz.sht.dto.ServiceParameter;
import com.itsz.sht.dto.ServiceStatus;
import com.itsz.sht.dto.ServiceStatusRemarks;
import com.itsz.sht.exception.ServiceStatusEnquiryException;
import com.itsz.sht.vp.common.ServiceStatusEnquiryResponse;
import com.opensymphony.oscache.util.FastCronParser;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

/**
 * 
 * $Id: ServiceStatusEnquiryProcessor.java,v 1.1 2011/03/02 10:30:36 pbxie Exp $
 * @Project:portal
 * @File:ServiceStatusEnquiryProcessor.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceStatusEnquiryProcessor {

    private static final long TIME_THRESHOLD = 60000;
    private static WebLogger logger = WebLoggerFactory.instance().getLogger(ServiceStatusEnquiryProcessor.class.getName());

    public static ServiceStatusEnquiryResponse getServiceStatus(String serviceName)
        throws ServiceStatusEnquiryException {


        try {
            // The ServiceStatusEnquiryResponse object to be returned
            ServiceStatusEnquiryResponse serviceStatusEnqResponse = new ServiceStatusEnquiryResponse();
            
            
            // Query the service status
            ServiceStatusDAO serviceStatusDAO = (ServiceStatusDAO) ServiceStatusDAOFactory.instance().getServiceStatusDAO();
            ServiceStatus serviceStatus = serviceStatusDAO.getServiceStatusByServiceName(serviceName);

            boolean bIsWithinValidPeriod = false;
            
            // Only need to check whether the current time is with the valid period when the status is ACTIVE
            if (serviceStatus != null 
                    && ServiceStatusEnquiryResponse.SERVICE_STATUS_ACTIVE.equals(serviceStatus.getStatus())) {
                
                FastCronParser fastCronParser = new FastCronParser(serviceStatus.getValidPeriod());
                Date currentTime = new Date();

                //logger.debug("Current time = " + currentTime.getTime() + " (" + currentTime + ")");

                // Handle Timezone issue
                Calendar localCal = new GregorianCalendar();
                localCal.setTimeInMillis(currentTime.getTime());

                // Create an instance using time zone specified
                Calendar destCal = new GregorianCalendar(TimeZone.getTimeZone(serviceStatus.getTimezone()));
                destCal.setTimeInMillis(currentTime.getTime());

                long timezoneOffset = destCal.get(Calendar.ZONE_OFFSET) - localCal.get(Calendar.ZONE_OFFSET);

                //logger.debug("Timezone offset = " + timezoneOffset);
                long timeAfterConversion = destCal.getTimeInMillis() + timezoneOffset;

                //logger.debug("Time after timezone conversion = " + timeAfterConversion + " (" + new Date(timeAfterConversion) + ")");

                //logger.debug("Valid period for the service = " + serviceStatus.getValidPeriod());
                long validTimeBefore = fastCronParser.getTimeBefore(timeAfterConversion);
                //logger.debug("The most recent time that matches the cron expression = " + validTimeBefore + " (" + new Date(validTimeBefore) + ")");


                // If current time is within the valid period, hasMoreRecentMatch will be equal to true
                boolean hasMoreRecentMatch = (timeAfterConversion - TIME_THRESHOLD < validTimeBefore);

                logger.info("ServiceStatus: raw: " + serviceStatus.getValidPeriod() + ", Current: " + 
                        new Date(timeAfterConversion) + ", parsed valid time: " + new Date(validTimeBefore) + 
                        ", result: " + hasMoreRecentMatch);

                //logger.debug("Has more recent match = " + hasMoreRecentMatch);
                bIsWithinValidPeriod = hasMoreRecentMatch;
            }
            else {
                // Always set to true when the status is not equal to 'ACTIVE'
                bIsWithinValidPeriod = true;
            }

            serviceStatusEnqResponse.setServiceName(serviceName);
            serviceStatusEnqResponse.setStatus(serviceStatus.getStatus());
            serviceStatusEnqResponse.setValidPeriod(serviceStatus.getValidPeriod());
            serviceStatusEnqResponse.setIsWithinValidPeriod(bIsWithinValidPeriod);

            
            // Get the service status remarks
            if (serviceStatus != null && !ServiceStatusEnquiryResponse.SERVICE_STATUS_INACTIVE.equals(serviceStatus.getStatus())) {
                ServiceStatusRemarksDAO serviceStatusRemarksDAO = (ServiceStatusRemarksDAO) ServiceStatusRemarksDAOFactory.instance().getServiceStatusRemarksDAO();
                
                // Get the english remarks
                ServiceStatusRemarks serviceStatusRemarks = serviceStatusRemarksDAO.getServiceStatusRemarks(serviceStatus.getServiceID(), serviceStatus.getStatus(), Consts.Global.Language.PatternC.ENGLISH);
                
                if (serviceStatusRemarks != null) {
                    serviceStatusEnqResponse.setRemarks_en_US(serviceStatusRemarks.getRemarks());
                }

                // Get the traditional chinese remarks
                serviceStatusRemarks = serviceStatusRemarksDAO.getServiceStatusRemarks(serviceStatus.getServiceID(), serviceStatus.getStatus(), Consts.Global.Language.PatternC.CHINESE_TRADITIONAL);
                
                if (serviceStatusRemarks != null) {
                    serviceStatusEnqResponse.setRemarks_zh_TW(serviceStatusRemarks.getRemarks());
                }

                // Get the simplified chinese remarks
                serviceStatusRemarks = serviceStatusRemarksDAO.getServiceStatusRemarks(serviceStatus.getServiceID(), serviceStatus.getStatus(), Consts.Global.Language.PatternC.CHINESE_SIMPLIFIED);
                
                if (serviceStatusRemarks != null) {
                    serviceStatusEnqResponse.setRemarks_zh_CN(serviceStatusRemarks.getRemarks());
                }
            }           

            return serviceStatusEnqResponse;
        } catch (DAOException de) {
            throw new ServiceStatusEnquiryException();
        } catch (ParseException pe) {
            logger.error("ParseException " + pe.getMessage());
            throw new ServiceStatusEnquiryException();
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error(e);
            logger.error("Exception occurred " + e.getMessage());
            throw new ServiceStatusEnquiryException();
        }
    }

    public static Object getServiceParam(String serviceName, String paramName)
        throws ServiceStatusEnquiryException {

        try {
            // Get the service details first
            ServiceStatusDAO serviceStatusDAO = (ServiceStatusDAO) ServiceStatusDAOFactory.instance().getServiceStatusDAO();
            ServiceStatus serviceStatus = serviceStatusDAO.getServiceStatusByServiceName(serviceName);
            // Get the param detail
            ServiceParamDAO serviceParamDAO = (ServiceParamDAO) ServiceParamDAOFactory.instance().getServiceParamDAO();
            ServiceParameter serviceParam = serviceParamDAO.getServiceParam(serviceStatus.getServiceID(), paramName);

            if (serviceParam != null) {
                String dataType = serviceParam.getDataType();

                if (dataType != null && dataType.equals("BigDecimal"))
                    return new BigDecimal(serviceParam.getParamValue());
                else if (dataType != null && dataType.equals("String"))
                    return (String) serviceParam.getParamValue();
                else {
                    // Return String by Default
                    return (String) serviceParam.getParamValue();
                }
            } else
                return null;
        } catch (DAOException de) {
            throw new ServiceStatusEnquiryException();
        }
    }

    public static ArrayList getAllServiceParams(String serviceName)
        throws ServiceStatusEnquiryException {
    
        try {
            // Get the service details first
            ServiceStatusDAO serviceStatusDAO = (ServiceStatusDAO) ServiceStatusDAOFactory.instance().getServiceStatusDAO();
            ServiceStatus serviceStatus = serviceStatusDAO.getServiceStatusByServiceName(serviceName);
            // Get the param list
            ServiceParamDAO serviceParamDAO = (ServiceParamDAO) ServiceParamDAOFactory.instance().getServiceParamDAO();
            ArrayList serviceParamList = serviceParamDAO.getServiceParams(serviceStatus.getServiceID());
            return serviceParamList;
        } catch (DAOException de) {
            throw new ServiceStatusEnquiryException();
        }
    }

    /**
     * Return the key-value parameters of a given service name. 
     */
    public static Map getServiceParams(String serviceName)
        throws ServiceStatusEnquiryException {
        
        try {
            // Get the service details first
            ServiceStatusDAO serviceStatusDAO = (ServiceStatusDAO) ServiceStatusDAOFactory.instance().getServiceStatusDAO();
            ServiceStatus serviceStatus = serviceStatusDAO.getServiceStatusByServiceName(serviceName);
            // Get the param list
            ServiceParamDAO serviceParamDAO = (ServiceParamDAO) ServiceParamDAOFactory.instance().getServiceParamDAO();
            ArrayList serviceParamList = serviceParamDAO.getServiceParams(serviceStatus.getServiceID());
            if (serviceParamList != null) {
                Map serviceParamMap = new HashMap();
                
                for (int i=0; i < serviceParamList.size(); i++) {
                    ServiceParameter tmpServiceParam = (ServiceParameter) serviceParamList.get(i);
                    if (tmpServiceParam.getDataType() != null && tmpServiceParam.getDataType().equalsIgnoreCase("BigDecimal")) {
                        serviceParamMap.put(tmpServiceParam.getParamName(), new BigDecimal(tmpServiceParam.getParamValue()));   
                    }
                    else if (tmpServiceParam.getDataType() != null && tmpServiceParam.getDataType().equalsIgnoreCase("String")) {
                        serviceParamMap.put(tmpServiceParam.getParamName(), tmpServiceParam.getParamValue());
                    } else {
                        serviceParamMap.put(tmpServiceParam.getParamName(), tmpServiceParam.getParamValue());
                    }
                }
                return serviceParamMap;
                
            }
            else {
                return null;    
            }
            
        } catch (DAOException de) {
            throw new ServiceStatusEnquiryException();
        }
    }

}
