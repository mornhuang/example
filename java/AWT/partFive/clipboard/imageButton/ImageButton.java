import java.awt.*;

class ImageButton extends Panel implements java.io.Serializable {
    private Image image;

	public ImageButton() {
		this(null);
	}
    public ImageButton(Image image) {
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
		if(image != null) {
			return new Dimension(image.getWidth(this) + 2,
			                     image.getHeight(this) + 2);
		}
		return new Dimension(0,0);
	}
}
