import java.applet.Applet;
import java.awt.*;

public class DrawAndFillArc extends Applet {
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawArc(10,10,150,100,0,270);
		g.setColor(Color.yellow);
		g.fillArc(10,10,150,100,0,270);
	}
}
