import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;

public class CropTest extends Applet {
 	private Image im;
	private Image cropped;

	public void init() {
		MediaTracker mt  = new MediaTracker(this);
		URL 		 url = getClass().getResource("pic.jpg");

		try {
			im = createImage((ImageProducer)url.getContent());
			mt.addImage(im, 0);
			mt.waitForID(0);
		}
		catch(Exception e) { e.printStackTrace(); }	

		ImageFilter filter = new CropImageFilter(110,5,100,100);

		FilteredImageSource fis = 
			new FilteredImageSource(im.getSource(), filter);

		cropped = createImage(fis);	
	}
	public void paint(Graphics g) {
		g.drawImage(im,0,0,this);
	  	g.drawImage(cropped,im.getWidth(this)+20,0,this);
	}
}
