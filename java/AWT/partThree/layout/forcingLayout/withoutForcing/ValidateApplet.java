import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ValidateApplet extends Applet {
	private GrayPanel grayPanel = new GrayPanel();
	public void init() {
		add(grayPanel);		
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
	public void actionPerformed(ActionEvent event) {
		Button button  = (Button)event.getSource();
		Font   curFont = field.getFont();
		int    newSize = curFont.getSize();

		if(button == lgButton) newSize += 3;
		if(button == smButton) newSize -= 3;

		field.setFont(new Font(curFont.getFamily(),
		                       curFont.getStyle(), newSize));
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0,0,getSize().width-1,getSize().height-1);
	}
}
