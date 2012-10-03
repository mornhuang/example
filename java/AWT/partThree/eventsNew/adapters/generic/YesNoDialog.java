import java.awt.*;
import java.awt.event.*;

class YesNoDialog extends Dialog {
	Button yesButton   = new Button("Yes");
	Button noButton    = new Button("No");
	boolean answer;

	public YesNoDialog(String title, 
						String message, boolean isModal) {
		super(new Frame(), title, isModal);

		Panel  buttonPanel = new Panel();
		Panel  labelPanel  = new Panel();

		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);

		labelPanel.add(new Label(message));

		add(labelPanel, "Center");
		add(buttonPanel, "South");

		yesButton.addActionListener(
			new GenericActionAdapter(this, "yesButtonActivated"));

		noButton.addActionListener(
			new GenericActionAdapter(this, "noButtonActivated"));

		pack();
	}
	public void yesButtonActivated(ActionEvent event) {
		answer = true;
		dispose();
	}
	public void noButtonActivated(ActionEvent event) {
		answer = false;
		dispose();
	}
	public boolean getAnswer() {
		return answer;
	}
}
