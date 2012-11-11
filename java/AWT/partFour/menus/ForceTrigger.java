import java.awt.*;
import java.awt.event.*;

public class ForceTrigger extends java.applet.Applet {
	public void init() {
		PopupMenu pm = new PopupMenu();
		AVMMenuBar c = new AVMMenuBar(pm);		
		//setLayout(new FlowLayout());
		add(c);

		pm.add("one");
		pm.add("two");
		pm.add("three");
		pm.add("four");

		c.add(pm);
	}
}
class AVMMenuBar extends Canvas {
	PopupMenu pm;

	public AVMMenuBar(PopupMenu menu) {
		pm = menu;

		// otherwise we may not get any mouse events

		enableEvents(AWTEvent.MOUSE_EVENT_MASK);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
					pm.show(AVMMenuBar.this,triggerEvent.getX(),
					triggerEvent.getY());	

				// event is the doctored event from processMouseEvent() ...
/*
		MouseEvent triggerEvent = new MouseEvent((Component)event.getSource(),
			event.getID(), event.getWhen(), event.getModifiers(), event.getX(),
			event.getY(), event.getClickCount(), true);

				if(triggerEvent.isPopupTrigger()) {
					pm.show(AVMMenuBar.this,triggerEvent.getX(),
					triggerEvent.getY());	
*/
				}
			}
		});
	}
	/*
	public void processMouseEvent(MouseEvent event) {

		// Otherwise we won't be notified of any mouse event


		// last arg to constructor specifies a true popup trigger flag

		MouseEvent triggerEvent = new MouseEvent((Component)event.getSource(),
			event.getID(), event.getWhen(), event.getModifiers(), event.getX(),
			event.getY(), event.getClickCount(), true);

		// now listeners get triggered event.
		super.processMouseEvent(triggerEvent); 
	}
	*/
	public Dimension getMinimumSize() {
		return new Dimension(100,100);
	}
	public Dimension getPreferredSize() { 
		return getMinimumSize(); 
	}
	public void paint(Graphics g) {
		Dimension sz = getSize();
		g.setColor(SystemColor.controlDkShadow);
		g.fillRect(0,0,sz.width,sz.height);
	}
}
