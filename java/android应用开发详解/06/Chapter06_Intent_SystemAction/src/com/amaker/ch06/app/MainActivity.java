package com.amaker.ch06.app;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 菜单项数组
		String[] menus = { "查看电话信息", "编辑电话信息", "显示拨打电话界面","直接打电话","访问浏览器","访问地图"};
		// 将菜单项数组设置为ListView的列表项展示
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menus));
		getListView().setTextFilterEnabled(true);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		Uri uri ;
		String data;
		switch (position) {
		// 查看_id 为1的用户电话信息
		case 0:
			data = "content://contacts/people/1";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 编辑_id 为1的用户电话信息
		case 1:
			data = "content://contacts/people/1";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_EDIT);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 显示拨打电话界面
		case 2:
			data = "tel:13800138000";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 直接打电话
		case 3:
			data = "tel:13800138000";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 访问浏览器
		case 4:
			data = "http://www.google.com";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 访问地图
		case 5:
			data = "geo:39.92,116.46";
			uri = Uri.parse(data);
			intent = new Intent(Intent.ACTION_VIEW,uri);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}