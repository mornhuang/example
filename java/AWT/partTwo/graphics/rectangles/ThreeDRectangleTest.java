import java.applet.Applet;
import java.awt.*;

public class ThreeDRectangleTest extends Applet {
	public void paint(Graphics g) {
		Insets insets = getInsets();
		Util.draw3DRectangle(this, 
							insets.left, insets.top,
						   	100, 100, 
						   	2, 
							BorderStyle.RAISED,
						   	SystemColor.controlShadow);

		Util.draw3DRectangle(this, 
							insets.left+110, insets.top,
						   	100, 200, 
						   	4, 
							BorderStyle.INSET,
						   	SystemColor.controlShadow);

		Util.draw3DRectangle(this, 
							insets.left+220, insets.top,
						   	200, 100, 
						   	6, 
							BorderStyle.RAISED,
						   	SystemColor.controlShadow);
	}
}
