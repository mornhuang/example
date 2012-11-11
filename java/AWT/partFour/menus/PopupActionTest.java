import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class PopupActionTest extends Applet {
	private PopupMenu popup = new PopupMenu();
	private MenuItem  itemOne, itemTwo, itemThree, itemFour;
	private PopupActionListener actionListener;

	public void init() {
		add(popup);

		popup.add(itemOne   = new MenuItem("item one"));
		popup.add(itemTwo   = new MenuItem("item two"));
		popup.add(itemThree = new MenuItem("item three"));
		popup.add(itemFour  = new MenuItem("item four"));

		actionListener = new PopupActionListener();

		itemOne.addActionListener  (actionListener);
		itemTwo.addActionListener  (actionListener);
		itemThree.addActionListener(actionListener);
		itemFour.addActionListener (actionListener);

		Menu m = new Menu("cascading");
		m.add("item one");
		m.add("item two");
		m.add("item three");
		m.add("item four");

		popup.add(m);

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
	class PopupActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			MenuItem mi = (MenuItem)event.getSource();
			showStatus(mi.getLabel());
		}
	}
}
