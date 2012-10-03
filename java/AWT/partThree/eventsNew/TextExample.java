import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class TextExample extends Applet {
	private TextField textField = new TextField(25);
	private TextArea  textArea  = new TextArea(10, 20);

    public void init() {
		add(textField);
		add(textArea);

		textField.addTextListener(new DebugTextListener());
		textArea.addTextListener(new DebugTextListener());
    }
}
class DebugTextListener implements TextListener {
    public void textValueChanged(TextEvent event) {
        Object obj = event.getSource();
        System.out.println(obj.toString());
    }
}
