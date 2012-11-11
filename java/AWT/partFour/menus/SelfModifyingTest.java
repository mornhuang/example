import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class SelfModifyingTest extends FrameWithMenuBar {
    private SelfModifyingMenu selfModifyingMenu;
    private MenuItem quitItem;

    public static void main(String args[]) {
        Frame frame = 
            new SelfModifyingTest("SelfModifying Menu Test");

        frame.setBounds(100,100,300,100);
        frame.show();
    }
    public SelfModifyingTest(String s) { 
        super(s); 
    }
    public void createMenus(MenuBar mbar) {
        mbar.add(createFileMenu());
        mbar.add(selfModifyingMenu = new SelfModifyingMenu());
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
}
class SelfModifyingMenu extends Menu {
    private Vector   newItems = new Vector();
	private MenuItemListener menuItemListener = 
						new MenuItemListener();
    private MenuItem toggleItem, enablerItem,
                     addItem, removeItem;

    public SelfModifyingMenu() {
        super("Self Modifying Menu", true);

        add(enablerItem = new MenuItem("Disable Item Below"));
        add(toggleItem  = new MenuItem("Enable/Disable Me"));
        addSeparator();

        add(addItem    = new MenuItem("Add a MenuItem ..."));
        add(removeItem = new MenuItem("Remove a MenuItem ..."));
        addItem.setFont(new Font("TimesRoman", Font.BOLD, 12));
        addSeparator();

		enablerItem.addActionListener(menuItemListener);
		toggleItem.addActionListener(menuItemListener);
		addItem.addActionListener(menuItemListener);
		removeItem.addActionListener(menuItemListener);
    }
    public void addItem() {
        MenuItem newItem = 
            new MenuItem("Extra Item #" + newItems.size());
        
        add(newItem);
        newItems.addElement(newItem);
    }
    public void removeLastItem() {
        if(newItems.size() == 0)
            System.out.println("Hey, nothing to remove!");
        else {
            MenuItem removeMe = 
                (MenuItem)newItems.lastElement();

            remove(removeMe);
            newItems.removeElement(removeMe);
        }
    }
    public void toggleItem() {
        if(toggleItem.isEnabled()) toggleItem.setEnabled(false);
        else                       toggleItem.setEnabled(true);
    }
	class MenuItemListener implements ActionListener {
    	public void actionPerformed(ActionEvent event) {
			MenuItem item = (MenuItem)event.getSource();

    	    if(item == enablerItem) {
    	        toggleItem();

    	        if(toggleItem.isEnabled()) 
    	            enablerItem.setLabel("Disable Item Below");
    	        else
    	            enablerItem.setLabel("Enable Item Below");
    	    }
    	    else if(item == addItem)    addItem();
    	    else if(item == removeItem) removeLastItem();
    	}
	}
}
