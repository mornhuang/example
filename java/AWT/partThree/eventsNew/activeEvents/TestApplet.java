import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TestApplet extends Applet {
	public void init() {
		final Button 	
				button1 = new Button("generate active event");

		add(button1);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnActiveEvent 	ae 		= new AnActiveEvent();
				EventQueue 		queue 	= new EventQueue();

				queue.postEvent(ae);	
			}
		});
	}
}
class AnActiveEvent extends AWTEvent implements ActiveEvent {
	public AnActiveEvent() {
		super(new Object(), AWTEvent.RESERVED_ID_MAX + 1);
	}
	public void dispatch() {
		System.out.println("active event dispatched");	
	}
}
