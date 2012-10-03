import java.net.URL;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

public class ImageTestAppletWithMediaTracker extends Applet {
 	private Image im;

	public void init() {
		MediaTracker tracker = new MediaTracker(this);

		im = getImage(getCodeBase(), "saint.gif");

		tracker.addImage(im, 0); 
		try { tracker.waitForID(0); }
		catch(InterruptedException e) { e.printStackTrace(); }

		System.out.print  ("Image width=" + im.getWidth(this));
		System.out.println(" height=" + im.getHeight(this));
	}
	public void paint(Graphics g) {
	  	g.drawImage(im,0,0,this);
	}
}
