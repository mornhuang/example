import java.applet.Applet;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;

public class DoubleBufferedContainerTest extends Applet {
	private DoubleBufferedContainer container;

    public void init() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL		cb = getCodeBase();

		Image mandrill = getImage(cb, "gifs/mandrill.jpg");
		Image dining = getImage(cb, "gifs/Dining.gif");
		Image bg = getImage(cb, "gifs/rain.gif");
		Image skelly = getImage(cb, "gifs/skelly.gif");
		Image gj = getImage(cb, "gifs/gjMedium.gif");

		container = new DoubleBufferedContainer();
		container.setWallpaperImage(bg);

		container.add(new Lightweight(skelly));
		container.add(new Lightweight(dining));
		container.add(new Lightweight(gj));
		container.add(new Lightweight(mandrill, false));

		setLayout(new BorderLayout());
		add(container, "Center");
	}
	public void update(Graphics g) {
		paint(g);
	}
}
