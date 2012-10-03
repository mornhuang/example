import java.applet.Applet;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.*;

public class Test extends Frame {
	public Test() {
		super("Drag and Drop Test");

		List list = new List();
		Label label = new Label(
							"drag an entry from the list here");

		StringDragSource source = new StringDragSource(
					list, "getSelectedItem", new Class[]{});

		StringDropTarget target = new StringDropTarget(
					label, "setText", new Class[]{String.class});

		list.add("item 1");
		list.add("item 2");
		list.add("item 3");
		list.add("item 4");
		list.add("item 5");
		list.add("item 6");
		list.add("item 7");
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
		add(list);
		add(label);
	}
	public static void main(String[] args) {
		Frame f = new Test();
		f.setBounds(300,300,450,150);
		f.setVisible(true);

		f.addWindowListener (new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		System.exit(0);
        	}
        });			
	}
}
class StringDragSource implements DragSourceListener, 
											DragGestureListener {
	Component component;
	DragSource dragSource;
	DragGestureRecognizer recognizer;
	Method dragMethod;
	String s;

	public StringDragSource(Component component, 
							String methodName,
							Class[] args) {
		try {
			dragMethod = 
				component.getClass().getMethod(methodName, args);
		}
		catch(NoSuchMethodException ex) {
			ex.printStackTrace();
		}
		this.component = component;
   		dragSource = new DragSource();
   		recognizer = 
			dragSource.createDefaultDragGestureRecognizer(
   					component,  // Component
					DnDConstants.ACTION_COPY_OR_MOVE,  // actions
					this); // DragGestureListener
	}
	public void dragGestureRecognized(DragGestureEvent e) {
		try {
			s = (String)dragMethod.invoke(
									component, new Object[]{});
		}
		catch(InvocationTargetException ex) {
			ex.printStackTrace();
		}
		catch(IllegalAccessException iae) {
			iae.printStackTrace();
		}
   		dragSource.startDrag(
   					e, 
					DragSource.DefaultCopyDrop,  // cursor
					new StringSelection(s), // transferable
					this); // DragSourceListener
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
	Method dropMethod;

	public StringDropTarget(Component component, 
							String methodName,
							Class[] args) {
		try {
			dropMethod = 
				component.getClass().getMethod(methodName, args);
		}
		catch(NoSuchMethodException ex) {
			ex.printStackTrace();
		}
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
				System.out.println(s);

				dropMethod.invoke(component, new Object[]{s});
				e.getDropTargetContext().dropComplete(true);
			}		
		} 
		catch(IOException io) {
			io.printStackTrace();
			e.rejectDrop();
		} 
		catch(UnsupportedFlavorException ufe) {
			ufe.printStackTrace();
			e.rejectDrop();
		}
		catch(IllegalAccessException iae) {
			iae.printStackTrace();	
			e.rejectDrop();
		}
		catch(InvocationTargetException ite) {
			ite.printStackTrace();
			e.rejectDrop();
		}
	}
	public void dragExit (DropTargetEvent e) { }
	public void dragOver (DropTargetDragEvent e) { }
	public void dropActionChanged (DropTargetDragEvent e) { }
}
