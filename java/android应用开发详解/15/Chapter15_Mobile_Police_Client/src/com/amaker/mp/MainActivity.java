package com.amaker.mp;

import com.amaker.mp.fileupload.FileUploadActivity;
import com.amaker.mp.infocollect.InfoCollectActivity;
import com.amaker.mp.infoquery.InfoQueryActivity;
import com.amaker.mp.location.GpsLocationActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] menus = { "信息查询", "信息采集", "文件上传","GPS定位功能" };
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menus));
		getListView().setTextFilterEnabled(true);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent=null;
		switch (position) {
		case 0:
			intent = new Intent(MainActivity.this,InfoQueryActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(MainActivity.this,InfoCollectActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(MainActivity.this,FileUploadActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(MainActivity.this,GpsLocationActivity.class);
			startActivity(intent);
			break;
		}
	}
}