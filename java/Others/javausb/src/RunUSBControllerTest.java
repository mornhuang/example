/*
 * RunUSBControllerTest.java
 *
 * Created on 23. Juli 2003, 17:31
 */
import usb.core.*;
import usb.windows.*;
import java.io.IOException;
import usb.test.ControllerUSBListener;

/**
 *
 * @author  mike
 */
public class RunUSBControllerTest {
    
     private static final int OBSERVATION_TIME = 1    ;
    
    /** Creates a new instance of RunUSBControllerTest */
    public RunUSBControllerTest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       System.out.println(">>> RunUSBControllerTest: Observation Time = "+ OBSERVATION_TIME +"s <<<");

        try{
           
           Host host = HostFactory.getHost();
           if(host == null) throw new Exception("USB is not available!");
           
           // add the ControllerUSBListener to the host
           host.addUSBListener(new ControllerUSBListener());
        // printUSBStack(host.getBusses()) ;
           
           
        long	start = System.currentTimeMillis ();
        long    end = (OBSERVATION_TIME * 1000) + start;
        while(System.currentTimeMillis() <= end) continue;

         System.out.println(">>> USB Device Stack at the End: <<<");
         printUSBStack(host.getBusses()) ;
         
        }catch (SecurityException e) {
	    System.err.println ("USB permissions problem:");
	    System.err.println (e.getMessage ());
	    System.exit (1);
	}catch(USBException e){
          System.err.println (e.getMessage ());
          e.printStackTrace ();
        }catch(Exception e){
          e.printStackTrace ();
        }


    }
    
    
       /**
    * prints all devices on this bus inclusive the root hub
    */
   private static void printUSBStack(Bus[] busses) {
        try{
          DeviceImpl dev;
          for(int k=0; k < busses.length ; k++){
             System.out.println("\n\nBus[ " + ((USB)busses[k]).getBusNum() + " ]  ");  
             for(int i = 0; i < 5; i++){
                  dev = (DeviceImpl)busses[k].getDevice(i);
                System.out.print(" [ " + i + " ] :  ");
                if(dev != null){
                       if(dev.getAddress() == 0)        System.out.println(" [ROOT]          numOfPort:" + dev.getNumPorts()+"  Address:" + dev.getAddress());
                       else {
                           if(dev.getNumPorts() > 0)   System.out.println(" [EXTERNAL HUB]  numOfPort:" + dev.getNumPorts()+"  Address:" + dev.getAddress());
                           else System.out.println(" [USB DEVICE]    on Port "+dev.getHubPortNum() +  "  Address           : " + dev.getAddress());
                           System.out.println("                                      uniqueID          : " + dev.getUniqueDeviceID());
                           System.out.println("                                      driverKeyName     : " + dev.getDriverKeyName());
                           System.out.println("                                      friendlyDeviceName: " + dev.getFriendlyDeviceName());
                           if (dev instanceof Device) System.out.print("                                      Object Type       : Device");
                           if (dev instanceof DeviceImpl) System.out.print(", DeviceImpl");
                           if (dev instanceof JUSB) System.out.println(", JUSB");
                           if (dev instanceof NonJUSB) System.out.println(", NonJUSB");
                          
                           if(dev instanceof NonJUSB)printDeviceDescriptor(dev);          System.out.println();
                           //printConfigurationDescriptor(dev);   System.out.println();
                           //printInterfaceDescriptors(dev);      System.out.println();
                           //printEndpointDescriptors(dev);       System.out.println();
                          
                               
                           if(dev instanceof JUSB){
                             JUSB jdev = (JUSB)dev;
                             //DeviceDescriptor dd = jdev.getDeviceDescriptor();
                             //String product = dd.getProduct(0);
                            //for(byte bRequest = 0; bRequest < 15; bRequest++){
                             //byte bRequest = 0x06;
                             try{
                                  // Get Device Descriptor though a Setup Request according to USB spec.  9.4.3
                                 // byte [] buf = jdev.readControl((byte)0x80,(byte)bRequest,(short) 0x0100,(short) 0x0000,(short) 0x0012);
                                 //  jdev.printByteArray(buf, "Read Control: ServicePacket Get Device Descriptor: Byte (Hex):", 16, 18);
                                   byte buf [];
                                   buf = ControlMessage.getStandardDescriptor (jdev, Descriptor.TYPE_DEVICE, (byte) 0, 0, 18);
                                   Descriptor descriptorJUSB = new DeviceDescriptor (jdev, buf);
                                   printDeviceDescriptorJUSB(jdev);          System.out.println();
                                   
                                 /* int langid[];
                                   langid = ControlMessage.getLanguages(jdev);
                                   for(int m = 0; m < langid.length; m++){
                                      System.out.println("Language ID: " + langid[m] + "  Hex: " + Integer.toHexString(langid[m]));
                                   }
                                  */
                                  
                                  //buf = ControlMessage.getStatus(jdev, ControlMessage.RECIPIENT_DEVICE | ControlMessage.TYPE_STANDARD, 0, 0, 2);
                                    
                                   buf = ControlMessage.getStatus(jdev, ControlMessage.RECIPIENT_ENDPOINT | ControlMessage.TYPE_STANDARD, 0, 0, 2);
                                   System.out.println("Status : [1]: " +buf[1] + " [0]: " + buf[0]);
                                  //buf = ControlMessage.getStatus(jdev, ControlMessage.RECIPIENT_INTERFACE | ControlMessage.TYPE_STANDARD, 0, 0, 2);
                                   //printConfigurationDescriptor2(jdev.getConfiguration(0));
                                  // printConfigurationDescriptor2(jdev.getConfiguration());
 
                                   //buf = ControlMessage.getStandardDescriptor(jdev, Descriptor.TYPE_CONFIGURATION,(byte)0,0,0);
                                   //Configuration configJUSB = new Configuration (jdev, buf);
                                  //printConfigurationDescriptor2(configJUSB);

                                   printEndpointDescriptors(jdev);
                                  
                                   try{
                                       
                                     buf = jdev.readIntr(0x81, 8);  
                                     printBuffer(buf);
                                     buf = jdev.readIntr(0x82, 32);  
                                     printBuffer(buf);
                                     buf = jdev.readIntr(0x81, 8);  
                                     printBuffer(buf);
                                   
                                   }catch(USBException e){
                                        System.out.println("readIntr ERROR !!!! " + e.getMessage());
                                   }
                                   
                                   
                                   
                                   
                                   
                             }catch(IOException e){
                               System.out.println(e.getMessage() + " ERROR !!!!!!!!!!!!!!!");
                             }
                             
                           //}
                                  
                           }

                       }

                    }
                   else System.out.println(" [NULL] ");
                
              }
          }
        }catch(Exception e){
	    e.printStackTrace ();
        }
     }

   
   public static void printSetupPacket(byte [] sp){
       System.out.println("SetupPacket:");
       for(int i=0; i<sp.length; i++){
           String temp = Integer.toBinaryString((int)sp[i]);
           if(temp.length() > 8){
              temp = temp.substring(temp.length() - 8, temp.length());
           }
           else if(temp.length() < 8){
            for(int k=temp.length(); k < 8; k++)   
               temp = "0" + temp;
           }
           
           System.out.println("["+i+"] : " + sp[i] + " Bits : " + temp);
       }
       
   }
 
    public static void printBuffer(byte [] buf){
       System.out.println("Buffer contains " + buf.length + " bytes:");
       for(int i=0; i<buf.length; i++){
           System.out.println("["+i+"] : " + buf[i] + "  (0x" + Integer.toHexString(buf[i]) + ")");
       }
       
   }
  
   
      public static void printDeviceDescriptor(DeviceImpl dev){
       DeviceDescriptor dd = dev.getDeviceDescriptor();
       if(dd == null) System.out.println("Device Descriptor: null!");
       else{System.out.println("Device Descriptor:");
           
            System.out.println("bcdUSB                   : " + dd.getUSBVersion());
            System.out.println("Device Class             : " + dd.getDeviceClass()  + " ( " + dd.getDeviceClassName() + " )");
            System.out.println("Device Subclass          : " + dd.getDeviceSubClass());
            System.out.println("Device Protocol          : " + dd.getDeviceProtocol());
            System.out.println("Max Packet Size          : " + dd.getMaxPacketSize0());
            System.out.println("idVendor                 : " + dd.getVendorId() + "      0x" + Integer.toHexString(dd.getVendorId()));
            System.out.println("idProduct                : " + dd.getProductId()+ "      0x" + Integer.toHexString(dd.getProductId()));
            System.out.println("bcdDevice release number : " + dd.getDeviceId());
            System.out.println("iManufacturer            : " + dd.getManufacturerStringId());
            System.out.println("iProduct                 : " + dd.getProductStringId());
            System.out.println("iSerialNumber            : " + dd.getSerialStringId());
            System.out.println("NumConfiguration         : " + dd.getNumConfigurations());
       }
       
   }

      public static void printDeviceDescriptorJUSB(JUSB dev){
       DeviceDescriptor dd = dev.getDeviceDescriptor();
       if(dd == null) System.out.println("Device Descriptor: null!");
       else{System.out.println("Device Descriptor:");
           
            System.out.println("bcdUSB                   : " + dd.getUSBVersion());
            System.out.println("Device Class             : " + dd.getDeviceClass()  + " ( " + dd.getDeviceClassName() + " )");
            System.out.println("Device Subclass          : " + dd.getDeviceSubClass());
            System.out.println("Device Protocol          : " + dd.getDeviceProtocol());
            System.out.println("Max Packet Size          : " + dd.getMaxPacketSize0());
            System.out.println("idVendor                 : " + dd.getVendorId() + "      0x" + Integer.toHexString(dd.getVendorId()) + "  " + dd.getManufacturer(0));
            System.out.println("idProduct                : " + dd.getProductId()+ "      0x" + Integer.toHexString(dd.getProductId()) + "  " + dd.getProduct(0));
            System.out.println("bcdDevice release number : " + dd.getDeviceId());
            System.out.println("iManufacturer            : " + dd.getManufacturerStringId());
            System.out.println("iProduct                 : " + dd.getProductStringId());
            System.out.println("iSerialNumber            : " + dd.getSerialStringId() + "  " + dd.getSerial(0));
            System.out.println("NumConfiguration         : " + dd.getNumConfigurations());
       }
       
   }
      
      
      public static void printConfigurationDescriptor(DeviceImpl dev){
       try{
            Configuration cd = dev.getConfiguration();
            if(cd == null) System.out.println("Configuration Descriptor: null!");
            else{System.out.println("Configuration Descriptor:");
                System.out.println("wTotalLength                    : " + cd.getTotalLength());
                System.out.println("bNumInterfaces                  : " + cd.getNumInterfaces());
                System.out.println("bConfigurationValue             : " + cd.getConfigurationValue());
                System.out.print("bmAttributes                      : " + cd.getAttributes());
                if((Configuration.ATTR_REMOTE_WAKEUP & cd.getAttributes()) == Configuration.ATTR_REMOTE_WAKEUP) System.out.print("  Device supports Remote Wakeup!");
                if((Configuration.ATTR_SELF_POWERED & cd.getAttributes()) == Configuration.ATTR_SELF_POWERED) System.out.print("  Device is Self Powered!!");
                System.out.println();
                System.out.print("bMaxPower                         : " + cd.getMaxPower());
                System.out.println("  ( " +(cd.getMaxPower()*2) + "mA)");
           }
       }catch(IOException e){
          System.out.println("ERROR ! Configuration Descriptor: null!"); 
       }
       
   }
 
      public static void printInterfaceDescriptors(DeviceImpl dev){
       try{
            Configuration cd = dev.getConfiguration();
            if(cd == null) System.out.println("Configuration Descriptor: null!");
            else{
                for(int i=0; i < cd.getNumInterfaces(); i++){
                    Interface ifc = cd.getInterface(i, 0);
                    if(ifc == null) System.out.println("Interface Descriptor: null!");
                    else{
                    
                        System.out.println("Interface Descriptor["+ i +"]:");
                        System.out.println("bInterfaceNumber                    : " + ifc.getNumber());
                        System.out.println("bAlternateSetting                   : " + ifc.getAlternateSetting());
                        System.out.println("bNumEndpoints                       : " + ifc.getNumEndpoints());
                        System.out.println("bInterfaceClass                       : " +ifc.getInterfaceClass());
                        System.out.println("bInterfaceSubClass                    : " +ifc.getInterfaceSubClass());
                        System.out.println("bInterfaceProtocol                    : " +ifc.getInterfaceProtocol());
                        System.out.println("iInterface                            : " +ifc.getInterfaceStringId());
                    }
                }
           }
       }catch(IOException e){
          System.out.println("ERROR ! Configuration Descriptor: null! or Interface Descriptor: null!"); 
       }
       
   }
      public static void printConfigurationDescriptor2(Configuration cd){
            if(cd == null) System.out.println("Configuration Descriptor: null!");
            else{System.out.println("Configuration Descriptor:");
                System.out.println("wTotalLength                    : " + cd.getTotalLength());
                System.out.println("bNumInterfaces                  : " + cd.getNumInterfaces());
                System.out.println("bConfigurationValue             : " + cd.getConfigurationValue());
                System.out.print("bmAttributes                      : " + cd.getAttributes());
                if((Configuration.ATTR_REMOTE_WAKEUP & cd.getAttributes()) == Configuration.ATTR_REMOTE_WAKEUP) System.out.print("  Device supports Remote Wakeup!");
                if((Configuration.ATTR_SELF_POWERED & cd.getAttributes()) == Configuration.ATTR_SELF_POWERED) System.out.print("  Device is Self Powered!!");
                System.out.println();
                System.out.print("bMaxPower                         : " + cd.getMaxPower());
                System.out.println("  ( " +(cd.getMaxPower()*2) + "mA)");
           }
   }

    public static void printEndpointDescriptors(DeviceImpl dev){
       try{
            Configuration cd = dev.getConfiguration();
            if(cd == null) System.out.println("Configuration Descriptor: null!");
            else{
                for(int i=0; i < cd.getNumInterfaces(); i++){
                    Interface ifc = cd.getInterface(i, 0);
                    if(ifc == null) System.out.println("Interface Descriptor: null!");
                    else{
                        for(int k=0; k < ifc.getNumEndpoints(); k++){
                        
                            Endpoint ep = ifc.getEndpoint(k);
                            if(ep == null) System.out.println("Endpoint Descriptor: null!");
                            else{
                                System.out.println("Interface["+ i +"]  Endpoint Descriptor["+ k +"]:");
                                System.out.print("bEndpointAdress                    : " + ep.getEndpointAddress());
                                if(ep.isInput()) System.out.println("    IN Pipe Data flows from Device to Host");
                                else System.out.println("    OUT Pipe Data flows from Host to Device");
                                System.out.println("bmAttributes                       : " + ep.getAttributes() + "    Type: " + ep.getType());
                                System.out.println("bMaxPacketSize                     : " + ep.getMaxPacketSize());
                                System.out.println("bInterval                          : " + ep.getInterval());
                                System.out.println("getEndpoint                        : " + ep.getEndpoint() + "  "+Integer.toHexString(ep.getEndpoint()) );
                            }
                        }
                    }
                }
           }
       }catch(IOException e){
          System.out.println("ERROR ! Configuration Descriptor: null! or Interface Descriptor: null!"); 
       }
       
   }

}
