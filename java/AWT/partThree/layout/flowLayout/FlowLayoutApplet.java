import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class FlowLayoutApplet extends Applet {
	public void init() {
		Button 	one = new Button("  1  "), 
				two = new Button("  2  "), 
				three = new Button("  3  "), 
				four = new Button("  4  "), 
				five = new Button("  5  "), 
				six = new Button("  6  ");

		Panel 			panel  = new Panel(); 
		TenPixelBorder 	border = new TenPixelBorder(panel);

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		panel.add(one);
		panel.add(five);
		panel.add(two);
		panel.add(three);
		panel.add(four);
		panel.add(five);
		panel.add(six);

		setLayout(new BorderLayout());
		add(border, "Center");
	}
}
