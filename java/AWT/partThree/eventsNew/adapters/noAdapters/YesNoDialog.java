import java.awt.*;
import java.awt.event.*;

class YesNoDialog extends Dialog implements ActionListener {
	private Button  yesButton   = new Button("Yes");
	private Button  noButton    = new Button("No");
	private boolean answer;

	public YesNoDialog(String title, String message, 
						boolean isModal) {
		super(new Frame(), title, isModal);

		Panel  buttonPanel = new Panel();
		Panel  labelPanel  = new Panel();

		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);

		labelPanel.add(new Label(message));

		add(labelPanel, "Center");
		add(buttonPanel, "South");

		yesButton.addActionListener(this);
		noButton.addActionListener(this);

		pack();
	}
	public boolean getAnswer() {
		return answer;
	}
	public void actionPerformed(ActionEvent event) {
		Button button = (Button)event.getSource();

		if(button == yesButton) 
			yesButtonActivated(event);
		else if(button == noButton) 
			noButtonActivated(event);
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
