import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class ClipboardTest4 extends    Applet 
                           implements ClipboardOwner {
	private Clipboard   clipboard;
	private ImageButtonCanvas copyFrom = new ImageButtonCanvas();
	private ImageButtonCanvas copyTo   = new ImageButtonCanvas();
	private Button copy  = new Button("Copy");
	private Button paste = new Button("Paste");

	public void init() {
		clipboard = getToolkit().getSystemClipboard();

		copyFrom.setImageButton(
		  new ImageButton(getImage(getCodeBase(),
		                  "ladybug.gif")));

		add(copyFrom);
		add(copyTo);
		add(copy);
		add(paste);

		copy.addActionListener (new CopyListener());
		paste.addActionListener(new PasteListener());
	}
	class CopyListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			ImageButton button = copyFrom.getImageButton();
			ImageButtonSelection contents = 
						new ImageButtonSelection(button);
			clipboard.setContents(contents, ClipboardTest4.this);
		}
	}
	class PasteListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Transferable contents = clipboard.getContents(this);

			if(contents != null) {
				try {
					ImageButton imageButton =
						(ImageButton) contents.getTransferData(
				         ImageButtonSelection.ImageButtonFlavor);
					copyTo.setImageButton(imageButton);
					copyTo.invalidate();
					validate();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void lostOwnership(Clipboard    clip, 
	                          Transferable transferable) {
		System.out.println("Lost ownership");
	}
}
class ImageButtonCanvas extends Panel {
    private ImageButton imageButton;

	public ImageButtonCanvas() {
		this(null);
	}
    public ImageButtonCanvas(ImageButton imageButton) {
		if(imageButton != null)
			setImageButton(imageButton);
    }
    public void paint(Graphics g) {
		g.setColor  (Color.black);
		g.drawRect  (0,0,getSize().width-1,getSize().height-1);

		g.setColor  (Color.lightGray);
		g.draw3DRect(1,1,getSize().width-3,
		               getSize().height-3,true);
		
    }
	public void setImageButton(ImageButton button) {
		imageButton = button;
		add(new ImageButton(imageButton.getImage()));

		if(isShowing()) {
			repaint();
		}
	}
	public ImageButton getImageButton() {
		return imageButton;
	}
}
