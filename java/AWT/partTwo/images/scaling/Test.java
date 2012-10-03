import java.awt.*;
import java.awt.event.*;

public class Test extends Frame {
	Image im = Toolkit.getDefaultToolkit().getImage("tiger.gif");

	public Test() {
		super("scaling and flipping");

		MediaTracker mt = new MediaTracker(this);

		mt.addImage(im, 0);
		try {
			mt.waitForID(0);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		Insets insets = getInsets();
		int il = insets.left, it = insets.top;
		int imw = im.getWidth(this), imh = im.getHeight(this);

		// top row
		g.drawImage(im, il, it, this);
		g.drawImage(im, il+imw, it, Color.blue, this);

		// second row
		g.drawImage(im, il, it+imh, imw/2, imh/2, this);

		g.drawImage(im, il+imw/2, it+imh, imw/2, imh/2, 
					Color.yellow, this);

		g.drawImage(im, il+imw, it+imh, 	
					il+imw+imw/2, it+imh+imh/2, 
					100, 100, imw, imh, this);

		g.drawImage(im, il+imw+imw/2, it+imh, 
						il+2*imw+imw/2, it+2*imh+imh/2,
						100, 100, imw, imh, Color.cyan, this);

		// third row
		g.drawImage(im, il, it+imh+imh/2, 
						il+imw, it+imh+imh/2+imh, 
						imw, imh, 100, 100, this);
	}
	public static void main(String args[]) {
		final Frame f = new Test();
		f.setBounds(100,100,800,850);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
				System.exit(0);
			}
		});
	}
}
