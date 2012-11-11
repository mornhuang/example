import java.awt.image.*;

public class DissolveFilter extends RGBImageFilter {
    private int alpha;

    public DissolveFilter() {
        this(0);
    }
    public DissolveFilter(int alpha) {
        canFilterIndexColorModel = true;

        if(alpha < 0 && alpha > 255)
			throw new IllegalArgumentException("bad alpha");

        this.alpha = alpha;
    }
    public int filterRGB(int x, int y, int rgb) {
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
