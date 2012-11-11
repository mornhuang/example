import java.applet.Applet;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;

public class Test extends Frame {
	public Test() {
		super("Drag and Drop Test");

		ListDragSource dragList = new ListDragSource();

		List dropList = new List();
		ListDropTarget dropTarget = new ListDropTarget(dropList);

		dragList.add("one");
		dragList.add("two");
		dragList.add("three");
		dragList.add("four");
		dragList.add("five");
		dragList.add("six");

		setLayout(new GridLayout(2,0));
		add(new Label("Drag Source"));
		add(dragList);
		add(new Label("Drop Target"));
		add(dropList);
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

// ListDropTarget delegates to an instance of java.awt.List

class ListDropTarget implements DropTargetListener {
	private List list;
	private DropTarget dropTarget;

	public ListDropTarget(List list) {
		this.list = list;
		dropTarget = new DropTarget(
					list, // component
					DnDConstants.ACTION_COPY_OR_MOVE, // actions
					this); // DropTargetListener
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
				list.add(s);
				e.dropComplete(true);
			} else {
				e.rejectDrop();
			}
		} 
		catch (IOException io) {
			io.printStackTrace();
			e.rejectDrop();
		} 
		catch (UnsupportedFlavorException ufe) {
			ufe.printStackTrace();
			e.rejectDrop();
		}
	}
	public void dragExit (DropTargetEvent e) { }
	public void dragOver (DropTargetDragEvent e) { }
	public void dropActionChanged (DropTargetDragEvent e) { }
}

// ListDragSource is a list that contains a drag source

class ListDragSource extends List
		implements DragSourceListener, DragGestureListener {
	DragSource dragSource;
	DragGestureRecognizer recognizer;

	public ListDragSource() {
   		dragSource = new DragSource();
   		recognizer = 
			dragSource.createDefaultDragGestureRecognizer(
   					this,  // Component
					DnDConstants.ACTION_COPY_OR_MOVE,  // actions
					this); // DragGestureListener
	}
	public void dragGestureRecognized(DragGestureEvent e) {
		String item = getSelectedItem();
   		e.startDrag(
				DragSource.DefaultCopyDrop,  // cursor
				new StringSelection(item), // transferable
				this); // DragSourceListener
   }
   public void dragDropEnd(DragSourceDropEvent e) {}
   public void dragEnter(DragSourceDragEvent e) {}
   public void dragExit(DragSourceEvent e) {}
   public void dragOver(DragSourceDragEvent e) {}
   public void dropActionChanged(DragSourceDragEvent e) {}
}
