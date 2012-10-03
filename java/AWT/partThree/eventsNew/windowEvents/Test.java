import java.awt.*;
import java.awt.event.*;

public class Test extends Frame {
	public Test() {
		super("WindowListener test");
	}
	public static void main(String args[]) {
		final Frame f = new Test();
		f.setBounds(100,100,250,150);
		f.setVisible(true);
		f.addWindowListener(new TestWindowListener());
	}
}
class TestWindowListener implements WindowListener {
	public void windowActivated(WindowEvent e) {
		System.out.println("window activated");
	}
	public void windowClosed(WindowEvent e) {
		System.out.println("window closed");
		System.exit(0);
	}
	public void windowClosing(WindowEvent e) {
		System.out.println("window closing ...");
		Window w = e.getWindow();
		w.dispose();
	}
	public void windowDeactivated(WindowEvent e) {
		System.out.println("window deactivated");
	}
	public void windowDeiconified(WindowEvent e) {
		System.out.println("window deiconified");
	}
	public void windowOpened(WindowEvent e) {
		System.out.println("window opened");
	}
}
