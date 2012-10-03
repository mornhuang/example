package com.itsz.sht.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.AbstractRequestModel;
import com.itsz.sht.struts.form.ITSZForm;
public class DataModelUtil {


	public static void form2Model(HttpServletRequest request,
			ITSZForm origForm, AbstractRequestModel destModel, HttpServletResponse response) {
		try {
			BeanCopyUtils.copyProperties(destModel, origForm);
			if(StringUtils.isBlank(origForm.getCLV())) origForm.setCLV(CLVSplitUtil.transNullCLV(request,response,origForm,""));
		} catch (Exception e) {
//			e.printStackTrace();
		}finally{
			destModel.setChannelType(CLVSplitUtil.getChannelType(origForm.getCLV()));
			destModel.setLanguage(CLVSplitUtil.getLanguage(origForm.getCLV()));
			destModel.setVersionId(String.valueOf(CLVSplitUtil.getVersionID(origForm.getCLV())));
			destModel.setChannelId(CLVSplitUtil.getChannelId(origForm.getCLV()));
		}
	}

	public static Object model2Response(AbstractRequestModel origModel, Object destResponse) {
		try {
			BeanUtils.copyProperties(destResponse, origModel);
		} catch (Exception e) {

		}
		return destResponse;
	}
	
	
	public static void request2Model(HttpServletRequest request,AbstractRequestModel destModel) {
		String clv = request.getParameter(Consts.Global.Common.CLV_NAME);
		if(StringUtils.isBlank(clv)){
			clv = (String)request.getSession().getAttribute(Consts.Global.Common.CLV_NAME);
		}
		destModel.setChannelId(CLVSplitUtil.getChannelId(clv));
		destModel.setChannelType(CLVSplitUtil.getChannelType(clv));
		destModel.setLanguage(CLVSplitUtil.getLanguage(clv));
		destModel.setVersionId(String.valueOf(CLVSplitUtil.getVersionID(clv)));
	}
	

}
