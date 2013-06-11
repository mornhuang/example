package com.amaker.ch12.app;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	// 声明使用到的Button视图组件
	private Button b1, b2;
	// 声明使用到的ImageView组件
	private ImageView myImage;
	// 声明AnimationDrawable
	private AnimationDrawable danceAnimation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置当前视图布局
		setContentView(R.layout.main);
		// 实例化视图组件
		myImage = (ImageView) findViewById(R.id.ImageView01);
		b1 = (Button) findViewById(R.id.Button01);
		b2 = (Button) findViewById(R.id.Button02);
		
		// 获得背景色，并转换为AnimationDrawable对象
		danceAnimation = (AnimationDrawable) myImage.getBackground();
		
		// 为按钮添加监听事件
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 开始动画
				danceAnimation.start();
			}
		});
		// 为按钮添加监听事件
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 停止动画
				danceAnimation.stop();
			}
		});

	}
}