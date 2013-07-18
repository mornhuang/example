package mobile.android.ch09.invokeotherapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity implements OnClickListener
{


	@Override
	public void onClick(View view)
	{
		
		switch (view.getId())
		{
			
			case R.id.btnCall:
				Intent callIntent = new Intent(Intent.ACTION_CALL, Uri
						.parse("tel:12345678"));
				startActivity(callIntent);
				break;
			case R.id.btnTouchDialer:
				Intent touchDialerIntent = new Intent("com.android.phone.action.TOUCH_DIALER");
				startActivity(touchDialerIntent);
				break;
			case R.id.btnDial:
				Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:87654321"));
				startActivity(dialIntent);
				break;
			case R.id.btnWeb:
				Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://nokiaguy.blogjava.net"));
				startActivity(webIntent);

				break;
			case R.id.btnSendToEmail:
				Uri uri = Uri.parse("mailto:xxx@abc.com");
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
				startActivity(intent);
				break;

			case R.id.btnSendEmail:

				Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);

				sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]
				{ "techcast@126.com" });
				sendEmailIntent.putExtra(Intent.EXTRA_CC, new String[]
				{ "abc@126.com", "test@126.com" });
				sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT,
						"关于Android的两个技术问题");
				sendEmailIntent.putExtra(Intent.EXTRA_TEXT,
						"1. 如何调用其他应用程序中的Activity?\n2. 在应用程序中如果接收系统广播？");

				sendEmailIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendEmailIntent,
						"选择发送消息的客户端"));
				break;
			case R.id.btnContactList:
				Intent contactListIntent = new Intent("com.android.contacts.action.LIST_CONTACTS");
				startActivity(contactListIntent);				
				break;
			case R.id.btnSettings:
				Intent settingsIntent = new Intent("android.settings.SETTINGS");
				startActivity(settingsIntent);				
				break;
			case R.id.btnWifiSettings:
				Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
				startActivity(wifiSettingsIntent);				
				break;
			case R.id.btnAudio:
				Intent audioIntent = new Intent(Intent.ACTION_GET_CONTENT);
				audioIntent.setType("audio/*");
				startActivity(Intent.createChooser(audioIntent, "选择音频程序"));				
				break;
		}

		
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnCall = (Button) findViewById(R.id.btnCall);		
		Button btnTouchDialer = (Button)findViewById(R.id.btnTouchDialer);		
		Button btnDial = (Button) findViewById(R.id.btnDial);
		Button btnWeb = (Button) findViewById(R.id.btnWeb);
		Button btnAudio = (Button) findViewById(R.id.btnAudio);
		Button btnSendToEmail = (Button) findViewById(R.id.btnSendToEmail);
		Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);		
		Button btnContactList = (Button)findViewById(R.id.btnContactList);
		Button btnSettings = (Button)findViewById(R.id.btnSettings);
		Button btnWifiSettings = (Button)findViewById(R.id.btnWifiSettings);
		btnCall.setOnClickListener(this);		
		btnTouchDialer.setOnClickListener(this);
		btnDial.setOnClickListener(this);
		btnWeb.setOnClickListener(this);
		btnAudio.setOnClickListener(this);
		btnSendToEmail.setOnClickListener(this);
		btnSendEmail.setOnClickListener(this);
		btnContactList.setOnClickListener(this);
		btnSettings.setOnClickListener(this);
		btnWifiSettings.setOnClickListener(this);
	}
}