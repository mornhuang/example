package mobile.android.ch07.three.button.dialog;

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
        "是否覆盖文件？").setPositiveButton("覆盖",
        new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                new AlertDialog.Builder(Main.this)
                        .setMessage("文件已经覆盖.").create().show();
            }
        }).setNeutralButton("忽略", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                new AlertDialog.Builder(Main.this).setMessage("忽略了覆盖文件的操作.")
                    .create().show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                new AlertDialog.Builder(Main.this).setMessage("您已经取消了所有的操作.").
                    create().show();
            }
        }).show();

		
	}
}