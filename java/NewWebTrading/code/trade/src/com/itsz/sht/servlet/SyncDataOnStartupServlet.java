/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.UserManagement;
import com.itsz.sht.servlet.util.SyncDataCallBack;
import com.taifook.adminportal.exceptions.SocketTransferException;
import com.taifook.common.socket.ClientSocketService;
import com.taifook.common.socket.SocketMessage;

public class SyncDataOnStartupServlet extends HttpServlet {
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SyncDataOnStartupServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		try {
			log_debug.info("Sync Data On Tomcat Startup.....");
			SocketMessage socketMessage = new ClientSocketService().startSend(new SocketMessage(Constants.SYNC_DATA, Constants.GET_DATA_ACTION,null,new SyncDataCallBack()),true);
			HashMap channelMap = (HashMap)socketMessage.getContext();
			
			//print all the sync data
			String[] channels = {"WEB","H3G","STT"};
			for(int i = 0;i<channels.length;i++){
				String channelType = channels[i];
				if((channelMap.containsKey(channelType))){
					HashMap map  = (HashMap) channelMap.get(channelType);
					Set set = map.keySet();
					Iterator it = set.iterator();
					while(it.hasNext()){
						String s = (String) it.next();
						System.out.println(channelType + "\t" + s + "\t" + map.get(s));
					}
				}
			}
			
			UserManagement.setChannelMap(channelMap);
			log_debug.info("Sync Data On Tomcat Startup Success.");
		} catch (SocketTransferException e) {
			log_debug.warn("Sync Data Action:Sent Sync Request SocketTransferException");
			log_debug.warn(e.getMessage());
		} catch (Exception e) {
			log_debug.warn("Sync Data Action:Sent Sync Request Exception");
			log_debug.warn(e.getMessage());			
		}
	}
}
