import java.awt.*;

class Lightweight extends Container {
	private Image image;

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
