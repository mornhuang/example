package com.amaker.ch06.app1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.amaker.ch06.app.R;

/**
 * @author 郭宏志
 * 测试Intent的ComponentName属性
 */
public class MyActivity extends Activity {
	// 声明TextView
	private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// 设置视图布局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        // 获得Intent
        Intent intent = this.getIntent();
        // 获得组件名称对象
        ComponentName cn = intent.getComponent();
        // 获得包名称
        String packageName = cn.getPackageName();
        // 获得类名称
        String className = cn.getClassName();
        // 实例化TextView
        tv = (TextView)findViewById(R.id.TextView01);
        // 显示
        tv.setText("组件包名称："+packageName+"\n"+"组件类名称："+className);
    }
}