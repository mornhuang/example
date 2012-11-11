import java.awt.*;
import java.awt.event.*;

public class BackingStore extends ComponentAdapter {
	Image offscreen;
	Component component;
	Dimension size;

	public BackingStore(Component component) {
		setComponent(component);
	}
	public void setComponent(Component c) {
		if(component != null) {
			component.removeComponentListener(this);
		}
		component = c;
		component.addComponentListener(this);
	}
	public Component getComponent() {
		return component;
	}
	public Image getImage() { 
		if(offscreen == null)
			createBuffers();

		return offscreen;
	}
	public Graphics getGraphics() { 
		if(offscreen == null)
			createBuffers();

		return offscreen.getGraphics(); 
	}
	public void componentResized(ComponentEvent event) {
		if(needNewOffscreenBuffer())
			createBuffers();
	}
	public void blitTo(Image im) {
		blitTo(im, null);
	}
	public void blitTo(Graphics graphics) {
		blitTo(graphics, null);
	}
	public void blitTo(Image im, Rectangle clip) {
		Graphics g = im.getGraphics();
		if(g != null) {
			try {
				if(clip != null)
					g.setClip(clip);

				g.drawImage(offscreen, 0, 0, component);
			}
			finally {
				g.dispose();
			}
		}
	}
	public void blitTo(Graphics g, Rectangle clip) {
		if(g != null) {
			if(clip != null)
				g.setClip(clip);

			g.drawImage(offscreen, 0, 0, component);
		}
	}
 	private boolean needNewOffscreenBuffer() {
		Dimension newSize = component.getSize();

   		return (offscreen == null ||
   				newSize.width  > size.width ||
   				newSize.height > size.height);
   	}
   	private void createBuffers() {
		size		= component.getSize();
   		offscreen 	= component.createImage(size.width, 
											size.height);
   	}
}
