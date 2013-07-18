package mobile.android.ch16.voice.recognizer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;

public class Main extends Activity
{
	private EditText editText;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		editText = (EditText) findViewById(R.id.edittext);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				ArrayList<String> matches = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				if (matches.size() > 0)
				{

					editText.setText(matches.get(0));
				}
			}
		}
	}

	public void onClick_Voice_Recognizer(View view)
	{

		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "”Ô“Ù¬º»Î");
		startActivityForResult(intent, 1);
	}
}