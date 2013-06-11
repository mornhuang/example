package com.amaker.mp.infocollect;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amaker.mp.R;
import com.amaker.mp.R.id;
import com.amaker.mp.R.layout;
import com.amaker.util.HttpUtil;

public class VehicleInfoCollActivity  extends Activity {

	private EditText myEditText1,myEditText2,myEditText3,myEditText4;
	private Spinner mySpinner ;
	private Button cancelBtn,submitBtn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vehicle_info_collect);
		setTitle("机动车违章信息采集");
		
		myEditText1 = (EditText)findViewById(R.id.nameEditText);
		myEditText2 = (EditText)findViewById(R.id.idnoEditText);
		myEditText3 = (EditText)findViewById(R.id.licenseEditText);
		myEditText4 = (EditText)findViewById(R.id.penaltyEditText);
		
		mySpinner = (Spinner)findViewById(R.id.faultRecordSpinner);
		
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		submitBtn = (Button)findViewById(R.id.submitButton);
		
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		submitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validate()){
					if(submit()){
						showAlert("提交成功！");
					}else{
						showAlert("提交失败！");
					}
				}else{
					showAlert("输入错误，请重新输入！");
				}
			}
		});
		
		
	}
	
	private void showAlert(String msg){
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
	
	private UrlEncodedFormEntity makeEntity(){
		String name = myEditText1.getText().toString();
		String idno = myEditText2.getText().toString();
		String license = myEditText3.getText().toString();
		String penalty = myEditText4.getText().toString();
		
		String faultRecord = mySpinner.getSelectedItem().toString();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yy-MM-dd HH:mm").format(date);
		
		
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("idno", idno));
		params.add(new BasicNameValuePair("license", license));
		params.add(new BasicNameValuePair("penalty", penalty));
		params.add(new BasicNameValuePair("faultRecord", faultRecord));
		params.add(new BasicNameValuePair("createTime", dateStr));
		
		
		try {
			return new UrlEncodedFormEntity(params,HTTP.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private boolean validate(){
		return true;
	}
	private boolean submit(){
		String url = HttpUtil.BASE_URL+"servlet/VehicleFaultInfoServlet";
		
		HttpPost request = HttpUtil.getHttpPost(url);
		request.setEntity(makeEntity());
		
		String result= HttpUtil.queryStringForPost(request);
		if(result!=null&&result.equals("1"))return true;
		
		return false;
	}
}
	
