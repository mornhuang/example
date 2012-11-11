import java.applet.Applet;
import java.awt.*;

public class BorderLayoutApplet extends Applet {
	private Button north, south, east, west, center;

	public void init() {
		Panel          buttonPanel = new Panel();
		TenPixelBorder border = new TenPixelBorder(buttonPanel);

		north  = new Button("north");
		east   = new Button("east");
		west   = new Button("west");
		center = new Button("center");
		south  = new Button("south");

		buttonPanel.setLayout(new BorderLayout(2,2));
		buttonPanel.add(north, 	"North");
		buttonPanel.add(south, 	"South");
		buttonPanel.add(east, 	"East");
		buttonPanel.add(west, 	"West");
		buttonPanel.add(center,	"Center");

		setLayout(new BorderLayout());
		add(border, "Center");
	}
}
