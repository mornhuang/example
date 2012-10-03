import java.applet.Applet;
import java.awt.*;

public class MouseSensorApplet extends Applet {
	public void init() {
		setLayout(new BorderLayout());
		add(new MouseSensorCanvas(), "Center");
	}
}

class MouseSensorCanvas extends Canvas {
	public boolean mouseDown(Event event, int x, int y) {
		System.out.println(whichMouseButton(event) + ":  Down");
		return true;
	}
	public boolean mouseUp(Event event, int x, int y) {
		System.out.println(whichMouseButton(event) + ":  Up");
		return true;
	}
	public boolean mouseDrag(Event event, int x, int y) {
		System.out.println(whichMouseButton(event) + ":  Drag");
		return true;
	}
	private String whichMouseButton(Event event) {
		String s = new String("Mouse Button 1");

		if((event.modifiers & Event.META_MASK) != 0) 
			s = "Mouse Button 2";
		else if((event.modifiers & Event.ALT_MASK) != 0) 
			s = "Mouse Button 3";

		return s;
	}
}
