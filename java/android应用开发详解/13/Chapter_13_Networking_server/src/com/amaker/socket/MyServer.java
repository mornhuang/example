package com.amaker.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 
 * @author 郭宏志
 * Socket 服务器，向客户端写Hello Android!
 */
public class MyServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
			while(true){
				Socket client = server.accept();
				OutputStream out = client.getOutputStream();
				String msg = "Hello Android!";
				out.write(msg.getBytes());
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
