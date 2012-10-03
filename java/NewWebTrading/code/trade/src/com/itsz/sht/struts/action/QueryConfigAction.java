package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.response.ConfigInfo;
import com.itsz.sht.struts.form.QueryConfigForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: QueryConfigAction.java,v 1.1 2010/12/01 06:33:15 kyzou Exp $
 * @Project:portal
 * @File:QueryParamAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-3-25
 */
public class QueryConfigAction extends ITSZAction {
	
	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		QueryConfigForm configForm = (QueryConfigForm)form;
		ProcessBean processBean = new ProcessBean(getConfigResults(configForm), null,mapping, request,response);
		return vp.processQueryConfig(processBean);
	}
	
	private Collection getConfigResults(QueryConfigForm configForm){
		String[] configKeys = StringUtils.split(configForm.getConfigKey(), Consts.Global.Common.FORM_PARAM_SEPARATOR_CHAR);
		Collection configResults = new ArrayList();
		if(configKeys==null || configKeys.length==0){
			return configResults;
		}
		if(configKeys.length==1 && Consts.Global.Common.CONFIG_PARAM_ALL.equalsIgnoreCase(configKeys[0])){
			return PropertyConfig.getAllCommonProperty();
		}
		for (int i = 0; i < configKeys.length; i++) {
			String configValue = PropertyConfig.getCommonProperty(configKeys[i]);
			if(StringUtils.isNotBlank(configValue)){
				configResults.add(new ConfigInfo(configKeys[i],configValue));
			}
		}
		return configResults;
	}

}
