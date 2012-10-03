import java.awt.*;
import java.awt.event.*;

public class Bubble extends Window {
	private String text;

	public Bubble(Component comp, String text) {
		super(getFrame(comp)); 
		this.text = text;
		setForeground(SystemColor.textText);
	}
	public Dimension getPreferredSize() {
		Graphics    g  = getGraphics();
		FontMetrics fm = g.getFontMetrics();

		return new Dimension(fm.stringWidth(text)+4, 
		                     fm.getHeight()+4);
	}
	public void paint(Graphics g) {
		Dimension   size = getSize();
		FontMetrics fm   = g.getFontMetrics();

		g.drawRect(0,0,size.width-1,size.height-1);
		g.drawString(text,2,fm.getAscent()+2);
	}
	public void show() {
		pack();
		super.show();
	}
	static Frame getFrame(Component c) {
		Frame frame = null;

        while((c = c.getParent()) != null) 
            if(c instanceof Frame)
                frame = (Frame)c;

        return frame;
    }
}
