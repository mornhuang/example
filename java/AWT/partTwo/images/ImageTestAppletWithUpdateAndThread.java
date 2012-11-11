import java.net.URL;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class ImageTestAppletWithUpdateAndThread extends Applet {
 	private Image im;

	public void init() {
		URL codebase = getCodeBase();
		System.out.println(codebase);
		im = getImage(codebase, "saint.gif");
		showImageSize();

		SomeOtherThread sot = new SomeOtherThread();
		sot.start();
	}
	public void paint(Graphics g) {
	  g.drawImage(im,0,0,this);
	}
	public boolean imageUpdate(Image image, int flags, 
							   int x, int y, int w, int h)
	{
 		System.out.println("imageUpdate():  x=" + x +  
		                   ",y=" + y + "  w=" + w + ",h=" + h);
		if((flags & ALLBITS) != 0)
			repaint();

	  	return true;
	}
	private void showImageSize() {
		System.out.print  ("Image width=" + im.getWidth(this));
		System.out.println(" height=" + im.getHeight(this));
	}
}

class SomeOtherThread extends Thread {
	public void run() {
		while(true) {
			System.out.println(
				"s o m e    o t h e r    t h r e a d");
			try   { Thread.currentThread().sleep(500); }
			catch (InterruptedException e) { }
		}
	}
}
