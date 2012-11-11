import java.applet.Applet;
import java.awt.*;

public class CopyTest extends Applet {
	public void paint(Graphics g) {
		setForeground(Color.yellow);
		g.drawLine(0,0,getSize().width-1, getSize().height-1);
	}
}
