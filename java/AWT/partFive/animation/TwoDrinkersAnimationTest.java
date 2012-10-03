import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.Panel;

public class TwoDrinkersAnimationTest extends Applet {
	public void init() {
		setLayout(new BorderLayout());
		add(new Label(
		"TwoDrinkers Animation - Mouse Down Starts/Stops")); 
		add(new TwoDrinkersAnimationTestPanel(this));
    } 
}
class TwoDrinkersAnimationTestPanel extends Panel {
    public TwoDrinkersAnimationTestPanel(Applet applet) {
        setLayout(new BorderLayout());
        add(new TwoDrinkersPlayfield(applet), "Center"); 
    }
	public void update(Graphics g) {
		paint(g);
	}
}
