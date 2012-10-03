import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CardLayoutApplet extends Applet {
	private Button 	tiny, small, med, lrg;
	private Panel	cardPanel   = new Panel(),
					tinyPanel   = new Panel(),
					smallPanel  = new Panel(), 
					MediumPanel = new Panel(), 
					LargePanel  = new Panel();

	private CardLayout card = new CardLayout(10,5);

	public void init() {
		TenPixelBorder 	border   = new TenPixelBorder(cardPanel);
		ButtonListener 	buttonListener = new ButtonListener();

		cardPanel.setLayout(card);

		// Panels share a FlowLayout by default ...
		tinyPanel.setLayout  (new BorderLayout());
		smallPanel.setLayout (new BorderLayout());
		MediumPanel.setLayout(new BorderLayout());
		LargePanel.setLayout (new BorderLayout());

		tiny = new Button("Card Layout");
		small = new Button("Card Layout");
		med  = new Button("Card Layout");
		lrg  = new Button("Card Layout");

		tiny.setFont(new Font("Helvetica", Font.BOLD, 12));
		small.setFont(new Font("Helvetica", Font.BOLD, 18));
		med.setFont(new Font("Helvetica", Font.BOLD, 24));
		lrg.setFont(new Font("Helvetica", Font.BOLD, 36));

		tiny.addActionListener(buttonListener);
		small.addActionListener(buttonListener);
		med.addActionListener(buttonListener);
		lrg.addActionListener(buttonListener);

		tinyPanel.add  (tiny, "Center"); 
		smallPanel.add (small, "Center"); 
		MediumPanel.add(med, "Center"); 
		LargePanel.add (lrg, "Center"); 

		cardPanel.add("tiny",  tinyPanel);
		cardPanel.add("small", smallPanel);
		cardPanel.add("med",   MediumPanel);
		cardPanel.add("lrg",   LargePanel);

		setLayout(new BorderLayout());
		add(border, "Center");
	}
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			card.next(cardPanel);
		}
	}
}
