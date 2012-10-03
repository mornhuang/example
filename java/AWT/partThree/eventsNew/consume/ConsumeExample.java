import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ConsumeExample extends Applet {
    public void init() {
		Button button = new Button("Can't Click This");
		button.addMouseListener(new ConsumeButtonListener(this));

		addKeyListener(new ConsumeKeyListener(this));
		requestFocus();

		add(button);
    }
}
class ConsumeButtonListener extends MouseAdapter {
	private Applet applet;

	public ConsumeButtonListener(Applet applet) {
		this.applet = applet;
	}
	public void mousePressed(MouseEvent event) {
		applet.showStatus("Consuming button press");
		event.consume();
	}
}
class ConsumeKeyListener extends KeyAdapter {
	private Applet applet;

	public ConsumeKeyListener(Applet applet) {
		this.applet = applet;
	}
	public void keyPressed(KeyEvent event) {
		char key = event.getKeyChar();

		if(key == 'a') {
			applet.showStatus("Consuming 'a' key");
			event.consume();
		}
	}
}
