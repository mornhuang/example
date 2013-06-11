package com.amaker.test.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView v = new MyView(this, null);
		setContentView(v);
	}
	// 自定义视图类
	class MyView extends View implements Runnable {
		// 图形当前坐标
		private int x = 20, y = 20;
		// 构造方法
		public MyView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// 获得焦点
			setFocusable(true);
			// 启动线程
			new Thread(this).start();
		}
		@Override
		public void run() {
			RefreshHandler mRedrawHandler = new RefreshHandler();
			while (!Thread.currentThread().isInterrupted()) {
				// 通过发送消息更新界面
				Message m = new Message();
				m.what = 0x101;
				mRedrawHandler.sendMessage(m);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			// 实例化画笔
			Paint p = new Paint();
			// 设置画笔颜色
			p.setColor(Color.GREEN);
			// 画圆
			canvas.drawCircle(x, y, 10, p);
		}
		
		// 更新界面处理器
		class RefreshHandler extends Handler {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x101) {
					MyView.this.update();
					MyView.this.invalidate();
				}
				super.handleMessage(msg);
			}
		};
		// 更新坐标
		private void update() {
			int h = getHeight();
			y += 5;
			if (y >= h)
				y = 20;
		}
	}
}