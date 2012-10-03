import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TranslateTest extends Applet {
	Image image;
	Point pressed = new Point(), lastTranslate = new Point();

	public void init() {
		image = getImage(getCodeBase(), "saint.gif");
		try {
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(image, 0);
			mt.waitForID(0);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point loc = e.getPoint();

				// adjust mouse pressed location for
				// translation ...
				pressed.x = loc.x - lastTranslate.x;
				pressed.y = loc.y - lastTranslate.y;
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point loc = e.getPoint();
				Point translate = new Point(loc.x - pressed.x,
											loc.y - pressed.y);
				Graphics g = getGraphics();

				try {
					g.clearRect(0,0,
								getSize().width,getSize().height);
					g.translate(translate.x, translate.y);
					showStatus("Translating Graphics:  " + 
								translate);

					g.drawImage(image, 0, 0, TranslateTest.this);
				}
				finally {
					g.dispose();
				}
				lastTranslate = translate;
			}
		});
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
}
