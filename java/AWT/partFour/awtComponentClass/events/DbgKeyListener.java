import java.awt.*;
import java.awt.event.*;

public class DbgKeyListener implements KeyListener {
	public void keyPressed(KeyEvent event) {
		System.out.println(event.toString());
	}
	public void keyReleased(KeyEvent event) {
		System.out.println(event.toString());
	}
	public void keyTyped(KeyEvent event) {
		System.out.println(event.toString());
	}
}
