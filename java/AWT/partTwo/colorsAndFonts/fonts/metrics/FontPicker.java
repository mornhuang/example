import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class FontPicker extends Applet {
	private FontPanel 	fontPanel 	= new FontPanel(this);
	private MetricPanel metricPanel = new MetricPanel();
	private Label		metricLabel = new Label();
	
	public void init() {
		setLayout(new BorderLayout());
		add(fontPanel, "North");
		add(metricPanel, "Center");
		add(metricLabel, "South");
	}
	public void start() {
		Font font = fontPanel.getSelectedFont();
		updateMetricPanel(font);
		updateMetricsInfo(font);
	}
	public void update(Font font) {
		updateMetricPanel(font);
		updateMetricsInfo(font);
	}
	public void updateMetricPanel(Font font) {
		metricPanel.setText(fullNameOfFont(font));
		metricPanel.setFont(font);
		metricPanel.repaint();
	}
	public void updateMetricsInfo(Font font) {
		FontMetrics fm = getFontMetrics(font);
		metricLabel.setText(
			"Ascent = " + fm.getAscent() + ", " +
			"Descent = " + fm.getDescent() + ", " +
			"Leading = " + fm.getLeading() + ", " +
			"Height= " + fm.getHeight() + ", " + 
			"Max Ascent= " + fm.getMaxAscent() + ", " +
			"Max Descent= " + fm.getMaxDescent());
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
class MetricPanel extends Panel {
	private String 	text;

	public void setText(String text) {
		this.text = text;
	}
	public void paint(Graphics g) {
		Dimension 	size 	= getSize();
		FontMetrics fm 		= g.getFontMetrics();
		Point 		bl 		= new Point();  // bl = baseline
		int 		sw		= fm.stringWidth(text),
					ascent 	= fm.getAscent(), 
					descent = fm.getDescent(),
					leading	= fm.getLeading();

		bl.x = size.width/2 - sw/2;
		bl.y = size.height/2;

		// draw leading rectangle
		g.setColor(Color.black);
		g.fillRect(bl.x, bl.y - ascent - leading, sw, leading);

		// draw ascent rectangle
		g.setColor(Color.white);
		g.fillRect(bl.x, bl.y - ascent, sw, ascent);

		// draw descent rectangle
		g.setColor(Color.red);
		g.fillRect(bl.x, bl.y, sw, descent);

		g.setColor(getForeground());
		g.drawLine(bl.x, bl.y, bl.x + sw, bl.y);
		g.drawString(text, bl.x, bl.y);
	}
}
class FontPanel extends Panel {
	private FontPicker listTest;
	private List familyList = new List(),
	             styleList  = new List(), 
	             sizeList   = new List();

	public FontPanel(FontPicker applet) {
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
			listTest.update(getSelectedFont());
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
        String sizes[] = {"12", "14", "16", "18", 
							"24", "36", "48"};

        for(int i=0; i < sizes.length; ++i)
            sizeList.add(sizes[i]);
    }
}
