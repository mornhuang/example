import java.applet.Applet;
import java.awt.*;

public class GridLayoutApplet extends Applet {
	private Button one, two, three, four, five, six, 
	                    seven, eight, nine, ten;

	public void init() {
		Panel          buttonPanel = new Panel();
		TenPixelBorder border = new TenPixelBorder(buttonPanel);

		one   = new Button("  1  ");
		two   = new Button("  2  ");
		three = new Button("  3  ");
		four  = new Button("  4  ");
		five  = new Button("  5  ");
		six   = new Button("  6  ");
		seven = new Button("  7  ");
		eight = new Button("  8  ");
		nine  = new Button("  9  ");
		ten   = new Button("  10  ");

		buttonPanel.setLayout(new GridLayout(3,0,10,10));
		buttonPanel.add(one);   
		buttonPanel.add(two);   
		buttonPanel.add(three);
		buttonPanel.add(four);  
		buttonPanel.add(five);  
		buttonPanel.add(six);
		buttonPanel.add(seven); 
		buttonPanel.add(eight); 
		buttonPanel.add(nine);
		buttonPanel.add(ten);

		setLayout(new BorderLayout());
		add(border, "Center");
	}
}
