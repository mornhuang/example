import java.awt.*;
import java.util.Hashtable;

/**
 * Lays out components as though they were pinned to 
 * a bulletin board, meaning:<p>
 *
 * Components are moved to the location that was set by calling
 * or setBounds().
 *
 * Components are sized according to the size that was set
 * by calls to either setSize() or setBounds().  If either the
 * width or height of the component is 0, the component is
 * sized to its preferred size.
 *
 * AWT Caveats:
 *
 * Buttons, Labels, Checkboxes and Choices do not take kindly
 * to being resized - it is better to let them take on their
 * preferred sizes instead.
 *
 * Lists must be moved and shaped only after their peers are
 * created.
 *
 * Choices can only be moved after their peers are created.
 *
 * @author  David Geary
 * @seeAlso BulletinLayoutTest
 */
public class BulletinLayout implements LayoutManager {
	private Hashtable hash = new Hashtable();

    public void addLayoutComponent(String s, Component comp) {
    }
    public void removeLayoutComponent(Component comp) {
    }
    public Dimension preferredLayoutSize(Container target) {
        Insets    insets      = target.getInsets();
        Dimension dim         = new Dimension(0,0);
        int       ncomponents = target.getComponentCount();
        Component comp;
        Dimension d;
        Rectangle size = new Rectangle(0,0);
        Rectangle compSize;

        for (int i = 0 ; i < ncomponents ; i++) {
            comp = target.getComponent(i);

            if(comp.isVisible()) {
				d = comp.getSize();
                compSize = new Rectangle(comp.getLocation());
                compSize.width  = d.width;
                compSize.height = d.height;

                size = size.union(compSize);
            }
        }
        dim.width  += size.width + insets.right;
        dim.height += size.height + insets.bottom;

        return dim;
    }
    public Dimension minimumLayoutSize(Container target) {
        Insets    insets      = target.getInsets();
        Dimension dim         = new Dimension(0,0);
        int       ncomponents = target.getComponentCount();
        Component comp;
        Dimension d;
        Rectangle minBounds = new Rectangle(0,0);
        Rectangle compMinBounds;

        for (int i = 0 ; i < ncomponents ; i++) {
            comp = target.getComponent(i);

            if(comp.isVisible()) {
                d = comp.getMinimumSize();
                compMinBounds = 
					new Rectangle(comp.getLocation());
				compMinBounds.setSize(d.width, d.height);

                minBounds = minBounds.union(compMinBounds);
            }
        }
        dim.width  += minBounds.width  + insets.right;
        dim.height += minBounds.height + insets.bottom;

        return dim;
    }
    public void layoutContainer(Container target) {
        Insets    insets      = target.getInsets();
        int       ncomponents = target.getComponentCount();
        Component comp;
        Dimension sz, ps;
        Point loc;

        for (int i = 0 ; i < ncomponents ; i++) {
            comp = target.getComponent(i);

            if(comp.isVisible()) {
                sz   = comp.getSize();    
				ps   = comp.getPreferredSize();
                loc  = getComponentLocation(comp);

				if(sz.width < ps.width || sz.height < ps.height)
					sz = ps;

                comp.setBounds(loc.x, loc.y, sz.width, sz.height);
            }
        }
    }
	private Point getComponentLocation(Component comp) {
		Insets insets = comp.getParent().getInsets();
		Point  loc    = comp.getLocation();

		if( ! hash.containsKey(comp)) {
			addComponentToHashtable(comp);
		}
		else {
			Point oldLocation = (Point)hash.get(comp);

			if(oldLocation.x != loc.x ||
			   oldLocation.y != loc.y) {
			   	addComponentToHashtable(comp);
			}
		}
		return comp.getLocation();
	}
	private void addComponentToHashtable(Component comp) {
		Insets insets = comp.getParent().getInsets();
		Point  loc    = comp.getLocation();

		comp.setLocation(loc.x + insets.left, 
			             loc.y + insets.top);
		hash.put(comp, comp.getLocation());
	}
}
