package mobile.android.ch07.text.image.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.View;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick_TextImageDialog(View view)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setMessage(

				Html.fromHtml("哈哈，<img src=''/>你好.", new ImageGetter()
				{

					@Override
					public Drawable getDrawable(String source)
					{
						Drawable drawable = getResources().getDrawable(
								R.drawable.face);
						drawable.setBounds(0, 0, 32, 32);
						return drawable;

					}
				}, null))
				.setPositiveButton(
						Html.fromHtml("<img src=''/>确定", new ImageGetter()
						{

							@Override
							public Drawable getDrawable(String source)
							{
								Drawable drawable = getResources().getDrawable(
										R.drawable.ok);
								drawable.setBounds(0, 0, 20, 20);
								return drawable;
							}
						}, null), null)
				.setNegativeButton(
						Html.fromHtml("<img src=''/>取消", new ImageGetter()
						{

							@Override
							public Drawable getDrawable(String source)
							{
								Drawable drawable = getResources().getDrawable(
										R.drawable.cancel);
								drawable.setBounds(0, 0, 20, 20);
								return drawable;
							}
						}, null), null).create();
		alertDialog.show();
	}
}