import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class ClipboardTest3 extends    Applet 
                           implements ClipboardOwner {
	private Clipboard   clipboard;
	private ImageCanvas copyFrom = new ImageCanvas();
	private ImageCanvas copyTo   = new ImageCanvas();
	private Button copy  = new Button("Copy");
	private Button paste = new Button("Paste");
	private int    width, height;

	public void init() {
		clipboard = new Clipboard("image clipboard");

		copyFrom.setImage(getImage(getCodeBase(),"gjSmall.gif"));
		add(copyFrom);
		add(copyTo);
		add(copy);
		add(paste);

		copy.addActionListener (new CopyListener());
		paste.addActionListener(new PasteListener());
	}
	class CopyListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Image           image = copyFrom.getImage();
			ImageSelection2 contents;
			
			width  = image.getWidth (ClipboardTest3.this);
			height = image.getHeight(ClipboardTest3.this);

			contents = new ImageSelection2(image,width,height); 
			clipboard.setContents(contents, ClipboardTest3.this);
		}
	}
	class PasteListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Transferable contents = clipboard.getContents(this);

			if(contents != null) {
				try {
					int[] array = (int[]) 
							contents.getTransferData(
							 ImageSelection2.ImageArrayFlavor);

					copyTo.setImage(
						waveThis(array,width,height));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	protected Image waveThis(int[] pixels, int w, int h) {
		int   newHeight, newSize, x, y, index, newIndex;
		int   newX,      newY;
		int   amplitude = 10, frequency = 10;
		int[] newPixels, sineArray;

		newHeight = h+(amplitude*2);
		newSize   = w * newHeight;
		newPixels = new int[newSize];
		sineArray = new int[w];

		for(x=0; x < w; ++x) {
			sineArray[x] = 
				((int)(amplitude*Math.sin(((double)(x)) /
		        ((double)frequency))));
		}
		for(y=0; y<h; y++) {
			for(x=0; x<w; x++) {
				index    = (y*w) + x;
				newY     = y + amplitude + sineArray[x];
				newIndex = (newY*w) + x;

				if(newIndex > 0 && newIndex < newSize) {
					newPixels[newIndex] = pixels[index];
				}
			}
		}
		MemoryImageSource mis = 
			new MemoryImageSource(w, newHeight, newPixels,
			                      0, w);
		return createImage(mis);
	}
	public void lostOwnership(Clipboard    clip, 
	                          Transferable transferable) {
		System.out.println("Lost ownership");
	}
}
class ImageCanvas extends Panel {
    private Image image;

	public ImageCanvas() {
		this(null);
	}
    public ImageCanvas(Image image) {
		if(image != null)
			setImage(image);
    }
    public void paint(Graphics g) {
		g.setColor(Color.lightGray);
		g.draw3DRect(0,0,getSize().width-1,
		               getSize().height-1,true);

		if(image != null) {
        	g.drawImage(image, 1, 1, this);
		}
    }
    public void update(Graphics g) {
        paint(g);
    }
	public void setImage(Image image) {
		this.image = image;
		try {
			MediaTracker tracker = new MediaTracker(this);
			tracker.addImage(image, 0);
        	tracker.waitForID(0);
		}
		catch(Exception e) { e.printStackTrace(); }

		if(isShowing()) {
			repaint();
		}
	}
	public Image getImage() {
		return image;
	}
	public Dimension getPreferredSize() {
		return new Dimension(275,100);
	}
}
