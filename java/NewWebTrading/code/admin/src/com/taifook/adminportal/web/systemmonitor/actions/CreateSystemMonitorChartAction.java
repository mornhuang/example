//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.systemmonitor.actions;

import java.awt.Color;
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
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.SystemMonitorDAO;

/**
 * MyEclipse Struts Creation date: 04-03-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/jsp/useractionchart.jsp"
 */
public class CreateSystemMonitorChartAction extends BaseSystemMonitorAction {

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

			List result = ((SystemMonitorDAO) ServiceManager
					.getInstance()
					.getService(
							"com.taifook.adminportal.service.SystemMonitorService"))
					.statisticsByTime(accesstime);

			String filename = this.getChart(session, result);
			session.setAttribute("systemmonitorchart", filename);
			forward = Constants.SUCCESS;
		} catch (Exception e) {
			log.error("SystemMonitorChartAction-executeAction: Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,"create system monitor chart fail! "+e.getMessage());
			forward = Constants.FAILURE;
		}
		request.setAttribute("accesstime", accesstime);
		return mapping.findForward(forward);
	}

	private String getChart(HttpSession session, List result) {
		Log log = LogFactory.getLog(this.getClass());
		String filename = "";
		try {
			final CategoryDataset dataset = createDataset(result);

			final JFreeChart chart = ChartFactory.createLineChart(
					"System Monitor Chart", // chart title
					"Time(/h)", // domain axis label
					"User Count", // range axis label
					dataset, // data
					PlotOrientation.VERTICAL, // orientation
					true, // include legend
					true, // tooltips
					false // urls
					);

			chart.setBackgroundPaint(Color.white);
			final CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.lightGray);
			plot.setRangeGridlinePaint(Color.white);

			filename = ServletUtilities.saveChartAsPNG(chart, 750, 400, null,
					session);
			
		} catch (Exception e) {
			log.warn(e);
			e.printStackTrace();
		}
		return filename;
	}

	private CategoryDataset createDataset(List result) {
		// row keys...
		List rowKey = new ArrayList();
		// column keys...
		List columnKey = new ArrayList();
		// data value
		List values = new ArrayList(0);
		
		rowKey.add(Constants.NORMAL_STATUS);
		rowKey.add(Constants.WARNING_STATUS);
		rowKey.add(Constants.ERROR_STATUS);

		for (int i = 0; i < 24; i++) {
			columnKey.add(String.valueOf(i));
			values.add(i, new Integer(0));
		}

		for (int i = 0; i < result.size(); i++) {
			Object[] obj = (Object[]) result.get(i);
			long accessTime = ((Date) obj[2]).getHours();
			String status = ((String) obj[1]);
			int statuCode = status.equals(Constants.NORMAL_STATUS) ? 1 : status
					.equals(Constants.WARNING_STATUS) ? 0 : -1;
			values.set((int) accessTime % 24, new Integer(statuCode));			
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
