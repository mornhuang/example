import java.awt.*;

public class ColorPatch extends Canvas {
	private Color color = Color.blue;

	public void paint(Graphics g) {
		Dimension size = getSize();
		g.setColor(Color.black);
		g.drawRect(0,0,size.width-1,size.height-1);
		g.setColor(color);
		g.fillRect(1,1,size.width-2,size.height-2);
	}
	public synchronized void setColor(Color c) {
		Color old = color; 
		this.color = c;
		firePropertyChange("color", old, color);
	}
	public synchronized Color getColor() {
		return color;
	}
	public Dimension getMinimumSize() {
		return new Dimension(100,25);
	}
}
