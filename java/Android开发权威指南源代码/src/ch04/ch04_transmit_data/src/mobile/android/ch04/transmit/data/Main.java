package mobile.android.ch04.transmit.data;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener
{	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);
		Button button5 = (Button) findViewById(R.id.button5);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode)
		{
			case 1:
				switch (resultCode)
				{
					case 2:
						setTitle(data.getStringExtra("value"));
						break;

					default:
						break;
				}
				break;

			default:
				break;
		}
	}

	@Override
	public void onClick(View view)
	{
		Intent intent = null;
		switch (view.getId())
		{
			case R.id.button1:

				intent = new Intent(this, MyActivity1.class);
				intent.putExtra("intent_string", "通过Intent传递的字符串");
				intent.putExtra("intent_integer", 300);
				Data data = new Data();
				data.id = 1000;
				data.name = "Android";
				intent.putExtra("intent_object", data);
				startActivity(intent);

				break;
			case R.id.button2:
				intent = new Intent(this, MyActivity2.class);
				MyActivity2.id = 3000;
				MyActivity2.name = "保时捷";
				MyActivity2.data = new Data();
				MyActivity2.data.id = 1000;
				MyActivity2.data.name = "Android";
				startActivity(intent);
				break;
			case R.id.button3:
				intent = new Intent(this, MyActivity3.class);
				ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				// clipboard.setText("通过Clipboard传递的数据");
				Data clipboardData = new Data();
				clipboardData.id = 6666;
				clipboardData.name = "通过Clipboard传递的数据";
				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				String base64Str = "";
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					oos.writeObject(clipboardData);

					base64Str = Base64.encodeToString(baos.toByteArray(),
							Base64.DEFAULT);
					oos.close();
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
				clipboard.setText(base64Str);
				startActivity(intent);
				break;
			case R.id.button4:
				MyApp myApp = (MyApp) getApplicationContext();
				myApp.country = "美国";
				myApp.data.id = 1234;
				myApp.data.name = "飞碟";
				intent = new Intent(this, MyActivity4.class);
				startActivity(intent);
				break;
			case R.id.button5:
				intent = new Intent(this, MyActivity5.class);
				startActivityForResult(intent, 1);
				break;

		}

	}

}