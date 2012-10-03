import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class PopupTest extends Applet {
	private PopupMenu popup = new PopupMenu();

	public void init() {
		add(popup);

		popup.add(new MenuItem("item one"));
		popup.add(new MenuItem("item two"));
		popup.add(new MenuItem("item three"));
		popup.add(new MenuItem("item four"));

		addMouseListener(new MouseAdapter() {
			public void mousePressed (MouseEvent e) { 
				showPopup(e); 
			}
			public void mouseClicked (MouseEvent e) { 
				showPopup(e); 
			}
			public void mouseReleased(MouseEvent e) { 
				showPopup(e); 
			}
		});
	}
	void showPopup(MouseEvent e) {
		if(e.isPopupTrigger()) 
			popup.show(this, e.getX(), e.getY());
	}
}
