package mobile.android.ch07.two.button.dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class Main extends Activity
{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		new AlertDialog.Builder(this).setIcon(R.drawable.question).setTitle(
		"是否下载文件").setPositiveButton("确定",
		new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				new AlertDialog.Builder(Main.this).setMessage(
						"文件已经成功下载.").create().show();
			}
		}).setNegativeButton("取消",
		new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{

				new AlertDialog.Builder(Main.this).setMessage(
						"您已经选择了取消按钮，该文件未被下载.").create().show();
			}
		}).show();
		
	}
}