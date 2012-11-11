import java.awt.*;

public class BorderedPanel extends Panel {
	public Insets getInsets() {
		return new Insets(3,3,3,3);
	}
	public void paint(Graphics g) {
		Dimension size = getSize();
		g.setColor(Color.black);
		g.drawRect(0,0,size.width-1,size.height-1);
		g.setColor(Color.lightGray);
		g.draw3DRect(1,1,size.width-3,size.height-3,true);
	}
}
