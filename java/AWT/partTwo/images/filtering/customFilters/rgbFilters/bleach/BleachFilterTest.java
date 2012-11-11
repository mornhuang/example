import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;

public class BleachFilterTest extends Applet {
    private Image   im, bleached;
    private int    	bp;

    public void init() {
		getBleachPercent();
		loadImage();
		filterImage();
    }
    public void paint(Graphics g) {
		int imw = im.getWidth(this);

		g.drawImage(im,0,0,this);
		g.drawImage(bleached,imw,0,this);
    }
	private void loadImage() {
		try {
			URL url = getClass().getResource("tiger.gif");
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
	private void getBleachPercent() {
        String bleachPercent = getParameter("bleachPercent");

        if(bleachPercent != null) 
            bp = new Integer(bleachPercent).intValue();
        else                      
            bp = 50;
	}
	private void filterImage() {
		bleached = createImage(new FilteredImageSource(
						im.getSource(), new BleachFilter(bp)));
	}
}
