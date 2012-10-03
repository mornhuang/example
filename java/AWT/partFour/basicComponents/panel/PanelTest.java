import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class PanelTest extends Applet {
	public void init() {
		Panel canvas = new ExamplePanel();
		canvas.addComponentListener(new DbgComponentListener());
		add(canvas);
	}
}
class ExamplePanel extends Panel {
	public void paint(Graphics g) {
		Dimension size = getSize();
		g.drawRect(0,0,size.width-1,size.height-1);
		g.setColor(Color.lightGray);
		g.draw3DRect(1,1,size.width-3,size.height-3,true);

		g.setColor(Color.blue);
		g.drawString("Panel!",20,20);

		g.setColor(Color.orange);
		g.fillRect(10,40,20,20);
		g.setColor(Color.red);
		g.drawRect(9,39,22,22);

		g.setColor(Color.gray);
		g.drawLine(40,25,80,80);
		g.setColor(Color.black);
		g.drawLine(50,50,20,90);

		g.setColor(Color.cyan);
		g.fillArc(60,25,30,30,0,270);
	}
	public Dimension getPreferredSize() {
		return new Dimension(100,100);
	}
}
