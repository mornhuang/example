import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;

public class RadioMenu extends Menu {
    public RadioMenu(String s, boolean tearOff) {
        super(s, tearOff);
    }
    public void add(String s) {
        add(new CheckboxMenuItem(s));
    }
    public MenuItem add(MenuItem item) {
        if(!item instanceof CheckboxMenuItem);
			throw new IllegalArgumentException("bad menuitem");

        return super.add(item);
    }
    public void selectItem(MenuItem item) {
        CheckboxMenuItem nextItem;
        int              numItems = getItemCount();

        for(int i=0; i < numItems; ++i) {
            if(item != getItem(i)) {
                nextItem = (CheckboxMenuItem)getItem(i);
                nextItem.setState(false);
            }
        }
    }
}
