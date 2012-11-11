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

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

import usb.core.*;

/**
 * Provides access to native USB host objects
 *
 * @author  Mike Stahl
 * @version $id: Windows.java, v1.0 Created on 11. Juli 2003, 21:23 
 */
public class Windows extends usb.core.HostFactory {
    
    public static final boolean debug = false;
    /** If true the debugs the enumeration process */    
    public static final boolean debugEnum = false;
    // To look what happens in the Watcher class
    /** if true then debugs the Watcher issues */    
    public static final boolean debugWatcher = false;
    /** if true then debugs the USB issues */    
    public static final boolean debugUSB = false;
    /** if true then debugs the DeviceImpl issues */    
    public static final boolean debugDeviceImpl = false;
    /** if true then debugs the JUSB issues */    
    public static final boolean debugJUSB = false;
    /** if true then debugs the USB issues */    
    public static boolean createUSB;
   
    
    private static Windows.HostImpl self;
    
    static private Watcher watcher;
    static private Thread daemon;
    /** The polling period for scanning the bus in seconds */    
    static final int POLL_PERIOD = 4;	// seconds
    
    /** The string to regocnise a jUSB driver. If the "DeviceDesc" value in the Windows
     * registry for an usb device starts with this name then the device is supposed to
     * be a jUSB device.
     */    
    public static final String A_JUSB_DRIVER = "JUSB Driver --:";
    
    /** 
     * Not part of the API; implements reference implementation SPI.
     */
    public Windows() {
    }

    /** 
     * Not part of the API; implements reference implementation SPI.
     */
     public Host createHost() throws java.io.IOException {
        return Windows.getHost();
    }
   
    /**
     * Provides access to a singelton USB Host
     */
    public static Host getHost() throws IOException{
       synchronized (Host.class){
           // check whether there exists already a HostImpl or not
           if (self == null) {
             // there exists no Host; create our own Host.
             
             // load the jusb.dll which must be put into the 
             // Windows system32 folder
             System.loadLibrary("jusb");
               
             self = new Windows.HostImpl();  
               
               
           }
      } // end of synchronized
       return self;
    }
    
    /** Return the host controller name of the Windows OS
     * @param hcdDevicePath The host controller device path from the Windows OS
     * @return The name of the host controller or null.
     */    
     static public native String getHostControllerName(String hcdDevicePath);
     /** Return the ith host controller device path from the Windows OS
      * @param number The number indicates the ith host controller on the system. Start with 0 and
      * increment until null is returned
      * @return The host controller device path or null.
      */     
     static public native String getHostControllerDevicePath(int number);

    
    /********************************************************************
     * Represents a Windows host associated with one or more 
     * Universal Serial Busses (USBs)
     *
     * To provide missunderstanding in the USB topology of Windows 
     * operating systems, one Universal Serial Bus is managed by one 
     * Host Controller. So the host we create through the jusb API has
     * nothing to do with the Host Controller from the Windows os.
     * In fact a host controller in the Windows os correspond to a
     * Bus according to this jusb API. This means if we have more
     * than one host controller on our Windows PC, we will have also
     * more USB Busses. The amount of busses is equal to the amount of
     * host controllers.
     */
    
     private static final class HostImpl implements Host{
         
         private final transient Hashtable busses = new Hashtable(3);
         private final transient Vector listeners = new Vector(3);
         
         HostImpl() throws IOException{
            super();
            
            watcher = new Watcher(busses,listeners);
            // create a new Thread with the target watcher, which implements the run() method
            // and name the thread "USB_watcher"
            daemon = new Thread(watcher,"USB-Watcher");
            // we set this thread as a daemon. This make sure that our watcher terminate as 
            // soon our main application stops. 
            daemon.setDaemon(true);
            daemon.start();
         }
         
         // actually not necessary because our thread is a daemon thread
         // and is terminated when our main application is terminated
         //
         protected void finalize(){
            daemon.interrupt();
         }
         
         public void addUSBListener(USBListener l) throws IOException {
             if (l == null) throw new IllegalArgumentException();
             listeners.add(l);
         }         
         
         public Bus[] getBusses() throws IOException {
          synchronized(busses){
            Bus retval [] = new Bus [busses.size()];
            int i=0;
            for(Enumeration e = busses.keys(); e.hasMoreElements(); )
                retval[i++] = (Bus) busses.get(e.nextElement());
            return retval;
          }
         }
         
         public Device getDevice(String portId) throws IOException {
             return new PortIdentifier(portId).getDevice(this);
         }
         
         public void removeUSBListener(USBListener l) throws IOException {
             listeners.remove(l);
         }
         
     } // end of class HostImpl
     
     /******************************************************************
      * Monitors the USB bus structure about removal and attachment of 
      * devices.
      */
     private static final class Watcher implements Runnable{
         
         private final Hashtable busses;
         private final Vector listeners;
         
         boolean finishedScan;

          
         Watcher(Hashtable b, Vector l) throws IOException{
            busses = b;
            listeners = l;
            //initial population of all the busses
            if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher.Constructor() >>> before scan! \n");
            
            boolean done = false;
            this.finishedScan = false;
            while(!this.finishedScan){
                if(!done){
                  if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher.Constructor() >>> scan started");
                  scan();
                  done = true;
                }
            }
            if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher.Constructor() >>> after scan! \n");
              
            if (busses.isEmpty()) throw new IOException(
                "There is no Host Controller on the Windows operating system." 
                + " You don't have a USB at all!");
             
         }
         
         public void run() {
              
            while(true){
                try{ 
                   if(Windows.debugWatcher) System.out.println("\n>>>> Watcher.run() >>> Thread goes now to sleeps for " + POLL_PERIOD +"s \n");
                   Thread.sleep (POLL_PERIOD * 1000);
                   
                   
                    boolean done = false;
                    this.finishedScan = false;
                    while(!this.finishedScan){
                        if(!done){
                            if(Windows.debugWatcher) System.out.println("\n>> Watcher.run() >>>   G O I N I G   T O    S C A N    A L L !    Starttime[" + System.currentTimeMillis()   + "] \n");
                            scan();
                            done = true;
                        }
                    }
                }
                 catch (InterruptedException e) {
		    // set dir to null to cause a clean exit
                }
                catch(IOException e){
                   e.printStackTrace();   
                }
            }
         }
         
         private native String getHostControllerName(int number);
         
         /**
          * scan all the busses and all the devices on each bus
          */
         private void scan() throws IOException{
            
             synchronized(busses){
                 // what busses exists now?
                 // look for all existing Host Controller on the Windows OS
                 int i = 0;
                 String hostControllerDevicePath;
                 Vector kids = null;  // Kids contains all current Host Controllers
                  
                 // as long we find a hostcontroller create a new bus
                 while((hostControllerDevicePath = Windows.getHostControllerDevicePath(i)) != null){
                   
                    if(kids == null)        //create a Vector for kids as soon we 
                       kids = new Vector(); // find at least one host controller
                   
                   kids.add(hostControllerDevicePath); //add the hc to the kids
                     
                   // for debugging
                   if(Windows.debugWatcher) 
                       System.out.println(Windows.createUSB + " Watcher.scan() >>> kids[" + i + "].add("+ hostControllerDevicePath + " )");
                   i++;
                 }
                 // so far our kids contains all host controller which are currently
                 // available on the windows system
                 // now check if something changed since the last scan has being applied
                 if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher.scan() >>> check if something changed since the last scan has being applied");
                
                 Vector seen = new Vector(kids.size());
                 for(i = 0; i < kids.size() ; i++) {
                     // Windows has not really a bus number for each USB bus
                     // therefore we give each bus an abstract number, so that
                     // it corresponds to linux impl and the jusb core API
                     int busnum = i + 1;
                     
                     //mark the kid we look at as seen
                     seen.add((String)kids.get(i));
                     
                     //try to get the bus object from the current busses
                     USB bus = (USB) busses.get((String)kids.get(i));
                     
                     //if we didn't get a bus object, it is a new bus
                     if(bus == null){
                        if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher.scan() >>> NEW <<< found a new bus. Create a new BUS with busnum="+busnum);
                        mkBus((String)kids.get(i),busnum); 
                     }else{
                         // check wheter the existing bus has some changes
                         // with its attached devices
                        if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher.scan() >>> SAME <<< BUS["+busnum+"] already exists. Check if something changed on the BUS[" + busnum + "]");
                        bus.setFinishScanBusFalse();
                        boolean done = false;
                        while(!bus.getFinishScanBus()){
                            if(!done){
                                if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher.scan() >>> start scanBus BUS["+busnum+"]!");
                                bus.scanBus();
                                done = true;
                            }
                         }
                     }
                   } // end of for
                   
                   // what are the busses that previously existed?
                   // if we find some busses that doesn't exist
                   // anymore, we have to remove them
                   
                   for(Enumeration e = busses.keys(); e.hasMoreElements(); ){
                     Object busname = e.nextElement();
                     if(!seen.contains(busname)){
                        //this bus is not attached anymore, remove it
                        if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher.scan() >>> OLD <<< remove a bus, because it does not exists anymore!");
                       rmBus(busname);
                     }
                   }
             } // end of synchronized(busses)
              this.finishedScan = true;
         }
         
         
         private void rmBus(Object busname){
           USB bus = (USB) busses.get(busname);
           if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher:rmBus() >>> REMOVE <<< BUS[" + bus.getBusNum() + "] REMOVED!  inform the USBListener that a bus is removed ");

           
           for(int i = 0; i < listeners.size(); i++){
               USBListener listener;
               listener = (USBListener) listeners.get(i);
                 try{
                  listener.busRemoved(bus); 
                }
                catch(Exception e){
                  e.printStackTrace();   
                }
           }
             
           busses.remove(busname);
           bus.kill();
             
             
         }
         
         
         private void mkBus(String hostControllerDevicePath, int busnum)
           throws IOException{
              USB bus;
              String hostControllerName = Windows.getHostControllerName(hostControllerDevicePath);
              
              boolean done = false;
              Windows.createUSB = false;
              bus = null;
              while(!Windows.createUSB){
                if(!done){
                    if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher:mkBus() >>> ADD <<< BUS[" + busnum + "]");

                    bus = new USB( hostControllerName,
                             hostControllerDevicePath, 
                             busnum, 
                             listeners, 
                             self);
                    done = true;
                }
              }
                    
              if(bus == null)
                if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher:mkBus() >>> ADD <<< BUS[" + busnum + "] not created!!! >>> FAILURE <<<");
              else
                if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher:mkBus() >>> ADD <<< BUS[" + busnum + "] created! BUS Object: " + bus);
              
              busses.put(hostControllerDevicePath,bus);

              if(Windows.debugWatcher) System.out.println(Windows.createUSB + " Watcher:mkBus() >>> ADD <<< BUS[" + busnum + "] inform the USBListener that a bus is added");

              for(int i=0; i < listeners.size(); i++){
                USBListener listener;
                listener = (USBListener) listeners.get(i);
                try{
                  listener.busAdded(bus); 
                }
                catch(Exception e){
                  e.printStackTrace();   
                }
              } // end for
         }
     } // end of class Watcher
     
     
}
