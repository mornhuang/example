import java.awt.*;
import java.awt.image.*;
import java.util.Hashtable;

class WaveFilter extends ImageFilter {
	ColorModel defaultRGB = ColorModel.getRGBdefault();
	int amplitude, frequency, width, height;
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
		this.width 	= width;
		this.height = height+(amplitude*2);
		intPixels	= new int[width * this.height];

		sineArray = new int[width];
		for(x=0; x < width; ++x) {
			sineArray[x] = 
				((int)(amplitude*Math.sin(((double)(x)) /
		        ((double)frequency))));
		}
		consumer.setDimensions(width, this.height);
	}
	public void setProperties(Hashtable props) {
		String 	akey = "wave amplitude", fkey = "wave frequency";
		String 	aval = Integer.toString(amplitude), 
				fval = Integer.toString(frequency);
		Object o = props.get(akey);

		if (o != null && o instanceof String)
			aval = ((String) o) + ", " + aval;

		o = props.get(fkey);

		if (o != null && o instanceof String)
			fval = ((String) o) + ", " + fval;

		props.put(akey, aval);
		props.put(fkey, fval);

		//System.out.println(props);
		consumer.setProperties(props);	
	}
	public void setColorModel(ColorModel model) {
		consumer.setColorModel(defaultRGB);
	}
	public void setHints(int hints) {
		hints |= TOPDOWNLEFTRIGHT | COMPLETESCANLINES;
		hints &= ~RANDOMPIXELORDER;	

		consumer.setHints(hints);
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
		consumer.setPixels(0,0,width,height,
								defaultRGB,intPixels,0,width);
		consumer.imageComplete(status);
	}
}
