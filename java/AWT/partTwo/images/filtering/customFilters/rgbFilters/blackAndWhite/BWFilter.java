import java.awt.image.*;

public class BWFilter extends RGBImageFilter {
    public BWFilter() {
        canFilterIndexColorModel = true;
    }
    public int filterRGB(int x, int y, int rgb) {
        DirectColorModel cm = 
            (DirectColorModel)ColorModel.getRGBdefault();

        int    alpha = cm.getAlpha(rgb);
        int    red   = cm.getRed  (rgb);
        int    green = cm.getGreen(rgb);
        int    blue  = cm.getBlue (rgb);
		int    mixed = (red + green + blue) / 3;

		red   = blue = green = mixed;
        alpha = alpha << 24;
        red   = red   << 16;
        green = green << 8;

        return alpha | red | green | blue;
    }
}
