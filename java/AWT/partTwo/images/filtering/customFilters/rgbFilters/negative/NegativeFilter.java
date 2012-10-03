import java.awt.image.*;

public class NegativeFilter extends RGBImageFilter {
    public NegativeFilter() {
        canFilterIndexColorModel = true;
    }
    public int filterRGB(int x, int y, int rgb) {
        DirectColorModel cm = 
            (DirectColorModel)ColorModel.getRGBdefault();

        int    alpha = cm.getAlpha(rgb);
        int    red   = cm.getRed  (rgb);
        int    green = cm.getGreen(rgb);
        int    blue  = cm.getBlue (rgb);

		red   = Math.abs(255 - red);
		green = Math.abs(255 - green);
		blue  = Math.abs(255 - blue );

		alpha = alpha << 24;
        red   = red   << 16;
        green = green << 8;
	
        return alpha | red | green | blue;
    }
}
