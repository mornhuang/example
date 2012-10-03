package com.taifook.common.socket;

public class SynDataCfgInfo {
	private String serviceName;

	private String role;
	
	private String ip;

	private int port;
	
	private int timeout;
	
	private String to_channel;

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return this.ip;
	}

	public void setPort( int port) {
		this.port = port;
	}

	public int getPort() {
		return this.port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTo_channel() {
		return to_channel;
	}

	public void setTo_channel(String to_channel) {
		this.to_channel = to_channel;
	}
}
