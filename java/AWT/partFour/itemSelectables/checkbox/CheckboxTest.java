import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CheckboxTest extends Applet {
	public void init() {
		setLayout(new BorderLayout());
		add(new PrintOptionsPanel(this), "Center");
	}
}
class PrintOptionsPanel extends Panel {
	Checkbox  oddPages, evenPages, collate, lastFirst;
	Listener  listener = new Listener();
	Applet    applet;

	public PrintOptionsPanel(Applet anApplet) {
		applet    = anApplet;
		oddPages  = new Checkbox("Odd Pages");
		evenPages = new Checkbox("Even Pages");
		collate   = new Checkbox("Collate");
		lastFirst = new Checkbox("Last Page First");

		oddPages.addItemListener (listener);
		evenPages.addItemListener(listener);
		collate.addItemListener  (listener);
		lastFirst.addItemListener(listener);

		add(oddPages);  
		add(evenPages);
		add(collate); 
		add(lastFirst);
	}
	class Listener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			applet.showStatus(
				"Odd Pages: "       + oddPages.getState()  + ",  " +
				"Even Pages: "      + evenPages.getState() + ",  " +
				"Collate: "         + collate.getState()   + ",  " +
				"Last Page First: " + lastFirst.getState());
		}
	}
}
