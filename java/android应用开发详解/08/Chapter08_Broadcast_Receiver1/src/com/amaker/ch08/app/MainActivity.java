package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 
 * @author 郭宏志
 * 发出广播
 */
public class MainActivity extends Activity {
	// 定义一个Action常量
	private static final String MY_ACTION = "com.amaker.ch08.action.MY_ACTION";
	// 定义一个Button对象
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置当前布局视图
        setContentView(R.layout.main);
        btn = (Button)findViewById(R.id.Button01);
        // 为按钮设置单击监听器
        btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 实例化Intent对象
		        Intent intent = new Intent();
		        // 设置Intent action属性
		        intent.setAction(MY_ACTION);
		        // 为Intent添加附加信息
		        intent.putExtra("msg", "地瓜地瓜，我是土豆，收到请回复，收到请回复！");
		        // 发出广播
		        sendBroadcast(intent);
			}
		});
    }
}