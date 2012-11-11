import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SerializeTest2 extends Frame {
	static Frame f;
	Button button;

	static public void main(String args[]) {
		f = new SerializeTest2();
		f.pack();
		f.setVisible(true);
	}
	static public Frame getFrame() {
		return f;
	}
	public SerializeTest2() {
		super("Serialize Test");
		setLayout(new FlowLayout());

		add(button = new Button("Serialize Me"));
		button.addActionListener(new ButtonListener());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
	}
}
class ButtonListener implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent event) {
		try {
			doSerialize((Button)event.getSource());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void doSerialize(Button button) throws 
											IOException ,
											ClassNotFoundException {
		FileOutputStream fo = new FileOutputStream("button");
		ObjectOutputStream so = new ObjectOutputStream(fo);
		so.writeObject(button);
		so.flush();

		FileInputStream fi = new FileInputStream("button");
		ObjectInputStream si = new ObjectInputStream(fi);
		Button b = (Button)si.readObject();

		Frame f = SerializeTest2.getFrame();
		f.add(b);

		f.pack();
		f.setVisible(false);
		f.setVisible(true);
	}
}
