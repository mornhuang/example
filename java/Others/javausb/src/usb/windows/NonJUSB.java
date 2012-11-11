/*
 * NonJUSB.java
 *
 * Created on 30. Juli 2003, 10:37
 */

package usb.windows;
import java.io.IOException;
import usb.core.*;
import usb.util.LangCode;
/**
 * Provides all functionality from the DeviceImpl class.
 * A device in that class is not using the jUSB driver and is therefore
 * not suited for the jUSB API
 *
 * @author  Mike Stahl
 * @version $id: NonJUSB.java, v1.0 Created on 30. Juli 2003, 10:37 
 */
public class NonJUSB extends DeviceImpl implements DeviceSPI {
   
   /** 
    * Create a new instance of NonJUSB root hub 
    * So far we can not modify the root hub
    */
   NonJUSB(USB bus, String devicePath, int address,DeviceImpl devices[])
   throws IOException{
      super(bus,devicePath,address,devices); 
   }
    
    
    /** 
     * Creates a new instance of NonJUSB devices, such as an external hub or an usb device
     */
    public NonJUSB(USB bus,DeviceImpl hub, String devicePath, int address,String driverKeyName,String friendlyDevName, String uniqueID,int portIndex)
     throws IOException{
        super(bus, hub, devicePath,address,driverKeyName,friendlyDevName,uniqueID,portIndex);
        
        
        try{
            
           // we get the device descriptor for this devices as well 
           int hubHandle = this.openHandle(hub.getDevicePath());
           if(hubHandle == USBException.ERROR_INVALID_HANDLE)
                throw new USBException("INVALID_HANDLE_VALUE for external hub ",hubHandle);
         
           if(Windows.debugJUSB) System.out.println("getDeviceDescriptor: hubHandle:" + hubHandle +"  Port:" + getHubPortNum());
           
                super.descriptor = new DeviceDescriptor (this, getDeviceDescriptor(hubHandle,getHubPortNum())); 
             // close the root hub handle
            this.closeHandle(hubHandle);
            
            hubHandle = this.openHandle(hub.getDevicePath());
            //get the current Configuration Descriptor
            byte[] buf;
            if(Windows.debugJUSB) System.out.println("getConfigurationDescriptor: hubHandle:" + hubHandle +"  Port:" + getHubPortNum());
            buf = getConfigurationDescriptor(hubHandle,getHubPortNum());
            if (buf == null) System.out.println("No Configuration Descriptor Builded for that device");
            else super.configuration = new Configuration(this, buf);
            
            // close the root hub handle
            this.closeHandle(hubHandle);

        }catch(IOException e){
          System.out.println("Could not open the Handle to the device: " + e.getMessage());   
        }

        
        
    }
    
  
    /** Never available, because this device does not use the jUSB driver. */    
    public void claimInterface(int ifnum) throws IOException {
        throw new IOException("NonJusb.claimInterface()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
     /** Never available, because this device does not use the jUSB driver. */    
   
    public int clearHalt(byte ep) throws IOException {
        throw new IOException("NonJusb.clearHalt()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
     /** Never available, because this device does not use the jUSB driver. */    
   public String getClaimer(int ifnum) throws IOException {
        throw new IOException("NonJusb.getClaimer()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
     /** Never available, because this device does not use the jUSB driver. */    
   public byte[] getConfigBuf(int n) throws IOException {
        throw new IOException("NonJusb.getConfigBuf()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
     /** Never available, because this device does not use the jUSB driver. */    
   public byte[] readBulk(int ep, int length) throws IOException {
        throw new IOException("NonJusb.readBulk()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
     /** Never available, because this device does not use the jUSB driver. */    
   public byte[] readControl(byte type, byte request, short value, short index, short length) throws IOException {
        throw new IOException("NonJusb.readControl()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
      /** Never available, because this device does not use the jUSB driver. */    
  public byte[] readIntr(int ep, int len) throws IOException {
        throw new IOException("NonJusb.readIntr()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
      /** Never available, because this device does not use the jUSB driver. */    
  public void releaseInterface(int ifnum) throws IOException {
        throw new IOException("NonJusb.releaseInterface()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
      /** Never available, because this device does not use the jUSB driver. */    
  public void setInterface(int ifnum, int alt) throws IOException {
        throw new IOException("NonJusb.setInterface()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
       /** Never available, because this device does not use the jUSB driver. */    
 public void writeBulk(int ep, byte[] buf) throws IOException {
        throw new IOException("NonJusb.writeBulk()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
      /** Never available, because this device does not use the jUSB driver. */    
  public void writeControl(byte type, byte request, short value, short index, byte[] buf) throws IOException {
        throw new IOException("NonJusb.cwriteControl()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
    
        /** Never available, because this device does not use the jUSB driver. */    
public void writeIntr(int ep, byte[] buf) throws IOException {
        throw new IOException("NonJusb.writeIntr()   >>> NO JUSB DRIVER SUPPORT! Install jusb driver for that device to use this method.");
    }
 
       /** Never available, because this device does not use the jUSB driver. */    
 public String getSpeed() {
        return null;
 }    
    
}
