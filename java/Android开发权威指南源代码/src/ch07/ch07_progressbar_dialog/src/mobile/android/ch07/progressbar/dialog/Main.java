package mobile.android.ch07.progressbar.dialog;

import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener
{
	private static final int MAX_PROGRESS = 100;
	private ProgressDialog progressDialog;
	private Handler progressHandler;
	private int progress;

	private void showProgressDialog(int style)
	{
		progressDialog = new ProgressDialog(this);
		progressDialog.setIcon(R.drawable.wait);
		progressDialog.setTitle("正在处理数据...");
		progressDialog.setMessage("请稍后...");
		progressDialog.setProgressStyle(style);
		progressDialog.setMax(MAX_PROGRESS);
		progressDialog.setButton("暂停", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				progressHandler.removeMessages(1);
			}
		});
		progressDialog.setButton2("取消", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				progressHandler.removeMessages(1);
				progress = 0;
				progressDialog.setProgress(0);
			}
		});
		progressDialog.show();
		progressHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				super.handleMessage(msg);

				if (progress >= MAX_PROGRESS)
				{
					progress = 0;
					progressDialog.dismiss();

				}
				else
				{
					progress++;
					progressDialog.incrementProgressBy(1);
					progressHandler.sendEmptyMessageDelayed(1,
							50 + new Random().nextInt(500));

				}
			}
		};
		progress = (progress > 0) ? progress : 0;
		progressDialog.setProgress(progress);
		progressHandler.sendEmptyMessage(1);
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.button1:
				showProgressDialog(ProgressDialog.STYLE_HORIZONTAL);
				break;

			case R.id.button2:
				showProgressDialog(ProgressDialog.STYLE_SPINNER);
				break;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);

	}
}