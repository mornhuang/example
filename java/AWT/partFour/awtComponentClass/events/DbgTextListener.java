import java.awt.*;
import java.awt.event.*;

public class DbgTextListener implements TextListener {
	public void textValueChanged(TextEvent event) {
		System.out.println(event.toString());
	}
}
