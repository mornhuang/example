/*
 * DeviceImpl.java
 *
 * Created on 15. Juli 2003, 16:35
 */
/*
 * Java USB Library
 * Copyright (C) 2000 by David Brownell
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
 */

package usb.windows;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;

import usb.core.*;
import usb.util.LangCode;
/**
 * Provides access to all USB devices on a bus in limited fashion.<br>
 * The device descriptor and configuration descriptor provides more information
 * to the devices which are attached to the bus. <br>
 * The devices itself are instances of the subclass NonJUSB when not using the 
 * jUSB driver and of the subclass JUSB when using the jUSB driver.
 *
 * @author  Mike Stahl
 * @version $id: DeviceImpl.java, v1.0 Created on 15. Juli 2003, 16:37 
 */

public class DeviceImpl extends Device {
    
    /** 
     * Corresponds to a hub port and indicates that no device is connected to that
     * port.
     */    
    public static final int NO_DEVICE_CONNECTED = 1;
    /** Corresponds to a hub port and indicates that an external hub is connected to that
     * port.
     */    
    public static final int EXTERNAL_HUB = 2;
    /** Corresponds to a hub port and indicates that a device is connected to that
     * port.
     */    
    public static final int USB_DEVICE = 3;
    /**
     * The number of ports on this hub, or zero if it's a device
     */
    private int numPorts;
    /**
     * The hub port number to which this device or hub is connected
     */
    private int hubPortNumber;
    /**
     * The bus to which this device or hub is connected
     */
    private final USB usb;
    
    /**
     * The devices that were on the USB before the scan
     */
    private static DeviceImpl oldDevices [] = new DeviceImpl [127];
    /**
     * usbDevicePath corresponds to the Windows DevicePath for this device
     */
    private String usbDevicePath;
    /**
     * This id is unique to every usb device. It is used to find out if something
     * changed on the bus.
     */
    private String uniqueDevID;
    /**
     * The Windows driverKeyName for that device
     */
    private String driverKeyName;
    /**
     * Friendly Name of the Driverkey
     */
    private String friendlyDeviceName;
    
    private DeviceImpl hub;
    private DeviceImpl children[];
    /** The device descriptor */    
    public  DeviceDescriptor	descriptor;
     /** The configuration descriptor */    
    public Configuration configuration;

    

    /** used to create an instance of the root hub
     * @param bus The bus this device belongs to
     * @param devicePath The Windows OS specific device path for that device
     * @param address The device address (should be always 0, because this constructor is only called
     * to create the root hub)
     * @param devices Contains the current structure of the bus. Is used to compare if device have
     * been removed or attached during the last scan.
     * @throws IOException An exception is raised when we could not get a handle to the root hub
     */
    DeviceImpl(USB bus, String devicePath, int address,DeviceImpl devices[])
    throws IOException {
        super (null, bus, address);
        
        this.usb = bus;
        this.usbDevicePath = devicePath;
        this.hubPortNumber = 0;
        this.driverKeyName = null;
        this.friendlyDeviceName = "Root Hub";
        this.oldDevices = devices;
        this.hub = null;

        if(Windows.debugDeviceImpl){
          System.out.println("DeviceImpl.Constructor() ROOT >>> Bus[ " +usb.getBusNum() + "]  --- Device[" + this + "]  --- Adr["+ address+ "] " + address + "  --- Port[" +hubPortNumber + "]" );  
        }
        
        
        //----------- R O O T   H U B ------------------------
        // check if it is the root hub
        if(devicePath != null && address == 0){
          //we are accessing the root hub
          //get the root hub handle and how many
          //ports it contains
          
         int rootHubHandle = this.openHandle(devicePath);
         if(rootHubHandle == USBException.ERROR_INVALID_HANDLE)
             throw new USBException("INVALID_HANDLE_VALUE for roothub ",rootHubHandle);

         this.numPorts = getNumPorts(rootHubHandle);
         if(Windows.debugDeviceImpl) System.out.println("DeviceImpl.Constructor() ROOT >>> Root Hub (rootHubHandle:" + rootHubHandle + "numPorts:" + this.numPorts+")");
         this.enumerateHubPorts(usb, rootHubHandle,this,this.numPorts);
         
         // close the root hub handle
         this.closeHandle(rootHubHandle);
         
        }
        else throw new USBException("This constructor is used only for a root hub",-1);
        
               
    }
   
    /** used to create an instance of either an external hub or an usb device
     * @param bus The bus object this device belongs to
     * @param hub The device(hub) this device is a child of
     * @param devicePath The Windows OS specific device path for that device
     * @param address The device address
     * @param driverKeyName The driver key name of that device<br>This look similiar to such a string
     * {<device interface class>}
     * @param friendlyDevName The friendly device name of that device
     * @param uniqueID The unique ID
     * @param portIndex The port number this device is connected to
     * @throws IOException An exception is raised when we could not get a handle a hub or a JUSB device
     */
     DeviceImpl(USB bus,DeviceImpl hub, String devicePath, int address,String driverKeyName,String friendlyDevName, String uniqueID,int portIndex)
     throws IOException {
        super (null, bus, address);
        this.usb = bus;
        this.usbDevicePath = devicePath;
        this.driverKeyName = driverKeyName;
        this.friendlyDeviceName = friendlyDevName;
        this.hubPortNumber = portIndex;
        this.uniqueDevID = uniqueID;

        if(Windows.debugDeviceImpl){
          System.out.println("DeviceImpl.Constructor() DEV or HUB >>> Bus[ " +usb.getBusNum() + "]  --- Device[" + this.uniqueDevID + "]  --- Adr["+ address + "] " + address + "  --- Port[" +portIndex + "] ");  
        }
        
        
        
        this.hub = hub;
        
          //---------- E X T E R N A L    H U B -----------------
         //
         if(devicePath != null && address > 0){
 
            int hubHandle = this.openHandle(devicePath);
            if(hubHandle == USBException.ERROR_INVALID_HANDLE)
                throw new USBException("INVALID_HANDLE_VALUE for external hub ",hubHandle);
            
            // to do:
            // at this stage it would be useful to build the hub descriptor
            // and all information that wanted to be know over an external hub
            // ...
           
            
            this.numPorts = getNumPorts(hubHandle);
            if(Windows.debugDeviceImpl) System.out.println("DeviceImpl.Constructor() DEV or HUB >>> External Hub (hubHandle:" + hubHandle + "numPorts:" + this.numPorts+")");
            this.enumerateHubPorts(usb, hubHandle,this,this.numPorts);
         
            // close the root hub handle
            this.closeHandle(hubHandle);
             
             
         }else      
         
         //---------- U S B    D E V I C E -----------------
         //
         if(devicePath == null){
 
            
            // to do:
            // at this stage it would be useful to build the hub descriptor
            // and all information that wanted to be know over an external hub
            // ...
           
            
          
               
            // to do:
            // so far we have nothing to do anymore
            // special thing gere is now to get access to a device
            // especially read and writing data, get its decriptor and 
            // configuration.
            // This will be part of the jusb driver
             
         }
        else throw new USBException("This constructor is only used either for a external hub or a device",-1);
      }

     /** Native function that invokes the CreateFile WinAPI function with the given
     * device path
     * @param devicePath A Windows specific device path for that device
     * @return A Windows handle for that device<br>
     * if failed it returns USBException.ERROR_INVALID_HANDLE
     */    
  
    public native int openHandle(String devicePath);
    /** Native function that invokes the CloseFile WinAPI function with the given
     * handle
     * @param devHandle A valid device handle. This value must correspond to a former call of openHandle
     * @return 1: when success and <br>-1: when failed to close the device
     */    
    public native int closeHandle(int devHandle);
    
    private native String getFriendlyDeviceName(String driverKeyName);
    /**
     *
     * Returns EXTERNAL_HUB or DEVICE, or NO_DEVICE_CONNECTED
     */
    private native int  getAttachedDeviceType(int hubHandle,int portIndex);
    
    /**
     * Returns the number of ports that are supported by this hub
     */
    private native int  getNumPorts(int hubHandle);
    /**
     * Gives the driverkeyname of the device which is attached to the hub
     * This value corresponds to the registry entry DeviceDesc which can be found
     * under \HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Enum\USB\Vid_xxxx&Pid_xxxx\..
     */
    private native String getDriverKeyNameOfDeviceOnPort(int hubHandle,int portIndex);
    /**
     * Returns the driver key name of that device
     */
    public String getDriverKeyName(){
        return this.driverKeyName;
    }
    
    
    /**
     * Returns the name of an external Hub
     */
    private native String getExternalHubName(int hubHandle,int portIndex);
    
    /**
     * Returns the byte stream for the DeviceDescriptor
     */
    public native byte[] getDeviceDescriptor(int hubHandle,int portIndex);

    /**
     * Returns the byte stream for the DeviceDescriptor
     */
    public native byte[] getConfigurationDescriptor(int hubHandle,int portIndex);
    
    
     /**
     * Returns identifier which is unique to a usbdevice
     * This string is only valid for usb devices! It is not 
     * used for roothub and hubs.
     */
    private native String getUniqueDeviceID(int hubHandle,int portIndex);
  
    
    /** Returns the device connected to this hub's specific port(origin one), or null
     * @param port The port number of the hub beginning at one to the number of ports the hub
     * provides
     */    
    public Device getChild(int port) throws java.io.IOException {
        if(children == null) return null;
        else return children[port-1];
    }
    
    /** Returns the default configuration from the device. The default configuration is
     * is always configuration at index zero.
     */    
    public Configuration getConfiguration() throws java.io.IOException {
        return this.configuration;
    }
    
    /** Not implemented for all devices. So far it will return always null!
     * @return Always null!
     */    
    public Configuration getConfiguration(int index) throws java.io.IOException {
        return null;
    }
    
    /** Returns the device dscriptor of the device */    
    public DeviceDescriptor getDeviceDescriptor() {
        return this.descriptor;
    }
    
    /** Returns the hub the device is connected to */    
    public Device getHub() {
        return this.hub;
    }
    
    /** Returns the port number of the hub where the device is connected to
     * @return The port number; value between 1..126
     */    
    public int getHubPortNum() {
        return this.hubPortNumber;
    }
    
    /** Returns the number of port the hub provides.
     * @return The number of ports this hub supports<br>
     * 0 if it is a device
     */    
    public int getNumPorts() {
        return (children == null) ? 0 : children.length;
    }
    
      /** The friendly device name of this device
       * @return The frinedly device name or null if it does not exist
       */
    public String getFriendlyDeviceName(){
     return this.friendlyDeviceName;   
    }
  
    // enumerate the hub
    /** EnumerateHubPorts does iterates over all the ports of this hub and creates
     * depending on the driver the device uses either a NonJusb or a JUSB object
     * @param bus The bus the device is conected to
     * @param hubHandle The Windows OS hub handle for this hub
     * @param hub The hub device itself
     * @param numPorts The number of ports this hub supports
     */    
    synchronized void enumerateHubPorts(USB bus, int hubHandle,DeviceImpl hub, int numPorts)
    throws IOException, USBException{
        
        DeviceImpl oldDev;
        DeviceImpl [] oldChildren = null;
        if(hub != null){
          if(hub.getAddress() != 0){  
            if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> oldChildren of an external hub");
             oldChildren = hub.getChildren();
             if(Windows.debugEnum) printDevices(oldChildren); 
          }
          else{
            if( this.oldDevices != null) {
                if(this.oldDevices[0] != null){
                    if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> oldChildren of the root hub");
                    oldChildren = oldDevices[0].getChildren();
                    if(Windows.debugEnum) printDevices(oldChildren); 
                }
            }
            else if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> no oldDevices: This happens when running the first time");
          }
        }
        else if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> we have no children, because we do not have a hub");
        
        
        //iterate over the port to discover what is connected to each one
        if(numPorts > 0) hub.children = new DeviceImpl[numPorts];
        else children = null;
  
        if(Windows.debugDeviceImpl) System.out.println("DeviceImpl.enumerateHubPorts() >>> hubHandle=" + hubHandle +"  address=" + this.getAddress() + " numPorts=" + numPorts);

        DeviceImpl dev;
        //  caution: Ports are numbered from 1 to n and not 0 to n!!!
        for(int portIndex=1; portIndex < numPorts + 1; portIndex++){
            
            if(Windows.debugEnum && this.oldDevices != null && bus.getBusNum()==1){
                System.out.println("DeviceImpl.enumerateHubPorts() >>> oldDevices:" );
                printDevices(this.oldDevices); 
            }
            int devType =  getAttachedDeviceType(hubHandle,portIndex);
            
            if(Windows.debugDeviceImpl){
                    System.out.print("DeviceImpl.enumerateHubPorts() >>> getAttachedDeviceType: portIndex:" + portIndex + "  Device Type:");
                    if(devType == USB_DEVICE)
                        System.out.println(" USB Device");
                    else  if(devType == EXTERNAL_HUB)
                        System.out.println(" External Hub");
                    else  if(devType == NO_DEVICE_CONNECTED)
                        System.out.println(" No device connected");
                    else System.out.println(" Somethibg else --> " + devType);
                    
            }
            
             
            
            // get some information about the attached device
            String driverKeyName = null;
            String friendlyDeviceName = null;
            String uniqueID = null;
            
            if(devType == USB_DEVICE || devType == EXTERNAL_HUB){
                driverKeyName = getDriverKeyNameOfDeviceOnPort(hubHandle,portIndex);
                
                if(driverKeyName == null){
                    // we have an external hub
                    friendlyDeviceName = null;
                 }
                else {
                    // we have a device
                    friendlyDeviceName = getFriendlyDeviceName(driverKeyName);
                 }
                
                uniqueID = this.getUniqueDeviceID(hubHandle, portIndex);
                if(Windows.debugDeviceImpl) System.out.println("DeviceImpl.enumerateHubPorts() >>> on Port["+portIndex+"]<< Device connected" );
                if(Windows.debugDeviceImpl) System.out.println("--- DriverKeyName      : " + driverKeyName );
                if(Windows.debugDeviceImpl) System.out.println("--- friendlyDeviceName : " + friendlyDeviceName );
                if(Windows.debugDeviceImpl) System.out.println("--- UniqueID           : " + uniqueID );
            }
            
            
            if(devType == USB_DEVICE){
                // the device on portIndex is a USB Device
                 if(Windows.debugEnum) System.out.print("DeviceImpl.enumerateHubPorts() >>> NOW on Port["+portIndex+"]<< Device connected" );
                
                //String driverKeyName = getDriverKeyNameOfDeviceOnPort(hubHandle,portIndex);
                //String uniqueID = this.getUniqueDeviceID(hubHandle, portIndex);
                if(uniqueID == null) throw new USBException("Could not get the uniqueID of the device! May be Error in native jusb.cpp::getUniqueDeviceID -->  DeviceIoControl (check with external debugger: Sysinternals)");
                
                int devAddress = Integer.parseInt(uniqueID.substring(8,uniqueID.indexOf('&')));
                
                if(Windows.debugDeviceImpl)
                    System.out.println("DeviceImpl.enumerateHubPorts() >>> USB_DEVICE:Port:" + portIndex + "  Address:" + this.getAddress() + "  DriverKeyName:" + driverKeyName + " <<<<<<<<<<    deviceAddress = " + devAddress);
                
                // check if this device was already on the bus
                if(this.oldDevices != null) oldDev = this.oldDevices[devAddress];
                else oldDev = null;
                
                if(oldDev != null ){
                   if(!oldDev.getUniqueDeviceID().equals(uniqueID)){
                    // we have a devive, but it is not identical to
                    // that device we just found on the bus
                    if(Windows.debugEnum) System.out.println(" >> BEFORE <<  a different DEVICE was connected to this (root)hub. Former Device ID:" + oldDev.getUniqueDeviceID());
                    if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>>  TO DO << remove the former device. Add the new device.");

                    bus.removeDevice(oldDev);
                    // add the new one to the bus
                    // depending on the friendlyDeviceName we create either a JSUB instance or a NonJUSB instance
                    if(friendlyDeviceName != null && friendlyDeviceName.startsWith(Windows.A_JUSB_DRIVER))
                        dev = new JUSB(usb,this,null,devAddress,driverKeyName,friendlyDeviceName,uniqueID,portIndex);
                    else
                        dev = new NonJUSB(usb,this,null,devAddress,driverKeyName,friendlyDeviceName,uniqueID,portIndex);
                        
                    hub.children[portIndex-1] = dev;
                    bus.addDevice(dev, devAddress); 

                   }
                   else{
                     // the device is still the same one
                     // add it again as a child of this hub.
                     if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> BEFORE <<  the same one");
                       
                     hub.children[portIndex-1] = oldDev;  
                   }
                }
                else{
                    // there was no device attached before
                    // add this new device now to the bus
                    if(Windows.debugEnum) System.out.println(" >> BEFORE <<   no connection");
                    if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> TO DO << Add the new device.");
                     // depending on the friendlyDeviceName we create either a JSUB instance or a NonJUSB instance
                    if(friendlyDeviceName != null && friendlyDeviceName.startsWith(Windows.A_JUSB_DRIVER))
                        dev = new JUSB(usb,this,null,devAddress,driverKeyName,friendlyDeviceName,uniqueID,portIndex);
                    else
                        dev = new NonJUSB(usb,this,null,devAddress,driverKeyName,friendlyDeviceName,uniqueID,portIndex);
                    
                    hub.children[portIndex-1] = dev;
                    bus.addDevice(dev, devAddress); 
                    
                }
                
                
                
            }else if(devType == EXTERNAL_HUB){
                //this device is an external hub
                // add this external hub to the bus
                 if(Windows.debugEnum) System.out.print("DeviceImpl.enumerateHubPorts() >>> NOW on Port["+portIndex+"]<< External hub connected" );
               
                String hubName = getExternalHubName(hubHandle,portIndex);
                //String uniqueID = this.getUniqueDeviceID(hubHandle, portIndex);

                if(uniqueID == null) throw new USBException("Could not get the uniqueID of the device! May be Error in native jusb.cpp::getUniqueDeviceID -->  DeviceIoControl (check with external debugger: Sysinternals)");
                
                int devAddress = Integer.parseInt(uniqueID.substring(8,uniqueID.indexOf('&')));
                
                if(Windows.debugDeviceImpl)
                    System.out.println("DeviceImpl.enumerateHubPorts() >>> EXTERNAL_HUB:Port:" + portIndex + "  Address:" + this.getAddress() + "  <<<<<<<<<<    deviceAddress = " + devAddress);
                
                String devPath = "\\\\.\\" + hubName;
                if(Windows.debugDeviceImpl)
                    System.out.println("DeviceImpl.enumerateHubPorts() >>> External Hub Name:" + devPath);
               
                
                 // check if this hub was already on the bus
                
                if(this.oldDevices != null) oldDev = this.oldDevices[devAddress];
                else oldDev = null;
                
                if(oldDev != null ){
                   if(!oldDev.getUniqueDeviceID().equals(uniqueID)){
                    // we have a hub, but it is not identical to
                    // that device we just found on the bus.
                    // Therefore we have to remove the hub and all its
                    // children and notify the bus
                    if(Windows.debugEnum) System.out.println(" >> BEFORE <<  a different EXTERNAL HUB was connected to this (root)hub. Former Hub ID:" + oldDev.getUniqueDeviceID());
   
                    if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> TO DO << remove all former chlidren of the hub and the hub itself too. Add the new hub.");
                    removeAllChildren(oldDev.getChildren());
                    bus.removeDevice(oldDev);
                    // add the new hub
                    // depending on the friendlyDeviceName we create either a JSUB instance or a NonJUSB instance
                    if(friendlyDeviceName != null && friendlyDeviceName.startsWith(Windows.A_JUSB_DRIVER))
                        dev = new JUSB(usb,this,devPath,devAddress,driverKeyName,friendlyDeviceName,uniqueID,portIndex);
                    else
                        dev = new NonJUSB(usb,this,devPath,devAddress,driverKeyName,friendlyDeviceName,uniqueID,portIndex);

                    hub.children[portIndex-1] = dev;
                    bus.addDevice(dev,devAddress); 
                   }
                   else{
                     // the device is still the same one
                     // check if the children are identical
                     // add it again as a child of this hub.
                     hub.children[portIndex-1] = oldDev;  
                    if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> BEFORE <<  the same one : Check now if its children changed.");
                       
                     checkChildren(oldDev);                     
                   }
                }
                else{
                    // there was no hub attached before
                    // add this new hub now to the bus
                    if(Windows.debugEnum) System.out.println(" >> BEFORE <<   no connection");
                    if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> TO DO << Add the new hub. This includes also all children, which are attached to this new hub.");

                    if(friendlyDeviceName != null && friendlyDeviceName.startsWith(Windows.A_JUSB_DRIVER))
                        dev = new JUSB(usb,this,devPath,devAddress,driverKeyName,friendlyDeviceName,uniqueID,portIndex);
                    else
                        dev = new NonJUSB(usb,this,devPath,devAddress,driverKeyName,friendlyDeviceName,uniqueID,portIndex);
                    
                    hub.children[portIndex-1] = dev;
                    bus.addDevice(dev,devAddress); 
                }
             
                
            }else if(devType == NO_DEVICE_CONNECTED){
                // there is no device connected to this port
                
                //check if there was before a device connected to this port
                if(Windows.debugEnum) System.out.print("DeviceImpl.enumerateHubPorts() >>> NOW on Port["+portIndex+"]<< No device connected" );
               

                if(oldChildren != null){
                 oldDev = oldChildren[portIndex-1];   
                }
                else oldDev = null;
             
                 if(oldDev != null ){
                    // we had a device connected to the bus
                    // but not anymore. remove it
                    //check whether the device was a hub or just a simple device
                    if(oldDev.getChildren() == null){
                        if(Windows.debugEnum) System.out.println(" >> BEFORE <<  a DEVICE was connected to this (root)hub.Former Device ID:" + oldDev.getUniqueDeviceID());
                        //just one device
                        bus.removeDevice(oldDev);
                    }
                    else{
                      if(Windows.debugEnum) System.out.println(" >> BEFORE <<  an EXTERNAL HUB was connected to this (root)hub. Former Hub ID:" + oldDev.getUniqueDeviceID());
                       // it's a hub. remove the hub and all its children
                       if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> TO DO << cremove all former chlidren of the hub and the hub itself too." );

                       removeAllChildren(oldDev.getChildren());
                       bus.removeDevice(oldDev);
                    }

                 }
                 else  if(Windows.debugEnum) System.out.println(" >> BEFORE << also no connection");
                
                 // add null to the bus
                hub.children[portIndex-1] = null;

                if(Windows.debugDeviceImpl)
                    System.out.println("DeviceImpl.enumerateHubPorts() >>> EnumPort:" + portIndex + "  Address:" + this.getAddress() + "  Device: null");
                
            }else{
                if(Windows.debugDeviceImpl)
                    System.out.println("DeviceImpl.enumerateHubPorts() >>> EnumPort:" + portIndex + "  Address:" + this.getAddress() + "  Device: null");
                
              throw new USBException("Unknown Device Type! Device Error Reason: " + devType,devType);
            }
        }// end for 
         // at this point we did enuerate all Devices but only if the hub.getAddress is 0
        // this is used for synchronization while scanning the bus
        if(hub.getAddress() == 0) bus.setFinishEnumerating();
     }
    
    /** Returns the device path of that device
     * @return The device path or null
     */    
    public String getDevicePath(){
        return this.usbDevicePath;
    }
    
    /** Returns the friendly device name
     * @return The friendly device name or null
     */    
    public String getDeviceFriendlyName(){
     return this.friendlyDeviceName;   
    }
    
    /** Returns the unique ID
     * @return The unique ID String or null
     */    
    public String getUniqueDeviceID(){
     return this.uniqueDevID;   
    }
    
    private DeviceImpl [] getChildren(){
      return children;
    }
    
    /** Removes all the children from the bus
     * @param children All the children of this hub
     */    
    synchronized void removeAllChildren(DeviceImpl children[]){
     DeviceImpl dev;  
     if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> remove all children of this hub! numOfChildren= " + children.length);
     for(int i=0; i < children.length; i++){
         
       dev = children[i];
       if(dev != null){
          if(dev.getChildren() != null){
              if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> there was a HUB on Port[ "+ dev.getHubPortNum()+ "]. remove it from the list! Device: " + dev.getUniqueDeviceID() + " Addr[" + dev.getAddress() + "]");
              removeAllChildren(dev.getChildren());
          }
          else{
              if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> there was a DEVICE on Port[ "+ dev.getHubPortNum()+ "]. remove it from the list! Device: " + dev.getUniqueDeviceID() + " Addr[" + dev.getAddress() + "]");
              usb.removeDevice(dev);
          }
       }
       else{
         if(Windows.debugEnum) System.out.println("DeviceImpl.enumerateHubPorts() >>> The " + i + ".th DEVICE was null");
       }
     }
    }
    
    /**
     * The hub is identical, we therefore have to check if the children
     * are still the same and when not doing the necessary changes.
     * We get a current handle to the hub and let enumerate the hub 
     * recursivly.
     *
     */
    void checkChildren(DeviceImpl hub) throws IOException{
 
      int hubHandle = hub.openHandle(hub.getDevicePath());
      if(hubHandle == USBException.ERROR_INVALID_HANDLE)
                throw new USBException("INVALID_HANDLE_VALUE for external hub ",hubHandle);
            
      if(Windows.debugDeviceImpl) System.out.println("DeviceImpl.checkChildren() >>> External Hub (hubHandle:" + hubHandle + "numPorts:" + this.numPorts+")");
      this.enumerateHubPorts(usb, hubHandle,hub,hub.getNumPorts());
      // close the hub handle
      this.closeHandle(hubHandle);
    }
    
    /** For debugging purposes */    
    synchronized void showAllChildren(DeviceImpl children[]){
     DeviceImpl dev;  
     if(Windows.debugEnum) System.out.println("DeviceImpl.checkChildren() >>> show all children of this hub! numOfChildren= " + children.length);
     for(int i=0; i < children.length; i++){
         
       dev = children[i];
       if(dev != null){
          if(dev.getChildren() != null){
              if(Windows.debugEnum) System.out.println("DeviceImpl.checkChildren() >>> there is a HUB on Port[ "+ dev.getHubPortNum()+ "].  Device: " + dev.getUniqueDeviceID() + " Addr[" + dev.getAddress() + "]");
              showAllChildren(dev.getChildren());
          }
          else{
              if(Windows.debugEnum) System.out.println("DeviceImpl.checkChildren() >>> there is a DEVICE on Port[ "+ dev.getHubPortNum()+ "]. Device: " + dev.getUniqueDeviceID() + " Addr[" + dev.getAddress() + "]");
          }
       }
       else{
         if(Windows.debugEnum) System.out.println("DeviceImpl.checkChildren() >>> The " + i + ".th DEVICE was null");
       }
     }
    }

       private void printDevices(DeviceImpl [] dev) {
        try{
        if(dev != null){    
            int length;
            if(dev.length < 7) length = dev.length;
            else length = 8;
            for(int i = 0; i < length; i++){
               if(dev[i] != null){
                   if(dev[i].getAddress() == 0)        System.out.println(" [ROOT]          numOfPort:" + dev[i].getNumPorts()+"  Address:" + dev[i].getAddress());
                   else if(dev[i].getNumPorts() > 0)   System.out.println(" [EXTERNAL HUB]  numOfPort:" + dev[i].getNumPorts()+"  Address:" + dev[i].getAddress() + "  UniqueID[" + dev[i].getUniqueDeviceID() + "]");
                   else System.out.println(" [USB DEVICE]                 Address:" + dev[i].getAddress() + "  UniqueID[" + dev[i].getUniqueDeviceID() + "]");
                }
               else System.out.println(" [NULL] ");
            }
          }
        }catch(Exception e){
	    e.printStackTrace ();
        }
    }

       /**
     * print a Byte Array to String
     * main purpose is for debugging matters
     * name: The name of the array, just for clarifying the output
     * radix: 0: decimal 2: binary (default 2)
     * numOfByteLine: the number of Byte printed at one line
     */
    public void printByteArray(byte buf[], String title,int radix, int numOfBytesLine){
       
       if(buf != null){ 
       String temp = null;
       if(radix != 0 && radix != 2 && radix!=16) radix = 2;
       
       if(title != null) System.out.println(title);
       for(int i=0; i<buf.length; i++){
           
           if(radix == 2){
                temp = Integer.toBinaryString((int)buf[i]);
                if(temp.length() > 8){
                    temp = temp.substring(temp.length() - 8, temp.length());
                }
                else if(temp.length() < 8){
                    for(int k=temp.length(); k < 8; k++)   
                    temp = "0" + temp;
                 }
           }
           
           if(radix == 16){
                temp = Integer.toHexString((int)buf[i]);
                if(temp.length() > 2){
                    temp = temp.substring(temp.length() - 2, temp.length());
                }
                if(temp.length() < 2){
                    for(int k=temp.length(); k < 2; k++)   
                    temp = "0" + temp;
                 }
           }
           
           
           if(radix == 0) System.out.print(buf[i] + "   ");
           else System.out.print(temp + "   ");
           
           if(i > 0 && (i % numOfBytesLine) == 0) System.out.println();
       }
       
       System.out.println(); 
       }
       else System.out.println("Buffer is Empty: buf == null!!!"); 
    }    
    
    /** Not implemented yet!<br>
     * Returns always null!
     */
    public String getSpeed() {
        return null;
    }
    
}
