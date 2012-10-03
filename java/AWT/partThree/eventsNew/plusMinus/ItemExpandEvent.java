import java.awt.AWTEvent;

public class ItemExpandEvent extends AWTEvent {
    public static final int EXPANDED   = 1;
    public static final int CONTRACTED = 2;

    Object item;
    int expandState;

    public ItemExpandEvent(ItemExpandable source, Object item, 
	                       int expandState) {
		super(source, -1);
		this.item = item;
		this.expandState = expandState;
    }
    public ItemExpandable getItemExpandable() {
        return (ItemExpandable)source;
    }
    public Object getItem() {
        return item;
    }
    public int getExpandState() {
        return expandState;
    }
    public String paramString() {
        String s = null;
        switch(expandState) {
          case EXPANDED:   s += "EXPANDED"; break;
          case CONTRACTED: s += "CONTRACTED"; break;
          default:         s += "unknown expand state";
        }
        return super.paramString() + "[expanded=" + s + "]";
    }
}
