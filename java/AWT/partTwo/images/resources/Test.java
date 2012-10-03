import java.applet.Applet;
import java.net.URL;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.awt.event.*;

public class Test extends Applet {
	Image im;

	public void start() {
		URL url = getClass().getResource("globe.gif");
		try {
			im = createImage((ImageProducer)url.getContent());
			if(im == null)
				System.out.println("null image");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		Insets insets = getInsets();
		g.drawImage(im, insets.left, insets.top, this);
	}
	public void update(Graphics g) {
		paint(g);
	}
}
