import java.awt.*;
import java.awt.event.*;

public class Lightweight extends Component {
	private ImageIcon   icon;
	private Dragger 	dragger = new Dragger();
	private boolean		isMobile = false;

	public Lightweight() {
		this(null, true);
	}
	public Lightweight(Image image) {
		this(image, true);
	}
	public Lightweight(Image image, boolean isMobile) {
		if(image != null)
			icon = new ImageIcon(image);

		this.isMobile = isMobile;

		if(isMobile) {
			addMouseListener      (dragger);
			addMouseMotionListener(dragger);
		}
	}
	public void paint(Graphics g) {
		if(isVisible() && icon != null) 
			icon.paintIcon(this, g, 0, 0);
	}
	public Dimension getPreferredSize() {
		Dimension rv = null;
		if(icon != null)
			rv = new Dimension(icon.getIconWidth(), 
								icon.getIconHeight());
		else
			rv = super.getPreferredSize();
		return rv;
	}
	public boolean isBeingDragged() {
		return dragger.isDragging();
	}
	class Dragger extends MouseAdapter 
								implements MouseMotionListener {
		Point	press	 = new Point();
		boolean dragging = false;

		public void mousePressed(MouseEvent event) {
			press.x = event.getX();
			press.y = event.getY();
			dragging = true;
		}
		public boolean isDragging() {
			return dragging;
		}
		public void mouseReleased(MouseEvent event) {
			dragging = false;
		}
		public void mouseClicked(MouseEvent event) {
			dragging = false;
		}
		public void mouseMoved(MouseEvent event) {
			// don't care
		}
		public void mouseDragged(MouseEvent event) {
			if(dragging) {
				DoubleBufferedContainer c;
				Point loc = getLocation();
				Point pt  = new Point();

				pt.x = event.getX() + loc.x - press.x;
				pt.y = event.getY() + loc.y - press.y;

				c = (DoubleBufferedContainer)getParent();
				c.moveComponent(Lightweight.this, pt);
			}
		}
	}
}
class ImageIcon extends Object {
	Image image;
	Component component = new Component() { };

	public ImageIcon(Image image) {
		this.image = image;		
		Util.waitForImage(component, image);
	}
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.drawImage(image,x,y,component);
	}
	public int getIconWidth() {
		return image.getWidth(null);
	}
	public int getIconHeight() {
		return image.getHeight(null);
	}
}
