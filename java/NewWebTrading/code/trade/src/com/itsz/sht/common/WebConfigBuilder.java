package com.itsz.sht.common;

import com.taifook.framework.foundation.configuration.ConfRepository;
import com.taifook.framework.foundation.configuration.Configurable;
import com.taifook.framework.foundation.exception.TFSystemException;

public class WebConfigBuilder
{

    protected static WebConfigBuilder instance = null;
    protected WebConfig webConfig;

    public WebConfigBuilder()
    {
        webConfig = null;
    }

    public static WebConfigBuilder instance()
    {
        if(instance == null)
            createInstance();
        return instance;
    }

    private static synchronized void createInstance()
    {
        if(instance == null)
            instance = new WebConfigBuilder();
    }

    public WebConfig buildWebConfig()
    {
        WebConfig webConfig = null;
        try
        {
            webConfig = build();
        }
        catch(Exception ex)
        {
            throw new TFSystemException("Unable to load web config file.", ex);
        }
        return webConfig;
    }

    private WebConfig build()
        throws Exception
    {
        StringBuffer confFile = new StringBuffer();
        ConfRepository confRepository = new ConfRepository("webchannel-config.xml", false);
        Configurable conf = confRepository.getConfiguration();
        WebConfig webConfig = new WebConfig();
        buildCoreConfig(webConfig, conf);
        buildSecurityConfig(webConfig, conf);
        buildErrorConfig(webConfig, conf);
        ConfRepository mcsConfRepository = new ConfRepository("MCS-MsgA-config.xml", false);
        Configurable mcsConf = mcsConfRepository.getConfiguration();
        buildMCSConfig(webConfig, conf, mcsConf);
        buildECertConfig(webConfig, conf);
        return webConfig;
    }

    private void buildCoreConfig(WebConfig webConfig, Configurable conf)
        throws Exception
    {
        String id = conf.getValue("//core/id/text()");
        webConfig.setId(id);
    }

    private void buildSecurityConfig(WebConfig webConfig, Configurable conf)
        throws Exception
    {
        boolean isDoubleLogonEnabled = false;
        SecurityConfig securityConfig = new SecurityConfig();
        String tmp = conf.getValue("//security/double-logon-checking/text()");
        if("TRUE".equalsIgnoreCase(tmp))
            securityConfig.setIsDoubleLogonOnEnabled(true);
        webConfig.setSecurityConfig(securityConfig);
    }

    private void buildErrorConfig(WebConfig webConfig, Configurable conf)
        throws Exception
    {
        ErrorConfig errConfig = null;
        String errConfigs[] = conf.getValues("//error-config/error");
        if(errConfigs != null)
        {
            String exception = null;
            String code = null;
            String isErrorCodeDisplayed = null;
            String isErrorRefDisplayed = null;
            for(int j = 0; j < errConfigs.length; j++)
            {
                exception = conf.getValue("//error-config/error[" + (j + 1) + "]/@exception");
                code = conf.getValue("//error-config/error[" + (j + 1) + "]/@code");
                isErrorCodeDisplayed = conf.getValue("//error-config/error[" + (j + 1) + "]/@isErrorCodeDisplayed");
                isErrorRefDisplayed = conf.getValue("//error-config/error[" + (j + 1) + "]/@isErrorRefDisplayed");
                errConfig = new ErrorConfig(code, exception, Boolean.valueOf(isErrorCodeDisplayed).booleanValue(), Boolean.valueOf(isErrorRefDisplayed).booleanValue());
                webConfig.addErrConfig(errConfig);
            }

        }
    }

    private void buildMCSConfig(WebConfig webConfig, Configurable conf, Configurable mcsConf)
        throws Exception
    {
        MCSConfig mcsConfig = new MCSConfig();
        String tmp = conf.getValue("//system/mcs-checking/text()");
        if("TRUE".equalsIgnoreCase(tmp))
            mcsConfig.setIsMCSCheckingEnabled(true);
        String tmpIP = mcsConf.getValue("//mcs/msg/server/hostname/text()");
        mcsConfig.setHostname(tmpIP);
        webConfig.setMCSConfig(mcsConfig);
    }

    private void buildECertConfig(WebConfig webConfig, Configurable conf)
        throws Exception
    {
        ECertConfig ecertConfig = new ECertConfig();
        String tmpIP = conf.getValue("//ecert-authentication/hostname/text()");
        ecertConfig.setHostname(tmpIP);
        String tmpPort = conf.getValue("//ecert-authentication/port/text()");
        ecertConfig.setPort(tmpPort);
        String tmpAppUrl = conf.getValue("//ecert-authentication/app_url/text()");
        ecertConfig.setAppUrl(tmpAppUrl);
        webConfig.setECertConfig(ecertConfig);
    }

}
