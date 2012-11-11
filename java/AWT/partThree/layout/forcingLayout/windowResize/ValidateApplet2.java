import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ValidateApplet2 extends Applet 
							 implements ActionListener {
	Button launchButton = new Button("launch dialog ...");
	ValidateDialog validateDialog;

	public void init() {
		add(launchButton);
		launchButton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent event) {
		if(validateDialog == null) {
			validateDialog = 
				new ValidateDialog(new Frame(),
									"Validate Dialog",
									true);
		}
		validateDialog.show();
	}
}
class GrayPanel extends Panel implements ActionListener {
	private TextField  field    = new TextField("TextField");
	private Button     lgButton = new Button   ("larger font");
	private Button     smButton = new Button   ("smaller font");

	public GrayPanel() {
		lgButton.addActionListener(this);
		smButton.addActionListener(this);

		add(field);		
		add(lgButton);
		add(smButton);

		setBackground(Color.gray);
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0,0,getSize().width-1,getSize().height-1);
	}
	public void actionPerformed(ActionEvent event) {
		Button button  = (Button)event.getSource();
		Font   curFont = field.getFont();
		int    newSize = curFont.getSize();

		if(button == lgButton) newSize += 3;
		if(button == smButton) newSize -= 3;

		field.setFont(new Font(curFont.getFamily(),
			                   curFont.getStyle(), 
							   newSize));
		field.invalidate();
		getParent().validate();
	}
}

class ValidateDialog extends Dialog {
	public ValidateDialog(Frame frame, 
	                      String title, 
						  boolean modal) {
		super(frame, title, true);
		add("Center", new GrayPanel());
		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
			}
		});
	}
	public void validate() {
		setSize(getPreferredSize().width, 
				getPreferredSize().height);
		super.validate();
	}
}
