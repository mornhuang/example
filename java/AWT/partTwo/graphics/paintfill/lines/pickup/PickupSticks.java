import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class PickupSticks extends Applet {
	private static Color[] colors = { 
		Color.white, Color.black, Color.blue, Color.red, 
		Color.yellow, Color.orange, Color.cyan, Color.pink, 
		Color.magenta, Color.green };

	public void init() {
		Button button = new Button("scatter");	

		add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				repaint();
			}
		});
	}
	public void paint(Graphics g) {
		for(int i=0; i < 500; ++i) {
			int x = (int)(Math.random()*100);
			int y = (int)(Math.random()*100);
			int deltax = (int)(Math.random()*100);
			int deltay = (int)(Math.random()*100);

			g.setColor(colors[(int)(Math.random()*10)]);
			g.drawLine(x,y,x + deltax, y + deltay);
		}
	}
}
