import java.applet.Applet;
import java.awt.*;

public class CreateTest extends Applet {
	private Image image;

	public void init() {
		MediaTracker mt = new MediaTracker(this); 
		image = getImage(getCodeBase(), "image.gif");

		try {
			mt.addImage(image, 0);
			mt.waitForID(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		Graphics copy = g.create(image.getWidth(this),0,100,100);

		try {
			System.out.println("g:  " + g.getClip().toString());
			System.out.println("copy: " + 
								copy.getClip().toString());

			g.drawImage(image, 0, 0, this);
			copy.drawImage(image, 0, 0, this);
		}
		finally {
			copy.dispose();
		}
	}
}
