import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;

public class Test extends Applet {
	private ColorModel defaultRGB = ColorModel.getRGBdefault();
	private Image 	image;
	private int		imw, imh, pixels[];

	public void init() {
		MediaTracker mt = new MediaTracker(this);

		URL url = getClass().getResource("tiger.gif");

		try {
			image = createImage((ImageProducer)url.getContent());
			mt.addImage(image, 0);
			mt.waitForID(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		imw = image.getWidth(this); 
		imh = image.getWidth(this);
		pixels = new int[imw*imh];

		try {
			PixelGrabber pg = 
				new PixelGrabber(image, 0, 0, imw, imh, 
									pixels, 0, imw);
			pg.grabPixels();
		}
		catch(InterruptedException e) {
			e.printStackTrace();	
		}

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				int mx = e.getX(), my = e.getY();

				if(mx > 0 && mx < imw && my > 0 && my < imh) {
					int pixel = ((int[])pixels)[my*imw + mx];

					int red 	= defaultRGB.getRed(pixel),
						green 	= defaultRGB.getGreen(pixel),
						blue 	= defaultRGB.getBlue(pixel),
						alpha 	= defaultRGB.getAlpha(pixel);

					showStatus("red=" + red + " green=" + green +
							" blue=" + blue + " alpha=" + alpha);
				}
			}
		});
	}
	public void paint(Graphics g) {
		Insets insets = getInsets();
		g.drawImage(image, insets.left, insets.top, this);
	}
}
