import java.applet.Applet;
import java.awt.*;

public class TenPixelBorderTestApplet extends Applet {
	public void init() {
		Button    		button = new Button("CENTER");
		TenPixelBorder 	border = new TenPixelBorder(button);

		setLayout(new BorderLayout());
		add(border, "Center");
	}
}
