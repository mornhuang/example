import java.applet.Applet;
import java.awt.*;

public class SimpleLightweightContainer extends Applet {
	private Image dining, paper;

    public void init() {
		dining = getImage(getCodeBase(),"../gifs/Dining.gif"); 
		paper  = getImage(getCodeBase(),"../gifs/paper.gif");

		Container container = new LightweightContainer(paper);	
		container.add(new Lightweight(dining));
		setLayout(new BorderLayout());
		add(container, "Center");
    }
}
class LightweightContainer extends Container {
	private Image wallpaperImage;

	public LightweightContainer(Image wallpaperImage) {
		this.wallpaperImage = wallpaperImage;
		setLayout(new FlowLayout());
	}
	public void paint(Graphics g) {
		Util.wallPaper(this, g, wallpaperImage);
		super.paint(g);
	}
}
class Lightweight extends Component {
	private Image   image;

	public Lightweight(Image image) {
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
