import java.awt.*;
import java.awt.event.*;

public class ScrollDialog extends Dialog {
	private ScrollPane scroller;
	private Panel  buttonPanel  = new Panel();
	private Panel  controlPanel = new Panel();
	private Button doneButton   = new Button("Done");
	private Button scrollButton = new Button("Scroll");

	private Label xLabel = new Label("X:"),
	              yLabel = new Label("Y:");

	private TextField xField = new TextField(3),
	                  yField = new TextField(3);

	public ScrollDialog(ScrollPane scrollpane) {
		super(getFrame(scrollpane), "Scroll To");
		FieldListener listener = new FieldListener();
		this.scroller = scrollpane;

		GridBagLayout      gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		controlPanel.setLayout(gbl);

		gbl.setConstraints(xLabel, gbc);
		controlPanel.add(xLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(xField, gbc);
		controlPanel.add(xField);

		gbc.gridwidth = 1;
		gbl.setConstraints(yLabel, gbc);
		controlPanel.add(yLabel);

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(yField, gbc);
		controlPanel.add(yField);

		buttonPanel.add(doneButton);
		buttonPanel.add(scrollButton);

		setLayout(new BorderLayout());
		add(controlPanel, "Center");
		add(buttonPanel, "South");

		xField.addActionListener(listener);
		yField.addActionListener(listener);

		scrollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				scroll ();
			}
		});
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Point pos = scroller.getScrollPosition();
				int   x   = Integer.parseInt(xField.getText());
				int   y   = Integer.parseInt(yField.getText());

				if(pos.x != x || pos.y != y)
					scroll ();

				dispose();
			}
		});
	}
	class FieldListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			scroll();
		}
	}
	public void show() {
		pack();
		xField.requestFocus();
		super.show();
	}
	void scroll() {
		scroller.setScrollPosition( 
		    Integer.parseInt(xField.getText()),
			Integer.parseInt(yField.getText()));
	}
	static Frame getFrame(Component c) {
		Frame     frame = null;

        while((c = c.getParent()) != null) {
            if(c instanceof Frame)
                frame = (Frame)c;
        }
        return frame;
    }
}
