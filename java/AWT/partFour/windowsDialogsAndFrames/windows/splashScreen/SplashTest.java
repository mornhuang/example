import java.awt.*;
import java.awt.event.*;

public class SplashTest extends Frame {
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Window  window;
	Image   image;

	static public void main(String[] args) {
		Frame frame = new SplashTest();
	}
	public SplashTest() {
		ImageCanvas canvas;

		window  = new Window(this);
		image   = toolkit.getImage("gifs/saint.gif");
		canvas  = new ImageCanvas(image);

		window.add(canvas, "Center");

		Dimension   scrnSize  = toolkit.getScreenSize();
		int         imgWidth  = image.getWidth(this),
		            imgHeight = image.getHeight(this);

		window.setLocation(scrnSize.width/2  - (imgWidth/2),
		                   scrnSize.height/2 - (imgHeight/2));
		window.setSize(imgWidth,imgHeight);
		window.show();
		window.toFront();

		try { Thread.currentThread().sleep(10000); }
		catch(Exception e) { e.printStackTrace(); }

		window.dispose();
		System.exit(0);
	}
}
class ImageCanvas extends Canvas {
    private Image image;

    public ImageCanvas(Image image) {
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 0);

		try                { mt.waitForID(0);     } 
		catch(Exception e) { e.printStackTrace(); }

		this.image = image;
    }
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
    public void update(Graphics g) {
        paint(g);
    }
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(this),
		                     image.getHeight(this));
	}
}
