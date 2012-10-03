import java.applet.Applet;
import java.awt.*;

public class ButtonActionApplet extends Applet {
	public void init() {
		Button buttonOne   = new Button("Button One");
		Button buttonTwo   = new Button("Button Two");
		Button buttonThree = new Button("Button Three");

		add(buttonOne);
		add(buttonTwo);
		add(buttonThree);
	}
	public boolean action(Event event, Object what) {
		if(what.equals("Button One"))
			System.out.println("Button One");
		if(what.equals("Button Two"))
			System.out.println("Button Two");
		if(what.equals("Button Three"))
			System.out.println("Button Three");

		return true;
	}
}
