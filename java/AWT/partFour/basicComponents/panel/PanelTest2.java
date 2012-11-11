import java.applet.Applet;
import java.awt.*;

public class PanelTest2 extends Applet {
	public void init() {
		Panel     center    = new Panel();
		WorkPanel workPanel = new WorkPanel(center);

		workPanel.addButton("Ok");
		workPanel.addButton("Cancel");

		center.add(new Label("Name:"));
		center.add(new TextField(25));

		setLayout(new BorderLayout());
		add(workPanel);
	}
}
class WorkPanel extends Panel {
	Panel centerPanel;
	Panel buttonPanel = new Panel();

	public WorkPanel(Panel centerPanel) {
		this.centerPanel = centerPanel;

		setLayout(new BorderLayout());
		add(centerPanel, "Center");
		add(buttonPanel, "South");
	}
	public void addButton(String label) {
		buttonPanel.add(new Button(label));
	}
}
