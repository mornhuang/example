import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TestApplet extends Applet {
	private TextField tf = new TextField(10);

	public void init() {
		tf.addKeyListener(new TextfieldListener());
		add(tf);
	}
}
class TextfieldListener implements KeyListener {
	public void keyPressed(KeyEvent e) {
		System.out.println("KEY_PRESSED:  ");
		report(e);
	}
	public void keyReleased(KeyEvent e) {
		System.out.println("KEY_RELEASED:  ");
		report(e);
	}
	public void keyTyped(KeyEvent e) {
		System.out.println("KEY_TYPED:  "); 
		report(e);
	}
	private void report(KeyEvent e) {
		int 	keyCode = e.getKeyCode();
		char 	keyChar = e.getKeyChar();
		String 	mods 	= e.getKeyModifiersText(keyCode);
		String 	txt 	= e.getKeyText(keyCode);

		if(keyCode != KeyEvent.KEY_UNDEFINED)
			System.out.println("Code:  " + keyCode);

		if(keyCode != KeyEvent.CHAR_UNDEFINED)
			System.out.println("Char:  " + keyChar);

		System.out.println("Modifiers:  " + mods);
		System.out.println("Text:  " + txt);

		if(e.isActionKey())
			System.out.println("ACTION");

		System.out.println();
	}
}
