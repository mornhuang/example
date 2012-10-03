//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.useraction.actions;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.UserActionDAO;

/**
 * MyEclipse Struts Creation date: 04-03-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/jsp/useractionchart.jsp"
 */
public class CreateUserActionChartAction_Bak extends BaseUserActionAction {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Log log = LogFactory.getLog(this.getClass());
		String forward = Constants.SUCCESS;
		HttpSession session = request.getSession();
		String accesstime = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			try {
				accesstime = df.format(df.parse(request
						.getParameter("accesstime")));
			} catch (Exception e) {
				accesstime = df.format(new Date());
//				System.out.println("current time: " + accesstime);
			}

			List result = ((UserActionDAO) ServiceManager
					.getInstance()
					.getService(
							"com.taifook.adminportal.service.UserActionService"))
					.statisticsByTime(accesstime);

			File file = this.getChart(session, result);			
			session.setAttribute("USER_ACTION_CHART_FILE", file);
			forward = Constants.SUCCESS;
		} catch (Exception e) {
			log
					.error("UserActionChartAction-executeAction:login count by time Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,"create user action chart fail! "+e.getMessage());
			forward = Constants.FAILURE;
		}
		request.setAttribute("accesstime", accesstime);
		return mapping.findForward(forward);
	}

	private File getChart(HttpSession session, List result) {
		Log log = LogFactory.getLog(this.getClass());
		File file = null;
		String YAxis_lable="";

		try {			
			//total count		
			long totalCount=0;
			
			for (int i = 0; i < result.size(); i++) {
				Object[] obj = (Object[]) result.get(i);
				totalCount+= ((Integer) obj[2]).intValue();
			}
			
			if(totalCount>0){
				YAxis_lable="User Rate ("+totalCount+"%)";
			}else{
				YAxis_lable="User Rate (0%)";
			}
			
			CategoryDataset dataset = createDataset(result);					

			JFreeChart chart = ChartFactory.createLineChart(
					"User Action Chart for Day", // chart title
					"Access Time(/h)", // domain axis label
					YAxis_lable, // range axis label
					dataset, // data
					PlotOrientation.VERTICAL, // orientation
					true, // include legend
					true, // tooltips
					false // urls
					);

			chart.setBackgroundPaint(new Color(232,237,241));
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.lightGray);
			plot.setRangeGridlinePaint(Color.white);
			
			plot.setBackgroundPaint(new Color(165,183,197));

			String dateStr=String.valueOf(new Date().getTime());
			file=new File(session.getAttribute(Constants.TEMP_FILE_PATH).toString()+"/useractionchart"+dateStr+".png");			
			Object tempObj=session.getAttribute("USER_ACTION_CHART_FILE");
			if(tempObj!=null && tempObj instanceof File){
				File prevfile=(File)tempObj;
				if(prevfile.exists()){
					prevfile.delete();
				}
			}
			
			if(file.exists()){
				file.delete();
			}

			FileOutputStream fos= new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(fos,chart,750,400);
			fos.flush();
			fos.close();
			fos=null;
			//filename = ServletUtilities.saveChartAsPNG(chart, 750, 400, null,
			//		session);
		} catch (Exception e) {
			log.warn(e);
			file = null;
//			e.printStackTrace();
		}
		return file;
	}

	private CategoryDataset createDataset(List result) {
		// row keys...
		List rowKey = new ArrayList();
		// column keys...
		List columnKey = new ArrayList();
		// data value
		List values = new ArrayList(0);
		
		for (int i = 0; i < 24; i++) {
			columnKey.add(String.valueOf(i));
			values.add(i, new Integer(0));
		}

		for (int i = 0; i < result.size(); i++) {
			Object[] obj = (Object[]) result.get(i);
			long operatTime = ((Date) obj[1]).getHours();
			int count = ((Integer) obj[2]).intValue();
			values.set((int) operatTime % 24, new Integer(count));
			rowKey.add((String) obj[0]);
		}

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < columnKey.size(); i++) {
			for (int j = 0; j < rowKey.size(); j++) {
				dataset.addValue((Integer) values.get(i), (String) rowKey
						.get(j), (String) columnKey.get(i));
			}
		}
		return dataset;
	}

}
