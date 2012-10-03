// SMS Tool for handling SMS communication with a mobile terminal
// (C) 2001 by Stefan Moscibroda

// Class for handling the serial connection and protocol support



import java.io.*;
import java.util.*;
import javax.comm.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.TooManyListenersException;


public class SmsTerminal implements SerialPortEventListener, CommPortOwnershipListener {
    public static final int SC_OK = 0;
    public static final int SC_ERROR = 1;
    public static final int SC_PDU_PARSE_ERROR = 2;

    private SerialPort serialPort;
    private OutputStream outStream;
    private InputStream inStream;
    public IncomingSms rx_sms  = null;
    private Sms_GUI parent;
    private SerialParameters parameters;
    private TextArea OutTextArea;
    private TextArea InTextArea;

    private static final String lfcr = "\r\n";

    public int portStatus = OK;
    private Boolean portStatusLock = new Boolean(true);
    private boolean POLLING_FLAG;
    private String portStatusMsg = "";
    private static final int OK      = 1;
    private static final int WAIT    = 2;
    private static final int ERROR   = 3;
    private static final int WMSG    = 4;
    private static final int RMSG    = 5;
    private static final int ECHO    = 6;
    private static final int TIMEOUT = 7;

    private CommPortIdentifier portId;
    private SerialPort sPort;

    private byte[] readBuffer = new byte[20000]; // serialEvent
    private int bufferOffset = 0;              // serialEvent

    public SmsTerminal(Sms_GUI parent,
			    SerialParameters parameters,
			    TextArea OutTextArea,
			    TextArea InTextArea) {
	this.parent = parent;
	this.parameters = parameters;
	this.OutTextArea = OutTextArea;
	this.InTextArea = InTextArea;
   }


    public void openConnection() throws IOException {

	// Obtain a CommPortIdentifier object for the port you want to open.
	try {
	    portId =
		 CommPortIdentifier.getPortIdentifier(parameters.getPortName());
	} catch (NoSuchPortException e) {
	    throw new IOException(e.getMessage());
	}

	// Open the port represented by the CommPortIdentifier object. Give
	// the open call a relatively long timeout of 30 seconds to allow
	// a different application to reliquish the port if the user
	// wants to.
	 try {
	    sPort = (SerialPort)portId.open("MobileAccess", 500);
	 } catch (PortInUseException e) {
	    throw new IOException(e.getMessage());
	 }


	// Set the parameters of the connection. If they won't set, close the
	// port before throwing an exception.
	try {
	    setConnectionParameters();
	} catch (IOException e) {
	    sPort.close();
	    throw e;
	}

	// Open the input and output streams for the connection. If they won't
	// open, close the port before throwing an exception.
	try {
	    outStream = sPort.getOutputStream();
	    inStream = sPort.getInputStream();
	} catch (IOException e) {
	    sPort.close();
	    throw new IOException("Error opening i/o streams");
	}

      	// Add this object as an event listener for the serial port.
	try {
	    sPort.addEventListener(this);
	} catch (TooManyListenersException e) {
	    sPort.close();
	    throw new IOException("too many listeners added");
	}

	// Set notifyOnDataAvailable to true to allow event driven input.
	sPort.notifyOnDataAvailable(true);

        // Add ownership listener to allow ownership event handling.
	portId.addPortOwnershipListener(this);

        // Turn off command echo
        atCmd("ATE0", 0, 1000);

    }


     /**
    Handles ownership events. If a PORT_OWNERSHIP_REQUESTED event is
    received a dialog box is created asking the user if they are
    willing to give up the port. No action is taken on other types
    of ownership events.
    */
    public void ownershipChange(int type) {
	if (type == CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED) {
	    PortRequestedDialog prd = new PortRequestedDialog(parent, this);
	}
    }


     /**
    Sets the connection parameters to the setting in the parameters object.
    If set fails return the parameters object to origional settings and
    throw exception.
    */
    private void setConnectionParameters() throws IOException {

	// Save state of parameters before trying a set.
	int oldBaudRate = sPort.getBaudRate();
	int oldDatabits = sPort.getDataBits();
	int oldStopbits = sPort.getStopBits();
	int oldParity   = sPort.getParity();
	int oldFlowControl = sPort.getFlowControlMode();

	// Set connection parameters, if set fails return parameters object
	// to original state.
	try {
	    sPort.setSerialPortParams(parameters.getBaudRate(),
				      parameters.getDatabits(),
				      parameters.getStopbits(),
				      parameters.getParity());
	} catch (UnsupportedCommOperationException e) {
	    parameters.setBaudRate(oldBaudRate);
	    parameters.setDatabits(oldDatabits);
	    parameters.setStopbits(oldStopbits);
	    parameters.setParity(oldParity);
	    throw new IOException("Unsupported parameter");
	}

	// Set flow control.
	try {
	    sPort.setFlowControlMode(parameters.getFlowControlIn()
			           | parameters.getFlowControlOut());
	} catch (UnsupportedCommOperationException e) {
	    throw new IOException("Unsupported flow control");
	}
    }



    // To be used to send AT command via the IR/serial link to the mobile
    // device (for standard AT commands use mode=0)
    public synchronized int atCmd(String cmd, int mode, int timeout) {

	synchronized(portStatusLock) {
	    portStatus = WAIT;

	    try {
                 if (mode == 0)
                   // normal end of at command
                   outStream.write((cmd + lfcr).getBytes());
                 if (mode == 1)
                   // end of pdu <CTRL+Z>
                   outStream.write((cmd + "\032").getBytes());
                 if (mode == 2)
                   // no lfcr: used for polling (just echoed back)
                   outStream.write((cmd).getBytes());

       	    } catch (IOException e) { ; }

	    // wait for response from device
	    try {
                // Respond time can vary for different types of AT commands and mobiles!
		portStatusLock.wait(timeout);
	    } catch (InterruptedException e) {  }

  	}

	return OK;
    }

    // Terminates IR/Serial connection to Mobile device
    public void close() {

      // Turn on again command echo
      atCmd("ATE1", 0, 1000);
      if (sPort != null) {
	    try {
		// close the i/o streams.
	    	outStream.close();
	    	inStream.close();
	    } catch (IOException e) {
		System.err.println(e);
	    }

	    // Close the port.
	    sPort.close();

	    // Remove the ownership listener.
	    portId.removePortOwnershipListener(this);
	}

    }

    // Listener Function: Data received from serial link and interpreted
    public void  serialEvent(SerialPortEvent event) {

	switch (event.getEventType()) {

	case SerialPortEvent.BI:
	case SerialPortEvent.OE:
	case SerialPortEvent.FE:
	case SerialPortEvent.PE:
	case SerialPortEvent.CD:
	case SerialPortEvent.CTS:
	case SerialPortEvent.DSR:
	case SerialPortEvent.RI:
	case SerialPortEvent.OUTPUT_BUFFER_EMPTY: break;
	case SerialPortEvent.DATA_AVAILABLE:
            int n;

	    try {
                if (POLLING_FLAG == false) {
		  while ( (n = inStream.available()) > 0) {
		    n = inStream.read(readBuffer, bufferOffset, n);
		    bufferOffset += n;

                    String sbuf = new String(readBuffer,0,bufferOffset-2);

		    // lfcr detected, line ready
		    if(((readBuffer[bufferOffset-1] == 10) &&
		       (readBuffer[bufferOffset-2] == 13))) {

			lineReceived(sbuf);
			bufferOffset = 0;
		    }
                  }
               }
               else portStatus = ECHO;

	    } catch (IOException e) { ; }

	    break; // end: case SerialPortEvent.DATA_AVAILABLE:
	}
    }

    // used for analyzing mobile response
    private void lineReceived(String buffer) {

        String response;
	StringTokenizer st = new StringTokenizer(buffer, "\r\n");
        rx_sms = null;
	synchronized(portStatusLock) {
	    while (st.hasMoreTokens()) {
        	response = st.nextToken();
                InTextArea.append(response + lfcr);
		if (response.startsWith("OK")) {
		   portStatus = OK;
                   portStatusLock.notify();
                   InTextArea.append(lfcr);
                } else if (response.startsWith(">")) {
		    portStatus = WMSG;
		} else if (response.startsWith("ERROR")) {
		    portStatus = ERROR;
		} else if (response.startsWith("+CME ERROR") ||
			   response.startsWith("+CMS ERROR")) {
		    portStatus = ERROR;
		    portStatusMsg = response;
		}

                else if (response.startsWith("07") || response.startsWith("00"))  {

		    try {
			rx_sms = new IncomingSms(response);
                        portStatusLock.notify();

                        InTextArea.append("\r\nSMS received: " + rx_sms.toString()+"\r\n");

		    } catch (PduParseException e) {
                        System.err.println("Error receiving SMS message: unable to parse PDU:\r\n"+
					       response);
                        portStatus = ERROR;
		    }
		}
                else {  }
	    }

	}
	return;
    }



    // Send an SMS to number (using SMS central having number "smsc_number")
    public synchronized void SendMessage(String number, String smsc_number, String msg) {

         // to specify specific SMS settings (character coding, sms type, ...)
         int tpPid = 0x00;
         int tpDcs = 0x00;

         if(number.startsWith("+"))
	    number = number.substring(1);
         if(smsc_number.startsWith("+"))
	    smsc_number = smsc_number.substring(1);

         try {

           OutgoingSms pdumsg = new OutgoingSms(number, smsc_number, msg, tpPid, tpDcs);

           String cmd = "AT+CMGS=" + pdumsg.length();
           String pdu = pdumsg.toString();

           atCmd(cmd,0,500); // Delay needed -> Waiting for ">" Prompt, otherwise Error MSG!
                             // For some mobiles  > 1000 ms!
           atCmd(pdu,1,1500);

       } catch (Exception e) {
           System.err.println("Irregular SMS format");      }

    }


    // Write SMS to phone/SIM memory (tranmit format)
    public synchronized void WriteTxMessage(String number, String smsc_number, String msg) {

         // to specify specific SMS settings (character coding, sms type, ...)
         int tpPid = 0x00;
         int tpDcs = 0x00;

         if(number.startsWith("+"))
	    number = number.substring(1);
         if(smsc_number.startsWith("+"))
	    smsc_number = smsc_number.substring(1);

         try {

           OutgoingSms pdumsg = new OutgoingSms(number, smsc_number, msg, tpPid, tpDcs);

           String cmd = "AT+CMGW=" + pdumsg.length();
           String pdu = pdumsg.toString();

           atCmd(cmd,0,500);

           atCmd(pdu,1,1500);

      } catch (Exception e) {
           System.err.println("Irregular SMS format");      }

    }


    // Write SMS to phone/SIM memory (received format)
    public synchronized void WriteRxMessage(String number, String smsc_number, String msg) {

         // to specify specific SMS settings (character coding, sms type, ...)
         int tpPid = 0x00;
         int tpDcs = 0x00;

         if(number.startsWith("+"))
	    number = number.substring(1);
         if(smsc_number.startsWith("+"))
	    smsc_number = smsc_number.substring(1);

         try {

           OutgoingSms pdumsg = new OutgoingSms(number, smsc_number, msg, tpPid, tpDcs);
           GregorianCalendar time = new GregorianCalendar();
           pdumsg.transform_to_received_SMS(time);
           String pdu_r = pdumsg.toString();

           // Important: PDU of SMS to be stored in RECEIVED (,0) folder
           // must follow received SMS PDU format!
           String cmd_r = "AT+CMGW=" + pdumsg.length() + ",0";

           atCmd(cmd_r,0,500);

           atCmd(pdu_r,1,1500);

       } catch (Exception e) {
           System.err.println("Irregular SMS format");      }

    }

   // Read SMS from Mobile/SIM storage having index i
    public synchronized void ReadSMS(int i) {

         atCmd("AT+CMGR=" + i, 0, 1000);  // Receive SMS

    }


   // Delete SMS from Mobile/SIM storage having index i
    public synchronized void DeleteSMS(int i) {

         atCmd("AT+CMGD=" + i, 0, 1000);  // Delete SMS

    }

}
