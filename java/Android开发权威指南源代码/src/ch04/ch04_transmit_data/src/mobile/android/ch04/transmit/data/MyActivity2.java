package mobile.android.ch04.transmit.data;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MyActivity2 extends Activity
{
	public static String name;
	public static int id;
	public static Data data;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myactivity);
		TextView textView = (TextView)findViewById(R.id.textview);
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("name£º");
		sb.append(name);
		sb.append("\n");
		sb.append("id£º");
		sb.append(id);
		sb.append("\n");
		sb.append("data.id£º");
		sb.append(data.id);
		sb.append("\n");
		sb.append("data.name£º");
		sb.append(data.name);
		sb.append("\n");
		
		textView.setText(sb.toString());
	}

}
