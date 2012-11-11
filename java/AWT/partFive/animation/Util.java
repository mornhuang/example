import java.awt.*;

public class Util {
    public static void waitForImage(Component component, 
                                    Image image) {
        MediaTracker tracker = new MediaTracker(component);
        try {
            tracker.addImage(image, 0);
            tracker.waitForID(0);
        }
        catch(InterruptedException e) { e.printStackTrace(); }
    }
    public static void wallPaper(Component component, 
                            Graphics  g, 
                            Image     image) {
        Dimension compsize = component.getSize();
        Util.waitForImage(component, image);

        int patchW = image.getWidth(component);
        int patchH = image.getHeight(component);

        for(int r=0; r < compsize.width; r += patchW) {
            for(int c=0; c < compsize.height; c += patchH) {
            	g.drawImage(image, r, c, component);
			}
        }
    }
}
