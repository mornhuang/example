import java.awt.*;
import java.awt.event.*;

class YesNoDialog extends Dialog {
	private Button yesButton   = new Button("Yes");
	private Button noButton    = new Button("No");
	private boolean answer;

	public YesNoDialog(String title, String message, boolean isModal) {
		super(new Frame(), title, isModal);

		Panel  buttonPanel = new Panel();
		Panel  labelPanel  = new Panel();

		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);

		labelPanel.add(new Label(message));

		add(labelPanel, "Center");
		add(buttonPanel, "South");

		yesButton.addActionListener(new YesAdapter(this));
		noButton.addActionListener(new NoAdapter(this));

		pack();
	}
	public boolean getAnswer() {
		return answer;
	}
	public void yesButtonActivated(ActionEvent event) {
		answer = true;
		dispose();
	}
	public void noButtonActivated(ActionEvent event) {
		answer = false;
		dispose();
	}
}
class YesAdapter implements ActionListener {
	YesNoDialog target;

	public YesAdapter(YesNoDialog dialog) {
		target = dialog;
	}
	public void actionPerformed(ActionEvent event) {
		target.yesButtonActivated(event);
	}
}
class NoAdapter implements ActionListener {
	YesNoDialog target;

	public NoAdapter(YesNoDialog dialog) {
		target = dialog;
	}
	public void actionPerformed(ActionEvent event) {
		target.noButtonActivated(event);
	}
}
