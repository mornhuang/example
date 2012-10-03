//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.useraction.actions;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

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
public class CreateUserActionChartAction extends BaseUserActionAction {

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
			log.error("UserActionChartAction-executeAction:login count by time Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,
					"create user action chart fail! " + e.getMessage());
			forward = Constants.FAILURE;
		}
		request.setAttribute("accesstime", accesstime);
		return mapping.findForward(forward);
	}

	private File getChart(HttpSession session, List result) {
		Log log = LogFactory.getLog(this.getClass());
		File file = null;
		String YAxis_lable = "";

		try {
			// total count
			long totalCount = 0;

			for (int i = 0; i < result.size(); i++) {
				Object[] obj = (Object[]) result.get(i);
				totalCount += ((Integer) obj[2]).intValue();
			}

			if (totalCount > 0) {
				YAxis_lable = "User Count";
			} else {
				YAxis_lable = "User Count";
			}

			XYDataset dataset = createDataset(result);

			JFreeChart chart = ChartFactory.createTimeSeriesChart(
					"User Action Chart for Day", // chart title
					"Access Time(/h)", // domain axis label
					YAxis_lable, // range axis label
					dataset, // data
					true, // include legend
					true, // tooltips
					false // urls
					);

			chart.setBackgroundPaint(new Color(232, 237, 241));
			XYPlot plot = (XYPlot) chart.getPlot();
			plot.setRangeGridlinePaint(Color.white);
			plot.setBackgroundPaint(new Color(165, 183, 197));
			plot.setDomainGridlinePaint(Color.white);
			plot.setRangeGridlinePaint(Color.white);
			plot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
			plot.setNoDataMessage("No data available");
			plot.setDomainCrosshairVisible(true);
			plot.setRangeCrosshairVisible(true);
			org.jfree.chart.renderer.xy.XYItemRenderer r = plot.getRenderer();
			if (r instanceof XYLineAndShapeRenderer) {
				XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
				renderer.setBaseShapesVisible(true);
				renderer.setBaseShapesFilled(true);
			}
			DateAxis axis = (DateAxis) plot.getDomainAxis();
			axis.setDateFormatOverride(new SimpleDateFormat("HH"));

			String dateStr = String.valueOf(new Date().getTime());
			file = new File(session.getAttribute(Constants.TEMP_FILE_PATH)
					.toString()
					+ "/useractionchart" + dateStr + ".png");
			Object tempObj = session.getAttribute("USER_ACTION_CHART_FILE");
			if (tempObj != null && tempObj instanceof File) {
				File prevfile = (File) tempObj;
				if (prevfile.exists()) {
					prevfile.delete();
				}
			}

			if (file.exists()) {
				file.delete();
			}

			FileOutputStream fos = new FileOutputStream(file);
			ChartUtilities.writeChartAsPNG(fos, chart, 750, 400);
			fos.flush();
			fos.close();
			fos = null;
			// filename = ServletUtilities.saveChartAsPNG(chart, 750, 400, null,
			// session);
		} catch (Exception e) {
			log.warn(e);
			file = null;
//			e.printStackTrace();
		}
		return file;
	}

	private XYDataset createDataset(List result) {		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		if (result.size() > 0) {
			TimeSeries series = null;
			Map Xmap = new HashMap();			
			for (int i = 0; i < result.size(); i++) {
				Object[] obj = (Object[]) result.get(i);
				Date operatTime = ((Date) obj[1]);
				int count = ((Integer) obj[2]).intValue();
				// ÿһ��UserAction��ͼ����һ������
				if (!Xmap.containsKey(obj[0])) {
					series = new TimeSeries(obj[0].toString(), Hour.class);
					for (int j = 0; j < 24; j++) {
						series.addOrUpdate(new Hour(j,operatTime.getDate(),operatTime.getMonth()+1,operatTime.getYear()+1900), new Double(0));
					}
					Xmap.put(obj[0], series);
				} else {
					series = (TimeSeries) Xmap.get(obj[0]);
				}	
				Hour hourIndex=new  Hour(operatTime.getHours(),operatTime.getDate(),operatTime.getMonth()+1,operatTime.getYear()+1900);
				double countUser=0;
				try{
					countUser=((Double)series.getValue(hourIndex)).doubleValue()+count;
				}catch(Exception e ){
					countUser=count;
				}
				series.addOrUpdate(hourIndex, new Double(countUser));					
			}

			Set keySet = Xmap.keySet();
			Iterator it = keySet.iterator();
			while (it.hasNext()) {
				dataset.addSeries((TimeSeries) Xmap.get(it.next()));
			}
			dataset.setDomainIsPointsInTime(true);
		}
		
		
		return dataset;
	}

}
