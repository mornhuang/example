import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ChoiceTest extends Applet {
	private FontPanel fontPanel = new FontPanel(this);
	private Label     label     = new Label(" ", Label.CENTER);
	
	public void init() {
		setLayout(new BorderLayout());
		add(fontPanel, "North");
		add(label, "Center");
	}
	public void start() {
		updateLabel(fontPanel.getSelectedFont());
	}
	public void updateLabel(Font font) {
		label.setText(fullNameOfFont(font));
		label.setFont(font);
	}
	private String fullNameOfFont(Font font) {
		String family = font.getFamily();
		String style  = new String();

		switch(font.getStyle()) {
			case Font.PLAIN:  style = " Plain ";  break;
			case Font.BOLD:   style = " Bold ";   break;
			case Font.ITALIC: style = " Italic "; break;

			case Font.BOLD + Font.ITALIC:
				style = " Bold Italic "; 
				break;
		}
		return family + style + Integer.toString(font.getSize());
	}
}
class FontPanel extends Panel {
	private ChoiceTest choiceTest;
	private Choice familyChoice = new Choice(),
	               styleChoice  = new Choice(), 
	               sizeChoice   = new Choice();

	public FontPanel(ChoiceTest applet) {
		Listener listener = new Listener();

		choiceTest = applet;

		populateFonts();   
		populateStyles(); 
		populateSizes();

		add(familyChoice); 
		add(styleChoice); 
		add(sizeChoice);

		familyChoice.addItemListener(listener);
		styleChoice.addItemListener (listener);
		sizeChoice.addItemListener  (listener);
	}
	public class Listener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			choiceTest.updateLabel(getSelectedFont());
		}
	}
	public Font getSelectedFont() {
		return new Font(familyChoice.getSelectedItem(),
		                styleChoice.getSelectedIndex(),
						Integer.parseInt(
							sizeChoice.getSelectedItem()));
	}
	private void populateFonts() {
        String fontNames[] = getToolkit().getFontList();

        for(int i=0; i < fontNames.length; ++i)
            familyChoice.add(fontNames[i]);
    }
    private void populateStyles() {
        styleChoice.add("Plain");  
		styleChoice.add("Bold");
        styleChoice.add("Italic"); 
		styleChoice.add("BoldItalic");
    }
    private void populateSizes() {
        String sizes[] = {"12", "14", "16", "18", "24", "36"};

        for(int i=0; i < sizes.length; ++i)
            sizeChoice.add(sizes[i]);
    }
}
