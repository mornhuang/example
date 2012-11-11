import java.applet.Applet;
import java.awt.*;

public class DrawnRectangleTest extends Applet {
	public void paint(Graphics g) {
		Insets insets = getInsets();
		Util.drawRectangle(this, 
							insets.left, insets.top,
						   	100, 100, 
						   	2, 
						   	SystemColor.controlShadow);
		Util.drawRectangle(this, 
							insets.left+110, insets.top,
						   	100, 200, 
						   	4, 
						   	SystemColor.blue);
		Util.drawRectangle(this, 
							insets.left+220, insets.top,
						   	200, 100, 
						   	6, 
						   	SystemColor.yellow);
	}
}
