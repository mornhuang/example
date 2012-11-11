package usb.test;

/*
 * USBController.java
 *
 * Created on 23. Juli 2003, 13:29
 */

import usb.core.*;
//import usb.windows.*;
import java.io.IOException;
/**
 *
 * @author  mike
 */
public class USBController extends Thread {
    
    private Host host;
    
    
    /** Creates a new instance of USBController */
    public USBController() {
        try{
           
           host = HostFactory.getHost();
           if(host == null) throw new Exception("USB is not available!");
           
           // add the ControllerUSBListener to the host
           host.addUSBListener(new ControllerUSBListener());
            
        }catch (SecurityException e) {
	    System.err.println ("USB permissions problem:");
	    System.err.println (e.getMessage ());
	    System.exit (1);
	}catch(Exception e){
          e.printStackTrace ();
        }
        this.start();
    }
  
    public void run(){
        int i = 0;
        while (true) {
           if (isInterrupted()){
            
            
           //System.out.println(i++);
           }
        }
    }
}


