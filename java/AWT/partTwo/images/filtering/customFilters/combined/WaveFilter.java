import java.awt.*;
import java.awt.image.*;

class WaveFilter extends ImageFilter {
	ColorModel defaultRGB = ColorModel.getRGBdefault();
	int amplitude, frequency, owidth, oheight, nheight;
	int sineArray[], intPixels[];

	public WaveFilter() {
		this(10,10);
	}
	public WaveFilter(int amplitude, int frequency) {
		this.amplitude = amplitude;
		this.frequency = frequency;
	}
	public void setDimensions(int width, int height) {
		int x;
		owidth 	= width;
		oheight	= height;
		nheight = height+(amplitude*2);
		intPixels	= new int[owidth * nheight];

		sineArray = new int[width];
		for(x=0; x < width; ++x) {
			sineArray[x] = 
				((int)(amplitude*Math.sin(((double)(x)) /
		        ((double)frequency))));
		}
		consumer.setDimensions(width, nheight);
	}
	public void setHints(int hints) {
		int newHints = 0;
		consumer.setHints(newHints | SINGLEPASS);
	}
	public void setPixels(int x, int y, int w, int h,
							ColorModel cm, int pixels[],
							int offset, int scansize) {
		int index, destY, destIndex = 0;

		for(int row=0; row < h; row++) {
			for(int col=0; col < w; col++) {
				destY     	= y + row + amplitude + sineArray[col];
				destIndex 	= (destY*w) + x + col;
				index 		= offset + row*scansize + col;

				intPixels[destIndex] = 
					cm.getRGB(pixels[index]);
			}
		}
	}
	public void setPixels(int x, int y, int w, int h,
							ColorModel cm, byte pixels[],
							int offset, int scansize) {
		int index, destY, destIndex = 0;

		for(int row=0; row < h; row++) {
			for(int col=0; col < w; col++) {
				destY     	= y + row + amplitude + sineArray[col];
				destIndex 	= (destY*w) + x + col;
				index 		= offset + row*scansize + col;

				intPixels[destIndex] = 
					cm.getRGB(pixels[index] & 0x0ff);
			}
		}
	}
	public void imageComplete(int status) {
		if(status == IMAGEERROR || status == IMAGEABORTED) {
			consumer.imageComplete(status);
			return;
		}
		consumer.setPixels(0,0,owidth,nheight,
								defaultRGB,intPixels,0,owidth);
		consumer.imageComplete(status);
	}
}
