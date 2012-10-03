import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ThreeDButtonTest extends Applet {
    public void init() {
        add(new ThreeDButton());
    }
}

class ThreeDButton extends Canvas {
	static public  int BORDER_INSET = 0, BORDER_RAISED = 1;
	       private int state = BORDER_RAISED;

    public void paint(Graphics g) {
        paintBorderRaised();
    }
    public Dimension getPreferredSize() {
        return new Dimension(100,100);
    }
	public int getState() {
		return state;
	}
    public void paintBorderRaised() {
        Graphics g = getGraphics();

		try {
        	g.setColor(Color.lightGray);
        	g.draw3DRect(0,0,
					 getSize().width-1,getSize().height-1, true);
			state = BORDER_RAISED;
		}
		finally {
			g.dispose();
		}
    }
    public void paintBorderInset() {
        Graphics g = getGraphics();

		try {
        	g.setColor(Color.lightGray);
        	g.draw3DRect(0,0,
					 getSize().width-1,getSize().height-1, false);
			state = BORDER_INSET;
		}
		finally {
			g.dispose();
		}
    }
}
