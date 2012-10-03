import java.awt.Event;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.Label;

public class StarterApplication extends Frame {
	public static void main(String args[]) {
		StarterApplication app = 
				new StarterApplication("Starter Application");

		app.setSize(300,100);
		app.show  ();
		System.out.println("StarterApplication::main()");
	}
	public StarterApplication(String frameTitle) {
		super(frameTitle);
		add  (new Label("Starter", Label.CENTER), "Center");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
	}
}
