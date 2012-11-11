import java.applet.Applet;
import java.awt.*;

public class PlusMinusTest extends    Applet 
                           implements ItemExpandListener {
	public void init() {
		PlusMinus pm = new PlusMinus();
		pm.addExpandListener(this);
		add(pm);
	}
	public void itemExpandStateChanged(ItemExpandEvent event) {
		ItemExpandable ie = event.getItemExpandable();
		Object[]       items = ie.getExpandedItems();

		if(items != null) showStatus("Expanded!");
		else              showStatus("Contracted!");
	}
}
