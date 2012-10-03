import java.awt.*;
import java.awt.event.*;

public class ColoredCanvas extends Canvas {
	Color color;

	public ColoredCanvas(Color color) {
		this.color = color;

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				requestFocus();
			}
		});
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				repaint();
			}
			public void focusLost(FocusEvent event) {
				repaint();
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				System.out.println("Key Pressed");
			}
		});
	}
	public boolean isFocusTraversable() {
		return true;
	}	
	public void paint(Graphics g) {
		Dimension size = getSize();
		g.setColor  (color);
		g.fill3DRect(2,2,size.width-4,size.height-4,true);

		if(getFrame().getFocusOwner() == this) {
			g.setColor(Color.black);
			g.drawRect(0,0,size.width-1,size.height-1);
		}
	}
	public Dimension getPreferredSize() {
		return new Dimension(100,100);
	}
    public Frame getFrame() {
		Component c = this;

        while((c = c.getParent()) != null) {
            if(c instanceof Frame)
                return (Frame)c;
        }
        return null;
    }
}
