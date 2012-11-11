import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TestApplet extends Applet {
	public void init() {
		final Button 	
				button1 = new Button("other button activates me"),
				button2 = new Button("press me");

		add(button1);
		add(button2);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStatus("button 1 activated");
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MouseEvent press = new MouseEvent(
									button1,
									MouseEvent.MOUSE_PRESSED,
									System.currentTimeMillis(),
									0, 5, 5, 1, false);

				MouseEvent release = new MouseEvent( 
									button1,
									MouseEvent.MOUSE_RELEASED,
									System.currentTimeMillis(),
									0, 5, 5, 1, false);

				button1.dispatchEvent(press);

				try {
					Thread.currentThread().sleep(50);
				}
				catch(InterruptedException ex) {
					ex.printStackTrace();
				}

				button1.dispatchEvent(release);
			}
		});
	}
}
