import java.awt.*;
import java.awt.event.*;

public class DbgMouseMotionListener implements 
                                    MouseMotionListener {
	public void mouseMoved(MouseEvent event) {
		System.out.println(event.toString());
	}
	public void mouseDragged(MouseEvent event) {
		System.out.println(event.toString());
	}
}
