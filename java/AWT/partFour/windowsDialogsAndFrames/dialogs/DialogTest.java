/*
	1.  You've got to have a Frame to create a Dialog.
*/
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class DialogTest extends Applet {
	private Button launchButton = new Button("Show Dialog ...");
	private Dialog dialog;

	public void init() {
		add(launchButton);

		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialog.pack();
				dialog.show();
			}
		});
	}
	public void start() {
		dialog = new TestDialog(getFrame(DialogTest.this),
		                        "Dialog Title");
	}
    static Frame getFrame(Component c) {
        while((c = c.getParent()) != null) {
            if(c instanceof Frame)
                return (Frame)c;
        }
        return null;
    }
}
class TestDialog extends Dialog {
	private Panel     centerPanel  = new DialogCenterPanel();
	private WorkPanel workPanel    = new WorkPanel(centerPanel);
	private Button    doneButton;

	public TestDialog(Frame frame, String title) {
		super(frame, title);
		doneButton = workPanel.addButton("Done");

		setLayout(new BorderLayout());
		add("Center", workPanel);

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				dispose();
			}
		});
	}
}
class DialogCenterPanel extends Panel {
	public DialogCenterPanel() {
		add(new Label("Center Panel"));
	}
}
class WorkPanel extends Panel {
	Panel centerPanel;
	Panel buttonPanel = new Panel();

	public WorkPanel(Panel centerPanel) {
		this.centerPanel = centerPanel;

		setLayout(new BorderLayout());
		add("Center", centerPanel);
		add("South",  buttonPanel);
	}
	public Button addButton(String label) {
		Button button = new Button(label);
		buttonPanel.add(button);
		return button;
	}
}
