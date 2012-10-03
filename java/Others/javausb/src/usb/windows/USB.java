/*
 * Java USB Library
 * Copyright (C) 2000 by David Brownell
 * Copyright (C) 2002 by Wayne Westerman
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 *
 */

package usb.windows;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.io.IOException;

import usb.core.*;

/**
 * Represents a Universal Serial Bus (USB) which hosts a set of
 * devices. Such busses are largely autoconfiguring. Aspects such as
 * power managemnet and detailed device configuration involve 
 * policy choices.
 *
 * @author  mike
 * @version $id: USB.java, v1.0 Created on 11. Juli 2003, 23:41
 */
public class USB implements Bus{
    
    /** @serial the host to which this bus is connected */
    private final Host host;
     
    /** 
     * Remember that one Host Controller represents one USB bus. 
     * The hostControllerName looks like :
     * "Intel(R) 82801DB/DBM USB Universal Host Controller - 24C2"
     */
    private final transient String hostControllerName;
    
    /**
     * Its internally used to connect to a USB Host Controller 
     * on Windows. 
     * The hostControllerDevicePath looks like:
     * "\\?\pci#ven_8086&dev_24c4&subsys_45418086&rev_03#3&61aaa01&0&e9#{3abf6f2d-71c4-462a-8a92-1e6861e6af27}"
     */
    private final transient String hostControllerDevicePath;
    
    private final int busnum;
    
    private final transient Vector listeners;
    
    // all devices which are currently attached to the bus
    private final transient DeviceImpl devices[] = new DeviceImpl [127];
    
    // all devices which are attached during the last scan
    private transient DeviceImpl oldDevices[] = new DeviceImpl [127];

    /**
     * Used to indicate that enumerating of the device is finished
     */
    private boolean finishedEnumerating;
    
    private boolean finishScanBus;
   
    /** 
     *  
     *
     */
    USB(String hcdName, String hcdDevicePath, int b, Vector l, Host h) {
        hostControllerName = hcdName;
        hostControllerDevicePath = hcdDevicePath;
        busnum = b;
        listeners = l;
        host = h;
        if(Windows.debugUSB) System.out.println("USB.Constructor() >>> BUS["+ busnum +"]   hcdDevicePath:" + hostControllerDevicePath + " " );
        try{
            this.setFinishScanBusFalse();
            boolean done = false;
             while(!this.finishScanBus){
                if(!done){
                    if(Windows.debugUSB) System.out.println("USB.Constructor() >>> start scanBus BUS["+busnum+"]!");
                    scanBus();
                    done = true;
                }
            }
            
         Windows.createUSB = true;  
        }catch(IOException e){
          System.out.println("scanBus Failed!" + e.getMessage());  
        }
        
    }
    
    /**
     * returns the roothub name or null in case there exists roothub
     */
    private native String getRootHubName(String hcdDevicePath);
 
    
    /**
     * Returns the USB host for this bus
     */
    public Host getHost(){
        return host;
    }
    /**
     * Returns the number assigned to this bus
     */
    public int getBusNum(){
        return busnum;
    }
    
    /**
     * Returns the host controller name for this Universal Serial Bus
     */
     public String getBusId(){
        if(hostControllerName == null) return null;
        else return hostControllerName;
    }
    
     /** Returns the device with the given address.
      * @param address The device address <br>
      * 0: Always the root hub
      * 1..126: devices or hubs or null
      */     
     public Device getDevice(int address) throws IOException {
         return devices[address];
     }     
    
     /** Returns the root hub of that bus */     
     public Device getRootHub() throws IOException {
         return devices[0];
     }
     
     
     /**
      * used to be boolean
      */
      void scanBus() throws IOException{
         
         if(Windows.debugUSB) System.out.println(this.finishedEnumerating +" USB.scanBus() >>> scan BUS["+ busnum +"]");
        
         String rootHubName;
         DeviceImpl rootHub;
         
         // init the bus and get all its
         // attached devices and hubs
         // get the RootHubName and create it
         rootHubName =  getRootHubName(hostControllerDevicePath);

         if (rootHubName == null) throw new USBException("Root hub name not found");
           
         //check if root hub has changed
         DeviceImpl root = devices[0];
         if(root != null && !root.getDevicePath().equals("\\\\.\\" + rootHubName)){
             // root hub has changed
             // in that case all devices need to be removed from the 
             // current usb bus. 
             if(Windows.debugUSB) System.out.println(this.finishedEnumerating +" USB.scanBus() >>> root hub of BUS["+busnum +"] has changed! remove all former devices from this bus!");
             kill();
         }
         // The root hub has not changed or does not exists
         // In either way create a root hub instance of the root hub.
         // This is a little overhead, in the case when there exists
         // already a root hub. In that case the root hub will be replaced
         // through the new one.
         // All other changes on the bus are now managed by the devices itself.
         // Therefore we need to give the old device list to the first instance of
         // DeviceImpl.
         // The root hub will be added to the bus.
         // The root hub recursively calls its ports
         // and will add each device that is connected to 
         // this root hub
         // look at the DeviceImpl constructor
         
         this.finishedEnumerating = false;
         boolean done = false;
         rootHub = null;
         while(!this.finishedEnumerating){
            
             if(!done){
                if(Windows.debugUSB) System.out.println(this.finishedEnumerating +" USB.scanBus() >>> start enumerating!");
                rootHub = new NonJUSB(this, "\\\\.\\"+ rootHubName,0,oldDevices);
                done = true;
            }
         }
           
         if(root == null){
            //there exists no root hub so far
            //we have to add it and all USBListener have to be informed
            this.addDevice(rootHub ,0 );   
         }else{
           // there is already a root hub
           // to make sure that the USBListener does not notify the
           // user about a device changes we add
           this.addDeviceWithNoNotification(rootHub ,0 );    
         }
           
         //update the oldDevices
         //this.oldDevices = devices;
         copyArray(this.devices, this.oldDevices);
         this.setFinishScanBusTrue();
         
     }
     
    
     /**
      * adds a device to this bus and notify the USBListeners
      * This method is used in DeviceImpl.enumerateHubPorts to add a
      * device, an external hub or the root hub to the bus
      */
     synchronized void addDevice(DeviceImpl dev,int address){
         
      if(dev == null) devices[address] = dev;
      else {devices[dev.getAddress()] = dev;
      if(Windows.debugUSB){
          System.out.print("USB.addDevice() >>> Bus [" + busnum+ "] : ");
          if(dev == null) System.out.println("NULL");
          else  if(dev.getAddress() == 0) System.out.println("ROOT         []  Root Addr[" +  dev.getAddress()+ "]");
          else if(dev.getNumPorts() > 0) System.out.println("EXTERNAL HUB [" + dev.getUniqueDeviceID() + "]  on Port[" + dev.getHubPortNum() + "]   Hub Addr[" +  dev.getAddress()+ "]");
          else System.out.println("USB DEVICE   [" + dev.getUniqueDeviceID() + "]  on Port[" + dev.getHubPortNum() + "]   Dev Addr[" +  dev.getAddress()+ "]");
       }
	// call synch'd on devices
	for (int i = 0; i < listeners.size (); i++) {
	    USBListener	listener;
	    listener = (USBListener) listeners.elementAt (i);
	    try { listener.deviceAdded (dev); }
	    catch (Exception e) {
		if (Windows.debug)
		    e.printStackTrace ();
	    }
	}
      }
     }
     /**
      * adds a device to this bus but does not notify its USBListeners
      * This is used so far only for the the rootHub:
      * If a rootHub did not change. 
      *
      */
    synchronized void addDeviceWithNoNotification(DeviceImpl dev,int address){
         
      if(dev == null) devices[address] = dev;
      else {devices[dev.getAddress()] = dev;
       //devices[address] = dev;
       if(Windows.debugUSB){
          System.out.print("USB.addDeviceWithNoNotification() >>> Bus [" + busnum+ "] : ");
          if(dev == null) System.out.println("NULL");
          else  if(dev.getAddress() == 0) System.out.println("ROOT         []  Root Addr[" +  dev.getAddress()+ "]");
          else if(dev.getNumPorts() > 0) System.out.println("EXTERNAL HUB [" + dev.getUniqueDeviceID() + "]  on Port[" + dev.getHubPortNum() + "]   Hub Addr[" +  dev.getAddress()+ "]");
          else System.out.println("USB DEVICE   [" + dev.getUniqueDeviceID() + "]  on Port[" + dev.getHubPortNum() + "]   Dev Addr[" +  dev.getAddress()+ "]");
       }
      }
     }

     /**
      * removes a device from this bus and notify the USBListeners
      * This method is used in DeviceImpl.enumerateHubPorts to remove a
      * device or an external hub.
      * 
      */
     synchronized void removeDevice(DeviceImpl dev){
       
       devices[dev.getAddress()] = null;
       if(Windows.debugUSB){
          System.out.print("USB.removeDevice() >>> Bus [" + busnum+ "] : ");
          if(dev == null) System.out.println("NULL");
          else  if(dev.getAddress() == 0) System.out.println("ROOT         []  Root Addr[" +  dev.getAddress()+ "]");
          else if(dev.getNumPorts() > 0) System.out.println("EXTERNAL HUB [" + dev.getUniqueDeviceID() + "]  on Port[" + dev.getHubPortNum() + "]   Hub Addr[" +  dev.getAddress()+ "]");
          else System.out.println("USB DEVICE   [" + dev.getUniqueDeviceID() + "]  on Port[" + dev.getHubPortNum() + "]   Dev Addr[" +  dev.getAddress()+ "]");
       }
       
	
       // call synch'd on devices
	for (int i = 0; i < listeners.size (); i++) {
	    USBListener	listener;
	    listener = (USBListener) listeners.elementAt (i);
	    try { listener.deviceRemoved (dev); }
	    catch (Exception e) {
		if (Windows.debug)
		    e.printStackTrace ();
	    }
	}
      }
    
     // package private
    synchronized void kill()
    {
	if (Windows.debugUSB)
	    System.err.println ("USB.kill() >>> kill BUS["+ busnum +"]");

	// notify any listeners that the bus died, and
	// clear backlinks that we control
	if (listeners.size () > 0) {
	    synchronized (devices) {
		for (int i = 0; i < devices.length; i++) {
		    if (devices [i] == null)
			continue;
		    removeDevice(devices [i]);
		}
	    }
	}
    }
    
    /** Used as a semaphore */    
   void setFinishEnumerating(){
       if (Windows.debugUSB)   System.err.println ("USB.setFinishEnumerating() >>> Enumerating finished for BUS["+ busnum +"]");
       
       this.finishedEnumerating = true;
   }

   /** Used as a semaphore */   
   void setFinishScanBusTrue(){
       if (Windows.debugUSB)   System.err.println ("USB.setFinishScanBusTrue() >>> Scan bus finished for BUS["+ busnum +"]");
       
       this.finishScanBus = true;
   }
   
   /** used as a semaphore */   
   void setFinishScanBusFalse(){
       this.finishScanBus = false;
   }
   /** used as a semaphore */   
   boolean getFinishScanBus(){
       return  this.finishScanBus;
   }

   
   /**
    * copy the one Array to the other Array
    */
    private void copyArray(DeviceImpl devfrom [], DeviceImpl devto[]){
       if (Windows.debugUSB)  System.out.println(this.finishedEnumerating +" "+this.finishScanBus+ "USB.copyArray() >>> BUS["+ busnum +"]");

        for(int i=0 ; i < devfrom.length ; i++){
            devto[i] = devfrom[i];
        }
    }

}
