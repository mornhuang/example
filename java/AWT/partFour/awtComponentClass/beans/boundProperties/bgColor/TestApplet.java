import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class TestApplet extends Applet 
						implements PropertyChangeListener {
	public void init() {
		final Canvas canvas = new Canvas();
		final ColorChoice colorChoice = new ColorChoice();

		setLayout(new BorderLayout());
		add(colorChoice, "North");
		add(canvas, "Center");

		canvas.addPropertyChangeListener("background", this);

		colorChoice.setColor(Color.lightGray);

		colorChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				canvas.setBackground(colorChoice.getColor());
			}
		});
	}
	public void propertyChange(PropertyChangeEvent e) {
		Color color = (Color)e.getNewValue();
		showStatus("Canvas Background Color:  " + color);
	}
}
