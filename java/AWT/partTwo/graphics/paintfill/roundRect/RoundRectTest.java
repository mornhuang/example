import java.applet.Applet;
import java.awt.*;

public class RoundRectTest extends Applet {
	public void paint(Graphics g) {
		Dimension sz = getSize();
		int       rows = 2;
		int       rowh = sz.height/rows;

		for(int i=0; i < 100; i+=10) {
			g.drawRoundRect(i,					// origin x 
							i,  				// origin y 
							sz.width-1-(i*2), 	// width of rectangle
							sz.height-1-(i*2), 	// height of rectangle
							sz.width/3,			// horizontal arc diameter
							sz.height/3);		// vertical arc diameter
		}
	}
}
