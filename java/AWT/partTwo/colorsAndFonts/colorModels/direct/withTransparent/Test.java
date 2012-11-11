import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Test extends Applet {
	Color[]	colors = { 	
			Color.red, Color.yellow, Color.blue, Color.cyan,
			Color.white, Color.green, Color.magenta, Color.orange};

	ColorModel defaultRGB = ColorModel.getRGBdefault();
	Image image;

	public void init() {
		int rp = 0xff << 24 | 0xff << 16; 
		int bp = 0xff << 24 | 0xff; 
		int tp = 0x00 << 24 | 0xff << 16 | 0xff << 8 | 0xff;

		int[] imageBits = new int[] { 
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp,
				rp,rp,rp,tp,tp,tp,bp,bp,bp };

		MemoryImageSource mis = new MemoryImageSource(
								9,10,  // width, height of image
								defaultRGB,	// ColorModel
								imageBits, 	// bits of image
								0, 			// offset
								9);			// scansize
		image = createImage(mis);
	}
	public void paint(Graphics g) {
		g.drawImage(image,0,0,240,200,this);
	}
}
