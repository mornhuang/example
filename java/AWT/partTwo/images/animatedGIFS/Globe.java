import java.awt.*;
import java.awt.event.*;

public class Globe extends Frame {
	Image globe;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public static void main(String args[]) {
		Frame f = new Globe();
		f.show();
	}
	public Globe() {
		super("globe");
		globe = tk.getImage("globe.gif");

		try {
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(globe,0);
			mt.waitForID(0);
		}
		catch(Exception e) { e.printStackTrace(); }

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
	}
	public void addNotify() {
		super.addNotify(); // this instantiates the peer

		Insets 	  insets = getInsets();
		Dimension scrnsz = tk.getScreenSize();
		Dimension globesz = new Dimension(globe.getWidth(this), 
											globe.getHeight(this));

		setBounds((scrnsz.width/2) - (globesz.width/2),
				  (scrnsz.height/2) - (globesz.height/2),
				  globesz.width + insets.left + insets.right, 
				  globesz.height + insets.top + insets.bottom);
	}
	public void paint(Graphics g) {
		Insets insets = getInsets();
	  	g.drawImage(globe,insets.left,insets.top,this);
	}
	public void update(Graphics g) {
		paint(g);
	}
}
