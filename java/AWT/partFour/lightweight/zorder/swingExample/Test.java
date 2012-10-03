import com.sun.java.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame {
	public Test() {
		super("Lightweight Zorder");

		JScrollPane sp = new JScrollPane();
		PaintCanvas pc = new PaintCanvas();

		sp.setViewportView(pc);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(sp, "Center");
	}
	public static void main(String args[]) {
		final JFrame f = new Test();
		f.setBounds(100,100,150,150);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
				System.exit(0);
			}
		});
	}
}
class PaintCanvas extends Canvas {
	public void paint(Graphics g) {
		Dimension sz = getSize();
		g.setColor(Color.black);
		g.drawRect(0,0,sz.width-1,sz.height-1);
	}
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}
}
