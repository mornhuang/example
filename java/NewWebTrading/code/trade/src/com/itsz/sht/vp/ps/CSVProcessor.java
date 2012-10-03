/**
 * <p>Title: 3G Portal</p>
 * <p>Description: 3G Portal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.vp.ps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;
import com.taifook.mcs.core.beans.msg.MCSMessage;
import com.taifook.mtss.core.parser.csv.binding.BeanToCSVConverter;
import com.taifook.mtss.core.parser.csv.binding.BindingConfig;
import com.taifook.mtss.core.parser.csv.binding.BindingNode;


public class CSVProcessor {
    private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
    private BeanToCSVConverter bean2csv;
    public CSVProcessor() throws Exception{
        try {
            BindingNode config=BindingConfig.getInstance()
            .readConfig(getClass().getClassLoader().getResourceAsStream("ESV-binding.xml"));   
            bean2csv=new BeanToCSVConverter(config);
          } catch (Exception e){
            log_debug.info("Unable to initialize the MessageProcessor");
            throw new Exception();
          }	 
    }
     
    public String BeanToCSV(Object result) throws Exception {
        MCSMessage message;
        String csv = "";
        message = (MCSMessage) result;
        csv = bean2csv.buildCSV(message);
        return csv;
    }
    
    public static void main(String[] args) {
//        System.out.println("there you will be");
//        PointsResponse points=new PointsResponse();
//        points.setPoints("44");
//		CSVProcessor processor;
//		String csv="";
//        try {
//            processor = new CSVProcessor();
//            csv = processor.BeanToCSV(points);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(csv);
    }
}
