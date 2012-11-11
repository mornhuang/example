import java.awt.*;
import java.awt.event.*;

public class RadioMenuTest extends FrameWithMenuBar {
    private RadioMenu         radioMenu;
	private MenuItem          quitItem;
    private CheckboxMenuItem  stItem, javaItem, cppItem,
						      eiffelItem, lispItem;

    public static void main(String args[]) {
        RadioMenuTest test = 
            new RadioMenuTest("FileEdit Menu Test");

        test.setBounds(300,300,300,100);
        test.show();
    }
    public RadioMenuTest(String s) {
        super(s);
    }
    public void createMenus(MenuBar mbar) {
        mbar.add(createFileMenu());
        mbar.add(createRadioMenu());
    }
    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File"); 
        fileMenu.add(quitItem = new MenuItem("Quit"));

		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
				System.exit(0);
			}
		});
        return fileMenu;
    }
    private Menu createRadioMenu() {
		CheckboxItemListener checkboxItemListener = 
							new CheckboxItemListener();
        radioMenu = new RadioMenu("Radio Menu", true); 

        stItem = new CheckboxMenuItem("Smalltalk");
        javaItem = new CheckboxMenuItem("Java");
        cppItem = new CheckboxMenuItem("C++");
        eiffelItem = new CheckboxMenuItem("Eiffel");
        lispItem = new CheckboxMenuItem("Lisp");

		radioMenu.add(stItem);
		radioMenu.add(javaItem);
		radioMenu.add(cppItem);
		radioMenu.add(eiffelItem);
		radioMenu.add(lispItem);

		stItem.addItemListener(checkboxItemListener);
		javaItem.addItemListener(checkboxItemListener);
		cppItem.addItemListener(checkboxItemListener);
		eiffelItem.addItemListener(checkboxItemListener);
		lispItem.addItemListener(checkboxItemListener);

        return radioMenu;
    }
	class CheckboxItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			CheckboxMenuItem item = 
				(CheckboxMenuItem)event.getSource();

			radioMenu.selectItem(item);
		}
	}
}
