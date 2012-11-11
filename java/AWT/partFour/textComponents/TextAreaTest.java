import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TextAreaTest extends Applet {
	TextField field = new TextField("0123456789012345");
	public void init() {
		add(field);	
		field.setSelectionStart(3);
		field.setSelectionEnd(7);
		add(new Label(
			"Selection Start: " + field.getSelectionStart()));
		add(new Label(
			"Selection End: " + field.getSelectionEnd()));
	}
}
