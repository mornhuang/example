import java.awt.*;
import java.awt.event.*;

public class DbgItemListener implements ItemListener {
	public void itemStateChanged(ItemEvent event) {
		System.out.println(event.toString());
	}
}
