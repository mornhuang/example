import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TestApplet extends Applet {
	public void init() {
		Button 	button = new Button("button 1"),
				button2 = new Button("button 2");

		ButtonFocusListener listener = new ButtonFocusListener();

		button.addFocusListener(listener);
		button2.addFocusListener(listener);

		add(button);
		add(button2);
	}
}
class ButtonFocusListener implements FocusListener {
	public void focusGained(FocusEvent event) {
		report(event);
	}
	public void focusLost(FocusEvent event) {
		report(event);
	}
	private void report(FocusEvent event) {
		Button b = (Button)event.getComponent();

		if(event.getID() == FocusEvent.FOCUS_GAINED)
			System.out.print(b.getLabel() + " gained focus");
		else if(event.getID() == FocusEvent.FOCUS_LOST)
			System.out.print(b.getLabel() + " lost focus");

		if(event.isTemporary()) 
			System.out.println(": temporary");
		else
			System.out.println();
	}
}
