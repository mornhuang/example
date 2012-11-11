import java.applet.Applet;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;

public class ScrollImages extends Applet {
	private ScrollPane  scroller = new ScrollPane();
	private Image[]     images   = new Image[4];

	public void init() {
		loadImages(getCodeBase());

		scroller = new ScrollPane();
		scroller.add(new ImageCanvas(images[0]));

		setLayout(new BorderLayout());
		add(new ControlPanel(scroller, images), "North");
		add(scroller, "Center");
	}
	private void loadImages(URL base) {
		images[0] = getImage(base, "gifs/ashleyAndRoy.gif");
		images[1] = getImage(base, "gifs/ashleyAndSabre.gif");
		images[2] = getImage(base, "gifs/anjinAndMariko.gif");
		images[3] = getImage(base, "gifs/ashleyAndAnjin.gif");
	}
}
class ControlPanel extends Panel {
	ScrollPane   scroller;
	ScrollDialog dialog;
	Button       advanceButton = new Button("Advance Image");
	Button       scrollButton  = new Button("Scroll To ...");
	Image[]      images;
	int          curImage = 0;

	public ControlPanel(ScrollPane scrollpane, Image[] pics) {
		scroller = scrollpane;
		images   = pics;

		advanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Container container = scroller.getParent();

				container.setCursor(Cursor.getPredefinedCursor(
					                Cursor.WAIT_CURSOR));

				curImage = (curImage == images.length - 1) ? 
				            0 : curImage + 1;

				scroller.add(new ImageCanvas(images[curImage]));
				container.validate();

				container.setCursor(Cursor.getPredefinedCursor(
				                    Cursor.DEFAULT_CURSOR));
			}
		});
		scrollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Point loc =
						ControlPanel.this.getLocationOnScreen();

				if(dialog ==null) {
					dialog = new ScrollDialog(scroller);
				}
				dialog.setLocation(loc.x, loc.y);
				dialog.setVisible(true);
			}
		});
		add(advanceButton);
		add(scrollButton);
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
