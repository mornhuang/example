package mobile.android.ch07.prevent.close.dialog;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

class ButtonHandler extends Handler
{
	private WeakReference<DialogInterface> mDialog;

	public ButtonHandler(DialogInterface dialog)
	{
		mDialog = new WeakReference<DialogInterface>(dialog);
	}

	@Override
	public void handleMessage(Message msg)
	{
		switch (msg.what)
		{

			case DialogInterface.BUTTON_POSITIVE:
			case DialogInterface.BUTTON_NEGATIVE:
			case DialogInterface.BUTTON_NEUTRAL:
				((DialogInterface.OnClickListener) msg.obj).onClick(
						mDialog.get(), msg.what);
				break;
		}
	}
}

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_Dialog1(View view)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setTitle("我来了！ ").setMessage("对话框内容").setIcon(R.drawable.icon)
				.setPositiveButton("确定", new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
					}
				}).setNegativeButton(" 取消 ", new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.dismiss();
					}
				}).create();

		try
		{

			Field field = alertDialog.getClass().getDeclaredField("mAlert");
			field.setAccessible(true);
			Object obj = field.get(alertDialog);
			field = obj.getClass().getDeclaredField("mHandler");
			field.setAccessible(true);
			field.set(obj, new ButtonHandler(alertDialog));
		}
		catch (Exception e)
		{
		}

		alertDialog.show();

	}

	public void onClick_Dialog2(View view)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setTitle("我来了！ ").setMessage("对话框内容").setIcon(R.drawable.icon)
				.setPositiveButton("确定", new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						try
						{
							Field field = dialog.getClass().getSuperclass()
									.getDeclaredField("mShowing");
							field.setAccessible(true);
							field.set(dialog, false);
							dialog.dismiss();

						}
						catch (Exception e)
						{

						}

					}
				}).setNegativeButton(" 取消 ", new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						try
						{
							Field field = dialog.getClass().getSuperclass()
									.getDeclaredField("mShowing");
							field.setAccessible(true);

							field.set(dialog, true);
							dialog.dismiss();

						}
						catch (Exception e)
						{

						}

					}
				}).create();

		alertDialog.show();

	}
}