import java.applet.Applet;
import java.awt.*;

public class DoubleClickApplet extends Applet {
	public void init() {
		setLayout(new BorderLayout());
		add(new DoubleClickCanvas(), "Center");
	}
}

class DoubleClickCanvas extends Canvas {
	public boolean mouseDown(Event event, int x, int y) {
		if(event.clickCount == 2)
			System.out.println("Double click");
		return true;
	}
}
