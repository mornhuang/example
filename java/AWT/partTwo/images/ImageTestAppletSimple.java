import java.net.URL;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class ImageTestAppletSimple extends Applet {
 	private Image im;

	public void init() {
		URL codebase = getCodeBase();
		System.out.println(codebase);

		im = getImage(codebase, "saint.jpg");

		System.out.print  ("Image width=" + im.getWidth(this));
		System.out.println(" height=" + im.getHeight(this));
	}
	public void paint(Graphics g) {
	  	g.drawImage(im,0,0,this);
	}
}
