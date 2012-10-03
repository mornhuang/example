import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ExitValidatorTest extends Applet {
	TextField     fieldOne  = new TextField(3),
	              fieldTwo  = new TextField(3);
	ExitValidator validator = new ExitValidator();

	public void init() {
		fieldOne.addActionListener(validator);
		fieldOne.addFocusListener (validator);
		add(fieldOne);
		add(fieldTwo);
	}
}
class ExitValidator extends    FocusAdapter 
                    implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		validate((TextField)event.getSource());
	}
	public void focusLost(FocusEvent event) {
		validate((TextField)event.getSource());
	}
	private void validate(TextField field) {
		try {
			Integer.parseInt(field.getText());
		}
		catch(NumberFormatException e) {
			field.requestFocus();
			field.selectAll();
		}
	}
}
