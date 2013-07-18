package mobile.android.ch05.choice.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

public class Main extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView lvCheckedTextView = (ListView) findViewById(R.id.lvCheckedTextView);
		ListView lvRadioButton = (ListView) findViewById(R.id.lvRadioButton);
		ListView lvCheckBox = (ListView) findViewById(R.id.lvCheckBox);
		String[] data = new String[]
		{ "Android", "Meego" };

		ArrayAdapter<String> aaCheckedTextViewAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_checked, data);
		lvCheckedTextView.setAdapter(aaCheckedTextViewAdapter);
		lvCheckedTextView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		// ArrayAdapter<String> aaCheckedTextViewAdapter = new
		// ArrayAdapter<String>(this, android.R.layout.simple_list_item_2,
		// android.R.id.text2,data);
		// lvCheckedTextView.setAdapter(aaCheckedTextViewAdapter);
		// lvCheckedTextView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		ArrayAdapter<String> aaRadioButtonAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_single_choice, data);
		lvRadioButton.setAdapter(aaRadioButtonAdapter);
		lvRadioButton.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		ArrayAdapter<String> aaCheckBoxAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, data);
		lvCheckBox.setAdapter(aaCheckBoxAdapter);
		lvCheckBox.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	
	
	}
}