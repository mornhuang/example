package com.taifook.common.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.exceptions.SocketTransferException;

public class ClientSocketService extends Thread {

	private Log log = LogFactory.getLog(ClientSocketService.class);

	public ClientSocketService() {
	}

	public SocketMessage startSend(SocketMessage msg)
			throws SocketTransferException {
		return this.Syn(msg,false);
	}
	
	public SocketMessage startSend(SocketMessage msg,boolean isOnceSuccessReturn)throws SocketTransferException {
		return this.Syn(msg,isOnceSuccessReturn);
	}
	
	//overload method for check channel type 重载一个方法，用于判断是否需要校验channelType
	public SocketMessage startSend(SocketMessage msg,boolean isOnceSuccessReturn,String channelType)throws SocketTransferException {
		return this.Syn(msg, isOnceSuccessReturn, channelType);
	}
	
	//overload method for check channel type 重载一个方法，用于判断是否需要校验channelType
	private SocketMessage Syn(SocketMessage requestMsg, boolean isOnceSuccessReturn,String channelType) throws SocketTransferException {
		SocketMessage respMsg=null;		
		List errorSendIPList=new ArrayList();
		StringBuffer errorMsg = new StringBuffer();
		List cfgList = SynDataCfgManager.getInstance().getClientConfig();
		if(cfgList.isEmpty()){
			throw new SocketTransferException("send to remote portal fail! none client config in configuration file!");
		}
		Iterator it = cfgList.iterator();
		while (it.hasNext()) {
			SynDataCfgInfo sfg = (SynDataCfgInfo) it.next();
			if(StringUtils.isNotBlank(sfg.getTo_channel())){ //指定channel，适应单机时的配置  assign channel,for single
			    if(channelType.equals(sfg.getTo_channel())){
			    	respMsg = sendMessage(requestMsg, isOnceSuccessReturn, respMsg, errorSendIPList, sfg);
					if(respMsg!=null && Constants.SOCKET_TRANSFER_FAIL.equals(respMsg.getNotifyObject())){
						errorMsg.append(" [").append(sfg.getIp()).append(":").append(sfg.getPort()).append("::").append(respMsg.getContext()).append("]");
					}
			    }
		    }
			else{   //没有指定channel，适应集群时的配置  not assign channel,for cluser
		    	respMsg = sendMessage(requestMsg, isOnceSuccessReturn, respMsg, errorSendIPList, sfg);
				if(respMsg!=null && Constants.SOCKET_TRANSFER_FAIL.equals(respMsg.getNotifyObject())){
					errorMsg.append(" [").append(sfg.getIp()).append(":").append(sfg.getPort()).append("::").append(respMsg.getContext()).append("]");
				}
			}
			if(isOnceSuccessReturn && respMsg!=null && Constants.SOCKET_TRANSFER_SUCCESS.equals(respMsg.getNotifyObject())){
				return respMsg;
			}
		}
		if(!errorSendIPList.isEmpty()){
			throw new SocketTransferException("send data to remote portal "+ errorSendIPList + " fail! "+errorMsg.toString());
		}
		if(respMsg==null){
			throw new SocketTransferException("send to remote portal fail! none client "+channelType+" config in configuration file!");
		}
		if(errorMsg.length()>0){
			respMsg.setNotifyObject(Constants.SOCKET_TRANSFER_FAIL);
			respMsg.setContext(errorMsg.toString());
		}
		return respMsg;
	}

	private SocketMessage Syn(SocketMessage requestMsg, boolean isOnceSuccessReturn) throws SocketTransferException {	
		SocketMessage respMsg=null;
		List errorSendIPList=new ArrayList();
		List cfgList = SynDataCfgManager.getInstance().getClientConfig();
		if(cfgList.isEmpty()){
			throw new SocketTransferException("send to remote portal fail! none client config in configuration file!");
		}
		Iterator it = cfgList.iterator();
		while (it.hasNext()) {
			SynDataCfgInfo sfg = (SynDataCfgInfo) it.next();
			respMsg = sendMessage(requestMsg, isOnceSuccessReturn, respMsg,errorSendIPList, sfg);
			if(isOnceSuccessReturn && respMsg!=null){
				return respMsg;
			}
		}
		if(!errorSendIPList.isEmpty()){
			throw new SocketTransferException("send data to remote portal "+ errorSendIPList + " fail!");
		}
		return respMsg;
	}

	private SocketMessage sendMessage(SocketMessage requestMsg,boolean isOnceSuccessReturn, SocketMessage respMsg,List errorSendIPList, SynDataCfgInfo sfg) {
		Socket client = null;
		String server_IP = sfg.getIp();
		int server_port = sfg.getPort();
		log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.format(new Date())
				+ requestMsg.getNotifyObject()
				+ "  "
				+ requestMsg.getAction()
				+ " starting......(IP: "
				+ server_IP + " port: " + server_port + ")");
		try {
			client = new Socket(InetAddress.getByName(server_IP),
					server_port);
			client.setSoTimeout(sfg.getTimeout() * 1000);
			respMsg = this.send(client, requestMsg);
			log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
					.format(new Date())
					+ requestMsg.getNotifyObject()
					+ "  "
					+ requestMsg.getAction()
					+ " send success to "
					+ server_IP);

		}catch(EOFException e){
			
		}catch (Exception e) {
//			System.out.println("+++++++++++++ClientSocketService Exception"+e.getMessage());
			log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
					.format(new Date())
					+ requestMsg.getNotifyObject()
					+ "  "
					+ requestMsg.getAction()
					+ " send to "
					+ server_IP
					+ " fail!");
			errorSendIPList.add(server_IP+":"+server_port);
		}
		return respMsg;
	}

	private SocketMessage send(Socket client, SocketMessage requestMsg)
			throws Exception {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;

		try {
			out = new ObjectOutputStream(new BufferedOutputStream(client
					.getOutputStream()));
			out.writeObject(requestMsg);
			out.flush();

			in = new ObjectInputStream(new BufferedInputStream(client
					.getInputStream()));
			Object obj = in.readObject();

			if (obj != null && obj instanceof SocketMessage) {
				if (obj instanceof DefaultFailSocketMessage) {
					throw new Exception();
				} else {
					return (SocketMessage) obj;
				}
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
				out = null;
			}

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
				in = null;
			}

			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
				}
				client = null;
			}
		}
	}

}
