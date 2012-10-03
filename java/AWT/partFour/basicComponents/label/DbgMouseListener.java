import java.awt.*;
import java.awt.event.*;

public class DbgMouseListener implements MouseListener {
	public void mouseEntered(MouseEvent event) {
		System.out.println(event.toString());
	}
	public void mouseExited(MouseEvent event) {
		System.out.println(event.toString());
	}
	public void mousePressed(MouseEvent event) {
		System.out.println(event.toString());
	}
	public void mouseClicked(MouseEvent event) {
		System.out.println(event.toString());
	}
	public void mouseReleased(MouseEvent event) {
		System.out.println(event.toString());
	}
}
