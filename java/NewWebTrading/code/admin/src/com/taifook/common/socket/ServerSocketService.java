package com.taifook.common.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.common.Constants;

public class ServerSocketService extends Thread {

	private static ServerSocketService notifySynServer = null;

	private static Log log = LogFactory.getLog(ServerSocketService.class);

	private int port = 0;

	private int timeout = 0;

	private boolean isRunFlag = false;

	private ServerSocket server;

	private ServerSocketService() throws Exception {
		try {
			List cfgList = SynDataCfgManager.getInstance().getServerConfig();
			if(cfgList.isEmpty()){
				throw new Exception("++++++++++++++++none server config in configuration file!++++++++++++++++");
			}
			SynDataCfgInfo sfg = (SynDataCfgInfo) cfgList
					.get(cfgList.size() - 1);
			port = sfg.getPort();
			timeout = sfg.getTimeout() * 1000;
		} catch (Exception e) {
			throw new Exception("++++++++++++++++init SynData Server Exception++++++++++++++++");
		}
	}

	public void run() {
		// ���̨���ͬ���������?
		try {
			startservice();
		} catch (IOException e) {
			log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
					.format(new Date())
					+ "syn server start fail.");
		}
	}

	private void startservice() throws IOException {
		try{
			server = new ServerSocket(port);
		}catch(IOException e){
			throw new IOException("++++++++++++++++start SynData Server Exception++++++++++++++++");
		}
		isRunFlag = true;
		log.info("==========" + server.toString() + "==============");

		log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.format(new Date())
				+ "syn server started, port in " + port);
		while (this.isRunFlag) {
			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
					.format(new Date())
					+ "syn server starting listener request......");
			try {
				Socket client = server.accept();

				log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
						.format(new Date())
						+ "syn server accpet a request......");

				ProcessRequestService processRequestThread = new ProcessRequestService(
						client);
				processRequestThread.start();

				// processRequestThread.setRunning(true);

			} catch (Exception e) {
				log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
						.format(new Date())
						+ "syn server process request exception," + e.getMessage());
			}

		}
	}

	public void stopService() throws IOException {
		this.isRunFlag = false;
		if (this.server != null && !this.server.isClosed()) {
			this.server.close();
		}
		this.server = null;
		log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.format(new Date())
				+ "++++++++++++++syn server stopped++++++++++++++");
	}

	public static synchronized ServerSocketService getInstance() throws Exception{
		if (notifySynServer == null) {
			notifySynServer = new ServerSocketService();			
		}
		return notifySynServer;
	}

	// =======================inner ProcessRequestService class
	// ======================================
	// ������Ҫ����ɺ�ִ̨�����ͬ���߳�,ֻΪNotifySynServer���ṩ����
	private class ProcessRequestService extends Thread {
		private Socket client;

		private boolean runFlag = false;

		private boolean isCancel = false;

		public synchronized void setRunning(boolean flag) {
			this.runFlag = flag;
			if (this.runFlag) {
				this.notify();
			}
		}

		public synchronized void cancel() {
			this.isCancel = true;
			this.notify();
			log.info("==========" + server.toString()
					+ "'s sysn thread is cancel==============");
		}

		public boolean isRunning() {
			return this.runFlag;
		}

		private ProcessRequestService() {

		}

		private ProcessRequestService(Socket client) {
			this.client = client;
		}

		private void setSocketClient(Socket client) {
			this.client = client;
		}

		public void run() {
			// while (true) {
			try {
				/*
				 * if (!this.runFlag) { this.wait(); }
				 * 
				 * if (this.isCancel) { return; }
				 */

				processRequest();

				// this.setRunning(false);
			} catch (Exception e) {
				// this.setRunning(false);
			}
			// }

		}

		private void processRequest() {
			log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
					.format(new Date())
					+ "syn server starting accept data from request......");

			ObjectInputStream in = null;
			ObjectOutputStream out = null;
			try {
				this.client.setSoTimeout(timeout);
				in = new ObjectInputStream(new BufferedInputStream(client
						.getInputStream()));
				SocketMessage msg = (SocketMessage) in.readObject();

				log.info("accepted data>>>>[notifyObject: " + msg.getNotifyObject()
						+ " action: " + msg.getAction() + " context: "
						+ msg.getContext()+"]");

				SocketMessage respMsg = msg.getCallBack().execute(msg);

				out = new ObjectOutputStream(new BufferedOutputStream(client
						.getOutputStream()));
				out.writeObject(respMsg);
				out.flush();
				log.info("syn server process request finished.");

			} catch (ClassNotFoundException e) {
				try {
					out.writeObject(new DefaultFailSocketMessage(e.getMessage()));
					out.flush();
				} catch (IOException e1) {

				}
				log.error("syn server process data error." + e.getMessage());
			} catch (Exception e) {
				try {
					out.writeObject(new DefaultFailSocketMessage(e.getMessage()));
					out.flush();
				} catch (IOException e1) {

				}
				log.error("syn server process data error." + e.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
					in = null;
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {

					}
					out = null;
				}
				if (this.client != null) {
					try {
							this.client.close();
						} catch (IOException e) {
					}
					this.client = null;
				}
			}

		}
	}

}
