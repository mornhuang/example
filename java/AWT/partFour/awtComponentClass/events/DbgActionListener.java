import java.awt.*;
import java.awt.event.*;

public class DbgActionListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println(event.toString());
	}
}
