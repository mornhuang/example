import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ThreeDButtonTest2 extends Applet {
    public void init() {
        add(new ThreeDButton());
    }
}
class ThreeDButton extends Canvas {
	static public  int BORDER_INSET = 0, BORDER_RAISED = 1;
	       private int state = BORDER_RAISED;

	public ThreeDButton() {
		ThreeDButtonListener listener = 
			new ThreeDButtonListener();
		addMouseListener      (listener);
		addMouseMotionListener(listener);
	}
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
        g.setColor(Color.lightGray);
        g.draw3DRect(0,0,
					 getSize().width-1,getSize().height-1, true);
		state = BORDER_RAISED;
		g.dispose();
    }
    public void paintBorderInset() {
        Graphics g = getGraphics();
        g.setColor(Color.lightGray);
        g.draw3DRect(0,0,
					 getSize().width-1,getSize().height-1, false);
		state = BORDER_INSET;
		g.dispose();
    }
}
class ThreeDButtonListener extends    MouseAdapter 
                           implements MouseMotionListener {
	public void mousePressed(MouseEvent event) {
		ThreeDButton button = (ThreeDButton)event.getSource();
		button.paintBorderInset();
	}
	public void mouseClicked(MouseEvent event) {
		ThreeDButton button = (ThreeDButton)event.getSource();
		button.paintBorderRaised();
	}
	public void mouseReleased(MouseEvent event) {
		ThreeDButton button = (ThreeDButton)event.getSource();
		button.paintBorderRaised();
	}
	public void mouseDragged(MouseEvent event) {
		ThreeDButton button = (ThreeDButton)event.getSource();

		if(button.contains(event.getX(), event.getY())) {
			if(button.getState() == ThreeDButton.BORDER_RAISED)
				button.paintBorderInset();
		}
		else {
			if(button.getState() == ThreeDButton.BORDER_INSET)
				button.paintBorderRaised();
		}
	}
	public void mouseMoved(MouseEvent event) {
	}
}
