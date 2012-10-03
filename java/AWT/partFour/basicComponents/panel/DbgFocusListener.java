import java.awt.*;
import java.awt.event.*;

public class DbgFocusListener implements FocusListener {
	public void focusGained(FocusEvent event) {
		System.out.println(event.toString());
	}
	public void focusLost(FocusEvent event) {
		System.out.println(event.toString());
	}
}
