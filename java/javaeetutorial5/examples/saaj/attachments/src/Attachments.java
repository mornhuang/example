/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.AttachmentPart;
import java.net.URL;
import java.util.Iterator;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.activation.DataHandler;


public class Attachments {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        String line = "";

        try {
            // Create message factory
            MessageFactory messageFactory = MessageFactory
                .newInstance();

            // Create a message
            SOAPMessage message = messageFactory.createMessage();

            // Get the SOAP header and body from the message
            // and remove the header
            SOAPHeader header = message.getSOAPHeader();
            SOAPBody body = message.getSOAPBody();
            header.detachNode();

            // Create attachment part for text
            AttachmentPart attachment1 = message.createAttachmentPart();

            fr = new FileReader(new File(args[0]));
            br = new BufferedReader(fr);

            String stringContent = "";
            line = br.readLine();

            while (line != null) {
                stringContent = stringContent.concat(line);
                stringContent = stringContent.concat("\n");
                line = br.readLine();
            }

            attachment1.setContent(stringContent, "text/plain");
            attachment1.setContentId("attached_text");

            message.addAttachmentPart(attachment1);

            // Create attachment part for image
            URL url = new URL("file:///../xml-pic.jpg");
            DataHandler dataHandler = new DataHandler(url);
            AttachmentPart attachment2 = message.createAttachmentPart(
                        dataHandler);
            attachment2.setContentId("attached_image");

            message.addAttachmentPart(attachment2);

            // Now extract the attachments
            Iterator iterator = message.getAttachments();

            while (iterator.hasNext()) {
                AttachmentPart attached = (AttachmentPart) iterator.next();
                String id = attached.getContentId();
                String type = attached.getContentType();
                System.out.println(
                        "Attachment " + id + " has content type " + type);

                if (type.equals("text/plain")) {
                    Object content = attached.getContent();
                    System.out.println("Attachment contains:\n" + content);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.toString());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("I/O exception: " + e.toString());
            System.exit(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
