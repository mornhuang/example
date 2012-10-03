import java.applet.Applet;
import java.awt.*;

public class TestApplet extends Applet {
	public void init() {
		Heavyweight hw = new Heavyweight();
		hw.setForeground(Color.white);
		add(hw);
	}
}
class Heavyweight extends Panel {
	public void paint(Graphics g) {
		Dimension sz = getSize();
		g.fillRect(0,0,sz.width,sz.height);
	}
}
