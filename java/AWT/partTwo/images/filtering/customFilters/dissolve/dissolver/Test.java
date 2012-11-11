import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Test extends Frame implements Runnable {
	Image 		tiger;
	boolean 	showing = false;
	Dissolver 	dissolver = null;
	int			x,y;

	public Test() {
		super("Image Dissolver");

		MediaTracker mt  = new MediaTracker(this);
		URL 		 url = getClass().getResource("tiger.gif");

		try {
			tiger = createImage((ImageProducer)url.getContent());
			mt.addImage(tiger, 0);
			mt.waitForID(0);
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	public void addNotify() {
		super.addNotify();

		Insets i = getInsets();
		dissolver = new Dissolver(this, x=i.left, y=i.top);

		synchronized(dissolver) {
			ImageProducer ip = tiger.getSource();
			ip.startProduction(dissolver);
			try { 
				dissolver.wait(); // wait for dissolved images ...
			} 
			catch(Exception e) { // wait() was interrupted
				e.printStackTrace();
			}
		}
		try {
			Thread thread = new Thread(this);
			thread.start();
		}
		catch(Exception e) { // thread was interrupted
			e.printStackTrace(); 
		}
	}
	public void run() {
		while(true) {
			if(isShowing()) {
				if(showing) dissolver.fadeOut();
				else        dissolver.fadeIn();
				showing = showing ? false : true;

				try {
					Thread.currentThread().sleep(1000);
				}
				catch(Exception e) { e.printStackTrace(); }
			}
		}
	}
	public static void main(String args[]) {
		final Test f = new Test();
		f.setBounds(100,100,375,375);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
				System.exit(0);
			}
		});
	}
}
