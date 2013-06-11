package com.amaker.ch07.app;

import com.amaker.ch07.app.IPerson;
import com.amaker.ch07.app.R;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
/**
 * 
 * @author 郭宏志
 * RPC 测试
 */
public class MainActivity extends Activity {
	// 声明IPerson接口
	private IPerson iPerson;
	// 声明 Button
	private Button btn;
	// 实例化ServiceConnection
	private ServiceConnection conn = new ServiceConnection() {
		@Override
		synchronized public void onServiceConnected(ComponentName name, IBinder service) {
			// 获得IPerson接口
			iPerson = IPerson.Stub.asInterface(service);
			if (iPerson != null)
				try {
					// RPC 方法调用
					iPerson.setName("hz.guo");
					iPerson.setAge(30);
					String msg = iPerson.display();
					// 显示方法调用返回值
					Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG)
							.show();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置当前视图布局
		setContentView(R.layout.main);
		// 实例化Button
		btn = (Button) findViewById(R.id.Button01);
		//为Button添加单击事件监听器
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 实例化Intent
				Intent intent = new Intent();
				// 设置Intent Action 属性
				intent
						.setAction("com.amaker.ch09.app.action.MY_REMOTE_SERVICE");
				// 绑定服务
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
	}
}