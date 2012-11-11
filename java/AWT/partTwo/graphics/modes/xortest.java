import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class xortest extends Applet {
	Point	pressed, last;
	Image 	image;
	boolean firstRect;

	public void init() {
		image = getImage(getCodeBase(), "saint.gif");
		try {
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(image, 0);
			mt.waitForID(0);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				firstRect = true;
				pressed   = e.getPoint();				
			}
			public void mouseReleased(MouseEvent e) {
				if(pressed != null) {
					Point  		released = e.getPoint();
					Rectangle 	clip 	= new Rectangle();
					Graphics 	g 		= getGraphics();
					Dimension 	size 	= getSize();

					try {
						clip.x = pressed.x;
						clip.y = pressed.y;
						clip.width  = 
							Math.abs(released.x - pressed.x);
						clip.height = 
							Math.abs(released.y - pressed.y);

						g.clearRect(0,0,size.width,size.height);
						g.setClip(clip);
						g.drawImage(image, 0, 0, xortest.this);
					}
					finally {
						g.dispose();
					}
				}
			}
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point loc = e.getPoint();
				Graphics g = getGraphics();

				try {
					g.setXORMode(getBackground());

					if(firstRect) {
						firstRect = false;	
					}
					else {
						g.drawRect(pressed.x, pressed.y, 
								Math.abs(pressed.x - last.x), 
								Math.abs(pressed.y - last.y));
					}
					g.drawRect(pressed.x, pressed.y, 
							Math.abs(loc.x - pressed.x), 
							Math.abs(loc.y - pressed.y));
					last = e.getPoint();
				}
				finally {
					g.dispose();
				}
			}
		});
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
}
