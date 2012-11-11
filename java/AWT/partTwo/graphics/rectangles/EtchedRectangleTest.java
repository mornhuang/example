import java.applet.Applet;
import java.awt.*;

public class EtchedRectangleTest extends Applet {
	public void paint(Graphics g) {
		Insets insets = getInsets();
		Util.drawEtchedRectangle(this, 
							insets.left, insets.top,
						   	100, 100, 
						   	2, 
							Etching.OUT,
						   	SystemColor.controlShadow);

		Util.drawEtchedRectangle(this, 
							insets.left+110, insets.top,
						   	100, 200, 
						   	2, 
							Etching.IN,
						   	SystemColor.controlShadow);

		Util.drawEtchedRectangle(this, 
							insets.left+220, insets.top,
						   	200, 100, 
						   	3, 
							Etching.OUT,
						   	SystemColor.controlShadow);
	}
}
