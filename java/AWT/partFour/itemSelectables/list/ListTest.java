import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ListTest extends Applet {
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
	private ListTest listTest;
	private List familyList = new List(),
	             styleList  = new List(), 
	             sizeList   = new List();

	public FontPanel(ListTest applet) {
		Listener listener = new Listener();

		listTest = applet;

		populateFonts();   
		populateStyles(); 
		populateSizes();

		add(familyList); 
		add(styleList); 
		add(sizeList);

		familyList.addItemListener(listener);
		styleList.addItemListener (listener);
		sizeList.addItemListener  (listener);

		familyList.select(0);
		styleList.select(0);
		sizeList.select(0);
	}
	public class Listener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			listTest.updateLabel(getSelectedFont());
		}
	}
	public Font getSelectedFont() {
		return new Font(familyList.getSelectedItem(),
		                styleList.getSelectedIndex(),
						Integer.parseInt(
							sizeList.getSelectedItem()));
	}
	private void populateFonts() {
        String fontNames[] = getToolkit().getFontList();

        for(int i=0; i < fontNames.length; ++i)
            familyList.add(fontNames[i]);
    }
    private void populateStyles() {
        styleList.add("Plain");  
		styleList.add("Bold");
        styleList.add("Italic"); 
		styleList.add("BoldItalic");
    }
    private void populateSizes() {
        String sizes[] = {"12", "14", "16", "18", "24", "36"};

        for(int i=0; i < sizes.length; ++i)
            sizeList.add(sizes[i]);
    }
}
