import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ChoiceTest2 extends Applet {
	private TextField addField     = new TextField(20);
	private TextField removeField  = new TextField(20);
	private Choice    choice       = new Choice();
	private Button    addButton    = new Button("Add Item");
	private Button    removeButton = new Button("Remove Item");

	public void init() {
		Panel north  = new Panel();
		Panel center = new Panel();

		north.add(choice);

		center.add(removeField);
		center.add(removeButton);
		center.add(addField);
		center.add(addButton);

		setLayout(new BorderLayout());
		add(north, "North");
		add(center, "Center");

		choice.add("Item One");
		choice.add("Item Two");
		choice.add("Item Three");

		removeField.setText(choice.getItem(0));

		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				removeField.setText(choice.getSelectedItem());
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				choice.add(addField.getText());
			}
		});
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String fieldStr = removeField.getText();

				if( ! isValidItem(fieldStr)) {
					ChoiceTest2.this.showStatus("That's cheating!");
					removeField.setEnabled(false);
				}
				else if(choice.getItemCount() > 1) {
					choice.remove(removeField.getText());

					Object[] objs = choice.getSelectedObjects();
					removeField.setText((String)objs[0]);
				}
			}
		});
	}
	boolean isValidItem(String string) {
		int numItems = choice.getItemCount();
		for(int i=0; i < numItems; ++i) {
			if(choice.getItem(i).equals(string))
				return true;
		}
		return false;
	}
}
