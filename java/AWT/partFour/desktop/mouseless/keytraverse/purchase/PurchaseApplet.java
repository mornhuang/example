import java.applet.Applet;
import java.awt.*;

public class PurchaseApplet extends Applet {
	public void init() {
		ThreeDPanel p = new ThreeDPanel();
		p.add(new ButtonPurchaseForm());
		add(p);
	}
}
class ThreeDPanel extends Panel {
	public void paint(Graphics g) {
		Dimension sz = getSize();
		g.setColor(Color.lightGray);
		g.draw3DRect(0, 0, sz.width-1, sz.height-1, true);
	}
}
class ButtonPurchaseForm extends Panel {
    Separator sep = new Separator();
    Label title   = new Label("Purchase Something Now");
    Label name    = new Label("Name:");
    Label address = new Label("Address:");
    Label payment = new Label("Purchase Method:");
    Label phone   = new Label("Phone:");
    Label city    = new Label("City:");
    Label state   = new Label("State:");

    TextField nameField    = new TextField(25);
    TextField addressField = new TextField(25);
    TextField cityField    = new TextField(15);
    TextField stateField   = new TextField(2);

    Choice    paymentChoice = new Choice();

    Button    paymentButton = new Button("Purchase");
    Button    cancelButton   = new Button("Cancel");

    public ButtonPurchaseForm() {
        GridBagLayout      gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();


        setLayout(gbl);

        paymentChoice.add("Visa");
        paymentChoice.add("MasterCard");
        paymentChoice.add("COD");

        title.setFont(new Font("Times-Roman", 
                               Font.BOLD + Font.ITALIC,
                               16));
        gbc.anchor    = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(title, gbc);

        gbc.fill      = GridBagConstraints.HORIZONTAL;
        gbc.insets    = new Insets(0,0,10,0);
        add(sep, gbc);

        gbc.anchor    = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.insets    = new Insets(0,0,0,0);
        add(name, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(nameField, gbc);

        gbc.gridwidth = 1;
        add(address, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(addressField, gbc);

        gbc.gridwidth = 1;
        add(city, gbc);

        add(cityField, gbc);

        add(state, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(stateField, gbc);

        gbc.gridwidth = 1;
        add(payment, gbc);

        gbc.insets = new Insets(5,0,5,0);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill      = GridBagConstraints.NONE;
        add(paymentChoice, gbc);

        ButtonPanel buttonPanel = new ButtonPanel();

        buttonPanel.add(paymentButton);
        buttonPanel.add(cancelButton);

        gbc.anchor    = GridBagConstraints.SOUTH;
        gbc.insets    = new Insets(5,0,0,0);
        gbc.fill      = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 4;
        add(buttonPanel, gbc);
    }
}
