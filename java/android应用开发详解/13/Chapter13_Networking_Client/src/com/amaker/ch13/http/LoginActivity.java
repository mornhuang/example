package com.amaker.ch13.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.amaker.ch13.R;
public class LoginActivity extends Activity {
	/*
	 * 声明使用到的Button和EditText视图组件
	 */
	private Button cancelBtn,loginBtn;
	private EditText userEditText,pwdEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http1);
		/*
		 * 实例化视图组件
		 */
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		loginBtn = (Button)findViewById(R.id.loginButton);
		
		userEditText = (EditText)findViewById(R.id.userEditText);
		pwdEditText = (EditText)findViewById(R.id.pwdEditText);
		
		/*
		 * 设置登录监听器
		 */
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					String username = userEditText.getText().toString();
					String pwd = pwdEditText.getText().toString();
					login(username,pwd);
			}
		});
		
		/*
		 * 设置取消监听器
		 */
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		

	}
	
	/*
	 * 定义一个显示提示信息的对话框
	 */
	private void showDialog(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	/*
	 * 通过用户名称和密码进行查询，发送Post请求，获得响应结果。
	 */
	private void login(String username,String password){
		// 1. 使用 HttpURLConnection 实现
		/*String urlStr = "http://192.168.1.101:8080/Chapter_13_Networking_server/servlet/LoginServlet?";
		String queryString = "username="+username+"&password="+password;
		urlStr+=queryString;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				
			if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
				InputStream in = conn.getInputStream();
				byte[] b = new byte[in.available()];
				in.read(b);
				String msg = new String(b);
				showDialog(msg);
				in.close();
			}
			conn.disconnect();
		} catch (Exception e) {
			showDialog(e.getMessage());
		}*/
		// 2. 使用 Apache HTTP 客户端实现
		String urlStr = "http://192.168.1.101:8080/Chapter_13_Networking_server/servlet/LoginServlet";
		HttpPost request = new HttpPost(urlStr);
		// 如果传递参数个数比较多的话，我们可以对传递的参数进行封装
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		try {
			request.setEntity( new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				String msg = EntityUtils.toString(response.getEntity());
				showDialog(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}