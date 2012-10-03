package com.itsz.sht.struts.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.request.AddProductRequestModel;
import com.itsz.sht.common.model.common.request.ChangeNoEmailProductRequestModel;
import com.itsz.sht.common.model.common.request.ChangeRTQ_SHKProductRequestModel;
import com.itsz.sht.common.model.common.request.MonthlyPurchaseEnquiryRequestModel;
import com.itsz.sht.common.model.common.request.ReserveAndRenewalEnquiryRequestModel;
import com.itsz.sht.common.model.common.request.ServiceProductRequestModel;
import com.itsz.sht.common.model.common.response.ListServiceProductResponseModel;
import com.itsz.sht.common.model.common.response.MonthlyPurchaseEnquiryResponseModel;
import com.itsz.sht.common.model.common.response.ReserveAndRenewalEnquiryResponseModel;
import com.itsz.sht.common.model.common.response.ServiceProductResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.dao.hibernate.model.RtqApplication;
import com.itsz.sht.model.ReserveAndRenewalEnquiry;
import com.itsz.sht.struts.form.ProductForm;
import com.taifook.adminportal.common.util.page.Page;

public class ProductAction extends ITSZAction {


	public ActionForward goAddProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<LabelValueBean> productList =new ArrayList<LabelValueBean>();
	    List<RtqApplication> appList=facade.listRTQApplication().getRtqApplication();
	    for (RtqApplication app : appList) {
		    productList.add(new LabelValueBean(app.getProdId(),app.getProdId()));
	    }
		request.setAttribute("productList", productList);
		
		String returnCode = "SUCCESS";
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward addProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductForm pform = (ProductForm) form;
		Product product = new Product();
		String productId=pform.getProdId();
		product.setProdId(pform.getProdId());
		product.setProdStatus(pform.getProdStatus());
		if("".equals(pform.getAlltQuota())){
			product.setAlltQuota(Long.valueOf(0));
		}else{
			product.setAlltQuota(Long.valueOf(pform.getAlltQuota()));
		}
		product.setSvcMode(pform.getSvcMode());
		product.setValtOfSvc(pform.getValtOfSvc());
		if("".equals(pform.getAcesUnit())){
			product.setAcesUnit(Long.valueOf(0));
		}else{
			product.setAcesUnit(Long.valueOf(pform.getAcesUnit()));
		}
		
		product.setDiscType(pform.getDiscType());
		if("".equals(pform.getPriceInHkd()))
		{
			product.setPriceInHkd(Long.valueOf(0));
		}else{
			product.setPriceInHkd(Long.valueOf(pform.getPriceInHkd()));
		}
		
		Date effDate = new Date();
		Date exprDate = new Date();
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    if(productId.equals("NO_EMAIL")){
	    effDate=format.parse("1970-1-1 00:00:00");
	    product.setBillType("FREE");
	    product.setSvcMode("PER_CLICK");
	    product.setValtOfSvc("NO_EXPIRY");
	    product.setProdStatus("AVAIL");
	    }
	    exprDate=format.parse("2049-12-31 23:59:59");
		product.setEffDate(effDate);
		product.setExprDate(exprDate);
	   int a=productId.lastIndexOf("_");
	   String lastStr=productId.substring(a+1, productId.length());
	   if("CN".equals(lastStr)){
		   product.setDiscType("PRC");
	   }else{
		   product.setDiscType("NONE");
	   }
		product.setRemarks(pform.getRemarks());	
		AddProductRequestModel pr=new AddProductRequestModel();
		pr.setProduct(product);
		String returnCode = "";
		returnCode=facade.addProduct(pr).getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			List<LabelValueBean> productList =new ArrayList<LabelValueBean>();
		    List<RtqApplication> appList=facade.listRTQApplication().getRtqApplication();
		    for (RtqApplication app : appList) {
			    productList.add(new LabelValueBean(app.getProdId(),app.getProdId()));
		    }
			request.setAttribute("productList", productList);
			String returnMessage="";
			if(returnCode.equals(Consts.Error.Code.ADMINPROTAL_PRODUCT_EXISTED)){
				returnMessage="Product has existed!";
			}else{
				returnMessage = "Product add failure!";
			}
			request.setAttribute("returnMessage", returnMessage);
			return mapping.findForward("failure");
		}
	}

	public ActionForward showProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String prodId =(String)request.getParameter("prodId");
		ServiceProductRequestModel req=new ServiceProductRequestModel();
		req.setProductId(prodId);		
		ServiceProductResponseModel res= facade.findServiceProductByProductId(req);
		String returnCode=res.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			Product product= res.getProduct();
			request.setAttribute("product", product);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward goUpdateProduct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String prodId =(String)request.getParameter("prodId");
		boolean isNoEmail=false;
		if(prodId.equals("NO_EMAIL")){
			isNoEmail=true;
		}
		ServiceProductRequestModel req=new ServiceProductRequestModel();
		req.setProductId(prodId);		
		ServiceProductResponseModel res= facade.findServiceProductByProductId(req);
		String returnCode=res.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			Product product= res.getProduct();
			request.setAttribute("product", product);
			request.setAttribute("isNoEmail", isNoEmail);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward updateProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		ProductForm pform = (ProductForm) form;
		String productId=pform.getProdId();
		String returnCode = "";
		if(productId.equals("NO_EMAIL")){
			ChangeNoEmailProductRequestModel reqModel=new ChangeNoEmailProductRequestModel();
			reqModel.setActionBy(user.getLognId());
			reqModel.setProductId(productId);
			reqModel.setRemarks(pform.getRemarks());
			returnCode = facade.updateNoEmailProduct(reqModel).getReturnCode();
		}else{
			ChangeRTQ_SHKProductRequestModel rtqProduct=new ChangeRTQ_SHKProductRequestModel();
			rtqProduct.setPriceInHKD(Long.valueOf(pform.getPriceInHkd()));
			rtqProduct.setProductId(pform.getProdId());
			rtqProduct.setProductStatus(pform.getProdStatus());
		//	rtqProduct.setQuota(Long.valueOf(pform.getQuota()));
			rtqProduct.setRemarks(pform.getRemarks());
			rtqProduct.setActionBy(user.getLognId());
			returnCode = facade.updateRTQ_SHKProduct(rtqProduct).getReturnCode();		
		}
		
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {		
			return mapping.findForward("success");
		} else {
			request.setAttribute("prodId", productId);
			if(returnCode.equals(Consts.Error.Code.ADMINPROTAL_PRODUCT_CANNOT_CHANGE_PRICE)){
				request.setAttribute("productMessage", returnCode);
			} else {
				request.setAttribute("productMessage", "Update Product:" + productId + " Failure!");
			}
			return mapping.findForward("failure");
		}
	}

	public ActionForward listProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ListServiceProductResponseModel model=	facade.listServiceProduct();
        List<Product> productList= model.getProductList();
		String returnCode = model.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("productList", productList);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	
	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
	
	public ActionForward monthlyPurchaseEnquiry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<Product> productVList=facade.listServiceProduct().getProductList();
		List<LabelValueBean> productList=new ArrayList<LabelValueBean>();
		productList.add(new LabelValueBean("请选择",""));
		for (Product product : productVList) {
			if(!product.getProdId().equals("NO_EMAIL"))
				productList.add(new LabelValueBean(product.getProdId(),product.getProdId()));
		}
		
		List<LabelValueBean> monthList=new ArrayList<LabelValueBean>();
		for (int i=1;i<13;i++) {
			monthList.add(new LabelValueBean(i+"",String.valueOf(i)));
		}
		List<LabelValueBean> yearList=new ArrayList<LabelValueBean>();
		for (int i=2010;i<2020;i++) {
			yearList.add(new LabelValueBean(i+"",String.valueOf(i)));
		}
		String productId=request.getParameter("productId");
		ProductForm pform = (ProductForm) form;
		if(productId==null||productId.equals("")){
			productId=pform.getProdId();
		}else{
			pform.setProdId(productId);
		}
		String startYear=pform.getStartYear();
		String startMonth=pform.getStartMonth();
		String startYearMonth="";
		if(startYear==null || startMonth==null)
		{ 
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			startYear = String.valueOf(calendar.get(Calendar.YEAR));
			startMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
			pform.setStartYear(startYear);
			pform.setStartMonth(startMonth);
		}
		startYearMonth=startYear+"-"+startMonth;
		MonthlyPurchaseEnquiryRequestModel reqModel=new MonthlyPurchaseEnquiryRequestModel();
		reqModel.setYearMonth(startYearMonth);
		reqModel.setProductId(productId);
		int pageNumber = getPageNumber(request); // get current page
		int pageSize = getPageSize(); // get page size;
		reqModel.setPageNumber(pageNumber);
		reqModel.setPageSize(pageSize);
		String returnCode="";
		MonthlyPurchaseEnquiryResponseModel resModel=facade.monthlyPurchaseEnquiry(reqModel);
		returnCode=resModel.getReturnCode();
		request.setAttribute("productList", productList);
		request.setAttribute("yearList", yearList);
		request.setAttribute("monthList", monthList);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("page", resModel.getPage());
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	
	public ActionForward reserveAndRenewalEnquiry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<Product> productVList=facade.listServiceProduct().getProductList();
		List<LabelValueBean> productList=new ArrayList<LabelValueBean>();
		productList.add(new LabelValueBean("请选择",""));
		for (Product product : productVList) {
			if(!product.getProdId().equals("NO_EMAIL"))
				productList.add(new LabelValueBean(product.getProdId(),product.getProdId()));
		}
		request.setAttribute("productList", productList);
		String productId=request.getParameter("productId");
		ProductForm pform = (ProductForm) form;
		if(productId==null||productId.equals("")){
			productId=pform.getProdId();
		}else{
			pform.setProdId(productId);
		}
		ReserveAndRenewalEnquiryRequestModel requestModel=new ReserveAndRenewalEnquiryRequestModel();
		int pageNumber = getPageNumber(request); // get current page
		int pageSize = getPageSize(); // get page size;
		requestModel.setPageNumber(pageNumber);
		requestModel.setPageSize(pageSize);
		requestModel.setProductId(productId);
		ReserveAndRenewalEnquiryResponseModel resModel=	facade.findReserveAndRenewalEnquiry(requestModel);
		String returnCode = "";
		returnCode=resModel.getReturnCode();
		List<ReserveAndRenewalEnquiry> reserveAndRenewalEnquiryList=new ArrayList<ReserveAndRenewalEnquiry>();
		Page page=resModel.getPage();
		if(page!=null){
		List list=	(List) page.getThisPageElements();
	    if(list!=null&&list.size()>0){
	    	 for(int a=0;a<list.size();a++){
				 Object[] obj=(Object[]) list.get(a);
				 ReserveAndRenewalEnquiry reserveAndRenewalEnquiry=new ReserveAndRenewalEnquiry(String.valueOf(obj[0]),String.valueOf(obj[1]),String.valueOf(obj[2]),String.valueOf(obj[3]),String.valueOf(obj[4]),String.valueOf(obj[5]),String.valueOf(obj[6]));
				 reserveAndRenewalEnquiryList.add(reserveAndRenewalEnquiry);
			 }
	      }
	    }
	    request.setAttribute("reserveAndRenewalEnquiryList", reserveAndRenewalEnquiryList);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("page", resModel.getPage());
			return mapping.findForward("success");
		} else {
			
			return mapping.findForward("failure");
		}
	}
	
}
