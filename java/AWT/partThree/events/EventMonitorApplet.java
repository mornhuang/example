import java.applet.Applet;
import java.awt.*;

public class EventMonitorApplet extends Applet {
	public static EventPrinter printer = new EventPrinter();

	public void init() {
		setLayout(new BorderLayout());
		add(new EventMonitor(), "Center");
	}
	public boolean handleEvent(Event event) {
		System.out.print("APPLET:  ");
		printer.print(event);
		System.out.println();
		return true;
	}
}

class EventMonitor extends Canvas {
	public boolean handleEvent(Event event) {
		System.out.print("CANVAS:  ");
		EventMonitorApplet.printer.print(event);
		System.out.println();
		return true;
	}
}

class EventPrinter {
	public void print(Event event) {
		String s = null;

		if(event.id == Event.MOUSE_DOWN)       s = "Mouse Down";
		else if(event.id == Event.MOUSE_UP)    s = "Mouse Up";
		else if(event.id == Event.MOUSE_DRAG)  s = "Mouse Drag";
		else if(event.id == Event.MOUSE_MOVE)  s = "Mouse Move";
		else if(event.id == Event.MOUSE_EXIT)  s = "Mouse Exit";
		else if(event.id == Event.MOUSE_ENTER) s = "Mouse Enter";

		if(s != null)
			System.out.print(s);
		else
			System.out.print(event.id);
	}
}
