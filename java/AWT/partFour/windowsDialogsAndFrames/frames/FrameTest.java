import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class FrameTest extends Applet {
	Button launchButton = new Button("Show Frame ...");
	Frame  frame;

	public void init() {
		add(launchButton);

		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Frame myFrame = getFrame(FrameTest.this);
				Point scrnLoc = myFrame.getLocationOnScreen();
				Dimension frameSize = frame.getSize();

				frame.setLocation(scrnLoc.x - frameSize.width - 2,
		                          scrnLoc.y);
				showStatus(null);
				frame.show();
				showStatus("Frame shown");
			}
		});
	}
	public void start() {
	 	Button doneButton = new Button("Done");

		frame     = new Frame("Simple Frame");
		frame.add (doneButton);
		frame.pack();
		frame.setResizable(false);

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				showStatus(null);
			}
		});
		frame.addWindowListener(new WindowAdapter() {		
			public void windowClosing(WindowEvent event) {
				showStatus("Window Closing");
				frame.dispose();
			}
			public void windowClosed(WindowEvent event) {
				showStatus("Window Closed");
			}
		});
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
