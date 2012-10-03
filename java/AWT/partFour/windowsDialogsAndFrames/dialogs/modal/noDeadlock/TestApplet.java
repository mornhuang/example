import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TestApplet extends Applet implements Runnable {
	Thread thread;
	Dialog d = new NoDeadlockDialog();

	public void init() {
		addMouseListener(new MouseAdapter() {
			public synchronized void mousePressed(MouseEvent e) {
				try {
					Thread.currentThread().sleep(2000);
				}
				catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				if(thread == null) {
					Thread thread = new Thread(TestApplet.this);
					thread.start();
				}
			}
		});
	}
	public void run() {
		d.setVisible(true);
		thread = null;
	}
	public void paint(Graphics g) {
		g.drawString("click in here twice in a row " +
					 "within a span of 2 seconds", 20, 20);
	}
}
class NoDeadlockDialog extends Dialog {
	Button doneButton = new Button("doneButton");

	public NoDeadlockDialog() {
		super(new Frame(), "NoDeadlock", true);
		add(doneButton);
		pack();

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
