import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class DotJavaFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		return name.endsWith(".java");
	}
}	
public class FileDialogTest2 extends Applet {
	Button     loadButton = new Button("Load File ...");
	Button     saveButton = new Button("Save File ...");
	Listener   listener   = new Listener();
	FileDialog dialog;
	String     filename;

	public void init() {
		add(loadButton);
		add(saveButton);
		loadButton.addActionListener(listener);
		saveButton.addActionListener(listener);
	}
    class Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Button button = ((Button)event.getSource());
			Frame myFrame = getFrame(button);

			showStatus(null);

			if(button == loadButton) 
				dialog = new FileDialog(myFrame, "Load A File");
			else 
				dialog = new FileDialog(myFrame, "Save A File",
												  FileDialog.SAVE);
			dialog.setFilenameFilter(new DotJavaFilter());
			dialog.setVisible(true);

			if((filename = dialog.getFile()) != null) {
				showStatus(filename);	
			}
			else {
				showStatus("FileDialog Cancelled");
			}
		}
	}
    static Frame getFrame(Component c) {
		Frame frame = null;

        while((c = c.getParent()) != null) {
            if(c instanceof Frame)
                frame = (Frame)c;
        }
        return frame;
    }
}
