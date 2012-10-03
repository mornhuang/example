import java.awt.*;

public class RubberbandRectangle extends Rubberband {
	public RubberbandRectangle() {
	}
    public RubberbandRectangle(Component component) {
        super(component);
    }
    public void drawLast(Graphics graphics) {
        Rectangle rect = lastBounds();
        graphics.drawRect(rect.x, rect.y, 
                          rect.width, rect.height);
    }
    public void drawNext(Graphics graphics) {
        Rectangle rect = getBounds();
        graphics.drawRect(rect.x, rect.y, 
                          rect.width, rect.height);
    }
}
