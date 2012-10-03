import java.awt.*;
import java.awt.event.*;

public class ImageTestApplication extends Frame {
	Insets 		insets;
 	Image 		im;

	static public void main(String args[]) {
		ImageTestApplication app = new ImageTestApplication();
		app.show();
	}
	public ImageTestApplication() {
		super("Image Test");
		im = Toolkit.getDefaultToolkit().getImage("cougar.jpg");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
	}
	public void addNotify() {
		super.addNotify();		// peer is created here
		insets = getInsets();
		setBounds(100, 100, 217 + insets.left, 321 + insets.top);
	}
	public void paint(Graphics g) {
	  	g.drawImage(im, insets.left, insets.top, this);
	}
}
