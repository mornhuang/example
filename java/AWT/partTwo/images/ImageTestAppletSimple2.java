import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class ImageTestAppletSimple2 extends Applet {
 	private Image im;

	public void init() {
		im = getImage(getCodeBase(), "saint.gif");
	}
	public void paint(Graphics g) {
		System.out.println("drawing image ...");
	  	System.out.println(g.drawImage(im,0,0,this));
	}
}
