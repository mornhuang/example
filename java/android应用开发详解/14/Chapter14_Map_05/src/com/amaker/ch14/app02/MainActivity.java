package com.amaker.ch14.app02;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	// 声明TextView
	private TextView tv;
	// 声明Button
	private Button b1,b2;
    // 定位服务管理器实例
    private LocationManager locationManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// 设置当前Activity的界面布局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      	// 定位服务常量
	    String locService = Context.LOCATION_SERVICE;
	    // 通过getSystemService方法获得LocationManager实例
	    locationManager = (LocationManager)getSystemService(locService);
	    // 通过findViewById方法获得TextView实例
	    tv = (TextView) findViewById(R.id.TextView01);
	    
	    // 通过findViewById方法获得Button实例
	    b1 = (Button) findViewById(R.id.Button01);
	    // 通过findViewById方法获得Button实例
	    b2 = (Button) findViewById(R.id.Button02);
	    // 为按钮添加单击事件监听器
	    b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 正向编码
				forward();
			}
		});
	    
	    // 为按钮添加单击事件监听器
	    b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 反向编码
				reverse();
			}
		});
    }
    
    // 正向编码
    private void forward() {
    	// 实例化Geocoder
    	Geocoder gc = new Geocoder(this, Locale.getDefault());
    	// 地址
    	String address = "北京天安门";
    	// 位置列表
    	List<Address> locations = null;
    	try {
    		// 获得位置类别
    		locations = gc.getFromLocationName(address, 10);
    		// 如果Locations不为空
    		if(locations.size()>0){
    			// 获得Address实例
    			Address a = locations.get(0);
    			// 获得经纬度
    			double lat = a.getLatitude();
    			double lng = a.getLongitude();
    			// 显示信息
    			tv.setText(lat+":"+lng);
    		}
    	} catch (IOException e) {}
    }
    
    
    // 反向编码
    private void reverse() {
    	// 经度
    	double lng = 116.46;
    	// 纬度
    	double lat = 39.92;
    	// 实例化Geocoder
    	Geocoder gc = new Geocoder(this, Locale.getDefault());
    	// 声明地址列表
    	List<Address> addresses = null;
    	try {
    		// 获得Address实例列表
    		addresses = gc.getFromLocation(lat, lng, 10);
    		// 声明StringBuilder，保存位置信息
    		StringBuilder sb = new StringBuilder();
    		// 判断addresses是否为空
    		if(addresses.size()>0){
    			// 获得Address
    			Address a = addresses.get(0);
    			for (int i = 0; i < a.getMaxAddressLineIndex(); i++){
    				// 地址
    				sb.append(a.getAddressLine(i)).append("\n");
    				// 地点
    				sb.append(a.getLocality()).append("\n");
    				// 邮编
    				sb.append(a.getPostalCode()).append("\n");
    				// 国家名称
    				sb.append(a.getCountryName());
    			}
    			tv.setText(sb.toString());
    		}
    	} catch (IOException e) {}
    }
}