import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TextComponentTest extends Applet {
	TextField fieldOne = new TextField(10);
	TextField fieldTwo = new TextField(10);

	public void init() {
		add(fieldOne);
		fieldTwo.setFont(new Font("Dialog", 
		                           Font.BOLD + Font.ITALIC,
								   24));
		add(fieldTwo);
	}
}
