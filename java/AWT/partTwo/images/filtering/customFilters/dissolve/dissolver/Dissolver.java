import java.awt.*;
import java.awt.image.*;

class Dissolver implements ImageConsumer {
	Image 		images[];
	Image 		image;
	ColorModel 	defaultRGB = ColorModel.getRGBdefault(); 
	Component 	component;
	Graphics	g;
	int 		intPixels[], x, y, width, height, pauseInterval;
	int			numImages;

	public Dissolver(Component c, int x, int y) {
		this(c,x,y,10,50);
	}
	public Dissolver(Component c, int x, int y, 
						int numImages, int pauseInterval) {
		this.component = c;
		this.numImages = numImages;
		this.pauseInterval = pauseInterval;
		this.x = x;
		this.y = y;

		images = new Image[numImages];
	}
	public void setColorModel(ColorModel model) {
		// don't care - we use our own in imageComplete(),
		// and the one passed into setPixels()
	}
	public void setHints(int hints) {
		// don't care
	}
	public void setProperties(java.util.Hashtable props) {
		// don't care
	}
	public void setDimensions(int width, int height) {
		this.width 	= width;
		this.height = height;
		intPixels = new int[width * height];
	}
	public void setPixels(int x, int y, int w, int h,
							ColorModel model, int[] pixels,
							int offset, int scansize) {
		for(int r=0; r < h; ++r) {
			for(int c=0; c < w; ++c) {
				int index = offset + r*scansize + c;
				intPixels[index] = 
					model.getRGB(pixels[index] & 0xff);
			}
		}
	}
	public void setPixels(int x, int y, int w, int h,
							ColorModel model, byte[] pixels,
							int offset, int scansize) {
		for(int r=0; r < h; ++r) {
			for(int c=0; c < w; ++c) {
				int index = offset + r*scansize + c;
				intPixels[index] = 
					model.getRGB(pixels[index] & 0xff);
			}
		}
	}
	public synchronized void imageComplete(int status) {
		if(status == IMAGEERROR || status == IMAGEABORTED) {
			return;
		}
		MemoryImageSource mis = new MemoryImageSource(
									width, height, defaultRGB, 
									intPixels, 0, width);
		image = component.createImage(mis);

		makeDissolvedImages();  // this takes some time, so ...
		notifyAll();			// notify all threads waiting on us
	}
	private void makeDissolvedImages() {
		MediaTracker mt = new MediaTracker(component);
		DissolveFilter filter;

		for(int i=0; i < images.length; ++i) {
			filter = new DissolveFilter((255/(numImages-1))*i);	

			FilteredImageSource fis = new FilteredImageSource(
											image.getSource(),
											filter);
			images[i] = component.createImage(fis);
			mt.addImage(images[i], i);
		}
		mt.addImage(image, numImages);
		try { mt.waitForAll(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	public void fadeOut() {
        g = component.getGraphics();

        if(g != null) {
			try {
            	for(int i=numImages-1; i >= 0; --i) {
					g.clearRect(x, y, width, height);
					g.drawImage(images[i], x, y, component);
                	pause();
            	}
			}
			finally {
				g.dispose();
			}
        }
    }
	public void fadeIn() {
        g = component.getGraphics();

        if(g != null) {
			try {
            	for(int i=0; i < numImages; ++i) {
					g.clearRect(x, y, width, height);
					g.drawImage(images[i], x, y, component);
                	pause();
            	}
				g.drawImage(image, x, y, component);
			}
			finally {
				g.dispose();
			}
        }
    }
	private void pause() {
        try { Thread.currentThread().sleep(pauseInterval); }
        catch(InterruptedException e) { }       
    }
}
