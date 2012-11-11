import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class ItemExample extends Frame {
    private Checkbox  cbox      = new Checkbox("Check Me");
    private Choice    choice    = new Choice();
    private List      list      = new List();
    private CheckboxMenuItem  menuItem = 
                    new CheckboxMenuItem("Menu menuItem");

    static public void main(String args[]) {
        ItemExample f = new ItemExample();
        f.setBounds(200,200,200,200);
        f.show();
    }
    public ItemExample() {
        super("Item Example");
        MenuBar mbar = new MenuBar();
        Menu    menu = new Menu("Menu!");
        menu.add(menuItem);
        mbar.add(menu);
        setMenuBar(mbar);

        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        list.add("list 4");
        list.add("list 5");

        choice.add("choice 1");
        choice.add("choice 2");
        choice.add("choice 3");
        choice.add("choice 4");
        choice.add("choice 5");

        setLayout(new FlowLayout());
        add(cbox);
        add(list);
        add(choice);
        
        cbox.addItemListener    (new DebugItemListener());
        list.addItemListener    (new DebugItemListener());
        choice.addItemListener  (new DebugItemListener());
        menuItem.addItemListener(new DebugItemListener());

        addWindowListener(new ItemWindowListener());
    }
}

class ItemWindowListener extends WindowAdapter {
    public void windowClosing(WindowEvent event) {
        Window window = (Window)event.getSource();
        window.dispose();
        System.exit(0);
    }
}

class DebugItemListener implements ItemListener {
    public void itemStateChanged(ItemEvent event) {
        Object obj = event.getSource();
        System.out.println(obj.toString());
    }
}
