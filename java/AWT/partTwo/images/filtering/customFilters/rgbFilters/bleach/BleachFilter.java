import java.awt.image.*;

public class BleachFilter extends RGBImageFilter {
    private int percent;

    public BleachFilter(int percent) {
        if(percent < 0 || percent > 100)
			throw new IllegalArgumentException(
							"bad bleach percent");
        this.percent = percent;
        canFilterIndexColorModel = true;
    }
    public int  percent()            { return percent;         }
    public void percent(int percent) { this.percent = percent; }

    public int filterRGB(int x, int y, int rgb) {
        DirectColorModel cm = 
            (DirectColorModel)ColorModel.getRGBdefault();

        int    alpha = cm.getAlpha(rgb);
        int    red   = cm.getRed  (rgb);
        int    green = cm.getGreen(rgb);
        int    blue  = cm.getBlue (rgb);
        double percentMultiplier = (double)percent/100;

        red   = Math.min((int)
                (red + (red * percentMultiplier)), 255);
        green = Math.min((int)
                (green + (green * percentMultiplier)), 255);
        blue  = Math.min((int)
                (blue  + (blue  * percentMultiplier)), 255);

        alpha = alpha << 24;
        red   = red   << 16;
        green = green << 8;

        return alpha | red | green | blue;
    }
}
