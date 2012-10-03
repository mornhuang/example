import java.applet.Applet;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.*;

public class StarterCombined extends Applet {
	private Label label;

	public static void main(String args[]) {
		StarterCombinedFrame app = 
				new StarterCombinedFrame("Starter Application");

		app.setSize(300,100);
		app.show  ();
		System.out.println("StarterCombinedFrame::main()");
	}
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

class StarterCombinedFrame extends Frame {
	public StarterCombinedFrame(String frameTitle) {
		super(frameTitle);

		StarterCombined applet = new StarterCombined();
		applet.start();
		add  (applet, "Center");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}	
		});
	}
}
