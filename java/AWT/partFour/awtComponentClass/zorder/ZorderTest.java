import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ZorderTest extends Applet {
	Button buttonOne   = new Button("Button 1");
	Button buttonTwo   = new Button("Button 2");
	Button buttonThree = new Button("Button 3");
	Button buttonFour  = new Button("Button 4");
	Button buttonFive  = new Button("Button 5");
	Button buttonSix   = new Button("Button 6");

	public void init() {
		Listener listener = new Listener();

		setLayout(new BulletinLayout());

		buttonOne.setLocation  (10,10);
		buttonTwo.setLocation  (35,20);
		buttonThree.setLocation(55,30);
		buttonFour.setLocation (75,40);
		buttonFive.setLocation (95,50);
		buttonSix.setLocation  (115,60);

		add(buttonOne);
		add(buttonTwo);
		add(buttonThree);
		add(buttonFour);
		add(buttonFive);
		add(buttonSix);

		buttonOne.addActionListener  (listener);
		buttonTwo.addActionListener  (listener);
		buttonThree.addActionListener(listener);
		buttonFour.addActionListener (listener);
		buttonFive.addActionListener (listener);
		buttonSix.addActionListener  (listener);
	}
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Button   button  = (Button)event.getSource();
			int      zorder   = getZorder(button);
			Button   top     = (Button)getComponent(0);

			remove(button);
			remove(top);

			add(button, 0);
			add(top, zorder);
			validate();
		}
		private int getZorder(Button button) {
			for(int i=0; i < getComponentCount(); ++i) {
				Component c = getComponent(i);
				if(c == button)
					return i;
			}
			return -1;
		}
	}
}
