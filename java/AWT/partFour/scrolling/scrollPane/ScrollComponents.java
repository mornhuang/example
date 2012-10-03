import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ScrollComponents extends Applet {
	private ScrollPane scroller;
	private ManyLabelsPanel labels;
	private ManyButtonsPanel buttons;

    public void init() {
		setCursor(
			Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		labels 	= new ManyLabelsPanel();
		buttons	= new ManyButtonsPanel();
		scroller = new ScrollPane();

		scroller.add(labels);

		setLayout(new BorderLayout());
		add(new TogglePanel(scroller, labels, buttons), "North");
		add(scroller, "Center");
    }
	public void start() {
		setCursor(
			Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
class TogglePanel extends Panel {
	Button  toggleButton = new Button("Toggle Component");
	Button  scrollButton = new Button("Scroll To ...");
	ScrollDialog 		dialog;
	ScrollPane 			scroller;
	ManyButtonsPanel 	buttons;
	ManyLabelsPanel     labels;
	
	public TogglePanel(ScrollPane scrollpane,
	                   ManyLabelsPanel store,
					   ManyButtonsPanel panel) {
		this.labels = store;
		this.buttons    = panel;
		this.scroller   = scrollpane;

		add(toggleButton);
		add(scrollButton);

		scrollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Point loc = TogglePanel.this.getLocationOnScreen();

				if(dialog ==null) {
					dialog = new ScrollDialog(scroller);
				}
				dialog.setLocation(loc.x, loc.y);
				dialog.show();
			}
		});	

		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(scroller.getComponent(0) == labels) 
					scroller.add(buttons);
				else 
					scroller.add(labels);

				scroller.getParent().validate();
			}
		});
	}
}
class ManyButtonsPanel extends Panel {
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public ManyButtonsPanel() {
		setLayout(gbl);

		for(int i=0; i < 50; ++i) {
			if(i != 0 && i % 5 == 0) 
				gbc.gridwidth = GridBagConstraints.REMAINDER;
			else
				gbc.gridwidth = 1;

			add(new Button("button number " + i), gbc);
		}
	}
}
class ManyLabelsPanel extends Panel {
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public ManyLabelsPanel() {
		setLayout(gbl);

		for(int i=0; i < 100; ++i) {
			if(i != 0 && i % 10 == 0) 
				gbc.gridwidth = GridBagConstraints.REMAINDER;
			else
				gbc.gridwidth = 1;

			add(new Label("label number " + i), gbc);
		}
	}
}
