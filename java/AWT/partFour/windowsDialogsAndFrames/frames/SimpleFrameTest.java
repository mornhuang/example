import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class SimpleFrameTest extends Applet {
	Button launchButton = new Button("Show Frame ...");
	Frame  frame        = new Frame ("Simple Frame");

	public void init() {
		add(launchButton);

		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Frame myFrame = getFrame(SimpleFrameTest.this);
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

		frame.add (doneButton);
		frame.pack();

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				showStatus(null);
			}
		});
		frame.addWindowListener(new WindowAdapter() {		
			public void windowClosing(WindowEvent event) {
				System.out.println("Window Closing");
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
