import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;

public class CropTestWithPG extends Applet {
 	private Image im;
	private Image cropped;
	private int w = 75, h = 200;

	public void init() {
		MediaTracker mt  = new MediaTracker(this);
		URL 		 url = getClass().getResource("pic.jpg");

		try {
			im = createImage((ImageProducer)url.getContent());
			mt.addImage(im, 0);
			mt.waitForID(0);
		}
		catch(Exception e) { e.printStackTrace(); }	

		int[] pixels = new int[w*h];
		PixelGrabber pg = new PixelGrabber(im, 90, 5, w, h,
											pixels, 0, w);
		try {
			pg.grabPixels();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		ImageProducer ip = new MemoryImageSource(w,h,pixels,0,w);
		cropped = createImage(ip);
	}
	public void paint(Graphics g) {
		g.drawImage(im,0,0,this);
	  	g.drawImage(cropped,im.getWidth(this)+20,0,this);
	}
}
