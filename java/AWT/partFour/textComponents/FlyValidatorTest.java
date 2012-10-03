import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class FlyValidatorTest extends Applet {
	TextField    field = new TextField(5);
	FlyValidator validator = new FlyValidator();

	public void init() {
		field.addKeyListener(validator);
		add(field);
	}
}
class FlyValidator extends KeyAdapter {
	public void keyPressed(KeyEvent event) {
		TextField field     = (TextField)event.getSource();
		String    oldstring = field.getText();
		String    newstring = new String();
		int       value     = 0, newValue;

		try {
			if(!oldstring.equals(""))
				value = Integer.parseInt(oldstring);

			newstring += event.getKeyChar();
			newValue  = Integer.parseInt(newstring);
		}
		catch(NumberFormatException e) {
			event.consume();
			field.selectAll();
		}
	}
}
