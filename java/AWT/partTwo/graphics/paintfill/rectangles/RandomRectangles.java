import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class RandomRectangles extends Applet {
	private static Color[] colors = { 
		Color.white, Color.black, Color.blue, Color.red, 
		Color.yellow, Color.orange, Color.cyan, Color.pink, 
		Color.magenta, Color.green };

	private int numRects = 10;
	private boolean fill = false, raise = false, 
					round = false, threeD = false;

	public void init() {
		Button rectsButton = new Button("rectangles");	
		Button roundButton = new Button("round rectangles");	
		Button threeDButton = new Button("3D rectangles");	
		Checkbox fillCheckbox = new Checkbox("fill");

		add(rectsButton);
		add(roundButton);
		add(threeDButton);
		add(fillCheckbox);

		rectsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				round = false;
				threeD = false;
				repaint();
			}
		});
		roundButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				round = true;	
				threeD = false;
				repaint();
			}
		});
		threeDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				threeD = true;
				round = false;
				repaint();
			}
		});
		fillCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				fill = ((Checkbox)(event.getSource())).getState();
			}
		});
	}
	public void paint(Graphics g) {
		for(int i=0; i < numRects; i++) {
			Point lhc = randomPoint(); // left hand corner
			Dimension size = randomDimension();

			g.setColor(colors[(int)(Math.random()*10)]);

			if(round) {
				if(fill)
					g.fillRoundRect(
						lhc.x,lhc.y,size.width,size.height,
						(int)(Math.random()*250),
						(int)(Math.random()*250));
				else
					g.drawRoundRect(
						lhc.x,lhc.y,size.width,size.height,
						(int)(Math.random()*250),
						(int)(Math.random()*250));
			}
			else if(threeD) {
				g.setColor(Color.lightGray);

				if(fill)
					g.fill3DRect(
						lhc.x,lhc.y,size.width,size.height,raise);
				else
					g.draw3DRect(
						lhc.x,lhc.y,size.width,size.height,raise);
			}
			else {
				if(fill)
					g.fillRect(lhc.x,lhc.y,size.width,size.height);
				else
					g.drawRect(lhc.x,lhc.y,size.width,size.height);
			}
			raise = raise ? false : true;
		}
	}
	private Dimension randomDimension() {
		return new Dimension((int)(Math.random()*250),
							(int)(Math.random()*250));
	}
	private Point randomPoint() {
		return new Point((int)(Math.random()*250),
						(int)(Math.random()*250));
	}
}
