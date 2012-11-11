import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class PopupShowTest extends Applet {
	Choice        choice = new Choice();
	PopupMenu     popup  = new PopupMenu();
	ColoredCanvas popupRelativeToMe;
	ColoredCanvas blueCanvas, redCanvas, yellowCanvas;

	public void init() {
		blueCanvas        = new ColoredCanvas(Color.blue);
		redCanvas         = new ColoredCanvas(Color.red);
		yellowCanvas      = new ColoredCanvas(Color.yellow);
		popupRelativeToMe = blueCanvas;

		popup.add(new MenuItem("item one"));
		popup.add(new MenuItem("item two"));
		popup.add(new MenuItem("item three"));
		popup.add(new MenuItem("item four"));

		add(popup);                   // Component.add(PopupMenu)

		Menu m = new Menu("Cascading");
		m.add("item one");
		m.add("item two");
		m.add("item three");
		m.add("item four");

		add(new Label("Popup Over:"));//Container.add(Component)
		add(choice);       
		add(blueCanvas);              // Container.add(Component)
		add(redCanvas);               // Container.add(Component)
		add(yellowCanvas);            // Container.add(Component)

		choice.add("Blue Canvas");
		choice.add("Yellow Canvas");
		choice.add("Red Canvas");

		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				Choice c     = (Choice)event.getSource();
				String label = c.getSelectedItem();

				if(label.equals("Blue Canvas"))
					popupRelativeToMe = blueCanvas;
				else if(label.equals("Red Canvas"))
					popupRelativeToMe = redCanvas;
				else if(label.equals("Yellow Canvas"))
					popupRelativeToMe = yellowCanvas;

				popup.show(popupRelativeToMe, 5, 5);
			}
		});
	}
}
class ColoredCanvas extends Canvas {
	private Color color;

	public ColoredCanvas(Color color) {
		this.color = color;
	}
	public void paint(Graphics g) {
		Dimension size = getSize();
		g.setColor  (color);
		g.fill3DRect(0,0,size.width-1,size.height-1,true);
	}
	public Dimension getPreferredSize() {
		return new Dimension(100,100);
	}
}
