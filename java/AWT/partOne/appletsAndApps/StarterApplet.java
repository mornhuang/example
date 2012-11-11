import java.applet.Applet;
import java.awt.Label;

public class StarterApplet extends Applet {
	private Label label;

	public void init() {
		System.out.println("Applet::init()");
	}
	public void start() {
		System.out.println("Applet::start()");
		label = new Label("Starter");
		add(label);
	}
	public void stop() {
		System.out.println("Applet::stop()");
		remove(label);
	}
	public void destroy() {
		System.out.println("Applet::destroy()");
	}
}
