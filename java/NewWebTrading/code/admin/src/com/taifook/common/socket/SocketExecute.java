package com.taifook.common.socket;

import java.io.Serializable;

public interface SocketExecute extends Serializable {
	
	public SocketMessage execute(SocketMessage msg);
}
