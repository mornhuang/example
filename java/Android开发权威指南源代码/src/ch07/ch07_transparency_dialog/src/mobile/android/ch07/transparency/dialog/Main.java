package mobile.android.ch07.transparency.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_TransparencyDialog(View view)
	{
		// 显示透明的对话框
		
		AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setMessage("透明对话框").setPositiveButton("确定", null).create();
		Window window = alertDialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		//  设置透明度为0.7
		lp.alpha = 0.7f;
		window.setAttributes(lp);
		alertDialog.show();
 
		// 显示非透明的对话框
		alertDialog = new AlertDialog.Builder(this).setMessage("在底端显示对话框")
				.setPositiveButton("确定", null).create();
		window = alertDialog.getWindow(); 
		
		window.setGravity(Gravity.BOTTOM);
		alertDialog.show();
	}
}