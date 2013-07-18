package mobile.android.ch07.position.dialog;

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

	public void onClick_TopDialog(View view)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setMessage("在顶端显示对话框").setPositiveButton("确定", null).create();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.TOP);
		
		alertDialog.show();
	}

	

	public void onClick_BottomDialog(View view)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage("在底端显示对话框")
				.setPositiveButton("确定", null).create();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		alertDialog.show();

	}

	public void onClick_CustomPositionDialog(View view)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage("在任意位置显示对话框")
				.setPositiveButton("确定", null).create();
		Window window = alertDialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.x = -20;
		lp.y = -120;
		window.setAttributes(lp);

		alertDialog.show();

	}
}