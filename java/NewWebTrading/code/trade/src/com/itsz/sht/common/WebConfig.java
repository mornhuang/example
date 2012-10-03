package com.itsz.sht.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.taifook.mtss.web.common.config:
//            ErrorConfig, SecurityConfig, MCSConfig, ECertConfig

public class WebConfig implements Serializable {

	private static final long serialVersionUID = -6163124973106500149L;
	private String id;
    private Map errConfigs;
    private SecurityConfig securityConfig;
    private MCSConfig mcsConfig;
    private ECertConfig ecertConfig;

    public WebConfig()
    {
        id = null;
        errConfigs = null;
        securityConfig = null;
        mcsConfig = null;
        ecertConfig = null;
        errConfigs = new HashMap();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public ErrorConfig getErrConfigByExceptionName(String exceptionName)
    {
        return (ErrorConfig)errConfigs.get(exceptionName);
    }

    public void addErrConfig(ErrorConfig errConfig)
    {
        errConfigs.put(errConfig.getException(), errConfig);
    }

    public SecurityConfig getSecurityConfig()
    {
        return securityConfig;
    }

    public void setSecurityConfig(SecurityConfig securityConfig)
    {
        this.securityConfig = securityConfig;
    }

    public MCSConfig getMCSConfig()
    {
        return mcsConfig;
    }

    public void setMCSConfig(MCSConfig mcsConfig)
    {
        this.mcsConfig = mcsConfig;
    }

    public ECertConfig getECertConfig()
    {
        return ecertConfig;
    }

    public void setECertConfig(ECertConfig ecertConfig)
    {
        this.ecertConfig = ecertConfig;
    }
}
