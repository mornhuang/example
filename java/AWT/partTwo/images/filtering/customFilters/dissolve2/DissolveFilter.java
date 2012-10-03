import java.awt.*;
import java.awt.image.*;

class DissolveFilter extends CacheImageFilter {
	ColorModel defaultRGB = ColorModel.getRGBdefault();
	int alpha, width, height, filteredPixels[];

	public DissolveFilter() {
		this(100);
	}
	public DissolveFilter(int alpha) {
		if(alpha < 0 || alpha > 255) 
			throw new IllegalArgumentException(
						"bad alpha argument");

		this.alpha = alpha;
	}
	public Dimension getFilteredSize(int width, int height) {
		int x;
		this.width 	= width;
		this.height	= height;

		filteredPixels = new int[width*this.height];
		return new Dimension(width, this.height);
	}
	public int[] getFilteredPixels(int[] originalPixels) {
		for(int i=0; i < originalPixels.length; ++i) {
			pixel = cm.getRGB(pixels[i]);
			originalAlpha = defaultRGB.getAlpha(pixel);

			pixel = originalAlpha == 0 ? 0 : alpha << 24 | 
						defaultRGB.getRed(pixel) << 16 |
						defaultRGB.getGreen(pixel) << 8 |
						defaultRGB.getBlue(pixel);

			filteredPixels[i] = pixel;
		}
	}
}
