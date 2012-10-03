import java.awt.*;
import java.awt.event.*;

public class DoubleBufferedContainer extends Container {
	private   Image wallPaperImage;
	protected BackingStore workplace, background;

	public DoubleBufferedContainer() {
		this(null);
	}
	public DoubleBufferedContainer(Image wallPaperImage) {
		if(wallPaperImage != null)
			setWallpaperImage(wallPaperImage);

		setLayout(new FlowLayout());

		workplace 	= new BackingStore(this);
		background 	= new BackingStore(this);
	}
	public void setWallpaperImage(Image wallPaperImage) {
		this.wallPaperImage = wallPaperImage;
		Util.waitForImage(this, wallPaperImage);
	}
	public void update(Graphics g) { 
		paint(g); 
	}
	public void paint(Graphics g) {
		if(windowDamaged(g)) {
        	blitWorkplaceToScreen(g.getClipBounds());
		}
		else {
			Graphics  wpg  = getWorkplaceGraphics();

			if(wpg != null) {
				try {
        			Dimension size = getSize();
					paintBackground();
					wpg.setClip(0,0,size.width,size.height);
					super.paint(wpg);
					blitWorkplaceToScreen();
				}
				finally {
					wpg.dispose();
				}
			}
		}
	}
	public void paintComponents(Rectangle clip, boolean update) {
		Graphics wpg = getWorkplaceGraphics();

		if(wpg != null) {	
			try {
				wpg.setClip(clip);
				super.paint(wpg);
		
				if(update)
					blitWorkplaceToScreen(clip);
			}
			finally {
				wpg.dispose();
			}
		}
	}
	public void paintComponent(Component comp) {
		paintComponent(comp, true);
	}
	public void eraseComponent(Component comp) {
		eraseComponent(comp, true);
	}
	public void moveComponent(Component comp, Point location) {
		moveComponent(comp, location, true);
	}
	public void paintComponent(Component comp, boolean update) {
		Graphics  wpg    = getWorkplaceGraphics(); 
		Rectangle bounds = comp.getBounds();
		Graphics  compGraphics;

		if(wpg != null) {
			try {
				compGraphics = wpg.create(bounds.x,     bounds.y, 
		                          bounds.width, bounds.height);
				comp.paint(compGraphics);

				if(update)
					blitWorkplaceToScreen(bounds);
			}
			finally {
				wpg.dispose();
			}
		}
	}
	public void eraseComponent(Component comp, boolean update) {
		Rectangle bounds = comp.getBounds();

		blitBackgroundToWorkplace(bounds);
		paintOverlappingComponents(comp);

		if(update)
			blitWorkplaceToScreen(bounds);
	}
	public void moveComponent(Component comp, Point newLoc, 
								boolean update) {
		Rectangle oldBounds = comp.getBounds();

		eraseComponent(comp, false);  // erase - no screen update
		comp.setLocation(newLoc);     // move component
		paintComponent(comp, false);  // paint comp - no update

		if(update)
			blitWorkplaceToScreen(
							oldBounds.union(comp.getBounds()));
	}
	public Graphics getWorkplaceGraphics() {
		return workplace.getGraphics();
	}
	public Graphics getBackgroundGraphics() {
		return background.getGraphics();
	}
	public void blitBackgroundToWorkplace() {
		blitBackgroundToWorkplace(null);
	}
	public void blitWorkplaceToScreen() {
		blitWorkplaceToScreen(null);
	}
	public void blitBackgroundToScreen() {
		blitBackgroundToScreen(null);
	}
	public void blitBackgroundToWorkplace(Rectangle r) {
		background.blitTo(workplace.getImage(), r);
	}
	public void blitWorkplaceToScreen(Rectangle r) {
		Graphics g = getGraphics();
		if(g != null) {
			try {
				workplace.blitTo(g, r);
			}
			finally {
				g.dispose();
			}
		}
	}
	public void blitBackgroundToScreen(Rectangle r) {
		Graphics g = getGraphics();
		if(g != null) {
			try {
				background.blitTo(g, r);
			}
			finally {
				g.dispose();
			}
		}
	}
	public Image getWorkplaceBuffer () { 
		return workplace.getImage();
	}
	public Image getBackgroundBuffer() { 
		return background.getImage();
	}
	protected boolean windowDamaged(Graphics g) {
		Rectangle clip = g.getClipBounds();
		Dimension size = getSize();

		return ((clip.x != 0 || clip.y != 0) ||
		        (clip.width  < size.width    ||
				 clip.height < size.height));
	}
	protected void paintOverlappingComponents(Component comp) {
		Graphics  wpg    = getWorkplaceGraphics();
		Rectangle bounds = comp.getBounds();

		if(wpg != null) {
			try {
				wpg.setClip    (bounds); 
				comp.setVisible(false);  
				super.paint    (wpg);    
				comp.setVisible(true);   
			}
			finally {
				wpg.dispose();
			}
		}
	}
    protected void paintBackground() {
		paintBackground((Rectangle)null);
	}
	protected void paintBackground(Rectangle clip) {
		Graphics g = getBackgroundGraphics();
		if(g != null) {
			try {
				if(clip != null) 
					g.setClip(clip);

				paintBackground(g);
				blitBackgroundToWorkplace();
			}
			finally {
				g.dispose();
			}
		}
    }
	protected void paintBackground(Graphics g) {           
		if(wallPaperImage != null) {
			Util.wallPaper(this, g, wallPaperImage);
		}
	}
}
