import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class SimpleWindowTest extends Applet {
	Button launchButton = new Button("Show Window ...");
	Window window;
	Image  image;
	Frame  myFrame;

	public void init() {
		add(launchButton);

		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int   imageW  = image.getWidth(myFrame);
				Point scrnLoc = myFrame.getLocationOnScreen();

				showStatus(null);
				window.setLocation(scrnLoc.x - imageW - 10,
				                   scrnLoc.y);
				window.show();
				showStatus("Window shown");
			}
		});
	}
	public void start() {
		ImageCanvas canvas;

		myFrame = getFrame(SimpleWindowTest.this);
		window  = new Window(myFrame);
		image   = getImage(getCodeBase(), "gifs/saint.gif");
		canvas  = new ImageCanvas(image);

		window.add("Center", canvas);
		window.pack();
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
class ImageCanvas extends Canvas {
    private Image image;

    public ImageCanvas(Image image) {
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 0);

		try                { mt.waitForID(0);     } 
		catch(Exception e) { e.printStackTrace(); }

		this.image = image;
    }
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
    public void update(Graphics g) {
        paint(g);
    }
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(this),
		                     image.getHeight(this));
	}
}
