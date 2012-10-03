import java.applet.Applet;
import java.awt.*;

public class DrawArc extends Applet {
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawArc(10,10,150,100,0,270);
	}
}
