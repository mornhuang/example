package com.amaker.ch12.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	// 声明使用到的Button视图组件
	private Button b1, b2, b3, b4;
	// 声明使用到的ImageView组件
	private ImageView girlImage;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置当前视图布局
		setContentView(R.layout.main);
		// 实例化视图组件
		girlImage = (ImageView) findViewById(R.id.ImageView01);
		b1 = (Button) findViewById(R.id.Button01);
		b2 = (Button) findViewById(R.id.Button02);
		b3 = (Button) findViewById(R.id.Button03);
		b4 = (Button) findViewById(R.id.Button04);
		
		// 为按钮添加监听事件
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建Sacle(尺寸)变化动画
				Animation scaleAnimation = 
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_scale);
				// 开始动画
				girlImage.startAnimation(scaleAnimation);
			}
		});
		// 为按钮添加监听事件
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建Alpha(渐变)动画
				Animation alphaAnimation = 
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_alpha);
				// 开始动画
				girlImage.startAnimation(alphaAnimation);
			}
		});
		// 为按钮添加监听事件
		b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建translate(位置变化)动画
				Animation translateAnimation =  
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_translate);
				// 开始动画
				girlImage.startAnimation(translateAnimation);
			}
		});
		// 为按钮添加监听事件
		b4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建rotate(旋转)动画
				Animation rotateAnimation =
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_rotate);
				// 设置动画持续时间
				rotateAnimation.setDuration(3000);
				// 开始动画
				girlImage.startAnimation(rotateAnimation);
			}
		});
		

	}
}