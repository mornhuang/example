package com.amaker.test;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	private ImageButton record, stop;
	private MediaRecorder mr;
	private String path;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		record = (ImageButton) findViewById(R.id.record);
		stop = (ImageButton) findViewById(R.id.stop);

		record.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				record();
			}
		});

		stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				stop();

			}
		});

		setup();
	}

	private void setPath() throws IOException {
		path = "/sdcard/test1.mp3";
		String state = android.os.Environment.getExternalStorageState();
		if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
			throw new IOException("SD 没有安装.  它的状态是" + state
					+ ".");
		}

		File directory = new File(path).getParentFile();
		if (!directory.exists() && !directory.mkdirs()) {
			throw new IOException("文件不能被创建.");
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (stop.isEnabled()) {
			stop();
		}
	}

	private void record() {
		try {
			mr.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mr.start();
		record.setEnabled(false);
		stop.setEnabled(true);
	}

	private void stop() {
		mr.stop();
		stop.setEnabled(false);
		record.setEnabled(true);
		mr.release();
	}

	private void setProperty() {
		try {
			mr = new MediaRecorder();
			mr.setAudioSource(MediaRecorder.AudioSource.MIC);
			mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			try {
				setPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mr.setOutputFile(path);
		} catch (Throwable t) {
			error(t);
		}
	}

	private void setup() {
		setProperty();
		record.setEnabled(true);
		stop.setEnabled(false);
	}

	private void error(Throwable t) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("报错啦!").setMessage(t.toString()).setPositiveButton(
				"确定", null).show();
	}
}