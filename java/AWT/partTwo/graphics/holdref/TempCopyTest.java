import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class HoldRef extends Applet {
	private Graphics oldg;
	private boolean first = true;
	private Color[] colors = { Color.white, Color.red, Color.blue };

	public HoldRef() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				System.out.println("changing foreground color");
				setForeground(colors[colorcnt]);
				colorcnt = (colorcnt == colors.length) ? 0 : colorcnt+1;
			}
		});
	}
	public void paint(Graphics g) {
		if(first) {
			oldg = g;
			first = false;
		}
		oldg.drawLine(0,0,getSize().width-1, getSize().height-1);
	}
}
