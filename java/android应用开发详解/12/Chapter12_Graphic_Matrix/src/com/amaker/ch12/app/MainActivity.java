package com.amaker.ch12.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView myView = new MyView(MainActivity.this);
		// 设置当前视图布局
		setContentView(myView);
	}
	// 自定义视图类
	class MyView extends View {
		// 位图实例
		private Bitmap bm;
		// Matrix 实例
		private Matrix matrix = new Matrix();
		// 旋转角度
		private float angle = 0.0f;
		// 位图宽和高
		private int w, h;
		// 缩放比例
		private float scale = 1.0f;
		// 判断缩放还是旋转
		private boolean isScale = false;
		
		// 构造方法
		public MyView(Context context) {
			super(context);
			// 获得位图
			bm = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.girl);
			// 获得位图宽
			w = bm.getWidth();
			// 获得位图高
			h = bm.getHeight();
			// 使当前视图获得焦点
			this.setFocusable(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			// 重置Matrix
			matrix.reset();
			if (!isScale) {
				// 旋转Matrix
				matrix.setRotate(angle);
			} else {
				// 缩放Matrix
				matrix.setScale(scale, scale);
			}
			// 根据原始位图和Matrix创建新视图
			Bitmap bm2 = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, true);
			// 绘制新视图
			canvas.drawBitmap(bm2, matrix, null);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// 向左旋转
			if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
				isScale = false;
				angle++;
				postInvalidate();
			}
			// 向右旋转
			if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
				isScale = false;
				angle--;
				postInvalidate();
			}
			// 放大
			if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
				isScale = true;
				if (scale < 2.0)
					scale += 0.1;
				postInvalidate();
			}
			// 缩小
			if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
				isScale = true;
				if (scale > 0.5)
					scale -= 0.1;
				postInvalidate();
			}

			return super.onKeyDown(keyCode, event);
		}
	}
}