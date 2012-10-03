import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuBar;

public class MenuBarPrinter {
    static public void print(MenuBar menubar) {
        int      numMenus = menubar.getMenuCount();
        Menu     nextMenu;
        MenuItem nextItem;

        System.out.println();
        System.out.println("MenuBar has "        +  
                            menubar.getMenuCount() + 
                            " menus");
        System.out.println();

        for(int i=0; i < numMenus; ++i) {
            nextMenu = menubar.getMenu(i);
            System.out.println(nextMenu);

            int numItems = nextMenu.getItemCount();

            for(int j=0; j < numItems; ++j) {
                nextItem = nextMenu.getItem(j);
                System.out.println(nextItem);
            }
            System.out.println();
        }
    }
}
