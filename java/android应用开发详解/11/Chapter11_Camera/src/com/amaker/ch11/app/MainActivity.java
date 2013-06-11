package com.amaker.ch11.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity {
	// 声明拍照界面组件SurfaceView
	private SurfaceView surfaceView;
	// 声明界面控制组件SurfaceHolder
	private SurfaceHolder surfaceHolder;
	// 声明照相机
	private Camera camera;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 实例化拍照界面组件
        surfaceView = (SurfaceView)findViewById(R.id.preview);
        // 从SurfaceView中获得SurfaceHolder
        surfaceHolder = surfaceView.getHolder();
        // 为SurfaceHolder 添加回调
        surfaceHolder.addCallback(surfaceCallback);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    
    // 响应按键事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode==KeyEvent.KEYCODE_CAMERA||keyCode==KeyEvent.KEYCODE_SEARCH){
    		takePic();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    // 拍照方法
    private void takePic(){
    	// 停止预览
    	camera.stopPreview();
    	// 拍照
    	camera.takePicture(null,null, pictureCallback);
    }
    // 照片回调
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			new SavePictureTask().execute(data);
			camera.startPreview();
		}
	};
	
	// 保存照片任务类
	class SavePictureTask extends AsyncTask<byte[],String,String>{
		@Override
		protected String doInBackground(byte[]... params) {
			// 创建文件
			File picture = new File(Environment.getExternalStorageDirectory(),"picture.jpg");
			// 如果文件存在删除掉
			if(picture.exists())picture.delete();
			try {
				// 获得文件输出流
				FileOutputStream  fos = new FileOutputStream(picture.getPath());
				// 写到该文件
				fos.write(params[0]);
				// 关闭文件流
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
    // SurfaceHodler 回调，处理打开相机，关闭相机以及照片尺寸的改变
    SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// 打开相机
			camera = Camera.open();
			try {
				// 设置预览
				camera.setPreviewDisplay(holder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// 获得相机参数
			Camera.Parameters parameters = camera.getParameters();
			// 设置照片大小
			parameters.setPreviewSize(width, height);
			// 设置照片格式
			parameters.setPictureFormat(PixelFormat.JPEG);
			// 设置相机参数
			camera.setParameters(parameters);
			// 开始预览
			camera.startPreview();
		}
		
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// 停止预览
			camera.stopPreview();
			// 释放相机资源
			camera.release();
			camera = null;
		}
	};
}