// Serial SMS Client for handling SMS communication with a mobile terminal
// (C) 2001 by Stefan Moscibroda


// Exception definition class

public class PduParseException extends Exception {

    PduParseException(String msg) {
	super(msg);
    }

    public static void main(String[] args) {

	try {
	    throw new PduParseException("exception test");
	} catch (PduParseException e) {
	    System.err.println("parse error: " + e);
	}

    }

}


