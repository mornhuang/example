import java.awt.*;
import java.awt.event.*;

public class ShortcutTest extends Frame {
	private MenuItem quitItem;

    public static void main(String args[]) {
        ShortcutTest test = 
            new ShortcutTest("FileEdit Menu Test");

        test.setBounds(300,300,300,300);
        test.show();
    }
    public ShortcutTest(String s) {
        super(s);

		MenuBar mbar = new MenuBar();

        mbar.add(createFileMenu());
        mbar.add(createEditMenu());
		setMenuBar(mbar);

		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
				System.exit(0);
			}
    	});
	}
    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File"); 
        fileMenu.add(quitItem = new MenuItem("Quit"));
        return fileMenu;
    }
    private Menu createEditMenu() {
        Menu editMenu = new Menu("Edit"); 

		MenuShortcut copyShortcut  = new MenuShortcut('c');
		MenuShortcut cutShortcut   = new MenuShortcut('x');
		MenuShortcut pasteShortcut = new MenuShortcut('p');

		MenuItemListener itemListener = new MenuItemListener();
		MenuItem cutItem  = new MenuItem("Cut",   cutShortcut), 
		         copyItem = new MenuItem("Copy",  copyShortcut), 
		         pasteItem= new MenuItem("paste", pasteShortcut);

		cutItem.addActionListener  (itemListener);
		copyItem.addActionListener (itemListener);
		pasteItem.addActionListener(itemListener);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        return editMenu;
    }
}
class MenuItemListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		MenuItem item = (MenuItem)event.getSource();
		System.out.println(item.getLabel());
	}
}
