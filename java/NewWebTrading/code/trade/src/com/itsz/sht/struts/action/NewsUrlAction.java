//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.common.AFXNewsInfo;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class NewsUrlAction extends ITSZAction {
    public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        ITSZForm actionform = (ITSZForm) form;
        String clv = actionform.getCLV();
        String language=CLVSplitUtil.getLanguage(clv);
        if (language == null ) {
            ITSZException ex = new ITSZException();
            ex.setErrorCode(com.itsz.sht.common.Constants.ERRORCODE_PARAMETER_MCS);
            ProcessBean processBean = new ProcessBean(ex, null, mapping, request, response);
            return vp.processException(processBean);                   
        }
        AFXNewsInfo news = new AFXNewsInfo();
        String afxNews;
        String afxNewsByStockCode;
       if(Constants.LANG_BIG5.equals(language)){
           afxNews = PropertyConfig.getCommonProperty(Constants.afxnewsBIG5);
           afxNewsByStockCode = PropertyConfig.getCommonProperty(Constants.afxnewsByStockCodeBIG5);
           news.setAfxNews(afxNews);
           news.setAfxNewsByStock(afxNewsByStockCode);
       }else if(Constants.LANG_GB.equals(language)){
           afxNews = PropertyConfig.getCommonProperty(Constants.afxnewsGB);
           afxNewsByStockCode = PropertyConfig.getCommonProperty(Constants.afxnewsByStockCodeGB);
           news.setAfxNews(afxNews);
           news.setAfxNewsByStock(afxNewsByStockCode);
       }else if(Constants.LANG_ENG.equals(language)){
           afxNews = PropertyConfig.getCommonProperty(Constants.afxnewsEN);
           afxNewsByStockCode = PropertyConfig.getCommonProperty(Constants.afxnewsByStockCodeEN);
           news.setAfxNews(afxNews);
           news.setAfxNewsByStock(afxNewsByStockCode);
       }
       ProcessBean processBean = new ProcessBean(news, null, mapping, request, response);
       return vp.processNewsUrl(processBean);
                
    }

}