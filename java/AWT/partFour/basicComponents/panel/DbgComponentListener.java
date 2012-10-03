import java.awt.*;
import java.awt.event.*;

public class DbgComponentListener implements ComponentListener {
	public void componentResized(ComponentEvent event) {
		System.out.println("");
	}
	public void componentShown(ComponentEvent event) {
		System.out.println(event.toString());
	}
	public void componentMoved(ComponentEvent event) {
		System.out.println(event.toString());
	}
	public void componentHidden(ComponentEvent event) {
		System.out.println(event.toString());
	}
}
