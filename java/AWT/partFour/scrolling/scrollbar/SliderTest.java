import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class SliderTest extends Applet {
	private Slider       slider       = new Slider(35,10,0,100);
	private ControlPanel controlPanel = new ControlPanel(slider);

	public void init() {
		setLayout(new BorderLayout(10,10));
		add(controlPanel, "North");
		add(slider, "Center");
	}
}
class ControlPanel extends    BorderedPanel 
                   implements AdjustmentListener {
	private Slider slider;

	private Label orientLabel  = new Label("Orientation:"),
	              valueLabel   = new Label("Value:"),
				  minLabel     = new Label("Minimum:"),
				  maxLabel     = new Label("Maximum:"),
				  visibleLabel = new Label("Visible:"),
				  unitLabel    = new Label("Unit:"),
				  blockLabel   = new Label("Block:");

	private Choice orientChoice = new Choice();

	private TextField valueField   = new TextField(5), 
	                  minField     = new TextField(5),
	                  maxField     = new TextField(5),
					  visibleField = new TextField(5),
	                  unitField    = new TextField(5),
	                  blockField   = new TextField(5);

	public void adjustmentValueChanged(AdjustmentEvent e) {
		valueField.setText(Integer.toString(slider.getValue()));
	}
	public ControlPanel(Slider aSlider) {
		slider = aSlider;
		slider.addAdjustmentListener(this);
		readSliderValues();

		GridBagLayout      gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		orientChoice.add("Horizontal");
		orientChoice.add("Vertical");

		setLayout(gbl);

		gbc.gridwidth = 1;
		gbl.setConstraints(orientLabel, gbc);
		add(orientLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(orientChoice, gbc);
		add(orientChoice);

		gbc.gridwidth = 1;
		gbl.setConstraints(valueLabel, gbc);
		add(valueLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(valueField, gbc);
		add(valueField);

		gbc.gridwidth = 1;
		gbl.setConstraints(visibleLabel, gbc);
		add(visibleLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(visibleField, gbc);
		add(visibleField);

		gbc.gridwidth = 1;
		gbl.setConstraints(minLabel, gbc);
		add(minLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(minField, gbc);
		add(minField);

		gbc.gridwidth = 1;
		gbl.setConstraints(maxLabel, gbc);
		add(maxLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(maxField, gbc);
		add(maxField);

		gbc.gridwidth = 1;
		gbl.setConstraints(unitLabel, gbc);
		add(unitLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(unitField, gbc);
		add(unitField);

		gbc.gridwidth = 1;
		gbl.setConstraints(blockLabel, gbc);
		add(blockLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(blockField, gbc);
		add(blockField);

		orientChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				int index = orientChoice.getSelectedIndex();

				if(index == 0)
					slider.setOrientation(Scrollbar.HORIZONTAL);
				else
					slider.setOrientation(Scrollbar.VERTICAL);
					
				readSliderValues();
			}
		});
		valueField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				slider.setValue(
					Integer.parseInt(valueField.getText()));
			}
		});
		visibleField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				slider.setVisibleAmount(
					Integer.parseInt(visibleField.getText()));
			}
		});
		minField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				slider.setMinimum(
					Integer.parseInt(minField.getText()));
			}
		});
		maxField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				slider.setMaximum(
					Integer.parseInt(maxField.getText()));
			}
		});
		unitField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				slider.setUnitIncrement(
					Integer.parseInt(unitField.getText()));
			}
		});
		blockField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				slider.setBlockIncrement(
					Integer.parseInt(blockField.getText()));
			}
		});
	}
	void readSliderValues() {
		String value = Integer.toString(slider.getValue()),
		       min   = Integer.toString(slider.getMinimum()),
		       max   = Integer.toString(slider.getMaximum()),
			   vis   = Integer.toString(slider.getVisibleAmount()),
			   unit  = Integer.toString(slider.getUnitIncrement()),
			   blck  = Integer.toString(slider.getBlockIncrement());

		valueField.setText  (value);
		minField.setText    (min);
		maxField.setText    (max);
		visibleField.setText(vis);
		unitField.setText   (unit);
		blockField.setText  (blck);
	}
}
