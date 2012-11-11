import java.applet.Applet;
import java.awt.*;

public class ComponentApplet extends Applet {
	private DbgComponentListener compListener =
								 new DbgComponentListener();
	private DbgKeyListener keyListener = 
	                             new DbgKeyListener();
	private DbgMouseListener mouseListener = 
	                             new DbgMouseListener();
	private DbgMouseMotionListener mouseMotionListener =
								 new DbgMouseMotionListener();
	private DbgFocusListener     focusListener =
								 new DbgFocusListener();
	public void init() {
		ScrollbarPanel sbarPanel;
		Panel     checkboxPanel;
		Label     label     = new Label("A Label");
		Canvas    canvas    = new TestCanvas();
		Panel     panel     = new TestPanel();
		Button    button    = new Button("A Button");
		TextField tf        = new TextField("A Textfield");
		TextArea  ta	    = new TextArea("TextArea", 5, 20);
		List      list      = new List(10, true);
		Choice    choice    = new Choice();

		addDebugListeners(label);
		addDebugListeners(canvas);
		addDebugListeners(panel);
		addDebugListeners(button);
		addDebugListeners(tf);
		addDebugListeners(ta);
		addDebugListeners(list);
		addDebugListeners(choice);
		addDebugListeners(label);
		addDebugListeners(label);

		checkboxPanel = new CheckboxPanel();
		sbarPanel     = new ScrollbarPanel();

		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("Four");
		list.add("Five");
		list.add("Six");
		list.add("Seven");
		list.add("Eight");
		list.add("Nine");
		list.add("Ten");
		list.add("Eleven");
		list.add("Twelve");
		list.add("Thirteen");
		list.add("Fourteen");
		list.add("Fiveteen");
		list.add("Sixteen");
		list.add("Seventeen");
		list.add("Eightteen");
		list.add("Nineteen");
		list.add("Twenty");

		choice.add("Buffalo Sabres");
		choice.add("Boston Bruins");
		choice.add("Colorado Avalanche");
		choice.add("Florida Panthers");
		choice.add("New Jersey Devils");
		choice.add("Philadelphia Flyers");
		choice.add("San Jose Sharks");

		Panel scrollMe = new ScrollMe();
		scrollMe.setLayout(
			new FlowLayout(FlowLayout.CENTER, 10, 10));

		scrollMe.add(canvas);
		scrollMe.add(panel);
		scrollMe.add(button);
		scrollMe.add(tf);
		scrollMe.add(ta);
		scrollMe.add(list);
		scrollMe.add(choice);
		scrollMe.add(checkboxPanel);
		scrollMe.add(label);
		scrollMe.add(sbarPanel);

		ScrollPane scroller = new ScrollPane();
		scroller.add(scrollMe);
		setLayout(new BorderLayout());
		add("Center", scroller);
	}
	private void addDebugListeners(Component comp) {
		comp.addComponentListener(compListener);
		comp.addKeyListener(keyListener);
		comp.addMouseListener(mouseListener);
		comp.addMouseMotionListener(mouseMotionListener);
		comp.addFocusListener(focusListener);
	}
}
class ScrollMe extends Panel {
	public Dimension getPreferredSize() {
		return new Dimension(500,700);
	}
}
class CheckboxPanel extends Panel {
	private DbgComponentListener compListener =
								 new DbgComponentListener();
	private DbgKeyListener keyListener = 
	                             new DbgKeyListener();
	private DbgMouseListener mouseListener = 
	                             new DbgMouseListener();
	private DbgMouseMotionListener mouseMotionListener =
								 new DbgMouseMotionListener();
	public CheckboxPanel() {
		Panel checkboxPanel = new Panel();
		CheckboxGroup group = new CheckboxGroup();
		Checkbox      cbox1, cbox2, cbox3;

		checkboxPanel.setLayout(new GridLayout(3,0));
		checkboxPanel.add(
			cbox1 = new Checkbox("bordered",    group, false));
		checkboxPanel.add(
			cbox2 = new Checkbox("transparent", group, false));
		checkboxPanel.add(
			cbox3 = new Checkbox("continuous",  group, true));

		addDebugListeners(cbox1);
		addDebugListeners(cbox2);
		addDebugListeners(cbox3);

		setLayout(new BorderLayout(10,10));
		add("Center", checkboxPanel);
	}
	public void paint(Graphics g) {
		g.drawRect(0,0,getSize().width-1,getSize().height-1);
	}
	private void addDebugListeners(Component comp) {
		comp.addComponentListener(compListener);
		comp.addKeyListener(keyListener);
		comp.addMouseListener(mouseListener);
		comp.addMouseMotionListener(mouseMotionListener);
	}
}
class TestPanel extends Panel {
	public void paint(Graphics g) {
		FontMetrics fm   = g.getFontMetrics();
		Dimension   size = getSize();
		String      str  = "A Panel";

		g.setColor(Color.red);
		g.drawRect(0,0,size.width-1,size.height-1);
		g.drawString(str, 
				     (size.width/2) - (fm.stringWidth(str)/2),
		             (size.height/2) - (fm.getHeight()/2) + 
					  fm.getAscent());
	}
	public Dimension getPreferredSize() {
		return new Dimension(100,100);
	}
}
class TestCanvas extends Canvas {
	public void paint(Graphics g) {
		FontMetrics fm   = g.getFontMetrics();
		Dimension   size = getSize();
		String      str  = "A Canvas";

		g.drawRect(0,0,size.width-1,size.height-1);
		g.drawString(str, 
				     (size.width/2) - (fm.stringWidth(str)/2),
		             (size.height/2) - (fm.getHeight()/2) + 
					  fm.getAscent());
	}
	public Dimension getPreferredSize() {
		return new Dimension(100,100);
	}
}
class ScrollbarPanel extends Panel {
	private Scrollbar sbar = new Scrollbar(Scrollbar.HORIZONTAL);

	public ScrollbarPanel() {
		setLayout(new BorderLayout(10,10));
		add("Center", sbar);
	}
	public Scrollbar getScrollbar() {
		return sbar;
	}
	public Dimension getPreferredSize() {
		return new Dimension(200,20);
	}
}
