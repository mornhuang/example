import java.applet.Applet;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.io.*;

public class Test extends Frame {
	public Test() {
		super("Drag and Drop Test");

		Label dragLabel = new Label("drag me");
		Label dropLabel = new Label("to here");

		StringDragSource dragSource = 
								new StringDragSource(dragLabel);

		StringDropTarget dropTarget = 
								new StringDropTarget(dropLabel);

		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

		add(dragLabel);
		add(dropLabel);
	}
	public static void main(String[] args) {
		Frame f = new Test();
		f.setBounds(300,300,250,300);
		f.setVisible(true);

		f.addWindowListener (new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		System.exit(0);
        	}
        });			
	}
}
class StringDragSource 
		implements DragSourceListener, DragGestureListener {
	Component component;
	DragSource dragSource;
	DragGestureRecognizer recognizer;

	public StringDragSource(Component component) {
		this.component = component;
   		dragSource = new DragSource();
   		recognizer = 
			dragSource.createDefaultDragGestureRecognizer(
   					component,  // Component
					DnDConstants.ACTION_COPY_OR_MOVE,  // actions
					this); // DragGestureListener
	}
	public void dragGestureRecognized(DragGestureEvent e) {
		if(component instanceof Label) {
			String s = ((Label)component).getText();
			StringSelection text = new StringSelection(s);

			// startDrag is asyncronous
   			dragSource.startDrag(
   							e, 
							DragSource.DefaultCopyDrop,  // cursor
							text, // transferable
							this); // DragSourceListener
			
		}
   }

   public void dragDropEnd(DragSourceDropEvent e) {}
   public void dragEnter(DragSourceDragEvent e) {}
   public void dragExit(DragSourceEvent e) {}
   public void dragOver(DragSourceDragEvent e) {}
   public void dropActionChanged(DragSourceDragEvent e) {}
}
class StringDropTarget implements DropTargetListener {
	Component component;
	DropTarget dropTarget;

	public StringDropTarget(Component component) {
		this.component = component;
 		dropTarget = new DropTarget(component, 
							DnDConstants.ACTION_COPY_OR_MOVE,
							this); 
	}
	public void dragEnter(DropTargetDragEvent e) {
		e.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
	}
	public void drop(DropTargetDropEvent e) {
		try {
			Transferable tr = e.getTransferable();

			if(tr.isDataFlavorSupported(DataFlavor.stringFlavor)){
				e.acceptDrop (DnDConstants.ACTION_COPY_OR_MOVE);
				String s = (String)tr.getTransferData (
										DataFlavor.stringFlavor);

				if(component instanceof Label) {
					((Label)component).setText(s);
					e.getDropTargetContext().dropComplete(true);
				}
			} else {
				e.rejectDrop();
			}
		} 
		catch (IOException io) {
			io.printStackTrace();
		} 
		catch (UnsupportedFlavorException ufe) {
			ufe.printStackTrace();
		}
		finally {
			e.rejectDrop();
		}
	}
	public void dragExit (DropTargetEvent e) { }
	public void dragOver (DropTargetDragEvent e) { }
	public void dropActionChanged (DropTargetDragEvent e) { }
}
