import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class RowLayoutApplet extends Applet {
 	private RowButtonPanel buttonPanel;

	public void init() {
		setLayout(new BorderLayout());
		add(buttonPanel = new RowButtonPanel(), "Center");
		add(new Box(new RowPicker(buttonPanel),
		                     "Row Layout Settings"), "North");
	}
}
class RowButtonPanel extends Panel {
	private Button    one, two, three;
	private Panel          panel  = new Panel();
	private TenPixelBorder border = new TenPixelBorder(panel);

	public RowButtonPanel() {
		one   = new Button("  1  ");
		two   = new Button("  2  ");
		three = new Button("  3  ");

		panel.setLayout(new RowLayout(0));
		panel.add(one);   
		panel.add(two);   
		panel.add(three); 

		setLayout(new BorderLayout());
		add      (border, "Center");
	}
	public void updateOrientations(Orientation horient, 
	                               Orientation vorient,
	                               int gap) {
		panel.setLayout(new RowLayout(horient, vorient, gap));
		border.validate();
	}
}
class RowPicker extends Panel implements ItemListener {
	private Label  horientLabel = new Label("Horizontal:");
	private Label  vorientLabel = new Label("Vertical:");
	private Label  gapLabel     = new Label("Gap:");

	private Choice hchoice   = new Choice();
	private Choice vchoice   = new Choice();
	private Choice gapChoice = new Choice();

	private RowButtonPanel buttonPanel;

	public RowPicker(RowButtonPanel buttonPanel) {
		Panel orientations = new Panel();
		Panel gap          = new Panel();

		this.buttonPanel = buttonPanel;

		hchoice.addItemListener(this);
		hchoice.addItem("left");
		hchoice.addItem("center");
		hchoice.addItem("right");
		hchoice.select(1);

		vchoice.addItemListener(this);
		vchoice.addItem("top");
		vchoice.addItem("center");
		vchoice.addItem("bottom");
		vchoice.select(1);

		gapChoice.addItemListener(this);
		gapChoice.addItem("0");
		gapChoice.addItem("5");
		gapChoice.addItem("10");
		gapChoice.addItem("15");
		gapChoice.addItem("20");

		orientations.add(horientLabel);  
		orientations.add(hchoice);
		orientations.add(vorientLabel);  
		orientations.add(vchoice);

		gap.add(gapLabel);               
		gap.add(gapChoice);

		add(new Box(orientations, "Orientations"));
		add(new Box(gap,          "Gap"));
	}
	public void itemStateChanged(ItemEvent event) {
		String horient, vorient;
		int    gap;

		horient = hchoice.getSelectedItem();
		vorient = vchoice.getSelectedItem();
		gap     = Integer.parseInt(gapChoice.getSelectedItem());

		buttonPanel.updateOrientations(
					Orientation.fromString(horient), 
		            Orientation.fromString(vorient), gap);
	}
}
