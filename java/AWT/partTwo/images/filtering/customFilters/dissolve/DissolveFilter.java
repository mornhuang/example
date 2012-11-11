import java.awt.*;
import java.awt.image.*;
import java.util.Hashtable;

class DissolveFilter extends ImageFilter {
	ColorModel defaultRGB = ColorModel.getRGBdefault();
	int pixelRow[], alpha;

	public DissolveFilter() {
		this(100);
	}
	public DissolveFilter(int alpha) {
		if(alpha < 0 || alpha > 255) 
			throw new IllegalArgumentException(
						"bad alpha argument");

		this.alpha = alpha;
	}
	public void setDimensions(int width, int height) {
		pixelRow = new int[width];
		consumer.setDimensions(width, height);
	}
	public void setColorModel(ColorModel model) {
		consumer.setColorModel(defaultRGB);
	}
	public void setProperties(Hashtable props) {
		String key = "dissolve";
		String val = Integer.toString(alpha);
		Object o = props.get(key);

		if (o != null && o instanceof String)
			val = ((String) o) + ", " + val;

		props.put(key, val);
		consumer.setProperties(props);	
	}
	public void setPixels(int x, int y, int w, int h,
							ColorModel cm, int pixels[],
							int offset, int scansize) {
		int pixel, index, originalAlpha;

		for(int row=0; row < h; row++) {
			for(int col=0; col < w; col++) {
				index = offset + row*scansize + col;
				pixel = cm.getRGB(pixels[index]);
				originalAlpha = defaultRGB.getAlpha(pixel);

				pixel = originalAlpha == 0 ? 0 : alpha << 24 | 
						defaultRGB.getRed(pixel) << 16 |
						defaultRGB.getGreen(pixel) << 8 |
						defaultRGB.getBlue(pixel);

				pixelRow[col] = pixel;
			}
			consumer.setPixels(x,y+row,w,1,
								defaultRGB,pixelRow,0,w);
		}
	}
	public void setPixels(int x, int y, int w, int h,
							ColorModel cm, byte pixels[],
							int offset, int scansize) {
		int pixel, index, originalAlpha;

		for(int row=0; row < h; row++) {
			for(int col=0; col < w; col++) {
				index = offset + row*scansize + col;
				pixel = cm.getRGB(pixels[index] & 0xff);

				originalAlpha = defaultRGB.getAlpha(pixel);

				pixel = originalAlpha == 0 ? 0 : alpha << 24 | 
						defaultRGB.getRed(pixel) << 16 |
						defaultRGB.getGreen(pixel) << 8 |
						defaultRGB.getBlue(pixel);

				pixelRow[col] = pixel;
			}
			consumer.setPixels(x,y+row,w,1,
								defaultRGB,pixelRow,0,w);
		}
	}
}
