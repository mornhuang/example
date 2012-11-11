import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;

public class MemorySourceAnimationTest extends Applet {
 	Image im, animatedImage, offscreen;
	MemoryImageSource mis;
	PixelGrabber pg;
	int imw, imh, allPixels[], pixels[];

	public void init() {
		MediaTracker mt  = new MediaTracker(this);
		URL 		 url = getClass().getResource("tiger.gif");

		try {
			im = createImage((ImageProducer)url.getContent());
			mt.addImage(im, 0);
			mt.waitForID(0);
		}
		catch(Exception e) { e.printStackTrace(); }	

		imw = im.getWidth(this);
		imh = im.getHeight(this);

		allPixels 	= new int[imw*imh];
		pixels 		= new int[imw*imh];

		offscreen = createImage(imw, imh);

		mis = new MemoryImageSource(imw,imh,pixels,0,imw);
		mis.setAnimated(true);
		animatedImage = createImage(mis);

		try {
			pg = new PixelGrabber(im, 0, 0, imw, imh, 
									allPixels, 0, imw);
			pg.grabPixels();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		Animator animator = new Animator(25);
		animator.start();
	}
	public void update(Graphics g) {
		paint(g);
	}
	public void paint(Graphics g) {
		Graphics 	offg = null;
		Dimension 	size = getSize();
		try { 
			offg = offscreen.getGraphics();
			offg.clearRect(0,0,size.width,size.height);
			offg.drawImage(animatedImage,0,0,this);
			g.drawImage(offscreen,0,0,this);
		}
		finally {
			offg.dispose();
		}
	}
	class Animator extends Thread {
		private int numBoxes, curx, cury, curw, curh;
		private boolean showing = false;
		private Graphics g;
		private int[] emptyBuffer = new int[imw*imh];

		public Animator(int numBoxes) {
			this.numBoxes = numBoxes;
		}
		public void run() {
			while(true) {
				if(isShowing()) {
					if(showing) boxIn();
					else		boxOut();

					showing = showing ? false : true;
					pause(1000);
				}
			}
		}
		private void boxIn() {
			curw = imw;
			curh = imh;

			while(curw > 0 && curh > 0) {
				curx = imw/2 - curw/2;
				cury = imh/2 - curh/2;

				System.arraycopy(emptyBuffer,0,pixels,0,imw*imh);

				for(int i=0; i < curh; ++i) {
					System.arraycopy(allPixels, 
									(cury+i)*imw + curx, pixels, 
									(cury+i)*imw + curx, curw);
				}
				mis.newPixels();

				curw -= imw/numBoxes;
				curh -= imh/numBoxes;

				pause(50);
			}
			System.arraycopy(emptyBuffer,0,pixels,0,imw*imh);
			mis.newPixels();
		}
		private void boxOut() {
			mis.setFullBufferUpdates(false);

			curw = imw/numBoxes;
			curh = imh/numBoxes;

			while(curw < imw && curh < imh) {
				curx = imw/2 - curw/2;
				cury = imh/2 - curh/2;

				for(int i=0; i < curh; ++i) {
					System.arraycopy(allPixels, 
									(cury+i)*imw + curx, pixels, 
									(cury+i)*imw + curx, curw);
				}
				mis.newPixels(curx,cury,curw,curh);

				curw += imw/numBoxes;
				curh += imh/numBoxes;

				pause(50);
			}
			System.arraycopy(allPixels, 0, pixels, 0, imw*imh);
			mis.newPixels();
		}
		private void pause(int milliseconds) {
			try {
				Thread.currentThread().sleep(milliseconds);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
