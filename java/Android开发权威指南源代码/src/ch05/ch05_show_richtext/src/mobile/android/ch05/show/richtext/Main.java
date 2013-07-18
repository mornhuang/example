package mobile.android.ch05.show.richtext;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Main extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView textView1 = (TextView) findViewById(R.id.textview1);
		TextView textView2 = (TextView) findViewById(R.id.textview2);

		String html = "<font color='red'>I love Android.</font><br>"; 
		html += "<font color='#0000FF' ><big><i>I love Android.</i></big></font><p>";
		html += "<font color='@" + android.R.color.white+ "' ><tt><b><big><u>I love Android.</u></big><b></tt></font><p>";
		html += "<big><a href='http://51happyblog.com'>我的网站：51happyblog.com</a></big>";
		
		
		CharSequence charSequence = Html.fromHtml(html);
		 
		textView1.setText(charSequence);

		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		
		String text = "我的URL：http://51happyblog.com\n";
		text += "我的Email：abcd@126.com\n";
		text += "我的电话：+86 024-12345678";
		textView2.setText(text);
		textView2.setMovementMethod(LinkMovementMethod.getInstance());
	}
}