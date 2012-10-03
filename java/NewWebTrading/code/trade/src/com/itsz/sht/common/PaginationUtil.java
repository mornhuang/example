package com.itsz.sht.common;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * $Id: PaginationUtil.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:PaginationUtil.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-7
 */
public class PaginationUtil {

	private static final Logger logger = Logger.getLogger(PaginationUtil.class);
	
	public static final String PageationPageAttribute = "page";
	public static final String CurrentPageAttribute = "currentPage";
	public static final String PageAmountAttribute = "pageAmount";
	public static final String ItemAmountAttribute = "itemAmount";
	public static final String PageMaxSizeAttribute = "pageMaxSize";

	
	/**
	 * 鍒嗛〉鏍稿績鏂规硶
	 * @param list
	 * @param beginIndex
	 * @param size
	 * @return
	 * @return List
	 */
	public static List pagination(List list,int beginIndex,int size){
		List result = new ArrayList();
		if(beginIndex < 0)beginIndex = 0;
		if(list != null && size > 0){
			if(beginIndex > list.size() ){
				beginIndex = getMaxIndex(list,size);
			}
			int maxIndex = beginIndex + size;
			if(maxIndex > list.size() ){
				maxIndex = list.size() ;
			}
			for(int i=beginIndex;i<maxIndex ;i++){
				result.add( list.get(i) );
			}
		}
		return result;
	}
	
	/**
	 * 鑾峰緱鍚绱㈠紩鍙�
	 * @param pageIndex
	 * @param pageMaxSize
	 * @return
	 * @return int
	 */
	public static int getBeginIndex(int pageIndex , int pageMaxSize){
		return pageIndex * pageMaxSize ;
	}
	/**
	 * 鑾峰緱褰撳墠瑕佹樉绀虹殑椤�
	 * @param request
	 * @return
	 * @return int
	 */
	public static int getCurrentPage(HttpServletRequest request){
		String param = request.getParameter( PageationPageAttribute );
		int page = 1;
		
		try{
			page=Integer.parseInt( param );
			if(page < 1 )page = 1;
		}catch(Exception e){
			logger.debug("Pageation's 'currentPage' Format Exception; Set Current Page : " + page);
		}
		return page ;
	}
	
	/**
	 * 鑾峰緱鎬婚〉鏁�
	 * @param list
	 * @param pageMaxSize
	 * @return
	 * @return int
	 */
	public static int getMaxIndex(List list ,int pageMaxSize){
		int result = 0;
		int pageAmount = getPageAmount(list,pageMaxSize);
		result = getBeginIndex(pageAmount,pageMaxSize);
		return result;
	}
	public static int getPageAmount(List list,int pageMaxSize){
		int result = 0;
		if(list != null){
			int size = list.size();
			result = getPageAmount(size,pageMaxSize);
		}
		return result;
	}
	
	public static int getPageAmount(int itemAmount,int pageMaxSize){
		int size = itemAmount;
		int result = (size % pageMaxSize)==0?(size / pageMaxSize):(size / pageMaxSize + 1);
		return result;
	}
	/**
	 * 鍒嗛〉
	 * @param list
	 * @param request
	 * @param pageMaxSize
	 * @return
	 * @return List
	 */
	public static List pagination(List list,HttpServletRequest request,int pageMaxSize){
		
		int currentPage = getCurrentPage(request);
		int pageAmount = getPageAmount(list,pageMaxSize);
		int itemAmount = getItemAmount(list);
		if(currentPage > pageAmount)currentPage=pageAmount;
		int beginIndex = getBeginIndex(currentPage - 1 , pageMaxSize );
		
		setAttribute(request,currentPage,pageAmount,itemAmount,pageMaxSize);
		
		return pagination(list,beginIndex,pageMaxSize);
	}
	
	private static int getItemAmount(List list) {
		int result = 0;
		if(list != null){
			result = list.size() ;
		}
		
		return result;
	}

	/**
	 * 璁惧埗鍒嗛〉鍙傛暟鍒伴〉闈㈡樉绀�
	 * @param request
	 * @param currentPage
	 * @param pageAmount
	 * @param itemAmount
	 * @param pageMaxSize
	 * @return void
	 */
	public static void setAttribute(HttpServletRequest request,int currentPage,int pageAmount,int itemAmount,int pageMaxSize){
		
		setCurrentPage(request,currentPage);
		setPageAmount(request,pageAmount);
		setItemAmount(request,itemAmount);
		setPageMaxSize(request,pageMaxSize);
	}
	public static void setItems(HttpServletRequest request,String attributeName,Collection items){
		request.setAttribute( attributeName,items);
	}
	public static void setPageMaxSize(HttpServletRequest request,int pageMaxSize){
		request.setAttribute( PageMaxSizeAttribute,Integer.valueOf(pageMaxSize));
	}
	public static void setItemAmount(HttpServletRequest request,int itemAmount){
		request.setAttribute(ItemAmountAttribute,Integer.valueOf(itemAmount));
	}
	public static void setPageAmount(HttpServletRequest request,int pageAmount){
		request.setAttribute( PageAmountAttribute,Integer.valueOf(pageAmount));
	}
	public static void setCurrentPage(HttpServletRequest request,int currentPage){
		request.setAttribute( CurrentPageAttribute,Integer.valueOf(currentPage));
	}
	/**
	 * 鍒嗛〉
	 * @param list
	 * @param request
	 * @param attributeName
	 * @param pageMaxSize
	 * @return
	 * @return List
	 */
	public static List pagination(List list,HttpServletRequest request,String attributeName,int pageMaxSize){

		List items = pagination(list,request,pageMaxSize);
		setItems( request,attributeName,items);
		return items;
	}
	
	/**
	 * 鍒嗛〉
	 * @param list
	 * @param request
	 * @param pageMaxSize
	 * @param currentPage
	 * @return
	 * @return List
	 */
	public static List pagination(List list,HttpServletRequest request,int pageMaxSize,int currentPage){

		int pageAmount = getPageAmount(list,pageMaxSize);
		int itemAmount = getItemAmount(list) ;
		if(currentPage > pageAmount)currentPage=pageAmount;
		int beginIndex = getBeginIndex(currentPage - 1 , pageMaxSize ); ;
		

		setAttribute(request,currentPage,pageAmount,itemAmount,pageMaxSize);
		
		return pagination(list,beginIndex,pageMaxSize);	
	}
	
	
	/**
	 * 鍒嗛〉
	 * @param list
	 * @param request
	 * @param attributeName
	 * @param pageMaxSize
	 * @param currentPage
	 * @return
	 * @return List
	 */
	public static List pagination(List list,HttpServletRequest request,String attributeName,int pageMaxSize,int currentPage){

		List items = pagination(list,request,pageMaxSize,currentPage);
		setItems( request,attributeName,items);
		return items;
	}
}
