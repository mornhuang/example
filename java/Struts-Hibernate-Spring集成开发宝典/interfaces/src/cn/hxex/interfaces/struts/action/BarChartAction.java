//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package cn.hxex.interfaces.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.interfaces.struts.datasource.DataSources;

/** 
 * MyEclipse Struts
 * Creation date: 05-11-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="display" path="/barchart.jsp"
 */
public class BarChartAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		List weekWeather = DataSources.getWeekForecast();
		request.setAttribute( "weekWeather", weekWeather );
		
		return mapping.findForward( "display" );
	}

}

