import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class HighlightButtonTest2 extends Applet {
    public void init() {
        HighlightButton leftButton  = new HighlightButton();
        HighlightButton rightButton = new HighlightButton();

        add(leftButton);
        add(rightButton);
    }
}

class HighlightButton2 extends Canvas {
    public HighlightButton2() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }
    public void paint(Graphics g) {
        paintBorder();
    }
    public Dimension getPreferredSize() {
        return new Dimension(100,100);
    }
    public void paintBorder() {
        Graphics g = getGraphics();
        g.setColor(Color.gray);
        g.drawRect(0,0,getSize().width-1,getSize().height-1);
    }
    public void highlight() {
		Graphics g = getGraphics();
		g.setColor(Color.lightGray);
		g.draw3DRect(0,0,getSize().width,getSize().height, true);
    }
    public void unhighlight() {
        paintBorder();
    }
    public void processEvent(AWTEvent event) {
        if(event.getID() == MouseEvent.MOUSE_ENTERED) {
            HighlightButton canvas = 
					(HighlightButton)event.getSource();
            canvas.highlight();
        }
        else if(event.getID() == MouseEvent.MOUSE_EXITED) {
            HighlightButton canvas = 
					(HighlightButton)event.getSource();
            canvas.unhighlight();
        }
        super.processEvent(event);  // must do
    }
}
