import java.applet.Applet;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleI18NTest extends Applet implements ItemListener {
	private Label  guiLabel = new Label();
	private Choice choice   = new Choice();

	public void init() {
		choice.add(Locale.ENGLISH.getDisplayName());
		choice.add(Locale.FRENCH.getDisplayName());
		choice.addItemListener(this);

		add(choice);
		add(guiLabel);

		guiLabel.setLocale(Locale.ENGLISH);
		internationalize();
	}
	public void itemStateChanged(ItemEvent event) {
		int index = choice.getSelectedIndex();
		
		if(index == 0) guiLabel.setLocale(Locale.ENGLISH);
		else           guiLabel.setLocale(Locale.FRENCH);

		internationalize();
		validate();
	}
	private void internationalize() {
		String s = getIdentifierString(guiLabel.getLocale());

		if(s != null) {
			guiLabel.setText(s);
			guiLabel.invalidate();
		}
	}
	private String getIdentifierString(Locale l) {
		ResourceBundle bundle = null;

		try { 
			bundle = ResourceBundle.getBundle("LabelsBundle", l);
		}
		catch(MissingResourceException e) {
			e.printStackTrace();
		}
		if(bundle == null) 
			return null;
		else 
			return (String)bundle.getObject("Identifier");
	}
}
