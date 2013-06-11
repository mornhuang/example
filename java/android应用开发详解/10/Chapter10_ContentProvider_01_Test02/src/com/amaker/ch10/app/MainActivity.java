package com.amaker.ch10.app;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.amaker.ch10.app.Employees.Employee;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 添加
        insert();
        // 查询
        query();
        // 更新
        update();
        // 查询
        query();
        // 删除
        del();
        // 查询
        query();
    }
    // 删除方法
    private void del(){
    	// 删除ID为1的记录
    	Uri uri = ContentUris.withAppendedId(Employee.CONTENT_URI, 1);
    	// 获得ContentResolver，并删除
    	getContentResolver().delete(uri, null, null);
    }
    // 更新
    private void update(){
    	// 更新ID为1的记录
    	Uri uri = ContentUris.withAppendedId(Employee.CONTENT_URI, 1);
    	ContentValues values = new ContentValues();
    	// 添加员工信息
    	values.put(Employee.NAME, "hz.guo");
    	values.put(Employee.GENDER, "male");
    	values.put(Employee.AGE,31);
    	// 获得ContentResolver，并更新
		getContentResolver().update(uri, values, null, null);
    }
    // 查询
    private void query(){
    	// 查询列数组
    	   String[] PROJECTION = new String[] { 
    			   Employee._ID, 		// 0
    			   Employee.NAME, 		// 1
    			   Employee.GENDER, 	// 2
    			   Employee.AGE 		// 3
    	};
    	// 查询所有备忘录信息
		Cursor c = managedQuery(Employee.CONTENT_URI, PROJECTION, null,
				null, Employee.DEFAULT_SORT_ORDER);
		// 判断游标是否为空
		if (c.moveToFirst()) {
			// 遍历游标
			for (int i = 0; i < c.getCount(); i++) {
				c.moveToPosition(i);
				// 获得姓名
				String name = c.getString(1);
				String gender = c.getString(2);
				int age = c.getInt(3);
				// 输出日志
				Log.i("emp", name+":"+gender+":"+age);
			}
		}
    }
    // 插入
    private void insert(){
    	// 声明Uri
    	Uri uri = Employee.CONTENT_URI;
    	// 实例化ContentValues
    	ContentValues values = new ContentValues();
    	// 添加员工信息
    	values.put(Employee.NAME, "amaker");
    	values.put(Employee.GENDER, "male");
    	values.put(Employee.AGE,30);
    	// 获得ContentResolver，并插入
    	getContentResolver().insert(uri, values);
    }
}