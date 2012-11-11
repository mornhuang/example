/*
 * ControllerUSBListener.java
 *
 * Created on 23. Juli 2003, 14:37
 */

package usb.test;

import usb.core.*;
import usb.windows.*;
import java.io.IOException;
/**
 *
 * @author  mike
 */
public class ControllerUSBListener extends usb.core.USBListenerAdapter {
    
    //private Host host;
    private Bus bus;
    
    /** Creates a new instance of MyUSBListener */
    public ControllerUSBListener() throws IOException{
    }
    
    public void busAdded (Bus bus) throws IOException{
       System.out.println("\n>>> L I S T E N E R <<<  Bus to listener added! BusID: " + bus.getBusId() + "\n");
    }
    public void busRemoved (Bus bus) throws IOException {
       System.out.println("\n>>> L I S T E N E R <<<  Bus from listener removed! BusID: " + bus.getBusId() + "\n");
    }
    public void deviceAdded (Device dev)  throws IOException{
        System.out.println("\n>>> L I S T E N E R <<<  Device to BusID["+ ((USB)dev.getBus()).getBusNum()+ "] added! uniqueID: "+ ((DeviceImpl)dev).getUniqueDeviceID() +"\n ");
       printUSBStack(dev.getBus());
    }
    public void deviceRemoved (Device dev)throws IOException{
        System.out.println("\n>>> L I S T E N E R <<<  Device from BusID["+ ((USB)dev.getBus()).getBusNum()+ "] removed!  uniqueID: "+ ((DeviceImpl)dev).getUniqueDeviceID() +"\n ");
        printUSBStack(dev.getBus());
    }
    
   /**
    * prints all devices on this bus inclusive the root hub
    */
   private void printUSBStack(Bus bus) {
        try{
         DeviceImpl dev;
        
         for(int i = 0; i < 7; i++){
               dev = (DeviceImpl)bus.getDevice(i);
               System.out.print(" [ " + i + " ] :  ");
               if(dev != null){
                   if(dev.getAddress() == 0)        System.out.println(" [ROOT]          numOfPort:" + dev.getNumPorts()+"  Address:" + dev.getAddress());
                   else{
                           if(dev.getNumPorts() > 0)   System.out.println(" [EXTERNAL HUB]  numOfPort:" + dev.getNumPorts()+"  Address:" + dev.getAddress());
                           else System.out.println(" [USB DEVICE]    on Port "+dev.getHubPortNum() +  "  Address           : " + dev.getAddress());
                           System.out.println("                                      uniqueID          : " + dev.getUniqueDeviceID());
                           System.out.println("                                      driverKeyName     : " + dev.getDriverKeyName());
                           System.out.println("                                      friendlyDeviceName: " + dev.getFriendlyDeviceName());
                           if (dev instanceof Device) System.out.print("                                      Object Type       : Device");
                           if (dev instanceof DeviceImpl) System.out.print(", DeviceImpl");
                           if (dev instanceof JUSB) System.out.println(", JUSB");
                           if (dev instanceof NonJUSB) System.out.println(", NonJUSB");
                   }
                }
               else System.out.println(" [NULL] ");
          }
        }catch(Exception e){
	    e.printStackTrace ();
        }
    }

}
