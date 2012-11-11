import java.applet.Applet;
import java.awt.*;

public class OverrideAddNotify extends Applet {
	public OverrideAddNotify() {
		// peer is not created yet
		System.out.println("Before Peer is Created:");
		showPeerDependentProperties();
		System.out.println();
	}
	public void addNotify() {
		super.addNotify();

		// now peer exists
		System.out.println("After Peer is Created:");
		showPeerDependentProperties();
	}
	private void showPeerDependentProperties() {
		System.out.println("font:  " + getFont());
		System.out.println("graphics:  " + getGraphics());
		System.out.println("offscreen image:  " + 
							createImage(50,50));
	}
}
