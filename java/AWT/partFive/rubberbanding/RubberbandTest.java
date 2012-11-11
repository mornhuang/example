import java.awt.*;
import java.awt.event.*;

public class RubberbandTest extends Frame {
    public RubberbandTest() {
		super("Rubberband Test");
		setLayout(new BorderLayout());
        add(new RubberbandTestPanel(), "Center");
    }
	public static void main(String args[]) {
		final Frame dbt = new RubberbandTest();
		dbt.setBounds(100,100,600,600);
		dbt.setVisible(true);

		dbt.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dbt.dispose();
				System.exit(0);
			}
		});
	}
}
class RubberbandTestPanel extends RubberbandPanel {
	ColorChoice colorChoice	= new ColorChoice();
	Choice		rbChoice	= new Choice();
	Rubberband 	linerb 		= new RubberbandLine(), 
				ellipserb 	= new RubberbandEllipse(), 
				rectanglerb	= new RubberbandRectangle();	

	public RubberbandTestPanel() {
		setRubberband(linerb);
		setForeground(Color.black);

		rbChoice.add("line");
		rbChoice.add("ellipse");
		rbChoice.add("rectangle");

		setLayout(new FlowLayout());
		add(rbChoice);
		add(colorChoice);

		rbChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				int index = rbChoice.getSelectedIndex();
				switch(index) {
					case 0:	setRubberband(linerb); break;
					case 1: setRubberband(ellipserb); break;
					case 2: setRubberband(rectanglerb); break;
				}
			}
		});
		colorChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				setForeground(colorChoice.getColor());
			}
		});
	}
	public void rubberbandEnded(Rubberband rb) {
		Graphics g = getGraphics();

		if(g != null) {
			Point anchor = rb.getAnchor(), end = rb.getEnd();
			int w = Math.abs(anchor.x - end.x);
			int h = Math.abs(anchor.y - end.y);

			g.setColor(getForeground());

			if(rb == linerb) 
				g.drawLine(anchor.x, anchor.y, end.x, end.y);
			else if(rb == ellipserb) 
				g.drawOval(anchor.x, anchor.y, w, h);
			else 
				g.drawRect(anchor.x, anchor.y, w, h);

			g.dispose();
		}
	}
	public void update(Graphics g) {
		paint(g);
	}
}
class ColorChoice extends Choice {
	private String colorNames[] = {
						"black", "blue", "cyan", "darkGray",
						"gray", "green", "lightgray", "magenta",
						"orange", "pink", "red", "white",
						"yellow" };

	private Color colors[] = {Color.black,     Color.blue,
							  Color.cyan,      Color.darkGray,
							  Color.gray,      Color.green,
							  Color.lightGray, Color.magenta,
							  Color.orange,    Color.pink,
							  Color.red,       Color.white,
							  Color.yellow };
	public ColorChoice() {
		for(int i=0; i < colors.length; ++i) {
			add(colorNames[i]);
		}
	}
	public Color getColor() {
		return colors[getSelectedIndex()];
	}
	public void setColor(Color color) {
		for(int i=0; i < colors.length; ++i) {
			if(colors[i].equals(color)) {
				select(i);
				break;
			}
		}
	}
}
