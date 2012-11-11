import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class HoldRef2 extends Applet {
	private Graphics copy;
	private boolean first = true;

	public void paint(Graphics g) {
		if(first) {
			// note: copy is never disposed of
			copy = g.create();

			copy.setColor(Color.red);
			copy.setFont(new Font("Times Roman", Font.BOLD, 14));
			first = false;
		}
		copy.drawString("Red Text", 10, 10);
	}
}
