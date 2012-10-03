import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class PrintApplet extends Applet {
	Button printButton = new Button("print ...");

	static Frame getFrame(Component c) {
		while((c = c.getParent()) != null) {
			if(c instanceof Frame)
				return (Frame)c;
		}
		return null;
	}
	static void printComponents(Component c) {
		Toolkit    tk    = Toolkit.getDefaultToolkit();
		Frame      frame = getFrame(c);
		Properties props = new Properties();

		props.put("awt.print.printer",   "durango");
		props.put("awt.print.numCopies", "2");

		if(tk != null) {
			String   name = c.getName() + " print job";
			PrintJob pj   = tk.getPrintJob(frame, name, props);

			if(pj != null) {
				Graphics pg = pj.getGraphics();

				if(pg != null) {
					try {
						c.printAll(pg);
					}
					finally {
						pg.dispose();
					}
				}
				pj.end();
			}
			System.out.println(props);
		}
	}
	public void init() {
		add(printButton);
		add(new Label("print this label"));
		add(new TextField("print this textfield"));
		add(new TextArea(10,20));

		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				printComponents(PrintApplet.this);
			}
		});
	}
}
