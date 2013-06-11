package com.amaker.test;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amaker.ch03.color.TestColorActivity;
import com.amaker.ch03.dimen.TestDimensionActivity;
import com.amaker.ch03.drawable.TestBitmapActivity;
import com.amaker.ch03.layout.TestLayoutActivity;
import com.amaker.ch03.menu.TestMenuActivity;
import com.amaker.ch03.string.TestStringActivity;
import com.amaker.ch03.xml.TestXmlActivity;

public class MainActivity extends ListActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    	// 菜单项数组
        String[] items = {"Test Color","Test String","Test Dimension","Test XML","Test Bitmap","Test Menu","Test Layout"};
		// 将菜单项数组设置为ListView的列表项展示
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items));
		getListView().setTextFilterEnabled(true);
        
    }
    
    // 响应菜单项的单击事件
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(MainActivity.this,TestColorActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(MainActivity.this,TestStringActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(MainActivity.this,TestDimensionActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(MainActivity.this,TestXmlActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent = new Intent(MainActivity.this,TestBitmapActivity.class);
			startActivity(intent);
			break;
		case 5:
			intent = new Intent(MainActivity.this,TestMenuActivity.class);
			startActivity(intent);
			break;
		case 6:
			intent = new Intent(MainActivity.this,TestLayoutActivity.class);
			startActivity(intent);
			break;
		}
	}
}