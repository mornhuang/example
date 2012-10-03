import java.awt.*;
import java.awt.event.*;

public class DbgContainerListener implements 
                                  ContainerListener {
	public void componentAdded(ContainerEvent event) {
		System.out.println(event.toString());
	}
	public void componentRemoved(ContainerEvent event) {
		System.out.println(event.toString());
	}
}
