import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class PlusMinus extends Component 
						implements ItemExpandable {
	protected boolean  expanded      = false;
	private   Object[] expandedItems = new Object[1];
	private   Vector   listeners     = new Vector();

	public PlusMinus() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if(expanded) contract();
				else         expand  ();

				repaint();
			}
		});
	}
	public void paint(Graphics g) {
		drawBorder(g);
		drawPlusOrMinus(g);
	}
	public void expand() {
		ItemExpandEvent event = 
			new ItemExpandEvent(this,  // we are event source
			                    this,  // we are item
								ItemExpandEvent.EXPANDED);
		expanded = true;
		processExpandEvent(event);
	}
	public void contract() {
		ItemExpandEvent event = 
			new ItemExpandEvent(this,  // we are event source
			                    this,  // we are item
								ItemExpandEvent.CONTRACTED);
		expanded = false;
		processExpandEvent(event);
	}
	public Dimension getPreferredSize() {
		return new Dimension(11,11);
	}
	public Object[] getExpandedItems() {
		if(expanded) {
			expandedItems[0] = this;
			return expandedItems;
		}
		else
			return null;
	}
	public void addExpandListener(ItemExpandListener l) {
		listeners.addElement(l);
	}
	public void removeExpandListener(ItemExpandListener l) {
		listeners.removeElement(l);
	}
	protected synchronized void processExpandEvent(
										ItemExpandEvent event) {
		Enumeration e = listeners.elements();
		while(e.hasMoreElements()) {
			ItemExpandListener l = (ItemExpandListener)
									e.nextElement();
			l.itemExpandStateChanged(event);
		}
	}
	private void drawBorder(Graphics g) {
		Dimension size = getSize();
		g.setColor(Color.darkGray.brighter());
		g.drawRect(0,0,size.width-1,size.height-1);
	}
	private void drawPlusOrMinus(Graphics g) {
		Dimension size = getSize();

		if(expanded) drawMinusSign(g, size, Color.black);
		else         drawPlusSign (g, size, Color.black);
	}
	private void drawMinusSign(Graphics g, Dimension size, 
							   Color color) {
		g.setColor(color);
		g.drawLine(2,size.height/2,size.width-3,size.height/2);
	}
	private void drawPlusSign(Graphics g, Dimension size, 
							  Color color) {
		g.setColor(color);
		g.drawLine(size.width/2,2,size.width/2,size.height-3);
		g.drawLine(2,size.height/2,size.width-3,size.height/2);
	}
}
