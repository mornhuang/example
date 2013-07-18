package mobile.android.ch22.helloworld.test;

import mobile.android.ch22.helloworld.Main;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class HelloWorldTest extends ActivityInstrumentationTestCase2<Main>
{
	private Activity activity;
	private TextView textView;

	public HelloWorldTest()
	{
		super("mobile.android.ch22.helloworld", Main.class);
	}
	@Override
	protected void setUp() throws Exception
	{
		// TODO Auto-generated method stub
		super.setUp();
		activity = this.getActivity();

		textView = (TextView) activity
				.findViewById(mobile.android.ch22.helloworld.R.id.textview);

	}
	public void testText()
	{
		assertEquals(" ¿ΩÁƒ„∫√", (String) textView.getText());
	}
	public void testPreconditions()
	{
		assertNotNull(textView);
	}


}
