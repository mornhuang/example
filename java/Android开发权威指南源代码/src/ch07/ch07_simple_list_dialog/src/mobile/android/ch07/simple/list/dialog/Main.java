package mobile.android.ch07.simple.list.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.os.Bundle;

public class Main extends Activity
{
	private String[] provinces = new String[]
	{ "辽宁省", "山东省", "河北省", "福建省", "广东省", "黑龙江省" };

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		new AlertDialog.Builder(this).setTitle("选择省份")
				.setItems(provinces, new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						final AlertDialog ad = new AlertDialog.Builder(
								Main.this).setMessage(
								"您已经选择了: " + which + ":" + provinces[which])
								.show();
						android.os.Handler hander = new android.os.Handler();
						// 设置定时器，5秒后调用run方法
						hander.postDelayed(new Runnable()
						{
							@Override
							public void run()
							{
								// 调用AlertDialog类的dismiss方法关闭对话框，也可以调用cancel方法
								ad.dismiss();
							} 
						}, 5 * 1000);
					}
				}).show();

	}
}