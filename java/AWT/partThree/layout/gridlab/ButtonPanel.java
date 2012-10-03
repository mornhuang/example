import java.awt.*;

class ButtonPanel extends Panel {
	Panel     buttonPanel = new Panel();
    Separator separator   = new Separator();

    public ButtonPanel() {
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        setLayout(new BorderLayout(0,5));
        add(separator, "North");
        add(buttonPanel, "Center");
    }
    public void add(Button button) {
        buttonPanel.add(button);
    }
}
