import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ButtonTest extends Applet {
	Button top  = new Button("Toggle bottom button");
	Button bottom = new Button("Toggle top button");

	public void init() {
		Panel bottomPanel = new Panel();
		Panel topPanel    = new Panel();

		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(bottom, "Center");

		topPanel.add(top);

		setLayout(new BorderLayout());
		add("North",  topPanel);
		add("Center", bottomPanel);

		top.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(bottom.isEnabled()) bottom.setEnabled(false);
				else                  bottom.setEnabled(true);
			}
		});
		bottom.addActionListener(new BottomActionListener(top));
	}
}
class BottomActionListener implements ActionListener {
	private Button otherButton;

	public BottomActionListener(Button otherButton) {
		this.otherButton = otherButton;
	}
	public void actionPerformed(ActionEvent event) {
		if(otherButton.isEnabled()) 
			otherButton.setEnabled(false);
		else                 
			otherButton.setEnabled(true);
	}
}
