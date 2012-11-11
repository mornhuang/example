import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class BubbleTest extends Applet {
	public void init() {
		BubblePanel left   = new BubblePanel("left");
		BubblePanel center = new BubblePanel("center");
		BubblePanel right  = new BubblePanel("right");

		add(left);
		add(center);
		add(right);
	}
}
class BubblePanel extends BorderedPanel {
	Bubble bubble;
	String bubbleText;

	public BubblePanel(String string) {
		bubbleText = string;

		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				BubblePanel canvas  = BubblePanel.this;
				Point scrnLoc = canvas.getLocationOnScreen();
				Dimension size    = getSize();

				if(bubble == null)
					bubble = new Bubble(canvas, bubbleText);

				bubble.setLocation(scrnLoc.x,
								   scrnLoc.y + size.height + 2);
				bubble.show();
			}
			public void mouseExited(MouseEvent event) {
				if(bubble != null && bubble.isShowing())
					bubble.dispose();
			}
		});
	}
	public Dimension getPreferredSize() {
		return new Dimension(50,50);
	}
}
