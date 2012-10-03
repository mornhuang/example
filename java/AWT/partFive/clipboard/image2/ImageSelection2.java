import java.awt.*;
import java.awt.image.*;
import java.awt.datatransfer.*;
import java.io.*;

public class ImageSelection2 implements Transferable,
                                       ClipboardOwner {
	static public DataFlavor ImageFlavor;
	static public DataFlavor ImageArrayFlavor;

	private DataFlavor[] flavors = {ImageFlavor,
	                                ImageArrayFlavor};
	private Image image;
	private int   width, height;

	static {
		try {
			ImageFlavor = new DataFlavor(
				Class.forName("java.awt.Image"),
				"AWT Image");

			ImageArrayFlavor = new DataFlavor("image/gif",
				                              "GIF Image");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(java.util.mime.MimeTypeParseException e) {
			e.printStackTrace();
		}
	}
	public ImageSelection2(Image image, int width, int height) {
		this.image  = image;
		this.width  = width;
		this.height = height;
	}
	public synchronized DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(ImageFlavor) ||
		       flavor.equals(ImageArrayFlavor);
	}
	public synchronized Object getTransferData(
											DataFlavor flavor) 
		throws UnsupportedFlavorException, IOException {
		if(flavor.equals(ImageFlavor)) {
			return image;
		}
		else if(flavor.equals(ImageArrayFlavor)) {
			return imageToArray();
		}
		else
			throw new UnsupportedFlavorException(flavor);
	}
	public void lostOwnership(Clipboard c, Transferable t) {
	}
	private int[] imageToArray() {
		int[] pixels = new int[width*height];
		PixelGrabber pg = new PixelGrabber(image,0,0,
								width,height,pixels,0,width);

		try { pg.grabPixels(); }
		catch(InterruptedException e) { e.printStackTrace(); }

		return pixels;
	}
}
