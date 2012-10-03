//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.onlineuser.actions;


import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
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
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.OnLineUserDAO;

/** 
 * MyEclipse Struts
 * Creation date: 03-30-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/jsp/onlineuserchart.jsp"
 */
public class CreateOnlineUserChartAction extends BaseOnlineUserAction {

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

	public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Log log = LogFactory.getLog(this.getClass());  
		String forward;
		try {
			forward = Constants.SUCCESS;
			HttpSession session=request.getSession();			
			File file = this.getPieChart(session);
			session.setAttribute("ONLINE_USER_DETAIL_CHART_FILE",file);
		} catch (Exception e) {
			log.error(e);
			request.setAttribute(Constants.GLOBAL_ERROR,"create online user chart fail! "+e.getMessage());
			forward=Constants.FAILURE;
		}
		
		return mapping.findForward(forward);
	}
	
	private File getPieChart(HttpSession session) throws Exception{
		 Log log = LogFactory.getLog(this.getClass()); 
		File file=null;
		int totaluser=0;
		DefaultPieDataset dpd = new DefaultPieDataset();
		 try {
			 
			 List result=((OnLineUserDAO)ServiceManager.getInstance()
	                        .getService("com.taifook.adminportal.service.OnLineUserService"))
                            .findByChannelCode();
			 for(Iterator it=result.iterator();it.hasNext();)
			 {
				 Object[] temp=(Object[])it.next();
				 String channelcode=(String)temp[0];
				 Integer count=(Integer)temp[1];
				 dpd.setValue(channelcode,count); 
				 totaluser=totaluser+count.intValue();
			 }

			JFreeChart chart = ChartFactory.createPieChart("Channel Server Online User Pie Chart(total user:"+totaluser+")",dpd,true,true,false);
			//chart.setBackgroundPaint(Color.white);
			chart.setBackgroundPaint(new Color(232,237,241));
			PiePlot pieplot = (PiePlot)chart.getPlot();
			    
			pieplot.setLabelFont(new Font("SansSerif",Font.BOLD,12));
			pieplot.setBackgroundPaint(new Color(232,237,241));
			pieplot.setNoDataMessage("No data available");
			pieplot.setCircular(true); 
			pieplot.setLabelGap(0.01D);
			
			String dateStr=String.valueOf(new Date().getTime());
			file=new File(session.getAttribute(Constants.TEMP_FILE_PATH).toString()+"/onlineuserchart"+dateStr+".png");
			
			Object tempObj=session.getAttribute("ONLINE_USER_DETAIL_CHART_FILE");
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
			
			ChartUtilities.writeChartAsPNG(fos,chart,500,300);
			fos.flush();
			fos.close();
			fos=null;
            //filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session);
		   } catch (Exception e) {
			   log.error(e.getMessage());
			   file=null;
			   throw e;
		   }
		return file;
	  }
   
}

