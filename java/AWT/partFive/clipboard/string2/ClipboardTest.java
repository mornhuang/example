import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.io.*;

public class ClipboardTest extends    Applet 
                           implements ClipboardOwner {
	private Clipboard clipboard;
	private TextField copyFrom;
	private TextArea  copyTo;
	private Button    copy, paste;

	public void init() {
		// Obtain a reference to the system clipboard
		clipboard = getToolkit().getSystemClipboard();

		copyFrom  = new TextField(20);
		copyTo    = new TextArea(3, 20);
		copy      = new Button("Copy To System Clipboard");
		paste     = new Button("Paste From System Clipboard");

		add(copyFrom);
		add(copy);
		add(paste);
		add(copyTo);

		copy.addActionListener (new CopyListener());
		paste.addActionListener(new PasteListener());
	}
	class CopyListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Wrap the data in a transferable object
			StringSelection contents = 
						new StringSelection(copyFrom.getText());

			// Place the transferable onto the clipboard	
			clipboard.setContents(contents, ClipboardTest.this);
		}
	}
	class PasteListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Transferable contents = clipboard.getContents(this);
			StringBuffer sb = new StringBuffer();

			// Determine if data is available in plain text flavor 
			if(contents != null && 
			   contents.isDataFlavorSupported(
			   				DataFlavor.plainTextFlavor)) {
				try {
					int i;
					StringReader s = (StringReader)
						contents.getTransferData(
								DataFlavor.plainTextFlavor);

					while( (i = s.read()) != -1) {
						sb.append((char)i);
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				copyTo.setText(sb.toString());
			}
		}
	}
	public void lostOwnership(Clipboard    clip, 
	                          Transferable transferable) {
		System.out.println("Lost ownership");
	}
}
