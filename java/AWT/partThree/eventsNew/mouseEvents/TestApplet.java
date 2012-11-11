import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TestApplet extends Applet {
	public void init() {
		addMouseListener(new TestMouseListener());
		addMouseMotionListener(new TestMouseMotionListener());
	}
}
class MouseReporter {
	public void report(MouseEvent e) {
		int 	clickCount 		= e.getClickCount();
		int 	mods 			= e.getModifiers();
		Point 	p 				= e.getPoint();
		boolean isPopupTrigger 	= e.isPopupTrigger();
		String 	s 				= "mouse ";

		if((mods & InputEvent.BUTTON3_MASK) != 0) 
			s += "button 3";
		else if((mods & InputEvent.BUTTON2_MASK) != 0) 
			s += "button 2";
		else if((mods & InputEvent.BUTTON1_MASK) != 0) 
			s += "button 1";
		else 
			s += "cursor";

		switch(e.getID()) {
			case MouseEvent.MOUSE_PRESSED:
				s += " pressed";
				break;
			case MouseEvent.MOUSE_RELEASED:
				s += " released";
				break;
			case MouseEvent.MOUSE_CLICKED:
				s += " clicked";
				break;
			case MouseEvent.MOUSE_MOVED:
				s += " moved";
				break;
			case MouseEvent.MOUSE_ENTERED:
				s += " entered";
				break;
			case MouseEvent.MOUSE_EXITED:
				s += " exited";
				break;
			case MouseEvent.MOUSE_DRAGGED:
				s += " dragged";
				break;
		}
		System.out.println(s + " at:  " + p);
		System.out.println(" click count:  " + clickCount);
		System.out.println(" is popup trigger:  " + 
							isPopupTrigger);
		System.out.println();
	}
}
class TestMouseListener implements MouseListener {
	private MouseReporter reporter = new MouseReporter();

	public void mouseClicked(MouseEvent e) {
		reporter.report(e);
	}
	public void mouseEntered(MouseEvent e) {
		reporter.report(e);
	}
	public void mouseExited(MouseEvent e) {
		reporter.report(e);
	}
	public void mousePressed(MouseEvent e) {
		reporter.report(e);
	}
	public void mouseReleased(MouseEvent e) {
		reporter.report(e);
	}
}
class TestMouseMotionListener implements MouseMotionListener {
	private MouseReporter reporter = new MouseReporter();

	public void mouseDragged(MouseEvent e) {
		reporter.report(e);
	}
	public void mouseMoved(MouseEvent e) {
		reporter.report(e);
	}
}
