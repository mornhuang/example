/*
 * JUSB.java
 *
 * Created on 30. Juli 2003, 09:50
 */

package usb.windows;

import java.io.IOException;
import usb.core.*;
import usb.util.LangCode;
/**
 * Provides access to the devices using and configured with the jUSB driver.
 * This involves that a device must first be configured with the jUSB driver.
 * How you can replace a driver from an USB device and attach the jUSB driver is
 * completly explained in Appendix D in the JavaUSBAPIfor Windows documentation 
 * found on www.steelbrothers.ch/jusb/.
 *
 * @author  Mike Stahl
 * @version $id: JUSB.java, v1.0 Created on 30. Juli 2003, 09:50 
 */

public class JUSB extends DeviceImpl implements DeviceSPI{
    
   /**
    * the devicepath of the JUSB device.
    * this devicepath gives access to the device
    */    
    private String devicePath;
   
    
    /**
     * contains the number of the selected Configuration
     * in most cases this will be the value 0. If a device
     * has more configuration, it can have a value greater than 0
     */
    private int			selectedConfig = -1;
    
    /**
     * the cuurent configuration the device is configured
     * The JUSB driver initialize the device always with the 
     * configuration number 0
     */
    private Configuration	currentConfig;

   
    
    /** Creates a new instance of JUSB */
 
    
    
    public JUSB(USB bus,DeviceImpl hub, String devicePath, int address,String driverKeyName,String friendlyDevName, String uniqueID,int portIndex)
     throws IOException{
        super(bus, hub, devicePath,address,driverKeyName,friendlyDevName,uniqueID,portIndex);
        
        // find the pathname to connect to this device
        // we need the product id and the vendor id to retrieve the device pathname from the Windows OS
        String pidAndVid = getUniqueDeviceID();
        //extract the pid and vid
        pidAndVid = pidAndVid.toLowerCase().substring(17,34);
        if(Windows.debugJUSB) System.out.println("JUSB.Constructor() >>> pidAndVid = " + pidAndVid);
        // get the device path name from the Windows OS 
        this.devicePath = getDevicePath(pidAndVid);
        if(this.devicePath == null) throw new USBException("Can not get devicePath of device " + this.getUniqueDeviceID());
        if(Windows.debugJUSB) System.out.println("JUSB.Constructor() >>> devicePath = " + this.devicePath);
        int handle;
        try{
            int hubHandle = this.openHandle(hub.getDevicePath());
             if(hubHandle == USBException.ERROR_INVALID_HANDLE)
                throw new USBException("INVALID_HANDLE_VALUE for external hub ",hubHandle);
         
            if(Windows.debugJUSB) System.out.println("getDeviceDescriptor: hubHandle:" + hubHandle +"  Port:" + getHubPortNum());
           
            //get the Device Descriptor
            super.descriptor = new DeviceDescriptor (this, getDeviceDescriptor(hubHandle,getHubPortNum())); 
            // close the root hub handle
            this.closeHandle(hubHandle);
            
            hubHandle = this.openHandle(hub.getDevicePath());
            //get the current Configuration Descriptor
            byte[] buf;
            if(Windows.debugJUSB) System.out.println("getConfigurationDescriptor: hubHandle:" + hubHandle +"  Port:" + getHubPortNum());

            buf = getConfigurationDescriptor(hubHandle,getHubPortNum());
            if (buf == null) System.out.println("No Configuration Descriptor Builded for that device");
            else {
                super.configuration = new Configuration(this, buf);
                currentConfig = super.configuration;
                selectedConfig = 0;
            }
            
            // close the root hub handle
            this.closeHandle(hubHandle);
            
            
           handle = openHandle(this.devicePath);
           
           if(handle == USBException.ERROR_INVALID_HANDLE){
             throw new USBException("INVALID_HANDLE_VALUE for this JUSB device ",handle);
           }
           else if(Windows.debugJUSB) System.out.println("Open Handle to this JUSB device successfully!" + handle);

           closeHandle(handle);
            
            
        }catch(USBException e){
          System.out.println("Could not open the Handle to the device: " + e.getMessage());   
        }
        
    }
    
    
   /**
    * Returns the Device Path for the device with the given pid_vid
    * ?? What happen if we have two identical devices  ??
    * So far we get always the first device listed by SetupDiXxx function
    */ 
    private native String getDevicePath(String pid_vid);
    
    /**
     * I wanted actually call this function just "readControl" but it ended up, that my compiler
     * (MSVC 6.0) did mangeled this name in the DLL. With the aid of dumpbin /EXPORTS C:\WINDOWS\SYSTEM32\jusb.dll
     * I was able to get the linking name and this one looked like this:
     * "?Java_usb_Windows_JUSB_readControl@@YGPAV_jbyteArray@@PAUJNIEnv_@@PAV_jobject@@PAV_jstring@@PAV1@@Z"
     * instead of just "_Java_usb_windows_JUSB_readControl@16"
     * I couldn't find an answer in the various newsgroup but the problem is widely known. I first changed
     * the name of the native method to "readControlS" but the problem remains.
     * As I changed the beginning to JUSB... it worked fine. 
     * This conclusion is not really informativ, but it seems that the compiler can't operate with every name.
     * It may have some reserved words for its own use?!
     *
     *
     * This function is used for the UsbBuilVendorRequest
     */
    private native byte[] JUSBReadControl(String devicePath, byte type,byte request, short value, short index, short length);

    
    /** Not implemented yet!<br><br>
     * Suggestion for implementation:<br>
     * <b>Java Side</b><br>
     * provide a native method which takes as argument the devicepath and the
     * interface number.<br><br>
     * <b>jUSB DLL</b><br>
     * Define a new IOCTL code such as IOCTL_JUSB_CLAIM_INTERFACE in the ioctls.h file.
     * Call DeviceIoControl WinAPI function with the above IOCTL code and provide the
     * interface number in the inptut buffer.<br><br>
     * <b>jUSB driver</b><br>
     * In Control.c implement the IOCTL_JUSB_CLAIM_INTERFACE in that you update the device
     * extension member "InterfaceClaimedInfo".
     */    
    public void claimInterface(int ifnum) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
    /** Not implemented yet!<br><br>
     * Suggestion for implementation:<br>
     * <b>Java Side</b><br>
     * provide a native method which takes as argument the devicepath and the
     * endpoint address.<br><br>
     * <b>jUSB DLL</b><br>
     * Define a new IOCTL code such as IOCTL_JUSB_CLEAR_HALT in the ioctls.h file.
     * Call DeviceIoControl WinAPI function with the above IOCTL code and provide the
     * the endpoint address in the inptut buffer as input to the jUSB driver.<br><br>
     * <b>jUSB driver</b><br>
     * In Control.c implement the IOCTL_JUSB_CLEAR_HALT.
     */    
    public int clearHalt(byte ep) throws IOException {
       throw new IOException("This function is not implemented yet");
    }
    
    /** Not implemented yet! */    
    public Device getChild(int port) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
    /** Not implemented yet!<br><br>
     * Suggestion for implementation:<br>
     * <b>Java Side</b><br>
     * provide a native method which takes as argument the devicepath and the
     * interface number.<br><br>
     * <b>jUSB DLL</b><br>
     * Define a new IOCTL code such as IOCTL_JUSB_GET_CLAIMER in the ioctls.h file.
     * Call DeviceIoControl WinAPI function with the above IOCTL code and provide the
     * the interface number in the inptut buffer as input to the jUSB driver.<br><br>
     * <b>jUSB driver</b><br>
     * In Control.c implement the IOCTL_JUSB_GET_CLAIMER in that you return the 
     * device extension member "ifname".
     */      
    public String getClaimer(int ifnum) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
    /** */      
  
    
    public byte[] getConfigBuf(int n) throws IOException {
        byte [] buf;
        buf = getConfigurationBuffer(this.devicePath,n);
        if(buf == null) throw new IOException("No Configuration Buffer of Configuration " + n  );
        else{
         return buf;   
        }
    }
    
    private native byte[] getConfigurationBuffer(String devicepath,int n);
    
    /** Not implemented yet! */    
    public byte[] readBulk(int ep, int length) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
    /**
     * All USB device respond to request from the host on the device's Default ControlPipe. These request
     * are made using control transfer. The request and the request's parameters are sent to the device in 
     * the Setup packet. The host is responsible for establishing the setupPacket. Every Setup packet has
     * eight bytes. USB device requests are discussed in section 9.3 of the USB 2.0 specification<br><br>
     * <b>Attention</b><br>
     * Not all standard request are yet implemented in the jUSB driver
     */
    public byte[] readControl(byte type, byte request, short value, short index, short length) throws IOException {
        
        if(this.devicePath == null)
            throw new USBException("No devicePath for that device !");
         return JUSBReadControl(this.devicePath,type,request,value,index,length);
    }
    
  
    public byte[] readIntr(int ep, int len) throws IOException {
        return doInterruptTransfer(this.devicePath, ep,len);
    }

    private native byte[] doInterruptTransfer(String devicepath,int ep, int len);
    
     /** Not implemented yet! */    
   
    public void releaseInterface(int ifnum) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
     /** Not implemented yet! */    
  
    public void setInterface(int ifnum, int alt) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
    
    /** Not implemented yet! */    
    
    public void writeBulk(int ep, byte[] buf) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
     /** Not implemented yet! */    
   
    public void writeControl(byte type, byte request, short value, short index, byte[] buf) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
      /** Not implemented yet! */    
  
    public void writeIntr(int ep, byte[] buf) throws IOException {
        throw new IOException("This function is not implemented yet");
    }
 
     /**
      * Not implemented yet! 
      * The return value will always be null!
      */    
  public String getSpeed() {
             return null;
    }      
    
    public Configuration getConfiguration (int index)
    throws IOException
    {
	if (index < 0 || index >= descriptor.getNumConfigurations ())
	    throw new IllegalArgumentException ();
	
	    if (index == selectedConfig && currentConfig != null)
              return currentConfig;
            
            selectedConfig = index;
            return new Configuration (this, getConfigBuf (index));
    }

     public Configuration getConfiguration ()
    throws IOException
    {
	return getConfiguration(selectedConfig);
    }



}
