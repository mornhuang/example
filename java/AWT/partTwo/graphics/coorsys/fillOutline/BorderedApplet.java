import java.applet.Applet;
import java.awt.*;

public class BorderedApplet extends Applet {
	public void paint(Graphics g) {
		g.drawRect(0,0,getSize().width-1,getSize().height-1);
		g.setColor(Color.magenta);
		g.fillRect(1,1,getSize().width-2,getSize().height-2);
	}
}
