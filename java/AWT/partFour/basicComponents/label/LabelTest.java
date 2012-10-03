import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class LabelTest extends Applet implements ItemListener {
	CheckboxGroup group  = new CheckboxGroup();
	Checkbox left   = new Checkbox("left",   true,  group);
	Checkbox right  = new Checkbox("right",  false, group);
	Checkbox center = new Checkbox("center", false, group);
	Label    label  = new SelectableLabel("Selectable Label");

	public void init() {
		Panel checkboxPanel = new Panel();

		left.addItemListener(this);
		right.addItemListener(this);
		center.addItemListener(this);

		checkboxPanel.add(left);
		checkboxPanel.add(center);
		checkboxPanel.add(right);

		setLayout(new BorderLayout(10,10));
		add(label, "North");
		add(checkboxPanel, "Center");
	}
	public void itemStateChanged(ItemEvent event) {
		Checkbox cbox = (Checkbox)event.getSource();
		if(cbox == left)   label.setAlignment(Label.LEFT);
		if(cbox == right)  label.setAlignment(Label.RIGHT);
		if(cbox == center) label.setAlignment(Label.CENTER);
	}
}
class SelectableLabel extends Label {
	public SelectableLabel(String label) {
		super(label);

		addComponentListener(new DbgComponentListener());
		addMouseListener(new DbgMouseListener());
		addMouseMotionListener(new DbgMouseMotionListener());

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				Color foreground = getForeground();
				setForeground(getBackground());
				setBackground(foreground);
			}
		});
	}
}
