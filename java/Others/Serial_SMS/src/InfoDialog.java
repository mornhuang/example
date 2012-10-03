// Mobile Access Control: Access Unit Prototype
// (C) 2002, Swisscom Corporate Technology, S. Moscibroda


// Class implementing a information window about the Mobile Access Control project




import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class InfoDialog extends Frame {

 final int HEIGHT = 380;
 final int WIDTH  = 420;
 private Image img;
 private String FilePath = "C:/Serial_SMS/Info.gif";
 private Panel buttonPanel;
 private Panel infoPanel;


 public InfoDialog(){
   super("Project Info");
   addWindowListener(new CloseHandler(this));
   setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
   add(new BitmapComponent(FilePath));
   pack();
   setSize(WIDTH, HEIGHT);
   setResizable(false);
}


    /**
    Handles closing down system. Allows application to be closed with window
    close box.
    */
    class CloseHandler extends WindowAdapter {

	InfoDialog sp;

	public CloseHandler(InfoDialog sp) {
	    this.sp = sp;
	}

	public void windowClosing(WindowEvent e) {
	    sp.setVisible(false);
	}
    }
}