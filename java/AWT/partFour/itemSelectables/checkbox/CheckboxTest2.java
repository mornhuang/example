import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class CheckboxTest2 extends Applet {
	public void init() {
		add(new PrintRangePanel(100, 101));
	}
}
class PrintRangePanel extends Panel {
	Checkbox  printAll, printRange;
	Label     startPage, endPage;
	TextField     startField, endField;

	public PrintRangePanel(int start, int end) {
		CheckboxGroup group= new CheckboxGroup();

		printAll   = new Checkbox("Print All", false, group);
		printRange = new Checkbox("Print Range", true, group);

		startPage = new Label("Start Page:");
		endPage   = new Label("End Page:");

		startField = new TextField(Integer.toString(start));
		endField   = new TextField(Integer.toString(end));

		add(printAll);  add(printRange);
		add(startPage); add(startField);
		add(endPage);   add(endField);

		printRange.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(printRange.getState()) {
					startField.setEnabled(true);
					endField.setEnabled  (true); 
					startPage.setEnabled (true); 
					endPage.setEnabled   (true); 

					startPage.repaint();
					endPage.repaint();

					startField.requestFocus();
				}
			}
		});
		printAll.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(printAll.getState()) {
					startField.setEnabled(false);
					endField.setEnabled  (false);
					startPage.setEnabled (false);
					endPage.setEnabled   (false);

					startPage.repaint();
					endPage.repaint();
				}
			}
		});
	}
}
