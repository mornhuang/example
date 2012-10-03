import java.applet.Applet;
import java.awt.*;

public class TestApplet extends Applet {
	public void init() {
		Heavyweight hw = new Heavyweight();
		hw.setForeground(Color.white);
		add(hw);
	}
}
class Heavyweight extends Canvas {
	public void paint(Graphics g) {
		Dimension sz = getSize();
		g.fillRect(0,0,sz.width,sz.height);
	}
	public Dimension getPreferredSize() {
		return new Dimension(100,100);
	}
}
