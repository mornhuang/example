import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class GridLabApplet extends Applet {
	public void init() {
		ButtonPanel buttonPanel = new ButtonPanel(this);
		Picker      picker      = new Picker(buttonPanel);

		setLayout(new BorderLayout());
		add(new Box(picker, "Grid Layout Settings"), "North");
		add(buttonPanel, "Center");
	}
}

class ButtonPanel extends Panel {
	private Button    one, two, three, four, five, 
	                       six, seven, eight, nine, ten;

	private Panel          panel  = new Panel();
	private TenPixelBorder border = new TenPixelBorder(panel);
	private GridLayout     gridLayout;

	public ButtonPanel(Applet applet) {
		one   = new Button("  1  ");
		two   = new Button("  2  ");
		three = new Button("  3  ");
		four  = new Button("  4  ");
		five  = new Button("  5  ");
		six   = new Button("  6  ");
		seven = new Button("  7  ");
		eight = new Button("  8  ");
		nine  = new Button("  9  ");
		ten   = new Button("  10  ");

		panel.setLayout(gridLayout = new GridLayout(3,2));
		panel.add(one);   panel.add(two);   panel.add(three); 
		panel.add(four);  panel.add(five);  panel.add(six);
		panel.add(seven); panel.add(eight); panel.add(nine);
		panel.add(ten);

		setLayout(new BorderLayout());
		add      ("Center", border);
	}
	public void updateLayout(int rows, int cols, 
	                         int hgap, int vgap) {
		try {
			gridLayout.setRows(rows);
			gridLayout.setColumns(cols);
			gridLayout.setHgap(hgap);
			gridLayout.setVgap(vgap);

			panel.invalidate();
			border.validate();
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}

class Picker extends Panel implements ItemListener {
	private Label  hgapLabel = new Label("Horizontal:");
	private Label  vgapLabel = new Label("Vertical:");
	private Label  rowLabel  = new Label("rows:");
	private Label  colLabel  = new Label("cols:");

	private Choice hchoice   = new Choice();
	private Choice vchoice   = new Choice();
	private Choice rowChoice = new Choice();
	private Choice colChoice = new Choice();

	private ButtonPanel buttonPanel;

	public Picker(ButtonPanel buttonPanel) {
		Panel rowCols = new Panel();
		Panel gaps    = new Panel();

		this.buttonPanel = buttonPanel;
		hchoice.addItem("0");  
		hchoice.addItem("5");  
		hchoice.addItem("10");
		hchoice.addItem("15"); 
		hchoice.addItem("20"); 

		vchoice.addItem("0");  
		vchoice.addItem("5");  
		vchoice.addItem("10");
		vchoice.addItem("15"); 
		vchoice.addItem("20");

		rowChoice.addItem("0"); 
		rowChoice.addItem("1"); 
		rowChoice.addItem("2");
		rowChoice.addItem("3"); 
		rowChoice.addItem("4"); 
		rowChoice.addItem("5");
		rowChoice.select (3);

		colChoice.addItem("0"); 
		colChoice.addItem("1"); 
		colChoice.addItem("2");
		colChoice.addItem("3"); 
		colChoice.addItem("4"); 
		colChoice.addItem("5");
		colChoice.select (2);

		rowCols.add(rowLabel); 
		rowCols.add(rowChoice);
		rowCols.add(colLabel); 
		rowCols.add(colChoice);

		gaps.add(hgapLabel);   
		gaps.add(hchoice);
		gaps.add(vgapLabel);   
		gaps.add(vchoice);

		hchoice.addItemListener(this);
		vchoice.addItemListener(this);
		rowChoice.addItemListener(this);
		colChoice.addItemListener(this);

		add(new Box(rowCols, "Rows and Columns"));
		add(new Box(gaps,    "Horizontal and Vertical Gaps"));
	}
	public void itemStateChanged(ItemEvent event) {
		int rows, cols, hgap, vgap;

		rows = Integer.parseInt(rowChoice.getSelectedItem());
		cols = Integer.parseInt(colChoice.getSelectedItem());
		hgap = Integer.parseInt(hchoice.getSelectedItem());
		vgap = Integer.parseInt(vchoice.getSelectedItem());

		buttonPanel.updateLayout(rows, cols, hgap, vgap);
	}
}
