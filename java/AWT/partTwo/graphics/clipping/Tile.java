import java.applet.Applet;
import java.awt.*;

public class Tile extends Applet {
	Image tile;

	public void init() {
		tile = Toolkit.getDefaultToolkit().getImage("tile.gif");
		MediaTracker mt = new MediaTracker(this);
		try {
			mt.addImage(tile, 0);
			mt.waitForID(0);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		Dimension size = getSize();
		int w = tile.getHeight(this);
        int h = tile.getHeight(this);

        for(int r=0; r < size.width; r += w) {
            for(int c=0; c < size.height; c += h)
            g.drawImage(tile, r, c, this);
        }
	}
}
