import java.applet.Applet;
import java.awt.*;

public class BulletinLayoutTest extends Applet {
    public void init() {
		add(new BulletinLayoutTestPanel(this), "Center"); 
    }
}
class BulletinLayoutTestPanel extends Panel {
	private Applet         applet;
	private Button         button;
	private Choice         choice;
	private TextField      textfield;
	private Checkbox       checkbox;
	private List           list;
	private Label          label;
	private Scrollbar      scrollbar;

	public BulletinLayoutTestPanel(Applet applet) {
		this.applet = applet;

		createComponents();
		setLayout(new BulletinLayout());

		add(button);        add(choice);
		add(textfield); 	add(checkbox);           
		add(scrollbar); 	add(list);               
		add(label);

		choice.setLocation(10,35);
		label.setLocation(230, 120);
		checkbox.setLocation(120, 120);

		textfield.setLocation(120,10);
		textfield.setSize(100,100);

		scrollbar.setLocation(120, 160);
		scrollbar.setSize(200,100);

		button.setLocation(375, 10);
		button.setSize(100,100);

		list.setLocation(230, 10);
		list.setSize(100,100);
	}
	public Insets getInsets() {
		return new Insets(10,10,10,10);
	}
	public void paint(Graphics g) {
		Dimension ps = getPreferredSize();
		Dimension ms = getMinimumSize();

		super.paint(g);
		applet.showStatus("Minimum Size:  " + ms.width + ","
		                  + ms.height + "   " + 
		                  "Preferred Size:  " + ps.width + ","
		                  + ps.height);
	}
	private void createComponents() {
		button         = new Button("A Button");
		choice         = new Choice();
		textfield      = new TextField("textfield");
		checkbox       = new Checkbox("Checkbox");
		list           = new List();
		label          = new Label("A Label");
		scrollbar      = new Scrollbar(Scrollbar.HORIZONTAL);

		choice.add("item one");
		choice.add("item two");
		choice.add("item three");
		choice.add("item four");

		list.add("list item one");
		list.add("list item two");
		list.add("list item three");
		list.add("list item four");
		list.add("list item five");
		list.add("list item six");
		list.add("list item seven");
		list.add("list item eight");
		list.add("list item nine");

		scrollbar.setValues(0,100,0,1000);
	}
}
