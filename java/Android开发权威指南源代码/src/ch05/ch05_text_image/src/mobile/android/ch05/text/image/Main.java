package mobile.android.ch05.text.image;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class Main extends Activity
{
	public int getResourceId(String name)
	{
		try
		{
			Field field = R.drawable.class.getField(name);
			return Integer.parseInt(field.get(null).toString());
		}
		catch (Exception e)
		{

		}
		return 0;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView textView = (TextView) findViewById(R.id.textview);
		textView.setTextColor(Color.BLACK);
		textView.setBackgroundColor(Color.WHITE);
		textView.setTextSize(20);
		String html = "Í¼Ïñ1<img src='image1'/>Í¼Ïñ2<img src='image2'/>Í¼Ïñ3<img src='image3'/><p>";
		html += "Í¼Ïñ4<a href='http://51happyblog.com'><img src='image4'/></a>Í¼Ïñ5<img src='image5'/>";

		CharSequence charSequence = Html.fromHtml(html, new ImageGetter()
		{

			@Override
			public Drawable getDrawable(String source)
			{
				Drawable drawable = getResources().getDrawable(
						getResourceId(source));
				
				if (source.equals("image3"))
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2,
							drawable.getIntrinsicHeight() / 2);
				else
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight() );
				return drawable; 

			}
		}, null);
		
		textView.setText(charSequence);
		textView.setMovementMethod(LinkMovementMethod.getInstance());

	}
}