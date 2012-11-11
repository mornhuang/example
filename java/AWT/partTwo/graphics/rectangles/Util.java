import java.applet.Applet;
import java.awt.*;
import java.util.*;

/**
 * A handy collection of methods for getting a component's 
 * frame, getting a component's applet, waiting for a 
 * component's image, wallpapering a components background, 
 * drawing rectangles inside a component of various types and
 * sizes, and localizing strings given a bundle name.
 * <p>
 *
 * @version 1.0, Apr 1 1996
 * @author  David Geary
 */
public class Util {
	public static Dialog getDialog(Component c) {
        if(c instanceof Dialog)
            return (Dialog)c;

        while((c = c.getParent()) != null) {
            if(c instanceof Dialog)
                return (Dialog)c;
		}
		return null;
	}
    public static Frame getFrame(Component c) {
        if(c instanceof Frame)
            return (Frame)c;

        while((c = c.getParent()) != null) {
            if(c instanceof Frame)
                return (Frame)c;
        }
        return null;
    }
    public static Applet getApplet(Component c) {
        if(c instanceof Applet)
            return (Applet)c;

        while((c = c.getParent()) != null) {
            if(c instanceof Applet)
                return (Applet)c;
        }
        return null;
    }
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
            for(int c=0; c < compsize.height; c += patchH)
            g.drawImage(image, r, c, component);
        }
    }
	public static void stretchImage(Component component,
	                                Graphics  g,
									Image     image) {
		Dimension sz = component.getSize();
		waitForImage(component, image);
		g.drawImage(image, 0, 0, sz.width, sz.height, component);
	}
    public static void setCursor(int cursor, 
                                 Component component) {
		component.setCursor(Cursor.getPredefinedCursor(cursor));
    }
	public static String getLocalizedString(Locale locale, 
	                                        String bundleName,
	                                        String key) {
		ResourceBundle rb = null;

		try {
			rb = ResourceBundle.getBundle(bundleName, locale);
		}
		catch(MissingResourceException e) {
			e.printStackTrace();
		}
		if(rb == null) return null;
		else           return rb.getString(key);
	}
	public static void forceLayout(Component c) {
		if(c.isShowing()) {
			Container parent = c.getParent();
			c.invalidate();
			parent.validate();
		}
	}
	public static void drawRectangle(Component c,
									int x, int y, int w, int h, 
									int thickness,
									Color   lineColor) {
		Graphics g = c.getGraphics();

		if(g != null) {
			g.setColor(lineColor);

        	for(int i=0; i < thickness; ++i)
        	    g.drawRect(x+i, y+i, w-(i*2)-1, h-(i*2)-1);	

			g.dispose();
		}
	}
	public static void draw3DRectangle(Component c,
									int x, int y, int w, int h, 
									int thickness,
									BorderStyle style,
									Color   lineColor) {
		Color topLeft, bottomRight;
		Graphics g = c.getGraphics();

		if(g != null) {
			if(style == BorderStyle.RAISED) {
				topLeft = 
				lineColor.brighter().brighter().brighter().brighter();
				bottomRight = lineColor;
			}
			else {
				topLeft = lineColor;
				bottomRight = 
				lineColor.brighter().brighter().brighter().brighter();
			}
			g.setColor(topLeft);
        	for(int i=0; i < thickness; ++i) {
        	    g.drawLine(x+i, y+i, x+w-(i+1), y+i);
        	    g.drawLine(x+i, y+i+1, x+i, y+h-(i+1));  
        	}
			g.setColor(bottomRight);
        	for(int i=1; i <= thickness; ++i) {
        	    g.drawLine(x+i-1, y+h-i, x+w-i, y+h-i);
        	    g.drawLine(x+w-i, y+i-1, x+w-i, y+h-i);
        	}
			g.dispose();
		}
	}
	public static void drawEtchedRectangle(Component c,
										   int x, int y, 
										   int w, int h, 
										   int thickness,
										   Etching etching,
										   Color   lineColor) {
		Color topLeft, bottomRight;
		Graphics g = c.getGraphics();

		if(g != null) {
			if(etching == Etching.IN) {
				topLeft = lineColor;
				bottomRight = 
				lineColor.brighter().brighter().brighter().brighter();
			}
			else {
				bottomRight = lineColor;
				topLeft = 
				lineColor.brighter().brighter().brighter().brighter();
			}
			g.setColor(topLeft);

        	for(int i=0; i < thickness/2; ++i) 
            	g.drawRect(x+i, y+i, w, h);

        	g.setColor(bottomRight);

        	for(int i=0; i < thickness/2; ++i) 
        	    g.drawRect(	(thickness/2)+x+i, 
							(thickness/2)+y+i, w, h);
			g.dispose();
		}
		
	}
}
