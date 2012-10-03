import java.applet.Applet;
import java.awt.*;

public class CopyTest2 extends Applet {
	public void paint(Graphics g) {
		setForeground(Color.yellow);

		// the next line would do just as well as the following
		// g.setColor(Color.yellow);

		Graphics copy = getGraphics();
		try {
			System.out.println("g=" + g.getColor() + 
								" copy=" + copy.getColor());

			copy.drawLine(0,0,
							getSize().width-1, getSize().height-1);
		}
		finally {
			copy.dispose();
		}
	}
}
