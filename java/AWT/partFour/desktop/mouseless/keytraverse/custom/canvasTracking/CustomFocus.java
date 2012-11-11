import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CustomFocus extends Applet {
	ColoredCanvas yellowCanvas = new ColoredCanvas(Color.yellow);
	ColoredCanvas blueCanvas   = new ColoredCanvas(Color.blue);
	ColoredCanvas whiteCanvas  = new ColoredCanvas(Color.white);

	public void init() {
		add(yellowCanvas);
		add(blueCanvas);
		add(whiteCanvas);

		yellowCanvas.requestFocus();
	}
}
