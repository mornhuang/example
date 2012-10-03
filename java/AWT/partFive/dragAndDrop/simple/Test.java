import java.applet.Applet;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;

public class Test extends Frame implements 
						DragGestureListener, DragSourceListener, 
											 DropTargetListener	{

	private List dragList = new List();
	private List dropList = new List();

	private DragSource dragSource = new DragSource();

	private DropTarget dropTarget = 
			new DropTarget(
				  dropList,  // Component
				  DnDConstants.ACTION_COPY_OR_MOVE,  // actions
				  this); // DropTargetListener

	DragGestureRecognizer recognizer =
				dragSource.createDefaultDragGestureRecognizer(
   					dragList,  // Component
					DnDConstants.ACTION_COPY_OR_MOVE,  // actions
					this); // DragGestureListener

	public Test() {
		super("Drag and Drop Test");

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
		f.setBounds(300,300,200,175);
		f.setVisible(true);

		f.addWindowListener (new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		System.exit(0);
        	}
        });			
	}

	// DragSourceListener implementation follows ...

	public void dragGestureRecognized(DragGestureEvent e) {
		String selectedItem = dragList.getSelectedItem();

   		e.startDrag(
				DragSource.DefaultCopyDrop,  // cursor
				new StringSelection(selectedItem), // transferable
				this); // DragSourceListener
   }
   public void dragDropEnd(DragSourceDropEvent e) {}
   public void dragEnter(DragSourceDragEvent e) {
   }
   public void dragExit(DragSourceEvent e) {}
   public void dragOver(DragSourceDragEvent e) {}
   public void dropActionChanged(DragSourceDragEvent e) {}

	// DropTargetListener implementation follows ...

	public void dragEnter(DropTargetDragEvent e) {
		e.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
	}
	public void drop(DropTargetDropEvent e) {
		try {
			if(e.isDataFlavorSupported(DataFlavor.stringFlavor)){
				Transferable tr = e.getTransferable();

				e.acceptDrop (DnDConstants.ACTION_COPY_OR_MOVE);

				String s = (String)tr.getTransferData (
										DataFlavor.stringFlavor);
				dropList.add(s);
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
	public void dragExit(DropTargetEvent e) {}
	public void dragOver(DropTargetDragEvent e) {}
	public void dropActionChanged(DropTargetDragEvent e) {}
}
