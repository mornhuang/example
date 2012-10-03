import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TestApplet extends Applet {
	Dialog d = new DeadlockDialog();

	public void init() {
		addMouseListener(new MouseAdapter() {
			public synchronized void mousePressed(MouseEvent e) {
				try {
					Thread.currentThread().sleep(2000);
				}
				catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				d.setVisible(true);
			}
		});
	}
	public void paint(Graphics g) {
		g.drawString("click in here twice in a row " +
					 "within a span of 2 seconds", 20, 20);
	}
}
class DeadlockDialog extends Dialog {
	Button doneButton = new Button("doneButton");

	public DeadlockDialog() {
		super(new Frame(), "Deadlock", true);
		add(doneButton);
		pack();

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
