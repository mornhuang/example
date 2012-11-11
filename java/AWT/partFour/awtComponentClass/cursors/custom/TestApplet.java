import java.applet.Applet;
import java.awt.*;

public class TestApplet extends Applet {
	public void start() {
		Toolkit 	tk = Toolkit.getDefaultToolkit();
		Image		cursorImage;
		Cursor 		cursor;
		Dimension 	bestSize = tk.getBestCursorSize(32,32);
		int 		maxColors = tk.getMaximumCursorColors();

		System.out.println("Cursor size:  " + bestSize);
		System.out.println("Max cursor colors:  " + maxColors);

		if(maxColors == 0) 
			System.out.println("custom cursors not supported");
		else {
			cursorImage = tk.getImage("smileCursor.gif");
			cursor 		= tk.createCustomCursor(cursorImage, 
											new Point(1,1), 
											"smile cursor");
			setCursor(cursor);
		}
	}
}
