package com.amaker.test.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView v = new MyView(this);
		setContentView(v);
	}
	// 自定义视图类
	class MyView extends View{
		// 声明Bitmap对象
		private Bitmap bm ;
		// 声明位图渲染对象
		private Shader bitmapShader;
		// 声明线性渲染对象
		private Shader linearGradient;
		// 声明光束渲染对象
		private Shader radialGradient;
		// 声明梯度渲染对象
		private Shader sweepGradient;
		// 声明混合渲染对象
		private Shader composeShader;
		// 声明画笔
		private Paint paint;
		// 声明颜色数组
		private int[] colors;
		private boolean isFirst = true;
		
		public MyView(Context context) {
			super(context);
			// 获得Bitmap实例
			bm = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
			// 实例化画笔
			paint = new Paint();
			colors = new int[]{Color.RED,Color.GREEN,Color.BLUE};
			// 实例化位图渲染对象,x坐标方向重复图形，y坐标方向镜像图形
			bitmapShader = new BitmapShader(bm, TileMode.REPEAT, TileMode.MIRROR);
			// 实例化线性渲染
			linearGradient = new LinearGradient(0, 0, 100, 100,
					colors, null,  TileMode.REPEAT);
			// 实例化光束渲染
			radialGradient = new RadialGradient(100, 100, 80, 
					colors, null,  TileMode.REPEAT);
			// 实例化梯度渲染
			sweepGradient =  new SweepGradient(200, 200, colors, null);
			
			// 实例化混合渲染
			composeShader = new ComposeShader(linearGradient, radialGradient,PorterDuff.Mode.DARKEN);
			// 使其获得焦点
			setFocusable(true);
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if(isFirst){
				//写字，用来提示用户操作
				String content = "按上/下/左/右/中间键测试！";
				paint.setColor(Color.BLUE);
				canvas.drawText(content, 0, content.length()-1, 20, 20, paint);
			}else{
				// 全屏画矩形
				canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			}
		}
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			isFirst = false;
			if(keyCode==KeyEvent.KEYCODE_DPAD_UP){
				// 将画笔渲染设置为位图渲染
				paint.setShader(bitmapShader);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
				// 将画笔渲染设置为线性渲染
				paint.setShader(linearGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
				// 将画笔渲染设置为光束渲染
				paint.setShader(radialGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
				// 将画笔渲染设置为梯度渲染
				paint.setShader(sweepGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_CENTER){
				// 将画笔渲染设置为混合渲染
				paint.setShader(composeShader);
			}
			// 重绘界面
			postInvalidate();
			return super.onKeyDown(keyCode, event);
		}
	}
}