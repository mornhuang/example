import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class HoldRef extends Applet {
	private Graphics oldg;
	private boolean first = true;

	public void paint(Graphics g) {
		if(first) {
			oldg = g;
			first = false;
		}
		oldg.drawLine(0,0,getSize().width-1, getSize().height-1);
	}
}
