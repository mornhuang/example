import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Polylines extends Applet {
	private static Color[] colors = { 
		Color.white, Color.black, Color.blue, Color.red, 
		Color.yellow, Color.orange, Color.cyan, Color.pink, 
		Color.magenta, Color.green };

	public void init() {
		Button button = new Button("repaint");
		add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Polylines.this.repaint();
			}
		});
	}
	public void paint(Graphics g) {
		int arraySize = ((int)(Math.random()*100));
		int[] xPoints = new int[arraySize];
		int[] yPoints = new int[arraySize];

		for(int i=0; i < xPoints.length; ++i) {
			xPoints[i] = ((int)(Math.random()*200)); 
			yPoints[i] = ((int)(Math.random()*200)); 
		}
		g.setColor(colors[(int)(Math.random()*10)]);
		g.drawPolyline(xPoints, yPoints, xPoints.length);

		showStatus(arraySize + " points");
	}
}
