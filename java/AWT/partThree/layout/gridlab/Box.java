import java.awt.*;

class Box extends Panel {
	String title;

	public Box(Component c, String title) {
		this.title = title;		
		setLayout(new BorderLayout());
		add(c, "Center");
	}
	public Insets getInsets() {
		Graphics 	g 	= getGraphics();
		FontMetrics fm 	= g.getFontMetrics();

		g.dispose();
		return new Insets(fm.getHeight(),2,2,2);
	}
	public void paint(Graphics g) {
		Dimension 	sz 	= getSize();
		FontMetrics fm 	= g.getFontMetrics();
		int 		h 	= fm.getHeight();

		g.setColor(SystemColor.controlShadow);
		g.drawRect(0,h/2,sz.width-2,sz.height-2-h/2);
		g.setColor(SystemColor.controlLtHighlight);
		g.drawRect(1,h/2+1,sz.width-2,sz.height-2-h/2);

		g.setColor(getBackground());
		g.clearRect(sz.width/2 - fm.stringWidth(title)/2 - 2,
					0, fm.stringWidth(title)+4, fm.getHeight());

		g.setColor(getForeground());
		g.drawString(title,sz.width/2 - fm.stringWidth(title)/2,
					fm.getAscent());
	}
}
