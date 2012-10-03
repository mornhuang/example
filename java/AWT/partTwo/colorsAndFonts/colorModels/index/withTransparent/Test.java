import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Test extends Applet {
	Color[]	colors = { 	
			Color.red, Color.yellow, Color.blue, Color.cyan,
			Color.white, Color.green, Color.magenta, Color.orange};

	byte[] reds = { 
			(byte)colors[0].getRed(),(byte)colors[1].getRed(),
			(byte)colors[2].getRed(),(byte)colors[3].getRed(),
			(byte)colors[4].getRed(),(byte)colors[5].getRed(),
			(byte)colors[6].getRed(),(byte)colors[7].getRed() };

	byte[] greens = { 
			(byte)colors[0].getGreen(),(byte)colors[1].getGreen(),
			(byte)colors[2].getGreen(),(byte)colors[3].getGreen(),
			(byte)colors[4].getGreen(),(byte)colors[5].getGreen(),
			(byte)colors[6].getGreen(),(byte)colors[7].getGreen()};

	byte[] blues = { 
			(byte)colors[0].getBlue(),(byte)colors[1].getBlue(),
			(byte)colors[2].getBlue(),(byte)colors[3].getBlue(),
			(byte)colors[4].getBlue(),(byte)colors[5].getBlue(),
			(byte)colors[6].getBlue(),(byte)colors[7].getBlue() };

	IndexColorModel icm = new IndexColorModel(
							8, // bits per pixel
							8, // size of arrays that follow
							reds,  	// red components
							greens, // green components
							blues,	// blue components
							3);  // transparent pixel index
	Image image;

	public void init() {
		int[] imageBits = new int[] { 
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,
				0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7 };

		MemoryImageSource mis = new MemoryImageSource(
								24,10,  // width, height of image
								icm,	// ColorModel
								imageBits, // bits of image
								0, 		// offset
								24);	// scansize
		image = createImage(mis);
	}
	public void paint(Graphics g) {
		g.drawImage(image,0,0,240,200,this);
	}
}
