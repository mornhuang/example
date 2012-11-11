import java.applet.Applet;
import java.awt.*;

public class ButtonActionByRefApplet extends Applet {
	private Button buttonOne   = new Button("Button One");
	private Button buttonTwo   = new Button("Button Two");
	private Button buttonThree = new Button("Button Three");

	public void init() {
		add(buttonOne);
		add(buttonTwo);
		add(buttonThree);
	}
	public boolean action(Event event, Object what) {
		if(event.target == buttonOne)
			System.out.println(buttonOne.getLabel());
		if(event.target == buttonTwo)
			System.out.println(buttonTwo.getLabel());
		if(event.target == buttonThree)
			System.out.println(buttonThree.getLabel());

		return true;
	}
}
