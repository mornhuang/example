package mobile.android.ch05.text_background;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

public class ColorSpan extends CharacterStyle
{
	private int mTextColor;
	private int mBackgroundColor;
	public ColorSpan(int textColor, int backgroundColor)
	{
		mTextColor = textColor;
		mBackgroundColor = backgroundColor;
	}
	@Override
	public void updateDrawState(TextPaint tp)
	{
		tp.bgColor = mBackgroundColor;
		tp.setColor(mTextColor);
		
	}

}
