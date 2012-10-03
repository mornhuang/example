import java.applet.Applet;
import java.awt.*;

public class InvokeAddNotify extends Applet {
	public InvokeAddNotify() {
		addNotify();
		showPeerDependentProperties();
	}
	private void showPeerDependentProperties() {
		System.out.println("font:  " + getFont());
		System.out.println("graphics:  " + getGraphics());
		System.out.println("offscreen image:  " + 
							createImage(50,50));
	}
}
