import java.awt.*;
import java.awt.image.*;

public class DissolveEdgeFilter extends RGBImageFilter {
	private Insets insets;
    private int alpha;
	private int width, height;

    public DissolveEdgeFilter(int alpha, Insets insets) {
		this.insets = insets;

        if(alpha < 0 && alpha > 255)
			throw new IllegalArgumentException("bad alpha");

        this.alpha = alpha;
    }
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		super.setDimensions(width,height);
	}
    public int filterRGB(int x, int y, int rgb) {
		int modifiedRGB = rgb;

		if(x < insets.left || x > width - insets.right ||
			y < insets.top || y > height - insets.bottom)
				modifiedRGB = modifyAlpha(rgb);

		return modifiedRGB;
	}
	private int modifyAlpha(int rgb) {
        DirectColorModel cm = 
            (DirectColorModel)ColorModel.getRGBdefault();

        int alpha = cm.getAlpha(rgb);
        int red   = cm.getRed  (rgb);
        int green = cm.getGreen(rgb);
        int blue  = cm.getBlue (rgb);

        alpha = alpha == 0 ? 0 : this.alpha;

        return alpha << 24 | red << 16 | green << 8 | blue;
    }
}
