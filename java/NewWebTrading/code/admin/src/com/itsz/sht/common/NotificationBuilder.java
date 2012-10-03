package com.itsz.sht.common;

import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;



abstract public class NotificationBuilder {
    protected SimpleHtmlMail mail = new SimpleHtmlMail();
    protected Locale locale;
    protected DateFormat dtFormatter;
    protected DateFormat dFormatter;
    public static final String FORGET_PASSWD="FORGET_PASSWD";
    public static final String RESET_PASSWD = "RESET_PASSWD";
    public NotificationBuilder() {
    }
    
    public static NotificationBuilder getInstance(String toAddr, String appId, String userId) {
        return new RTQAccountNotificationBuilder(toAddr, appId, userId);
    } 
    
    public static NotificationBuilder getInstance(String toAddr, Map noAccountMap) {
        return new RTQAccountNotificationBuilder(toAddr, noAccountMap);
    }
    
    public Mail build() throws MessagingException {
        setLocale();
      //  i18n = I18NUltilizer.getI18N(locale);
        dtFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT,locale);
        dFormatter = DateFormat.getDateInstance(DateFormat.LONG,locale);

        addTo();
        mail.setSubject(buildSubject());

        StringBuffer content = new StringBuffer();
        content.append("<html><body>");
        content.append(buildTitle());
        content.append(buildHeader());
        content.append(buildBody());
        content.append(buildFooter());
        content.append("</body></html>");
        mail.setHtmlMsg(content.toString());
        
        return mail;
    }
    
    public Mail buildAll() throws MessagingException {
        setLocale();
      //  i18n = I18NUltilizer.getI18N(locale);
        dtFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT,locale);
        dFormatter = DateFormat.getDateInstance(DateFormat.LONG,locale);

        addTo();
        mail.setSubject(buildSubject());

        StringBuffer content = new StringBuffer();
        content.append("<html><body>");
        content.append(buildTitle());
        content.append(buildHeader());
        content.append(buildBodyAll());
        content.append(buildFooter());
        content.append("</body></html>");
        mail.setHtmlMsg(content.toString());
        
        return mail;
    }
    
    protected abstract void addTo()  throws MessagingException;
    protected abstract String buildSubject();
    protected abstract void setLocale();
    protected abstract String buildTitle();
    protected abstract String buildHeader();
    protected abstract String buildBody();
    protected abstract String buildBodyAll();
    protected abstract String buildFooter();

 }

class RTQAccountNotificationBuilder extends NotificationBuilder{
    protected String toAddr;
    protected String appId;
    protected String userId;
    protected Map noAccountMap;
    public RTQAccountNotificationBuilder(String toAddr, String appId, String userId){
        if(toAddr == null)
            throw new IllegalArgumentException("RTQAccountNotificationBuilder: toAddr is null !");
        if(appId == null)
            throw new IllegalArgumentException("RTQAccountNotificationBuilder: appId is null!");
        if(userId == null)
            throw new IllegalArgumentException("RTQAccountNotificationBuilder: Userid is Null!");
        this.toAddr = toAddr;
        this.appId = appId;
        this.userId = userId;
        setLocale();
    }
    
    public RTQAccountNotificationBuilder(String toAddr, Map noAccountMap){
        if(toAddr == null)
            throw new IllegalArgumentException("RTQAccountNotificationBuilder: toAddr is null !");
        if(noAccountMap == null)
            throw new IllegalArgumentException("RTQAccountNotificationBuilder: noAccountMap is null!");
        this.toAddr = toAddr;
        this.noAccountMap = noAccountMap;
        setLocale();
    }
    
    protected void addTo() throws MessagingException {
    	/*
    	 * modified by khe 20061212 for uat bug 799 on td
    	 */
    	if(toAddr.indexOf(";") == -1){
			mail.addTo(toAddr, null);
		}else{
			String[] addresses = toAddr.split(";");
			for(int i=0; i < addresses.length; i++){
				String address = addresses[i];
				if(address!=null && !"".equals(address.trim())){
					mail.addTo(address, null);
				}
			}
		}
    }

    protected String buildSubject() {

        return "Insufficient RTQ account in iWeb";
    }

    protected void setLocale() {
        locale = Locale.US;

    }

    protected String buildTitle() {

        return "";
    }

    protected String buildHeader() {

        return "你好， <br><br>";
    }

    protected String buildBody() {
        String body = "iWeb中RTQ账号不足分配，详情如下：<br><br>";
        String appName = null;
        if(appId != null){
        //	appName = "new web Trading  RTQ - "+i18n.getLocalizedString("formdata_subservice-application-name_"+appId,"")+"<br>";
        	appName = "RTQ产品： "+ appId+"<br><br>";
        }else{
        	appName = "未知的RTQ产品。<br><br>";
        }
        String custName = "客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户： "+userId;
        body = body + appName + custName + "<br><br>请尽快处理。";

        return body;
    }
    
    protected String buildBodyAll() {
        String body = "iWeb中RTQ账号不足分配，详情如下：<br><br>";
        if(noAccountMap != null){
        	Iterator iter = noAccountMap.entrySet().iterator();
        	while(iter.hasNext()){ 
        		Map.Entry entry = (Map.Entry)iter.next();
        		String productId = (String) entry.getKey();
        		List<String> clientList = (List<String>) entry.getValue();
        		body += "RTQ产品：                      "+ productId + "<br>";
        		body += "需分配账号的数目：" + clientList.size() + "<br>";
        		body += "需分配账号的客户：";
        		for(String clientId : clientList){
        			body += clientId + "、";
        		}
        		body = body.substring(0, body.length()-1);
        		body += "<br><br>";
        	}
        }else{
        	body += "未知的RTQ产品。<br>";
        }
        body = body + "请尽快处理。";

        return body;
    }


    protected String buildFooter() {

        return "";
    }
 }