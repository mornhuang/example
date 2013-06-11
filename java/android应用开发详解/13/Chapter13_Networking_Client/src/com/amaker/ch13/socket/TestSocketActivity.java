package com.amaker.ch13.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.amaker.ch13.R;
/**
 * 
 * @author 郭宏志
 * Android　Socket 客户端接收服务器信息
 */
public class TestSocketActivity extends Activity {
	private TextView myTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket);
        myTextView = (TextView)findViewById(R.id.msgTextView01);
        try {
			Socket socket = new Socket("192.168.1.101",8888);
			InputStream in = socket.getInputStream();
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			String msg = new String(buffer);
			myTextView.setText(msg);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}