import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

public class DissolveEdgeFilterTest extends Applet {
    private Image   im, alpha;
    private int    	alphaValue;

    public void init() {
		loadImage();
		filterImage();
    }
    public void paint(Graphics g) {
		int imw = im.getWidth(this);

		g.drawImage(im,0,0,this);
		g.drawImage(alpha,imw+10,0,this);
    }
	private void loadImage() {
		try {
			URL url = getClass().getResource("gjMedium.gif");
			im = createImage((ImageProducer)url.getContent());
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		MediaTracker mt = new MediaTracker(this);
		mt.addImage(im, 0);
		try {
			mt.waitForID(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void filterImage() {
		Insets insets = new Insets(15,15,15,15);
		alpha = createImage(new FilteredImageSource(
				im.getSource(), 
				new DissolveEdgeFilter(175, insets)));
	}
}
