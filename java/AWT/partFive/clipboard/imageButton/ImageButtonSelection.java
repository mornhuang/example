import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;

public class ImageButtonSelection implements Transferable,
                                       ClipboardOwner {
	public static DataFlavor ImageButtonFlavor;
	private DataFlavor[] flavors = {ImageButtonFlavor}; 
	private ImageButton imageButton;

	static {
		try {
			ImageButtonFlavor = new DataFlavor(
									Class.forName("ImageButton"), 
									"ImageButton");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(java.util.mime.MimeTypeParseException e) {
			e.printStackTrace();
		}
	}
	public ImageButtonSelection(ImageButton imageButton) {
		this.imageButton = imageButton;
	}
	public synchronized DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(ImageButtonFlavor);
	}
	public synchronized Object getTransferData(
											DataFlavor flavor) 
		throws UnsupportedFlavorException, IOException {
		if(flavor.equals(ImageButtonFlavor)) {
			return imageButton;
		}
		else
			throw new UnsupportedFlavorException(flavor);
	}
	public void lostOwnership(Clipboard c, Transferable t) {
	}
}
