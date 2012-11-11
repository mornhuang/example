import java.awt.*;

public class RubberbandLine extends Rubberband {
	public RubberbandLine() {
	}
	public RubberbandLine(Component component) {
		super(component);
	}
	public void drawLast(Graphics graphics) {
		graphics.drawLine(anchorPt.x, anchorPt.y, 
							lastPt.x,   lastPt.y);
	}
	public void drawNext(Graphics graphics) {
		graphics.drawLine(anchorPt.x,    anchorPt.y, 
							stretchedPt.x, stretchedPt.y);
	}
}
