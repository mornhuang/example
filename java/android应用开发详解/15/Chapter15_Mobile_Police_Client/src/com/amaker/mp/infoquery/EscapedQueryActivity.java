package com.amaker.mp.infoquery;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaker.mp.R;
import com.amaker.mp.R.id;
import com.amaker.mp.R.layout;
import com.amaker.util.HttpUtil;

public class EscapedQueryActivity extends Activity {
	
	
	
	
	
	private EditText myEditText;
	private Button myBtn,myBtn2;
	private TextView myTextView;
	private ImageView myImageView;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.escape_people_query);
		myEditText = (EditText)findViewById(R.id.EditText01);
		
		myTextView = (TextView)findViewById(R.id.resultTextView);
		
		myImageView = (ImageView)findViewById(R.id.ImageView01);
		
		myBtn = (Button)findViewById(R.id.Button01);
		myBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validate()){
					String idno = myEditText.getText().toString();
					String msg = query(idno);
					if(msg!=null&&!msg.equals(""))
						myTextView.setText(msg);
					else
						myTextView.setText("查无此人！");
				}
			}
		});
		
		myBtn2 = (Button)findViewById(R.id.Button02);
		myBtn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validate()){
					String idno = myEditText.getText().toString();
					String urlStr = queryPicPath(idno);
					if(urlStr!=null&&!urlStr.equals("")){
						showPic(urlStr);
					}
				}
			}
		});
	}
	
	private void showPic(String urlStr){
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream in = conn.getInputStream();
			Bitmap map = BitmapFactory.decodeStream(in);
			myImageView.setImageBitmap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean  validate(){
		String no = myEditText.getText().toString();
		if(no==null||no.equals("")){
			showAlert();
			return false;
		}
		return true;
	}
	
	private void showAlert(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("请输入身份证号！")
		       .setCancelable(false)
		       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private String queryPicPath(String idno){
		String queryString = "idno="+idno;
		String url = HttpUtil.BASE_URL+"servlet/PeopleImgServlet?"+queryString;
		return HttpUtil.queryStringForPost(url);
    }
	
	private String query(String idno){
		String queryString = "idno="+idno;
		String url = HttpUtil.BASE_URL+"servlet/PeopleServlet?"+queryString;
		return HttpUtil.queryStringForPost(url);
    }
}