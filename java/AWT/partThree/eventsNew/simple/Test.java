import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Test extends Applet {
	public void init() {
		Button eventSource = new Button("Event Source");
		eventSource.addActionListener(new ButtonListener(this));
		add(eventSource);
	}
}
class ButtonListener implements ActionListener {
	private Applet applet;

	public ButtonListener(Applet applet) {
		this.applet = applet;
	}
	public void actionPerformed(ActionEvent event) {
		Button source = (Button)event.getSource();
		applet.showStatus(source.getLabel() + " activated");
	}
}
