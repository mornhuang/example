import java.applet.Applet;
import java.awt.*;

public class TestApplet extends Applet {
	public void init() {
		Lightweight lw = new Lightweight();
		lw.setForeground(Color.white);
		add(lw);
	}
}
class Lightweight extends Component {
	public void paint(Graphics g) {
		Dimension sz = getSize();
		g.fillRect(0,0,sz.width,sz.height);
	}
}
