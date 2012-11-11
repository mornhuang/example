import java.applet.Applet;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.*;

public class Test extends Frame {
	public Test() {
		super("Drag and Drop Test");

		List drag = new List();
		DropCanvas drop = new DropCanvas("cover");

		StringDragSource source = new StringDragSource(
					drag, "getSelectedItem", new Class[]{});

		StringDropTarget target = new StringDropTarget(
					drop, "loadImage", new Class[]{String.class});

		drag.add("tiger");
		drag.add("mountain");
		drag.add("cover");

		setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
		add(drag);
		add(drop);
	}
	public static void main(String[] args) {
		Frame f = new Test();
		f.setBounds(300,300,500,400);
		f.setVisible(true);

		f.addWindowListener (new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		System.exit(0);
        	}
        });			
	}
}
class DropCanvas extends ImageCanvas implements Autoscroll {
	private Insets insets = new Insets(10,10,10,10);
	private Point offset = new Point(0,0);

	public DropCanvas() {
		this(null);
	}
	public DropCanvas(String imageName) {
		super(imageName);
	}
	public void paint(Graphics g) {
		Dimension size = getSize();
		g.drawRect(0,0,size.width-1,size.height-1);

		g.translate(-offset.x, -offset.y);
		super.paint(g);
	}
	public Dimension getPreferredSize() {
		return new Dimension(200,200);
	}
	public Insets getAutoscrollInsets() {
		return insets;
	}
	public void autoscroll(Point drag) {
		Dimension size = getSize();

		if(drag.x < insets.left) {
			if(offset.x + 10 < size.width - 
								insets.left - insets.right) 
			offset.x += 10;
		}
		if(drag.x > size.width - insets.right) {
			if(offset.x - 10 >= 0)
				offset.x -= 10;
		}
		if(drag.y < insets.top) {
			if(offset.y + 10 < size.height -
								insets.top - insets.bottom) 
				offset.y += 10;
		}
		if(drag.y > size.height - insets.bottom) {
			if(offset.y - 10 >= 0)
				offset.y -= 10;
		}
		repaint();
	}
}
class ImageCanvas extends Canvas {
	Image image;

	public ImageCanvas(String imageName) {
		loadImage(imageName);
	}
	public void loadImage(String imageName) {
		image = 
			Toolkit.getDefaultToolkit().getImage(
											imageName + ".gif");

		MediaTracker mt = new MediaTracker(this);
		try {
			mt.addImage(image, 0);
			mt.waitForID(0);
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		if(isShowing()) {
			repaint();
		}
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(null),
							 image.getHeight(null));
	}
}
class StringDragSource 
		implements DragSourceListener, DragGestureListener {
	Component component;
	DragSource dragSource;
	DragGestureRecognizer recognizer;
	Method dragMethod;

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
		String s = null;

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
	String string;

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
				e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

				string = (String)tr.getTransferData (
										DataFlavor.stringFlavor);
				System.out.println(string);

				dropMethod.invoke(component, 
										new Object[]{string});

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
	public void dragExit(DropTargetEvent e) { }
	public void dragOver(DropTargetDragEvent e) { }
	public void dropActionChanged(DropTargetDragEvent e) { }
}
