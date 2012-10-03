import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CursorChoiceTest extends Applet {
	private CursorChoice cursorChoice = new CursorChoice();

    public void init() {
		add(cursorChoice);

		cursorChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				setCursor(cursorChoice.getSelectedCursor());
			}
		});
    }
}
