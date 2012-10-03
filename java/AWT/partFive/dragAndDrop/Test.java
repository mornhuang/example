import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;

public class Test extends Frame {
	public Test() {
		DndPanel dndp = new DndPanel();
		add(dndp, "Center");
	}
	public static void main(String args[]) {
		final Frame f = new Test();
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
class DndPanel extends Panel {
	DragSource dsrc = new DragSource();

	public DndPanel() {
	}
}
