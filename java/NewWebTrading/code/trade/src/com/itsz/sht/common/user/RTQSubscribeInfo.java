package com.itsz.sht.common.user;

import java.io.Serializable;

public class RTQSubscribeInfo implements Serializable {
	private String subscribedSHK;
	private String subscribedStream;
	private String subscribedTDX;
	private String SubscribedAPP;
	private int snapshotUnits;
	public String getSubscribedSHK() {
		return subscribedSHK;
	}
	public void setSubscribedSHK(String subscribedSHK) {
		this.subscribedSHK = subscribedSHK;
	}
	public int getSnapshotUnits() {
		return snapshotUnits;
	}
	public void setSnapshotUnits(int snapshotUnits) {
		this.snapshotUnits = snapshotUnits;
	}
	public String getSubscribedStream() {
		return subscribedStream;
	}
	public void setSubscribedStream(String subscribedStream) {
		this.subscribedStream = subscribedStream;
	}
	public String getSubscribedTDX() {
		return subscribedTDX;
	}
	public void setSubscribedTDX(String subscribedTDX) {
		this.subscribedTDX = subscribedTDX;
	}
	public String getSubscribedAPP() {
		return SubscribedAPP;
	}
	public void setSubscribedAPP(String subscribedAPP) {
		SubscribedAPP = subscribedAPP;
	}
	
}
