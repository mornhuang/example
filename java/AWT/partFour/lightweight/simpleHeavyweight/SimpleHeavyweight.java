import java.applet.Applet;
import java.awt.*;

public class SimpleHeavyweight extends Applet {
	private Image dining, paper;

	public void init() {
		dining = getImage(getCodeBase(),"../gifs/Dining.gif"); 
		paper  = getImage(getCodeBase(),"../gifs/paper.gif");
		add(new Heavyweight(dining));
	}
	public void paint(Graphics g) {
		Util.wallPaper(this, g, paper);
	}
}
class Heavyweight extends Panel {
	private Image   image;

	public Heavyweight(Image image) {
		this.image = image;
		Util.waitForImage(this, image);
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(this),
		                     image.getHeight(this));
	}
}
