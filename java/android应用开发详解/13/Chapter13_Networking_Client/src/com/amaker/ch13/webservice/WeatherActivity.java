package com.amaker.ch13.webservice;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.amaker.ch13.R;

/**
 * 
 * @author 郭宏志
 * 显示天气预报
 */
public class WeatherActivity extends Activity {
	// 声明视图组件
	private TextView displayTextView;
	private Spinner spinner;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.weather);
		// 实例化视图组件
		displayTextView = (TextView) findViewById(R.id.displayTextView03);
		spinner = (Spinner) findViewById(R.id.citySpinner01);
		
		List<String> citys = WebServiceUtil.getCityList();
		ArrayAdapter a = new ArrayAdapter(this,
				android.R.layout.simple_spinner_dropdown_item, citys);
		spinner.setAdapter(a);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String msg = WebServiceUtil.getWeatherMsgByCity(spinner.getSelectedItem().toString());
				displayTextView.setText(msg);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		

	}
}